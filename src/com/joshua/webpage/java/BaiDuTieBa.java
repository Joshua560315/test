package com.joshua.webpage.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by linzl on 16-12-22.
 */
public class BaiDuTieBa {
    public static void main(String[] args) {
        Document document = Jsoup.parse(Reptile.get("http://tieba.baidu.com/f?kw=%E9%AB%98%E8%BE%BE%E6%A8%A1%E5%9E%8B&fr=index&red_tag=0061946759"));
        Element threadList = document.getElementById("thread_list");
        List<Element> jThreadLists = threadList.getElementsByClass("j_thread_list");
        System.out.println("下面是本页所有帖子的标题：");
        jThreadLists.stream().forEach(tiezi->{
            System.out.println("============================");
            System.out.println(tiezi.getElementsByClass("threadlist_title").get(0).getElementsByTag("a").text());
        });
        List<Element> aTags = threadList.getElementsByClass("vpic_wrap").subList(0,20);
        long start =  System.nanoTime();
        System.out.println("开始下载图片");
        System.out.println(start);
        downloadImgs(aTags);
//        downloadImgsNew(aTags);
        System.out.println("图片下载完毕,消耗时间：");
        long end =  System.nanoTime();
        System.out.println(end-start);
    }

    public static void downloadImgs(List<Element> aTags){
        aTags.stream().forEach(aTag-> Reptile.downloadImage(aTag.getElementsByTag("img").get(0).attr("bpic")));
    }

    public static void downloadImgsNew(List<Element> aTags){
        for (int i = 0; i<4; i++){
            List<Element> subList = aTags.subList(i*5, (i+1)*5);
            new Thread(() -> {
                subList.stream().forEach(aTag-> Reptile.downloadImage(aTag.getElementsByTag("img").get(0).attr("bpic")));
                System.out.println(System.nanoTime());
            }).start();
        }
    }
}
