package view;

import Model.Global;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class Select {
    AnchorPane pane;
    Scene mainScene;
    
    public Select(Scene mainScene){
        this.mainScene = mainScene;
        pane = new AnchorPane();
        createBackground(); 
        createButton();
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:Resource/img/menuBackground.jpg" , Global.GAME_WIDTH , Global.GAME_HEIGHT , false , true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }
   public AnchorPane getPane(){
       return this.pane;
   }
   
   private void createButton(){
       Button button = new Button("Start ");
       pane.getChildren().add(button);
       button.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               GameViewManager game = new GameViewManager();
               
               
           }
           
       });
       
       
   }
    
}
