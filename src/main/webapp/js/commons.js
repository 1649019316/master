//数组排重
Array.prototype.unique = function()
{
	if(this==null){
		return this;
	}
	if(this.length==0){
		return this;
	}
	this.sort();
	var re=[this[0]];
	for(var i = 1; i < this.length; i++)
	{
		if( this[i] !== re[re.length-1])
		{
			re.push(this[i]);
		}
	}
	return re;
}
Array.prototype.contains = function(e)
{
	for(i=0;i<this.length;i++)
	{
		if(this[i] == e)
		{
			return true;
		}
	}
return false;
}

var Digit = {};
/**
 * 四舍五入法截取一个小数
 * @param float digit 要格式化的数字
 * @param integer length 要保留的小数位数
 * @return float
 */
Digit.round = function(digit, length) {
	length = length ? parseInt(length) : 0;
	if(length <= 0)
		return Math.round(digit);
	digit = Math.round(digit * Math.pow(10, length)) / Math.pow(10, length);
	return digit;
};
/**
 * 舍去法截取一个小数
 * @param float digit 要格式化的数字
 * @param integer length 要保留的小数位数
 * @return float
 */
Digit.floor = function(digit, length) {
	length = length ? parseInt(length) : 0;
	if(length <= 0)
		return Math.floor(digit);
	digit = Math.floor(digit * Math.pow(10, length)) / Math.pow(10, length);
	return digit;
};
/**
 * 进一法截取一个小数
 * @param float digit 要格式化的数字
 * @param integer length 要保留的小数位数
 * @return float
 */
Digit.ceil = function(digit, length) {
	length = length ? parseInt(length) : 0;
	if(length <= 0)
		return Math.ceil(digit);
	digit = Math.ceil(digit * Math.pow(10, length)) / Math.pow(10, length);
	return digit;
};

//日期操作
DateUtils = {};
DateUtils.simpleParseDate = function(dateStr){
	if(!dateStr){
		return null;
	}
	var str = dateStr.replace(/-/g,'/');
	var time = Date.parse(str);
	var date = new Date();
	date.setTime(time);
	return date;
}
DateUtils.createDate = function(year,month,day){
	var _year =  year ? year : 0;
	var _month =  month ? month-1 : 0 ;
	var _day =  day ? day : 1;
	var date = new Date();
	date.setTime(0);
	date.setFullYear(_year);
	date.setMonth(_month );
	date.setDate(_day);
	return date;
}
DateUtils.createFullDate = function(year,month,day,hour,minute,second,milisecond){
	var _year =  year ? year : 0;
	var _month =  month ? month-1 : 0 ;
	var _day =  day ? day : 1;
	var _hour =  hour ? hour:0;
	var _minute =  minute ? minute : 0;
	var _second =  second ? second : 0;
	var _milisecond =  milisecond ? milisecond : 0;
	var date = new Date();
	date.setTime(0);
	date.setFullYear(_year);
	date.setMonth(_month );
	date.setDate(_day);
	date.setHours(_hour);
	date.setMinutes(_minute)
	date.setSeconds(_second)
	date.setMilliseconds(_milisecond);
	return date;
}
DateUtils.formatDate=function(date){
	var now = "";
	if(!date){
		return now;
	}
	now = date.getFullYear()+"-";
	if(date.getMonth()+1<10){
		now = now + 0+ (date.getMonth()+1)+"-";
	}else{
		now = now + (date.getMonth()+1)+"-";
	} 
	if(date.getDate()<10){
		now = now + 0 + date.getDate()+" ";
	}else{
		now = now + date.getDate()+" ";
	}
	return now;
}
DateUtils.formatFullDate=function(date){
	var now = "";
	now = date.getFullYear()+"-"; 
	now = now + (date.getMonth()+1)+"-";
	now = now + date.getDate()+" ";
	now = now + date.getHours()+":";
	now = now + date.getMinutes()+":";
	now = now + date.getSeconds()+"";
	return now;
}

