package main.java.mediator;

/**
 * Abstract Class for Mediator Pattern: Bees.
 */
public abstract class Bee {
    protected BeeMediator mediator;
    protected String type;
    protected String status;

    public Bee(BeeMediator med, String beeType){
        this.mediator = med;
        this.type = beeType;
        this.status = "Free";
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}