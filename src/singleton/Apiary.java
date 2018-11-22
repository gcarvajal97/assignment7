package singleton;

import builder.Beehive;

import java.util.Hashtable;

public class Apiary {
    private static Apiary _apiary = null;
    private static Hashtable<Integer, Beehive> hives;

    private Apiary() {

    }
    public static Apiary getApiary() {
        if (_apiary == null) _apiary = new Apiary();
        return _apiary;
    }
}
