package GUI.GameAuthoringEnvironment.AuthoringConfig;


import java.io.Serializable;

public class Enemy  implements Serializable {
    private String name;

    public Enemy(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
