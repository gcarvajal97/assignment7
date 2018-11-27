/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.sun.tools.javac.Main;

import main.java.builder.Beehive;
import main.java.mediator.BeeImpl;
import main.java.mediator.BeeMediatorImpl;
import main.java.singleton.Apiary;
import main.java.strategy.SimulationAlgos;

/**
 * @author gcarvaja
 *
 */
public class AssignmentTestCases {
    private Apiary apiary;
    private int hiveNumber = 0;
    ArrayList<BeeImpl> beeList;

    /**
     * @throws java.lang.Exception
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
        this.apiary.getSpecifiedHive(this.hiveNumber).buildRoom(5, beeList);
        this.hiveNumber++;
    }
    
    @Test
    public void testAddRoom() {
        int initialCount = this.apiary.getSpecifiedHive(hiveNumber - 1).getRoomCount();
        this.apiary.getSpecifiedHive(hiveNumber - 1).buildRoom();
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1).getRoomCount() 
                == initialCount++);
    }
    
    @Test
    public void testContinueSimulation() {
        this.apiary.addBeehive();
        this.hiveNumber++;
        assertTrue(this.apiary.continueSimulation());
    }
    
    @Test
    public void testKillHiveEndSimulation( ) {
        this.apiary.getSpecifiedHive(this.hiveNumber - 1).setFoodCount(0);
        SimulationAlgos algos = new SimulationAlgos(this.beeList, this.apiary.getSpecifiedHive(this.hiveNumber - 1));
        algos.performTickAction();
        assertFalse(this.apiary.continueSimulation());
    }
    
    
    @Test
    public void testSimulationAlgos() {
        this.apiary.addBeehive();
        this.hiveNumber++;
        SimulationAlgos algos = new SimulationAlgos(this.beeList, this.apiary.getSpecifiedHive(this.hiveNumber - 1));
        algos.performTickAction();
        assertFalse(this.apiary.getSpecifiedHive(hiveNumber - 1).getStatus().equals("Dead: Queen died of starvation"));
    }

    @Test
    public void testSimulationAlgosKillBee() {
        this.apiary.getSpecifiedHive(this.hiveNumber - 1).setFoodCount(0);
        SimulationAlgos algos = new SimulationAlgos(this.beeList, this.apiary.getSpecifiedHive(this.hiveNumber - 1));
        algos.performTickAction();
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1).getStatus().equals("Dead: Queen died of starvation"));
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1).getFoodCount() == 0);
    }
    
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
        assertTrue(this.apiary.getSpecifiedHive(hiveNumber - 1).getStatus().equals("Alive"));
    }

}
