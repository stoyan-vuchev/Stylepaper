package com.stoyanvuchev.stylepaper.feature_wallpapers.framework.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.feature_wallpapers.framework.manager.DownloadProgressManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class WallpaperDownloadService : Service() {

    companion object {
        private const val CHANNEL_ID = "wallpaper_download_channel"
    }

    @Inject
    lateinit var progressManager: DownloadProgressManager
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        val url = intent?.getStringExtra("url") ?: return START_NOT_STICKY
        val id = intent.getStringExtra("id") ?: return START_NOT_STICKY
        val fileName = intent.getStringExtra("fileName") ?: return START_NOT_STICKY

        val notificationId = id.hashCode()
        createNotificationChannel()
        startForeground(notificationId, buildNotification(0))

        scope.launch {

            downloadFile(url, fileName) { progress ->
                updateNotification(notificationId, progress)
                progressManager.update(id, progress)
            }

        }.invokeOnCompletion {

            progressManager.remove(id)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(STOP_FOREGROUND_REMOVE)
            }

            showDownloadCompleteNotification(notificationId, fileName)
            stopSelf(startId)

        }

        return START_NOT_STICKY

    }

    private fun buildNotification(progress: Int): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Downloading Wallpaper")
            .setContentText("$progress%")
            .setSmallIcon(R.drawable.download_icon)
            .setOngoing(true)
            .setProgress(100, progress, false)
            .build()
    }

    private fun updateNotification(notificationId: Int, progress: Int) {
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, buildNotification(progress))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID, "Downloads",
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)

        }
    }

    private suspend fun downloadFile(
        url: String,
        fileName: String,
        onProgress: (Int) -> Unit
    ) = try {

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        val response = client.newCall(request).execute()
        val body = response.body

        val total = body.contentLength()
        val input = body.byteStream()

        val dir = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "Stylepaper"
        )

        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, fileName)

        val output = FileOutputStream(file)

        val buffer = ByteArray(8 * 1024)
        var downloaded = 0L
        var read: Int

        while (input.read(buffer).also { read = it } != -1) {
            output.write(buffer, 0, read)
            downloaded += read
            val progress = ((downloaded * 100) / total).toInt()
            onProgress(progress)
        }

        output.flush()
        output.close()
        input.close()

    } catch (e: Exception) {
        Log.e("WallpaperDownloadService", e.message, e)
        withContext(Dispatchers.Main) {
            Toast.makeText(
                this@WallpaperDownloadService.applicationContext,
                "Wallpaper download failed!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showDownloadCompleteNotification(
        notificationId: Int,
        fileName: String
    ) {
        getSystemService(NotificationManager::class.java)
            .notify(
                notificationId,
                NotificationCompat.Builder(
                    this@WallpaperDownloadService,
                    CHANNEL_ID
                )
                    .setContentTitle("Download complete")
                    .setContentText(fileName)
                    .setSmallIcon(R.drawable.wallpaper_icon)
                    .setAutoCancel(true)
                    .build()
            )
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        stopSelf()
        super.onDestroy()
    }

}
