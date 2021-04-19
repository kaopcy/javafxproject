package view;

import Model.Global;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class SelectPlayerManager {
    private AnchorPane selectPane;
    private Stage selectStage;
    private Scene selectScene;
    
    private Stage menuStage;
    
    private int numberPlayer = 0;
    
    public SelectPlayerManager() {
        initialize();
        createKeyListener();
        
    }
    
    private void initialize(){
        selectPane = new AnchorPane();
        selectStage = new Stage();
        selectScene = new Scene(selectPane, Global.GAME_WIDTH, Global.GAME_HEIGHT);
        selectStage.setScene(selectScene);
        
    }
    
    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        menuStage.hide();
        createBackground();
        selectStage.show();
        
    }
    
                
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/background1.jpg" , Global.GAME_WIDTH , Global.GAME_HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        selectPane.setBackground(new Background(background));
    }
    
    private void createKeyListener() {
        selectScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(selectStage , menuStage , 3);
                    
                    
                }
                else if (event.getCode() == KeyCode.ENTER){
                    
                }
                
                
            }
        });
    }
    
    
    
}
