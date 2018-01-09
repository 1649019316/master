package com.demo.ocr.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by WSK on 2017/1/6.
 */
public class OCR {
    /**
     * @param srImage 图片路径
     * @param ZH_CN 是否使用中文训练库,true-是
     * @return 识别结果
     */
    private static String FindOCR(String srImage, boolean ZH_CN) {
        try {
            System.out.println("start");
            double start=System.currentTimeMillis();
            File imageFile = new File(srImage);
            if (!imageFile.exists()) {
                return "图片不存在";
            }
//          BufferedImage textImage = ImageIO.read(imageFile);
            ITesseract instance = new Tesseract();  
            File tessDataFolder = LoadLibs.extractTessResources("tessdata");
            instance.setDatapath(tessDataFolder.getAbsolutePath());
            if (ZH_CN){
            	instance.setLanguage("chi_sim");//中文识别
            }else{
            	instance.setLanguage("eng");//英文库识别数字比较准确
            }
            String result = null;
            result = instance.doOCR(imageFile);
            double end=System.currentTimeMillis();
            System.out.println("耗时"+(end-start)/1000+" s");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "发生未知错误";
        }
    }
    
    public static void main(String[] args) throws Exception {
        String result=FindOCR("C:\\Users\\admin\\Desktop\\图片识别样本\\澳棉\\仓库入库通知.pdf",false);
//        String result=FindOCR("C:\\Users\\admin\\Desktop\\博客.png",true);
        System.out.println(result);
    }
}
