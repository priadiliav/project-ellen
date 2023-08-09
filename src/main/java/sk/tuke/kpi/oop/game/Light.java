package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    private Animation lightOn;
    private Animation lightOf;

    private boolean powered = false;
    private boolean isOn = false;

    public Light(){
        this.lightOn = new Animation("sprites/light_on.png");
        this.lightOf = new Animation("sprites/light_off.png");
        updateLight();
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        updateLight();
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        updateLight();
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }

    private void updateLight() {
        if (this.powered) {
            if (isOn()) {
                setAnimation(lightOn);
            } else {
                setAnimation(lightOf);
            }
        } else {
            setAnimation(lightOf);
        }
    }

    public void toggle(){
        if(isOn()){
            turnOff();
        }else{
            turnOn();
        }
        updateLight();
    }

    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
        updateLight();
    }
}
