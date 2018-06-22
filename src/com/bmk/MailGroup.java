package com.bmk;

import com.joshua.webpage.java.Reptile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bmk on 18-4-27.
 */
public class MailGroup {

    // 导出指定邮件地址列表
    public static void main(String[] args) {
        String address = "180320@maillist.sendcloud.org";
        String url = "http://api.sendcloud.net/apiv2/addressmember/list?apiUser=emailAddress_apiUser&apiKey=1KBegZpz5EZjib8e&address="+address+"&start=0&limit=1";
        JSONObject response = JSONObject.fromObject(Reptile.get(url));
        System.out.println(response);
        JSONObject info = response.getJSONObject("info");
        int total = info.getInt("total");
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        int requestSize = 100;
        int requestTimes = total%requestSize == 0 ? total/requestSize : total/requestSize+1;
        for (int i=0;i<requestTimes;i++){
            String requestUrl = "http://api.sendcloud.net/apiv2/addressmember/list?apiUser=emailAddress_apiUser&apiKey=1KBegZpz5EZjib8e&address="+address+"&start="+i*requestSize+"&limit="+requestSize;
            System.out.println(requestUrl);
            response = JSONObject.fromObject(Reptile.get(requestUrl));
            JSONArray dataList = response.getJSONObject("info").getJSONArray("dataList");
            List<String> addressList = new ArrayList<>();
            executorService.execute(()->{
                dataList.forEach(obj->{
                    JSONObject member = (JSONObject) obj;
                    addressList.add(member.getString("member"));
                });
                try {
                    FileUtils.writeLines(new File("/home/bmk/桌面/mailAddress.txt"),addressList,true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"结束");
            });
        }
        executorService.shutdown();
    }
}