//检验日期是否正确2013-09-01或者2013/09/01,都可以没有,flag==true表示日期不能为空
 DateUtils._checkDate = function(RQ,flag) {
    var date = RQ;
    if(!flag &&!RQ){
    	return true;
    }else if(flag && !RQ){
    	alert("日期不能为空!");
    	return false;
    }
    var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (result == null){
    	alert("日期错误!");
    	return false;
    }
    var d = new Date(result[1], result[3] - 1, result[4]);
    return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
}
// 日期加减
 DateUtils.addDay = function(date,dadd) {
		var a = new Date(date);
		a = a.valueOf();
		a = a + dadd * 24 * 60 * 60 * 1000;
		a = new Date(a);
	//	alert(a.getFullYear() + "年" + (a.getMonth() + 1) + "月" + a.getDate() + "日");	
		return   a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate() ;
 }
 // 日期差值  仅支持yyyy-MM-dd 格式
 DateUtils.dateDIff=function(d1,d2){
		var day = 24 * 60 * 60 *1000;
		try{    
		   var dateArr = d1.split("-");
		   var checkDate = new Date(d1);
		   checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
		   var checkTime = checkDate.getTime();
		  
		   var dateArr2 = d2.split("-");
		   var checkDate2 = new Date(d2);
		   checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
		   var checkTime2 = checkDate2.getTime();
		    
		   var cha = (checkTime - checkTime2)/day;  
		        return cha;
		    }catch(e){
		   return false;
		}
 }
 // 日期差值  格式fmt
 DateUtils.dateDIffMonth=function(d1,d2){
	 var day = 24 * 60 * 60 *1000;
	 try{    
		 var dateArr = d1.split("-");
		 var checkDate = new Date(d1);
		 checkDate.setFullYear(dateArr[0], dateArr[1]-1, 0);
		 var checkTime = checkDate.getTime();
		 
		 var dateArr2 = d2.split("-");
		 var checkDate2 = new Date(d2);
		 checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1,0);
		 var checkTime2 = checkDate2.getTime();
		 
		 var cha = (checkTime - checkTime2)/day;  
		 return cha;
	 }catch(e){
		 return false;
	 }
 }
 
//计算月份差
 DateUtils.getMonthNumber = function(date1,date2){
 //默认格式为"20030303",根据自己需要改格式和方法
	 date1 = new Date(date1);
	 date2 = new Date(date2);
	 var year1 =  date1.getFullYear();
	 var year2 =  date2.getFullYear();
	 var month1 = date1.getMonth();     
	 var month2 = date2.getMonth();     
	 var len=(year1-year2)*12+(month1-month2);
	 return len;
}
 
var DateGrid = {};
//EasyUI用DataGrid用日期格式化
DateGrid.FullTimeFormatter= function (val) {
	if(!val.year){
		return "";
	}
	var year=parseInt(val.year)+1900;
	var month=(parseInt(val.month)+1);
	month=month>9?month:('0'+month);
	var date=parseInt(val.date);
	date=date>9?date:('0'+date);
	var hours=parseInt(val.hours);
	hours=hours>9?hours:('0'+hours);
	var minutes=parseInt(val.minutes);
	minutes=minutes>9?minutes:('0'+minutes);
	var seconds=parseInt(val.seconds);
	seconds=seconds>9?seconds:('0'+seconds);
	var time=year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
	return time;
}
DateGrid.timeFormatter= function (val) {
	if(!val.year){
		return "";
	}
	var year=parseInt(val.year)+1900;
	var month=(parseInt(val.month)+1);
	month=month>9?month:('0'+month);
	var date=parseInt(val.date);
	date=date>9?date:('0'+date);
	var hours=parseInt(val.hours);
	hours=hours>9?hours:('0'+hours);
	var minutes=parseInt(val.minutes);
	minutes=minutes>9?minutes:('0'+minutes);
	//var seconds=parseInt(val.seconds);
	//seconds=seconds>9?seconds:('0'+seconds);
	var time=year+'-'+month+'-'+date;//+' '+hours+':'+minutes+':'+seconds;
	return time;
}

function getNoRepeatDatas(arr1,arr2,property1,property2){
	if(!property1){
		property1 = "value";
	}
	if(!property2){
		property2 =" value";
	}
	var arr = [];
	if(arr1.length==0){
		return [];
	}
	if(arr2.length==0){
		return arr1;	
	}
	for(var i=0;i<arr2.length;i++){
		arr.push(arr2[i]);
	}
	if(arr1.length!=0){
		for(var i=0;i<arr1.length;i++){
			var o1 = arr1[i];
			var j = 0;
			for(;j<arr2.length;j++){
				if(o1[property1]===arr2[j][property1]){
					break;
				}
			}
			if(j==arr2.length){
				arr.push(o1);
			}
		}
	}
	return arr;
}

