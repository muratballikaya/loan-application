package com.mb.auth.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacsonUtils {



    // Serialize Java object to JSON
    public static String serializeToJson(Object object)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    // Deserialize JSON to Java object
    public static <T> T deserializeJson(String jsonString, Class<T> valueType) {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, valueType);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
