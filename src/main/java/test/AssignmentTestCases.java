/**
 * 
 */
package main.java.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import main.java.main.java.builder.Beehive;
import main.java.main.java.mediator.BeeImpl;
import main.java.main.java.mediator.BeeMediatorImpl;
import main.java.main.java.singleton.Apiary;
import main.java.main.java.strategy.SimulationAlgos;

import org.junit.Before;
import org.junit.Test;



/**
 * Unit tests for Bee Apiary.
 * @author gcarvaja
 *
 */
public class AssignmentTestCases {
    private Apiary apiary;
    private int hiveNumber = 0;
    private ArrayList<BeeImpl> beeList;

    /**
     * Setups up apiary with a new beehive.
     * @throws java.lang.Exception when an error occurs in setting up tests
     */
    @Before
    public void setUp() throws Exception {
        this.apiary = Apiary.getApiary();
        BeeMediatorImpl mediator = new BeeMediatorImpl();

        this.apiary.addBeehive();
        this.beeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.beeList.add(new BeeImpl(mediator, "Drone"));
            this.beeList.add(new BeeImpl(mediator, "Forager"));
            this.beeList.add(new BeeImpl(mediator, "Worker"));
        }
        this.beeList.add(new BeeImpl(mediator, "Queen"));
        this.apiary.getSpecifiedHive(
                this.hiveNumber).buildRoom(5, beeList);
        this.hiveNumber++;
    }

    /**
     * Tests that a room can be added properly.
     */
    @Test
    public void testAddRoom() {
        int initialCount = this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getRoomCount();
        this.apiary.getSpecifiedHive(hiveNumber - 1).buildRoom();
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getRoomCount() == initialCount + 1);
    }

    /**
     * Tests that the continue check is working as expected.
     */
    @Test
    public void testContinueSimulation() {
        this.apiary.addBeehive();
        this.hiveNumber++;
        assertTrue(this.apiary.continueSimulation());
    }

    /**
     * Tests that the simulation successfully ends when <= 1 hives remain.
     */
    @Test
    public void testKillHiveEndSimulation() {
        this.apiary.getSpecifiedHive(this.hiveNumber - 1).setFoodCount(0);
        SimulationAlgos algos =
                new SimulationAlgos(
                        this.beeList, this.apiary.getSpecifiedHive(
                                this.hiveNumber - 1));
        algos.performTickAction();
        assertFalse(this.apiary.continueSimulation());
    }

    /**
     * Tests that the tick actions are performed properly for given bees.
     */
    @Test
    public void testSimulationAlgos() {
        this.apiary.addBeehive();
        this.hiveNumber++;
        SimulationAlgos algos =
                new SimulationAlgos(
                        this.beeList, this.apiary.getSpecifiedHive(
                                this.hiveNumber - 1));
        algos.performTickAction();
        assertFalse(this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getStatus().equals("Dead: Queen died of starvation"));
    }

    /**
     * Tests that bees will be killed upon not having enough food, and hive
     * is dead when queen is killed.
     */
    @Test
    public void testSimulationAlgosKillBee() {
        this.apiary.getSpecifiedHive(this.hiveNumber - 1).setFoodCount(0);
        SimulationAlgos algos =
                new SimulationAlgos(
                        this.beeList, this.apiary.getSpecifiedHive(
                                this.hiveNumber - 1));
        algos.performTickAction();
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getStatus().equals("Dead: Queen died of starvation"));
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getFoodCount() == 0);
    }

    /**
     * Tests that an external hive with give builder parameters can be made.
     */
    @Test
    public void testSimulateCreateOutsideHive() {
        Beehive hive = new Beehive.Builder().setMaxBeeCount(100)
                .setSpecies("Gatherer")
                .setWorkerCount(50)
                .setWarriorCount(10)
                .setKillCount(0)
                .setHiveKillCount(0)
                .setTickCount(1)
                .setHiveStatus("Alive")
                .setRoomCount(5).build();
        hive.setFoodCount(100);
        this.apiary.addBeehive(hive);
        this.hiveNumber++;
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1)
                .getStatus().equals("Alive"));
    }

}
