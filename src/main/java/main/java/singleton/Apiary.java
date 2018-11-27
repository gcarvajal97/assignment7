package main.java.main.java.singleton;

import java.util.Hashtable;
import main.java.main.java.builder.Beehive;

/**
 * Singleton Pattern: Singular apiary to hold all beehives.
 */
public class Apiary {
    private static Apiary _apiary = null;
    private Hashtable<Integer, Beehive> hives = new Hashtable<>();
    private int numberOfHives = 0;

    /**
     * Default Constructor.
     */
    private Apiary() {}

    /**
     * Creates singleton instance of apiary. If one is present, returns that
     * instead.
     * @return Apiary
     */
    public static Apiary getApiary() {
        if (_apiary == null) {
            System.out.println("No apiary found, creating a new one");
            _apiary = new Apiary();
        }
        System.out.println("Existing apiary found");
        return (_apiary);
    }

    /**
     * Generates new generic hive.
     */
    public void addBeehive() {
        Beehive newHive = new Beehive.Builder()
                .setMaxBeeCount(100)
                .setSpecies("Gatherer")
                .setWorkerCount(50)
                .setWarriorCount(10)
                .setKillCount(0)
                .setHiveKillCount(0)
                .setTickCount(1)
                .setHiveStatus("Alive")
                .setRoomCount(5).build();
        newHive.setFoodCount(100);
        this.hives.put(numberOfHives, newHive);
        this.numberOfHives++;
        
    }

    /**
     * Adds pre-built hive to table of hives.
     * @param newHive pre-built hive
     */
    public void addBeehive(Beehive newHive) {
        this.hives.put(this.numberOfHives, newHive);
        this.numberOfHives++;
    }

    private Hashtable<Integer, Beehive> getHives() {
        return hives;
    }

    /**
     * Returns beehive at specified key.
     * @param key which hive to return
     * @return Beehive
     */
    public Beehive getSpecifiedHive(int key) {
        Hashtable<Integer, Beehive> hives = this.getHives();
        return (hives.get(key));
    }

    /**
     * Checks if the current simulation should be continuing.
     * @return boolean
     */
    public boolean continueSimulation() {
        int numberOfLivingHives = 0;
        for (int i = 0; i < this.numberOfHives; i++) {
            Beehive currentHive = getSpecifiedHive(i);
            if (currentHive.getStatus().equalsIgnoreCase("alive")) {
                numberOfLivingHives++;
            }
        }
        return (numberOfLivingHives > 1);
    }
}
