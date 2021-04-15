package Model;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.Glow; 
public class Card {
    private boolean isSelect = false;
    private int number;
    private Image image;
    private ImageView  imageView;
    public Card(Image image , int number){
        this.number = number;
        this.image = image;
        imageView = new ImageView(image);
        
        imageView.setScaleX(0.35);
        imageView.setScaleY(0.35);
        imageView.setViewport(new Rectangle2D(225 * number, 0, 225,315 ));
        createdClickEvent();
    }
    
    public ImageView getImageView(int WIDTH , int HEIGHT){
        
        
        imageView.setLayoutX(WIDTH);
        imageView.setLayoutY(HEIGHT);

        return this.imageView;
    }
    public double getCardSizeX(){
        return imageView.getViewport().getWidth() * imageView.getScaleX() ;
    }
    public double getCardSizeY(){
        return imageView.getViewport().getHeight() * imageView.getScaleY() ;
    }
    
    public String getNum(){
        switch(this.number % 13){
            case 0: return  "2";
            case 1: return  "3"; 
            case 2: return  "4";      
            case 3: return  "5";      
            case 4: return  "6";      
            case 5: return  "7";      
            case 6: return  "8";      
            case 7: return  "9";      
            case 8: return  "10";     
            case 9: return  "Jack";   
            case 10: return "Queen"; 
            case 11: return "King";  
            case 12: return "Ace";   
        }
        return "";
    }
    public String getSuit(){
        switch((int)(number / 13)){
            case 0: return "Heart";
            case 1: return "Diamond";
            case 2: return "Club";
            case 3: return "Spade";
        }
        return "";
    }
    
    private void createdClickEvent(){
        ColorAdjust colorAdjust = new ColorAdjust();
        Glow glow = new Glow();  
        glow.setLevel(0.9); 
        
        imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                colorAdjust.setBrightness(-0.2);
                imageView.setEffect(colorAdjust);
            }
        });
        
        imageView.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                isSelect = !isSelect;
                System.out.println("Your selected : " + getNum() + getSuit() + " " + isSelect);
                if(isSelect == true){
                    colorAdjust.setBrightness(-0.5);
                    imageView.setEffect(glow);
                }
                else{
                    imageView.setEffect(null);
                }
            }
        });
        
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                imageView.setScaleX(0.4);
                imageView.setScaleY(0.4);
                
            }
        });
        
        imageView.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                imageView.setScaleX(0.35);
                imageView.setScaleY(0.35);
            }
        });
    }
    
    
    
}
