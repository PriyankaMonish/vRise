package com.vrise.bazaar.newpages.subscriber.menu.todo.recipts

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.AsyncTask
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.ConsAndroidCallback
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.ReceiptListItem
import com.vrise.bazaar.newpages.utilities.models.submodels.ReceiptidsItem
/*import io.reactivex.disposables.Disposable
import zlc.season.rxdownload3.core.**/
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import android.os.Environment
import android.os.Handler
import androidx.core.app.NotificationCompat
import android.util.Log
import android.widget.ProgressBar
import com.google.gson.GsonBuilder
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.retrofit.DownloadClient
import com.vrise.bazaar.newpages.retrofit.DownloadService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import java.io.*
import java.lang.ref.WeakReference
import java.util.*

class RecepitsList(val activity: FragmentActivity, val dataItem: ArrayList<ReceiptListItem>?, val apiService: ApiService?) : RecyclerView.Adapter<RecepitsList.ViewHolder>() {

    /*private var disposable: Disposable? = null
    private var currentStatus = Status()*/

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_receipts, p0, false))
    }

    override fun getItemCount(): Int {
        return dataItem!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        /*p0.setsData(dataItem!![p0.adapterPosition], activity)*/
        p0.textView120.text = dataItem!![p0.adapterPosition].billCode.toString()
        p0.imageView25.setOnClickListener {
            p0.progressBar24.visibility = View.VISIBLE
            sendMails(activity, dataItem[p0.adapterPosition], p0)
        }
        /*p0.imageView26.setOnClickListener {
            if (dataItem[p0.adapterPosition].billLink.toString().isNotBlank()) {
                print(dataItem[p0.adapterPosition].billLink.toString())
                val http = dataItem[p0.adapterPosition].billLink.toString().substring(0, 7)
                val www = "www."
                val baki = dataItem[p0.adapterPosition].billLink.toString().substring(7, dataItem[p0.adapterPosition].billLink.toString().length)
                val mail = "$http$www$baki"
                try {
                    //RetrieveFeedTask().execute(mail)
                } catch (e: Exception) {
                    toastit(activity, "Download Failed")
                    e.printStackTrace()
                }
            }
        }*/

        p0.imageView26.setOnClickListener {
            p0.progressBar25.visibility = View.VISIBLE
            try {
                if (!dataItem[p0.adapterPosition].billLink.isNullOrBlank()) {
                    val http = dataItem[p0.adapterPosition].billLink.toString().substring(0, 7)
                    val www = "www."
                    val baki = dataItem[p0.adapterPosition].billLink.toString().substring(7, dataItem[p0.adapterPosition].billLink.toString().length)
                    val mail = "$http$www$baki"

                    val arrayString: ArrayList<String> = ArrayList()
                    arrayString.add(mail)

                    downloadFile(arrayString)
                    /*val service = Intent(activity, DownloadService()::class.java)
                    val bundle = Bundle()
                    bundle.putStringArrayList(Constants.ID, arrayString)
                    service.putExtras(bundle)
                    activity.startService(service)*/


                }
            } catch (e: Exception) {

                e.printStackTrace()
            }
            Handler().postDelayed({
                p0.progressBar25.visibility = View.GONE
            }, 300)

        }
    }


    private fun downloadFile(arrayString: ArrayList<String>) {
        val service = DownloadClient.getClient().create(DownloadService::class.java)

        val call = service.downloadFile(arrayString[0])

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "server contacted and has file");
                    var notificationBuilder: NotificationCompat.Builder? = null
                    var notificationManager: NotificationManager? = null
                    notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

                    val i = generateId()
                    notificationBuilder = NotificationCompat.Builder(activity)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Downloading")
                            .setContentText("Please wait...")
                            .setProgress(100, 0, false)
                            .setAutoCancel(true)
                    notificationManager?.notify(i, notificationBuilder.build())

                    DownloadTask(activity, response.body()!!, arrayString[0].substring(arrayString[0].lastIndexOf('/') + 1, arrayString[0].length), i, notificationBuilder, notificationManager).execute()

                } else {
                    Log.d(TAG, "server contact failed");

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "error");
            }
        })
    }

    private fun generateId(): Int {
        return Random().nextInt(100)
    }

    class DownloadTask internal constructor(context: FragmentActivity, val responseBody: ResponseBody, val substring: String, val id: Int, val notificationBuilder: NotificationCompat.Builder, val notificationManager: NotificationManager?) : AsyncTask<Void, Void, Boolean>() {

        var weakReference = WeakReference<Context>(context)
        private var start_Time = 0L

        init {
            start_Time = System.currentTimeMillis()
        }

        override fun doInBackground(vararg voids: Void?): Boolean {
            val writtenToDisk = writeResponseToDisk(responseBody, id)
            Log.d(TAG, "file download was a success? $writtenToDisk")
            return writtenToDisk
        }

        override fun onPostExecute(result: Boolean) {
            if (result) {
                Log.d(TAG, "FileDownloaded")
                onDownloadComplete(id)
            }
        }

        private fun onDownloadComplete(id: Int) {
            notificationBuilder.setProgress(100, 100, false)
            notificationBuilder.setContentTitle("Download Complete")
            notificationBuilder.setContentText("Completed")
            notificationBuilder.setAutoCancel(true)

            /*try {
                val path1 = Environment.getExternalStorageDirectory().toString() + "/PayToAllBills" + "/" + "${start_Time.toString()}_$substring"
                val file = File(path1)
                val uri_path = FileProvider.getUriForFile(weakReference.get()!!, BuildConfig.APPLICATION_ID + ".provider", file)
                val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(path1))
                val intent = Intent(Intent.ACTION_VIEW)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.type = mimeType
                intent.setDataAndType(uri_path, mimeType)
                val pIntent = PendingIntent.getActivity(weakReference.get(), System.currentTimeMillis().toInt(), intent, 0)
                notificationBuilder.setContentIntent(pIntent)
            } catch (e: Exception) {
                e.printStackTrace()
            }*/

            notificationManager?.notify(id, notificationBuilder.build())

            Handler().postDelayed({
                notificationManager?.cancel(id)
            }, 1000)
        }

        fun writeResponseToDisk(body: ResponseBody?, id: Int): Boolean {
            try {
                //change the file location/name according to your needs

                val folderPath = Environment.getExternalStorageDirectory().toString() + "/PayToAllBills"
                val futureStudioFile = File(folderPath)
                if (!futureStudioFile.exists()) {
                    File(folderPath).mkdirs()
                }

                val startTime = System.currentTimeMillis()
                val futureStudioIconFile = File(folderPath, "${start_Time}_$substring")


                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null
                var timeCount = 1
                try {
                    val fileReader = ByteArray(4096)
                    val fileSize = body?.contentLength()?.toInt() ?: 0
                    var fileSizeDownloaded: Long = 0
                    inputStream = body?.byteStream()
                    outputStream = FileOutputStream(futureStudioIconFile)
                    while (true) {
                        val read = inputStream!!.read(fileReader)
                        if (read == -1) {
                            break
                        }
                        outputStream.write(fileReader, 0, read)
                        fileSizeDownloaded += read.toLong()
                        val currentTime = System.currentTimeMillis() - startTime
                        if (currentTime > 10 * timeCount) {
                            sendNotification(fileSizeDownloaded, id, (fileSizeDownloaded / fileSize) / 100)
                            timeCount++
                        }
                        Log.d(TAG, "file download: $fileSizeDownloaded $futureStudioIconFile of $fileSize")
                    }
                    outputStream.flush()
                    return true
                } catch (e: IOException) {
                    e.printStackTrace()
                    return false
                } finally {
                    inputStream?.close()
                    outputStream?.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }
        }

        private fun sendNotification(fileSizeDownloaded: Long, id: Int, percent: Long) {
            notificationBuilder.setOngoing(true)
                    .setContentTitle("Downloading")
                    .setContentText("Downloading file $fileSizeDownloaded KB")
                    .setProgress(100, percent.toInt(), false)
            notificationManager?.notify(id, notificationBuilder.build())
        }
    }


    private fun sendMails(activity: FragmentActivity, receiptListItem: ReceiptListItem, p0: ViewHolder) {
        var ids: ArrayList<ReceiptidsItem>? = null
        ids = ArrayList()
        ids.add(ReceiptidsItem(receiptListItem.id))

        println(GsonBuilder().setPrettyPrinting().create().toJson(Request(
                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
                receiptids = ids
        )))

        apiService?.mailreceipts(Request(
                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
                receiptids = ids
        ))?.enqueue(object : ConsAndroidCallback(activity, null, object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {

            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                if (state) {
                    p0.progressBar24.visibility = View.GONE
                } else {
                    p0.progressBar24.visibility = View.GONE
                }
            }

            override fun failed(t: Throwable) {
                p0.progressBar24.visibility = View.GONE
                t.printStackTrace()
            }
        }) {})
    }

    class RetrieveFeedTask : AsyncTask<String, Void, Boolean>() {

        override fun doInBackground(vararg urls: String): Boolean? {
            return downloadDatas(urls.toString())
        }

        fun downloadDatas(toString: String): Boolean {
            try {
                val path = toString
                val targetFileName = ""

                val eof = false
                val u = URL(path)
                val c: HttpURLConnection = u.openConnection() as HttpURLConnection
                c.requestMethod = "GET"
                c.doOutput = true
                c.connect()
                val outputFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), targetFileName)

                val f = FileOutputStream(File("$outputFile")) //c:\junk\$targetFileName
                val ins = c.inputStream
                val buffer = ByteArray(1024)
                var len1 = 0
                while ({ len1 = ins.read(buffer); len1 }() > 0) {
                    f.write(buffer, 0, len1)
                }
                /*
                while ((ins.read(buffer)) > 0) {
                    len1 = ins.read(buffer)
                    f.write(buffer, 0, len1)
                }
                */
                f.close()

                return true
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return false
            } catch (e: ProtocolException) {
                e.printStackTrace()
                return false
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                return false
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }
        }


        override fun onPostExecute(status: Boolean) {
            if (status) {

            } else {

            }
        }
    }

    /*override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }*/

    /*override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach()
    }*/

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView120 = itemView.findViewById<TextView>(R.id.textView120)
        val imageView25 = itemView.findViewById<ImageView>(R.id.imageView25)
        val imageView26 = itemView.findViewById<ImageView>(R.id.imageView26)
        val progressBar24 = itemView.findViewById<ProgressBar>(R.id.progressBar24)
        val progressBar25 = itemView.findViewById<ProgressBar>(R.id.progressBar25)

        private var customMission: ReceiptListItem? = null
        private var activity: FragmentActivity? = null

        /*var disposable: Disposable? = null
        var currentStatus: Status? = null*/

        /*init {
            imageView26.setOnClickListener {
                progressBar25.visibility = View.VISIBLE
                *//*
                                when (currentStatus) {
                                    is Normal -> start()
                                    is Suspend -> start()
                                    is Failed -> start()
                                    is Deleted -> start()
                                    is Downloading -> stop()
                                    is Succeed -> {
                                        if (activity != null) {
                                            toastit(activity!!, "success")
                                        }
                                    }
                                }
                            *//*
                try {
                    print(customMission!!.billLink.toString())
                    if (!customMission!!.billLink.isNullOrBlank()) {
                        val http = customMission!!.billLink.toString().substring(0, 7)
                        val www = "www."
                        val baki = customMission!!.billLink.toString().substring(7, customMission!!.billLink.toString().length)
                        val mail = "$http$www$baki"

                        val arrayString : ArrayList<String> = ArrayList()
                        arrayString.add(mail)

                        val service = Intent(activity, DownloadService()::class.java)
                        val bundle = Bundle()
                        bundle.putStringArrayList(Constants.ID, arrayString)
                        service.putExtras(bundle)
                        activity!!.startService(service)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                progressBar25.visibility = View.GONE
            }
        }*/

        /*private fun start() {
            RxDownload.start(customMission!!.billLink!!).subscribe()
        }

        private fun stop() {
            RxDownload.stop(customMission!!.billLink!!).subscribe()
        }

        fun setsData(receiptListItem: ReceiptListItem, activity: FragmentActivity) {
            this.customMission = receiptListItem
            this.activity = activity
        }

        fun onAttach() {
            disposable = RxDownload.create(customMission!!.billLink!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        currentStatus = it
                        setActionText(it)
                    }
        }

        fun onDetach() {
            dispose(disposable)
        }

        private fun setActionText(status: Status) {
            val text = when (status) {
                is Normal -> "Normal"
                is Suspend -> "Susp"
                is Waiting -> "Wait"
                is Downloading -> "Downloading"
                is Failed -> "Failed"
                is Succeed -> "Success"
                else -> ""
            }
            if (activity != null) {
                *//*toastit(activity!!, text)*//*
            }
        }*/

    }


}