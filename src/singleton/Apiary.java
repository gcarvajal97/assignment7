package singleton;

import builder.Beehive;

import java.util.Hashtable;

public class Apiary {
    private static Apiary _apiary = null;
    private Hashtable<Integer, Beehive> hives;
    private int numberOfHives = 0;

    private Apiary() {}

    public static Apiary getApiary() {
        if (_apiary == null) _apiary = new Apiary();
        return _apiary;
    }

    public void addBeehive(int beeCount, String beeSpecies, int workers,
                           int warriors, int kills, int hiveKills,
                           String hiveStatus, int roomCount) {
        Beehive newHive = new Beehive.Builder()
                .setMaxBeeCount(beeCount)
                .setSpecies(beeSpecies)
                .setWorkerCount(workers)
                .setWarriorCount(warriors)
                .setKillCount(kills)
                .setHiveKillCount(hiveKills)
                .setTickCount(1)
                .setHiveStatus(hiveStatus)
                .setRoomCount(roomCount).build();
        this.hives.put(numberOfHives, newHive);
        this.numberOfHives++;
    }

    private Hashtable<Integer, Beehive> getHives() {
        return hives;
    }

    public Beehive getSpecifiedHive(int key) {
        Hashtable<Integer, Beehive> hives = this.getHives();
        return hives.get(key);
    }
}
