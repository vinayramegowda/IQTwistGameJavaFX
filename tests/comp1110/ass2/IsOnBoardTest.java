package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsOnBoardTest {

    @Test
    public void testIsOnBoard(){
        int[] goodArray1 = {1,2,3,4};
        assertTrue(Piece.isOnBoard(goodArray1));
        int[] goodArray2 = {20,21,22,23};
        assertTrue(Piece.isOnBoard(goodArray2));
        int[] badArray1 = {6,7,8,9};
        assertFalse(Piece.isOnBoard(badArray1));
        int[] badArray2 = {33,44,55,66};
        assertFalse(Piece.isOnBoard(badArray2));
        int[] badArray3 = {-1};
        assertFalse(Piece.isOnBoard(badArray3));
    }
}
