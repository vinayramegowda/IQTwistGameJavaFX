package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetPieceColourTest {

    @Test
    public void testGetPieceColour(){
        assertEquals(Colour.RED,Colour.getPieceColour('a'));
        assertEquals(Colour.GREEN,Colour.getPieceColour('k'));
        assertFalse(Colour.getPieceColour('b')==Colour.BLUE);
        assertFalse(Colour.getPieceColour('j')==Colour.YELLOW);
    }
}
