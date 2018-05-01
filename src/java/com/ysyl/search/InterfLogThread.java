package com.ysyl.search;

import com.ysyl.search.service.facade.SearchService;
import com.ysyl.search.vo.SearchInterfMesaageVo;
public class InterfLogThread extends Thread {
	private SearchService searchService;
	private SearchInterfMesaageVo vo;


	public InterfLogThread(SearchInterfMesaageVo vo,SearchService searchService) {
		this.vo=vo;
		this.searchService = searchService;
	}


	public void run() {
		searchService.saveInterfMessage(vo);
	}
}
