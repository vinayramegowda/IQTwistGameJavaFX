package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertAlphabeticallyTest {

    @Test
    public void testInsertAlphabetically(){
        assertSame("a1A1",TwistGame.insertAlphabetically("","a1A1"));

        assertEquals("a1A1b1A1",TwistGame.insertAlphabetically("a1A1","b1A1"));
        assertFalse(TwistGame.insertAlphabetically("a1A1","b1A1")=="b1A1a1A1");

        assertEquals("a7A7b6A7c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0",TwistGame.insertAlphabetically("a7A7b6A7d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0","c1A3"));
        assertFalse(TwistGame.insertAlphabetically("a7A7b6A7d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0","c1A3")=="c1A3a7A7b6A7d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0");
        assertFalse(TwistGame.insertAlphabetically("a7A7b6A7d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0","c1A3")=="a7A7b6A7d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C01A3");

        assertEquals("i6B0j2B0j1C0k3C0l4B0l5C0",TwistGame.insertAlphabetically("i6B0j2B0k3C0l4B0l5C0","j1C0"));
        assertFalse(TwistGame.insertAlphabetically("i6B0j2B0k3C0l4B0l5C0","j1C0")=="i6B0j1C0j2B0k3C0l4B0l5C0");
        assertFalse(TwistGame.insertAlphabetically("i6B0j2B0k3C0l4B0l5C0","j1C0")=="j1C0i6B0j2B0k3C0l4B0l5C0");
        assertFalse(TwistGame.insertAlphabetically("i6B0j2B0k3C0l4B0l5C0","j1C0")=="i6B0j2B0k3C0l4B0l5C0j1C0");
    }
}
