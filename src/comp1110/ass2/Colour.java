package comp1110.ass2;

import com.sun.org.apache.regexp.internal.RE;

public enum Colour { // this enum helps the main Piece class by with colour stuff
    RED,
    BLUE,
    GREEN,
    YELLOW;

    static Colour getPieceColour(Character z){         // written by Llewelyn (u6673089)
        Colour x = RED;
        switch (z){
            case 'a': x = RED; break;
            case 'b': x = RED; break;
            case 'c': x = BLUE; break;
            case 'd': x = BLUE; break;
            case 'e': x = GREEN; break;
            case 'f': x = GREEN; break;
            case 'g': x = YELLOW; break;
            case 'h': x = YELLOW; break;
            case 'i': x = RED; break;
            case 'j': x = BLUE; break;
            case 'k': x = GREEN; break;
            case 'l': x = YELLOW; break;
        }
        return x;
    }
}
