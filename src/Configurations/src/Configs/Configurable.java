package Configs;

import java.lang.Class;
import java.util.Map;

public interface Configurable {
    Map<String, Class> getAttributes();
    void setAllAtrributes(Map<String,Object> attributes);
    void setAtrribute(String name, Object value);

}
