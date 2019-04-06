package Configs;

import java.lang.Class;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    @interface Configure{}

    default Map<String, Type>  getAttributes(Class clazz){//potentially use object here and getclass? idk
        Map<String, Type> attributes = new HashMap<>();
        for (Field field: clazz.getDeclaredFields()){
            if (field.isAnnotationPresent(Configure.class)){
                attributes.put(field.getName(), field.getGenericType());//TODO you have the fields with the annotation, reflect upon them to get the type
            }//TODO check if super class configures are automatically found
        }
        return attributes;
    };
//    void setAllAtrributes(Map<String,Object> attributes);
//    void setAtrribute(String name, Object value);
}
