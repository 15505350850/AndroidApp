package com.example.administrator.shoppingapp.Cart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18.
 */
public class CartAdapter extends BaseAdapter {
    ArrayList<CartGoodsBean> cartlist = new ArrayList<CartGoodsBean>();
    Context context;
    //private int CurrentNum;
    //private int Price;
    private int SumPrice = 0;
    private CartDB cartDB;

    public CartAdapter(ArrayList<CartGoodsBean> cartlist, Context context) {
        this.cartlist = cartlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cartlist.size();
    }

    @Override
    public Object getItem(int position) {
        return cartlist.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_goods_list_item,null);
            viewHolder.tv_cart_item_name = (TextView) convertView.findViewById(R.id.tv_cart_item_name);
            viewHolder.tv_cart_item_sales = (TextView) convertView.findViewById(R.id.tv_cart_item_sales);
            viewHolder.tv_cart_item_buynum = (TextView) convertView.findViewById(R.id.tv_cart_item_buynum);
            viewHolder.tv_cart_item_add = (TextView) convertView.findViewById(R.id.tv_cart_item_add);
            viewHolder.tv_cart_item_move = (TextView) convertView.findViewById(R.id.tv_cart_item_move);
            viewHolder.tv_cart_item_price = (TextView) convertView.findViewById(R.id.tv_cart_item_price);
            viewHolder.tv_cart_item_sumprice = (TextView) convertView.findViewById(R.id.tv_cart_item_sumprice);
            viewHolder.ll_cart_item_am = (LinearLayout) convertView.findViewById(R.id.ll_cart_item_am);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv_cart_item_sales.setText("热销");

        viewHolder.tv_cart_item_name.setText(cartlist.get(position).getName());
        viewHolder.tv_cart_item_buynum.setText(cartlist.get(position).getBuynum()+"");
        viewHolder.tv_cart_item_price.setText(cartlist.get(position).getPrice()+"");
        //viewHolder.tv_cart_item_buynum.setText(cartlist.get(position).getBuynum());
        viewHolder.tv_cart_item_sumprice.setText((cartlist.get(position).getPrice() * cartlist.get(position).getBuynum())+"");
        //CurrentNum = cartlist.get(position).getBuynum();
        //Price = cartlist.get(position).getPrice();
        //AddPrice(Integer.parseInt(viewHolder.tv_cart_item_sumprice.getText().toString()));

        //viewHolder.tv_cart_item_add.setVisibility(View.INVISIBLE);
        //viewHolder.tv_cart_item_move.setVisibility(View.INVISIBLE);
        //viewHolder.ll_cart_item_am.setBackgroundColor(Color.parseColor("#00FFFFFF"));


        /**
         * 设置购买数量加减器
         */
       final ViewHolder finalViewHolder = viewHolder;
        viewHolder.tv_cart_item_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CurrentNum = Integer.parseInt(finalViewHolder.tv_cart_item_buynum.getText().toString());
                int Price = Integer.parseInt(finalViewHolder.tv_cart_item_price.getText().toString());
                cartDB = new CartDB(context);

                if (CurrentNum == 1) {
                    Toast.makeText(context, "最小购买数量为 1", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    CurrentNum--;
                    Toast.makeText(context, "购物车信息修改后请先刷新购物车再结算！", Toast.LENGTH_SHORT).show();
                    String name  = cartlist.get(position).getName();
                    String username = cartlist.get(position).getUsername();
                    cartDB.updateBuyNumByGoodNameName(name,username,CurrentNum);
                    finalViewHolder.tv_cart_item_buynum.setText(CurrentNum + "");
                    finalViewHolder.tv_cart_item_sumprice.setText((CurrentNum * Price) + "");
                }
            }
        });

        viewHolder.tv_cart_item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDB = new CartDB(context);
                int CurrentNum = Integer.parseInt(finalViewHolder.tv_cart_item_buynum.getText().toString());
                int Price = Integer.parseInt(finalViewHolder.tv_cart_item_price.getText().toString());
                CurrentNum++;
                Toast.makeText(context, "购物车信息修改后请先刷新购物车再结算！", Toast.LENGTH_SHORT).show();
                String name  = cartlist.get(position).getName();
                String username = cartlist.get(position).getUsername();
                cartDB.updateBuyNumByGoodNameName(name,username,CurrentNum);
                finalViewHolder.tv_cart_item_buynum.setText(CurrentNum + "");
                finalViewHolder.tv_cart_item_sumprice.setText((CurrentNum * Price) + "");
            }
        });
        return convertView;
    }

    public class ViewHolder{
        TextView tv_cart_item_name,tv_cart_item_sales,tv_cart_item_move,tv_cart_item_buynum,tv_cart_item_add,tv_cart_item_price,tv_cart_item_sumprice;
        LinearLayout ll_cart_item_am;

    }

    public int AddPrice(int sumprice){
        SumPrice = SumPrice+sumprice;
        System.out.println(SumPrice+"+"+sumprice+"=");
        return SumPrice;
    }

}
