package Configs;

import java.util.Map;
import java.util.Set;

public class Configuration {
    private Map<String, Class> myAttributeTypes;
    private Map<String,Object> myAttributes;

    /**
     * call in constructor of each configurable
     * @param attributeTypes
     */
    public Configuration(Map<String,Class> attributeTypes) {
        myAttributeTypes = attributeTypes;
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

    public void cast(Map<String,Object> attributes) {
        for(String key : attributes.keySet()) {
            myAttributeTypes.get(key).cast(attributes.get(key));
        }

    }



}
