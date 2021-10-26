package com.example.yadomanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yadomanage.R;

import java.util.List;

public class ClassAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Class> classList;

    public ClassAdapter(Context context, int layout, List<Class> classList) {
        this.context = context;
        this.layout = layout;
        this.classList = classList;
    }

    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtCid,txtCname;
        ImageView imgClass,imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            viewHolder.txtCid = (TextView) convertView.findViewById(R.id.classid);
            viewHolder.txtCname = (TextView) convertView.findViewById(R.id.classname);
            viewHolder.imgClass = (ImageView) convertView.findViewById(R.id.icclass);
           // viewHolder.imgEdit = (ImageView) convertView.findViewById(R.id.edit);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Class cclass = classList.get(position);
        viewHolder.txtCid.setText(cclass.getCid());
        viewHolder.txtCname.setText(cclass.getCname());

        return convertView;
    }
}
