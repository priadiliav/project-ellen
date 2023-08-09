package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class Firearm extends AbstractActor {
    private int initAmmo;
    private int maxAmmo;

    public Firearm(int initAmmo, int maxAmmo){
        this.initAmmo = initAmmo;
        this.maxAmmo = maxAmmo;
    }

    public Firearm(int initAmmo){
        this.initAmmo = initAmmo;
        this.maxAmmo = initAmmo;
    }

    public int getAmmo(){
        return this.initAmmo;
    }
    public void reload(int newAmmo){
        this.initAmmo = Math.min(this.maxAmmo, this.initAmmo + newAmmo);
    }

    public Fireable fire(){
        if(this.initAmmo > 0){ this.initAmmo -= 1;
           return createBullet();
        }
        return null;
    }

    public int getInitAmmo() {
        return initAmmo;
    }

    public void setInitAmmo(int initAmmo) {
        this.initAmmo = initAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    protected abstract Fireable createBullet();
}
