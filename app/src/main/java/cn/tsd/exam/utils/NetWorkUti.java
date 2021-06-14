package cn.tsd.exam.utils;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NetWorkUti {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void get(String url,Handler handler,int responseLabel  ,int failureLabel) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder() //请求
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request); //将Request封装为Call
        //同步调用,返回Response,会抛出IO异常 卡线程
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //异步调用,并设置回调函数

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendMessage(handler.obtainMessage(failureLabel));
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                handler.sendMessage(handler.obtainMessage(responseLabel,res));
            }
        });
    }


    public static void post(String url, JsonObject jsonObject , Handler handler,int responseLabel  ,int failureLabel) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //        将Request封装为Call
        Call call = client.newCall(request);
        //        调用请求,重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendMessage(handler.obtainMessage(failureLabel));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                handler.sendMessage(handler.obtainMessage(responseLabel,res));
            }
        });
    }

    public static void postKeyValue(String url, Context context) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "admin")
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //        将Request封装为Call
        Call call = client.newCall(request);
        //        调用请求,重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "Post Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
            }
        });

    }

}
