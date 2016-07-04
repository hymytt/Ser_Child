package com.eh.ser_child_ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.eh.ser_child_view.ToastMaker;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * Demo
 * Created by luojiang on 2015/9/18.r
 */
public class Demo extends BaseActivity {
	@ViewInject(R.id.btn1)
	Button btn1;
	@ViewInject(R.id.btn2)
	Button btn2; 	 				
	@ViewInject(R.id.btn3)
	Button btn3;

	@SuppressLint("HandlerLeak") 
	@SuppressWarnings("unused")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			dismissDialog();
			switch (msg.what) {
			case 1:
				ToastMaker.showShortToast("上传成功");
				break;
			case -1:
				ToastMaker.showShortToast("上次失败");
				break;

			}

		};
	};

	@Override
	protected void initTitle() {
//		CustomTitleBar TitleSet =new CustomTitleBar();
//		TitleSet.getTitleBar(this,"二娃");
	}

	@Override
	protected int getLayoutId() {
		return R.layout.demo;
	}

	@Override
	protected void initParams() {
	}

	@OnClick({ R.id.btn1, R.id.btn2, R.id.btn3 })
	public void viewOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:

			showWaitDialog("加载中...", true, null);

			break;
		case R.id.btn2:

			showAlertDialog("额！不满意吗？", "没关系，再挑战1次，说不定会有意外的惊喜哦~", new String[] {
					"取消", "确定", "继续挑战" }, true, true, null);

			break;

		case R.id.btn3:

			ToastMaker.showShortToast("再按一次退出程序");

			break;

		default:
			break;
		}
	}
}
