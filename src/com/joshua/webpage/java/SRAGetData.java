package com.joshua.webpage.java;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linzl on 16-12-29.
 */
public class SRAGetData {
    public static void main(String[] args) {
        getBySrr("/home/bmk/srrData/srrUrls");
//      getBySrx("/home/bmk/srrData/srxUrls");
 }


    private static void getBySrx(String srxUrlsPath){
        try{
            List<String> SRXurls = FileUtils.readLines(new File(srxUrlsPath));
            List<SRFile> srFiles = new ArrayList<>();
            Set<String> srrUrls = new HashSet<>();
            SRXurls.stream().forEach(url->{
                if (!url.startsWith("https://www.ncbi.nlm.nih.gov/sra/")){
                    url = "https://www.ncbi.nlm.nih.gov/sra/"+url;
                }
                String srxPage = Reptile.get(url.trim());

                Matcher matcher1 = Pattern.compile("trace\\.ncbi\\.nlm\\.nih\\.gov/Traces/sra/\\?run=[ES]RR\\d+").matcher(srxPage);
                while(matcher1.find()){
                    System.out.println(matcher1.group());
                    srrUrls.add("https://"+matcher1.group());
                }
            });
            System.out.println("扒取出的SRR为"+srrUrls.size()+"个");
            srrUrls.stream().forEach(srrUrl->{
                SRFile srFile = getSrFile(srrUrl);
                if (srFile!=null){
                    srFiles.add(srFile);
                }
            });
            StringBuilder sqlBuilder = new StringBuilder();
            StringBuilder dataBuilder = new StringBuilder();
            srFiles.stream().forEach(srFile -> printSql(srFile,sqlBuilder,dataBuilder));
            System.out.print(sqlBuilder.toString()+dataBuilder.toString());
        } catch (IOException e) {
            System.out.println("读取文件获取连接地址失败");
            e.printStackTrace();
        }
    }

    private static void getBySrr(String srrUrlsPath) {
        try{
            List<String> urls = FileUtils.readLines(new File(srrUrlsPath));
            List<SRFile> srFiles = new ArrayList<>();
            urls.stream().forEach(url->{
                if (!url.startsWith("https://trace.ncbi.nlm.nih.gov/Traces/sra/?run=")){
                    url = "https://trace.ncbi.nlm.nih.gov/Traces/sra/?run="+url;
                }
                SRFile srFile = getSrFile(url);
                if (srFile!=null){
                    srFiles.add(srFile);
                }
            });
            StringBuilder sqlBuilder = new StringBuilder();
            StringBuilder dataBuilder = new StringBuilder();
            srFiles.stream().forEach(srFile -> printSql(srFile,sqlBuilder,dataBuilder));
            System.out.print(sqlBuilder.toString()+dataBuilder.toString());
        } catch (IOException e) {
            System.out.println("读取文件获取连接地址失败");
            e.printStackTrace();
        }
    }

    private static SRFile getSrFile(String url){
        SRFile sr = null;
        try {
            sr = new SRFile();
            String result = Reptile.get(url.trim());
            Document document = Jsoup.parse(result);
            Element SRX = document.getElementById("exp-accession");
            Element base = document.getElementsByClass("file-size").get(1);
            sr.setBase(base.parent().text());
            sr.setId(url.substring(!url.contains("SRR")?url.indexOf("ERR"):url.indexOf("SRR")));
            sr.setType(result.contains("SINGLE")?"SINGLE":"PAIRED");
            sr.setSrxId(SRX.text());
            Matcher matcher = Pattern.compile("[ES]RP\\d+").matcher(result);
            while(matcher.find()) {
                sr.setSrpId(matcher.group());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取srr信息出错，出错链接为："+url);
        }
        return sr;
    }

    private static void printSql(SRFile srFile, StringBuilder sqlBuilder,StringBuilder dataBuilder) {
        String type = srFile.getType();
        String id = srFile.getId();
        String srpId = srFile.getSrpId();
        String srxId = srFile.getSrxId();
        String base = srFile.getBase();
        long bases = 0;
        if (base.endsWith("Mbp")) {
            double size = Double.parseDouble(base.substring(0,base.indexOf("Mbp")));
            size = size*1024*1024;
            bases = (long) Math.ceil(size);
        } else if(base.endsWith("Gbp")) {
            double size = Double.parseDouble(base.substring(0,base.indexOf("Gbp")));
            size = size*1024*1024*1024;
            bases = (long) Math.ceil(size);
        }
            if (type.equals("PAIRED")){
                String sql1 = "INSERT INTO `t_file_download` (`run_id`, `file_name`, `file_path`, `file_state`, `sra_file_size`) " +
                        "VALUES('"+id+"','"+id+"_1.fastq','/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+"_1.fastq','New','"+bases+"');\n";
                String sql2 = "INSERT INTO `t_file_download` (`run_id`, `file_name`, `file_path`, `file_state`, `sra_file_size`) " +
                        "VALUES('"+id+"','"+id+"_2.fastq','/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+"_2.fastq','New','"+bases+"');\n";
                String data1 = "/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+"_1.fastq\t"+bases+"\n";
                String data2 = "/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+"_2.fastq\t"+bases+"\n";
                sqlBuilder.append(sql1);
                sqlBuilder.append(sql2);
                dataBuilder.append(data1);
                dataBuilder.append(data2);

            }else {
                String sql = "INSERT INTO `t_file_download` (`run_id`, `file_name`, `file_path`, `file_state`, `sra_file_size`) " +
                        "VALUES('"+id+"','"+id+".fastq','/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+".fastq','New','"+bases+"');";
                String data = "/share/bioCloud/cloud/rawdata/"+srpId+"/"+srxId+"/"+id+".fastq\t"+bases;
                sqlBuilder.append(sql);
                dataBuilder.append(data);
            }

    }
}
