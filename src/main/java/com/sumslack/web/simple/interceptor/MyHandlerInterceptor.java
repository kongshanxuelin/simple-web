package com.sumslack.web.simple.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sumslack.web.simple.config.BusinessConfig;

public class MyHandlerInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView mv)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		initConfig(req);
		return true;
	}
	
	private void initConfig(HttpServletRequest req) {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());
		BusinessConfig businessConfig = factory.getBean(BusinessConfig.class);
		if(businessConfig!=null) {
			req.setAttribute(KEY_APPNAME, businessConfig.getAppName());
		}
	}
	
	public static final String KEY_APPNAME = "appName";

}
