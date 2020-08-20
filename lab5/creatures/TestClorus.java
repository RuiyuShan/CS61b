package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics(){
        Clorus clorus = new Clorus(1);
        assertEquals(1, clorus.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), clorus.color());
        clorus.move();
        assertEquals(1 - 0.03, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(1 - 0.03 - 0.01, clorus.energy(), 0.01);
    }

    @Test
    public void testReplicate(){
        Clorus clorus = new Clorus(1.2);
        Clorus clorus1 = (Clorus) clorus.replicate();
        assertEquals(0.6, clorus.energy(), 0.01);
        assertEquals(0.6, clorus1.energy(), 0.01);
    }

    @Test
    public void testAttack(){
        Clorus clorus = new Clorus(0.5);
        Plip plip = new Plip(0.5);
        clorus.attack(plip);
        assertEquals(1, clorus.energy(), 0.01);
    }

    @Test
    public void testChoose(){
        // No empty adjacent spaces; stay.
        Clorus clorus = new Clorus(0.8);

        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(actual, expected);

        // Energy >= 1; replicate towards an empty space.
        clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(actual, expected);

        // If there exists plip(s) and an empty square, then attack.
        clorus = new Clorus(0.4);
        HashMap<Direction, Occupant> plipAndEmpty = new HashMap<>();
        plipAndEmpty.put(Direction.TOP, new Empty());
        plipAndEmpty.put(Direction.BOTTOM, new Impassible());
        plipAndEmpty.put(Direction.LEFT, new Plip(0.4));
        plipAndEmpty.put(Direction.RIGHT, new Plip(0.4));

        actual = clorus.chooseAction(plipAndEmpty);
        Action attackLeft = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        Action attackRight =  new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        boolean result = actual.equals(attackLeft) || actual.equals(attackRight);
        assertTrue(result);


        // Test MOVE()
        clorus = new Clorus(0.5);
        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

    }
}
