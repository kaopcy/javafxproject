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
    public boolean tradeCard = false;
    
    private int number;
    public static double CARD_SIZE_X ;
    public static double CARD_SIZE_Y ;
    public static double CARD_SCALE = 0.6;
    
    private final double  startPositionX = (Global.GAME_WIDTH);
    private final double  startPositionY = (Global.GAME_HEIGHT);
    
    private double endPosX = 0;
    private double endPosY = 0;
    
    private boolean isScale = false;
    public boolean isSlide = false;
    
    private Image image;
    private ImageView  imageView;
    
    private Image imageBack;
    private ImageView imageViewBack;
    
    private int allcard = 52;
    
    //EFFECT
    ColorAdjust colorAdjust;
    DropShadow ds;
    //METHOD FIELD
    public Card(Image image , int number , Image imageBack){
        this.number = number;
        this.image = image;
        
        this.imageBack = imageBack;
        
        //Initialize POSITION - SIZE
        initialize();

        createdEffect();
        createdClickEvent();
 
    }
    
    public Card(Image image , int number , Image imageBack , int allcard){
        this.number = number;
        this.image = image;
        this.allcard = allcard;
        
        this.imageBack = imageBack;
        
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
    //use to call normal node
    public ImageView getImageView(double WIDTH , double HEIGHT){
        
        imageView.setImage(image);
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
    
    //Use when want to move card when enter nnext scene
    public void resetView(double WIDTH , double HEIGHT){
        this.endPosX = WIDTH  ;
        this.endPosY = HEIGHT;
        
       
        
        imageView.setLayoutX(startPositionX-imageView.getTranslateX());
        imageView.setLayoutY(startPositionY-imageView.getTranslateY());
        

        //Animation
        createMoveAnimation(1500,endPosX-startPositionX ,endPosY -startPositionY , imageView);
        createScaleAnimation(750, 0, 1, imageView);
    }
    
    public void resetNewCardView(double WIDTH , double HEIGHT , int duration){
        //Animation
        createMoveAnimation(duration,WIDTH-getPosX() ,HEIGHT -getPosY() , imageView);
    }
    
    
    public void resetBackCardView(double WIDTH , double HEIGHT){
        this.endPosX = WIDTH  ;
        this.endPosY = HEIGHT;
        
        imageView.setLayoutX(1920-imageView.getTranslateX());
        imageView.setLayoutY(0-imageView.getTranslateY());
        
        //Animation
        createMoveAnimation(1500,endPosX-getPosX() ,endPosY -getPosY() , imageView);
        createScaleAnimation(750, 0, 1, imageView);
    }
    
    //It just use to call Backward node 
    public ImageView getBackwardImageView(double WIDTH , double HEIGHT){
        imageView.setImage(imageBack);
        imageView.setLayoutX(100);
        imageView.setLayoutY(100);
        return this.imageView;
    }
   
    public String getNum(){
        if(number == 53){
            return "Joker";
        }
        else{
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
        }
        return "";
    }
    
    public String getSuit(){
        if(number == 53){
            return "Joker";
        }
        else{
            switch((int)(number / 13)){
                case 0: return "Heart";
                case 1: return "Diamond";
                case 2: return "Club";
                case 3: return "Spade";
            }
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
                        if(imageView.getImage().equals(image)){
                            colorAdjust.setBrightness(-0.2);
                            imageView.setEffect(colorAdjust);
                            
                        }
                        
                    }
                }
            });

            imageView.setOnMouseReleased(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if(canSelect){
                        if(imageView.getImage().equals(image)){
                            isSelect = !isSelect;
                            System.out.println("Your selected : " + getNum() + getSuit() + " " + isSelect);
                            
                        }
                        else if(imageView.getImage().equals(imageBack)){
                            
                            tradeCard = true;
                            imageView.setImage(image);
                        }
                        
                    }
                    else{
                        isSlide = true;
                    }
                }
            });

            imageView.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    double offset = imageView.getTranslateY();
                    if(canSelect){
                        if(imageView.getImage().equals(image)){
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
                        else if(imageView.getImage().equals(imageBack)){
//                            TranslateTransition ts = new TranslateTransition();
//                            ts.setNode(imageView);
//                            System.out.println("offset = "+getPosY());
//                            ts.setDuration(Duration.millis(200));
//                            ts.setFromY(-283);
//                            ts.setToY(-283+50);
//                            ts.play();

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


                }
            });

            imageView.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    double offset = imageView.getTranslateY();
                    if(canSelect){
                        if(imageView.getImage().equals(image)){
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
                        else if(imageView.getImage().equals(imageBack)){
//                            TranslateTransition ts = new TranslateTransition();
//                            ts.setNode(imageView);
//                            System.out.println("offset = "+getPosY());
//                            ts.setDuration(Duration.millis(200));
//                            ts.setFromY(-283+50);
//                            ts.setToY(-283);
//                            ts.play();
//                            
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
                        
                    }
                }
            });
        
    }
    
    public boolean getIsSelected(){
        return this.isSelect;
    }
    
    public void setVisible( boolean isVis){
        createMoveAnimation(1500, ((Global.GAME_WIDTH) - (getSizeX())) - getPosX(), 0 - getPosY() , imageView);
        canSelect = false;
        isSelect = false;
    }
    
    public void setIsSelect(){
        isSelect = false;
    }
    
    public void playShakeAnimation(){
        TranslateTransition ts = new TranslateTransition();
        ts.setNode(imageView);
        ts.setDuration(Duration.millis(100));
        ts.setCycleCount(5);
        ts.setByX(-20);
        ts.setByX(20);
        ts.play();
    }
    
    private void initialize(){
        CARD_SIZE_X = image.getWidth() / allcard;
        CARD_SIZE_Y = image.getHeight();
        
        imageView = new ImageView(image); 
        if(allcard==1){
            imageView.setViewport(new Rectangle2D(0, 0, CARD_SIZE_X,CARD_SIZE_Y ));
        }
        else{
            imageView.setViewport(new Rectangle2D(CARD_SIZE_X * number, 0, CARD_SIZE_X,CARD_SIZE_Y ));
        }
        
        imageView.setFitHeight(getSizeY());
        imageView.setFitWidth(getSizeX());
        
        imageViewBack = new ImageView(imageBack);
        imageViewBack.setViewport(new Rectangle2D(0, 0, CARD_SIZE_X,CARD_SIZE_Y ));
        
        imageViewBack.setFitHeight(getSizeY());
        imageViewBack.setFitWidth(getSizeX());
        
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

    
