package view;

import Model.Deck;
import Model.Global;
import Model.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage selectStage;
    private Stage menuStage;
        
    private final static int WIDTH = Global.GAME_WIDTH;
    private final static int HEIGHT = Global.GAME_HEIGHT;
    
    private int numberPlayer = 0;
    private int currentplayer = 0;
    private boolean startGame = false;
    
    private boolean canPlayWinText = true;
    
    private AnimationTimer gameTimer;
    
    
    
    Deck deck;
    
    List<Player> playerList;
    List<AnchorPane> paneList;
    List<TextField> playerNameList;
    

    Player player1;
    
    public GameViewManager() {
        playerList = new ArrayList<>();
        paneList = new ArrayList<>();
        playerNameList = new ArrayList<>();
        
        initializeStage();
        createKeyListener();
        deck = new Deck();
    }
    
    private void drawCard(){
        int maxCard = (int)(53 / numberPlayer);
        
        int remainCard = 53%numberPlayer;
        
        for(int j = 0; j < playerList.size() ; j++){
            for(int i = 0; i < maxCard; i++){
                playerList.get(j).Draw(deck.getCard());
            }
        }
        for(int i = 0; i < remainCard; i++){
            playerList.get(i).Draw(deck.getCard());
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
                else if (event.getCode() == KeyCode.ENTER){
                    if(currentplayer > numberPlayer-1){
                        if(playerList.get(currentplayer % numberPlayer).canSwap){
                            nextPlayer();
                        }
                        else
                        {
                            System.out.println("Please draw card ");
                        }
                        
                    }
                    else{
                        nextPlayer();
                    }
                }
                
                
            }
        });
    }

    public void goToMenu() {
        gameStage.hide();
        menuStage.show();
    }

    public void createNewGame(Stage selectStage , Stage menuStage , int numberPlayer) {
        this.menuStage = menuStage;
        this.selectStage = selectStage;
        selectStage.hide();
        createButton();
        createSelectBackground();
        
        gameStage.show();
    }
    
    private void createButton(){
//        create3Button();
//        create4Button();
//        create2Button();
//        create5Button();
        addButton();
        addStartButton();
    }
    
    private void addButton(){
        Button button = new Button("ADD");
        button.setLayoutX((WIDTH *0.6) - button.getPrefWidth() / 2);
        button.setLayoutY(HEIGHT * 0.8);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addInput();
            }
        });
        gamePane.getChildren().add(button);
    }
    
    private void addInput(){
        TextField inputText = new TextField();   
        inputText.setPrefSize(120,40);
        inputText.setLayoutX(Global.GAME_WIDTH / 2 - (inputText.getPrefWidth()/2));
        inputText.setLayoutY((playerNameList.size()+2) * 60);
        playerNameList.add(inputText);
        gamePane.getChildren().add(inputText);
        
    }
    
    private void addStartButton(){
        Button button = new Button("Start");
        button.setLayoutX((WIDTH *0.2) - button.getPrefWidth() / 2);
        button.setLayoutY(HEIGHT * 0.8);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                numberPlayer = playerNameList.size();
                startGame(numberPlayer);
                gameScene.setRoot(paneList.get(currentplayer));
            }
        });
        
        gamePane.getChildren().add(button);
    }
     
    private void createSelectBackground(){
        Image backgroundImage = new Image("file:Resource/img/SelectCardBackground.png" , WIDTH , HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/background1.jpg" , WIDTH , HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        for(int i = 0; i < numberPlayer; i++){
            paneList.get(i).setBackground(new Background(background));
        }  
    }
    
    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i = 0 ; i< playerList.size() ; i++){
                    
                    playerList.get(i).update();
                    
                    
                }
                if(currentplayer > numberPlayer-1){
                    playerList.get(currentplayer % numberPlayer).anotherUpdate(playerList.get((currentplayer+1) % numberPlayer));
                    
                }
                for(int i = 0; i < playerList.size(); i++){
                    if(playerList.get((i)).getIsEmpty()){
                        playWinText(playerNameList.get(i).getText().concat(" Win !!!!"),  paneList.get((currentplayer) % numberPlayer));
                    }
                }
                
                if(playerList.size() == 1 ){
                    
                    
                    
                }
                
                
            }
        };
        gameTimer.start();
    }
    
    private void addPlayer(int numberPlayer){
        
        for(int i = 0; i < numberPlayer; i++){
            playerList.add(new Player(playerNameList.get(i).getText()));
        }
        for(int i = 0; i < numberPlayer; i++){
            paneList.add(new AnchorPane());
        }
        System.out.println(playerList.size());
        
        
    }

    private void nextPlayer(){
        canPlayWinText = true;
        playerList.get(currentplayer % numberPlayer).resetCardToNotSelect();
        paneList.get(currentplayer % numberPlayer).getChildren().clear();
        if(playerList.size() > 1){
            playerList.get((currentplayer+1) % numberPlayer).resetCardToNotSelect();
            paneList.get((currentplayer+1) % numberPlayer).getChildren().clear();
            currentplayer++;
        }        
        
        
                
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get((i)).getIsEmpty()){
                playerNameList.remove(i);
                playerList.remove(i);
                numberPlayer = numberPlayer-1;
            }
        }
        if(playerList.size() > 1){
            playerList.get(currentplayer % numberPlayer).render(paneList.get(currentplayer % numberPlayer));
        }
        if(currentplayer > numberPlayer-1){
            playerList.get(currentplayer % numberPlayer).backwardRender(paneList.get(currentplayer % numberPlayer),  playerList.get((currentplayer+1) % numberPlayer));
            
        }
        gameScene.setRoot(paneList.get(currentplayer % numberPlayer));
        if(currentplayer > numberPlayer-1){
            playerList.get(currentplayer % numberPlayer).getAnotherCard(playerList.get((currentplayer+1) % numberPlayer));
            
        }
        playerList.get(currentplayer % numberPlayer).resetCard();
        
        
        
    }
    
    private void startGame( int numberPlayer){
        
        addPlayer(numberPlayer);
        startGame = true;
        if(startGame){
            drawCard();
            createBackground();
            
                playerList.get(0).render(paneList.get(0));
                   
            playerList.get(currentplayer % numberPlayer).resetCard();
            createGameLoop();
        }
    }
    
    private void playWinText(String text , AnchorPane pane){
        if(canPlayWinText){
            Label label = new Label(text);
            label.setFont(Font.loadFont("file:Resource/font/font2.ttf", 125));
            label.setTextFill(Color.WHITESMOKE);
            label.setEffect(new DropShadow());

            label.setPrefWidth(1000);
            label.setPrefHeight(100);
            label.setAlignment(Pos.CENTER);
            label.setLayoutX(Global.GAME_WIDTH);
            label.setLayoutY(200);
            pane.getChildren().add(label);

            TranslateTransition translate = new TranslateTransition();
            translate.setNode(label);
            translate.setDuration(Duration.millis(750));
            translate.setByX( (-Global.GAME_WIDTH *0.5) - (label.getPrefWidth() * 0.5)  ); 
            translate.setByY( 0 );
            translate.play(); 
            translate.setOnFinished(e->{
                 TranslateTransition ts = new TranslateTransition();
                 ts.setDelay(Duration.millis(750));
                ts.setNode(label);
                ts.setDuration(Duration.millis(750));
                ts.setByX( (-Global.GAME_WIDTH)); 
                ts.setByY( 0 );
                ts.play(); 
            });
            canPlayWinText = false;
        }
    }

    
    

} 