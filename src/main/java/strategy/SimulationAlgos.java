package main.java.strategy;

import java.util.ArrayList;
import main.java.builder.Beehive;
import main.java.mediator.BeeImpl;

/**
 * Strategy Pattern: Class stores all methods involved with actions per tick.
 */
public class SimulationAlgos {
    private int tick = 0;
    private ArrayList<BeeImpl> bees;
    private Beehive hive;

    /**
     * Default Constructor.
     * @param bees ArrayList of bees for this algo.
     * @param hive Hive for this algo.
     */
    public SimulationAlgos(ArrayList<BeeImpl> bees, Beehive hive) {
        this.bees = bees;
        this.hive = hive;
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
        for(int i = 0; i < tick; i++) {
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
    private void performTickAction() {
        for (BeeImpl bee : this.bees) {

            if (bee.getBeeType().equalsIgnoreCase("worker")) {
                if (!bee.getStatus().equalsIgnoreCase("free")) {
                    if(tick % 100 == 0) {
                        bee.setStatus("free");
                        useFood(bee);
                    }
                }
                else if(bee.getStatus().equalsIgnoreCase("rest")) {
                    useFood(bee);
                }
                else {
                    bee.setStatus("Working");
                    useFood(bee);
                }
            }

            if (bee.getBeeType().equalsIgnoreCase("queen")) {
                bee.send("Laying egg");
                useFood(bee);
            }

            if (bee.getBeeType().equalsIgnoreCase("drone")) {
                bee.send("Looking for bees to kill");
            }

            if (bee.getBeeType().equalsIgnoreCase("forager")) {
                bee.send("Gathering food");
                this.hive.setFoodCount(this.hive.getFoodCount() + 10);
                useFood(bee);
            }
        }

    }

    private void useFood(BeeImpl bee) {
        if (this.hive.getFoodCount() == 0) {
            killBee(bee);
        }
        else {
            this.hive.setFoodCount(this.hive.getFoodCount() - 1);
            System.out.println("Current food stores: "
                    + this.hive.getFoodCount());
        }
    }

    /**
     * Kills the current bee due to lack of food.
     * @param bee Bee to remove
     */
    private void killBee(BeeImpl bee) {
        System.out.println("Not enough food to sustain bee, so death...");
        this.bees.remove(bee);
        if (bee.getBeeType().equalsIgnoreCase("queen")) {
            System.out.println("The queen has died.");
            this.hive.killHive("Dead: Queen died of starvation");
        }
    }
}
