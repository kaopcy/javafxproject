package Model;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.Glow; 
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class Card {
    //DATA FIELD
    private boolean isSelect = false;
    private boolean canSelect = true;
    
    private int number;
    public static double CARD_SIZE_X ;
    public static double CARD_SIZE_Y ;
    public static double CARD_SCALE = 0.6;
    
    
    private double startPositionX = (Global.GAME_WIDTH * 0.5) - (getSizeX() * 0.5);
    private double startPositionY = (Global.GAME_HEIGHT * 0.5) - (getSizeY() * 0.5);
    
    private double endPosX = 0;
    private double endPosY = 0;
    
    private boolean isScale = false;
    
    private Image image;
    private ImageView  imageView;
    
    //EFFECT
    ColorAdjust colorAdjust;
    DropShadow ds;
    //METHOD FIELD
    public Card(Image image , int number){
        this.number = number;
        this.image = image;
        //Initialize POSITION - SIZE
        initialize();
        
         
        createdEffect();
        createdClickEvent();
 
    }
    
    public void update(){             
      if(canSelect){
        if(isSelect == true){
            colorAdjust.setBrightness(-0.2);
            colorAdjust.setInput(ds);
            imageView.setEffect(colorAdjust);
        }  
      
        else{
            imageView.setEffect(null);
        }
      }
    }
    
    public ImageView getImageView(double WIDTH , double HEIGHT){
        this.endPosX = WIDTH  ;
        this.endPosY = HEIGHT;
        System.out.println(number + " " + endPosX);
        imageView.setLayoutX(startPositionX);
        imageView.setLayoutY(startPositionY);
        
        //Animation
        createMoveAnimation(1500,endPosX-startPositionX ,endPosY -startPositionY , imageView);
        createScaleAnimation(750, 0, 1, imageView);

        return this.imageView;
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
    
    private void createdEffect(){
        colorAdjust = new ColorAdjust();
        ds = new DropShadow();
        ds.setOffsetY(20.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
    }
 
    private void createdClickEvent(){
            imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if(canSelect){
                        colorAdjust.setBrightness(-0.2);
                        imageView.setEffect(colorAdjust);
                        
                    }
                }
            });

            imageView.setOnMouseReleased(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if(canSelect){
                        isSelect = !isSelect;
                        System.out.println("Your selected : " + getNum() + getSuit() + " " + isSelect);
                        
                    }
                }
            });

            imageView.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if(canSelect){
                        ScaleTransition st = new ScaleTransition(Duration.millis(70), imageView);

                        st.setFromX(1);
                        st.setToX(1.1);
                        st.setFromY(1);
                        st.setToY(1.1);
                        st.setOnFinished((ev) -> {
                            isScale = true;
                        });
                        st.play();
                        
                    }


                }
            });

            imageView.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {

                        ScaleTransition st = new ScaleTransition(Duration.millis(70), imageView);

                        st.setFromX(1.1);
                        st.setToX(1);
                        st.setFromY(1.1);
                        st.setToY(1);


                        st.setOnFinished((ev) -> {
                            isScale = false;
                        });
                        st.play();

                }
            });
        
    }
    
    public boolean getIsSelected(){
        return this.isSelect;
    }
    
    public void setVisible( boolean isVis){
        System.out.println("Now current pos = " + getPosX() + " Y = " +getPosY() + " " + getNum() + getSuit());
        createMoveAnimation(1500, ((Global.GAME_WIDTH / 2) - (getSizeX() / 2)) - getPosX(), 0 - getPosY() , imageView);
        canSelect = false;
        isSelect = false;
//        if(!isVis){
//            FadeTransition fade = new FadeTransition();  
//            fade.setDuration(Duration.millis(500));
//            fade.setFromValue(1.0);  
//            fade.setToValue(0);
//            fade.setNode(imageView);
//            fade.play();
//            isSelect = false;
//        }
//        else{
//            FadeTransition fade = new FadeTransition();  
//            fade.setDuration(Duration.millis(500));
//            fade.setFromValue(0);  
//            fade.setToValue(10);
//            fade.setNode(imageView);
//            fade.play();
//            isSelect = false;
//        }
    }
    
    public void playShakeAnimation(){
        
    }
    
    private void initialize(){
        CARD_SIZE_X = image.getWidth() / 52;
        CARD_SIZE_Y = image.getHeight();
        
        imageView = new ImageView(image); 
        imageView.setViewport(new Rectangle2D(CARD_SIZE_X * number, 0, CARD_SIZE_X,CARD_SIZE_Y ));
        
        imageView.setFitHeight(getSizeY());
        imageView.setFitWidth(getSizeX());
    }
    
    public static double getSizeX(){
        return CARD_SIZE_X * CARD_SCALE;
    }
    
    public static double getSizeY(){
        return CARD_SIZE_Y * CARD_SCALE;
    }
    
    public double getPosX(){
        return imageView.getLayoutX() + imageView.getTranslateX();
    }
    
    public double getPosY(){
        return imageView.getLayoutY() + imageView.getTranslateY();
    }
    
    private void createMoveAnimation(int duration , double x , double y , ImageView node ){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(duration));
        translate.setByX( x ); 
        translate.setByY( y );
        translate.play(); 
    }
    
    private void createScaleAnimation( int duration , double beforeScale, double afterScale , ImageView node   ){
        ScaleTransition st = new ScaleTransition(Duration.millis(duration), node);
        st.setFromX(beforeScale);
        st.setToX(afterScale);
        st.setFromY(beforeScale);
        st.setToY(afterScale);
        st.play();
    }


}

    
