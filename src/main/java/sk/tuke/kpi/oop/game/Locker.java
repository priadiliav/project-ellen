package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;


public class Locker extends AbstractActor implements Usable<Actor> {
    private boolean neverUsed;

    public Locker(){
        System.out.println("hello");
        setAnimation(new Animation("sprites/locker.png"));
        setNeverUsed(true);
    }

    public boolean isNeverUsed() {
        return this.neverUsed;
    }

    public void setNeverUsed(boolean neverUsed) {
        this.neverUsed = neverUsed;
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void useWith(Actor actor) {
        System.out.println("test use With of locker");
        if(this.isNeverUsed()){
            setNeverUsed(false);
            Objects.requireNonNull(getScene()).removeActor(this);
            Objects.requireNonNull(getScene()).addActor(new Hammer(),
                this.getPosX(), this.getPosY());
        }
    }
}
