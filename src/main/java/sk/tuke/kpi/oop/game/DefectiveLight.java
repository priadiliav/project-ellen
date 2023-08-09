package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

//test
public class DefectiveLight extends Light implements Repairable {
    private Disposable disposable;
    private boolean isFixed = false;
    private int getRandomInteger(int max, int min){
        return ((int) (Math.random() * (max - min))) + min;
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.disposable = new Loop<>(new Invoke<>(this::defectLight)).scheduleFor(this);
    }
    public void defectLight(){
        this.isFixed = false;
        boolean defect = getRandomInteger(20, 0) == 1;
        if(defect)
            super.toggle();
    }
    public DefectiveLight(){
        super();
        this.isFixed = false;
    }
    private void broken(){
        this.isFixed = false;
        this.disposable = new Loop<>(new Invoke<>(this::defectLight)).scheduleFor(this);
    }
    private void fix(){
        this.isFixed = true;
        //setAnimation(new Animation("sprites/light_on.png"));
    }
    @Override
    public boolean repair() {
        if(this.isFixed) return false;
        this.disposable.dispose();
        new ActionSequence<>(
            new Invoke<>(this::fix),
            new Wait<>(11),
            new Invoke<>(this::broken)
        ).scheduleFor(this);
        return true;
    }
}

//asd
