package cn.jsoup.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wu on 2016/9/14.
 */
public class JsouTest {
    @Test
    public void firstTest() {
        String html = "<html><head><title>测试页</title></head><body><p>第一个Jsoup解析测试</p></body></html>";
        Document doc = Jsoup.parse(html);
        // 获取标题
        // 获取 P 标签
        Elements p = doc.getElementsByTag("p");
        System.out.println(p.text());
    }

    @Test
    public void secondTest() throws IOException {
        File file = new File("D:\\Vbox\\html\\itcast.html");
        Document doc = Jsoup.parse(file, "utf-8");
        // 获取 标题
//        String title = doc.title();
//        System.out.println(title);

        // 获取课程 列表
        Elements select = doc.select("ul.nav_txt span");
        System.out.println(select.text());
    }

    @Test
    public void threadTest() throws IOException {

        Map<String, String> cookies = new HashMap<String, String>();
        cookies.put("SUB", "_2AkMgjyKnf8NhqwJRmPoVzWLkbo91zwvEieLBAH7sJRMxHRl-yT83qlE-tRBugK4pNGFqQOeZ2jhXtWhgd1IgAg..");
        cookies.put("SUBP", "0033WrSXqPxfM72-Ws9jqgMF55529P9D9WW9C3G0qo3UXk7EHDK3UEDO");
        cookies.put("UOR", "developer.51cto.com,widget.weibo.com,developer.51cto.com");
        cookies.put("login_sid_t", "a261bc25e536479a15b52b2841d44a1e");
        cookies.put("YF-Ugrow-G0", "169004153682ef91866609488943c77f");
        cookies.put("YF-V5-G0", "73b58b9e32dedf309da5103c77c3af4f");
        cookies.put("WBStorage", "86fb700cbf513258|undefined");
        cookies.put("_s_tentry", "-");
        cookies.put("Apache", "338790751485.71356.1473560316633");
        cookies.put("SINAGLOBAL", "338790751485.71356.1473560316633");
        cookies.put("ULV", "1473560322022:1:1:1:338790751485.71356.1473560316633:");
        cookies.put("YF-Page-G0", "19f6802eb103b391998cb31325aed3bc");

        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36";
        Document doc = Jsoup.connect("http://weibo.com").cookies(cookies).userAgent(userAgent).get();
        // 获取 标题
        String title = doc.title();
        System.out.println(title);

        // 获取课程 列表
//        Elements select = doc.select("div.qrcode-text");
//        System.out.println(select.text());
    }

    @Test
    public void fourTest() throws IOException {
        File file = new File("D:\\Vbox\\html\\itcast.html");
        Document doc = Jsoup.parse(file, "utf-8");
        // 获取课程 列表
        Elements select = doc.select("ul.nav_txt a");
        for (Element a : select) {
//            Elements span = a.select("span");
//            System.out.println(a.select("span").text() + " : " + a.attr("href"));
            if (a.select("span").text().contains("JavaEE")) {
                a.attr("href", "itcast.cn");
            }
            System.out.println(a.select("span").text() + " : " + a.attr("href"));
        }
    }

    @Test
    public void fiveTest() throws IOException {
        File file = new File("D:\\Vbox\\html\\itcast.html");
        Document doc = Jsoup.parse(file, "utf-8");
        Elements select = doc.select("body > div.zx > ul > li.li_5.li_gd > div > p");

        System.out.println(select.text() +"　：　"+select.attr("href"));
    }

}
