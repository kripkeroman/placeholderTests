package com.org.placeholder.property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Utility class for reading properties from a file.
 * This class provides a method to load properties from a specified file path.
 */
public class PropertyReader
{
    /**
     * Loads properties from the specified file path.
     *
     * This method attempts to read a properties file from the given file path
     * and returns the loaded properties as a {@link Properties} object.
     * If the file cannot be found or an I/O error occurs during reading,
     * a {@link RuntimeException} is thrown.
     *
     * @param filePath the path to the properties file to load
     * @return a {@link Properties} object containing the properties loaded from the file
     * @throws RuntimeException if an I/O error occurs or the file cannot be found
     */
    public static Properties loadProperties(String filePath)
    {
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }
}
