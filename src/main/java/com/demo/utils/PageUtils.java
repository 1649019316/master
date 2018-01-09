package com.demo.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;
public class PageUtils {
	
	/*
	 * 常规的分页：起始页赋值
	 */
	public static void setPageParameter(HttpServletRequest request, Map<String, Object> map) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Integer intPage = Integer.parseInt((page == null || page == "0") ? "1": page); // 当前页
		Integer number = Integer.parseInt((rows == null || rows == "0") ? "50": rows); // 每页显示条数
		Integer start = (intPage - 1) * number + 1; // 每页的开始记录 第一页为1 第二页为number+1
		map.put("pagestart", start);
		map.put("pageend", number * intPage);
	}
	
	/*
	 * 结合分页工具PageHelper使用 begin
	 */
	public static void startPage(HttpServletRequest request, Map<String, Object> map) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Integer intPage = Integer.parseInt((page == null || page == "0") ? "1": page); // 当前页
		Integer number = Integer.parseInt((rows == null || rows == "0") ? "50": rows); // 每页显示条数
		PageHelper.startPage(intPage, number);
	}
	//结合easyui返回map对象
	public static <T> Map<String,Object> getEasyMap(List<T> list) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		resultMap.put("total", pageInfo.getTotal());
		resultMap.put("rows", pageInfo.getList());
		resultMap.put("page", pageInfo.getPageNum());
		resultMap.put("totalPages", pageInfo.getPages());
		return resultMap;
	}
	/*
	 * 结合分页工具PageHelper使用 end
	 */
	
	
	/*
	 * 封装返回的list-》json
	 */
	public static JSONObject getPageObjJson(int count,List<Object> list) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", count);// total键 存放总记录数，必须的
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		return JSONObject.fromObject(jsonMap);// 格式化result 一定要是JSONObject
	}
	/*
	 * 一般的分页之后赋值给map返回对象
	 */
	public static void setPageMap(Map<String, Object> resultMap, Map<String, Object> map,
			List<?> list, int size) {
		if (map.containsKey("page") && map.containsKey("rows")) {
			String page = (String) map.get("page");
			String rows = (String) map.get("rows");
			Integer intPage = Integer.parseInt((StringUtils.isEmpty(page) || page == "0") ? "1" : page); // 当前页
			Integer number = Integer.parseInt((StringUtils.isEmpty(rows) || rows == "0") ? "50" : rows); // 每页显示条数
			if(number>50){
				number = 50;
			}
			Integer totalPages = size == 0 ? 0 : size % number == 0 ? size / number : size / number + 1; // +1
			resultMap.put("totalPages", totalPages);
			resultMap.put("currentPageNumber", intPage);
			resultMap.put("pageSize", number);
			resultMap.put("totalRecords", size);
			resultMap.put("records", list);
		}
	}
}
