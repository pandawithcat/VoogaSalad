package Configs;

//import Configs.Behaviors.BehaviorManager;
import Configs.Waves.WaveConfig;
        import Configs.Waves.WaveSpawner;

import java.lang.reflect.Field;
import java.util.*;

public class Configuration {
    private Map<String,Class> myAttributeTypes;
    private Map<String,Object> myAttributes;
    private boolean isComplete = false;
    private Configurable myConfigurable;
    private Class myConfigurableClass;

    public Configuration(Configurable configurable) {
        myConfigurable = configurable;
        myConfigurableClass = configurable.getClass();
    }

    private boolean isAttributesComplete(Map<String,Object> attributeInputs) {
        return attributeInputs.keySet().containsAll(myAttributeTypes.keySet()) && attributeInputs.size()==myAttributeTypes.size();
    }

    private void validateAttributes(Map<String,Object> attributeInputs) {
        if(!isAttributesComplete(attributeInputs)) {
            throw new IllegalArgumentException();
        }
        myAttributes.keySet().stream().forEach(key -> validateType(key,attributeInputs.get(key)));
    }

    private void validateType(String attributeInput, Object value) {
        if (value.getClass()!=myAttributeTypes.get(attributeInput)) {
            throw new IllegalArgumentException();
        }
    }

    public void setOneAttribute(String name, Object value) {
        validateType(name,value);
        myAttributes.put(name,value);
        if(isAttributesComplete(myAttributes)) {
            isComplete = true;
        }
    }

    public void setAllAttributes(Map<String,Object> attributes) {
        validateAttributes(attributes);
        for (String key:attributes.keySet()) {
//            if(attributes.get(key) instanceof Behavior[]) {
//                attributes.put(key,new BehaviorManager(new ArrayList<>(Arrays.asList(attributes.get(key)))));
//            }
            if(attributes.get(key) instanceof WaveConfig[]) {
                attributes.put(key,new WaveSpawner(new ArrayList<>(Arrays.asList((WaveConfig[]) attributes.get(key)))));
            }
        }
        myAttributes = attributes;
        isComplete = true;
    }

    private void setAttributesInConfigurable(){

    }

    public Map<String, Class>  getAttributes(){
        Map<String, Class> attributes = new HashMap<>();
        for (Field field: myConfigurableClass.getDeclaredFields()){
            if (field.isAnnotationPresent(Configurable.Configure.class)){
                attributes.put(field.getName(), field.getType());
            }
        }
        myAttributeTypes = attributes;
        return Collections.unmodifiableMap(attributes);
    };

    public boolean isConfigurationComplete() {
        return isComplete;
    }

    public Map<String,Object> getDefinedAttributes() throws IllegalStateException {
        if (!isComplete) throw new IllegalStateException();
        return Collections.unmodifiableMap(myAttributes);
    }




//    public void cast(Map<String,Object> attributes) {
//        for(String key : attributes.keySet()) {
//            myAttributeTypes.get(key).cast(attributes.get(key));
//        }
//    }




}
