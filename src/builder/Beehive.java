package builder;

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

    public Beehive(int beeCount, String beeSpecies, int workers, int
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
}
