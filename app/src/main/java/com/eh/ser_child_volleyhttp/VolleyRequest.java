package com.eh.ser_child_volleyhttp;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;


/**
 * Created by jiang on 2015/12/22.
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    public static  void doRequestGet(Context mContext,String url,String tag,VolleyInterfaceHelp vCallback){
        RequestQueue mQueue =  VolleyHttUtils.getRequestQueue(mContext);
        mQueue.cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,vCallback.loadingListener(),vCallback.loadErrorListener());
        stringRequest.setTag(tag);
        mQueue.add(stringRequest);
    }

    public static  void doRequestPost(Context mContext,String url,String tag, final Map<String,String> params,VolleyInterfaceHelp vCallback){
        RequestQueue mQueue =  VolleyHttUtils.getRequestQueue(mContext);
        mQueue.cancelAll(tag);
        stringRequest = new StringRequest(url,vCallback.loadingListener(),vCallback.loadErrorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        mQueue.add(stringRequest);
    }

}
