package com.megamaker.codechallenge.domain.problem.vo;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public enum JavaTypeClazz {
    BYTE(byte.class ,Byte.class, byte[].class, Byte[].class),
    SHORT(short.class, Short.class, short[].class, Short[].class),
    INT(int.class, Integer.class, int[].class, Integer[].class),
    INTEGER(int.class, Integer.class, int[].class, Integer[].class),
    LONG(long.class, Long.class, long[].class, Long[].class),
    FLOAT(float.class, Float.class, float[].class, Float[].class),
    DOUBLE(double.class, Double.class, double[].class, Double[].class),
    CHAR(char.class, Character.class, char[].class, Character[].class),
    CHARACTER(char.class, Character.class, char[].class, Character[].class),
    STRING(String.class, String.class, String[].class, String[].class),
    BOOLEAN(boolean.class, Boolean.class, boolean[].class, Boolean[].class);

    private final Class<?> type;
    private final Class<?> wrapperType;
    private final Class<?> arrayType;
    private final Class<?> arrayWrapperType;

    JavaTypeClazz(Class<?> type, Class<?> wrapperType, Class<?> arrayType, Class<?> arrayWrapperType) {
        this.type = type;
        this.wrapperType = wrapperType;
        this.arrayType = arrayType;
        this.arrayWrapperType = arrayWrapperType;
    }

    public Class<?> getType() {
        return type;
    }

    public Class<?> getWrapperType() {
        return wrapperType;
    }

    public Class<?> getArrayType() {
        return arrayType;
    }

    public Class<?> getArrayWrapperType() {
        return arrayWrapperType;
    }

    public static Class<?> stringTypeToClass(String str) {
        if (!StringUtils.hasText(str)) return null;

        for (JavaTypeClazz typeConv : JavaTypeClazz.values()) {
            if (typeConv.name().equals(str.toUpperCase())
                    ||  str.toUpperCase().matches(typeConv.name() + "(\\[\\]){1,2}")) {
                boolean isStartWithLowerCase = Character.isLowerCase(str.charAt(0));

                // 배열일 때
                if (str.endsWith("]")) {
                    if (isStartWithLowerCase) return typeConv.getArrayType();  // primitive Array 일 때
                    else return typeConv.getArrayWrapperType();  // Wrapper Array 일 때
                }

                // 배열이 아닐 때
                if (isStartWithLowerCase) return typeConv.getType();  // primitive type 일 때
                else return typeConv.getWrapperType();  //  Wrapper Type 일 때
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
            Class<?> foundType = stringTypeToClass(strType);

            if (foundType != null) result.add(foundType);
        }
        return result.toArray(Class<?>[]::new);
    }

    public static Method toMethod(Class<?> clazz) throws NoSuchMethodException {
        if (clazz.equals(String.class)) {
            return JavaTypeClazz.class.getMethod("toString", String.class);
        }

        // 값 타입일 때
        if (clazz.isPrimitive()) {
            if (clazz.equals(INT.type)) {
                return Integer.class.getMethod("parseInt", String.class);
            } else if (clazz.equals(CHAR.type)) {
                return JavaTypeClazz.class.getMethod("toChar", String.class);
            } else {
                return clazz.getMethod(
                        "parse" +
                        clazz.getName().substring(0, 1).toUpperCase() + clazz.getName().substring(1),
                        String.class
                );
            }
        } else {  // Wrapper 클래스 일 때
            return clazz.getMethod("valueOf", String.class);
        }
    }

    public static String toString(String str) {
        return str;
    }

    public static char toChar(String str) {
        return str.charAt(0);
    }
}
