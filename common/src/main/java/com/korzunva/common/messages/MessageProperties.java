package com.korzunva.common.messages;

import com.korzunva.common.exception.MessagePropertiesException;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Provides access to messages' properties of application
 */
public enum MessageProperties {

    INSTANCE;

    private static final String MESSAGE_PROPERTIES_FILE = "messages";

    private ResourceBundle bundle;

    MessageProperties() {
        try {
            bundle = ResourceBundle.getBundle(MESSAGE_PROPERTIES_FILE);
        } catch (MissingResourceException e) {
            throw new MessagePropertiesException(e.getMessage(), e);
        }
    }

    /**
     * Return property value found by key
     * @param key property's key
     * @return property's value
     */
    public String getProperty(String key) {
        try {
            bundle = ResourceBundle.getBundle(MESSAGE_PROPERTIES_FILE, Locale.getDefault());
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            throw new MessagePropertiesException(e.getMessage(), e);
        }
    }

}
