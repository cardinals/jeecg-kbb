package org.jeecgframework.web.excel.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IExcelOfferService {

	HSSFWorkbook getWorkbook(String id) throws Exception;
}
