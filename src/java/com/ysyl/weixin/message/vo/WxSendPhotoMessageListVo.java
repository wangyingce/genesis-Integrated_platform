package com.ysyl.weixin.message.vo;

import java.util.List;

public class WxSendPhotoMessageListVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<WxSendPhotoMessageListDetailVo> articles;

	public List<WxSendPhotoMessageListDetailVo> getArticles() {
		return articles;
	}

	public void setArticles(List<WxSendPhotoMessageListDetailVo> articles) {
		this.articles = articles;
	}


}
