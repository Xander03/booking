package com.korzunva.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view/*")
public class PagesController extends HttpServlet {

    private static final String ROOT = "/WEB-INF/jsp/";
    private static final String EMPTY_STRING = "";
    private static final String JSP = ".jsp";
    private static final String INDEX = "index";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getJsp(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getJsp(req, resp);
    }

    private void getJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        uri = uri.replace(req.getServletPath(), EMPTY_STRING);
        if (uri.equals(EMPTY_STRING)) {
            uri = INDEX;
        }
        uri = ROOT + uri + JSP;
        ServletContext context = req.getServletContext().getContext(uri);
        context.getRequestDispatcher(uri).forward(req, resp);
    }

}
