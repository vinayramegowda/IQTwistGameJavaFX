package comp1110.ass2;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */
public class TwistGame {
    public static Set<String> result = new HashSet<>();

    public TwistGame() {
        // calls both initializeBoard and initializePegs and gets the game going
    }

    private void initializeBoard() {
        // this will create the board where pieces are placed
    }

    private void initializePegs() {
        // this will place the pegs on the board
    }

    /**
     * Determine whether a piece or peg placement is well-formed according to the following:
     * - it consists of exactly four characters
     * - the first character is in the range a .. l (pieces and pegs)
     * - the second character is in the range 1 .. 8 (columns)
     * - the third character is in the range A .. D (rows)
     * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
     *
     * @param piecePlacement A string describing a single piece or peg placement
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String piecePlacement) {  // this method checks the characters at all four points and makes sure they lie in the acceptable ranges
        if (piecePlacement.length() != 4) {                                  // written by Vinay (u6776177)
            return false;
        }
        boolean valid = true;

        char first = piecePlacement.charAt(0);
        char fourth = piecePlacement.charAt(3);

        if ((first == 'i' || first == 'j' || first == 'k' || first == 'l') && (fourth != '0')) {
            valid = false;
        }
        if (!(piecePlacement.matches("[a-l][1-8][A-D][0-7]"))) {
            valid = false;
        }
        return valid;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
     * - each piece or peg placement is well-formed
     * - each piece or peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
     * - no piece or red peg appears more than once in the placement
     * - no green, blue or yellow peg appears more than twice in the placement
     *
     * @param placement A string describing a placement of one or more pieces and pegs
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementStringWellFormed(String placement) {       // written by Llewelyn (u6673089) and Vinay (u6776177)

        boolean valid = true;
        int length = placement.length();
        if (length == 0) {
            return false;
        }
        if (length % 4 != 0) {   // make sure that the overall placement string is made up of four-character pieces
            return false;
        }
        int pieceCount = length / 4;
        int countOfPieces = 0;
        int indexOfPieces = 0;
        while (countOfPieces < pieceCount) {
            if (!(isPlacementWellFormed(placement.substring(indexOfPieces, 4 + indexOfPieces)))) {  // make sure that each four-character piece is well-formed
                valid = false;
            }
            countOfPieces++;
            indexOfPieces += 4;
        }
        if (pieceCount != 1 && length != 0) {
            int countPiecesOrPeg = pieceCount;
            int index = 0;
            String pieceOrPeg = "";
            while (countPiecesOrPeg > 0) {
                pieceOrPeg = pieceOrPeg + placement.charAt(index);
                countPiecesOrPeg--;

                index += 4;
            }
            if (!(isAuthentic(pieceOrPeg))) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * To determine whether  each piece and peg is in alphabetical order
     * To determine pieces (except non-red pegs) aren't placed twice
     *
     * @return True if the placement is well-formed
     */

    private static boolean isAuthentic(String string) {                     // written by Vinay (u6776177)
        boolean valid = true;
        int bluePegCount = 0, greenPegCount = 0, yellowPegCount = 0, redPegCount = 0;
        int length = string.length();
        int currentCharacter = 0;
        int nextCharacter = 1;
        redPegCount = string.length() - string.replace("i", "").length();
        bluePegCount = string.length() - string.replace("j", "").length();
        greenPegCount = string.length() - string.replace("k", "").length();
        yellowPegCount = string.length() - string.replace("l", "").length();
        while (nextCharacter < length) {
            if (string.charAt(currentCharacter) > string.charAt(nextCharacter) || redPegCount > 1 || bluePegCount > 2 || greenPegCount > 2 || yellowPegCount > 2) {
                // this  loops through every side-by-side pair of characters, moving up one each iteration
                valid = false;
            } else if (string.charAt(currentCharacter) == 'a' || string.charAt(currentCharacter) == 'b'
                    || string.charAt(currentCharacter) == 'c' || string.charAt(currentCharacter) == 'd'
                    || string.charAt(currentCharacter) == 'e' || string.charAt(currentCharacter) == 'f'
                    || string.charAt(currentCharacter) == 'g' || string.charAt(currentCharacter) == 'h') {
                if (string.charAt(currentCharacter) >= string.charAt(nextCharacter)) {
                    valid = false;
                }
            }
            currentCharacter++;
            nextCharacter++;
        }
        return valid;
    }

    /**
     * Determine whether a placement string is valid.  To be valid, the placement
     * string must be well-formed and each piece placement must be a valid placement
     * according to the rules of the game.
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - pieces may only overlap pegs when the a) peg is of the same color and b) the
     * point of overlap in the piece is a hole.
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */

