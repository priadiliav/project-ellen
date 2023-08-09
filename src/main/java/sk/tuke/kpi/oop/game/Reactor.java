package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Repairable, Switchable {
    private int temperature;
    private int damage;
    private boolean isOn;
    private Set<EnergyConsumer> devices;
    private Animation normalAnimation;
    private Animation brokenAnimation;
    private Animation hotAnimation;
    private Animation turnOfReactor;
    private Animation exicutionReactor;

    public Reactor() {
        this.temperature = 0;
        this.damage = 0;
        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,
            80,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG);
        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG);
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG);
        this.turnOfReactor = new Animation(
            "sprites/reactor.png",
            80,
            80,
            0F,
            Animation.PlayMode.LOOP_PINGPONG);
        this.exicutionReactor = new Animation("sprites/reactor_extinguished.png");
        turnOff();
        setAnimation(this.normalAnimation);
        ubdateAnimation();
        devices = new HashSet<>();
    }

    private void ubdateAnimation(){
        if(!isOn()){
            if(this.damage >= 100 && this.temperature > 4000){
                setAnimation(this.brokenAnimation);
            }else if(this.damage >= 100){
                setAnimation(this.exicutionReactor);
            } else{
                setAnimation(this.turnOfReactor);
            }
        }else if(this.temperature < 4000){
            setAnimation(this.normalAnimation);
        }else if(this.temperature < 6000){
            setAnimation(this.hotAnimation);
        }else{
            setAnimation(brokenAnimation);
            setDamage(100);
            turnOff();
            if(this.devices != null) {
                this.devices.forEach((s) -> s.setPowered(false));
            }
        }

    }

    public void increaseTemperature(int increment){
        if(increment < 0) return;
        if(isOn() && this.damage < 100) {
            if (this.damage < 33) {
                this.temperature += increment;
            } else if (this.damage <= 66) {
                this.temperature += (int) (increment * 1.5);
            } else {
                this.temperature += (increment * 2);
            }

            if (this.temperature > 2000) {
                int oldDamage = this.damage;
                setDamage((this.temperature - 2000) / 40);
                if(this.damage < oldDamage){
                    this.damage = oldDamage;
                }
            }
        }
        ubdateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if( decrement < 0 ) return;
        if (this.isOn() && this.damage < 100) {
                if (this.damage < 50) {
                    this.temperature -= decrement;
                } else {
                    this.temperature -= (decrement / 2);
                }
                if (this.temperature <= 0) {
                    this.temperature = 0;
                }
        }
        ubdateAnimation();
    }

    public void addDevice(EnergyConsumer device){
        device.setPowered(isOn() && this.damage < 100);
        this.devices.add(device);
    }

    public void removeDevice(EnergyConsumer device){
        device.setPowered(false);
        devices.remove(device);
    }
    @Override
    public boolean repair(){
        if( this.damage > 0 &&
            this.damage < 100){
                int oldDamage = this.damage;
                this.damage = Math.max(0, this.damage - 50);
                setTemperature(Math.min(this.temperature, Math.max((((oldDamage - 50) * 40) + 2000), 0)));
                ubdateAnimation();
                return true;
        }
        return false;
    }

    public boolean extinguish(){
        if(this.damage == 100 && this.temperature > 4000){
            setAnimation(this.exicutionReactor);
            this.temperature = 4000;
            ubdateAnimation();
            return true;
        }
        return false;
    }

    @Override
    public void turnOn(){
        this.isOn = this.damage < 100;
        if(this.devices != null)
            this.devices.forEach((s) -> s.setPowered(true));
        ubdateAnimation();
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        if(this.devices != null)
            this.devices.forEach((s) -> s.setPowered(false));
        ubdateAnimation();
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }
}
