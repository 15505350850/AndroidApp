package com.example.administrator.shoppingapp.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HomeAdminLogin extends AppCompatActivity {
    private TextView et_home_admin_name,et_home_admin_psw;
    private Button bt_home_admin_login,bt_home_admin_login_back,bt_home_admin_input_goods;
    private String adminname,adminpsw;
    private HomeAddGoodsDB homeAddGoodsDB;

    /**
     * 以下商品信息仅作为测试使用
     */
    private String [] name = new String[]{
            "阿迪达斯运动针织长裤2016新款上市Z76547","美特斯邦威牛仔长裤秋季新款弹力莱卡棉","男装 仿羊羔绒运动长裤(束脚) 172363 优衣库UNIQLO","阿迪达斯男裤2016冬季加绒加厚针织直筒运动裤宽松保暖长裤S17601",
            "李宁男子2016新款训练开衫运动卫衣男运动服AWDL303","森马连帽卫衣 2016秋季新款 男士连帽字母套头运动卫衣青少年韩版","包邮正品李宁运动服套装男装秋冬季新款运动开衫男上衣外套卫衣",
            "阿迪达斯羽绒服男 2016冬季大码保暖运动服休闲连帽外套 AY9926","CONVERSE匡威官方 连帽短款羽绒服 男款 10002860","NIKE耐克羽绒服男2016年冬季新款官方正品短款休闲运动外套806862",
            "男装 牛津纺修身衬衫(长袖) 172926 优衣库UNIQLO","Heilan Home/海澜之家热卖男装纯色牛津纺休闲长袖衬衫男","罗蒙衬衫男士长袖白衬衣商务修身纯色职业装3C13547保暖加绒衬衫","GXG男装 秋季修身衬衣男白色休闲长袖男衬衫#63803034",
            "ONLY秋冬含羊毛茧型毛呢大衣女|11536T012","ZARA TRF 女装 加大码呢绒大衣 01255810800","VeroModa秋冬新款大翻领长款毛呢大衣女|316327522",
            "ONLY2016冬装新品茧型可拆卸貉子毛领中长羽绒服女L|116312515","VeroModa秋冬新款貉子毛短款羽绒服女|316423506","艾格 ES 2016 冬新品涂鸦印花短款羽绒服16033508642", "芭欧2016冬季新款女士韩版狐狸毛领真皮皮衣长款气质羽绒服外套女",
            "Amii[极简主义]2016冬新款短款纯色圆领长袖针织套头毛衣女装大码","茵曼女装文艺范条纹长袖套头毛衣女春秋修身打底衫上衣8531320498","迪瑞羊半高领羊绒衫女 中长款加厚打底外穿宽松韩版纯山羊绒毛衣","Artka阿卡赫本系列2016秋冬新品长袖百搭套头针织衫多色YB11255Q",
            "VeroModa秋冬新款修身九分棉弹牛仔裤女|316349507","ONLY冬装新拼接侧缝修身SD牛仔裤女E|116132031","ZARA 女装 高腰紧身裤 08228238800","韩都衣舍2016韩版女装秋季新款裤子小脚裤修身长裤牛仔裤JW5186筱","李维斯700系列女士712修身原色水洗牛仔裤19561-0014"};
    private String [] simple = new String[]{
            "针织面料 裤脚收口 专柜正品","弹力莱卡 纯棉 包邮","初上市价格399","宽松舒适 针织面料 裤脚收口 专柜正品",
            "秋冬新品 天猫认证 完美套装 部分尺码 分包发出","冬季新款","包邮 专柜正品",
            "时尚保暖 运动休闲","专柜正品","冬季时尚 2016新款上市",
            "出上市价格399","温和五色 更易搭配 ","版型挺括 修饰身形 百搭单品","专柜正品",
            "秋装新品 含羊毛 双排扣 7天降退差价","长版大衣 背部下摆开叉 内衬设计","羊毛材质 大翻领 双排扣",
            "16秋季新品 中长款 可拆卸 7天降退差价","可拆卸毛领 罗纹针织袖口 实用侧兜 内里收紧","2016新款上市", "专柜正品 包邮",
            "2016冬季新款","专柜正品","秋冬新品 限时特惠 售后保障 7天后发货","赫本系列 不易褪色起球 预售货期 请看颜色分类",
            "双腰头 修身版型 金属钮扣门襟","16新品 拼接侧缝 多口袋 7天降退差价","高腰牛仔紧身 裤；五口袋 拉链和纽扣闭 合","时尚黑色 小脚微收 修身版型 三颗纽扣","秋冬新款 专柜正品"};
    private int [] price = new int[]{
            189,199,239,129,459,489,269,199,629,599,
            399,199,299,129,239,419,529,99,349,329,
            359,129,269,453,179,199,379,369,349,169};
    private String [] type1 = new String[]{
            "男装","男装","男装","男装",
            "男装","男装","男装",
            "男装","男装","男装",
            "男装","男装","男装","男装",
            "男装","男装","男装",
            "女装","女装","女装", "女装",
            "女装","女装","女装", "女装",
            "女装","女装","女装","女装","女装"};
    private String [] type2 = new String[]{
            "长裤","长裤","长裤","长裤",
            "T恤","T恤","T恤",
            "羽绒服","羽绒服","羽绒服",
            "衬衣","衬衣", "衬衣","衬衣",
            "毛呢大衣", "毛呢大衣","毛呢大衣",
            "羽绒服","羽绒服","羽绒服", "羽绒服",
            "羊毛衫","羊毛衫","羊毛衫", "羊毛衫",
            "牛仔裤","牛仔裤", "牛仔裤","牛仔裤","牛仔裤"};
    private String [] sales = new String[]{"无","包邮","折扣","新上市","分期购","货到付款","包邮","无","包邮","货到付款",
            "无","包邮","折扣","新上市","分期购","货到付款","包邮","无","包邮","货到付款",
            "无","包邮","折扣","新上市","分期购","货到付款","包邮","无","包邮","货到付款"};
    private int [] num = new int[]{
            359,129,269,453,179,199,379,369,349,169,
            189,199,239,129,459,489,269,199,629,599,
            399,199,299,129,239,419,529,99,349,329};
    /**
     * 上架时间自动生成  getTime()
     */
    private String [] goodstext = new String[]{
            "宝贝详情","宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情", "宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情","宝贝详情",
            "宝贝详情","宝贝详情","宝贝详情","宝贝详情","宝贝详情"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_admin_login);

        et_home_admin_name = (TextView) findViewById(R.id.et_home_admin_name);
        et_home_admin_psw = (TextView) findViewById(R.id.et_home_admin_psw);
        bt_home_admin_login = (Button) findViewById(R.id.bt_home_admin_login);
        bt_home_admin_login_back = (Button) findViewById(R.id.bt_home_admin_login_back);
        bt_home_admin_input_goods = (Button) findViewById(R.id.bt_home_admin_input_goods);

        bt_home_admin_input_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdminLogin.this);
                builder.setTitle("提示");
                builder.setIcon(R.drawable.warn);
                builder.setMessage("此功能仅作为测试使用，如已经导入过一次商品信息，请勿重复导入！\n\n 确认要导入商品信息吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /**
                         * 将默认商品信息导入到数据库中
                         */
                        for (int i = 0;i<name.length;i++){
                            GoodsBean goodsBean = new GoodsBean();
                            goodsBean.setName(name[i]);
                            goodsBean.setSimple(simple[i]);
                            goodsBean.setPrice(price[i]);
                            goodsBean.setType1(type1[i]);
                            goodsBean.setType2(type2[i]);
                            goodsBean.setSales(sales[i]);
                            goodsBean.setNum(num[i]);
                            goodsBean.setTime(getTime());
                            goodsBean.setGoodstext(goodstext[i]);
                            //System.out.println(goodsBean.toString());
                            homeAddGoodsDB = new HomeAddGoodsDB(HomeAdminLogin.this);
                            homeAddGoodsDB.insertGoods(goodsBean);
                        }

                        //System.out.println(name.length+"@"+simple.length+"@"+price.length+"@"+type1.length+"@"+type2.length+"@"+sales.length+"@"+num.length+"@"+goodstext.length);



                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }
        });


        bt_home_admin_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_home_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminname = et_home_admin_name.getText().toString();
                adminpsw = et_home_admin_psw.getText().toString();

                if (adminname.length()==0||adminpsw.length()==0){
                    Toast.makeText(HomeAdminLogin.this,"请输入账号和密码！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (adminname.equals("123")&&adminpsw.equals("123")){
                    /*
                    登陆成功
                     */
                    Intent intent = new Intent(HomeAdminLogin.this,HomeAddGoods.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(HomeAdminLogin.this,"账号或密码错误！请重新输入！",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        return time;
    }

}