//table comon
var TableUtils= {};
TableUtils.selectedTRIndex = -1;
TableUtils.titleLength = 1;
TableUtils.setSelectedRowIndex = function(){//当删除行的按钮在这一行的时候
	TableUtils.selectedTRIndex = -1;
 	var srcObj = event.srcElement;
 	while(srcObj.tagName!="TR" && srcObj.tagName!="TABLE"){
 		srcObj = srcObj.parentNode;
 	}
 	TableUtils.selectedTRIndex = srcObj.rowIndex;
 }

TableUtils.deleteRowByTemplate = function(tableId){
  	var objTable = document.getElementById(tableId);
  	if(objTable.rows.length>(TableUtils.titleLength+1)){//avoid delete template
  		objTable.deleteRow(objTable.rows.length-1);
	  	TableUtils.selectedTRIndex = -1;
  	}
 }
 
TableUtils.insertRowByTemplate = function(tableId,trId){//插入
	var tableObj=document.getElementById(tableId);	
	var trObj=tableObj.insertRow(TableUtils.selectedTRIndex);
	var templateRowObj=document.getElementById(trId);	
	TableUtils.fillRowByTemplateRow(trObj,templateRowObj);
	return trObj;
 }

TableUtils.addRowByTemplate = function(tableId,trId){//行尾增加一行
     var tableObj=document.getElementById(tableId);
	 var rowIndex=tableObj.rows.length;
	var trObj=tableObj.insertRow(rowIndex);
	var templateRowObj=document.getElementById(trId);	
	 TableUtils.fillRowByTemplateRow(trObj,templateRowObj);
	 return trObj;
}

TableUtils.fillRowByTemplateRow = function(trObj,templateRowObj){
	/*
	var template_cell_num=templateRowObj.cells.length;
	var new_cell_num=trObj.cells.length;
	for(var i=0;i<template_cell_num;i++){
		if(template_cell_num>new_cell_num){
			trObj.insertCell(i);
		}
		if(templateRowObj.cells[i].style.display=="none"){
			trObj.cells[i].style.display="none";
		}
		trObj.cells[i].innerHTML=templateRowObj.cells[i].innerHTML;
	}
	*/
	trObj.innerHTML=templateRowObj.innerHTML;
 } 
//日期格式化
Date.prototype.format = function (fmt) {
var o = {
    "M+": this.getMonth() + 1, //月份 
    "d+": this.getDate(), //日 
    "h+": this.getHours(), //小时 
    "m+": this.getMinutes(), //分 
    "s+": this.getSeconds(), //秒 
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    "S": this.getMilliseconds() //毫秒 
};
if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
for (var k in o)
if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
return fmt;
}

//---------------------------------------------------  
//判断闰年  
//---------------------------------------------------  
Date.prototype.isLeapYear = function()   
{   
 return (0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0)));   
}   

//---------------------------------------------------  
//日期格式化  
//格式 YYYY/yyyy/YY/yy 表示年份  
//MM/M 月份  
//W/w 星期  
//dd/DD/d/D 日期  
//hh/HH/h/H 时间  
//mm/m 分钟  
//ss/SS/s/S 秒  
//---------------------------------------------------  
Date.prototype.Format = function(formatStr)   
{   
 var str = formatStr;   
 var Week = ['日','一','二','三','四','五','六'];  

 str=str.replace(/yyyy|YYYY/,this.getFullYear());   
 str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   

 str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());   
 str=str.replace(/M/g,this.getMonth());   

 str=str.replace(/w|W/g,Week[this.getDay()]);   

 str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
 str=str.replace(/d|D/g,this.getDate());   

 str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
 str=str.replace(/h|H/g,this.getHours());   
 str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
 str=str.replace(/m/g,this.getMinutes());   

 str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
 str=str.replace(/s|S/g,this.getSeconds());   

 return str;   
}   

