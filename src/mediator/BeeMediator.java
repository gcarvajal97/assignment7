package mediator;

/**
 * Mediator interface for Bees
 */
public interface BeeMediator {

    public void sendMessage(String msg, Bee bee);

    void addBee(Bee bee);
}
