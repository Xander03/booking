package com.korzunva.dispatcher.processors;

import javax.servlet.http.HttpServletRequest;

/**
 * PathParamProcessor analysis
 * {@link com.korzunva.dispatcher.annotation.PathParam}
 * annotation
 */
public class PathParamProcessor {

    private static final String PATH_PARAM_PATTERN = "^\\{\\w+}$";

    /**Parses request url and method url and returns
     * path param values taken from request url
     * @param request HttpServletRequest
     * @param methodPath url of the method*/
    static String getValues(HttpServletRequest request, String methodPath, String paramName) {
        String param = null;
        String[] parsedMethodPath = methodPath.split("/");
        String[] parsedUri = request.getRequestURI().split("/");
        for (int i = 0; i < parsedUri.length; i++) {
            if (parsedMethodPath[i].matches(PATH_PARAM_PATTERN)) {
                String pathParamName = parsedMethodPath[i].replaceAll("[{}]", "");
                if (paramName.equalsIgnoreCase(pathParamName))
                param = parsedUri[i];
            }
        }
        return param;
    }

}
