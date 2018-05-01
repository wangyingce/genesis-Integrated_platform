package com.ysyl.file;

/**
 * <p>Title: 后台服务</p>
 *
 * <p>Description: 为客户端提供上传及文件传输状态查询服务</p>
 *
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.file.service.facade.FileService;


@SuppressWarnings("serial")
public class UpLoadFileServlet extends HttpServlet{
    public static final String UPLOAD_DIR = "/upload";
    public static final String DEFAULT_UPLOAD_FAILURE_URL = "FileUpLoad.jsp";
    private FileService fileService;
    private LoginService loginService;
    
    public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();   
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
		this.fileService = (FileService) ctx.getBean("fileService");
	}

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        doPost(request, response);
    }

    /**
     * 从文件路径中取出文件名
     */
    private String takeOutFileName(String filePath) {
        int pos = filePath.lastIndexOf(File.separator);
        if (pos > 0) {
            return filePath.substring(pos + 1);
        } else {
            return filePath;
        }
    }

    /**
     * 从request中取出FileUploadStatus Bean
     */
    public static FileUploadStatus getStatusBean(HttpServletRequest request) {
        BeanControler beanCtrl = BeanControler.getInstance();
        return beanCtrl.getUploadStatus(request.getRemoteAddr());
    }

    /**
     * 把FileUploadStatus Bean保存到类控制器BeanControler
     */
    public static void saveStatusBean( HttpServletRequest request,FileUploadStatus statusBean) {
        statusBean.setUploadAddr(request.getRemoteAddr());
        BeanControler beanCtrl = BeanControler.getInstance();
        beanCtrl.setUploadStatus(statusBean);
    }

    /**
     * 删除已经上传的文件
     */
    private void deleteUploadedFile(HttpServletRequest request) {
        FileUploadStatus satusBean = getStatusBean(request);
        for (int i = 0; i < satusBean.getUploadFileUrlList().size(); i++) {
            File uploadedFile = new File(request.getRealPath(UPLOAD_DIR) + File.separator +satusBean.getUploadFileUrlList().get(i));
            uploadedFile.delete();
        }
        satusBean.getUploadFileUrlList().clear();
        satusBean.setStatus("删除已上传的文件");
        saveStatusBean(request, satusBean);
    }

    /**
     * 上传过程中出错处理
     */
    private void uploadExceptionHandle(HttpServletRequest request,String errMsg) throws ServletException, IOException {
        //首先删除已经上传的文件
        deleteUploadedFile(request);
        FileUploadStatus satusBean = getStatusBean(request);
        satusBean.setStatus(errMsg);
        saveStatusBean(request, satusBean);
    }

    /**
     * 初始化文件上传状态Bean
     */
    private FileUploadStatus initStatusBean(HttpServletRequest request) {
        FileUploadStatus satusBean = new FileUploadStatus();
        satusBean.setStatus("正在准备处理");
        satusBean.setUploadTotalSize(request.getContentLength());
        satusBean.setProcessStartTime(System.currentTimeMillis());
        satusBean.setBaseDir(request.getContextPath() + UPLOAD_DIR);
        return satusBean;
    }

    /**
     * 处理文件上传
     */
    private void processFileUpload(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	/**后台系统上传照片添加区别处理 start*/
    	String remark = "";
    	String phone = "";
    	String ower = "";
    	String rpath = request.getRequestURI();
    	String ids = "";
    	if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("backstage")>-1){
    		ServletContext servletContext = this.getServletContext();   
    		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
    		this.loginService = (LoginService) ctx.getBean("loginService");
    		String usercode = (String) request.getSession().getAttribute("UserCode");
    		String username = (String) request.getSession().getAttribute("UserName");
    		if(usercode ==null||"".equals(usercode)||username==null||"".equals(username)){
    			try {
					throw new Exception("用户未登录，请登录");
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    		YyUser userDto  = loginService.findUserByNameAndCode(username,usercode);
    		phone = userDto.getPhone();
    		ower = userDto.getOwner();
    		remark = "backstage";
    	}
    	if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("warehouse")>-1){
    		ServletContext servletContext = this.getServletContext();   
    		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
    		this.loginService = (LoginService) ctx.getBean("loginService");
    		String usercode = (String) request.getSession().getAttribute("UserCode");
    		String username = (String) request.getSession().getAttribute("UserName");
    		if(usercode ==null||"".equals(usercode)||username==null||"".equals(username)){
    			try {
					throw new Exception("用户未登录，请登录");
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    		YyUser userDto  = loginService.findUserByNameAndCode(username,usercode);
    		phone = userDto.getPhone();
    		ower = userDto.getOwner();
    		remark = "warehouse";
    	}
    	/**后台系统上传照片添加区别处理 end*/
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	//设置内存缓冲区，超过后写入临时文件
    	factory.setSizeThreshold(10240000);
    	//设置临时文件存储位置
    	factory.setRepository(new File(request.getRealPath("/upload/temp")));
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	upload.setHeaderEncoding("UTF-8");
    	//设置单个文件的最大上传值 2M
    	upload.setFileSizeMax(1024*1024*2);
    	//设置整个request的最大值10M
    	upload.setSizeMax(1024*1024*10);
    	upload.setProgressListener(new FileUploadListener(request));
    	//保存初始化后的FileUploadStatus Bean
    	saveStatusBean(request, initStatusBean(request));
    	FileUploadStatus satusBean = getStatusBean(request);
    	String forwardURL = "";
    	int flag=1;
    	String returnStr = "<font size=2><b>文件上传成功!</b></font>";
    	try {
    		List items = upload.parseRequest(request);
    		//获得返回url
    		for (int i = 0; i < items.size(); i++) {
    			FileItem item = (FileItem) items.get(i);
    			if (item.isFormField()) {
    				forwardURL = item.getString();
    				break;
    			}
    		}
    		Map<String,String> sendMessageMap = new HashMap<String,String>();
    		List<YyImageFile> yyImageFiles = new ArrayList<YyImageFile>();
    		//处理文件上传
    		for (int i = 0; i < items.size(); i++) {
    			FileItem item = (FileItem) items.get(i);
    			//取消上传
    			if (getStatusBean(request).getCancel()) {
    				deleteUploadedFile(request);
    				break;
    			}
    			//保存文件
    			else if (!item.isFormField() && item.getName().length() > 0) {
    				SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
    				String nowDate = df.format(new Date());
    				String fileName = takeOutFileName(item.getName());
    				String fileUrl = request.getRealPath(UPLOAD_DIR) + File.separator + nowDate;
    				//先判断文件夹是否存在
    				File file =new File(fileUrl);
    				if(!file .exists()  && !file .isDirectory()){       
    					System.out.println(fileUrl+"目录不存在");  
    					file.mkdir();    
    				} else{
    					System.out.println(fileUrl+"目录存在");  
    				}
    				String filePath = fileUrl + File.separator + fileName;
    				File uploadedFile = new File(filePath);
    				item.write(uploadedFile);
    				
    				//保存文件数据进数据库
    				YyImageFile yyImageFile = new YyImageFile();
    				yyImageFile.setFileName(fileName);

    				yyImageFile.setFilepath(UPLOAD_DIR + "/"+nowDate+"/" + fileName);
    				yyImageFile.setValidStatus("1");
    				//截串
    				String spstr[] = fileName.split("-");
    				if(remark=="backstage"||"backstage".equals(remark)){
        				yyImageFile.setFileIndex(remark +"_"+fileName);	
    					yyImageFile.setRemark(remark);
        				yyImageFile.setOwner(ower);
        				yyImageFile.setPhone(phone);
    				}else if(remark=="warehouse"||"warehouse".equals(remark)){
    					yyImageFile.setFileIndex(remark +"_"+fileName);	
    					yyImageFile.setRemark(remark);
        				yyImageFile.setOwner(ower);
        				yyImageFile.setPhone(phone);
    				}else{
    					yyImageFile.setFileIndex(spstr[1]);	
        				yyImageFile.setRemark(remark);
        				yyImageFile.setOwner("20160001");
        				yyImageFile.setPhone(spstr[0]);
    				}
    				yyImageFiles.add(yyImageFile);
    				//fileService.saveFile(yyImageFile);
    				sendMessageMap.put(spstr[0], spstr[0]);
    				flag++;
    				//更新上传文件列表
    				satusBean.getUploadFileUrlList().add(fileName);
    				saveStatusBean(request, satusBean);
    				//Thread.sleep(500);
    			}
    		}
    		if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("backstage")>-1){
    			ids = fileService.saveBackstageFiles(yyImageFiles);    			
    		}else if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("warehouse")>-1){
    			ids = fileService.saveBackstageFiles(yyImageFiles);    	
    		}else{
    			fileService.saveFileAndSendWxMessage(yyImageFiles, sendMessageMap);
    		}
    		
    		
    	} catch (FileUploadException e) {
    		e.printStackTrace();
    		uploadExceptionHandle(request, "上传文件时发生错误:" + e.getMessage());
    		returnStr = "上传文件时发生错误，请检查是否上传文件超出限制";
    		//添加json类型的返回
    		Gson g = new Gson();
    		String json = "";
    		json = g.toJson(returnStr);
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(json);
    	} catch (Exception e) {
    		e.printStackTrace();
    		uploadExceptionHandle(request, "保存上传文件时发生错误:" + e.getMessage());
    		returnStr = "保存上传文件时发生错误，请联系系统管理人员";
    		//添加json卡类型的返回
    		Gson g = new Gson();
    		String json = "";
    		json = g.toJson(returnStr);
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(json);
    	}
    	if (forwardURL.length() == 0) {
    		forwardURL = DEFAULT_UPLOAD_FAILURE_URL;
    	}
    	if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("backstage")>-1){
    		Gson g = new Gson();
    		String json = "";
    		json = g.toJson(ids);
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(json);
    	}else if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("warehouse")>-1){
    		Gson g = new Gson();
    		String json = "";
    		json = g.toJson(ids);
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(json);
    	}else{
    		request.setAttribute("msg", returnStr);
        	request.getRequestDispatcher("/pages/upload/FileUpLoad.jsp").forward(request, response);
    	}
    	
    }

    /**
     * 回应上传状态查询
     */
    private void responseStatusQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        FileUploadStatus satusBean = getStatusBean(request);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(satusBean.toJSon());
    }

    /**
     * 处理取消文件上传
     */
    private void processCancelFileUpload(HttpServletRequest request,
                                         HttpServletResponse response) throws
            IOException {
        FileUploadStatus satusBean = getStatusBean(request);
        satusBean.setCancel(true);
        saveStatusBean(request, satusBean);
        responseStatusQuery(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            processFileUpload(request, response);
        } else {
            request.setCharacterEncoding("utf-8");

            if (request.getParameter("uploadStatus") != null) {
                responseStatusQuery(request, response);
            }
            if (request.getParameter("cancelUpload") != null) {
                processCancelFileUpload(request, response);
            }

        }
    }
}
