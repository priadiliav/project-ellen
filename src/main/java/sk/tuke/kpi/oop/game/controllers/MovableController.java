package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import java.util.*;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Move<Movable> move;
    private Input.Key[] list;
    private Disposable disposable;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );

    @Override
    public void keyPressed(Input.Key key) {
        if(this.keyDirectionMap.containsKey(key)){
            if(this.list[0] != null){
                this.list[1] = key;
            }else{
                this.list[0] = key;
            }
            pressed(key);
        }
    }

    private void pressed(Input.Key key){
        if(key != null) {
            if (this.list[1] != null && this.list[0] != null) {
                this.movable.startedMoving(this.keyDirectionMap.get(this.list[1]).combine(this.keyDirectionMap.get(this.list[0])));
                move = new Move<>(this.keyDirectionMap.get(this.list[1]).combine(this.keyDirectionMap.get(this.list[0])), Float.MAX_VALUE);
            } else {
                this.movable.startedMoving(this.keyDirectionMap.get(key));
                move = new Move<>(this.keyDirectionMap.get(key), Float.MAX_VALUE);
            }
            if(this.disposable != null){this.disposable.dispose(); this.move.stop();}
            disposable = move.scheduleFor(this.movable);
        }
    }

    @Override
    public void keyReleased(Input.Key key) {
        if(this.keyDirectionMap.containsKey(key)){
            this.move.stop();
            for(int i = 0; i < list.length; i++){
                if(list[i] != null && list[i].equals(key)){
                    list[i] = null;
                }
            }
            pressed(list[0] != null ? list[0] : list[1] != null ? list[1] : null);
        }
    }

    //    @Override
//    public void keyReleased(Input.@NotNull Key key) {
//        if(this.keyDirectionMap.containsKey(key)){
//            System.out.println("its stop");
//            this.disposable.dispose();
//            this.movable.stoppedMoving();
//
//        }
//    }

    public MovableController(Movable movable){
        this.movable = movable;
        this.list = new Input.Key[2];
    }
}
