package comp1110.ass2.gui;

import comp1110.ass2.TwistGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Board extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 1800;
    private static final int VIEWER_HEIGHT = 520;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();

    Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

    private static Group allPieceImages = new Group();
    private Group startingPieces = new Group();
    private final Group controls = new Group();

    ArrayList<BoardNode> allBoardNodes = new ArrayList<>();

    TextField textField;


    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {          // updated by Vinay (u6776177)
        int index = 0;
        for(index=0;index<=placement.length()-4;index=index+4)
        {
            ImageView piece = getImage(placement.substring(index,index+4));
            allPieceImages.getChildren().addAll(piece);
        }
    }

    /**
     * It returns ImageView by computing its rotation position on the board and returns it to
     * makePlacement method
     *
     * @param piecePlacement  A valid placement string
     * @return ImageView of the pieces one by one as and when it is called
     */
    ImageView getImage(String piecePlacement)       // witten by Vinay (u6776177)
    {
        char[] charPlacementArray = piecePlacement.toCharArray();
        char pieceType=charPlacementArray[0]; char columnIndex=charPlacementArray[1]; char rowIndex=charPlacementArray[2]; char position=charPlacementArray[3];
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(Board.class.getResourceAsStream(URI_BASE + pieceType + ".png")));
        int rotation = Character.getNumericValue(position);

        //to place each piece on the viewer correctly
        switch (pieceType)
        {
            case 'a':
                if((position == '2' || position == '4'||position == '6'||position == '0')){
                    imageView.setLayoutY(0);
                    imageView.setLayoutX(150);
                }
                if((position == '3'||position=='5'||position == '7' || position == '1')){
                    imageView.setLayoutX(100);
                    imageView.setLayoutY(50);
                }
                break;
            case 'b':
                if((position == '2' || position == '4'||position == '6'||position == '0')){
                    imageView.setLayoutY(0);
                    imageView.setLayoutX(150);
                }
                if((position == '3'||position=='5'||position == '7' || position == '1')){
                    imageView.setLayoutX(100);
                    imageView.setLayoutY(50);
                }
                break;
            case 'c':
                if((position == '2' || position == '4'||position == '6'||position == '0')){
                    imageView.setLayoutX(150);
                }
                if(position == '3'||position=='5'||position == '7' || position == '1'){
                    imageView.setLayoutY(150);
                }
                break;
            case 'd':
                if((position == '2' || position == '4'||position == '6'||position == '0')){
                    imageView.setLayoutY(0);
                    imageView.setLayoutX(0);
                }
                if(position == '3'||position=='5'||position == '7' || position == '1'){
                    imageView.setLayoutX(-50);
                    imageView.setLayoutY(50);
                }
                break;
            case 'f':
                if((position == '2' || position == '4'||position == '6'||position == '0')){
                    imageView.setLayoutY(0);
                    imageView.setLayoutX(0);
                }
                if(position == '3'||position=='5'||position == '7' || position == '1'){
                    imageView.setLayoutX(-50);
                    imageView.setLayoutY(50);
                }
                break;
            case 'h':
                if(position == '2' || position == '4'||position == '6'||position == '0'){
                    imageView.setLayoutY(0);
                    imageView.setLayoutX(0);
                }
                if((position == '3'||position=='5'||position == '7' || position == '1')){
                    imageView.setLayoutX(-50);
                    imageView.setLayoutY(50);
                }
                break;
        }

        //To rotate the images based on rotation value which is the fourth character in a valid placement string
        if (rotation >= 0 && rotation <= 3)
        {
            imageView.setRotate(rotation * 90);
        }
        if (rotation >= 4 && rotation <= 7)
        {
            imageView.setScaleY(-1);
            imageView.setRotate((rotation-4)*90);
        }

        if(pieceType=='c'|| pieceType=='a'||pieceType=='b')
            imageView.setTranslateX((Character.getNumericValue(columnIndex)*100 - 250));
        else
            imageView.setTranslateX((Character.getNumericValue(columnIndex)*100 - 100));

        //To bifurcate even and every row in the viewer so as to get equal spaced rows
        if(rowIndex=='A')
            imageView.setTranslateY(0);
        else if(rowIndex=='B')
            imageView.setTranslateY(100);
        else if(rowIndex=='C')
            imageView.setTranslateY(200);
        else if(rowIndex=='D')
            imageView.setTranslateY(300);

        return imageView;
    }

    void createBoardNodes(){            // written by Llewelyn (u6673089)
        int r = 1;
        int c = 1;
        while (r <= 4){                               // this will place all the nodes of the board
            while (c <= 8){
                BoardNode node = new BoardNode(c, r);
                allBoardNodes.add(node);
                c++;
            }
            r++;
            c = 1;
        }
    }

    BoardNode findNearestBoardNode(double x, double y){             // written by Llewelyn (u6673089)
        double smallest = Integer.MAX_VALUE;
        BoardNode closeBN = null;
        for (BoardNode bn : allBoardNodes){         // this will find the nearest BoardNode to the currently dragged piece
            if (bn.distance(x,y) < smallest){       // this is the node the dragged piece will snap to
                closeBN = bn;
                smallest = bn.distance(x,y);
            }
        }
        return closeBN;
    }

    void highlightNearestNode(double x, double y){                  // written by Llewelyn (u6673089)
        BoardNode highlighted = findNearestBoardNode(x,y);
        highlighted.setFill(Color.SKYBLUE);
        for (BoardNode bn : allBoardNodes){           // this will highlight the nearest node in a light blue colour
            if (bn != highlighted){
                bn.setFill(Color.GRAY);
            }
        }
    }

    void unhighlightAll(){                                             // written by Llewelyn (u6673089)
        for (BoardNode bn : allBoardNodes){
            bn.setFill(Color.GRAY);     // this will make sure nothing is highlighted once a piece is no longer being dragged
        }
    }

    private void makeStartingPlacements(String placementString){                        // written by Llewelyn (u6673089)
        int index;
        for(index=0;index<=placementString.length()-4;index=index+4)
        {                                                                               // this method creates some starting placements by randomly choosing from
            ImageView piece = getImage(placementString.substring(index,index+4));       // a set of possible placement strings
            startingPieces.getChildren().addAll(piece);
        }
        ArrayList<Character> unusedPieces = new ArrayList<>();
        unusedPieces.add('a'); unusedPieces.add('b'); unusedPieces.add('c'); unusedPieces.add('d'); unusedPieces.add('e'); unusedPieces.add('f'); unusedPieces.add('g'); unusedPieces.add('h');
        for (index = 0; index <= placementString.length()-4; index = index + 4){
            Character removable = placementString.charAt(index);
            unusedPieces.remove(removable);                             // this part of the method sends a list of the unused pieces to another method to create draggable pieces
        }                                                               // of the unused pieces to the side of the board
        createNonStartingPieces(unusedPieces);
    }

    private void createNonStartingPieces(ArrayList<Character> pieceChars){              // written by Llewelyn (u6673089)
        for (Character x : pieceChars){
            Random rand = new Random();                     // this creates all necessary draggable pieces to the side of the board
            int randx = 850+rand.nextInt(550);
            int randy = rand.nextInt(400);
            DraggablePiece draggablePiece = new DraggablePiece(randx, randy, x, this);
            root.getChildren().add(draggablePiece);
        }
    }

    private String possibleStarts(){                    // written by Llewelyn (u6673089)
        String result = "";
        Random rand = new Random();
        int choice = rand.nextInt(15);          // this method will randomly choose a placement string from its choices to be starting pieces
        switch (choice){
            case 1:  result = ""; break;
            case 2:  result = "f2C2h4A4j1C0"; break;
            case 3:  result = "g3A0"; break;
            case 4:  result = "a1C2e7A7k4C0"; break;
            case 5:  result = "d3B5k8B0l7B0"; break;
            case 6:  result = "c1A1f2A5k8D0"; break;
            case 7:  result = "a2B6f7B1l4D0"; break;
            case 8:  result = "e3B2g5A5"; break;
            case 9:  result = "f1B3k4C0l7A0j8D0"; break;
            case 10: result = "b6A6e1A3h4A2j7D0l6A0"; break;
            case 11: result = "a6C4d1A0g3A3k6B0"; break;
            case 12: result = "c3A0f1C4k3D0"; break;
            case 13: result = "c5A2d1B3e4A5i7D0j7A0l3A0"; break;
            case 14: result = "e1C6g4A5h1A0k1C0l6B0"; break;
            case 15: result = "a7B1c1B2e1C3g6A1j3B0"; break;
        }
        return result;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Board");

        root.getChildren().add(controls);
        createBoardNodes();
        makeStartingPlacements(possibleStarts());
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().addAll(allBoardNodes);
        root.getChildren().add(allPieceImages);
        root.getChildren().addAll(startingPieces);
    }

    class BoardNode extends Circle {                                       // written by Llewelyn (u6673089)
        BoardNode(int column, int row){
            this.setRadius(20.0);                // this is the constructor for the separate positions on the board
            this.setLayoutY(row*100-50);
            this.setLayoutX(column*100-50);
            this.setFill(Color.GRAY);
        }

        private double distance(double x, double y){                     // written by Llewelyn (u6673089)
            double xchange = x - getLayoutX() + 50;
            double ychange = y - getLayoutY() + 50;     // this finds the distance from a BoardNode
            return Math.sqrt((xchange*xchange)+(ychange*ychange));
        }
    }

    class Piece extends ImageView{
        int r = 0;
        char piece;

        Piece(double x, double y, char piece){                                              // written by Llewelyn (u6673089)
            this.setImage(new Image(Board.class.getResourceAsStream(URI_BASE + piece + ".png")));
            this.setLayoutX(x);
            this.setLayoutY(y);         // basic constructor for a piece
            this.piece = piece;
        }
    }

    class DraggablePiece extends Piece {                    // written by Llewelyn (u6673089)
        private Board board;
        private double mousex;
        private double mousey;      // some additional information used by a dragged piece
        private double originalmousex;
        private double originalmousey;

        DraggablePiece(double x, double y, char piece, Board board){                                    // written by Llewelyn (u6673089)
            super(x,y,piece);
            this.board = board;     // basic constructor for draggable piece

            this.setOnMousePressed(event -> {
                toFront();
                mousex = event.getSceneX();
                mousey = event.getSceneY(); // lots of information is stored on mouse press to be used later
                originalmousex = event.getSceneX();
                originalmousey = event.getSceneY();
            });
            this.setOnMouseDragged(event2 -> {
                double dragx = event2.getSceneX() - mousex;
                double dragy = event2.getSceneY() - mousey;
                setLayoutX(dragx + getLayoutX());            // this drags the piece, updating the image as it moves, and highlights the nearest board node at all times
                setLayoutY(dragy + getLayoutY());
                board.highlightNearestNode(event2.getSceneX()-50,event2.getSceneY()-50);
                mousex = event2.getSceneX();
                mousey = event2.getSceneY();
            });
            this.setOnMouseReleased(event3 -> {
                unhighlightAll();
                if (findNearestBoardNode(getLayoutX(),getLayoutY()).distance(getLayoutX(),getLayoutY()) < 120) {
                        setLayoutX(findNearestBoardNode(getLayoutX(), getLayoutY()).getLayoutX() - 50);   // checks that piece is near board, and snaps the piece to the nearest BoardNode if it is
                        setLayoutY(findNearestBoardNode(getLayoutX(), getLayoutY()).getLayoutY() - 50);
                } else {
                    double releasey = event3.getSceneY();
                    double releasex = event3.getSceneX();   // if piece is not close to board, and mouse button is released very close to where it was picked up, it rotates the piece.
                    if (originalmousey - releasey < 20 && originalmousey - releasey > -20 && originalmousex - releasex < 20 && releasex > -20) {
                        rotatePiece();
                    }                             // if neither close to point of pickup nor close to board, simply places the piece where it is
                }
            });
        }

        void rotatePiece(){                                                     // written by Llewelyn (u6673089)
            if (r < 7){
                r++;
            } else {   // updates the rotation of the piece
                r = 0;
            }
            Rotate rotate = new Rotate();
            Scale flipx = new Scale();
            flipx.setX(-1);
            double halfxpoint;
            if (piece == 'c') {
                halfxpoint = 150;
            } else {
                halfxpoint = this.getImage().getWidth()/2;
            }
            double halfypoint;
            if (piece == 'a' || piece == 'b' || piece == 'c' || piece == 'd' || piece == 'f'){
                halfypoint = 50;
            } else {
                halfypoint = this.getImage().getHeight() / 2;
            }
            System.out.println(halfxpoint);
            System.out.println(halfypoint);
            rotate.setPivotY(halfypoint);
            rotate.setPivotX(halfxpoint);
            flipx.setPivotX(halfxpoint);
            flipx.setPivotY(halfypoint);
            if (r == 0){
                this.getTransforms().removeAll(flipx); // if rotation being set back to 0, resets the piece to normal state
                this.getTransforms().removeAll(rotate);
            }
            if (r <= 3 && r > 0){
                rotate.setAngle(90);
                this.getTransforms().addAll(rotate);
            } else if (r <= 7) {
                rotate.setAngle(-90);
                this.getTransforms().addAll(rotate);
                if (r == 4) {      // flips the piece once rotation hits 4
                    this.getTransforms().addAll(flipx);
                }
            }
        }
    }
}