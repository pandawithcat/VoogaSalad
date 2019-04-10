package Configs;

<<<<<<< HEAD
import Configs.Behaviors.Behavior;
import Configs.Behaviors.BehaviorManager;
import Configs.Waves.Wave;
import Configs.Waves.WaveSpawner;

=======
//import Configs.Behaviors.BehaviorManager;
import Configs.Waves.WaveConfig;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
import java.lang.reflect.Field;
import java.util.*;

public class Configuration {
    private Map<String,Class> myAttributeTypes;
    private Map<String,Object> myAttributes;
    private boolean isComplete = false;
    Class myConfigurableClass;

    public Configuration(Configurable configurable) {
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

<<<<<<< HEAD
    public void setOneAttribute(String name, Object value) {
        validateType(name,value);
        myAttributes.put(name,value);
        if(isAttributesComplete(myAttributes)) isComplete = true;
    }

    public void setAllAttributes(Map<String,Object> attributes) {
        validateAttributes(attributes);
        for (String key:attributes.keySet()) {
            if(attributes.get(key) instanceof Behavior[]) {
                attributes.put(key,new BehaviorManager(new ArrayList<>(Arrays.asList(attributes.get(key)))));
            }
            if(attributes.get(key) instanceof Wave[]) {
                attributes.put(key,new WaveSpawner(new ArrayList<>(Arrays.asList((Wave[]) attributes.get(key)))));
            }
        }
=======
//    public void setOneAttribute(String name, Object value) {
//        validateType(name,value);
//        myAttributes.put(name,value);
//        if(isAttributesComplete(myAttributes)) {
//            isComplete = true;
//        }
//    }

    public void setAllAttributes(Map<String,Object> attributes) {
        validateAttributes(attributes);
//        for (String key:attributes.keySet()) {
//            if(attributes.get(key) instanceof Behavior[]) {
//                attributes.put(key,new BehaviorManager(new ArrayList<>(Arrays.asList(attributes.get(key)))));
//            }
//            if(attributes.get(key) instanceof WaveConfig[]) {
//                attributes.put(key,new WaveSpawner(new ArrayList<>(Arrays.asList((WaveConfig[]) attributes.get(key)))));
//            }
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
        myAttributes = attributes;
        setAttributesInConfigurable();
        isComplete = true;
    }

<<<<<<< HEAD
=======
    private void setAttributesInConfigurable() throws IllegalStateException{
        for(String key:myAttributes.keySet()) {
            try {
                Field field = myConfigurableClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(myConfigurable, myAttributes.get(key));
            }
            catch (NoSuchFieldException e) {
                throw new IllegalStateException();
            }
            catch (IllegalAccessException e) {
                throw new IllegalStateException();
            }
        }

    }

>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    public Map<String, Class>  getAttributes(){
        Map<String, Class> attributes = new LinkedHashMap<>();
        for (Field field: myConfigurableClass.getDeclaredFields()){
            if (field.isAnnotationPresent(Configurable.Configure.class)){
                attributes.put(field.getName(), field.getType());
            }
        }
        return Collections.unmodifiableMap(attributes);
    }

    public boolean isConfigurationComplete() {
        return isComplete;
    }

    public Map<String,Object> getDefinedAttributes() {
        return Collections.unmodifiableMap(myAttributes);
    }



<<<<<<< HEAD
//    public void cast(Map<String,Object> attributes) {
//        for(String key : attributes.keySet()) {
//            myAttributeTypes.get(key).cast(attributes.get(key));
//        }
//    }

=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860



}
