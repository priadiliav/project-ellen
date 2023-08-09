package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Helicopter extends AbstractActor {
    public Helicopter(){
        setAnimation(new Animation(
            "sprites/heli.png",
            64,
            64,
            0.05f,
            Animation.PlayMode.LOOP_REVERSED));
    }

    public void searchAndDestroy() {
        new Loop<>(new Invoke<>(this::search)).scheduleFor(this);
    }

    private void search(){
        Player player = Objects.requireNonNull(getScene()).getFirstActorByType(Player.class);
        if(player != null){
            this.setPosition((player.getPosX() > this.getPosX()) ? this.getPosX() + 1: this.getPosX() - 1,
                (player.getPosY() > this.getPosY()) ? this.getPosY() + 1: this.getPosY() - 1);
            if(player.intersects(this)){
                player.setEnergy(player.getEnergy() - 1);
            }
        }

    }
}

/*
            int pX = player.getPosX(), pY = player.getPosY(), hX = this.getPosX(), hY = this.getPosY();
            if(pX > hX && pY > hY){
                this.animation.setRotation(270);
            }else if(pX < hX && pY > hY){
                this.animation.setRotation(0);
            }else if(pX < hX && pY < hY){
                this.animation.setRotation(90);
            }else if(pX > hX && pY < hY){
                this.animation.setRotation(180);
            }
*/
