package com.example.brajeshkumar.demo_fb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brajesh Kumar on 17-01-2016.
 */
public class ListDataAdapter extends BaseAdapter {
    List<FriendDetails> wdl;
    Activity act;
    private static LayoutInflater inflator;
    public ListDataAdapter(Activity activity, List<FriendDetails
            > list){
        wdl=list;
        act=activity;
        inflator= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class LayoutHandler {
        TextView Name, Afflication;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return wdl.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi=inflator.inflate(R.layout.column_layout,null);
        TextView name= (TextView) vi.findViewById(R.id.name_c);
        TextView afflication = (TextView) vi.findViewById(R.id.afflication_c);
        ImageView img= (ImageView) vi.findViewById(R.id.icon_c);

        FriendDetails wd=wdl.get(position);

        name.setText(wd.getName());
        afflication.setText(wd.getEmailID());
        img.setImageDrawable(wd.getImage(act));
        return  vi;
    }
}
