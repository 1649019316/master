package com.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author ayq
 * 
 */
public class ExcelUtils {
	private static Log logger = LogFactory.getLog(ExcelUtils.class);
	
	private static List<Short> indexlist;
	
	static{
		indexlist=new ArrayList<Short>();
	}

	public static void writeWorkbook(HSSFWorkbook wb, String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			logger.error(new StringBuffer("[").append(e.getMessage()).append(
					"]").append(e.getCause()));
		} catch (IOException e) {
			logger.error(new StringBuffer("[").append(e.getMessage()).append(
					"]").append(e.getCause()));
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				logger.error(new StringBuffer("[").append(e.getMessage())
						.append("]").append(e.getCause()));
			}
		}
	}

	public static HSSFWorkbook createWorkBook(String sysPath) throws IOException {
		InputStream input = ExcelUtils.class.getClassLoader()
				.getResourceAsStream(sysPath);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		return new HSSFWorkbook(fs);
	}

	public static HSSFWorkbook readWorkBook(InputStream inputStream) throws IOException{
		POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		return new HSSFWorkbook(fs);
	}
	
	public static HSSFWorkbook readWorkBook(File file) throws IOException{
		FileInputStream input = new FileInputStream(file);
		HSSFWorkbook hssfWorkbook =  readWorkBook(input);
		input.close();
		return hssfWorkbook;
	}
	
	
	
	public static HSSFWorkbook readWorkBook(URL url) throws IOException{
		InputStream input = url.openStream();
		HSSFWorkbook hssfWorkbook =  readWorkBook(input);
		input.close();
		return hssfWorkbook;
	}
	
	public static HSSFSheet createDefaultSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		sheet.setGridsPrinted(false);
		sheet.setDisplayGridlines(false);
		return sheet;
	}

	// set column width
	public static void setSheetColumnWidth(HSSFSheet sheet, int colNum,
			int length) {
		for (int i = 0; i <= colNum; i++) {
			sheet.setColumnWidth(i, length);
		}
	}

	// set excel cell
	public static void setCellValue(HSSFRow row, int column,
			HSSFCellStyle style, int cellType, Object value) {
		HSSFCell cell = row.getCell(column);
		if (cell == null) {
			cell = row.createCell(column);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		if(value==null){
			return;
		}
		String str = value.toString();
		if (StringUtils.isBlank(str)) {
			return;
		}
		switch (cellType) {
			case HSSFCell.CELL_TYPE_BLANK: {
				break;
			}
			case HSSFCell.CELL_TYPE_STRING: {
				cell.setCellValue(value == null ? "--" : str + "");
				break;
			}
			case HSSFCell.CELL_TYPE_NUMERIC: {
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Double.parseDouble(str));
				break;
			}
			case 6: {//日期格式
				cell.setCellValue((Date)value);
				break;
			}
			default:{
				break;
			}
				
		}
	}

	public static void copyRows(HSSFWorkbook wb, int fromsheet, int tosheet,
			int pStartRow, int pEndRow, int pPosition, boolean flag) {
		HSSFRow sourceRow = null;
		HSSFRow targetRow = null;
		HSSFCell sourceCell = null;
		HSSFCell targetCell = null;
		HSSFSheet sourceSheet = null;
		HSSFSheet targetSheet = null;
		CellRangeAddress oldRange = null;
		CellRangeAddress newRange = null;
		int cType;
		int i;
		int j;
		int targetRowFrom;
		int targetRowTo;
		if ((pStartRow == -1) || (pEndRow == -1)) {
			return;
		}
		sourceSheet = wb.getSheetAt(fromsheet);
		targetSheet = wb.getSheetAt(tosheet);
		List<CellRangeAddress> oldRanges = new ArrayList<CellRangeAddress>();
		for (i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
			oldRanges.add(sourceSheet.getMergedRegion(i));
		}

		// copy mergcell
		for (int k = 0; k < oldRanges.size(); k++) {
			oldRange = oldRanges.get(k);
			newRange = new CellRangeAddress(oldRange
					.getFirstRow(), oldRange.getLastRow(), oldRange
					.getFirstColumn(), oldRange.getLastColumn());

			if (oldRange.getFirstRow() >= pStartRow
					&& oldRange.getLastRow() <= pEndRow) {
				targetRowFrom = oldRange.getFirstRow() - pStartRow + pPosition;
				targetRowTo = oldRange.getLastRow() - pStartRow + pPosition;
				oldRange.setFirstRow(targetRowFrom);
				oldRange.setLastRow(targetRowTo);
				targetSheet.addMergedRegion(oldRange);
				if(fromsheet==tosheet){
					sourceSheet.addMergedRegion(newRange);
				}
				oldRange = null;
				newRange = null;
			}
		}
		// set width
		for (i = pStartRow; i <= pEndRow; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow != null) {
				for (j = sourceRow.getLastCellNum(); j > sourceRow
						.getFirstCellNum(); j--) {
					targetSheet
							.setColumnWidth(j, sourceSheet.getColumnWidth(j));
					targetSheet.setColumnHidden(j, false);
				}
				break;
			}
		}
		// copy row fill content
		for (i = pStartRow; i <= pEndRow; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow == null) {
				continue;
			}
			targetRow = targetSheet.createRow(i - pStartRow + pPosition);
			targetRow.setHeight(sourceRow.getHeight());
			for (j = sourceRow.getFirstCellNum(); j <= sourceRow
					.getPhysicalNumberOfCells(); j++) {
				sourceCell = sourceRow.getCell(j);
				if (sourceCell == null) {
					continue;
				}
				targetCell = targetRow.createCell(j);
				// targetCell.setEncoding(((Object) sourceCell).getEncoding());
				targetCell.setCellStyle(sourceCell.getCellStyle());
				cType = sourceCell.getCellType();
				targetCell.setCellType(cType);
				if (flag) {
					switch (cType) {
					case HSSFCell.CELL_TYPE_BOOLEAN:
						targetCell.setCellValue(sourceCell
								.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						targetCell.setCellErrorValue(sourceCell
								.getErrorCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						targetCell.setCellFormula(parseFormula(sourceCell
								.getCellFormula()));
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						targetCell.setCellValue(sourceCell
								.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING:
						targetCell.setCellValue(sourceCell
								.getRichStringCellValue());
						break;
					}
				}
			}
		}
	}

	public static String parseFormula(String pPOIFormula) {
		final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
		StringBuffer result = null;
		int index;

		result = new StringBuffer();
		index = pPOIFormula.indexOf(cstReplaceString);
		if (index >= 0) {
			result.append(pPOIFormula.substring(0, index));
			result.append(pPOIFormula.substring(index
					+ cstReplaceString.length()));
		} else {
			result.append(pPOIFormula);
		}

		return result.toString();
	}

	// merge cell
	public static int mergeCell(HSSFSheet sheet, int firstRow, int lastRow,
			int firstColumn, int lastColumn) {
		return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow,
				firstColumn, lastColumn));

	}

	public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress region,
			HSSFCellStyle cs) {
		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	public static Font createFont(HSSFWorkbook wb, short boldweight,
			short color, short size) {
		Font font = wb.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}

	public static CellStyle createCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign,
			Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		return cs;
	}

	public static CellStyle createBorderCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign,
			Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		cs.setBorderLeft(CellStyle.BORDER_DASHED);
		cs.setBorderRight(CellStyle.BORDER_DASHED);
		cs.setBorderTop(CellStyle.BORDER_DASHED);
		cs.setBorderBottom(CellStyle.BORDER_DASHED);
		return cs;
	}

	public static HSSFCellStyle createAllStyle(HSSFWorkbook wb,String fontName,
			Short boldWeight, Short fontHeight, Short inHeight,
			Short fontColor, Short lborder, Short rborder, Short tborder,
			Short bborder, Short align,Boolean wrap) {
		HSSFFont boldFont = wb.createFont();
		if (fontHeight == null) {
			boldFont.setFontHeight((short) 180);
		} else {
			boldFont.setFontHeight(fontHeight);
		}
		if(fontName == null){
			boldFont.setFontName("宋体");
		}else{
			boldFont.setFontName(fontName);
		}
		if (fontColor == null) {
			boldFont.setColor(HSSFColor.BLACK.index);
		} else {
			boldFont.setColor(fontColor);
		}
		if (inHeight == null) {
			boldFont.setFontHeightInPoints((short) 12);
		} else {
			boldFont.setFontHeightInPoints(inHeight);
		}
		if (boldWeight == null) {
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		} else {
			boldFont.setBoldweight(boldWeight);
		}
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		if(wrap==null){
			style.setWrapText(true);
		}else{
			style.setWrapText(wrap);
		}
		if (bborder == null) {
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		} else {
			style.setBorderBottom(bborder);
		}
		if (lborder == null) {
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		} else {
			style.setBorderLeft(lborder);
		}
		if (rborder == null) {
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		} else {
			style.setBorderRight(rborder);
		}
		if (tborder == null) {
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		} else {
			style.setBorderTop(tborder);
		}
		if (align == null) {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		} else {
			style.setAlignment(align);
		}
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	
	public static Object getCellValue(HSSFCell cell) throws IOException {
		Object value = "";
		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			value = cell.getRichStringCellValue().toString();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				value = sdf.format(date);
			} else {
				double value_temp = (double) cell.getNumericCellValue();
				BigDecimal bd = new BigDecimal(value_temp);
				BigDecimal bd1 = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
				value = bd1.doubleValue();
				
				/*
				DecimalFormat format = new DecimalFormat("#0.###");
				value = format.format(cell.getNumericCellValue());
				*/
			}
		}
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			value = "";
		}
		return value;
	}
	public static Object getCellValue(Cell cell) throws IOException {
		Object value = "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			value = cell.getRichStringCellValue().toString();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				value = sdf.format(date);
			} else {
				double value_temp = (double) cell.getNumericCellValue();
				BigDecimal bd = new BigDecimal(value_temp);
				BigDecimal bd1 = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
				value = bd1.doubleValue();
				
				/*
				DecimalFormat format = new DecimalFormat("#0.###");
				value = format.format(cell.getNumericCellValue());
				*/
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			value = "";
		}
		return value;
	}
	
	public static HSSFCellStyle createRowStartStyle(HSSFWorkbook wb, Short align, HSSFFont font, Boolean wrapText){
		HSSFCellStyle cellStyle = wb.createCellStyle();  
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色   
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框   
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框   
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//左边框   
		cellStyle.setAlignment(align); // 居中  
		cellStyle.setFont(font);//选择需要用到的字体格式  
		cellStyle.setWrapText(wrapText);//设置自动换行  
		return cellStyle;
	}

	public static HSSFCellStyle createRowCellStyle(HSSFWorkbook wb, Short align, HSSFFont font, Boolean wrapText){
		HSSFCellStyle cellStyle = wb.createCellStyle();  
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色   
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框   
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
		cellStyle.setAlignment(align); // 居中  
		cellStyle.setFont(font);//选择需要用到的字体格式  
		cellStyle.setWrapText(wrapText);//设置自动换行  
		return cellStyle;
	}

	
//	自定义颜色：为style添加颜色值设置
	public static void setCustomerColor(HSSFCellStyle style,HSSFWorkbook wb,String color) {
		short colorIndex=0;
		for (int i = 8; i < 64; i++) {
			colorIndex = (short) i;
			if(!indexlist.contains(colorIndex)){
				indexlist.add(colorIndex);
				break;
			}
		}
		//转为RGB码  
		int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制  
		int g = Integer.parseInt((color.substring(2,4)),16);  
		int b = Integer.parseInt((color.substring(4,6)),16);  
		//自定义cell颜色  
		HSSFPalette palette = wb.getCustomPalette();  
		//这里的9是索引 :8到64之间循环 
		palette.setColorAtIndex(colorIndex, (byte) r, (byte) g, (byte) b);  
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    style.setFillForegroundColor(colorIndex);  
	}
	
	public static void setColor(HSSFCellStyle style,Short index) {
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    style.setFillForegroundColor(index);  
	}

	public static void setBorderColor(HSSFCellStyle style, short index) {
		style.setBottomBorderColor(index);
		style.setLeftBorderColor(index);
		style.setRightBorderColor(index);
		style.setTopBorderColor(index);
	} 
	
	
}
