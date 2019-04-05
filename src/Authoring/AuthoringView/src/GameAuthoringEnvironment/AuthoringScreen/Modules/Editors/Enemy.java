package GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import java.io.Serializable;

public class Enemy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name = "";

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
