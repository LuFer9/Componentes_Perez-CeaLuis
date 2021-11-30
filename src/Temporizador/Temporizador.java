/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporizador;

import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class Temporizador extends HBox {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label labelTemp;
    @FXML
    private Label lb1;
    @FXML
    private Label lbSegundos;

    /**
     * Initializes the controller class.
     */
    //atributos
    private static final int DURATION = 10;
    private Timeline timeline;
    private Integer i = DURATION;
   
    
    // En este caso se utiliza MouseEvent solo porque llama desde onMouseClicked, se puede sustituir por cualquiertipo que se necesite
    private ObjectProperty<EventHandler<ActionEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    //Temporizador por defecto con 10 segundos de tiempo
    public Temporizador(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try{
            fxmlLoader.load();
        }
        catch(IOException exception){
            throw new RuntimeException(exception);
        }
        
        labelTemp.setText(i.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);//No para el timer hasta que se encuentra un STOP
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                i--;
                
                //Actualiza el timer
                labelTemp.setText(i.toString());
                
                //termina el timline si llega a 0 el contador
               if(i <= 0){

                onActionProperty().get().handle(event);
                timeline.stop();
                System.out.println("FIN");

               }
                    
            }
            
                    
        }));
        
        timeline.play(); 
        
    }
    
    //Constructor para pasarle un tiempo determinado al constructor
    public Temporizador(Integer tiempo){

       this.i = tiempo;

       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
       fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);

       try{
           fxmlLoader.load();
       }
       catch(IOException exception){
           throw new RuntimeException(exception);
       }

       labelTemp.setText(i.toString());
       timeline = new Timeline();
       timeline.setCycleCount(Timeline.INDEFINITE);//No para el timer hasta que se encuentra un STOP
       timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
               i--;

               //Actualiza el timer
               labelTemp.setText(i.toString());

               //termina el timline si llega a 0 el contador
              if(i <= 0){

               onActionProperty().get().handle(event);
               timeline.stop();
               System.out.println("FIN");

              }

           }


       }));

       timeline.play(); 

   }
        
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
       return propertyOnAction;
    }
    
    public final void setOnAction(EventHandler<ActionEvent> handler) {
        propertyOnAction.set(handler);
    }
    
    public final EventHandler<ActionEvent> getOnAction() {
       return propertyOnAction.get();
    }

    //Getter y Setter de tiempo
    public Integer getTiempo() {
        return i;
    }

    public void setTiempo(Integer i) {
        this.i = i;
    }
    
   }   
       
    

