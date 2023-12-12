package com.example.stickhero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StickTest {

    @Test
    public void testSetLength() {
        Stick stick = new Stick(0);
        stick.setLength(10);
        assertEquals(10, stick.getLength());
        assertEquals(10, stick.getStick().getHeight());
    }

    @Test
    public void testSetX() {
        Stick stick = new Stick(0);
        stick.setX(20);
        assertEquals(20, stick.getStick().getX());
    }

    @Test
    public void testSetY() {
        Stick stick = new Stick(0);
        stick.setY(30);
        assertEquals(30, stick.getStick().getY());
    }

    @Test
    public void testConstructor() {
        Stick stick = new Stick(15);
        assertEquals(15, stick.getLength());
        assertEquals(610, stick.getStick().getY());
    }

    @Test
    public void testToString() {
        Stick stick = new Stick(8);
        assertEquals("Stick of length: 8", stick.toString());
    }
}