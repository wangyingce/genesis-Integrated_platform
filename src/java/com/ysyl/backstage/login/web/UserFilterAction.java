package com.ysyl.backstage.login.web;
import javax.servlet.*;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;  

public class UserFilterAction implements Filter {  
  
	//backstage-后台管理系统
	//warehouse-仓库系统
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {  
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String usercode = (String) req.getSession().getAttribute("UserCode");  
        String username = (String) req.getSession().getAttribute("UserName"); 
        String rpath = req.getRequestURI();  
        if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("backstage")>-1){//后台管理系统
        	if(usercode==null||"".equals(usercode)||username==null||"".equals(username)){//没有登录  
                if(rpath!="/pages/backstage/login.jsp"&&!"/pages/backstage/login.jsp".equals(rpath)) {//不是登录页面  
                    HttpServletResponse res = (HttpServletResponse) servletResponse;  
                    res.sendRedirect("http://"+req.getHeader("Host")+"/pages/backstage/login.jsp"); //回到登录页面
                }else{
                	filterChain.doFilter(servletRequest,servletResponse);
                }
            }else {
            	if(rpath=="/pages/backstage/login.jsp"||"/pages/backstage/login.jsp".equals(rpath)) {//如果登录了不允许回到登录页面
            		HttpServletResponse res = (HttpServletResponse) servletResponse;  
                    res.sendRedirect("http://"+req.getHeader("Host")+"/pages/backstage/index.jsp"); //回到登录页面
            	}
                filterChain.doFilter(servletRequest,servletResponse);  
            }  
        }else if(rpath!=null&&!"".equals(rpath)&&rpath.indexOf("warehouse")>-1){//仓库系统
        	if(usercode==null||"".equals(usercode)||username==null||"".equals(username)){//没有登录  
                if (rpath!="/pages/warehouse/login.html"&&!"/pages/warehouse/login.html".equals(rpath)){
                	HttpServletResponse res = (HttpServletResponse) servletResponse;  
                    res.sendRedirect("http://"+req.getHeader("Host")+"/pages/warehouse/login.html"); //回到登录页面
                }else{
                	filterChain.doFilter(servletRequest,servletResponse);
                }
            }else {
            	if(rpath=="/pages/warehouse/login.html"||"/pages/warehouse/login.html".equals(rpath)){
            		HttpServletResponse res = (HttpServletResponse) servletResponse;  
                    res.sendRedirect("http://"+req.getHeader("Host")+"/pages/warehouse/index.html"); //回到登录页面
            	}
                filterChain.doFilter(servletRequest,servletResponse);  
            }  
        }else{
        	filterChain.doFilter(servletRequest,servletResponse);
        }
        
    }  
  
    public void destroy() {  
  
    }
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}  
}  