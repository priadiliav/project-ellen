package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {
    private Armed shooter;
    private Fire<Armed> fire;

    @Override
    public void keyPressed(Input.Key key) {
        if(key.equals(Input.Key.SPACE)){
            this.fire = new Fire<>();
            this.fire.setActor(this.shooter);
            fire.scheduleFor(this.shooter);
        }
    }

    public ShooterController(Armed shooter){
        this.shooter = shooter;
    }
}
