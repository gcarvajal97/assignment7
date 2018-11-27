package main.java;

import java.util.ArrayList;

import main.java.builder.Beehive;
import main.java.mediator.BeeImpl;
import main.java.mediator.BeeMediator;
import main.java.mediator.BeeMediatorImpl;
import main.java.singleton.Apiary;


/**
 * Main runner.
 */
public class Main {

    /**
     * Runner.
     * @param args command line args
     */
    public static void main(String[] args) {
        //*
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
        apiary.getSpecifiedHive(0).buildRoom(5, beeList);

    }
}
