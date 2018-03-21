package com.atino.kevin.rxexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RunnableActivity extends BaseActivity {
    private static final int RUNNABLE_CAT_BITMAP = 0;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RUNNABLE_CAT_BITMAP : {
                    cats.setImageBitmap((Bitmap) msg.obj);
                }
            }

        }
    };

    void requestCats() {
        Thread thread = new Thread(catURLRunnable());
        thread.start();
    }

    private Runnable catURLRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    URL catUrl = url;

                    HttpURLConnection connection = (HttpURLConnection)
                            catUrl.openConnection();
                    connection.connect();

                    final URL catURL = connection.getURL();

                    connection.disconnect();

                    connection = (HttpURLConnection) catURL.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    Message message = new Message();
                    message.what = RUNNABLE_CAT_BITMAP;
                    message.obj = bitmap;
                    handler.sendMessage(message);

                    inputStream.close();
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
