package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class Clorus extends huglife.Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Called when this creature moves.
     */
    public void move(){
        energy -= 0.03;
    }

    public Color color(){
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * Called when this creature attacks C.
     */
    public void attack(Creature c){
        this.energy += c.energy();
    };

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    public Creature replicate(){
        this.energy = energy * 0.5;
        Clorus offSpring = new Clorus(this.energy);
        return offSpring;
    }

    /**
     * Called when this creature chooses stay.
     */
    public void stay(){
        energy -= 0.01;
    };

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors){
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> neighborPlips = new ArrayDeque<>();
        boolean anyEmptySquare = false;
        for(Direction key: neighbors.keySet()){
            if(neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
                anyEmptySquare = true;
            }else if(neighbors.get(key).name().equals("plip")){
                neighborPlips.add(key);
            }
        }
        if(!anyEmptySquare){
            return new Action(Action.ActionType.STAY);
        }else if(neighborPlips.size() != 0){
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(neighborPlips));
        }else if(this.energy >= 1.0){
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }else{
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

    }

    /**
     * Returns the current energy.
     */
    public double energy() {
        return energy;
    }

}
