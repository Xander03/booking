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
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@WebFilter("/*")
public class FilterLocale implements Filter {

    private static final String LANGUAGE = "language";
    private static final String DEFAULT_LANGUAGE = "en";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Map<String, String> params = getQueryMap(req.getQueryString());
        String language;
        if (params != null) {
            language = params.get(LANGUAGE);
        } else {
            language = DEFAULT_LANGUAGE;
        }
        Locale.setDefault(new Locale(language));

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    private Map<String, String> getQueryMap(String query)
    {
        if (query == null ||  "".equals(query)) {
            return null;
        }
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        Arrays.stream(params).forEach(param -> {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        });
        return map;
    }

}
