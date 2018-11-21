package singleton;

public class Apiary {
    private static Apiary _apiary = null;

    private Apiary() {

    }
    public static Apiary getApiary() {
        if (_apiary == null) _apiary = new Apiary();
        return _apiary;
    }
}
