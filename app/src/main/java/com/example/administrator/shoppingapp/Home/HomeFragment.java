package com.example.administrator.shoppingapp.Home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.administrator.shoppingapp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/17.
 */
public class HomeFragment extends Fragment {
    private RelativeLayout rl_home_search_goods,rl_home_editgoods,rl_home_myfootprint;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.ad_1,
            R.drawable.ad_2,
            R.drawable.ad_3,
            R.drawable.ad_4,
            R.drawable.ad_5
    };
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeLayout = inflater.inflate(R.layout.main_home, container,
                false);
        return homeLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_home_search_goods = (RelativeLayout) getActivity().findViewById(R.id.rl_home_search_goods);
        rl_home_editgoods = (RelativeLayout) getActivity().findViewById(R.id.rl_home_editgoods);
        rl_home_myfootprint = (RelativeLayout) getActivity().findViewById(R.id.rl_home_myfootprint);

        rl_home_myfootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeHistoryActivity.class);
                startActivity(intent);
            }
        });


        rl_home_editgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeAdminLogin.class);
                startActivity(intent);

            }
        });


        rl_home_search_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeSearchGoods.class);
                startActivity(intent);
            }
        });

        mViewPaper = (ViewPager) getActivity().findViewById(R.id.vp);
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            Bitmap bitmap=readBitMap(getActivity(),imageIds[i]);
            //imageView.setBackgroundDrawable(new BitmapDrawable(bitmap2));
            imageView.setImageBitmap(bitmap);
            //imageView.setBackgroundResource(imageIds[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setAdjustViewBounds(true);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(getActivity().findViewById(R.id.dot_0));
        dots.add(getActivity().findViewById(R.id.dot_1));
        dots.add(getActivity().findViewById(R.id.dot_2));
        dots.add(getActivity().findViewById(R.id.dot_3));
        dots.add(getActivity().findViewById(R.id.dot_4));
        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.pic_on);
                dots.get(oldPosition).setBackgroundResource(R.drawable.pic_noton);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    /**
     * 自定义Adapter
     */
    private class ViewPagerAdapter extends PagerAdapter {
        public int getCount() {
            return images.size();
        }
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }

    }
    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }
    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}
