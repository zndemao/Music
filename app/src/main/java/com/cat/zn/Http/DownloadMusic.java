package com.cat.zn.Http;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cat.zn.util.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by zxl96 on 2017/3/30.
 */

public class DownloadMusic {
    private static final String TAG = "DownloadMusic";

    /**
     * 此为下载方法，暂不做下载继续等
     * 完成条件良好情况下，音乐下载与播放任务
     * 后续添加断点续传
     */
    public static void requestDownloadMusic(final String audio, final String songs) {
//        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
//        StringRequest stringRequest = new StringRequest(audio, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "onResponse: " + response.toString());
//                SaveFile.saveMusicFile(response, songs);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
        try {
            InputStream is = null;
            RandomAccessFile savedFile = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(audio).build();
            okhttp3.Response response = client.newCall(request).execute();
//            if (response != null) {
                is = response.body().byteStream();
//                savedFile = new RandomAccessFile(file, "rw");
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {

                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已下载的百分比

                    }
//                }
                response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
