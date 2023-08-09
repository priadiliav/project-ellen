package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.concurrent.locks.Lock;


public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor>{
    public AccessCard(){
        setAnimation(new Animation("sprites/key.png"));
    }
    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }

    @Override
    public void useWith(LockedDoor door) {
        if(door != null){
        if(door.isLocked()){
            door.unlock();
        }else{
            door.lock();
        }}
    }
}
