package exampleproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class spriteAndBoundaryTest {

    Sprite player = new Sprite();
    Sprite enemy = new Sprite();


    @Test
    public void testSetPosition() {

        enemy.setPositionX(100);
        assertEquals(100, enemy.getPositionX());

        player.setPositionY(200);
        assertEquals(200, player.getPositionY());

        assertThrows(IllegalArgumentException.class, () 
         -> {
             player.setPositionY(-100);
         });
    }

    @Test
    public void testTeleport() {
        player.setPositionX(-60);
        player.teleport();
        assertEquals(768 - 1, player.getPositionX());

        player.setPositionX(768 + 1);
        player.teleport();
        assertEquals(0 - 48, player.getPositionX());

        player.setPositionX(1000);
        player.teleport();
        assertEquals(0 - 48, player.getPositionX());
    }

    @Test
    public void testCollision() {

        player.setPositionX(200);
        player.setPositionY(100);

        enemy.setPositionX(200);
        enemy.setPositionY(100);

        assertTrue(player.collide(enemy));
    }

    @Test
    public void testBoundaryPosition() {
        player.setPositionX(300);
        player.setPositionY(100);
        Boundary boundary = player.getBoundary();

        assertEquals(300.0, boundary.getX());
        assertEquals(100.0, boundary.getY());
    }

    @Test
    public void testBoundarySize() {
        Boundary boundary = player.getBoundary();

        assertEquals(30, boundary.getWidth());
        assertEquals(40, boundary.getHeight());
    }

}
