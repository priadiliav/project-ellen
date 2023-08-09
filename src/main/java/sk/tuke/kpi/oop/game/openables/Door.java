package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Door extends AbstractActor implements Usable<Actor>, Openable{
    public enum Orientation {
        HORIZONTAL, VERTICAL
    }
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private Animation animationOpen;
    private boolean isOpen;
    private Animation animationClose;

    private Orientation orientation;

    public Door(String name, Orientation orientation) {
        super(name);
        if (orientation.equals(Orientation.HORIZONTAL)) {
            this.animationClose = new Animation(
                "sprites/hdoor.png",
                32,
                16,
                0.1F,
                Animation.PlayMode.ONCE);
            this.animationOpen = new Animation(
                "sprites/hdoor.png",
                32,
                16,
                0.1f,
                Animation.PlayMode.ONCE_REVERSED);
        }else if(orientation.equals(Orientation.VERTICAL)){
            this.animationClose = new Animation(
                "sprites/vdoor.png",
                16,
                32,
                0.1F,
                Animation.PlayMode.ONCE);
            this.animationOpen = new Animation(
                "sprites/vdoor.png",
                16,
                32,
                0.1f,
                Animation.PlayMode.ONCE_REVERSED);
        }
        this.orientation = orientation;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void useWith(Actor actor) {
        if(this.isOpen){
            close();
            return;
        }
        System.out.println("opened");
        open();
    }

    @Override
    public void open() {
        setOpen(true);
        setAnimation(this.animationOpen);
        int xTile = this.getPosX()/16, yTile = this.getPosY()/16;
        if(this.orientation.equals(Orientation.HORIZONTAL)){
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile).setType(MapTile.Type.CLEAR);
            Objects.requireNonNull(getScene()).getMap().getTile(xTile+1, yTile).setType(MapTile.Type.CLEAR);
        }else if(this.orientation.equals(Orientation.VERTICAL)){
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile).setType(MapTile.Type.CLEAR);
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile+1).setType(MapTile.Type.CLEAR);
        }
        this.animationOpen.stop();
        Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_OPENED, this);
    }
    @Override
    public void close() {
        setOpen(false);
        setAnimation(this.animationClose);
        int xTile = this.getPosX()/16, yTile = this.getPosY()/16;
        if(this.orientation.equals(Orientation.HORIZONTAL)){
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile).setType(MapTile.Type.WALL);
            Objects.requireNonNull(getScene()).getMap().getTile(xTile+1, yTile).setType(MapTile.Type.WALL);
        }else if(this.orientation.equals(Orientation.VERTICAL)){
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile).setType(MapTile.Type.WALL);
            Objects.requireNonNull(getScene()).getMap().getTile(xTile, yTile+1).setType(MapTile.Type.WALL);
        }
        this.animationClose.stop();
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }

    public Orientation getOrientation() {return orientation;}
}
