package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable, Enemy, Alive {
    private Health health;
    public Alien(){
        setAnimation(new Animation(
            "sprites/alien.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.LOOP_REVERSED));
        health = new Health(100, 100);
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }
}
