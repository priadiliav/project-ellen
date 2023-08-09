package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int initHealth;
    private List<ExhaustionEffect> list;
    private int maxHealth;
    public Health(int initHealth, int maxHealth){
        this.initHealth = initHealth;
        this.maxHealth = maxHealth;
        this.list = new ArrayList<>();
    }

    public Health(int initHealth){
        this.initHealth = initHealth;
        this.maxHealth = initHealth;
        this.list = new ArrayList<>();
    }

    public int getValue(){
        return this.initHealth;
    }

    public void refill(int amount){
        this.initHealth = Math.min(this.maxHealth, this.initHealth + amount);
    }

    public void restore(){
        this.initHealth = this.maxHealth;
    }

    public void drain(int amount){
        if(this.initHealth != 0 && this.initHealth > amount){
            System.out.println("test passed drain");
            this.initHealth = this.initHealth - amount;
        }else if(this.initHealth != 0){
            exhaust();
        }
    }

    void exhaust(){
        if(this.initHealth != 0) {
            this.initHealth = 0;
            if (list != null) for (ExhaustionEffect exhaustionEffect : this.list)
                    exhaustionEffect.apply();
        }
    }

    public void onExhaustion(ExhaustionEffect effect) {
        if(effect != null) list.add(effect);
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void setInitHealth(int initHealth) {
        this.initHealth = initHealth;
    }
}
