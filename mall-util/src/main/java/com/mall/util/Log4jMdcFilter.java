package com.mall.util;

import org.apache.log4j.MDC;

import javax.servlet.*;
import java.io.IOException;

public class Log4jMdcFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		MDC.put("ip", request.getRemoteAddr());		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
