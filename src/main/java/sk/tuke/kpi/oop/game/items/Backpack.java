package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> content;

    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.content = new ArrayList<>();
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(this.content);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getSize() {
        return this.content.size();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(this.content.size() <= this.capacity - 1){
            this.content.add(actor);
        }else{
            throw new IllegalStateException(this.name + " is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        this.content.remove(actor);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return this.content.iterator();
    }

    @Override
    public @Nullable Collectible peek() {
        if(this.content.size() == 0 ||
            this.content.get(Math.max(this.content.size() - 1, 0)) == null) return null;
        return this.content.get(Math.max(this.content.size() - 1, 0));
    }

    @Override
    public void shift() {
        Collections.rotate(this.content,1);
    }
}
