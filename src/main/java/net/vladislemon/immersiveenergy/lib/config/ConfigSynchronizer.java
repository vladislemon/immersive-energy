package net.vladislemon.immersiveenergy.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.lang.reflect.Field;

public class ConfigSynchronizer {
    private final File configFile;
    private final Class<?> configClass;
    private final Object config;

    public ConfigSynchronizer(File configFile, Class<?> staticConfigClass) {
        this.configFile = configFile;
        this.configClass = staticConfigClass;
        this.config = null;
    }

    public ConfigSynchronizer(File configFile, Object config) {
        this.configFile = configFile;
        this.configClass = config.getClass();
        this.config = config;
    }

    public void synchronize() {
        try {
            doSynchronization();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void doSynchronization() throws IllegalAccessException {
        Configuration configuration = new Configuration(configFile);
        configuration.load();
        for (Class<?> categoryClass : configClass.getDeclaredClasses()) {
            String category = categoryClass.getSimpleName();
            for (Field propertyField : categoryClass.getDeclaredFields()) {
                String name = propertyField.getName();
                Class<?> type = propertyField.getType();
                Object value = propertyField.get(config);
                String comment = getComment(propertyField);
                if (type == String.class) {
                    String[] validValues = getValidValues(propertyField);
                    value = configuration.getString(name, category, (String) value, comment, validValues);
                } else if (type == int.class || type == Integer.class) {
                    int min = getMinInt(propertyField);
                    int max = getMaxInt(propertyField);
                    value = configuration.getInt(name, category, (Integer) value, min, max, comment);
                } else if (type == float.class || type == Float.class) {
                    float min = getMinFloat(propertyField);
                    float max = getMaxFloat(propertyField);
                    value = configuration.getFloat(name, category, (Float) value, min, max, comment);
                } else if (type == boolean.class || type == Boolean.class) {
                    value = configuration.getBoolean(name, category, (Boolean) value, comment);
                }
                propertyField.setAccessible(true);
                propertyField.set(config, value);
                propertyField.setAccessible(false);
            }
        }
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    private static String getComment(Field field) {
        Comment comment = field.getAnnotation(Comment.class);
        return comment != null ? comment.value() : "";
    }

    private static String[] getValidValues(Field field) {
        ValidValues validValues = field.getAnnotation(ValidValues.class);
        return validValues != null ? validValues.value() : new String[0];
    }

    private static int getMinInt(Field field) {
        MinInt minInt = field.getAnnotation(MinInt.class);
        return minInt != null ? minInt.value() : Integer.MIN_VALUE;
    }

    private static int getMaxInt(Field field) {
        MaxInt maxInt = field.getAnnotation(MaxInt.class);
        return maxInt != null ? maxInt.value() : Integer.MAX_VALUE;
    }

    private static float getMinFloat(Field field) {
        MinFloat minFloat = field.getAnnotation(MinFloat.class);
        return minFloat != null ? minFloat.value() : Float.NEGATIVE_INFINITY;
    }

    private static float getMaxFloat(Field field) {
        MaxFloat maxFloat = field.getAnnotation(MaxFloat.class);
        return maxFloat != null ? maxFloat.value() : Float.POSITIVE_INFINITY;
    }
}