package com.marsinfotech.secforecast.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppUtils {

    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(List elements) {
        if (elements == null || elements.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(Set elements) {
        if (elements == null || elements.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

}
