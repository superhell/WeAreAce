/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dan
 */
public class AccessControlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        int userLevel = (Integer) req.getSession().getAttribute("Userlvl");
        String url = req.getRequestURI();

        System.err.println("isLogin: " + userLevel);

        if (url.contains("javax.faces.resource")) {
            chain.doFilter(request, response);
        }
        //check first time login or logged out
        if (url.contains("loginPage.xhtml")) {
            chain.doFilter(request, response);
        }        
        if (userLevel == 0) {
            chain.doFilter(request, response);

        } else if (userLevel == 1 && (url.contains("/Factory/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);

        } else if (userLevel == 2 && (url.contains("/Store/") || url.contains("WorkPlace.xhtml"))) {

            chain.doFilter(request, response);
        } else if (userLevel == 3 && (url.contains("/SCM/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);
        } else if (userLevel == 4 && (url.contains("/MRP/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);
        } else if (userLevel == 5 && (url.contains("/Kitchen/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);
        } else if (userLevel == 6 && (url.contains("/Market/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);
        } else if (userLevel == 7 && (url.contains("/ticket/") || url.contains("WorkPlace.xhtml"))) {
            chain.doFilter(request, response);
        } else {

            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You don't have access right!");

        }
    }

    @Override
    public void destroy() {

    }

}