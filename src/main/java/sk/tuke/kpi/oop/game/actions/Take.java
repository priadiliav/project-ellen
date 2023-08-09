package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;

public class Take<T extends Keeper> extends AbstractAction<T> {
    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            List<Actor> actorList = Objects.requireNonNull(Objects.
                requireNonNull(getActor()).getScene()).getActors();
            for (Actor actor : actorList) {
                if (actor instanceof Collectible && ((Collectible) actor).intersects(getActor())) {
                    try {
                        getActor().getBackpack().add((Collectible) actor);
                        getActor().getScene().removeActor(actor);
                        break;
                    } catch (Exception ex) {
                        getActor().getScene().getGame().getOverlay()
                            .drawText(ex.getMessage(), 5, 5).showFor(2);
                    }
                }
            }
        }
        setDone(true);
    }
}
