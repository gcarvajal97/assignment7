package strategy;

import builder.Beehive;
import mediator.Bee;
import mediator.BeeImpl;

import java.util.ArrayList;
import java.util.List;

public class SimulationAlgos {
    private int tick = 0;
    private List<BeeImpl> bees;
    private Beehive hive;

    public SimulationAlgos(List<BeeImpl> bees, Beehive hive) {
        this.bees = bees;
        this.hive = hive;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        for(int i = 0; i < tick; i++) {
            this.tick++;
        }
    }

    public void setTick() {
        this.tick++;
    }

    private void performTickAction() {
        List<BeeImpl> beesToAdd = new ArrayList<>();
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
                int currentFood = this.hive.getFoodCount();
                this.hive.setFoodCount(currentFood + 10);
                useFood(bee);
            }
        }

    }

    private void useFood(BeeImpl bee) {
        int currentFoodCount = this.hive.getFoodCount();
        if (currentFoodCount == 0) {
            killBee(bee);
        }
        else {
            this.hive.setFoodCount(currentFoodCount--);
            System.out.println("Current food stores: "
                    + this.hive.getFoodCount());
        }
    }

    private void killBee(BeeImpl bee) {
        System.out.println("Not enough food to sustain bee, so death...");
        this.bees.remove(bee);
        if (bee.getBeeType().equalsIgnoreCase("queen")) {
            System.out.println("The queen has died.");
            this.hive.killHive("Dead: Queen died of starvation");
        }
    }
}
