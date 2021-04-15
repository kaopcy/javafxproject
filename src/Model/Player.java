package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.AnchorPane;

public class Player {
    static int playerNum = 0;
    List<Card> cards;
    String name;
    private int currentPlayer;
    
    public Player(String name ){
        playerNum++;
        currentPlayer = playerNum;
        this.name = name;
        cards = new ArrayList<>();
    }
    
    public void Draw(Card newCard){
        cards.add(newCard);
    }
    
    public void remove(int i){
        cards.remove(i);
    }
    
    public void render(AnchorPane pane) {
        System.out.println(cards.size());
        for(int i = 0; i < cards.size(); i++){
            
            System.out.println("kik :" + ((int)cards.get(i).getCardSizeY()*currentPlayer - (int)cards.get(i).getCardSizeY()) );
            pane.getChildren().add(cards.get(i).getImageView(
                    (((int)cards.get(i).getCardSizeX()+ 10) * i) , 
                    ((int)cards.get(i).getCardSizeY() + 100)*currentPlayer - (int)cards.get(i).getCardSizeY()));
        }
    }
    
    public static int getPlayerNum(){
        return playerNum;
    }
    
    public int getSize(){
        return this.cards.size();
    }
}
