package view;



import Model.Deck;
import Model.GameButton;
import Model.Global;
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
   
   
    private static final int BUTTON_START_Y = 150;
    
    Image icon;
    
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<GameButton> menuButton;
    
    private Deck deck;
    
    public ViewManager() {
        menuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane , Global.GAME_WIDTH , Global.GAME_HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createdIcon();
        
        deck = new Deck();
        
        createButton();
        
        createBackground();
        createGif();
    }
    public Stage getMainStage(){
        return mainStage;
    }
    
    private void renderDeck(int num){
        deck.render(mainPane,num );
    }
    
    private void addButton(GameButton button){
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }
    
    private void createButton(){
        createStartButton();
        createExitButton();
    }
    
    private void createStartButton(){
        GameButton startButton = new GameButton("Start");
        startButton.setPos(Global.GAME_WIDTH * 0.2 - startButton.getSizeX() *0.5 , Global.GAME_HEIGHT * 0.3);
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                SelectPlayerManager selectManager = new SelectPlayerManager();
                selectManager.createNewGame(mainStage);
            }
        });
        addButton(startButton);
    }
    
    private void createExitButton(){
        GameButton exitButton = new GameButton("Exit");
        exitButton.setPos(Global.GAME_WIDTH * 0.2 - exitButton.getSizeX() *0.5 , Global.GAME_HEIGHT * 0.6);
        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
        addButton(exitButton);
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/backgroundMain.jpg" , Global.GAME_WIDTH , Global.GAME_HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
    
    private void createdIcon(){
        icon = new Image("file:Resource/img/icon.png");
        mainStage.getIcons().add(icon);
       
    }
    
    private void createGif(){
        Image gifimage = new Image("file:Resource/img/test6.gif");
        
        ImageView gif = new ImageView(gifimage);
        
        mainPane.getChildren().add(gif);
    }
    
    
}
