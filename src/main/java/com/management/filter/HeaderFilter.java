package com.management.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：解决跨域问题
 * 路径在web.xml修改
 */
public class HeaderFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HeaderFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse response01 = (HttpServletResponse) response;
		response01.setHeader("Access-Control-Allow-Origin", "*");
        response01.setHeader("Access-Control-Allow-Methods", "*");
        response01.setHeader("Access-Control-Max-Age", "3600");
        response01.addHeader("Access-Control-Allow-Headers", "*");
        response01.setHeader("Access-Control-Allow-Credentials", "*");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
