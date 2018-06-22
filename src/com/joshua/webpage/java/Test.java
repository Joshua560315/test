package com.joshua.webpage.java;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.joshua.webpage.java.Interface.BufferedReaderProcessor;
import com.joshua.webpage.java.bean.TreeNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Created by linzl on 16-8-3.
 */

public class Test {
    static final String sss = "我好啊";

    public static void main(String[] args) throws IOException {
        System.out.println(new File("/home/bmk/桌面/test").mkdir());
    }


    public static boolean isMatch(String s, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
    static int min(int[] arr){
        return min2(arr,0,arr.length-1);
    }

    static int min2(int[] arr,int start,int end){
        if (end-start==1){
            return Math.min(arr[start],arr[end]);
        }else {
            int length = end-start+1;
            if (length%2==0){
                int middleNum = length/2;
                int leftMin = min2(arr,start,start+middleNum-1);
                int rightMin = min2(arr,start+middleNum,end);
                return Math.min(leftMin,rightMin);
            }else {
                int middleNum =(length+1)/2;
                int leftMin = min2(arr,start,start+middleNum-1);
                int rightMin = min2(arr,start+middleNum-1,end);
                return Math.min(leftMin,rightMin);
            }
        }
    }
    /**
     * 二叉树重构
     **/
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);
        for(int i=startIn;i<=endIn;i++)
        if(in[i]==pre[startPre]){
            root.setLeft(reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1));
            root.setRight(reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn));
        }
        return root;
    }

    /**
     *二叉树前序遍历
     */
    static void DLR(TreeNode treeNode){
        if (treeNode!=null){
            System.out.println(treeNode.getVal());
            DLR(treeNode.getLeft());
            DLR(treeNode.getRight());
        }
    }


    static String newReadOut(int num){
        String origin = "1";
        for (int i =1;i<num;i++){
            origin = newRead(origin);
        }
        return origin;
    }

    static String newRead(String string){
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("(.)\\1+|([1-9])");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()){
            int size = matcher.group(0).length();
            String statement;
            if (size>1){
                statement = matcher.group(1);
            }else {
                statement = matcher.group();
            }
            sb.append(size);
            sb.append(statement);
        }
        return sb.toString();
    }

    public static String isRepeatString(String string){
        Pattern pattern = Pattern.compile("[a-z]{0,10000}");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()){
            return "false";
        }

        List<Integer> divisors = getDivisors(string.length());
        boolean flag = false;
        int repeateNum = 0;
        for (Integer divisor: divisors){
            if (getFixedLengthStrEquals(string,divisor)){
                flag = true;
                repeateNum = divisor;
                break;
            }
        }
        if (flag){
            return  "true,metaString is "+string.substring(0,repeateNum);
        }else {
            return "false";
        }
    }

    static List<Integer> getDivisors(int num){
        List<Integer> divisors = new ArrayList<>();
        for (int i=1;i<num;i++){
            if (num%i==0)
                divisors.add(i);
        }
        return divisors;
    }

    static boolean getFixedLengthStrEquals(String string,int length){
        String metaStr = string.substring(0,length);
        boolean flag = true;
        for (int i =length;i<string.length()-length;i=i+length){
            if (!metaStr.equals(string.substring(i,i+length))){
                flag = false;
                break;
            }
        }
        return flag;
    }
    public static List<String> readDir2FileSuffix(String path, String suffix) {
        List<String> list = new ArrayList<String>();
        File dirFile = new File(path);
        String[] filStrings = dirFile.list();
        if (filStrings.length > 0) {
            for (String string : filStrings) {
                if (string.endsWith(suffix)) {
                    list.add(getPathByPath(path) + string);
                }
            }
        }
        return list;
    }

    public static String getPathByPath(String file){
        if(file.matches("^.*/{2,}.*$")){
            file = file.replaceAll("/{2,}", "/");
        }
        if (!file.matches("^.*/+$")) {
            file += "/";
        }
        return file;
    }

    public static String getParameterJsonReflect(Object object,Class<?> clazz){
        try{
            JSONObject jsonObject = new JSONObject();
            Field[] fields = clazz.getDeclaredFields();
            Arrays.asList(fields).stream().forEach(field -> {
                String paramName = field.getName();
                jsonObject.put(paramName,getFieldValueByName(paramName,object).toString());
            });
            return jsonObject.toString();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    public static boolean runShellCode(String shellCode){
        try{
            String shellFilePath = "/home/bmk/桌面/"+genUUID()+".sh";
            FileUtils.writeStringToFile(new File(shellFilePath),shellCode);
            Process ps = Runtime.getRuntime().exec("sh "+shellFilePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println("error:"+result);
            return true;
        } catch (Exception e){
            System.out.println("exception:"+e.getMessage());
            return false;
        }
    }

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str
                .substring(19, 23) + str.substring(24);
        return temp;
    }
    public static JSONObject getParamFromShell(String shellCode){
        JSONObject paramSetting = new JSONObject();
        shellCode = shellCode.substring(shellCode.indexOf("-"),shellCode.length());
        Matcher matcher = Pattern.compile("-[\\w]+\\s+[\\w]+").matcher(shellCode);
        while (matcher.find()) {
            String[] param = matcher.group().split("\\s+");
            paramSetting.put(param[0].replace("-",""),param[1]);
        }
        return paramSetting;
    }


    public static void additionalContent(File file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static String parseToCientificNotation(double value) {
        int cont = 0;
        java.text.DecimalFormat DECIMAL_FORMATER = new java.text.DecimalFormat("#.000");
        if (value>10){
            while (((int) value) >10) {
                value /= 10;
                cont++;
            }
        } else {
            while (((int) value) ==0) {
                value *= 10;
                cont--;
            }
        }
        return DECIMAL_FORMATER.format(value)+ "E" + cont;
    }





    public static boolean isNumber(String str) {
        try{
            Double doubleValue = Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String escapeSequence(String string) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile("[/./(/)]").matcher(string);
        while (matcher.find()) {
            matcher.appendReplacement(sb,"\\\\"+matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private  String replaceIgnoreCaps(String string, String keyword){
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile("(?i)"+keyword).matcher(string);
        while (matcher.find()) {
            matcher.appendReplacement(sb , "<span style = \"color:#F0870D\">"+matcher.group()+"</span>");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void zipMultiFile(String filepath ,String zippath, boolean dirFlag) {
        try {
            File file = new File(filepath);// 要被压缩的文件夹
            File zipFile = new File(zippath);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File fileSec:files){
                    if(dirFlag){
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    }else{
                        recursionZip(zipOut, fileSec, "");
                    }
                }
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fileSec:files){
                recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
            }
        }else{
            byte[] buf = new byte[1024];
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
            int len;
            while((len = input.read(buf)) != -1){
                zipOut.write(buf, 0, len);
            }
            input.close();
        }
    }

    public static String getMD5String(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such AlgorithmException");
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }


    public static void readPdf(String pdfPath, String outputPath) throws IOException{
        PdfReader pdfReader =   new PdfReader(pdfPath);
        int pageNum = pdfReader.getNumberOfPages();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        String pageText;
        for (int i=1;i<pageNum;i++) {
            pageText = PdfTextExtractor.getTextFromPage(pdfReader,i);
            pageText = pageText.replaceAll("\n"," ");
            sb.append(pageText);
            if (pageText.contains("Enhancer modeling uncovers transcriptional signatures of individual cardiac cell states in Drosophila")) {
                flag = true;
                break;
            }
        }
        System.out.println(flag?"有效":"无效");
        FileUtils.writeStringToFile(new File(outputPath),sb.toString());
        pdfReader.close();
    }
}
