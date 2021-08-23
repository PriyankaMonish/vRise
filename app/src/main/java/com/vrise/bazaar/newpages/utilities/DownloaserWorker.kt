package com.vrise.bazaar.newpages.utilities

class DownloaserWorker/*(var context: Context, params: WorkerParameters) : Worker(context, params)*/ {/*

    companion object {
        var URL = "URL"
    }

    private var totalFileSize: Int = 0
    private var notificationBuilder: NotificationCompat.Builder? = null
    private var notificationManager: NotificationManager? = null

    override fun doWork(): Result {

        val url = inputData.getStringArray(URL)

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationBuilder = NotificationCompat.Builder(applicationContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Downloading")
                .setContentText("Please wait...")
                .setProgress(100, 0, false)
                .setAutoCancel(false)
        notificationManager!!.notify(1, notificationBuilder!!.build())

        initDownload(url)

        return Result.SUCCESS
    }

    private fun initDownload(url: Array<String>?) {
        try {
            //new Random().nextInt(100)
            for(i in 0 until url!!.size){
                downloadFile(url[i].substring(url[i].lastIndexOf('/') + 1, url[i].length), 1, url[i])
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun downloadFile(filename: String, id: Int, vararg body: String) {
        var count = 0
        val data = ByteArray(1024 * 4)
        val urls = URL(body[0])
        val connection = urls.openConnection()
        connection.connect()
        val fileSize = connection.contentLength.toLong()
        val bis = BufferedInputStream(urls.openStream(), 1024 * 8)
        val outputFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename)
        val output = FileOutputStream(outputFile)
        var total: Long = 0
        val startTime = System.currentTimeMillis()
        var timeCount = 1
        while ({ count = bis.read(data); count }() != -1) {
            total += count.toLong()
            totalFileSize = (fileSize / Math.pow(1.0, 2.0)).toInt() / 1000
            val current = (Math.round(total / Math.pow(1.0, 2.0)) / 1000).toDouble()
            val progress = (total * 100 / fileSize).toInt()
            val currentTime = System.currentTimeMillis() - startTime
            val download = Download()
            download.totalFileSize = totalFileSize
            if (currentTime > 10 * timeCount) {
                download.currentFileSize = current.toInt()
                download.progress = progress
                sendNotification(download, id)
                timeCount++
            }
            output.write(data, 0, count)
        }
        onDownloadComplete(filename, id)
        output.flush()
        output.close()
        bis.close()
    }

    private fun sendNotification(download: Download, id: Int) {
        sendIntent(download, id)
        notificationBuilder!!.setOngoing(true)
                .setContentTitle("Downloading")
                .setContentText("Downloading file " + download.currentFileSize + "/" + totalFileSize + " KB")
                .setProgress(100, download.progress, false)
        notificationManager!!.notify(id, notificationBuilder!!.build())
    }

    private fun onDownloadComplete(filename: String, id: Int) {
        try {

            val download = Download()
            download.progress = 100
            sendIntent(download, id)

            *//*notificationManager.cancel(id);*//*
            notificationBuilder!!.setProgress(0, 0, false)
            notificationBuilder!!.setContentTitle("Download Complete")
            notificationBuilder!!.setContentText("Tap to open")

            val path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + filename

            val file = File(path1)
            val uri_path = Uri.fromFile(file)
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(path1))

            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = mimeType
            intent.setDataAndType(uri_path, mimeType)
            val pIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), intent, 0)

            notificationBuilder!!.setContentIntent(pIntent)
            notificationManager!!.notify(id, notificationBuilder!!.build())

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun sendIntent(download: Download, id: Int) {
        val intent = Intent("MESSAGE_PROGRESS")
        intent.putExtra("download", download)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
*/}