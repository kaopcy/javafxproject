package Model;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class GameButton extends Button{
    private final String BUTTON_PRESSED_PATH = "-fx-background-color: transparent; -fx-background-image: url('file:src/Button2.png');";
    private final String BUTTON_RELEASE_PATH = "-fx-background-color: transparent; -fx-background-image: url('file:src/Button2.png');";
    
    public GameButton(String text){
        setFont(Font.font ("Verdana", 50));
        setTextFill(Color.WHITE);
        setText(text);
        
        setPrefWidth(300);
        setPrefHeight(105);
        setStyle(BUTTON_RELEASE_PATH);
        ButtonListener();
        
    }
    
    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_PATH);
        setPrefHeight(105);
        setLayoutY(getLayoutY()+4);
    }
    
    private void setButtonReleaseStyle(){
        setStyle(BUTTON_RELEASE_PATH);
        setPrefHeight(105);
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
                setEffect(new DropShadow());
            }
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
        
        
    }
    
    
    
}
