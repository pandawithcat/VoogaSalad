/*
package GameAuthoringEnvironment.AuthoringScreen;

public class RecursionHelper {

    //TODO Refactor code for gamecontroller and gameoutline and put it here
}


if (definedAttributesMap.keySet().contains(key)) {
        if (definedAttributesMap.get(key).equals(true)) {
        trueButton.setSelected(true);
        } else {
        falseButton.setSelected(true);
        }
        }


        Button myButton = new Button("Configure " + value.getSimpleName());
        myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event) {
        try {
        Class<?> clazz = Class.forName(value.getName());
        //special case: map TODO use reflection for this
        if (clazz.getSimpleName().equals("MapConfig")) {
        if (definedAttributesMap.keySet().contains(key)) {
        MapConfig mapConfig = (MapConfig) definedAttributesMap.get(key);
        ConfigurableMap configurableMap = new ConfigurableMap(mapConfig, myAttributesMap, myConfigurable);
        configurableMap.resetConfigurations();
        } else {
        ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap, myConfigurable);
        configurableMap.setConfigurations();
        }
        } else if (clazz.getSimpleName().equals("View")) {
        Constructor<?> cons = clazz.getConstructor(Configurable.class);
        var object = cons.newInstance(myConfigurable);
        try {
        showTheScreen((Configurable) object);
        } catch (NoSuchFieldException e) {
        e.printStackTrace();
        }
        myAttributesMap.put(key, object);
        } else {
        //TODO idf clazz does not taken in myconfigurable as a parameter, then error
        Constructor<?> cons = clazz.getConstructor(myConfigurable.getClass());
        var object = cons.newInstance(myConfigurable);
        try {
        showTheScreen((Configurable) object);
        } catch (NoSuchFieldException e) {
        e.printStackTrace();
        }
        myAttributesMap.put(key, object);
        }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
        //TODO ErrorChecking
        e.printStackTrace();
        }

        }
        }));
        layout.getChildren().add(myButton);*/
