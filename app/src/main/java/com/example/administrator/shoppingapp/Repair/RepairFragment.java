package com.example.administrator.shoppingapp.Repair;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.shoppingapp.R;

/**
 * Created by Administrator on 2016/11/17.
 */
public class RepairFragment  extends Fragment{
    private RelativeLayout rl_repair_kuaidi,rl_repair_tianqi,rl_repair_gupiao,rl_repair_xiaohua,rl_repair_wifi,rl_repair_huilv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View repairLayout = inflater.inflate(R.layout.main_repair, container,
                false);
        return repairLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rl_repair_gupiao = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_gupiao);
        rl_repair_huilv = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_huilv);
        rl_repair_kuaidi = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_kuaidi);
        rl_repair_tianqi = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_tianqi);
        rl_repair_wifi = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_wifi);
        rl_repair_xiaohua = (RelativeLayout) getActivity().findViewById(R.id.rl_repair_xiaohua);

        rl_repair_kuaidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RepairKuaidi.class);
                startActivity(intent);

            }
        });
        rl_repair_tianqi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RepairTianqi.class);
                startActivity(intent);
            }
        });
        rl_repair_gupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_repair_xiaohua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        rl_repair_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rl_repair_huilv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}
