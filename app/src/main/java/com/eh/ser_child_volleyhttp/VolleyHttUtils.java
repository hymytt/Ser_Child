package com.eh.ser_child_volleyhttp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jiang on 2015/12/22.
 */
public class VolleyHttUtils {
    private static RequestQueue mQueue;
    private VolleyHttUtils(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public static synchronized RequestQueue getRequestQueue(Context context){
        if (mQueue == null){
            new VolleyHttUtils(context.getApplicationContext());
        }
        return mQueue;
    }
}
