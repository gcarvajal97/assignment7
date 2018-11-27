package main.java.main.java.mediator;

import java.util.ArrayList;

/**
 * Concrete Mediator implementation for Bees.
 */
public class BeeMediatorImpl implements BeeMediator {
    private ArrayList<Bee> bees;

    /**
     * Default constructor.
     */
    public BeeMediatorImpl() {
        this.bees = new ArrayList<>();
    }

    /**
     * Adds a new bee listener to the mediator.
     * @param bee New Bee
     */
    @Override
    public void addBee(Bee bee) {
        this.bees.add(bee);
    }

    /**
     * Sends action that a bee took to all other bees.
     * @param msg action be took
     * @param bee which bee sent the message
     */
    @Override
    public void sendMessage(String msg, Bee bee) {
        for (Bee b : this.bees) {
            //message should not be received by the user sending it
            if (b != bee) {
                b.receive(msg);
            }
        }
    }
}
