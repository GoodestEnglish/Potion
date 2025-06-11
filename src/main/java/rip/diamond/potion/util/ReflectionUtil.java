package rip.diamond.potion.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtil {

    public static final Map<String, Field> FIELD_CACHE = new ConcurrentHashMap<>();
    public static final Map<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();

    public static Field getFieldFromHierarchy(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy.");
    }

    public static Field getCachedField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        String key = clazz.getName() + "#" + fieldName;

        if (FIELD_CACHE.containsKey(key)) {
            return FIELD_CACHE.get(key);
        }

        Field field = getFieldFromHierarchy(clazz, fieldName);
        FIELD_CACHE.put(key, field);
        return field;
    }

    public static Field getCachedField(String className, String fieldName) throws NoSuchFieldException, ClassNotFoundException {
        Class<?> clazz = getCachedClass(className);

        return getCachedField(clazz, fieldName);
    }

    public static Class<?> getCachedClass(String className) throws ClassNotFoundException {
        if (CLASS_CACHE.containsKey(className)) {
            return CLASS_CACHE.get(className);
        }

        Class<?> clazz = Class.forName(className);
        CLASS_CACHE.put(className, clazz);
        return clazz;
    }

}
