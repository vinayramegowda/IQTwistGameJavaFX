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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * A very simple viewer for piece placements in the twist game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {
    private static final int VIEWER_WIDTH = 833;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private static Group allPieceImages = new Group();
    private final Group controls = new Group();

    ArrayList<BoardNode> allBoardNodes = new ArrayList<>();

    private String currentPlacementString = "";

    TextField textField;


    void makePlacement(String placement) {          // updated by Vinay (u6776177)
        if (TwistGame.isPlacementStringValid(TwistGame.insertAlphabetically(currentPlacementString, placement))) {
            currentPlacementString = TwistGame.insertAlphabetically(currentPlacementString,placement);
            int index;
            for (index = 0; index <= placement.length() - 4; index = index + 4) {
                ImageView piece = getImage(placement.substring(index, index + 4));
                allPieceImages.getChildren().addAll(piece);
            }
        }
    }

    public ImageView getImage(String piecePlacement)       // witten by Vinay (u6776177)
    {
        char[] charPlacementArray = piecePlacement.toCharArray();
        char pieceType=charPlacementArray[0]; char columnIndex=charPlacementArray[1]; char rowIndex=charPlacementArray[2]; char position=charPlacementArray[3];
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(Viewer.class.getResourceAsStream(URI_BASE + pieceType + ".png")));
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

    class BoardNode extends Circle {
        BoardNode(int column, int row){
            this.setRadius(5.0);                // this is the constructor for the separate positions on the board
            this.setCenterY(row*100-50);
            this.setCenterX(column*100-50);
        }
    }

    void createBoardNodes(){
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

    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Add Piece");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        createBoardNodes();
        root.getChildren().addAll(allBoardNodes);
        root.getChildren().add(allPieceImages);
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

