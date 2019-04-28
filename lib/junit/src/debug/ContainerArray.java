package debug;


/**
 * A buggy testfx.
 *
 * How you fix the bugs is entirely up to you, but please fix them ONE at a time, using the TDD process.
 */
public class ContainerArray {
    private int myInitialCapacity = 10;
    private int myCurrentSize = 0;
    private String[] myInternalArray;

    public ContainerArray () {
        this(10);
    }

    public ContainerArray (int initialCapacity) {
        myInternalArray = new String[initialCapacity];
    }

    public void add (String element) {
        myInternalArray[myCurrentSize] = element;
        myCurrentSize += 1;
    }

    public int size () {
        return myCurrentSize;
    }

    public void remove (String objectToRemove) {
        myCurrentSize -= 1;
    }

    public String get (int index) {
        return myInternalArray[index];
    }
}
