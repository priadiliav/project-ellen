package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;


public class MissionImpossible implements SceneListener {
    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if(name != null && name.equals("access card")){
                return new AccessCard();
            }else if(name != null && name.equals("locker")){
                return new Locker();
            }else if(name != null && name.equals("ventilator")){
                return new Ventilator();
            }else if(name != null && name.equals("ellen")){
                return new Ripley();
            }
            return null;
        }
    }
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        MovableController movableController = new MovableController(scene.getFirstActorByType(Movable.class));
        Disposable dis = scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(scene.getFirstActorByType(Ripley.class));
        Disposable dis2 = scene.getInput().registerListener(keeperController);

        Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getBackpack().add(new Hammer());

        SceneListener.super.sceneInitialized(scene);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("|    Energy: " +
            Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getHealth().getValue(), 120, yTextPos);
        scene.getGame().getOverlay().drawText("|    Ammo: " +
            Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getFirearm().getAmmo(), 320, yTextPos);
        scene.getGame().pushActorContainer(Objects.requireNonNull(scene.
                                                    getFirstActorByType(Ripley.class)).getBackpack());
    }
}
