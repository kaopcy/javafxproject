package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.AnchorPane;

public class Player {
    static int playerNum = 0;
    
    private static final double CARD_GAP_X = 30;
    private static final double CARD_GAP_Y = 30;
    
    private List<Card> cards;
    private List<Integer> selectedCardnum; 
    private String name;
    private int currentPlayer;
    private int cardCount = 0;
    
    
    public Player(String name ){
        playerNum++;
        currentPlayer = playerNum;
        this.name = name;
        cards = new ArrayList<>();
        selectedCardnum = new ArrayList<>();
        
    }
    
    public void Draw(Card newCard){
        cards.add(newCard);
    }
    
    public void remove(int i){
        cards.remove(i);
    }
    
    public void render(AnchorPane pane) {
        int row = (int)((cards.size()/7) +1);

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
    
    public static int getPlayerNum(){
        return playerNum;
    }
    
    public int getSize(){
        return this.cards.size();
    }
    public void update(){
        cardCount = cards.size();
        
        for(int i = 0; i < this.cards.size(); i++){
            cards.get(i).update();
        }
        
        checkSameNumber();
        
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
            for(int i = 0; i < temp ; i++){
                if(cards.get(i).getIsSelected() ){
                    if(cards.get(i).getNum().equals(cards.get(temp).getNum())){
                        cards.get(i).setVisible(false);
                        cards.get(temp).setVisible(false);
                    }
                }
            }
            for(int i = temp+1 ; i < cards.size() ; i++){
                if(cards.get(i).getIsSelected() ){
                    if(cards.get(i).getNum().equals(cards.get(temp).getNum())){
                        cards.get(i).setVisible(false);
                        cards.get(temp).setVisible(false);
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
}
