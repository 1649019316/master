package com.demo.utils;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageFileUtils {

	public static void zoomToDisk(String src, String dest, int height, int width, String fileType) throws Exception {
		double Ratio = 0.0;
		File f = new File(src);
		if (!f.isFile()) {
			throw new Exception(f + " is not image file error!");
		}
		String ftype = "jpg";
		if (fileType.indexOf("gif") != -1 || fileType.indexOf("png") != -1) {
			ftype = "gif";
		} else if (fileType.indexOf("bmp") != -1) {
			ftype = "bmp";
		}
		
		BufferedImage bi = ImageIO.read(f);
		if ((bi.getHeight() > height && height != -1) || (bi.getWidth() > width)) {// 缩放，缩小保存
			if (bi.getHeight() > bi.getWidth() && height != -1) {
				Ratio = (new Integer(height)).doubleValue() / bi.getHeight();
			} else {
				Ratio = (new Integer(width)).doubleValue() / bi.getWidth();
			}
			File ThF = new File(dest);
			height = (int) (bi.getHeight() * Ratio);
			Image Itemp = bi.getScaledInstance(width, height, BufferedImage.TYPE_3BYTE_BGR);
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
			Itemp = op.filter(bi, null);
			try {
				ImageIO.write((BufferedImage) Itemp, ftype, ThF);
				//Icon ret = new ImageIcon(ThF.getPath());
			} catch (Exception ex) {

			}
		} else {
			File destFile = new File(dest);
			File pFile = destFile.getParentFile();
			if (!pFile.exists()) {
				pFile.mkdirs();
			}
			ImageIO.write(bi, ftype, destFile);
		}
	}

	public static void zoomToDisk(File file, String dest, String fileType) throws Exception {
		if (!file.isFile()) {
			throw new Exception(file + " is not image file error!");
		}
		String ftype = "jpg";
		if (fileType.indexOf("gif") != -1) {
			ftype = "gif";
		} else if (fileType.indexOf("bmp") != -1) {
			ftype = "bmp";
		} else if (fileType.indexOf("png") != -1) {
			ftype = "png";
		}
		BufferedImage bi = ImageIO.read(file);
		File destFile = new File(dest);
		File pFile = destFile.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		ImageIO.write(bi, ftype, destFile);
	}

	public static void saveToDisk(byte[] bytes, String dest, String fileType) throws Exception {
		String ftype = "jpg";
		if (fileType.indexOf("gif") != -1) {
			ftype = "gif";
		} else if (fileType.indexOf("bmp") != -1) {
			ftype = "bmp";
		} else if (fileType.indexOf("png") != -1) {
			ftype = "png";
		}
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(in);
		File destFile = new File(dest);
		File pFile = destFile.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		ImageIO.write(bi, ftype, destFile);
	}

	public static void saveToDisk(InputStream in, String dest, String fileType) throws Exception {
		String ftype = "jpg";
		if (fileType.indexOf("gif") != -1) {
			ftype = "gif";
		} else if (fileType.indexOf("bmp") != -1) {
			ftype = "bmp";
		} else if (fileType.indexOf("png") != -1) {
			ftype = "png";
		}
		BufferedImage bi = ImageIO.read(in);
		File destFile = new File(dest);
		File pFile = destFile.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		ImageIO.write(bi, ftype, destFile);
	}
}
