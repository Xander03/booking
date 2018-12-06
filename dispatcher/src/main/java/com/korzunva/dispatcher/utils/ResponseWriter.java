package com.korzunva.dispatcher.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**ResponseWriter is an util class that contains methods
 * to write response string to HttpServletResponse object*/
public class ResponseWriter {

    /**Writes message to HttpServletResponse object
     * @param response HttpServletResponse object
     * @param message response message
     * @throws IOException if something go wrong when writing to response PrintWriter*/
    public static void write(HttpServletResponse response, String message) throws IOException {
        PrintWriter printWriter = response.getWriter();
        if (printWriter != null) {
            printWriter.write(message);
        }
    }

    /**Sets error HTTP code to HttpServletResponse object
     * and writes response message to it
     * @param response HttpServletResponse object
     * @param message response message
     * @param code HTTP error code
     * @throws IOException if something go wrong when writing to response PrintWriter*/
    public static void error(HttpServletResponse response, String message, Integer code) throws IOException {
        response.setStatus(code);
        write(response, message);
    }

}
