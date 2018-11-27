package main.java.main.java;

import java.util.ArrayList;

import main.java.main.java.builder.Beehive;
import main.java.main.java.mediator.BeeImpl;
import main.java.main.java.mediator.BeeMediator;
import main.java.main.java.mediator.BeeMediatorImpl;
import main.java.main.java.singleton.Apiary;
import main.java.main.java.strategy.SimulationAlgos;


/**
 * Main runner.
 */
public class Main {

    /**
     * Runner.
     * @param args command line args
     */
    public static void main(String[] args) {
        //Singleton example
        Apiary apiary = Apiary.getApiary();
        BeeMediatorImpl mediator = new BeeMediatorImpl();

        apiary.addBeehive();
        ArrayList<BeeImpl> beeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            beeList.add(new BeeImpl(mediator, "Drone"));
            beeList.add(new BeeImpl(mediator, "Forager"));
            beeList.add(new BeeImpl(mediator, "Worker"));
        }
        beeList.add(new BeeImpl(mediator, "Queen"));
        System.out.println("\n");
        Beehive hive = apiary.getSpecifiedHive(0);
        hive.buildRoom(5, beeList);
        Apiary secondApiary = Apiary.getApiary();
        if (hive == secondApiary.getSpecifiedHive(0)) {
            System.out.println("Singleton successful");
        }
        
        //Example strategy/mediator patterns
        SimulationAlgos algos = new SimulationAlgos(beeList, hive);
        System.out.println("Starting tick: " + algos.getTick());
        algos.setTick();
        System.out.println("Performing tick: " + algos.getTick());
        algos.performTickAction();
        
        
        //Example Builder Pattern
        Beehive newHive = new Beehive.Builder().setMaxBeeCount(100)
                .setSpecies("Killer")
                .setWorkerCount(50)
                .setWarriorCount(10)
                .setKillCount(0)
                .setHiveKillCount(0)
                .setTickCount(1)
                .setHiveStatus("Alive")
                .setRoomCount(5).build();
        System.out.println("This new hive's status is: " + newHive.getStatus());
    }
    
}
