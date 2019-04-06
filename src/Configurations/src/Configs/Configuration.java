package Configs;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private Map<String,Type> myAttributeTypes;
    private Map<String,Object> myAttributes;
    private boolean isComplete = false;
    Class myConfigurableClass;

    //TODO: check one key at a time

    public Configuration(Configurable configurable) {
        myConfigurableClass = configurable.getClass();
    }

    private void validateAttributes(Map<String,Object> attributeInputs) {
        if(!(attributeInputs.keySet().containsAll(myAttributeTypes.keySet()) &&attributeInputs.size()==myAttributeTypes.size())) {
            throw new IllegalArgumentException();
        }
        myAttributes.keySet().stream().forEach(key -> validateType(key));
    }

    private void validateType(String attributeInput) {
        if (attributeInput.getClass()!=myAttributeTypes.get(attributeInput)) {
            throw new IllegalArgumentException();
        }
    }

    public void setAttributes(Map<String,Object> attributes) {
        validateAttributes(attributes);
        myAttributes = attributes;
    }

    public Map<String, Class>  getAttributes(){
        Map<String, Class> attributes = new HashMap<>();
        for (Field field: myConfigurableClass.getDeclaredFields()){
            if (field.isAnnotationPresent(Configurable.Configure.class)){
                attributes.put(field.getName(), field.getType());
            }
        }
        return attributes;
    };

//    public void cast(Map<String,Object> attributes) {
//        for(String key : attributes.keySet()) {
//            myAttributeTypes.get(key).cast(attributes.get(key));
//        }
//    }




}
