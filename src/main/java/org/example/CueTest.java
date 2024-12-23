package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CueTest
{
    @Test
    public void encue()
    {
        Cue<Integer> cue = new Cue<>();
        assertEquals("->", cue.toString());
        cue.encue(5);
        assertEquals("->5->", cue.toString());
        cue.encue(2);
        assertEquals("->5<->2->", cue.toString());
        assertEquals(5, cue.peek());
    }

    @Test
    public void decue()
    {
        Cue<Integer> cue = new Cue<>();
        assertEquals("->", cue.toString());
        cue.encue(5);
        assertEquals("->5->", cue.toString());
        cue.encue(2);
        assertEquals("->5<->2->", cue.toString());
        cue.decue();
        assertEquals("->2->", cue.toString());
    }
}
