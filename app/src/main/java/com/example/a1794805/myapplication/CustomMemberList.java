package com.example.a1794805.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMemberList extends BaseAdapter {


    private Activity context;
    ArrayList<Member>members;


    public CustomMemberList (Activity context, ArrayList<Member> members) {

        this.context = context;
        this.members=members;

    }

    public static class ViewHolder
    {

        TextView textViewId;
        TextView textViewname;
        TextView textViewemail;
        TextView textViewprofession;
        TextView textViewphonenumber;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.row_list, null, true);
            vh.textViewId = (TextView) row.findViewById(R.id.textViewid);
            vh.textViewname = (TextView) row.findViewById(R.id.textViewname);
            vh.textViewemail = (TextView) row.findViewById(R.id.textViewemail);
            vh.textViewprofession= (TextView) row.findViewById(R.id.textViewprofession);
            vh.textViewphonenumber = (TextView) row.findViewById(R.id.textViewphonenumber);
          ;
            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textViewname.setText(""+members.get(position).getMemberName());
        vh.textViewId.setText(""+members.get(position).getId());
        vh.textViewemail.setText(""+members.get(position).getEmail());
        vh.textViewprofession.setText(""+members.get(position).getProfession());
        vh.textViewphonenumber.setText(""+members.get(position).getPhonenumber());


        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(members.size()<=0)
            return 1;
        return members.size();
    }
}
