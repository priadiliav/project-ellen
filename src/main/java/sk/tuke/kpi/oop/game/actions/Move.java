package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move<A extends Movable> implements Action<Actor> {
    private Direction direction;
    private int itsFirstTime;
    private Movable actor;
    private float duration;
    private boolean isDone;

    public Move(Direction direction, float duration) {
        this.itsFirstTime = 0;
        this.direction = direction;
        this.duration = duration;
        this.isDone = false;
    }

    public Move(Direction direction) {
        this.itsFirstTime = 0;
        this.direction = direction;
        this.isDone = false;
        this.duration = 0;
    }


    @Override
    public @Nullable Movable getActor() {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable Actor actor) {
        this.actor = (Movable) actor;
    }

    public void setActor(@Nullable Movable actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public void reset() {
        if(this.actor != null) {
            actor.stoppedMoving();
            this.isDone = false;
            this.itsFirstTime = 0;
        }
    }

    public int getItsFirsttime() {
        return this.itsFirstTime;
    }

    public void setItsFirstTime(int itsFirstTime) {
        this.itsFirstTime = itsFirstTime;
    }

    public void execute(float deltaTime) {
        if(actor != null && deltaTime >= 0 && !isDone()) {
            if (getItsFirsttime() == 0) {
                actor.startedMoving(this.direction);
                setItsFirstTime(1);
            }

            this.duration = this.duration - deltaTime;

            if (this.duration > 0) {
                this.actor.setPosition(this.actor.getPosX() + this.direction.getDx() * this.actor.getSpeed(),
                    this.actor.getPosY() + this.direction.getDy() * this.actor.getSpeed());
                if(Objects.requireNonNull(Objects.requireNonNull(getActor()).getScene()).getMap().intersectsWithWall(this.actor)){
                    this.actor.setPosition(this.actor.getPosX() - this.direction.getDx() * this.actor.getSpeed(),
                        this.actor.getPosY() - this.direction.getDy() * this.actor.getSpeed());
                    actor.collidedWithWall();
                }
            } else {
                stop();
            }
        }
    }
    public void stop(){
        if(this.actor != null) {
            this.isDone = true;
            actor.stoppedMoving();
        }
    }
}

