package debug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ContainerArrayTest {
    private ContainerArray myContainer = null;

    @BeforeEach
    public void setUp () {
        myContainer = new ContainerArray();
    }

    // do not change this test
    @Test
    public void testSizeChangeWithAdd () {
        int expected = 3;
        myContainer.add("Alligator");
        myContainer.add("Bear");
        myContainer.add("Camel");
        assertEquals(expected, myContainer.size(), "Add size");
    }

    // do not change this test
    @Test
    public void testObjectIsStored () {
        String expected = "Alligator";
        myContainer.add("Alligator");
        assertEquals(expected, myContainer.get(0), "Add should be same reference");
    }

    // do not change this test
    @Test
    public void testSizeChangeWithRemove () {
        int expected = 1;
        myContainer.add("Alligator");
        myContainer.add("Bear");
        myContainer.remove("Alligator");
        assertEquals(expected, myContainer.size(), "Remove size");
    }

    // do not change this test
    @Test
    public void testObjectIsRemoved () {
        String expected = "Alligator";
        myContainer.add("Alligator");
        myContainer.add("Bear");
        myContainer.remove("Bear");
        assertEquals(expected, myContainer.get(0), "Remove should be same reference");
    }

    // TODO: add new tests here
}
