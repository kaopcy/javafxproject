package Model;


import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
public class GameButton extends Button{
    private final String BUTTON_ENTER_PATH = "-fx-background-color: transparent; -fx-background-image: url('file:Resource/img/startbutton/press.png'); ";
    private final String BUTTON_NORNAL_PATH = "-fx-background-color: transparent; -fx-background-image: url('file:Resource/img/startbutton/normal.png');";
    private final String BUTTON_CLICK_PATH = "-fx-background-color: transparent; -fx-background-image: url('file:Resource/img/startbutton/click.png');";
    
    private final double SCALE = 0.7;
    
    public GameButton(String text){
        
        setPrefWidth(256);
        setPrefHeight(258);
        
        setScaleX(SCALE);
        setScaleY(SCALE);
        
        setStyle(BUTTON_NORNAL_PATH);
        ButtonListener(this);
        
    }
    
    private void setButtonPressedStyle(){
        setStyle(BUTTON_CLICK_PATH);
        setLayoutY(getLayoutY()+4);
    }
    
    private void setButtonReleaseStyle(){
        setStyle(BUTTON_NORNAL_PATH);
        setLayoutY(getLayoutY()-4);
    }
    
    private void setButtonExitStyle(){
        setStyle(BUTTON_ENTER_PATH);
        setLayoutY(getLayoutY()-4);
    }
    
    private void ButtonListener(Button button){
        setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    setButtonPressedStyle();
            }
        });
        
        setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    setButtonReleaseStyle();
            }
        });
        
        setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setButtonExitStyle();
                ScaleTransition st = new ScaleTransition(Duration.millis(70) , button);
                st.setFromX(SCALE);
                st.setToX(SCALE+0.1);
                st.setFromY(SCALE);
                st.setToY(SCALE+0.1);
                st.play();
                
            }
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setButtonPressedStyle();
                ScaleTransition st = new ScaleTransition(Duration.millis(70) , button);
                st.setFromX(SCALE+0.1);
                st.setToX(SCALE);
                st.setFromY(SCALE+0.1);
                st.setToY(SCALE);
                st.play();
            }
        });

    }
    
    public void setPos(double x , double y){
        setLayoutX(x);
        setLayoutY(y);
    }
    
    public double getSizeX(){
        return getPrefWidth();
    }
    
    public double getSizeY(){
        return getPrefHeight();
    }
    
    
}
