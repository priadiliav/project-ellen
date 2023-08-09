package sk.tuke.kpi.oop.game.characters;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);

    private Firearm firearm;
    private Health health;
    private Backpack backpack;
    public Ripley(){
        super("Ripley");
        this.health = new Health(50, 100);
        setAnimation(new Animation(
            "sprites/player.png",
            32,
            32,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG));
        stoppedMoving();
        this.backpack = new Backpack("Ripley's backpack", 10);
        this.firearm = new Gun(100, 100);
        health.onExhaustion(() -> {
            this.setAnimation(new Animation("sprites/player_die.png",
                32,32,0.1f,
                Animation.PlayMode.ONCE));
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED,this);
        });
    }

    @Override
    public int getSpeed() {
        return 2;
    }
    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().stop();
    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    @Override
    public Firearm getFirearm() {
        return this.firearm;
    }

    @Override
    public void setFirearm(Firearm firearm) {
        this.firearm = firearm;
    }
}
