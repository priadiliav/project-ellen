package sk.tuke.kpi.oop.game.scenarios;

import com.sun.jdi.VMOutOfMemoryException;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.oop.game.ChainBomb;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;

import java.awt.image.renderable.RenderableImage;
import java.util.Objects;

public class FirstSteps implements SceneListener {
    private Ripley ripley;
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("|    Energy: " + this.ripley.getHealth().getValue(), 120, yTextPos);
        scene.getGame().getOverlay().drawText("|    Ammo: " + this.ripley.getFirearm().getAmmo(), 320, yTextPos);
        scene.getGame().pushActorContainer(this.ripley.getBackpack());
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        //Move<Ripley> move = new Move<>(Direction.WEST);
        //scene.addActor(Objects.requireNonNull(move.getActor()));

        //System.out.println(move.getActor().getPosX() + " " + move.getActor().getPosY());
//        this.ripley = new Ripley();
//        ripley.setEnergy(50);
//        ripley.setAmmo(480);
//        MovableController movableController = new MovableController(ripley);
//        scene.getInput().registerListener(movableController);
//
//        KeeperController keeperController = new KeeperController(ripley);
//        scene.getInput().registerListener(keeperController);
//
//
//        scene.addActor(ripley, 150, 70);
    }
}
