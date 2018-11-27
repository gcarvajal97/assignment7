package main.java.main.java.strategy;

import java.util.ArrayList;
import main.java.main.java.builder.Beehive;
import main.java.main.java.mediator.BeeImpl;

/**
 * Strategy Pattern: Class stores all methods involved with actions per tick.
 */
public class SimulationAlgos {
    private int tick = 0;
    private ArrayList<BeeImpl> bees;
    private Beehive hive;
    private String lock = "unlocked";
    private int kill;
    ArrayList<BeeImpl> newBeeList;

    /**
     * Default Constructor.
     * @param bees ArrayList of bees for this algo.
     * @param hive Hive for this algo.
     */
    public SimulationAlgos(ArrayList<BeeImpl> bees, Beehive hive) {
        this.bees = bees;
        this.hive = hive;
        this.newBeeList = new ArrayList<>();
        this.newBeeList.addAll(bees);
    }

    /**
     * Gets current tick.
     * @return current tick
     */
    public int getTick() {
        return tick;
    }

    /**
     * Increments tick by designated value.
     * @param tick number of increments
     */
    public void setTick(int tick) {
        for (int i = 0; i < tick; i++) {
            this.tick++;
        }
    }

    /**
     * Increments tick by one.
     */
    public void setTick() {
        this.tick++;
    }

    /**
     * Performs different tick action based on the bee type.
     */
    public void performTickAction() {
        System.out.println(
                "Performing actions for tick number: " + this.getTick());
        this.lock = "locked";
        for (BeeImpl bee : this.bees) {
            this.kill = 0;
            if (bee.getBeeType().equalsIgnoreCase("worker")) {
                if (!bee.getStatus().equalsIgnoreCase("free")) {
                    if (tick % 100 == 0) {
                        useFood(bee);
                        if (this.kill == 0) {
                            bee.setStatus("free");
                        }
                    }
                } else if (
                        bee.getStatus().equalsIgnoreCase("rest")) {
                    useFood(bee);
                } else {
                    useFood(bee);
                    if (this.kill == 0) {
                        bee.setStatus("Working");
                    }
                }
            }

            if (bee.getBeeType().equalsIgnoreCase("queen")) {
                useFood(bee);
                if (this.kill == 0) {
                    bee.send("Laying egg");
                }
                
            }

            if (bee.getBeeType().equalsIgnoreCase("drone")) {
                useFood(bee);
                if (this.kill == 0) {
                    bee.send("Looking for bees to kill");
                }
            }

            if (bee.getBeeType().equalsIgnoreCase("forager")) {
                useFood(bee);
                if (this.kill == 0) {
                    bee.send("Gathering food");
                    hive.setFoodCount(hive.getFoodCount() + 10);
                }
            }
        }
        this.lock = "unlocked";
        BeeImpl beeToKill = (
                this.newBeeList.size() > 0) ? this.newBeeList.get(0) : null;
        killBee(beeToKill);

    }

    private void useFood(BeeImpl bee) {
        if (hive.getFoodCount() == 0) {
            killBee(bee);
        } else {
            hive.setFoodCount(hive.getFoodCount() - 1);
            System.out.println("Current food stores: "
                    + hive.getFoodCount());
        }
    }

    /**
     * Kills the current bee due to lack of food.
     * @param bee Bee to remove
     */
    private void killBee(BeeImpl bee) {
        if (bee == null) {
            System.out.println("No bees died this tick");
        } else {
            System.out.println("Not enough food to sustain bee, so death...");
            this.newBeeList.remove(bee);
            this.hive.increaseKillCount();
            this.kill = 1;
            if (bee.getBeeType().equalsIgnoreCase("queen")) {
                System.out.println("The queen has died.");
                this.hive.killHive("Dead: Queen died of starvation");
            }
            if (this.lock.equals("unlocked")) {
                this.bees = this.newBeeList;
            }
        }
    }
}
