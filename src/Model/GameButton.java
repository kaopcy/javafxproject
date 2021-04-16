package Model;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class GameButton extends Button{
    private final String BUTTON_PRESSED_PATH = "-fx-background-color: transparent; ";
    private final String BUTTON_RELEASE_PATH = "-fx-background-color: transparent; ";
    
    public GameButton(String text){
        setFont(Font.font ("Chiller", 60));
        setTextFill(Color.WHITE);
        setText(text);
        
        
        setPrefWidth(500);
        setPrefHeight(60);
        setStyle(BUTTON_RELEASE_PATH);
        ButtonListener();
        
    }
    
    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_PATH);
        setLayoutY(getLayoutY()+4);
    }
    
    private void setButtonReleaseStyle(){
        setStyle(BUTTON_RELEASE_PATH);
        setLayoutY(getLayoutY()-4);
    }
    
    private void ButtonListener(){
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
                DropShadow ds = new DropShadow();
                ds.setColor(Color.BLACK);
                ds.setOffsetX(2);
                ds.setOffsetY(2);
                
                setEffect(ds);
            }
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
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
