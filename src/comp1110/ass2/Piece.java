package comp1110.ass2;

import java.util.stream.IntStream;

public class Piece {
    // contains code for the separate pieces and pegs for the game
    private Colour colour;
    private PieceShape pieceShape;
    private Orientation orientation;
    private char pieceID;
    private int[] positions;

    // the pieceCode holds the information on which piece and which orientation the piece is in, 10*ID+Orientation value
    // the pieceID is simply which piece, a-h, it is

    public Piece(String placement){         // written by Llewelyn (u6673089)
        this.pieceID = placement.charAt(0);
        positions = piecePositionValues(placement);
        orientation = getPieceOrientation(placement.charAt(1));
        colour = colour.getPieceColour(placement.charAt(0));
        pieceShape = PieceShape.getPieceShape(placement.charAt(0));
    }

    public static Orientation getPieceOrientation(Character z){      // written by Llewelyn (u6673089)
        // takes info from the piece to determine the orientation (and if flipped) of the piece
        Orientation x = Orientation.ZERO;
        switch (z){
            case '0': x = Orientation.ZERO; break;
            case '1': x = Orientation.NINETY; break;
            case '2': x = Orientation.ONEEIGHTY; break;
            case '3': x = Orientation.TWOSEVENTY; break;
            case '4': x = Orientation.ZEROFLIPPED; break;
            case '5': x = Orientation.NINETYFLIPPED; break;
            case '6': x = Orientation.ONEEIGHTYFLIPPED; break;
            case '7': x = Orientation.TWOSEVENTYFLIPPED; break;
        }
        return x;
    }


    public String getPlacementString(){
        // returns the placement string for a piece
        return "There isn't a game yet";
    }

    public String updatePlacementString(){
        // this will update the overall placement string and pass it to TwistGame
        return "There isn't a game yet";
    }

