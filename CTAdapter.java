package com.example.tantan.city;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanTan on 18/08/2017.
 */

public class CTAdapter extends BaseAdapter {
    private List<CityInfo> list = new ArrayList<CityInfo>();
    private Activity mActivity;

    public CTAdapter(List<CityInfo> mlist, Activity mActivity) {
        this.list = mlist;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = LayoutInflater.from(mActivity);
        convertView = vi.inflate(R.layout.lv_item, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView ivImg = (ImageView) convertView.findViewById(R.id.iv_img);

        Ion.with(ivImg)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .load(list.get(position).getImg());
        tvName.setText(list.get(position).getName());

        return convertView;
    }
}
