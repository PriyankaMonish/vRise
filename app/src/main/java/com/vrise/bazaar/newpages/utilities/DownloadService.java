package com.vrise.bazaar.newpages.utilities;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.vrise.R;
import com.vrise.bazaar.ex.app.BaseApp;
import com.vrise.bazaar.newpages.utilities.models.Download;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

public class DownloadService extends Service {
    public ArrayList<String> url = new ArrayList<>();

    private final class ServiceHandler extends Handler {
        ArrayList<String> urlc;

        ServiceHandler(Looper looper, ArrayList<String> urlb) {
            super(looper);
            urlc = urlb;
        }

        @Override
        public void handleMessage(Message msg) {
            BaseApp x = (BaseApp) getApplicationContext();
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Downloading")
                    .setContentText("Please wait...")
                    .setProgress(100, 0, false)
                    .setAutoCancel(true);
            Log.i("Paras", String.valueOf(url));
            for (int i = 0; i < url.size(); i++) {
                int id = new Random().nextInt(100);
                notificationManager.notify(id, notificationBuilder.build());
                initDownload(url.get(i), id);
            }
        }
    }

    @Override
    public void onCreate() {
        // Get the HandlerThread's Looper and use it for our Handler
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        if (intent.getExtras() != null) {
            url = intent.getExtras().getStringArrayList(Constants.ID);
            HandlerThread thread = new HandlerThread("ServiceStartArguments", android.os.Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();
            Looper mServiceLooper = thread.getLooper();
            ServiceHandler mServiceHandler = new ServiceHandler(mServiceLooper, url);
            BaseApp x = (BaseApp) getApplicationContext();
            Message msg = mServiceHandler.obtainMessage();
            msg.arg1 = startId;
            mServiceHandler.sendMessage(msg);
        }
        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    private int totalFileSize;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;

    private void initDownload(String url, int id) {
        try {
            downloadFile(url.substring(url.lastIndexOf('/') + 1, url.length()), id, url);
            //new Random().nextInt(100)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String filename, int id, String... body) throws IOException {

        int count;
        byte data[] = new byte[1024 * 4];
        URL urls = new URL(body[0]);
        URLConnection connection = urls.openConnection();
        connection.connect();
        long fileSize = connection.getContentLength();

        InputStream bis = new BufferedInputStream(urls.openStream(), 1024 * 8);

        String folderPath = Environment.getExternalStorageDirectory().toString() + "/PayToAllBills";
        File futureStudioFile = new File(folderPath);
        if (!futureStudioFile.exists()) {
            File file = new File(folderPath);
            file.mkdirs();
        }

        long startTimes = System.currentTimeMillis();
        File outputFile = new File(folderPath, startTimes + "_" + filename);


        OutputStream output = new FileOutputStream(outputFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {
            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1, 2))) / 1000;
            double current = Math.round(total / (Math.pow(1, 2))) / 1000;
            int progress = (int) ((total * 100) / fileSize);
            long currentTime = System.currentTimeMillis() - startTime;
            Download download = new Download();
            download.setTotalFileSize(totalFileSize);
            if (currentTime > 10 * timeCount) {
                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                sendNotification(download, id);
                timeCount++;
            }
            output.write(data, 0, count);
        }
        onDownloadComplete(filename, id);
        output.flush();
        output.close();
        bis.close();
    }

    private void sendNotification(Download download, int id) {

        sendIntent(download, id);
        notificationBuilder.setOngoing(true)
                .setContentTitle("Downloading")
                .setContentText("Downloading file " + download.getCurrentFileSize() + "/" + totalFileSize + " KB")
                .setProgress(100, download.getProgress(), false);
        notificationManager.notify(id, notificationBuilder.build());
    }

    private void sendIntent(Download download, int id) {

        Intent intent = new Intent("MESSAGE_PROGRESS");
        intent.putExtra("download", download);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }

    private void onDownloadComplete(String filename, final int id) {
        try {

            Download download = new Download();
            download.setProgress(100);
            sendIntent(download, id);

            notificationBuilder.setProgress(100, 100, false);
            notificationBuilder.setContentTitle("Download Complete");
            notificationBuilder.setContentText("");
            notificationBuilder.setAutoCancel(true);
            notificationManager.notify(id, notificationBuilder.build());

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    notificationManager.cancel(id);
                }
            }, 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

    }
}