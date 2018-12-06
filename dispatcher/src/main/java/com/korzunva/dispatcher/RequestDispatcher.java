package com.korzunva.dispatcher;

import com.korzunva.common.exception.ExceptionWithResponseCode;
import com.korzunva.common.injector.BeanManager;
import com.korzunva.common.injector.Component;
import com.korzunva.common.messages.MessageProperties;
import com.korzunva.dispatcher.annotation.PreAuthorized;
import com.korzunva.dispatcher.processors.ControllerProcessor;
import com.korzunva.dispatcher.processors.MethodProcessor;
import com.korzunva.dispatcher.processors.ParamProcessor;
import com.korzunva.dispatcher.utils.JsonParser;
import com.korzunva.dispatcher.utils.ResponseWriter;
import com.korzunva.dispatcher.utils.HeaderUtils;
import com.korzunva.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**Represents an entry point of the application*/
@WebServlet(urlPatterns = {"/api/*"})
public class RequestDispatcher extends HttpWithPatchServlet {

    private static final String NOT_FOUND = "controller.not_found";
    private static final String FORBIDDEN = "controller.forbidden";

    private static final BeanManager beanManager = BeanManager.INSTANCE;
    private static final MessageProperties messageProperties = MessageProperties.INSTANCE;

    @Override
    public void init() throws ServletException {
        super.init();
        ControllerProcessor.scanControllers();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        performRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        performRequest(request, response);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        performRequest(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        performRequest(request, response);
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        performRequest(request, response);
    }

    private void performRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURI();
        Method targetMethod = MethodProcessor.getMethod(request.getMethod(), url);
        if (targetMethod == null) {
            ResponseWriter.error(response, messageProperties.getProperty(NOT_FOUND), HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        User user = HeaderUtils.getUser(request);
        if (!checkPermission(targetMethod, user)) {
            ResponseWriter.error(response, messageProperties.getProperty(FORBIDDEN), HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            String controllerName = targetMethod.getDeclaringClass().getAnnotation(Component.class).value();
            Controller controller = beanManager.getBean(Controller.class, controllerName);
            Object result = targetMethod.invoke(controller, ParamProcessor.getParams(request, targetMethod));
            String resultJson = JsonParser.objectToJson(result);

            ResponseWriter.write(response, resultJson);
        } catch (InvocationTargetException e) {
            try {
                ExceptionWithResponseCode exception = (ExceptionWithResponseCode) e.getTargetException();
                ResponseWriter.error(response, exception.getMessage(), exception.getResponseCode().getCode());
            } catch (ClassCastException ex) {
                ResponseWriter.error(response, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ResponseWriter.error(response, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkPermission(Method method, User user) {
        PreAuthorized preAuthorized = method.getAnnotation(PreAuthorized.class);
        if (preAuthorized != null) {
            return null != Arrays.stream(preAuthorized.value())
                    .filter(role -> user.getRoles().contains(role))
                    .findAny()
                    .orElse(null);
        }
        return true;
    }

}
