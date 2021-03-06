package main.java.main.java.mediator;

/**
 * Mediator interface for Bees.
 */
public interface BeeMediator {

    public void sendMessage(String msg, Bee bee);

    void addBee(Bee bee);
}
