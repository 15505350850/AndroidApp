package com.example.administrator.shoppingapp.Repair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23.
 */
public class GetPostUtil {

    public static String sendGet(String url,String params)
    {
        String result="";
        BufferedReader in=null;
        try {
            String urlName=url+"?"+params;
            URL realUrl;
            realUrl = new URL(urlName);
            URLConnection conn=realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE)");
            conn.connect();
            Map<String,List<String>> map=conn.getHeaderFields();
            for(String key:map.keySet())
            {
                System.out.println(key+"--->"+map.get(key));
            }
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!=null)
            {
                result+=line;
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("发送GET请求出现异常！"+e);
            e.printStackTrace();
        }
        finally
        {
            if(in!=null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;

    }


    public static String sendPost(String url,String params)
    {
        PrintWriter out=null;
        BufferedReader br=null;
        String result="";
        try {
            URL realURL=new URL(url);
            URLConnection conn=realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输入流
            out=new PrintWriter(conn.getOutputStream());
            //发送请求参数
            out.print(params);
            out.flush();
            br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=br.readLine())!=null)
            {
                result+=line;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("程序出现异常"+e);
            e.printStackTrace();
        }
        finally{
            try {

                if(br!=null)
                {
                    br.close();
                }
                if(out!=null)
                {
                    out.close();
                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;

    }


}
