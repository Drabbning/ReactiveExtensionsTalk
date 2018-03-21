package com.atino.kevin.rxexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskActivity extends BaseActivity {

    @Override
    void requestCats() {
        new AsyncCatTask().execute(url);
    }

    private class AsyncCatTask extends AsyncTask<URL, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(URL... urls) {
            for (URL u : urls) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                    connection.connect();

                    final URL catURL = connection.getURL();

                    connection.disconnect();

                    connection = (HttpURLConnection) catURL.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    inputStream.close();
                    connection.disconnect();

                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            cats.setImageBitmap(bitmap);
        }
    }
}
