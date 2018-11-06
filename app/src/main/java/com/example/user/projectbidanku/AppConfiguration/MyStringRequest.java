package com.example.user.projectbidanku.AppConfiguration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by user on 16/09/2018.
 */

public class MyStringRequest extends StringRequest implements Response.Listener{
    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public void onResponse(Object response) {

    }
}