    public static int[] piecePositionValues(String placement){       // written by Llewelyn (u6673089)
        // takes info from the piece to determine the positions of the piece on the board
        int a = 0;
        switch (placement.charAt(0)){   // get the length of the array of positions
            case 'a': a = 4; break;
            case 'b': a = 4; break;
            case 'c': a = 4; break;
            case 'd': a = 5; break;
            case 'e': a = 3; break;
            case 'f': a = 4; break;
            case 'g': a = 5; break;
            case 'h': a = 3; break;
            case 'i': a = 1; break;
            case 'j': a = 1; break;
            case 'k': a = 1; break;
            case 'l': a = 1; break;
        }
        int[] positions = new int[a];

        int p = 0;
        switch (placement.charAt(2)){
            case 'A': p = 0; break;
            case 'B': p = 1; break;
            case 'C': p = 2; break;
            case 'D': p = 3; break;     // find the translation of the piece using its two middle characters
        }
        int q = 0;
        switch (placement.charAt(1)){
            case '1': q = 0; break;
            case '2': q = 1; break;
            case '3': q = 2; break;
            case '4': q = 3; break;
            case '5': q = 4; break;
            case '6': q = 5; break;
            case '7': q = 6; break;
            case '8': q = 7; break;
        }
        int m = q + (8*p);                 // now find the position values for each rotation of each piece and add it to the translation value
        switch (placement.substring(0,1)+placement.substring(3)){
            case "a0": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+102; positions[3] = m+10;  break;
            case "a1": positions[0] = m+101; positions[1] = m+9;   positions[2] = m+16;  positions[3] = m+117; break;
            case "a2": positions[0] = m;     positions[1] = m+108; positions[2] = m+9;   positions[3] = m+110; break;
            case "a3": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+8;   positions[3] = m+116; break;
            case "a4": positions[0] = m+2;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+110; break;
            case "a5": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+116; positions[3] = m+17;  break;
            case "a6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+102; positions[3] = m+8;   break;
            case "a7": positions[0] = m;     positions[1] = m+101; positions[2] = m+9;   positions[3] = m+117; break;

            case "b0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+109; positions[3] = m+10;  break;
            case "b1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+16;  break;
            case "b2": positions[0] = m;     positions[1] = m+101; positions[2] = m+9;   positions[3] = m+10;  break;
            case "b3": positions[0] = m+1;   positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  break;
            case "b4": positions[0] = m+101; positions[1] = m+2;   positions[2] = m+8;   positions[3] = m+9;   break;
            case "b5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+17;  break;
            case "b6": positions[0] = m+1;   positions[1] = m+2;   positions[2] = m+8;   positions[3] = m+109; break;
            case "b7": positions[0] = m;     positions[1] = m+108; positions[2] = m+9;   positions[3] = m+17;  break;

            case "c0": positions[0] = m;     positions[1] = m+101; positions[2] = m+2;   positions[3] = m+3;   break;
            case "c1": positions[0] = m;     positions[1] = m+108; positions[2] = m+16;  positions[3] = m+24;  break;
            case "c2": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+3;   break;
            case "c3": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; positions[3] = m+24;  break;
            case "c4": positions[0] = m;     positions[1] = m+101; positions[2] = m+2;   positions[3] = m+3;   break;
            case "c5": positions[0] = m;     positions[1] = m+108; positions[2] = m+16;  positions[3] = m+24;  break;
            case "c6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+3;   break;
            case "c7": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; positions[3] = m+24;  break;

            case "d0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+109; positions[4] = m+110; break;
            case "d1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+116; positions[4] = m+17;  break;
            case "d2": positions[0] = m+100; positions[1] = m+101; positions[2] = m+8;   positions[3] = m+9;   positions[4] = m+10;  break;
            case "d3": positions[0] = m;     positions[1] = m+101; positions[2] = m+8;   positions[3] = m+109; positions[4] = m+16;  break;
            case "d4": positions[0] = m+101; positions[1] = m+102; positions[2] = m+8;   positions[3] = m+9;   positions[4] = m+10;  break;
            case "d5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  positions[4] = m+117; break;
            case "d6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+108; positions[4] = m+109; break;
            case "d7": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+108; positions[3] = m+9;   positions[4] = m+17;  break;

            case "e0": positions[0] = m;     positions[1] = m+101; positions[2] = m+109; break;
            case "e1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+109; break;
            case "e2": positions[0] = m+100; positions[1] = m+108; positions[2] = m+9;   break;
            case "e3": positions[0] = m+100; positions[1] = m+101; positions[2] = m+8;   break;
            case "e4": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+109; break;
            case "e5": positions[0] = m;     positions[1] = m+108; positions[2] = m+109; break;
            case "e6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+108; break;
            case "e7": positions[0] = m+100; positions[1] = m+101; positions[2] = m+9;   break;

            case "f0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+109; break;
            case "f1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+117; break;
            case "f2": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  break;
            case "f3": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  break;
            case "f4": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; break;
            case "f5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+116; break;
            case "f6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+109; break;
            case "f7": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+17;  break;

            case "g0": positions[0] = m+100; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  positions[4] = m+117; break;
            case "g1": positions[0] = m+101; positions[1] = m+102; positions[2] = m+108; positions[3] = m+9;   positions[4] = m+17;  break;
            case "g2": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; positions[4] = m+118; break;
            case "g3": positions[0] = m+1;   positions[1] = m+9;   positions[2] = m+110; positions[3] = m+116; positions[4] = m+117; break;
            case "g4": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  positions[4] = m+116; break;
            case "g5": positions[0] = m+100; positions[1] = m+101; positions[2] = m+9;   positions[3] = m+110; positions[4] = m+17;  break;
            case "g6": positions[0] = m+102; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; positions[4] = m+117; break;
            case "g7": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+117; positions[4] = m+118; break;

            case "h0": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   break;
            case "h1": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+16;  break;
            case "h2": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; break;
            case "h3": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; break;
            case "h4": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   break;
            case "h5": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+16;  break;
            case "h6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; break;
            case "h7": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; break;

            case "i0": positions[0] = m+1000; break; case "j0": positions[0] = m+1000; break; case "k0": positions[0] = m+1000; break; case "l0": positions[0] = m+1000; break;
        }
        return positions;
    }

    public static int[] positionValuesToPositions(int[] positionValues){        // this turns the array of position values to just positions
        int x = positionValues.length;                                          // written by Llewelyn (u6673089)
        int n = 0;
        int[] positions = new int[x];
        while (n < x){
            if (positionValues[n] >= 1000){
                positions[n] = positionValues[n] % 1000;
                n++;
            } else {
                positions[n] = positionValues[n] % 100;
                n++;
            }
        }
        return positions;
    }

    public static int[] addPositions(int[] current, int[] add){         // this will concatenate two arrays into one larger array
        int x = current.length;                                         // written by Llewelyn (u6673089)
        int y = add.length;
        int z = x+y;
        int[] positions = new int[z];
        int n = 0;
        int m = 0;
        while (n < x){
            positions[n] = current[m];
            n++;
            m++;
        }
        int p = x;
        m = 0;
        while (m < y){
            positions[p] = add[m];
            p++;
            m++;
        }
        return positions;
    }

