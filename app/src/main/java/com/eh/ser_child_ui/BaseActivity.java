package com.eh.ser_child_ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.eh.ser_child_utils.ActivityStack;
import com.eh.ser_child_view.DialogMaker;
import com.lidroid.xutils.ViewUtils;

/**
 * BaseActivity
 *
 * Created by luojiang on 2015/9/17.
 */
public abstract class BaseActivity extends FragmentActivity implements DialogMaker.DialogCallBack
{
	protected Dialog dialog;

	private boolean isCreate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initTitle();
		super.onCreate(savedInstanceState);

		ActivityStack.getInstance().addActivity(this);
		setContentView(getLayoutId());
		ViewUtils.inject(this);
		isCreate = true;
	}
/**
 * 加载标题栏
 */
	protected   abstract   void initTitle() ;  //加载标题栏 需要就 	CustomTitleBar TitleSet =new CustomTitleBar();	TitleSet.getTitleBar(this,"二娃");  //不需要不用写


	@Override
	protected void onResume()
	{
		super.onResume();
		if (isCreate)
		{
			isCreate = false;
			initParams();
		}
	}

	/**
	 * 初始化布局
	 * 
	 * @author blue
	 */
	protected abstract int getLayoutId();

	/**
	 * 参数设置
	 * 
	 * @author blue
	 */
	protected abstract void initParams();

	/**
	 * 弹出对话框
	 * 
	 * @author blue
	 */
	public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag)
	{
		if (null == dialog || !dialog.isShowing())
		{
			dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag);
		}
		return dialog;
	}

	/**
	 * 等待对话框
	 * 
	 * @author blue
	 */
	public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag)
	{
		if (null == dialog || !dialog.isShowing())
		{
			dialog = DialogMaker.showCommenWaitDialog(this, msg, this, isCanCancelabel, tag);
		}
		return dialog;
	}

	/**
	 * 关闭对话框
	 * 
	 * @author blue
	 */
	public void dismissDialog()
	{
		if (null != dialog && dialog.isShowing())
		{
			dialog.dismiss();
		}
	}

	@Override
	public void onButtonClicked(Dialog dialog, int position, Object tag)
	{
	}

	@Override
	public void onCancelDialog(Dialog dialog, Object tag)
	{
	}

	@Override
	protected synchronized void onDestroy()
	{
		dismissDialog();
		ActivityStack.getInstance().removeActivity(this);
		super.onDestroy();
	}

	/**
	 * 跳转
	 * @param packageContext
	 * @param cls
     */
	protected void intoActivity(Context packageContext,Class cls){
		Intent into=new Intent(packageContext,cls);
		startActivity(into);
	}


}
