package sk.tuke.kpi.oop.game;

public class SmartCooler extends Cooler{
    public SmartCooler(Reactor reactor) {
        super(reactor);
    }
    @Override
    protected void coolReactor() {
        if(super.getReactor() != null) {
            if (super.getReactor().getTemperature() >= 2500) {
                turnOn();
            } else if (super.getReactor().getTemperature() <= 1500) {
                turnOff();
            }
            super.coolReactor();
        }
    }
}
