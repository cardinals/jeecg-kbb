package org.jeecgframework.web.excel.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.excel.entity.*;
import org.jeecgframework.web.excel.service.IExcelOfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Service("excelOfferService")
@Transactional
public class ExcelOfferServiceImpl extends CommonServiceImpl  implements IExcelOfferService {
	
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	ExcelOfferEntity bill;
	String billId;
	Integer rowIndex;
	
	@Override
	public HSSFWorkbook getWorkbook(String id) throws Exception{
		billId=id;
		Init();
		buildTitle();
		buildEmptyRow();
		buildMainDoor();
		buildEmptyRow();
		buildMainDoor();
		buildEmptyRow();
		return workbook;
	}

	void Init() throws Exception{
		rowIndex=0;
		InitData();
		InitWorkbook();
	}

	void InitData() throws Exception{
		bill=new ExcelOfferEntity();
		Map<String,Object> map=this.commonDao.findOneForJdbc(
		"select fbillno,famount,fprojectid,fapplicant,fapplicant_date,fdiscountrate,fafteramount ,t1.fname as fcust "
		+" from t_offers t0 inner join t_base_customer t1 on t0.fcustid=t1.id where t0.id=?", billId);
		if(map==null){
			throw new Exception("单据未找到");
		}
		bill.setBillno(map.get("fbillno").toString());
		bill.setCreatedate(readDate(map.get("fapplicate_date")));
		bill.setCustname(map.get("fcust").toString());
		bill.setProjectname(map.get("fprojectid").toString());
		bill.setSaleman(map.get("fapplicant").toString());
		bill.setTotalamount(Double.parseDouble(map.get("famount").toString()));
		bill.setEngineer("");
		bill.setDiscountrate(0.00);
		bill.setAfteramount(0.00);
		
		List<Map<String,Object>> mapList=this.commonDao.findForJdbc("select * from t_offers_entry where id=?",billId);
		List<ExcelOfferEntry> entryList=new ArrayList<ExcelOfferEntry>();
		Iterator<Map<String,Object>>  m =mapList.iterator();
		while (m.hasNext()) {  
			 Map<String,Object> dr = m.next();  
			 ExcelOfferEntry entry=new ExcelOfferEntry();
			 if(dr.get("doortype").toString().equals("XZM")){
				 entry.setOffergroup(OfferGroup.REVOLUTION);
			 }else{
				 entry.setOffergroup(OfferGroup.SMOOTH);
			 }
			 JSONObject obj = new JSONObject().fromObject(dr.get("detail2json"));
			 //门型
			 String doorModelId=obj.get("p1").toString();
			 JSONArray standardArray=JSONArray.fromObject(obj.getString("p2"));
			 JSONArray optionsArray=JSONArray.fromObject(obj.getString("p3"));
			 String surface =obj.getString("p4").toString();
			 Integer i=0;
			 for(i=0;i<standardArray.size();i++){
				 
			 }
		}
	}
	void InitWorkbook(){
		workbook=new HSSFWorkbook();
		sheet = workbook.createSheet();
		// 设置列宽   
	    sheet.setColumnWidth(0, 3500);   
	    sheet.setColumnWidth(1, 1500);   
	    sheet.setColumnWidth(2, 1000);   
	    sheet.setColumnWidth(3, 3500);   
	    sheet.setColumnWidth(4, 4000);   
	    sheet.setColumnWidth(5, 3500);   
	    sheet.setColumnWidth(6, 3500);   
	    sheet.setColumnWidth(7, 4000);   
	    sheet.setColumnWidth(8, 4500);   
	    sheet.setColumnWidth(9, 4500);   
	    // Sheet样式   
	    HSSFCellStyle sheetStyle = workbook.createCellStyle();   
	    // 背景色的设定   
	    sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);   
	    // 前景色的设定   
	    sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);   
	    // 填充模式   
	    sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);   
	    // 设置列的样式   
	    for (int i = 0; i <= 14; i++) {   
	      sheet.setDefaultColumnStyle((short) i, sheetStyle);   
	    }
	}
	
	HSSFCellStyle getTitleStyle(){
		 // 设置字体   
	    HSSFFont headfont = workbook.createFont();   
	    headfont.setFontName("黑体");   
	    headfont.setFontHeightInPoints((short) 22);// 字体大小   
	    headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
	    // 另一个样式   
	    HSSFCellStyle headstyle = workbook.createCellStyle();   
	    headstyle.setFont(headfont);   
	    headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
	    headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    headstyle.setLocked(true);   
	    headstyle.setWrapText(true);// 自动换行   
	    return headstyle;
	}
	HSSFCellStyle getColumnHeadStyle(){
		 // 另一个字体样式   
	    HSSFFont columnHeadFont = workbook.createFont();   
	    columnHeadFont.setFontName("宋体");   
	    columnHeadFont.setFontHeightInPoints((short) 10);   
	    columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
	    // 列头的样式   
	    HSSFCellStyle columnHeadStyle = workbook.createCellStyle();   
	    columnHeadStyle.setFont(columnHeadFont);   
	    columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
	    columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    columnHeadStyle.setLocked(true);   
	    columnHeadStyle.setWrapText(true);   
	    columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色   
	    columnHeadStyle.setBorderLeft((short) 1);// 边框的大小   
	    columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色   
	    columnHeadStyle.setBorderRight((short) 1);// 边框的大小   
	    columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
	    columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色   
	    // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）   
	    columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);   
		return columnHeadStyle;
	}
	HSSFCellStyle getNormalStyle(){
		HSSFFont font = workbook.createFont();   
	    font.setFontName("宋体");   
	    font.setFontHeightInPoints((short) 10);   
	    // 普通单元格样式   
	    HSSFCellStyle style = workbook.createCellStyle();   
	    style.setFont(font);   
	    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中   
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    style.setWrapText(true);   
	    style.setLeftBorderColor(HSSFColor.BLACK.index);   
	    style.setBorderLeft((short) 1);   
	    style.setRightBorderColor(HSSFColor.BLACK.index);   
	    style.setBorderRight((short) 1);   
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
	    style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．   
	    style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
	    return style;
	}
	HSSFCellStyle getCenterStyle(){
		HSSFFont font = workbook.createFont();   
	    font.setFontName("宋体");   
	    font.setFontHeightInPoints((short) 10);   
		 // 另一个样式   
	    HSSFCellStyle centerstyle = workbook.createCellStyle();   
	    centerstyle.setFont(font);   
	    centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
	    centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    centerstyle.setWrapText(true);   
	    centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);   
	    centerstyle.setBorderLeft((short) 1);   
	    centerstyle.setRightBorderColor(HSSFColor.BLACK.index);   
	    centerstyle.setBorderRight((short) 1);   
	    centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
	    centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．   
	    centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．   
	    return centerstyle;
	}
	HSSFCellStyle getAmountStyle(){
		HSSFFont font = workbook.createFont();   
	    font.setFontName("宋体");   
	    font.setFontHeightInPoints((short) 10);   
		 // 另一个样式   
	    HSSFCellStyle style = workbook.createCellStyle();   
	    style.setFont(font);   
	    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居中   
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    style.setWrapText(true);   
	    style.setLeftBorderColor(HSSFColor.BLACK.index);   
	    style.setBorderLeft((short) 1);   
	    style.setRightBorderColor(HSSFColor.BLACK.index);   
	    style.setBorderRight((short) 1);   
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
	    style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．   
	    style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．	    
	    HSSFDataFormat format= workbook.createDataFormat();  
	    style.setDataFormat(format.getFormat("#,##0.00"));         
	    return style;
	}
	String readDate(Object obj){ 
		if(obj==null){
			return "";
		}else{
		    SimpleDateFormat formatter; 
		    formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		    String ctime = formatter.format(obj); 
		    return ctime; 
		}
	} 
	void buildTitle(){
		// 创建第一行   
	      HSSFRow row0 = sheet.createRow(rowIndex);   
	      // 设置行高   
	      row0.setHeight((short) 900);
	      setCellValue(row0,0,getTitleStyle(),"报价单",new Integer[]{0,0,0,9});
	      HSSFRow row1 = sheet.createRow(++rowIndex); 
	      setCellValue(row1,0,getNormalStyle(),"单据编号：",null);
	      setCellValue(row1,1,getNormalStyle(),bill.getBillno(),new Integer[]{rowIndex,rowIndex,1,4});
	      setCellValue(row1,5,getNormalStyle(),"客户名称：",new Integer[]{rowIndex,rowIndex,5,6});
	      setCellValue(row1,7,getNormalStyle(),bill.getCustname(),new Integer[]{rowIndex,rowIndex,7,9});
	      HSSFRow row2 = sheet.createRow(++rowIndex); 
	      setCellValue(row2,0,getNormalStyle(),"项目名称：",null);
	      setCellValue(row2,1,getNormalStyle(),bill.getProjectname(),new Integer[]{rowIndex,rowIndex,1,4});
	      setCellValue(row2,5,getNormalStyle(),"项目总额：",new Integer[]{rowIndex,rowIndex,5,6});
	      setCellValue(row2,7,getAmountStyle(),bill.getTotalamount(),new Integer[]{rowIndex,rowIndex,7,8});
	      if(bill.getAfteramount()!=0){
	    	  HSSFRow row3 = sheet.createRow(++rowIndex); 
	    	  setCellValue(row3,0,getNormalStyle(),"折扣：",null);
		      setCellValue(row3,1,getNormalStyle(),bill.getDiscountrate(),new Integer[]{rowIndex,rowIndex,1,4});
		      setCellValue(row3,5,getNormalStyle(),"折后金额：",new Integer[]{rowIndex,rowIndex,5,6});
		      setCellValue(row3,7,getAmountStyle(),bill.getAfteramount(),new Integer[]{rowIndex,rowIndex,7,8});
	      }
	}
	
	void setCellValue(HSSFRow row,Integer colIndex,HSSFCellStyle style,String text,Integer[] rangeAddress){		
		  if(rangeAddress!=null && rangeAddress.length==4){
	    	  CellRangeAddress range=new CellRangeAddress(
	    			  rangeAddress[0],
	    			  rangeAddress[1],
	    			  rangeAddress[2],
	    			  rangeAddress[3]);
		      sheet.addMergedRegion(range);
	      }
	      HSSFCell cell1=row.createCell(colIndex);
	      cell1.setCellValue(new HSSFRichTextString(text));   
	      cell1.setCellStyle(style);   
	    
	}
	void setCellValue(HSSFRow row,Integer colIndex,HSSFCellStyle style,Double amount,Integer[] rangeAddress){	
		 if(rangeAddress!=null && rangeAddress.length==4){
	    	  CellRangeAddress range=new CellRangeAddress(
	    			  rangeAddress[0],
	    			  rangeAddress[1],
	    			  rangeAddress[2],
	    			  rangeAddress[3]);
		      sheet.addMergedRegion(range);
	      }
	      HSSFCell cell1=row.createCell(colIndex);
	      cell1.setCellValue(amount);   
	      cell1.setCellStyle(style);   
	}
	void setCellValue(HSSFRow row,Integer colIndex,HSSFCellStyle style,Integer intValue,Integer[] rangeAddress){	
		 if(rangeAddress!=null && rangeAddress.length==4){
	    	  CellRangeAddress range=new CellRangeAddress(
	    			  rangeAddress[0],
	    			  rangeAddress[1],
	    			  rangeAddress[2],
	    			  rangeAddress[3]);
		      sheet.addMergedRegion(range);
	      }
	      HSSFCell cell1=row.createCell(colIndex);
	      cell1.setCellValue(intValue);   
	      cell1.setCellStyle(style);   
	     
	}

	
	void buildEmptyRow(){
		HSSFRow row = sheet.createRow(++rowIndex);	   
	    row.setHeight((short) 100);
	}
	
	HSSFRow buildColumnHeadStyle(){
		HSSFRow row = sheet.createRow(++rowIndex);	
		setCellValue(row,3,getColumnHeadStyle(),"名称",null);
		setCellValue(row,4,getColumnHeadStyle(),"规格型号",new Integer[]{rowIndex,rowIndex,4,5});
		setCellValue(row,6,getColumnHeadStyle(),"数量",null);
		setCellValue(row,7,getColumnHeadStyle(),"价格",null);
		setCellValue(row,8,getColumnHeadStyle(),"金额",null);
		setCellValue(row,9,getColumnHeadStyle(),"备注",null);
		return row;
	}
	
	void buildMainDoor(){
		HSSFRow rowColumnHead=buildColumnHeadStyle();
		
		HSSFRow row1 = sheet.createRow(++rowIndex);	
		setCellValue(row1,3,getNormalStyle(),"名称",null);
		setCellValue(row1,4,getNormalStyle(),"规格型号",new Integer[]{rowIndex,rowIndex,4,5});
		setCellValue(row1,6,getCenterStyle(),"数量",null);
		setCellValue(row1,7,getAmountStyle(),"价格",null);
		setCellValue(row1,8,getAmountStyle(),"金额",null);
		setCellValue(row1,9,getNormalStyle(),"备注",null);
		//一般参数
		Integer paramStart=++rowIndex;		
		HSSFRow row2 = sheet.createRow(rowIndex);			
//		setCellValue(row2,3,getNormalStyle(),"名称",null);
		setCellValue(row2,4,getColumnHeadStyle(),"参数项",new Integer[]{rowIndex,rowIndex,4,5});
		setCellValue(row2,6,getColumnHeadStyle(),"参数值",new Integer[]{rowIndex,rowIndex,6,9});
		
		HSSFRow row3 = sheet.createRow(++rowIndex);	
		setCellValue(row3,4,getCenterStyle(),"总高",new Integer[]{rowIndex,rowIndex,4,5});
		setCellValue(row3,6,getCenterStyle(),"2200mm",new Integer[]{rowIndex,rowIndex,6,9});
		
		HSSFRow row4 = sheet.createRow(++rowIndex);	
		setCellValue(row4,4,getCenterStyle(),"直径",new Integer[]{rowIndex,rowIndex,4,5});
		setCellValue(row4,6,getCenterStyle(),"2200mm",new Integer[]{rowIndex,rowIndex,6,9});
		
		setCellValue(row2,3,getCenterStyle(),"一般参数",new Integer[]{paramStart,rowIndex,3,3});
		buildEmptyRow();
		buildColumnHeadStyle();
		
		setCellValue(rowColumnHead,1,getColumnHeadStyle(),"A0024",new Integer[]{paramStart-2,rowIndex,1,2});		
		//标准配件
		Integer standardStart=rowIndex;
		for(Integer i=1;i<5;i++){
			HSSFRow row = sheet.createRow(++rowIndex);	
			setCellValue(row,2,getCenterStyle(),i+"",null);//序号
			setCellValue(row,3,getNormalStyle(),i+"",null);//名称
			setCellValue(row,4,getCenterStyle(),i+"",new Integer[]{rowIndex,rowIndex,4,5});//规格型号
			setCellValue(row,6,getCenterStyle(),i,null);//数量
			setCellValue(row,7,getAmountStyle(),i,null);//单价
			setCellValue(row,8,getAmountStyle(),i,null);//总价
			setCellValue(row,9,getNormalStyle(),"",null);//备注
		}
		setCellValue(sheet.getRow(standardStart+1),1,getCenterStyle(),"标准配件",new Integer[]{standardStart+1,rowIndex,1,1});
		//可选配件
		Integer optionsStart=rowIndex;
		for(Integer i=1;i<4;i++){
			HSSFRow row = sheet.createRow(++rowIndex);	
			setCellValue(row,2,getCenterStyle(),i+"",null);//序号
			setCellValue(row,3,getNormalStyle(),i+"",null);//名称
			setCellValue(row,4,getCenterStyle(),i+"",new Integer[]{rowIndex,rowIndex,4,5});//规格型号
			setCellValue(row,6,getCenterStyle(),i,null);//数量
			setCellValue(row,7,getAmountStyle(),i,null);//单价
			setCellValue(row,8,getAmountStyle(),i,null);//总价
			setCellValue(row,9,getNormalStyle(),"",null);//备注
		}
		setCellValue(sheet.getRow(optionsStart+1),1,getCenterStyle(),"可选配件",new Integer[]{optionsStart+1,rowIndex,1,1});
		//表面处理
		HSSFRow row5 = sheet.createRow(++rowIndex);			
		setCellValue(row5,2,getColumnHeadStyle(),"名称",new Integer[]{rowIndex,rowIndex,2,5});//名称
		setCellValue(row5,6,getColumnHeadStyle(),"系数",new Integer[]{rowIndex,rowIndex,6,8});//规格型号
		setCellValue(row5,9,getColumnHeadStyle(),"备注",null);//规格型号
		
		HSSFRow row6 = sheet.createRow(++rowIndex);			
		setCellValue(row6,2,getCenterStyle(),"名称",new Integer[]{rowIndex,rowIndex,2,5});//名称
		setCellValue(row6,6,getAmountStyle(),1.0,new Integer[]{rowIndex,rowIndex,6,8});//规格型号
		setCellValue(row6,9,getNormalStyle(),"备注",null);//规格型号
		
		setCellValue(sheet.getRow(rowIndex-1),1,getCenterStyle(),"表面处理",new Integer[]{rowIndex-1,rowIndex,1,1});
		setCellValue(rowColumnHead,0,getCenterStyle(),"旋转门及其选项",new Integer[]{rowColumnHead.getRowNum(),rowIndex,0,0});
		
	}
}