//+---------------------------------------------------  
//| 求两个时间的天数差 日期格式为 YYYY-MM-dd   
//+---------------------------------------------------  
function daysBetween(DateOne,DateTwo)  
{   
 var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
 var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
 var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  

 var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
 var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
 var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  

 var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
 return Math.abs(cha);  
}  


//+---------------------------------------------------  
//| 日期计算  
//+---------------------------------------------------  
Date.prototype.DateAdd = function(strInterval, Number) {   
 var dtTmp = this;  
 switch (strInterval) {   
     case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number));  
     case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number));  
     case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number));  
     case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number));  
     case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));  
     case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());  
     case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());  
     case 'y' :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());  
 }  
}  

//+---------------------------------------------------  
//| 比较日期差 dtEnd 格式为日期型或者 有效日期格式字符串  
//+---------------------------------------------------  
Date.prototype.DateDiff = function(strInterval, dtEnd) {   
 var dtStart = this;  
 if (typeof dtEnd == 'string' )//如果是字符串转换为日期型  
 {   
     dtEnd = StringToDate(dtEnd);  
 }  
 switch (strInterval) {   
     case 's' :return parseInt((dtEnd - dtStart) / 1000);  
     case 'n' :return parseInt((dtEnd - dtStart) / 60000);  
     case 'h' :return parseInt((dtEnd - dtStart) / 3600000);  
     case 'd' :return parseInt((dtEnd - dtStart) / 86400000);  
     case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));  
     case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);  
     case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();  
 }  
}  

//+---------------------------------------------------  
//| 日期输出字符串，重载了系统的toString方法  
//+---------------------------------------------------  
Date.prototype.toString = function(showWeek)  
{   
 var myDate= this;  
 var str = myDate.toLocaleDateString();  
 if (showWeek)  
 {   
     var Week = ['日','一','二','三','四','五','六'];  
     str += ' 星期' + Week[myDate.getDay()];  
 }  
 return str;  
}  

//+---------------------------------------------------  
//| 日期合法性验证  
//| 格式为：YYYY-MM-DD或YYYY/MM/DD  
//+---------------------------------------------------  
function IsValidDate(DateStr)   
{   
 var sDate=DateStr.replace(/(^\s+|\s+$)/g,''); //去两边空格;   
 if(sDate=='') return true;   
 //如果格式满足YYYY-(/)MM-(/)DD或YYYY-(/)M-(/)DD或YYYY-(/)M-(/)D或YYYY-(/)MM-(/)D就替换为''   
 //数据库中，合法日期可以是:YYYY-MM/DD(2003-3/21),数据库会自动转换为YYYY-MM-DD格式   
 var s = sDate.replace(/[\d]{ 4,4 }[\-/]{ 1 }[\d]{ 1,2 }[\-/]{ 1 }[\d]{ 1,2 }/g,'');   
 if (s=='') //说明格式满足YYYY-MM-DD或YYYY-M-DD或YYYY-M-D或YYYY-MM-D   
 {   
     var t=new Date(sDate.replace(/\-/g,'/'));   
     var ar = sDate.split(/[-/:]/);   
     if(ar[0] != t.getYear() || ar[1] != t.getMonth()+1 || ar[2] != t.getDate())   
     {   
         //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');   
         return false;   
     }   
 }   
 else   
 {   
     //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');   
     return false;   
 }   
 return true;   
}   

//+---------------------------------------------------  
//| 日期时间检查  
//| 格式为：YYYY-MM-DD HH:MM:SS  
//+---------------------------------------------------  
function CheckDateTime(str)  
{   
 var reg = /^(\d+)-(\d{ 1,2 })-(\d{ 1,2 }) (\d{ 1,2 }):(\d{ 1,2 }):(\d{ 1,2 })$/;   
 var r = str.match(reg);   
 if(r==null)return false;   
 r[2]=r[2]-1;   
 var d= new Date(r[1],r[2],r[3],r[4],r[5],r[6]);   
 if(d.getFullYear()!=r[1])return false;   
 if(d.getMonth()!=r[2])return false;   
 if(d.getDate()!=r[3])return false;   
 if(d.getHours()!=r[4])return false;   
 if(d.getMinutes()!=r[5])return false;   
 if(d.getSeconds()!=r[6])return false;   
 return true;   
}   

