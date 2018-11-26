package builder;

import mediator.BeeImpl;

import java.util.List;

/**
    Beehive Object: Utilizes Builder pattern
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
    private List<Room> rooms;

    /**
     * Private constructor since utilizing a builder
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

    public String getStatus() {
        return status;
    }

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

        public Builder setMaxBeeCount(int maxCount){
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

        public Builder setWarriorCount(int count){
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

        public Beehive build(){
            return new Beehive(maxBeeCount, species, workerCount,
                    warriorCount, killCount, hiveKillCount, tickCount,
                    status, roomCount);
        }
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public class Room {
        List<BeeImpl> bees;
        int currentTickCount = 0;
        String status;

        public Room() {
            status = "Building";
        }

        public Room(List<BeeImpl> bees, String status) {
            this.status = "Built";
            this.bees = bees;
        }

        public int getTickCount() {
            return currentTickCount;
        }
    }

    public void killHive(String killReason) {
        this.status = killReason;
    }
}
