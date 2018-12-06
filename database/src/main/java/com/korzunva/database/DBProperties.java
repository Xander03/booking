package com.korzunva.database;

import com.korzunva.database.exception.DBPropertyException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * DBProperties produce access to db.properties file,
 * where should be stored data to connect to database
 */
public enum  DBProperties {

    INSTANCE;

    private static final String DB_PROPERTIES_FILE = "db.properties";

    private static final String PROPERTY_NOT_FOUND_EXCEPTION = "Property with key '%s' not found";
    private static final String READING_PROPERTIES_FILE_EXCEPTION = "Exception while reading db.properties file";

    private Properties properties;

    DBProperties() {
        try {
            properties = new Properties();
            properties.load(new InputStreamReader(
                    DBProperties.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)));
        } catch (IOException | NullPointerException e) {
            throw new DBPropertyException(READING_PROPERTIES_FILE_EXCEPTION, e);
        }
    }

    /**
     * Returns property value found by key
     * @param key property's key
     * @return property's value
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new DBPropertyException(String.format(PROPERTY_NOT_FOUND_EXCEPTION, key));
        }
        return value;
    }

}