//+---------------------------------------------------  
//| 把日期分割成数组  
//+---------------------------------------------------  
Date.prototype.toArray = function()  
{   
 var myDate = this;  
 var myArray = Array();  
 myArray[0] = myDate.getFullYear();  
 myArray[1] = myDate.getMonth();  
 myArray[2] = myDate.getDate();  
 myArray[3] = myDate.getHours();  
 myArray[4] = myDate.getMinutes();  
 myArray[5] = myDate.getSeconds();  
 return myArray;  
}  

//+---------------------------------------------------  
//| 取得日期数据信息  
//| 参数 interval 表示数据类型  
//| y 年 m月 d日 w星期 ww周 h时 n分 s秒  
//+---------------------------------------------------  
Date.prototype.DatePart = function(interval)  
{   
 var myDate = this;  
 var partStr='';  
 var Week = ['日','一','二','三','四','五','六'];  
 switch (interval)  
 {   
     case 'y' :partStr = myDate.getFullYear();break;  
     case 'm' :partStr = myDate.getMonth()+1;break;  
     case 'd' :partStr = myDate.getDate();break;  
     case 'w' :partStr = Week[myDate.getDay()];break;  
     case 'ww' :partStr = myDate.WeekNumOfYear();break;  
     case 'h' :partStr = myDate.getHours();break;  
     case 'n' :partStr = myDate.getMinutes();break;  
     case 's' :partStr = myDate.getSeconds();break;  
 }  
 return partStr;  
}  

//+---------------------------------------------------  
//| 取得当前日期所在月的最大天数  
//+---------------------------------------------------  
Date.prototype.MaxDayOfDate = function()  
{   
 var myDate = this;  
 var ary = myDate.toArray();  
 var date1 = (new Date(ary[0],ary[1]+1,1));  
 var date2 = date1.dateAdd(1,'m',1);  
 var result = dateDiff(date1.Format('yyyy-MM-dd'),date2.Format('yyyy-MM-dd'));  
 return result;  
}  

//+---------------------------------------------------  
//| 取得当前日期所在周是一年中的第几周  
//+---------------------------------------------------  
Date.prototype.WeekNumOfYear = function()  
{   
 var myDate = this;  
 var ary = myDate.toArray();  
 var year = ary[0];  
 var month = ary[1]+1;  
 var day = ary[2];  
 
 var d1 = new Date(year, month-1, day);
 var d2 = new Date(year, 0, 1);
  var d = Math.round((d1 - d2) / 86400000); 
  
  return Math.ceil((d + ((d2.getDay() + 1) - 1)) / 7); 
  
}  

// String 判断以xx开头
String.prototype.startWith=function(str){     
	  var reg=new RegExp("^"+str);     
	  return reg.test(this);        
}
//+---------------------------------------------------  
//| 字符串转成日期类型   
//| 格式 MM/dd/YYYY MM-dd-YYYY YYYY/MM/dd YYYY-MM-dd  
//+---------------------------------------------------  
function StringToDate(DateStr)  
{   

 var converted = Date.parse(DateStr);  
 var myDate = new Date(converted);  
 if (isNaN(myDate))  
 {   
     //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
     var arys= DateStr.split('-');  
     myDate = new Date(arys[0],--arys[1],arys[2]);  
 }  
 return myDate;  
}  

function fixWidth(percent)     
{     
	 var width = document.body.clientWidth;
    return (width - 5) * percent;      
} 

/**
 * 身份证号码判断
*/
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 
    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  } 

  /**  
   * 验证手机号码是否有效  
   * @param linkPhone   
   * @return  
   */  
	function mobilePhoneNumValidate(linkPhone){
		var reg=new RegExp("^[1][3458][0-9]{9}$");     
		return reg.test(linkPhone); 
	}
	

  /**  
   * 验证电话号码是否有效  
   * @param linkPhone   
   * @return  
   */  
	function telPhoneNumValidate(linkPhone){
		var reg=new RegExp("^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$");     
		return reg.test(linkPhone); 
	}
	
	
  
	//去掉字符串头尾空格   
	function trim(str) {   
	    return str.replace(/(^\s*)|(\s*$)/g, "");   
	}
		String.prototype.endWith=function(str){     
		  var reg=new RegExp(str+"$");     
		  return reg.test(this);        
		}