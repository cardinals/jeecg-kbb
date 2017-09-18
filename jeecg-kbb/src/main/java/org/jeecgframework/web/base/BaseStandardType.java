package org.jeecgframework.web.base;

import java.util.HashMap;
import java.util.Map;

public enum BaseStandardType {
	/**
	 * 旋转门标准配件
	 **/
	sdtype1	,
	/**
	 * 旋转门可选配件
	 **/
	sdtype2	,
	/**
	 * 平滑门标准配件
	 **/
	sdtype3	,
	/**
	 * 平滑门可选配件
	 **/
	sdtype4	,
	/**
	 * 边门旋转门可选配件
	 **/
	sdtype5	,
	/**
	 * 维保费用
	 **/
	sdtype6	,
	/**
	 * 运输费用
	 **/
	sdtype7	,
	/**
	 * 安装费用
	 **/
	sdtype8	,
	/**
	 * 其他
	 **/
	sdtype9	;		
  
    private static final Map<String, BaseStandardType> stringToEnum = new HashMap<String, BaseStandardType>();
    static {	       
        for(BaseStandardType blah : values()) {
            stringToEnum.put(blah.toString(), blah);
        }
    }
    
   
    public static BaseStandardType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
