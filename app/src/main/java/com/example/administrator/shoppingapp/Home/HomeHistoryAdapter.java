package com.example.administrator.shoppingapp.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/27.
 */
public class HomeHistoryAdapter extends BaseAdapter {
    private ArrayList<HistoryBean> list = new ArrayList<HistoryBean>();
    private Context context;

    public HomeHistoryAdapter(ArrayList<HistoryBean> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_history_list_item,null);
            viewHolder.tv_history_item_name = (TextView) convertView.findViewById(R.id.tv_history_item_name);
            viewHolder.tv_history_item_delete = (TextView) convertView.findViewById(R.id.tv_history_item_delete);
            viewHolder.tv_history_item_price = (TextView) convertView.findViewById(R.id.tv_history_item_price);
            viewHolder.tv_history_item_sale = (TextView) convertView.findViewById(R.id.tv_history_item_sales);
            viewHolder.tv_history_item_time = (TextView) convertView.findViewById(R.id.tv_history_item_time);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv_history_item_sale.setText(list.get(position).getSales());
        viewHolder.tv_history_item_name.setText(list.get(position).getName());
        viewHolder.tv_history_item_time.setText(list.get(position).getTime());
        viewHolder.tv_history_item_price.setText("¥ "+list.get(position).getPrice());

        viewHolder.tv_history_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "删除"+list.get(position).getUsername()+"@"+list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView tv_history_item_sale,tv_history_item_name,tv_history_item_time,tv_history_item_price,tv_history_item_delete;



    }

}
