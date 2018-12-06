package com.korzunva.dispatcher.processors;

import com.korzunva.dispatcher.utils.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Parameter;

/**
 * RequestBodyProcessor analysis
 * {@link com.korzunva.dispatcher.annotation.RequestBody}
 * annotation
 */
public class RequestBodyProcessor {

    /**Returns request body parsed in parameter class object
     * @param request HttpServletRequest
     * @param parameter method parameter*/
    static Object getValue(HttpServletRequest request, Parameter parameter) throws IOException {
        Object requestBody = null;
        if (parameter != null) {
            String json = getRequestBody(request);
            Class<?> requestBodyType = parameter.getType();
            requestBody = JsonParser.jsonToObject(requestBodyType, json);
        }
        return requestBody;
    }

    private static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder body = new StringBuilder();
        String line;
        BufferedReader br = request.getReader();
        while ((line = br.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }

}
