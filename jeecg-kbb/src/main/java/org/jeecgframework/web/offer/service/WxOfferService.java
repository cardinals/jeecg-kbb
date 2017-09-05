package org.jeecgframework.web.offer.service;

import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.web.offer.entity.WxOffer;

public interface WxOfferService {



	MiniDaoPage<WxOffer> getAll(WxOffer act, int page, int rows);

	boolean isEnableViewProject();

}
