package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{
    @Override
    protected Fireable createBullet() {return new Bullet();}
    public Gun(int initAmmo, int maxAmmo) {
        super(initAmmo, maxAmmo);
    }
}
