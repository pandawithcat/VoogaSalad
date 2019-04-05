package Configs;

import java.lang.Class;
import java.util.Map;

public interface Configurable {
    Map<String, Class> getAttributes();
}
