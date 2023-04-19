package br.com.ifms.microservices.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    private Utils() {}


    public static String[] getNullAttributes(Object dto) {
        List<String> nullAttributes = new ArrayList<>();
        for (Field field: dto.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(dto) == null) {
                    nullAttributes.add(field.getName());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return nullAttributes.toArray(String[]::new);
    }    
}
