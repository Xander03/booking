package com.korzunva.dispatcher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korzunva.common.messages.MessageProperties;

import java.io.IOException;

/**JsonParser util class that contains methods to work with JSONs*/
public class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final MessageProperties messageProperties = MessageProperties.INSTANCE;

    private static final String JSON_TO_ENTITY_EXCEPTION = "json.json_to_entity";
    private static final String OBJECT_TO_JSON_EXCEPTION = "json.object_to_json";

    /**Parse object to JSON string
     * @param object object to be parsed*/
    public static String objectToJson(Object object) throws IOException {
        if (object instanceof String) {
            return (String) object;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new IOException(messageProperties.getProperty(OBJECT_TO_JSON_EXCEPTION));
        }
    }

    /**Parse JSON string to object
     * @param clazz class of needed object
     * @param json JSON string*/
    public static <C> C jsonToObject(Class<C> clazz, String json) throws IOException {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IOException(messageProperties.getProperty(JSON_TO_ENTITY_EXCEPTION));
        }
    }

}
