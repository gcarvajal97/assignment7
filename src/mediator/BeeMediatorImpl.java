package mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Mediator implementation for Bees
 */
public class BeeMediatorImpl implements BeeMediator {
    private List<Bee> bees;

    public BeeMediatorImpl(){
        this.bees = new ArrayList<>();
    }

    @Override
    public void addBee(Bee bee){
        this.bees.add(bee);
    }

    @Override
    public void sendMessage(String msg, Bee bee) {
        for(Bee b : this.bees){
            //message should not be received by the user sending it
            if(b != bee){
                b.receive(msg);
            }
        }
    }

    public List<Bee> getBees() {
        return bees;
    }
}