    public static int getNumberOfPositions(String placement){       // this just gets the total number of positions that the pieces will take up
        int x = (placement.length())/4;                             // written by Llewelyn (u6673089)
        int z = 0;
        int y = 0;
        int m = 0;
        while (m < x){
            switch (placement.charAt(z)){
                case 'a': y = y + 4; break;
                case 'b': y = y + 4; break;
                case 'c': y = y + 4; break;
                case 'd': y = y + 5; break;
                case 'e': y = y + 3; break;
                case 'f': y = y + 4; break;
                case 'g': y = y + 5; break;
                case 'h': y = y + 3; break;
            }
            m++;
            z++; z++; z++; z++;
        }
        return y;
    }

    public static boolean isOnBoard(int[] positions){       // this methods checks the pieces are on the board
        int n = 0;                                          // written by Llewelyn (u6673089)
        while (n < positions.length){
            if (positions[n] >= 32 && positions[n] <= 99){
                return false;
            }
            if (positions[n] < 0){
                return false;
            }
            if (n < positions.length-1){
                   if ((positions[n] == 7 && positions[n+1] == 8)||(positions[n] == 15 && positions[n+1] == 16)||(positions[n] == 23 && positions[n+1] == 24)){
                       return false;
                   }
                }
            n++;
        }
        return true;
    }

    public static boolean noOverlap(int[] current, int[] add, int[] currentValues, int[] newValues, char addedPiece, String placementString){       // this method checks that pieces don't overlap
        int m = 0;                                                                                                                                  // written by Llewelyn (u6673089)
        int n = 0;
        boolean valid = true;
        while (n < add.length){
            while (m < current.length){
                if (current[m] == add[n]){
                    valid = isPegAndHole(current[m],add[n],currentValues,newValues,addedPiece,placementString);      // this calls another function to check if the overlapping pieces are a peg and a hole
                }
                m++;
            }
            m = 0;
            n++;
        }
        return valid;
    }

    public static boolean isPegAndHole(int placed, int adding, int[] currentValues, int[] newValues, char addedPiece, String placementString){  // this method checks that two pieces at the same position are a peg and hole
        boolean valid = false;                                                                                                                  // written by Llewelyn (u6673089)
        boolean placedIsPeg = IntStream.of(currentValues).anyMatch(x -> x == placed+1000);
        boolean placedIsHole = IntStream.of(currentValues).anyMatch(x -> x == placed+100);
        boolean addIsPeg = IntStream.of(newValues).anyMatch(x -> x == adding+1000);
        boolean addIsHole = IntStream.of(newValues).anyMatch(x -> x == adding+100);
        if (placedIsPeg && addIsHole){
            valid = doColoursMatch1(placed+1000, addedPiece, placementString);
        }
        if (placedIsHole && addIsPeg){                                                          // this calls other functions to check the colours of the peg and hole
            valid = doColoursMatch2(placed+100, addedPiece, placementString);
        }
        return valid;
    }

    public static boolean doColoursMatch1(int placed, char addedPiece, String placementString){    // this method checks that a peg and hole are the same colour
        int a = 0;                                                                                 // written by Llewelyn (u6673089)
        int b = 4;
        char placedPiece = 'z';
        while (b < placementString.length()-3){
            String piece = placementString.substring(a,b);
            int[] positionValues = piecePositionValues(piece);
            if (IntStream.of(positionValues).anyMatch(x -> x == placed)){
                placedPiece = piece.charAt(0);
                b+=100;
            }
            a+=4;
            b+=4;
        }
        if (placedPiece == 'z'){
            return false;
        }
        if (Colour.getPieceColour(addedPiece)==Colour.getPieceColour(placedPiece)){
            return true;
        }
        return false;
    }

    public static boolean doColoursMatch2(int placed, char addedPiece, String placementString){  // this method checks that a hole and a peg are the same colour
        int a = 0;                                                                               // written by Llewelyn (u6673089)
        int b = 4;
        char placedPiece = 'z';
        while (b < placementString.length()-3){
            String piece = placementString.substring(a,b);
            int[] positionValues = piecePositionValues(piece);
            if (IntStream.of(positionValues).anyMatch(x -> x == placed)){
                placedPiece = piece.charAt(0);
                b+=100;
            }
            a+=4;
            b+=4;
        }
        if (placedPiece == 'z'){
            return false;
        }
        if (Colour.getPieceColour(addedPiece)==Colour.getPieceColour(placedPiece)){
            return true;
        }
        return false;
    }
}
