package com.eh.ser_child_xutilshttp;

import android.os.Handler;
import android.os.Message;

import com.eh.ser_child_application.LocalApplication;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.json.JSONException;

/**
 * Get请求
 * 
 * Created by luojiang on 2015/9/17.
 * 
 */
public class AsyncHttpGet {
	// 线程通信
	private Handler handler;

	// 访问路径
	private String url = "";

	public AsyncHttpGet(Handler handler) {
		this.handler = handler;
	}

	public AsyncHttpGet(Handler handler, String url) {
		this.handler = handler;
		this.url = url;
	}

	// 设置请求参数
	public void setParamsBylogin(String account, String password) {
		url += "account=" + account + "&password=" + password;
	}

	// 登录验证
	public void loginCheck() {
		LocalApplication.getInstance().httpUtils.send(HttpMethod.GET, url,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 回送消息
						handler.sendEmptyMessage(-1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 回送消息
						Message message = handler.obtainMessage(1,
								analysisData(arg0.result));
						handler.sendMessage(message);
					}
				});
	}

	// 解析json || 可改 gson等
	public int analysisData(String resultJson) {
		int result = 0;
		// 对json结果进行处理，避免干扰字符
		resultJson = resultJson.substring(resultJson.indexOf("{"),
				resultJson.lastIndexOf("}") + 1);

		org.json.JSONObject jsonObject;
		try {
			jsonObject = new org.json.JSONObject(resultJson);
			result = jsonObject.optInt("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
