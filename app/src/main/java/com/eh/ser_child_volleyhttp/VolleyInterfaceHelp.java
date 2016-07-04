package com.eh.ser_child_volleyhttp;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by jiang on 2015/12/22.
 */
public abstract class VolleyInterfaceHelp {
    public Context context;
    public static Response.Listener mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyInterfaceHelp(Context context,Response.Listener listener,Response.ErrorListener errorListener) {
        this.context = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public abstract void onSuccess(String responese);
    public abstract void onFailer(VolleyError volleyError);

    public Response.Listener loadingListener(){
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                onSuccess(result);
            }
        };
        return mListener;
    }
    public Response.ErrorListener  loadErrorListener(){
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onFailer(volleyError);
            }
        };
        return mErrorListener;
    }


}
