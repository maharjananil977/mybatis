package org.personsal.mybatis.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** created by: maharjananil created on: 11/30/2024 */
public class JsonUtils {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();

    private JsonUtils(){
    }

    public static <T> String toJsonObj(T obj) {
        return gson.toJson(obj);
    }

    public static <T> String toJsonList(List<T> objCol) {
        return gson.toJson(objCol);
    }

    public static <T> T fromJsonToObj(String jsonString, Class<T> obj) {
        if (!isValidJsonString(jsonString)) {
            throw new IllegalArgumentException("Invalid Json data.");
        }
        return gson.fromJson(jsonString, obj);
    }
    public static <T> List<T> fromJsonToList(String jsonString, Type t) {
        if (!isValidJsonString(jsonString)) {
            return Collections.emptyList();
        }
        return Arrays.asList(gson.fromJson(jsonString, t));
    }

    private static boolean isValidJsonString(String jsonString) {
        if (StringUtils.isBlankOrNull(jsonString)) {
            return false;
        }
        try {
            gson.fromJson(jsonString, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
