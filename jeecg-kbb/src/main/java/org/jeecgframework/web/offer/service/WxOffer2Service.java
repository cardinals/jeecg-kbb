package org.jeecgframework.web.offer.service;

import java.util.List;

import org.jeecgframework.web.offer.entity.WxOffer2Entry;

public interface WxOffer2Service {
	List<WxOffer2Entry> getOffer2Entry(String offerId,String type);
}
