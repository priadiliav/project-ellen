package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;

import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Objects;
public class ChainBomb extends TimeBomb{

    public ChainBomb(float seconds) {
        super(seconds);
    }
    @Override
    public void activate() {
        super.activate();
        new ActionSequence<>(
            new Wait<>(super.getSeconds()),
            new Invoke<>(this::activateHelp)
        ).scheduleFor(this);
    }
//    private void activateHelp(){
//        List<Actor> actors = Objects.requireNonNull(getScene()).getActors();
//        Ellipse2D ellipse2D = new Ellipse2D.Float(this.getPosX() + 8 - 50, this.getPosY() + 8 + 50, 100, 100);
//        for(Actor actor : actors){
//            if(actor != this && actor instanceof ChainBomb && !((ChainBomb) actor).isActivated()
//                 && ellipse2D.intersects(actor.getPosX(), actor.getPosY()+16, 16, 16)){
//                ((ChainBomb) actor).activate();
//            }
//        }
//    }

    // by vector
//    private void activateHelp(){
//        List<Actor> actors = Objects.requireNonNull(getScene()).getActors();
//        int actorPosX, actorPosY;
//        for(Actor actor : actors){
//            if(actor != this && actor instanceof ChainBomb && !((ChainBomb) actor).isActivated()
//                && ((this.getPosX() + 8 + 50 >= actor.getPosX() + 8) && (this.getPosX() + 8 - 50 <= actor.getPosX() + 8)) &&
//                        ((this.getPosY() + 8 + 50 >= actor.getPosY() + 8) && (this.getPosY() + 8 - 50 <= actor.getPosY() + 8))){
//                ((ChainBomb) actor).activate();
//            }
//        }
//    }

    private void activateHelp(){
        List<Actor> actors = Objects.requireNonNull(getScene()).getActors();
        Ellipse2D ellipse2D = new Ellipse2D.Float(this.getPosX() - 50, this.getPosY()-50, 101, 93);
        for(Actor actor : actors){
            if(actor != this && actor instanceof ChainBomb && !((ChainBomb) actor).isActivated()
                && ellipse2D.intersects(actor.getPosX(), actor.getPosY()-8, 16, 16)){
                System.out.println("yes");
                ((ChainBomb) actor).activate();
            }
        }
    }

}
