package com.ysyl.file.service.facade;

import java.util.List;
import java.util.Map;

import com.ysyl.common.schema.model.YyImageFile;



public interface FileService{
	
	/**
	 * 保存服务
	 */
	public YyImageFile saveFile(YyImageFile yyImageFile) throws Exception;
	
	/**
	 * 保存服务并且发送消息
	 */
	public void saveFileAndSendWxMessage(List<YyImageFile> yyImageFiles,Map<String,String> sendMessageMap) throws Exception;

	/**
	 * 保存后台系统照片并返回存储信息
	 * @param yyImageFiles
	 * @return
	 */
	public String saveBackstageFiles(List<YyImageFile> yyImageFiles);

	/**
	 * 主键查询上传日志表
	 * @param brandPicture
	 * @return
	 */
	public YyImageFile findImageFileById(String brandPicture);
}
