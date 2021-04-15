package view;

import Model.Deck;
import Model.Player;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private final static int HEIGHT = 600;
    private final static int WIDTH = 1200;
    
    private AnimationTimer gameTimer;
    
    Deck deck;
    Player player1;
    Player player2;
    
    
    public GameViewManager() {
        player1 = new Player("Kao");
        player2 = new Player("Boom");
        initializeStage();
        createKeyListener();
        
        deck = new Deck();
        
        
    }
    
    private void drawCard(Player player){
        for(int i = 0; i < 13; i++){
            player.Draw(deck.getCard());
        }
    }
    
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameStage = new Stage();
        gameScene = new Scene(gamePane, WIDTH, HEIGHT);
        gameStage.setScene(gameScene);
    }

    private void createKeyListener() {
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    goToMenu();
                }

            }

        });
    }

    public void goToMenu() {
        gameStage.hide();
        menuStage.show();

    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        menuStage.hide();
        drawCard(player1);
        drawCard(player2);
        
        createBackground();
        createButton();
        player1.render(gamePane);
        player2.render(gamePane);
        
        createGameLoop();
        System.out.println(Player.getPlayerNum());
        gameStage.show();

    }
    
    private void createButton(){
        createNextButton();
        
    }
    
    private void createNextButton(){
        Button button = new Button("Next");
        button.setLayoutX(WIDTH / 2 - button.getPrefWidth() / 2);
        button.setLayoutY(HEIGHT * 0.8);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawCard(player1);
            }
        });
        
        gamePane.getChildren().add(button);
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/background1.jpg" , WIDTH , HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
    }
    
    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                
                
            }
        };
        gameTimer.start();
    }
    
}
