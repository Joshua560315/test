package com.joshua.webpage.java;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by linzl on 16-12-21.
 */
public class Reptile {

    public static String get(String url){
        String result = "";
        try {
            //获取httpclient实例
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //获取方法实例。GET
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(1000)
                    .build();
            httpGet.setConfig(requestConfig);
            //执行方法得到响应
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                result = EntityUtils.toString(response.getEntity());
            } finally {
                httpclient.close();
                response.close();
            }
        } catch (ConnectException ex){
            try{
                System.out.println("链接错误，url为"+url);
                FileUtils.write(new File("/home/bmk/httpLogs/ConnectException-"+(new SimpleDateFormat("yyyy-MM-dd")).format(new Date())),url+"\n",true);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String postByJson(String params,String url,int timeout) {
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type","application/json;charset=utf-8");
            post.setHeader("Accept","application/json");
            post.setEntity(new StringEntity(params, "UTF-8"));
            new FileEntity(new File(""), ContentType.MULTIPART_FORM_DATA);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(1000)
                    .build();
            post.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(post);
            try {
                result = EntityUtils.toString(response.getEntity());
            } finally {
                httpClient.close();
                response.close();
            }

        }catch (ConnectException ex){
            try{
                System.out.println("链接错误，url为"+url);
                String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
                FileUtils.write(new File("/home/bmk/httpLogs/ConnectException-"+date),url+"\n",true);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private static String handleResponse(HttpResponse response)  {
        //如果正确执行而且返回值正确，即可解析
        String result = "";
        if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            //从输入流中解析结果
            result = readResponse(entity, "utf-8");
        }
        return result;
    }

    /**
     * stream读取内容，可以传入字符格式
     * @param resEntity
     * @param charset
     * @return
     */
    private static String readResponse(HttpEntity resEntity, String charset) {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            if (resEntity == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(
                    resEntity.getContent(), charset));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void downloadImage(String imageUrl) {
        InputStream fis = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            URL url = new URL(imageUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5*1000);
            fis = urlConnection.getInputStream();
            if (fis!=null) {
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = fis.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                out.flush();
                FileUtils.writeByteArrayToFile(new File("/home/bmk/ReptileFiles/Images/"+(new File(imageUrl).getName())),out.toByteArray());
            }
        } catch (Exception e ){
            e.printStackTrace();
        } finally {
            try{
                out.close();
                if (fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}