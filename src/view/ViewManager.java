package view;



import Model.Deck;
import Model.GameButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class ViewManager {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1200;
    
    private static final int BUTTON_START_Y = 150;
    
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<GameButton> menuButton;
    
    private Deck deck;
    int test = 0;
    
    public ViewManager() {
        menuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane , WIDTH , HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        
//        Image image = new Image("file:Resource/img/CardCut.png");
//        ImageView imageView = new ImageView(image);
//        imageView.setViewport(new Rectangle2D(2250, 0, 225, 315));
//        
        deck = new Deck();
        
        createButton();
        
        createBackground();
    }
    public Stage getMainStage(){
        return mainStage;
    }
    
    private void renderDeck(int num){
        deck.render(mainPane,num );
    }
    
    private void addButton(GameButton button){
        button.setLayoutY(BUTTON_START_Y + menuButton.size() * 200);
        button.setLayoutX(WIDTH / 2 - button.getPrefWidth() / 2);
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }
    
    private void createButton(){
        createStartButton();
        createExitButton();
    }
    
    private void createStartButton(){
        GameButton startButton = new GameButton("START");
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
//                renderDeck(test);
//                test++;
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage);


            }
        });
        addButton(startButton);
    }
    
    private void createExitButton(){
        GameButton exitButton = new GameButton("EXIT");
        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
        addButton(exitButton);
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/card.png" , WIDTH , HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
    
}
