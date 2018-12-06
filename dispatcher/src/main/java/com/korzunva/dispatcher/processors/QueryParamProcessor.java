package com.korzunva.dispatcher.processors;

import javax.servlet.http.HttpServletRequest;

/**
 * QueryParamProcessor analysis
 * {@link com.korzunva.dispatcher.annotation.QueryParam}
 * annotation
 */
public class QueryParamProcessor {

    /**Returns query param taken from request by its name
     * @param request HttpServletRequest
     * @param paramName name of the query param*/
    static String getValue(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName);
    }

}
