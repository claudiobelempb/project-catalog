package br.com.surb.catalog.shared.utils;

import org.apache.logging.log4j.util.Strings;

import java.util.List;

public final class StringUtils {
    /*Converte um lista e separa por virgula*/
    public static String AppStringUltilsJoin(List<String> list) {
        return Strings.join(list, ',');
    }
}
