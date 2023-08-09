package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{
    public FireExtinguisher(int countOfUsing){
        super(countOfUsing);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }
    public FireExtinguisher(){
        this(1);
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }

    @Override
    public void useWith(Reactor reactor) {
        if(reactor != null && reactor.extinguish()) {
            super.useWith(reactor);
        }
    }
}
