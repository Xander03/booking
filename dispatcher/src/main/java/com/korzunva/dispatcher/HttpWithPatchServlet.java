package com.korzunva.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpWithPatchServlet extends HttpServlet {

    private static final String PATCH = "PATCH";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase(PATCH)) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    protected abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
