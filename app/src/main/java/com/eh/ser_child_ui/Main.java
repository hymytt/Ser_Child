package com.eh.ser_child_ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.eh.ser_child_fragment.CalendarFragment;
import com.eh.ser_child_fragment.IndexFragment;
import com.eh.ser_child_fragment.MineFragment;
import com.eh.ser_child_fragment.RemindFragment;
import com.eh.ser_child_fragment.TopicFragment;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by 丶丶凝沙 on 2016/7/4.
 */
public class Main  extends  BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.btn_0)
    RadioButton rb_main;

    @ViewInject(R.id.RadioGroup1)
    RadioGroup group;
    @ViewInject(R.id.framlayout_1)
    FrameLayout frameLayout;

    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void initTitle() {

    }

    /**
     * 初始化布局
     *
     * @author blue
     */
    @Override
    protected int getLayoutId() {
        return R.layout.main;
    }

    /**
     * 参数设置
     *
     * @author blue
     */
    @Override
    protected void initParams() {
        fm= getFragmentManager();
        rb_main.setChecked(true);
        group.setOnCheckedChangeListener(this);
        changeFragment(new IndexFragment(), false);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.btn_0:
                changeFragment(new IndexFragment(), true);

                break;
           case R.id.btn_1:
                changeFragment(new CalendarFragment(), true);
                break;
            case R.id.btn_2:
                changeFragment(new RemindFragment(), true);
                break;
            case R.id.btn_3:
                changeFragment(new TopicFragment(), true);
                break;
            case R.id.btn_4:
                changeFragment(new MineFragment(), true);
                break;
        }

    }
    // 切换不同的碎片
    public void changeFragment(Fragment fragment, boolean isInit) {
        ft = fm.beginTransaction();
        ft.replace(R.id.framlayout_1, fragment);
        if (!isInit)
            ft.addToBackStack(null);

        ft.commit();

    }

    }




