package main.java.main.java.builder;

import java.util.ArrayList;
import main.java.main.java.mediator.BeeImpl;


/**
    Beehive Object: Utilizes Builder pattern.
 */
public class Beehive {
    private int maxBeeCount;
    private String species;
    private int workerCount;
    private int warriorCount;
    private int killCount;
    private int hiveKillCount;
    private int tickCount;
    private String status;
    private int roomCount;
    private int foodCount = 100;
    private ArrayList<Room> rooms = new ArrayList<>();

    /**
     * Private constructor since utilizing a main.java.builder.
     * @param beeCount number of bees that can be in a room
     * @param beeSpecies overall species of bee
     * @param workers number of worker bees
     * @param warriors number of warrior bees
     * @param kills number of killed bees
     * @param hiveKills number of hives killed
     * @param ticksAlive how long the hive has been alive
     * @param hiveStatus current status of the hive
     * @param roomCount how many rooms are in the hive
     */
    private Beehive(int beeCount, String beeSpecies, int workers, int
            warriors, int kills, int hiveKills, int ticksAlive, String
            hiveStatus, int roomCount) {
        this.maxBeeCount = beeCount;
        this.species = beeSpecies;
        this.workerCount = workers;
        this.warriorCount = warriors;
        this.killCount = kills;
        this.hiveKillCount = hiveKills;
        this.tickCount = ticksAlive;
        this.status = hiveStatus;
        this.roomCount = roomCount;
    }

    /**
     * Gets current hive status.
     * @return hive status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Inner class to implement builder pattern.
     */
    public static class Builder {
        private int maxBeeCount;
        private String species;
        private int workerCount;
        private int warriorCount;
        private int killCount;
        private int hiveKillCount;
        private int tickCount;
        private String status;
        private int roomCount;

        public Builder(){}

        public Builder setMaxBeeCount(int maxCount) {
            this.maxBeeCount = maxCount;
            return this;
        }

        public Builder setSpecies(String hiveSpecies) {
            this.species = hiveSpecies;
            return this;
        }

        public Builder setWorkerCount(int count) {
            this.workerCount = count;
            return this;
        }

        public Builder setWarriorCount(int count) {
            this.warriorCount = count;
            return this;
        }

        public Builder setKillCount(int count) {
            this.killCount = count;
            return this;
        }

        public Builder setHiveKillCount(int count) {
            this.hiveKillCount = count;
            return this;
        }

        public Builder setTickCount(int count) {
            this.tickCount = count;
            return this;
        }

        public Builder setHiveStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setRoomCount(int roomCount) {
            this.roomCount = roomCount;
            return this;
        }

        /**
         * Method to build the beehive using the builder pattern.
         * @return new beehive object
         */
        public Beehive build() {
            System.out.println("Creating new beehive of type: " + species);
            return new Beehive(maxBeeCount, species, workerCount,
                    warriorCount, killCount, hiveKillCount, tickCount,
                    status, roomCount);
        }
    }

    /**
     * Builds generic rooms.
     * @param roomCount how many rooms to build
     * @param bees list of bees that live there
     */
    public void buildRoom(int roomCount, ArrayList<BeeImpl> bees) {
        for (int i = 0; i < roomCount; i++) {
            this.rooms.add(new Room(bees));
        }
    }

    /**
     * Builds empty room.
     */
    public void buildRoom() {
        this.rooms.add(new Room());
    }

    /**
     * Gets current food stores.
     * @return food count
     */
    public int getFoodCount() {
        return foodCount;
    }

    /**
     * Sets current food stores.
     * @param foodCount What value to set food stores to.
     */
    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    /**
     * Inner class to represent a hive room.
     */
    public static class Room {
        ArrayList<BeeImpl> bees;
        int currentTickCount = 0;
        String status;

        public Room() {
            status = "Building";
        }

        public Room(ArrayList<BeeImpl> bees) {
            this.status = "Built";
            this.bees = bees;
        }

        /**
         * Gets current tick count for room building.
         * @return tick count
         */
        public int getTickCount() {
            return currentTickCount;
        }
    }

    /**
     * Kills current hive.
     * @param killReason Why hive died.
     */
    public void killHive(String killReason) {
        this.status = killReason;
    }
    
    /**
     * Gets current room number.
     * @return room count
     */
    public int getRoomCount() { 
        return this.roomCount;
    }
    
    /**
     * Gets current kill count.
     * @return kill count
     */
    public int getKillCount() {
        return this.killCount;
    }
    
    /**
     * Increments current kill count.
     */
    public void increaseKillCount() {
        this.killCount++;
    }
}
