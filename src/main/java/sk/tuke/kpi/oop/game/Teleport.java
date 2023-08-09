package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    private Teleport destinationTeleport = null;

    private boolean playerOnTeleport = false;
    private Player player;

    public boolean isPlayerOnTeleport() {
        return playerOnTeleport;
    }

    public void setPlayerOnTeleport(boolean playerOnTeleport) {
        this.playerOnTeleport = playerOnTeleport;
    }

    public Teleport(Teleport destinationTeleport) {
        setDestination(destinationTeleport);
        setAnimation(new Animation("sprites/lift.png"));
    }

    public Teleport getDestination() {
        return this.destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        this.destinationTeleport = (destinationTeleport != this) ? destinationTeleport : this.destinationTeleport;
    }

    public void teleportPlayer(Player player) {
        this.player = player;
        new Loop<>(new Invoke<>(this::teleport)).scheduleFor(this);
    }

    private void teleport() {
        if (this.player != null && getDestination() != null && this.player.intersects(getDestination()) && !getDestination().playerOnTeleport) {
            this.player.setPosition(this.getPosX(), this.getPosY());


            this.destinationTeleport.setPlayerOnTeleport(true);
            if (!this.playerOnTeleport) {
                this.playerOnTeleport = true;
            }

        } else if (this.player != null && getDestination() != null && this.getDestination().playerOnTeleport &&
            !this.player.intersects(getDestination())) {
            this.destinationTeleport.setPlayerOnTeleport(false);
        }
    }
    /*
    private void teleport(){
        if (this.player != null && this.player.intersects(this) &&
                    !this.playerOnTeleport && this.getDestination() != null) {


            this.player.setPosition(this.getDestination().getPosX(),
                                                this.getDestination().getPosY());
            this.playerOnTeleport = true;
            if (!this.getDestination().playerOnTeleport) {
                this.getDestination().playerOnTeleport = true;
            }
        } else if (this.player != null && this.playerOnTeleport && !this.player.intersects(this)) {
            this.playerOnTeleport = false;
        }
    }
    */
    // when
    // voides na a A zapuste B teleport
    // perepisat this}

}
