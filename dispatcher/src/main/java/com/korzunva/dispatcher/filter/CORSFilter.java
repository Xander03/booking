package com.korzunva.dispatcher.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {

    private static final String CORS_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ORIGIN = "*";
    private static final String CORS_METHODS = "Access-Control-Allow-Methods";
    private static final String METHODS = "GET, HEAD, POST, OPTIONS, PUT, DELETE, UPDATE, PATCH";
    private static final String CORS_HEADERS = "Access-Control-Allow-Headers";
    private static final String HEADERS = "Content-Type, Token";
    private static final String OPTIONS = "OPTIONS";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.addHeader(CORS_ORIGIN, ORIGIN);
        resp.addHeader(CORS_METHODS, METHODS);
        resp.addHeader(CORS_HEADERS, HEADERS);

        if (req.getMethod().equalsIgnoreCase(OPTIONS)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
