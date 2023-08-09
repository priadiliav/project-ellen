package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {
    private float seconds;
    private boolean activated;
    private Animation bombActivated;

    private Animation smallExplosion;

    public boolean isActivated(){
        return this.activated;
    }
    public void setActivated(boolean activated){
        this.activated = activated;
    }
    public float getSeconds() {return this.seconds;}
    public TimeBomb(float seconds){
        setAnimation(new Animation("sprites/bomb.png"));
        this.bombActivated = new Animation(
            "sprites/bomb_activated.png",
            16,
            16,
            0.2F,
            Animation.PlayMode.LOOP_PINGPONG);
        this.smallExplosion = new Animation(
            "sprites/small_explosion.png",
            16,
            16,
            0.1F,
            Animation.PlayMode.ONCE);
        this.seconds = seconds;
        this.activated = false;
    }

    public void activate(){
        this.setActivated(true);
        setAnimation(this.bombActivated);
        new ActionSequence<>(
            new Wait<>(this.seconds),
            new Invoke<>(this::explosion),
            new Wait<>(smallExplosion.getFrameCount() * 0.1F),
            new Invoke<>(this::removeBomb)
        ).scheduleFor(this);
    }
    private void removeBomb(){
        Objects.requireNonNull(getScene()).removeActor(this);
    }
    private void explosion(){
        setAnimation(this.smallExplosion);
    }

    // preco robime tak ako robimo.
}
