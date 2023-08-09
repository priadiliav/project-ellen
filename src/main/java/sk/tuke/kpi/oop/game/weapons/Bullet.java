package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable{
    public Bullet() {
        setAnimation(new Animation("sprites/bullet.png",16, 16));
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    @Override
    public void collidedWithWall() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }


    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        System.out.println("test");
        new Loop<>(
            new Invoke<>(this::search)
        ).scheduleFor(this);
    }

    private void search(){
        for(Actor actor : Objects.requireNonNull(getScene()).getActors()) {
            if (!(actor instanceof Ripley) && actor instanceof Alive && actor.intersects(this)) {
                ((Alive) actor).getHealth().drain(1);
                getScene().removeActor(this);
                break;
            }
        }
    }
}
