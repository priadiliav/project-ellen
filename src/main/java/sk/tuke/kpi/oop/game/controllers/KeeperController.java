package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;

public class KeeperController implements KeyboardListener {
    private Keeper keeper;
    @Override
    public void keyPressed(Input.Key key) {
        switch (key){
            case BACKSPACE:
                new Drop<>().scheduleFor(this.keeper);
                break;
            case ENTER:
                new Take<>().scheduleFor(this.keeper);
                break;
            case S:
                new Shift<>().scheduleFor(this.keeper);
                break;
            case U:
                usingWithU();
                break;
            case B:
                usingWithB();
                break;
            default:
        }
    }
    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }
    private void usingWithU(){
        for(Actor actor : Objects.requireNonNull(this.keeper.getScene()).getActors()){
            if(actor != this.keeper && actor instanceof Usable &&
                !(actor instanceof LockedDoor) && this.keeper.intersects(actor)){
                new Use<>((Usable<?>)actor).scheduleForIntersectingWith(this.keeper);
            }
        }
    }
    private void usingWithB(){
        if(this.keeper != null && this.keeper.getBackpack().peek() instanceof Usable){
            new Use<>((Usable<?>)this.keeper.getBackpack().peek()).scheduleForIntersectingWith(this.keeper);
        }
    }
}