    public static boolean isPlacementStringValid(String placement) {          // written by Llewelyn (u6673089)
        if (!(isPlacementStringWellFormed(placement))) {
            return false;
        }
        int[] positions = new int[0];             // these two arrays store the positions and position values of all pieces that hve been placed
        int[] positionValues = new int[0];
        int substringStart = 0;
        int substringEnd = 4;
        int pieceNumber = 0;
        while (pieceNumber < placement.length() / 4) {            // each piece is taken from the main string one at a time
            String piece = placement.substring(substringStart, substringEnd);
            int[] newPositionValues = Piece.piecePositionValues(piece);            // these are the positions and position values of the new piece
            int[] newPositions = Piece.positionValuesToPositions(newPositionValues);
            if (!(Piece.isOnBoard(newPositions))) {
                return false;                 // this checks the piece is on the board
            }
            if (!(Piece.noOverlap(positions, newPositions, positionValues, newPositionValues, piece.charAt(0), placement))) {
                return false;                 // this checks that the piece doesn't overlap, or if it does, it's a hole and a peg
            }
            positionValues = Piece.addPositions(positionValues, newPositionValues);
            positions = Piece.addPositions(positions, newPositions);       // update the overall positions and position values once everything is passed
            pieceNumber++;
            substringStart += 4;
            substringEnd += 4;
        }
        return true;
    }

    public boolean hasGameBeenCompleted() {
        // check to see if the total placement string is full and valid after a piece is put down, ending the game if it is
        return false;
    }

