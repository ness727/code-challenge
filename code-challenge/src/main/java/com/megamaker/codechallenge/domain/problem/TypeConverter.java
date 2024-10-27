package com.megamaker.codechallenge.domain.problem;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum TypeConverter {
    BYTE(Byte.class, byte[].class),
    SHORT(Short.class, short[].class),
    INT(Integer.class, int[].class),
    INTEGER(Integer.class, int[].class),
    LONG(Long.class, long[].class),
    FLOAT(Float.class, float[].class),
    DOUBLE(Double.class, double[].class),
    CHAR(Character.class, char[].class),
    CHARACTER(Character.class, char[].class),
    STRING(String.class, String[].class),
    BOOLEAN(Boolean.class, boolean[].class);

    private final Class<?> type;
    private final Class<?> arrayType;

    TypeConverter(Class<?> type, Class<?> arrayType) {
        this.type = type;
        this.arrayType = arrayType;
    }

    private Class<?> getType() {
        return type;
    }

    private Class<?> getArrayType() {
        return arrayType;
    }

    public static Class<?> stringToClass(String str) {
        if (!StringUtils.hasText(str)) return null;

        for (TypeConverter typeConv : TypeConverter.values()) {
            if (typeConv.name().equals(str.toUpperCase())
                    || (typeConv.name() + "[]").equals(str.toUpperCase())) {
                if (str.endsWith("]")) return typeConv.getArrayType();
                return typeConv.getType();
            }
        }
        return null;
    }

    /**
     * 필요없는 String은 스킵하고 타입으로 변경 가능한 것만 변경하여 반환
     */
    public static Class<?>[] convertAll(String[] strTypes) {
        List<Class<?>> result = new ArrayList<>();

        for (String strType : strTypes) {
            Class<?> foundType = stringToClass(strType);

            if (foundType != null) result.add(foundType);
        }
        return result.toArray(Class<?>[]::new);
    }
}
