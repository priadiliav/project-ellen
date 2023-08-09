package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Armed> {
    public Ammo(){
        setAnimation(new Animation("sprites/ammo.png"));
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

    @Override
    public void useWith(Armed actor) {
        if(actor != null && actor.getFirearm().getAmmo() != 500){
            actor.getFirearm().reload(50);
            Objects.requireNonNull(getScene()).removeActor(this);
        }
    }
}
