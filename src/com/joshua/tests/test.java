package com.joshua.tests;

import com.joshua.webpage.java.Reptile;
import com.joshua.webpage.java.bean.Book;
import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmk on 18-3-28.
 */
public class test {
    public static void main(String[] args) {
        String ids = "25723164,25713131,25703379,25703095,25702043,25687867,25686838,25685101,25652454,25651181,25639471,29795817,29795816,29795815,29795814,29795813,29795812,29795811,29795810,25634836,25625433,25602703,25556122,25553273,29795770,25548184,25520466,25492049,25485291,25480294,25467704,25467479,25462561,25458640,25458638,25449922,25445662,25419583,25388167,25385182,25377429,25368426,26958736,27129125,25305229,25263554,25240951,25233117,25187694,25149274,25143397,25142735,25078977,25078976,24997067,24986085,24985131,24966302,24962242,24951648,24936595,24926531,24920877,24918056,24902473,24848465,24792229,29568819,27128614,24687511,24633673,24558222,24536060,24529636,24528522,24516376,24507191,24505439,24398530,24388108,27128233,25856154,24324693,24252239,24198369,24174456,24114793,24111391,24074301,24015398,24002445,24001767,23947345,23942120,23940348,23896588,23896304,23853121,23820719,23792571,23765393,23756297,23748920,23728622,23719451,23641450,23631715,23573183,25013744,23515222,23514716,23480880,23478212,23432842,23432841,23432840,23432839,23432838,23432837,23432836,23432835,23432834,23432833,23432832,23432831,23432830,23432829,23432828,23432827,23432826,23416221,23366172,23334672,29589342,23089761,23089746,23089196,23076103,23034302,23026969,23007459,22969001,22966762,22964593,22926415,22916917,22913414,22913382,22913345,22905859,22905782,22901281,22901240,22901099,22901059,22901035,22901008,22900500,22882278,22882228,22862551,22862423,22846505,22817673,22817653,22801368,22727695,22427953,22354305,22352983,22328084,22304918,22278637,22255764,22255697,22255692,22255206,22231401,22219382,22206009,22189060,29539330,29539327,22002477,21927617,21912479,21878642,21866250,21865074,21652023,21645221,21543574,21518958,21508152,21507571,21373701,21335608,21307872,21176953,21150842,21148206,21147953,21118759,21037262,20958918,20836798,20836797,20836794,20836793,20705649,20685816,29537542,20629773,20629771,20513434,19965082,19883615,19785031,19668231,19410536,19405722,19329851,19329850,19262908,19255380,19147494,19021360,18997609,18842628,18726955,18691008,18651798,18472004,18452037,18180699,18032559,17964248,17959743,17956130,17885692,17850086,29539155,17494914,29539180,29472754,29644670,29644630,17046691,16741758,16547736,29795718,29795717,29795716,29795715,29795714,29795713,16507997,16107706,15574745,14522728,12763405,11764000,11763999,11763998,11763997,11763996,11763995,11444531,11444530,11444529,11444528,11444527,11444526,11243412,11243411,11243410,11243409,11243408,11243407,11097426,11097425,11097424,11097423,11097422,11097421,10421079,29350025,8911606,29681064,29681377,8496902,1820214,1820213,1820212,1820211,1820210,1820209,1820208,1820207,1840296,1820206,1820205,1820204,1820203,1726467,1668145,1668144,29539672,1820219,1820218,1820217,1820216,1820215,1687916,1668146,29314950,29314948,29657358,29539675,3385857,3385856,3385855,3385854,3385853,3385852,3385851,2968463,2838650,29314881,3346942,3346941,3346940,3346939,3346938,3346937,3346935,3258041,3258040,29320230,3694741,3694740,3694739,3694738,3694737,3694736,3694735,3694734,3694733,3694732,3694731,3694730,3501017,3501016,3442297,3320384,3320383,3501480,3501479,3501478,3430677,3430676,3430675,3430674,3430673,3430672,3430671,3430670,3430669,3430668,3430667,3430666,3323547,3123710,2963139,2828648,2828647,3669144,3669143,3669142,3669141,3669140,3669139,3669138,3669137,3669136,3669135,3669134,3669133,3669132,3669131,3669130,3312627,2822943,3612936,3612935,3612934,3612933,3612932,3612931,3612930,3612929,3612928,3612927,3612926,3612925,3497282,3586101,3586100,3586099,3586098,3586097,3586096,3586095,3295281,3295280,3035206,3573140,3573139,3573138,3573137,3573136,3573135,3573134,3573133,3573132,3573131,3573130,3573129,3573128,3494853,3106648,3806763,3806762,3806761,3806760,3806759,3806758,3806757,3806756,3806755,3806754,3806753,3806752,3806751,3806750,3795342";
        System.out.println(ids.split(",").length);
        downLoardXml(ids,1,"https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=");
    }

    private static void downLoardXml(String ids, int fileNum, String uris) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            uris = uris.replaceAll(" ", "%20");
            HttpPost httpPost = new HttpPost(uris + ids);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("db", "pubmed"));
            params.add(new BasicNameValuePair("retmode", "xml"));
            params.add(new BasicNameValuePair("id", ids));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).setConnectionRequestTimeout(5 * 1000).setSocketTimeout(2 * 60 * 1000).build();
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();//得到请求回来的数据
            System.out.println(entity.getContent());
            String xmlStr = EntityUtils.toString(entity);
            String path = "/home/bmk/pubmed/";
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            try {
                Files.write(Paths.get(path + "/pubmed" + fileNum + ".xml"), xmlStr.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            downLoardXml(ids, fileNum, uris);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel(JSONArray result){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        for (int i = 0; i<result.size();i++){
            HSSFRow row = sheet.createRow(i);
            JSONArray value = result.getJSONArray(i);
            for (int j=0;j<value.size();j++){
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(value.getString(j));
            }
        }
        File file = new File("/home/bmk/ganju/phenotype/test.xls");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
