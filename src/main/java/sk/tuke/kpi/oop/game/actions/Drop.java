package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Drop<T extends Keeper> extends AbstractAction<T> {
    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            Collectible collectible = getActor().getBackpack().peek();
            if (collectible != null) {
                getActor().getBackpack().remove(collectible);
                Objects.requireNonNull(getActor().getScene()).
                    addActor(collectible, getActor().getPosX() + 8, getActor().getPosY() + 8);
            }
        }
        setDone(true);
    }
}
