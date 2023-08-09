package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.Objects;

public class Ventilator extends AbstractActor implements Repairable {
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);
    private Animation animation;
    private boolean repaired;

    public Ventilator(){
        this.animation = new Animation("sprites/ventilator.png",
            32,32,0.1f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(this.animation);
        this.animation.pause();
        repaired = false;
    }
    @Override
    public boolean repair() {
        if(!this.repaired){
            this.animation.play();
            Objects.requireNonNull(getScene()).getMessageBus().publish(VENTILATOR_REPAIRED, this);
            return true;
        }
        return false;
    }

    public Ventilator(String name) {
        super(name);
    }
}