    /**
     * Given a string describing a placement of pieces and pegs, return a set
     * of all possible next viable piece placements.   To be viable, a piece
     * placement must be a valid placement of a single piece.  The piece must
     * not have already been placed (ie not already in the placement string),
     * and its placement must be valid.   If there are no valid piece placements
     * for the given placement string, return null.
     * <p>
     * When symmetric placements of the same piece are viable, only the placement
     * with the lowest rotation should be included in the set.
     *
     * @param placement A valid placement string (comprised of peg and piece placements)
     * @return An set of viable piece placements, or null if there are none.
     */
    public static Set<String> getViablePiecePlacements(String placement) {         // written by Llewelyn (u6673089)
        Set<Character> piecesSet = new HashSet<>();
        int a = 0;
        while (a < placement.length() - 1) {          // this finds the pieces that are not on the board
            piecesSet.add(placement.charAt(a));
            a += 4;
        }

        Set<String> placementsSet = new HashSet<>(); // this is the set of possible placements
        char current = 'a';
        int column = 1;
        char row = 'A';
        int rotation = 0;
        Set<String> exclusionSet = new HashSet<>(); // this is the set that will hold symmetries that can't be placed
        while (current <= 'h') {
            if (piecesSet.contains(current)) {
                current++;
            } else {
                while (row < 'E') {                   // while loops to move through each piece in each position in each rotation
                    while (column < 9) {
                        while (rotation < 8) {

                            String addition = current + Integer.toString(column) + row + Integer.toString(rotation);
                            if (!exclusionSet.contains(addition)) {
                                if (isPlacementStringValid(insertAlphabetically(placement, addition))) {    // this makes sure the addition is valid, and adds it if it is
                                    placementsSet.add(addition);
                                    switch (current) {
                                        case 'a':
                                            break;                    // this is what is added to the set of weak symmetries
                                        case 'b':
                                            if (rotation == 0) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '2');
                                            }
                                            if (rotation == 1) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '3');
                                            }
                                            if (rotation == 4) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 5) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            break;
                                        case 'c':
                                            if (rotation == 0) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '2');
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 2) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 4) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 1) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '3');
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 3) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 5) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            break;
                                        case 'd':
                                            break;
                                        case 'e':
                                            if (rotation == 0) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 1) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                            }
                                            if (rotation == 2) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                            }
                                            if (rotation == 3) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            break;
                                        case 'f':
                                            if (rotation == 0) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 1) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 2) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                            }
                                            if (rotation == 3) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                            }
                                            break;
                                        case 'g':
                                            break;
                                        case 'h':
                                            if (rotation == 0) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '2');
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 2) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '4');
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 4) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '6');
                                            }
                                            if (rotation == 1) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '3');
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 3) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '5');
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            if (rotation == 5) {
                                                exclusionSet.add(current + Integer.toString(column) + row + '7');
                                            }
                                            break;
                                    }
                                }
                            }

                            rotation++;
                        }
                        column++;
                        rotation = 0;
                    }
                    row++;
                    column = 1;
                }
                row = 'A';
                current++;
            }
        }
        if (placementsSet.isEmpty()) {
            return null;
        } else {
            return placementsSet;
        }
    }

    public static String insertAlphabetically(String placement, String newPiece) {           // written by Llewelyn (u6673089)
        String updated = placement;
        int a = 0;                                    // this method inserts a placement into a placement string alphabetically so it can be checked for validity, used in getViablePiecePlacements
        if (placement.length() == 0) {
            return newPiece;
        }
        while (a < placement.length() - 1) {
            if (newPiece.charAt(0) < placement.charAt(a)) {
                updated = new StringBuilder(placement).insert(a, newPiece).toString();
                a += 100;
            } else {
                a += 4;
            }
        }
        if (a == placement.length()) {
            updated = placement + newPiece;
        }
        return updated;
    }

    /**
     * Return an array of all unique solutions for a given starting placement.
     * <p>
     * Each solution should be a 32-character string giving the placement sequence
     * of all eight pieces, given the starting placement.
     * <p>
     * The set of solutions should not include any symmetric piece placements.
     * <p>
     * In the IQ-Twist game, valid challenges can have only one solution, but
     * other starting placements that are not valid challenges may have more
     * than one solution.  The most obvious example is the unconstrained board,
     * which has very many solutions.
     *
     * @param placement A valid piece placement string.
     * @return An array of strings, each 32-characters long, describing a unique
     * unordered solution to the game given the starting point provided by placement.
     */
    public static String[] getSolutions(String placement) {   // Written by Vinay (u6776177)
        // explanation makes it pretty clear what to do here
        String[] uniqueSolution;
        int countSize = 0;
        int index = 0;
        int size = placement.length() - 4;
        String[] placementArray = new String[placement.length() / 4];
        for (int i = 0; i <= size; i = i + 4) {
            placementArray[index] = placement.substring(i, i + 4);
            ++index;
        }

        possibleSolutions(placement);

        uniqueSolution = new String[result.size()];
        for (String piece : result) {
            uniqueSolution[countSize] = piece;
            countSize++;
        }
        result = new HashSet<>();
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return uniqueSolution;
    }

    /**
     * This method accepts placement string and computes the viable placements for the placemnt
     * string and is a recurring function which goes on until we find the best possible solutions
     *
     * @param placement A valid piece placement string.
     */

    public static void possibleSolutions(String placement) { // Written by Vinay(u6776177)
        int index = 0;
        int size = placement.length() - 4;
        String temp = "";
        Set<String> viablePiecePlacements = getViablePiecePlacements(placement);
        String[] placementArray = new String[placement.length() / 4];
        for (int i = 0; i <= size; i = i + 4) {
            placementArray[index] = placement.substring(i, i + 4);
            index = index + 1;
        }
        if (viablePiecePlacements == null) {
            temp = getPieces(placementArray);
            if (temp.length() == 32) {
                result.add(temp);
            }
        } else {
            Iterator<String> iterate = viablePiecePlacements.iterator();
            while (iterate.hasNext()) {
                temp = placement;
                temp = temp + iterate.next();
                temp = ReorderAlphabetically(temp);
                possibleSolutions(temp);
            }
        }
    }

    /**
     * This fuvtion accepts the placement string and reorders it alphabetically
     *
     * @param placement A valid piece placement string.
     * @return a string each 32-characters long, describing a unique
     * unordered solution to the game given the starting point provided by placement.
     */

    static public String ReorderAlphabetically(String placement) // Written by Vinay(u6776177)
    {
        int index = 0;
        String[] placementArray = new String[placement.length() / 4];
        for (int i = 0; i <= placement.length() - 4; i = i + 4) {
            placementArray[index] = placement.substring(i, i + 4);
            ++index;
        }
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(placementArray));
        String alphaList[] = new String[arrayList.size()];
        alphaList = arrayList.toArray(alphaList);

        Arrays.sort(alphaList);
        StringBuilder reorderedPlacement = new StringBuilder();
        for (String stringValue : alphaList) {

            reorderedPlacement.append(stringValue);

        }
        return reorderedPlacement.toString();
    }

    /**
     * This functions helps to get only the pieces from the placement string
     *
     * @param placementArray A valid piece placement array of strings(Pieces).
     * @return A string of only pieces in it.
     */

    static public String getPieces(String[] placementArray) { // Written By Vinay(u6776177)
        int countPieces;
        StringBuilder reformedPlacement = new StringBuilder();
        String[] pieceArray = null;
        countPieces = 0;
        int index = 0;

        for (String piece : placementArray) {
            if (piece.charAt(0) < 'i') {

                countPieces++;
            }
        }
        if (countPieces != 0) {

            pieceArray = new String[countPieces];

            while (index < countPieces) {
                pieceArray[index] = placementArray[index];
                index++;
            }
        }
        if (pieceArray != null) {
            for (String s : pieceArray) {
                reformedPlacement.append(s);
            }
        }

        return reformedPlacement.toString();
    }

}
