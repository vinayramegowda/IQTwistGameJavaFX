package comp1110.ass2;

public enum PieceShape {
    RED_L,
    RED_Z,
    BLUE_STRAIGHT,
    BLUE_FIVER,
    GREEN_CORNER,
    GREEN_T,
    YELLOW_WEIRD,
    YELLOW_STRAIGHT,
    RED_PEG,
    BLUE_PEG,
    GREEN_PEG,
    YELLOW_PEG;


    public static PieceShape getPieceShape(Character z){      // written by Llewelyn (u6673089)
        PieceShape x = RED_L;
        switch (z){
            case 'a': x = RED_L; break;
            case 'b': x = RED_Z; break;
            case 'c': x = BLUE_STRAIGHT; break;
            case 'd': x = BLUE_FIVER; break;
            case 'e': x = GREEN_CORNER; break;
            case 'f': x = GREEN_T; break;
            case 'g': x = YELLOW_WEIRD; break;
            case 'h': x = YELLOW_STRAIGHT; break;
            case 'i': x = RED_PEG; break;
            case 'j': x = BLUE_PEG; break;
            case 'k': x = GREEN_PEG; break;
            case 'l': x = YELLOW_PEG; break;
        }
        return x;
    }


}
