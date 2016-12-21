package com.example.administrator.shoppingapp.Repair;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/11.
 */
public class HistoricalAdapter extends BaseAdapter {
    private ArrayList<HList> list;
    private Context context;
    private DBPostid dbPostid;


    public HistoricalAdapter(ArrayList<HList> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.repair_kuaidi_listitem,null);
            viewHolder.h_postid = (TextView) convertView.findViewById(R.id.h_postid);
            viewHolder.h_express = (TextView) convertView.findViewById(R.id.h_express);
            viewHolder.h_delete = (TextView) convertView.findViewById(R.id.h_delete);
            //viewHolder.weixin_pic = (ImageView) convertView.findViewById(R.id.weixin_pic);
           // viewHolder.network_image_view = (NetworkImageView) convertView.findViewById(R.id.network_image_view);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.h_postid.setText(list.get(position).getH_postid());
        viewHolder.h_express.setText(list.get(position).getH_express());


        /*
        为单独的控件添加单击事件
         */
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.h_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbPostid = new DBPostid(context);
                System.out.println("delete:"+finalViewHolder.h_postid.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("确定删除该记录吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int dele_flage = dbPostid.DeletePostidByID(finalViewHolder.h_postid.getText().toString());
                        if (dele_flage > 0) {
                            Toast.makeText(context, "记录已删除！", Toast.LENGTH_SHORT).show();
                            //Intent intent1 = new Intent(context, MainActivity.class);
                            //context.startActivity(intent1);
                        } else {
                            Toast.makeText(context, "删除记录失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                //Toast.makeText(context, "删除记录", Toast.LENGTH_SHORT).show();
            }
        });





        return convertView;
    }

    public class ViewHolder{
        TextView h_postid,h_express,h_delete;
    }


}
