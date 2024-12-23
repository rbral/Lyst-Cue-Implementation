package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LystTest
{
    @Test
    public void addFirst()
    {
        Lyst<Integer> lyst = new Lyst<>();
        assertEquals("->", lyst.toString());
        lyst.addFirst(678);
        assertEquals("->678->", lyst.toString());
        lyst.addFirst(379);
        assertEquals("->379<->678->", lyst.toString());
        assertEquals(379, lyst.getFirst());
        assertEquals(678, lyst.getLast());
    }
    @Test
    public void addLast()
    {
        Lyst<Integer> lyst = new Lyst<>();
        assertEquals("->", lyst.toString());
        lyst.addLast(345);
        assertEquals("->345->", lyst.toString());
        lyst.addLast(321);
        assertEquals("->345<->321->", lyst.toString());
        assertEquals(345, lyst.getFirst());
        assertEquals(321, lyst.getLast());
    }

}
