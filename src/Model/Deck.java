package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Deck {

    List<Card> cards = new ArrayList<>();

    public Deck() {
        Image image = new Image("file:Resource/img/CardCut.png");
        for (int i = 0; i < 52; i++) {
            cards.add(new Card(image, i));
        }
    }

    public void render(AnchorPane pane, int num) {
        pane.getChildren().add(cards.get(num).getImageView(500 , 200) );
    }

    public Card getCard() {
        int i = getRand(0, cards.size());
        try {
            return cards.get(i);
        } finally {
            cards.remove(i);
        }
    }

    private int getRand(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
    
    public void update(){
        for(int i = 0 ;i < cards.size() ; i++){
            cards.get(i).update();
        }
    }

}
