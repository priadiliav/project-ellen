package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.oop.game.items.AccessCard;

public class LockedDoor extends Door{
    private AccessCard accessCard;
    private boolean locked;

    public LockedDoor(String name, Orientation orientation){
        super(name, orientation);
        setLocked(true);
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked(){
        return this.locked;
    }

    public void lock(){
        setLocked(true);
        super.close();
    }

    public void unlock() {
        setLocked(false);
        super.open();
    }
}
