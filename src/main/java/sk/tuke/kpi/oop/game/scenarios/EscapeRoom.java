package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;

public class EscapeRoom implements SceneListener {
    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
          //  return null;
            if(name != null && name.equals("ellen")){
                return new Ripley();
            }else if(name != null && name.equals("ammo")){
                return new Ammo();
            }else if(name != null && name.equals("energy")){
                return new Energy();
            }else if(name != null && name.equals("front door")){
                return new Door("front door", Door.Orientation.VERTICAL);
            }else if(name != null && name.equals("back door")){
                return new Door("back door", Door.Orientation.HORIZONTAL);
            }else if(name != null && name.equals("alien")){
                return new Alien();
            }
            return null;
        }
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        //scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, );
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {

        MovableController movableController = new MovableController(scene.getFirstActorByType(Movable.class));
        Disposable dis = scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(scene.getFirstActorByType(Ripley.class));
        Disposable dis2 = scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(scene.getFirstActorByType(Ripley.class));
        Disposable di3 = scene.getInput().registerListener(shooterController);
        //Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).decreaseEnergy();
        System.out.println(Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getHealth().getValue());

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> dis2.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> dis.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> scene.cancelActions(Objects.requireNonNull(scene.
                        getFirstActorByType(Ripley.class))));
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> dis2.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> dis.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> scene.cancelActions(Objects.requireNonNull(scene.
            getFirstActorByType(Ripley.class))));
       // scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooterCon.dispose());

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        scene.getGame().getOverlay().drawText("|    Energy: " +
            Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getHealth().getValue(), 120, 40);
        scene.getGame().getOverlay().drawText("|    Ammo: " +
            Objects.requireNonNull(scene.getFirstActorByType(Ripley.class)).getFirearm().getAmmo(), 320, 40);
        scene.getGame().pushActorContainer(Objects.requireNonNull(scene.
            getFirstActorByType(Ripley.class)).getBackpack());
    }
}
