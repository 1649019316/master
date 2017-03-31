package cn.jsoup.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wu on 2016/9/15.
 */
public class HttpclientVsJsoupTest {
    @Test
    public void aVoid() throws URISyntaxException {

        DBCollection dbCollection = OuMongoDBUtils.getCollection("crawler", "jd");


        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
        httpGet.setURI(new URI("http://list.jd.com/list.html?cat=9987,653,655&ev=exbrand_8557&go=0&trans=1&JL=3_%E5%93%81%E7%89%8C_%E5%8D%8E%E4%B8%BA%EF%BC%88HUAWEI%EF%BC%89#J_crumbsBar"));
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36");

//        HttpHost httpHost = new HttpHost("121.61.103.202", 8118);
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)
//                .setSocketTimeout(10000).setProxy(httpHost).build();
//        httpGet.setConfig(config);

        // 接收获取的流对象
        CloseableHttpResponse response = null;
        try {
            // 执行GET请求
            response = httpClient.execute(httpGet);
            // 判断状态码 200 为获取成功
            if (200 == response.getStatusLine().getStatusCode()) {
                // 把流对象转换为String 转换时并设置编码
                String pageSource = EntityUtils.toString(response.getEntity(), "utf-8");
                // 输出 获取到的源码
//                System.out.println(pageSource);
//                Document pageDoc = Jsoup.parse(pageSource);
//                // 获取标题
//                String title = pageDoc.title();
//                // 输出标题
//                System.out.println(title);
//                // 获取所有手机名称 的DIV 内容是JSON 在JS中加载, 动态解析的, 所以无法用jsoup 选择器进行获取, 这里使用正则
//                Elements select = pageDoc.select("div.p-name.p-name-type3");
//                System.out.println(select);
                // 正则表达式
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            OuMongoDBUtils.close(dbCollection);
        }
    }

    public String getPageSource(String url) throws URISyntaxException {
        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
        httpGet.setURI(new URI(url));
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36");

        // 接收获取的流对象
        CloseableHttpResponse response = null;
        try {
            // 执行GET请求
            response = httpClient.execute(httpGet);
            // 判断状态码 200 为获取成功
            if (200 == response.getStatusLine().getStatusCode()) {
                // 把流对象转换为String 转换时并设置编码
                String pageSource = EntityUtils.toString(response.getEntity(), "utf-8");
                return pageSource;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void mongodbInertTest() {
        DBCollection dbCollection = OuMongoDBUtils.getCollection("crawler", "jd");
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("测试", "sdfsf");
        dbCollection.insert(basicDBObject);
        OuMongoDBUtils.close(dbCollection);

    }

}
