package org.jeecgframework.web.excel.service;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IExcelOfferService {

	

	String getWorkbook(String parameter, HSSFWorkbook workbook) throws Exception;
}
