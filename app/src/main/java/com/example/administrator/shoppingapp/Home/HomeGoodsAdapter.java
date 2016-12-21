package com.example.administrator.shoppingapp.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */
public class HomeGoodsAdapter extends BaseAdapter {
    private ArrayList<GoodsBean> list = new ArrayList<GoodsBean>();
    Context context;

    public HomeGoodsAdapter(ArrayList<GoodsBean> list, Context context) {
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
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_goods_list_item,null);
            viewHolder.tv_goods_item_sales = (TextView) convertView.findViewById(R.id.tv_goods_item_sales);
            viewHolder.tv_goods_item_name = (TextView) convertView.findViewById(R.id.tv_goods_item_name);
            viewHolder.tv_goods_item_simple = (TextView) convertView.findViewById(R.id.tv_goods_item_simple);
            viewHolder.tv_goods_item_type = (TextView) convertView.findViewById(R.id.tv_goods_item_type);
            viewHolder.tv_goods_item_price = (TextView) convertView.findViewById(R.id.tv_goods_item_price);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        //相应组件设置值
        if (list.get(position).getSales().toString().equals("无")){
            viewHolder.tv_goods_item_sales.setText("热销");
        }else{
            viewHolder.tv_goods_item_sales.setText(list.get(position).getSales().toString());
        }
        viewHolder.tv_goods_item_name.setText(list.get(position).getName().toString());
        viewHolder.tv_goods_item_simple.setText(list.get(position).getSimple().toString());
        viewHolder.tv_goods_item_type.setText(list.get(position).getType1()+"-"+list.get(position).getType2());
        viewHolder.tv_goods_item_price.setText("¥ "+list.get(position).getPrice());
        return convertView;
    }


    public class ViewHolder{
        TextView tv_goods_item_sales,tv_goods_item_name,tv_goods_item_simple,tv_goods_item_type,tv_goods_item_price;

    }


}
