package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;
import java.util.Objects;

public class Fire<A extends Armed> extends AbstractAction<A> {
    public Fire(){}
    @Override
    public void execute(float deltaTime) {
        if(deltaTime >= 0 && !isDone() && getActor() != null){
            Fireable fireable = getActor().getFirearm().fire();
            if(fireable != null){
                getActor().getAnimation().setRotation(getActor().getAnimation().getRotation());
                Objects.requireNonNull(getActor().getScene()).addActor(fireable, getActor().getPosX() + 8, getActor().getPosY() + 8);
                new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()),Float.MAX_VALUE).scheduleFor(fireable);
            }
        }
        super.setDone(true);
    }
}
