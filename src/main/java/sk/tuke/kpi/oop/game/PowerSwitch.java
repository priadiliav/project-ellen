package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Switchable switchableClass;
    public void toggle(){
        if(this.switchableClass != null) {
            if (this.switchableClass.isOn()) {
                this.switchableClass.turnOff();
            } else {
                this.switchableClass.turnOn();
            }
        }
    }

    public Switchable getDevice(){
        return this.switchableClass;
    }

    public void switchOn() {
        if(this.switchableClass != null) {
            this.switchableClass.turnOn();
            getAnimation().setTint(Color.WHITE);
        }
    }

    public void switchOff() {
        if(this.switchableClass != null) {
            this.switchableClass.turnOff();
            getAnimation().setTint(Color.GRAY);
        }
    }

    public PowerSwitch(Switchable switchableClass){
        this.switchableClass = switchableClass;
        setAnimation(new Animation("sprites/switch.png"));
    }
}
