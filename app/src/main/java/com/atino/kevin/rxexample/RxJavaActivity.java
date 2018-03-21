package com.atino.kevin.rxexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends BaseActivity {

    @Override
    void requestCats() {
        catObservable(url)
                .doOnNext(bitmap -> cats.setImageBitmap(bitmap))
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }


    private Observable<Bitmap> catObservable(URL url) {
        return Observable
                .just(url)
                .filter(Objects::nonNull)
                .subscribeOn(Schedulers.io())
                .map(URL::openConnection)
                .map(urlConnection -> {
                    final URL catURL = urlConnection.getURL();
                    ((HttpURLConnection) urlConnection).disconnect();
                    return catURL;
                })
                .map(URL::openConnection)
                .map(urlConnection -> {
                    final Bitmap bitmap = BitmapFactory.decodeStream(
                            urlConnection.getInputStream());
                    ((HttpURLConnection) urlConnection).disconnect();
                    return bitmap;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
