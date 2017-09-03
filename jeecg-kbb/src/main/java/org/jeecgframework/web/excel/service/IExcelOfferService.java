package org.jeecgframework.web.excel.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IExcelOfferService {

	String getWorkbook(String id,HSSFWorkbook workbook ) throws Exception;
}
