package cn.jsoup.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wu on 2016/9/15.
 */
public class HttpclientTest {
    @Test
    public void firstTest() throws URISyntaxException {
        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
        httpGet.setURI(new URI("http://www.weibo.com"));

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
                System.out.println(pageSource);
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
        }

    }

    @Test
    public void secondTest() throws URISyntaxException {
        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
        httpGet.setURI(new URI("http://www.weibo.com"));
//        httpGet.setURI(new URI("http://finance.sina.com.cn/stock/"));
        httpGet.setHeader("Cookie", "SUB=_2AkMgjyKnf8NhqwJRmPoVzWLkbo91zwvEieLBAH7sJRMxHRl-yT83qlE-tRBugK4pNGFqQOeZ2jhXtWhgd1IgAg..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WW9C3G0qo3UXk7EHDK3UEDO; SINAGLOBAL=338790751485.71356.1473560316633; UOR=developer.51cto.com,widget.weibo.com,www.zhouhua.info; ULV=1473836347615:4:4:4:7606680749832.631.1473836347525:1473582869152; login_sid_t=138ec122adf5fed1e329ce05f20e62b8; TC-Ugrow-G0=5e22903358df63c5e3fd2c757419b456; TC-V5-G0=2030a6a079209a7c31934a48cfe2f5f6; WBStorage=86fb700cbf513258|undefined");
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
                // 输出 获取到的源码
                System.out.println(pageSource);
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
        }

    }

    @Test
    public void threadTest() throws URISyntaxException {
        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
        httpGet.setURI(new URI("http://www.weibo.com"));
        httpGet.setHeader("Cookie", "SUB=_2AkMgjyKnf8NhqwJRmPoVzWLkbo91zwvEieLBAH7sJRMxHRl-yT83qlE-tRBugK4pNGFqQOeZ2jhXtWhgd1IgAg..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WW9C3G0qo3UXk7EHDK3UEDO; SINAGLOBAL=338790751485.71356.1473560316633; UOR=developer.51cto.com,widget.weibo.com,www.zhouhua.info; ULV=1473836347615:4:4:4:7606680749832.631.1473836347525:1473582869152; login_sid_t=138ec122adf5fed1e329ce05f20e62b8; TC-Ugrow-G0=5e22903358df63c5e3fd2c757419b456; TC-V5-G0=2030a6a079209a7c31934a48cfe2f5f6; WBStorage=86fb700cbf513258|undefined");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36");

        HttpHost httpHost = new HttpHost("127.0.0.1", 8118);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)
                .setSocketTimeout(10000).setProxy(httpHost).build();

        httpGet.setConfig(config);

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
                System.out.println(pageSource);
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
        }

    }

    @Test
    public void fourTest() throws URISyntaxException {
        // 获取一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个GET请求
        HttpGet httpGet = new HttpGet();
        // 设置要请求的网页
//        httpGet.setURI(new URI("http://list.jd.com/list.html?cat=9987,653,655&ev=exbrand_8557&go=0&trans=1&JL=3_%E5%93%81%E7%89%8C_%E5%8D%8E%E4%B8%BA%EF%BC%88HUAWEI%EF%BC%89#J_crumbsBar"));
        httpGet.setURI(new URI("http://eladies.sina.com.cn/"));
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
                String reg = "(\\{.*?\\}\\}\\] \\})";     // matcher他的参数是需要被匹配的文本
                Matcher matcher = Pattern.compile(reg).matcher(pageSource);
                String text = "";
                while (matcher.find()) {
                    text += matcher.group(1);
                }
                System.out.println(text);
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
        }

    }
}
