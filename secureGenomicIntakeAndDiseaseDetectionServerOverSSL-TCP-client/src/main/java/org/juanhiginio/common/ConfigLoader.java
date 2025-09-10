/*
 *  //  Objective //
 *     Load system config.properties from config.config.properties.
 *  // Inputs //
 *     config.config.properties file in the classpath.
 *  // Process //
 *     Use java.util.Properties to read the file and expose getters.
 *  // Outputs //
 *     ConfigLoader object with access to config.properties.
 */

package org.juanhiginio.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties props = new Properties();

    public ConfigLoader() {
        try (InputStream is = getClass().getResourceAsStream("/config.properties")) {
            if (is == null) {
                throw new IllegalStateException("config.config.properties not found in resources");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error reading configuration: " + e.getMessage(), e);
        }
    }

    public String get(String key, String def) {
        return props.getProperty(key, def);
    }

    public int getInt(String key, int def) {
        return Integer.parseInt(props.getProperty(key, String.valueOf(def)));
    }
}