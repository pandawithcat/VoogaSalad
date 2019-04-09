package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;

import java.io.ObjectInputFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
public class GameController {

    private String myGameName;
    private String gameType;
    private int myScreenSize;
    private int myNumberOfLevels;
    private int myNumberOfLives;


    public GameController(String gameName, int screenSize, int numberOfLevels, int numberOfLives) {

        myGameName = gameName;
        myScreenSize = screenSize;
        myNumberOfLevels = numberOfLevels;
        myNumberOfLives = numberOfLives;
        Game myGame = new Game();
        createConfigurable(myGame);

    }


    public void createConfigurable(Configurable myConfigurable){
        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();
        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //System.out.println(key + " " + value);


            if (!value.equals(java.lang.String.class) && !value.equals(java.lang.Integer.class)) {
                //Handle obj list first
                System.out.println(key + " " + value);
                if (!value.equals(java.util.List.class)) {
                    try {
                        Object myObject = value.getConstructor().newInstance();
                        System.out.println(myObject.toString() + "single object");
                        if (myObject instanceof Configurable) {
                            createConfigurable((Configurable) myObject);
                        }
                    } catch (Exception e) {//TODO Write Some errors here
                    }
                }
                // Handle Object List
                else {
                    System.out.println("this reached here");
                    try {
                        Object[] list = (Object[]) value.getConstructor().newInstance();
                        System.out.println(list.toString());
                        System.out.println(list);
                        for (int a = 0; a < list.length; a++) {
                            if (list[a] instanceof Configurable) {
                                System.out.println(list[a].toString() + "dadhfad");
                                createConfigurable((Configurable) list[a]);
                            } else {
                                handlePrimitives(myConfigurable, myAttributesMap, key, list[a]);
                            }
                        }
                    } catch (Exception e) {//TODO Write Some error
                    }
                }
            }
            else {
                handlePrimitives(myConfigurable, myAttributesMap, key, value);
            }

            System.out.println(myAttributesMap);
            //default object
           /* Object object = new Game();
            try {
                Class<?> clazz = Class.forName(key);
                object = clazz.getConstructor().newInstance();
            }catch (Exception e){
            }

        myAttributesMap.put(key, object);*/

        }
        myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
    }

    private void handlePrimitives(Configurable configurable, Map<String, Object> attributeMap, String key, Object value){
        String keyToCap = key.substring(0, 1).toUpperCase() + key.substring(1);
        try {
            Method method = configurable.getClass().getMethod("get"+keyToCap);
            Object object = method.invoke(configurable);
            attributeMap.put(key, object);
        } //TODO Error Handling needed
        catch (InvocationTargetException e) {}
        catch (IllegalArgumentException e) {}
        catch (IllegalAccessException e) {}
        catch (NoSuchMethodException e){}

    }


    public int getMyNumberOfLevels(){
        return myNumberOfLevels;
    }



}
