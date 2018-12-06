package com.korzunva.dispatcher.processors;

import com.korzunva.dispatcher.annotation.RestController;
import org.reflections.Reflections;

import java.util.Set;

/**
 * ControllerProcessor analysis {@link RestController} annotation
 */
public class ControllerProcessor {

    /**Search all classes annotated with @Controller*/
    public static void scanControllers() {
        Reflections reflections = new Reflections("");
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(RestController.class);
        for (Class<?> controller : controllers) {
            MethodProcessor.getMethods(controller);
        }
    }

    /**Returns controllers url taken from @Controller annotation
     * @param controller controller's class*/
    static String getControllerPath(Class<?> controller) {
        return controller.getAnnotation(RestController.class).value();
    }

}
