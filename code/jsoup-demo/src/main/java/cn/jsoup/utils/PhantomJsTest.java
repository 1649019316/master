package cn.jsoup.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wu on 2016/9/26.
 */
public class PhantomJsTest {

    @Test // 调用第三方程序 PhantomJS 网页源码
    public void tGet() {
        String phatomJsPath = "D:\\Vbox\\phantomjs.exe";
        String jsPath = "D:\\Vbox\\js\\getPageSouce.js";
        String url = "http://m.sohu.com/";
        Process process = null;
        try {
            // 使用Process 调用第三方程序
            process = Runtime.getRuntime().exec(phatomJsPath + " " + jsPath + " " + url);
            // 获取第三方程序 的输出流
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            String pageSource = "";
            // 逐行读取源码
            while ((line = bfr.readLine()) != null) {
                pageSource += line;
            }
            // 等待第三方程序结束
            process.waitFor();
            // 输出获取的源码
            System.out.println(pageSource);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    @Test
    public void aVoid() throws URISyntaxException {

        DBCollection dbCollection = OuMongoDBUtils.getCollection("crawler", "jd");

        try {
                // 商品列表 URL
                String pageSource = getPageSource("http://list.jd.com/list.html?cat=9987,653,655&ev=exbrand_8557&go=0&trans=1&JL=3_%E5%93%81%E7%89%8C_%E5%8D%8E%E4%B8%BA%EF%BC%88HUAWEI%EF%BC%89#J_crumbsBar");
                String reg = "var slaveWareList =(\\{.*?\\}\\}\\] \\})";     // matcher他的参数是需要被匹配的文本
                Matcher matcher = Pattern.compile(reg).matcher(pageSource);
                String text = "";
                while (matcher.find()) {
                    text += matcher.group(1);
                }

                String regId = "\\{\"(\\d+)\":\\{";     // matcher他的参数是需要被匹配的文本
                Matcher matcherId = Pattern.compile(regId).matcher(text);
                List<String> list = new ArrayList<String>();

                while (matcherId.find()) {
                    try {
                        list.add(matcherId.group(1));
                    } catch (Exception e) {
                    }
                }
                for (String id : list) {
                    String url = "http://item.jd.com/" + id + ".html";
                    String pageSourceId = getPageSource(url);
                    if (pageSourceId != null) {
                        BasicDBObject basicDBObject = new BasicDBObject();

                        Document pageDocId = Jsoup.parse(pageSourceId);

                        String 分辨率 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter1.p-parameter-list > li.fore0 > div > p").text();
                        basicDBObject.put("分辨率",分辨率);
                        String 后置摄像头 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter1.p-parameter-list > li.fore1 > div > p:nth-child(1)").text();
                        basicDBObject.put("后置摄像头",后置摄像头);
                        String 前置摄像头 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter1.p-parameter-list > li.fore1 > div > p:nth-child(2)").text();
                        basicDBObject.put("前置摄像头",前置摄像头);
                        String 核数 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter1.p-parameter-list > li.fore2 > div > p").text();
                        basicDBObject.put("核数",核数);
                        String 品牌 = pageDocId.select("#parameter-brand > li").text();
                        basicDBObject.put("品牌",品牌);
                        String 商品名称 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(1)").text();
                        basicDBObject.put("商品名称",商品名称);
                        String 商品编号 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(2)").text();
                        basicDBObject.put("商品编号",商品编号);
                        String 店铺 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(3) > a").text();
                        basicDBObject.put("店铺",店铺);
                        String 系统 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(5)").text();
                        basicDBObject.put("系统",系统);
                        String 运行内存 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(6)").text();
                        basicDBObject.put("运行内存",运行内存);
                        String 商品毛重 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(4)").text();
                        basicDBObject.put("商品毛重",商品毛重);
                        String 像素 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(8)").text();
                        basicDBObject.put("像素",像素);
                        String 购买方式 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(7)").text();
                        basicDBObject.put("购买方式",购买方式);
                        String 电池容量 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(9)").text();
                        basicDBObject.put("电池容量",电池容量);
                        String 机身内存 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(10)").text();
                        basicDBObject.put("机身内存",机身内存);
                        String 热点 = pageDocId.select("#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li:nth-child(11)").text();
                        basicDBObject.put("热点",热点);

                        dbCollection.insert(basicDBObject);

                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            OuMongoDBUtils.close(dbCollection);
        }
    }


    @Test // 测试 封装的获取源码方法
    public void testGetPageSource() {
        String url = "http://baidu.com";
        System.out.println(getPageSource(url));
    }
    // 封装 获取 源码方法
    public static String getPageSource(String url) {
        String phatomJsPath = "D:\\Vbox\\phantomjs.exe";
        String jsPath = "D:\\Vbox\\js\\getPageSouce.js";
        Process process = null;
        BufferedReader bfr = null;
        try {
            // 使用Process 调用第三方程序
            process = Runtime.getRuntime().exec(phatomJsPath + " " + jsPath + " " + url);
            // 获取第三方程序 的输出流
            bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            String pageSource = "";
            // 逐行读取源码
            while ((line = bfr.readLine()) != null) {
                pageSource += line;
            }
            // 等待第三方程序结束
            process.waitFor();
            // 返回获取的源码
            return pageSource;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
