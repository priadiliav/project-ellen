package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable{
    private Animation animationOn;
    private Reactor reactor;
    private boolean isOn = false;

    public Cooler(Reactor reactor){
        this.animationOn = new Animation(
            "sprites/fan.png",
            32,
            32,
            0.2F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        setAnimation(this.animationOn);
        updateAnimation();
        this.reactor = reactor;
    }

    public Reactor getReactor(){
        return this.reactor;
    }

    private void updateAnimation(){
        if(this.isOn){
            this.animationOn.play();
        }else{
            this.animationOn.pause();
        }
    }

    @Override
    public void turnOn(){
        this.isOn = true;
        updateAnimation();
    }

    @Override
    public void turnOff(){
        this.isOn = false;
        updateAnimation();
    }

    @Override
    public boolean isOn(){
        return this.isOn;
    }

    protected void coolReactor(){
        if(this.isOn() && this.reactor != null) {
            this.reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
