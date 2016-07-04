package com.eh.ser_child_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eh.ser_child_ui.R;
import com.lidroid.xutils.ViewUtils;

import java.lang.reflect.Member;
import java.util.List;

/**
 * 实体类对应的适配器
 * 
 * Created by luojiang on 2015/9/17.
 * 
 */
public class MemberAdapter extends SimpleBaseAdapter<Member> {

	public MemberAdapter(Context c, List<Member> datas) {
		super(c, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EntityHolder entityHolder = null;

		if (convertView == null) {
			entityHolder = new EntityHolder();

			convertView = layoutInflater.inflate(R.layout.demo, null);

			ViewUtils.inject(entityHolder, convertView);

			convertView.setTag(entityHolder);
		} else {
			entityHolder = (EntityHolder) convertView.getTag();
		}

		entityHolder.name.setText(datas.get(position).getName());

		return convertView;
	}

	private class EntityHolder {
		// 名称
		public TextView name;
	}

}
