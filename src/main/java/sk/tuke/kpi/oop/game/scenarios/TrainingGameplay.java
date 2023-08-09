package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.ChainBomb;

public class TrainingGameplay extends Scenario {
    @Override
    public void setupPlay(@NotNull Scene scene) {
        ChainBomb chainBomb = new ChainBomb(2);
        ChainBomb chainBomb1 = new ChainBomb(2);
        ChainBomb chainBomb2 = new ChainBomb(2);
        ChainBomb chainBomb3 = new ChainBomb(2);
        ChainBomb chainBomb4 = new ChainBomb(2);
        scene.addActor(chainBomb, 150, 150);
        scene.addActor(chainBomb1, 150, 201);
        scene.addActor(chainBomb2, 200, 150);
        scene.addActor(chainBomb3, 201, 150);
        chainBomb.activate();
    /*
        List<Actor> actors = scene.getActors();
        Teleport teleport1 = new Teleport(null);
        Teleport teleport2 = new Teleport(null);

        scene.addActor(teleport1, 80, 250);
        scene.addActor(teleport2, 264, 250);

        System.out.println("Teleport check: " + teleport1.getPosX() + " " + teleport1.getPosY());

        teleport1.setDestination(teleport2);
        teleport2.setDestination(teleport1);


        teleport1.teleportPlayer((Player) actors.get(0));
        teleport2.teleportPlayer((Player) actors.get(0));
        */
    }
}
