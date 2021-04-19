package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Player {
    public boolean canSwap = false;
    
    private ImageView blackScreenView;
    private Label label;
    
    static int playerNum = 0;
    
    private static final double CARD_GAP_X = 30;
    private static final double CARD_GAP_Y = 30;
    
    private boolean isTrade = false;
    private List<Card> cards;
    private List<Integer> selectedCardnum; 
    private List<Card> removeCard;
    
    private String name;
    private int currentPlayer;
    
    private Card ejectCard;
    
    //Constructor
    public Player(String name ){
        playerNum++;
        currentPlayer = playerNum;
        this.name = name;
        cards = new ArrayList<>();
        removeCard = new ArrayList<>();
        selectedCardnum = new ArrayList<>();
        
        
    }
    
    public void resetCardToNotSelect(){
        canSwap = false;
        removeCard.clear();
        System.out.println("canSwap = false");
        for(int i = 0; i < cards.size() ; i++){
            cards.get(i).setIsSelect();
        }
    }
    
    //USE TO REPOSITIONING------------------------------------
    public void resetCard(){
        int row = (int)((cards.size()/7) +1);
        if(!cards.isEmpty()){
            for(int j = 0 ; j < row ; j++ ){
                if(j != row - 1){
                    for(int i = 0 ; i < 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - (7 * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        cards.get(i +(7*j)).resetView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1));
                    }
                }
                else if (j == row-1){
                    for(int i = 0 ; i < cards.size() % 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - ((cards.size() % 7) * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        cards.get(i +(7*j)).resetView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT  - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1));
                    }
                }
            }
            
        }
    }
    
    public void resetNewCard(){
        int row = (int)((cards.size()/7) +1);
        if(!cards.isEmpty()){
            for(int j = 0 ; j < row ; j++ ){
                if(j != row - 1){
                    for(int i = 0 ; i < 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - (7 * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        cards.get(i +(7*j)).resetNewCardView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1),
                        1000);
                    }
                }
                else if (j == row-1){
                    for(int i = 0 ; i < cards.size() % 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - ((cards.size() % 7) * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        cards.get(i +(7*j)).resetNewCardView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT  - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1),
                        1000);
                    }
                }
            }
            
        }
    }
    
    public void backwardReset(Player player){
        if(!player.cards.isEmpty()){
            
            for(int i = 0 ; i < player.getSize() ; i++){
                double frontGap = (Global.GAME_WIDTH - ((player.getSize()) * 50) - (Card.CARD_SIZE_X *0.5))*0.5;
                player.cards.get(i).resetBackCardView(
                frontGap + (50 * i) , 
                Global.GAME_HEIGHT * 0.5 );
            }
        }
    }
    
    public void slideReset(){
        if(!removeCard.isEmpty()){
            
            for(int i = 0 ; i < removeCard.size(); i++){
                removeCard.get(i).resetNewCardView(
                Global.GAME_WIDTH - ((i+1)* (Card.CARD_SIZE_X)), 
                100 ,
                1000);
            }
        }
    }
    
    public void hideCardReset(Player player){
        blackScreenView.setVisible(false);
        label.setVisible(false);
        if(!player.cards.isEmpty()){
            for(int i = 0 ; i < player.getSize() ; i++){
                double frontGap = (Global.GAME_WIDTH - (7 * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                player.cards.get(i).resetNewCardView(
                0, 
                -300 ,
                1250);
            }
        }
    }
    
    //RENDERING-----------------------------------------------
    public void render(AnchorPane pane ) {
        int row = (int)((cards.size()/7) +1);
        if(!cards.isEmpty()){
            for(int j = 0 ; j < row ; j++ ){
                if(j != row - 1){
                    for(int i = 0 ; i < 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - (7 * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        pane.getChildren().add(cards.get(i +(7*j)).getImageView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1 )));
                    }
                }
                else if (j == row-1){
                    for(int i = 0 ; i < cards.size() % 7 ; i++){
                        double frontGap = (Global.GAME_WIDTH - ((cards.size() % 7) * (Card.getSizeX() + CARD_GAP_X)))*0.5;
                        pane.getChildren().add(cards.get(i +(7*j)).getImageView(
                        frontGap+((Card.getSizeX() + CARD_GAP_X) * i) , 
                        Global.GAME_HEIGHT  - (CARD_GAP_Y *(j+1)) - (Card.getSizeY()) * (j+1) ));
                    }
                }
            }
            
        }
        createName(pane);
        
        
        
        
        
    }
    
    public void backwardRender(AnchorPane pane, Player player){
        Image blackScreen = new Image("file:Resource/img/BlackScreen.jpg");
        blackScreenView = new ImageView(blackScreen);
        blackScreenView.setOpacity(0.7);
        pane.getChildren().add(blackScreenView);
        Collections.shuffle(player.cards);
        if(!player.cards.isEmpty()){
            
            for(int i = 0 ; i < player.getSize() ; i++){
                pane.getChildren().add(player.cards.get(i).getBackwardImageView(
                100 , 
                0));
            }
        }
        label = new Label("PICK CARD");
        label.setFont(Font.loadFont("file:Resource/font/font2.ttf", 125));
        label.setTextFill(Color.WHITESMOKE);
        label.setEffect(new DropShadow());
        
        label.setPrefWidth(1000);
        label.setPrefHeight(100);
        label.setAlignment(Pos.CENTER);
        label.setLayoutX((Global.GAME_WIDTH * 0.5) - (label.getPrefWidth() * 0.5)  );
        label.setLayoutY((Global.GAME_HEIGHT * 0.3) - (label.getPrefHeight()* 0.5)  );
        pane.getChildren().add(label);
        
        
    }

    
    
    public void checkSameNumber(){
        //boolean isEqual = false;
        int temp = -1;
        if(temp == -1){
            for(int i = 0; i < cards.size() ; i++){
                if(cards.get(i).getIsSelected() ){
                    temp = i;
                }
            }
        }
        if(temp != -1){
            for(int i = 0; i < cards.size() ; i++){
                if( i != temp){
                    if(cards.get(i).getIsSelected() ){
                        if(cards.get(i).getNum().equals(cards.get(temp).getNum())){
                            removeCard.add(cards.get(i));
                            removeCard.add(cards.get(temp));
                            cards.get(i).setVisible(false);
                            cards.get(temp).setVisible(false);
                            cards.remove(i);
                            if(i < temp){
                                cards.remove(temp-1);
                            }
                            else{
                                cards.remove(temp);
                            }
                            resetNewCard();
                        }
                        else{
                            cards.get(i).setIsSelect();
                            cards.get(temp).setIsSelect();
                            cards.get(i).playShakeAnimation();
                            cards.get(temp).playShakeAnimation();
                        }
                    }
                }
            }
        }
        
        
        
        
//        for(int i = 1; i < selectedCardnum.size(); i++){
//            if(cards.get(selectedCardnum.get(0)).getNum().equals(cards.get(selectedCardnum.get(i)).getNum())){
//                isEqual = true;
//                System.out.println(cards.get(selectedCardnum.get(0)).getNum()+ " is equal"
//                    + cards.get(selectedCardnum.get(i)).getNum()
//                ); 
//            }
//            else{
//                isEqual = false;
//                System.out.println(cards.get(selectedCardnum.get(0)).getNum()+ " not equal"
//                    + cards.get(selectedCardnum.get(i)).getNum()
//                );
//                break;
//            }
//        }
//        if(isEqual){
//            for(int i = 0; i < selectedCardnum.size(); i++){
//                cards.get(selectedCardnum.get(i)).setVisible(false);
//            }
//            System.out.println("Equal");
//        }
//        else{
//            System.out.println("Not Equal");
//        }

        
    }
    
    
    public void getAnotherCard(Player player2){
        player2.backwardReset(player2);

    }
    //UPDATE--------------------------------------------------
    public void update(){
        if(!cards.isEmpty()){

            for(int i = 0; i < this.cards.size(); i++){
                cards.get(i).update();
            }
            
            
            checkSameNumber();
            
            for(int i = 0; i < removeCard.size(); i++){
                if(!removeCard.isEmpty()){
                    if(removeCard.get(i).isSlide){
                        slideReset();
                        removeCard.get(i).isSlide = false;
                    }
                }
            }
        }
    }
    
    public void anotherUpdate(Player player){
            int index = 0;
            for(int i = 0 ; i < player.getSize() ; i++){
                if(player.cards.get(i).tradeCard){
                    System.out.println("kakakakaka");
                    player.ejectCard = player.cards.get(i);
                    index = i;
                    player.isTrade = true;
                    player.cards.get(i).tradeCard = false;
                }
            }

            if(player.isTrade){
                player.cards.remove(index);
                cards.add(player.getEjectCard());
                hideCardReset(player);
                player.ejectCard = null;
                canSwap = true;
                resetNewCard();
                System.out.println("this player out");
                player.isTrade = false;
            }
        
        
    }
    
    //GETTER--------------------------------------------------
    public static int getPlayerNum(){
        return playerNum;
    }
    
    public int getSize(){
        return this.cards.size();
    }
    
    public Card getEjectCard(){
        return ejectCard;
    }
    
    //Setter
    public void Draw(Card newCard){
        cards.add(newCard);
    }
    
    public void remove(int i){
        cards.remove(i);
    }
    
    private void createName(AnchorPane pane){
        label = new Label("Player : "+name);
        label.setFont(Font.loadFont("file:Resource/font/font2.ttf", 50));
        label.setTextFill(Color.WHITESMOKE);
        label.setEffect(new DropShadow(20, Color.BLACK));
        label.setLayoutX(0);
        label.setLayoutY(0);
        pane.getChildren().add(label);
    }
    
    public boolean getIsEmpty(){
        return cards.isEmpty();
    }
    
}
