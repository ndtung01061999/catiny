package com.regitiny.catiny.tools.utils;

import org.jsoup.Jsoup;

/**
 *
 */
public class StringUtils {

    public static String clean(String input) {
        String afterCleanHTML = Jsoup.parse(input).text();
        String lowerCase = afterCleanHTML.toLowerCase();
        return org.apache.commons.lang3.StringUtils.stripAccents(lowerCase).replace("đ", "d");
    }
}
