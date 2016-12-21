package com.example.administrator.shoppingapp.Cart;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.MainActivity;
import com.example.administrator.shoppingapp.My.MyLoginInfoDB;
import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/11/17.
 */
public class CartFragment extends Fragment {
    private ListView lv_cart_goods_list;
    private MyLoginInfoDB myLoginInfoDB;
    private CartDB cartDB;
    private Context context;
    private CheckBox cb_main_cart_select_all;
    private TextView tv_main_cart_sumprice;
    private LinearLayout ll_cart_item_refresh;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cartLayout = inflater.inflate(R.layout.main_cart, container,
                false);
       // lv_cart_goods_list = (ListView) cartLayout.findViewById(R.id.lv_cart_goods_list);
       // myLoginInfoDB = new MyLoginInfoDB(cartLayout);
        return cartLayout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_cart_goods_list = (ListView) getActivity().findViewById(R.id.lv_cart_goods_list);
        cb_main_cart_select_all = (CheckBox) getActivity().findViewById(R.id.cb_main_cart_select_all);
        tv_main_cart_sumprice = (TextView) getActivity().findViewById(R.id.tv_main_cart_sumprice);
        ll_cart_item_refresh = (LinearLayout) getActivity().findViewById(R.id.ll_cart_item_refresh);
        //ll_cart_item_edit = (LinearLayout) getActivity().findViewById(R.id.ll_cart_item_edit);
        //ll_cart_item_save = (LinearLayout) getActivity().findViewById(R.id.ll_cart_item_save);

        //ll_cart_item_save.setVisibility(View.INVISIBLE);



        myLoginInfoDB = new MyLoginInfoDB(getActivity());
        String username = myLoginInfoDB.queryUserInfo();
        if (username!=null){
            cartDB = new CartDB(getActivity());
            //ArrayList<CartGoodsBean> list = new  ArrayList<CartGoodsBean>();
            ArrayList<CartGoodsBean> list  = cartDB.queryCartByUserName(username);
            final CartAdapter cartAdapter = new CartAdapter(list,getActivity());
            lv_cart_goods_list.setAdapter(cartAdapter);
            //cartAdapter.notifyDataSetChanged();
            //cartAdapter.notifyDataSetChanged();
            cartAdapter.notifyDataSetChanged();

            int count = lv_cart_goods_list.getCount();
            int sumprice = 0;
            for (int i=0;i<count;i++){
                View view = cartAdapter.getView(i,null,lv_cart_goods_list);
                TextView tv = (TextView) view.findViewById(R.id.tv_cart_item_sumprice);
                sumprice = sumprice + Integer.parseInt(tv.getText().toString());
            }
            tv_main_cart_sumprice.setText("¥ "+sumprice);

           /* ll_cart_item_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_cart_item_edit.setVisibility(View.INVISIBLE);
                    ll_cart_item_save.setVisibility(View.VISIBLE);

                    int count = lv_cart_goods_list.getCount();
                    for (int i=0;i<count;i++){
                        View view = cartAdapter.getView(i,null,lv_cart_goods_list);
                        TextView add = (TextView) view.findViewById(R.id.tv_cart_item_add);
                        TextView move = (TextView) view.findViewById(R.id.tv_cart_item_move);
                        LinearLayout ll_cart_item_am = (LinearLayout) view.findViewById(R.id.ll_cart_item_am);
                        ll_cart_item_am.setBackgroundColor(Color.parseColor("#d7d7d7"));

                        add.setVisibility(View.VISIBLE);
                        move.setVisibility(View.VISIBLE);
                    }
                }
            });

            ll_cart_item_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_cart_item_save.setVisibility(View.INVISIBLE);
                    ll_cart_item_edit.setVisibility(View.VISIBLE);
                    int count = lv_cart_goods_list.getCount();
                    for (int i=0;i<count;i++){
                        View view = cartAdapter.getView(i,null,lv_cart_goods_list);
                        TextView add = (TextView) view.findViewById(R.id.tv_cart_item_add);
                        TextView move = (TextView) view.findViewById(R.id.tv_cart_item_move);
                        LinearLayout ll_cart_item_am = (LinearLayout) view.findViewById(R.id.ll_cart_item_am);
                        ll_cart_item_am.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                        add.setVisibility(View.INVISIBLE);
                        move.setVisibility(View.INVISIBLE);
                    }

                }
            });*/
            ll_cart_item_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    Bundle data = new Bundle();
                    data.putSerializable("from","cart");
                    intent.putExtras(data);
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();
                }
            });



        }else{

        }

        lv_cart_goods_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_cart_item_sumprice = (TextView) view.findViewById(R.id.tv_cart_item_sumprice);
                Toast.makeText(getActivity(), "总价："+tv_cart_item_sumprice.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        System.out.println(lv_cart_goods_list.getId()+"");
        System.out.println(lv_cart_goods_list.getCount()+"");
        System.out.println(lv_cart_goods_list.getItemAtPosition(1));

        cb_main_cart_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = lv_cart_goods_list.getCount();
                int sumprice = 0;
                for (int i=0;i<count;i++){

                    //lv_cart_goods_list.getChildAt(i).invalidate();
                    //TextView tv = (TextView) lv_cart_goods_list.getChildAt(i).findViewById(R.id.tv_cart_item_sumprice);
                    //sumprice = sumprice + Integer.parseInt(tv.getText().toString());
                }
                //tv_main_cart_sumprice.setText("¥ "+sumprice);

                //SumPrice();
                Toast.makeText(getActivity(), "点击了全选按钮", Toast.LENGTH_SHORT).show();

            }
        });




    }


    public void notifyDataSetChanged(){

    }
    public int SumPrice(){
        int count = lv_cart_goods_list.getCount();
        int sumprice = 0;
        for (int i=0;i<count;i++){

            sumprice = sumprice + Integer.parseInt(getActivity().findViewById(R.id.tv_cart_item_sumprice).toString());
        }
        return sumprice;
    }

}
