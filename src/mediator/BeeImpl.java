package mediator;

/**
 * Concrete Mediator User implementation for Bees
 */
public class BeeImpl extends Bee {

    public BeeImpl(BeeMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String action){
        System.out.println(this.type + ": Performing action: " + action);
        mediator.sendMessage(action, this);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.type + ": Received action");
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeeType() {
        return this.type;
    }
}
