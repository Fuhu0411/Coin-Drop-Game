// Added by BlueJ (plus some others)
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import java.util.Random;

//Added in example code
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;
/**
 * Write a description of JavaFX class DropCoinAnime here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DropCoinAnimeTest extends Application
{
    private DropCoinAnimation animation;
    private Pane root;
    private BoxAnimeTest box;
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    private static ArrayList<Coin1Test> list = new ArrayList<Coin1Test>();
    
    private Coin1Test coin;
    private int countForSpawning =0;
    private int countForRemoving =0;
    
    
    private int points = 0;
    private int pointTemp =0;
    
    private int coinsLeft = 100;
    
    private final String initialMessage = "Coins Left: ";
    private Text coinsMessage;
    
    private final String ini = "Points: ";
    private Text pointsWonMessage;
    
    private int counter = 7;
    
    
    @Override
    public void start(Stage stage)
    {
        root = new Pane();
        box = new BoxAnimeTest("pattern.txt");
        
        coinsMessage = new Text(0, 33*22, initialMessage+coinsLeft);
        coinsMessage.setFont(Font.font("Arial", 33));
        
        pointsWonMessage = new Text(33*7, 33*22, ini+points);
        pointsWonMessage.setFont(Font.font("Arial", 33));
        
        root.getChildren().add(box.getBack());
        root.getChildren().addAll(box.getPegList());
        animation = new DropCoinAnimation();
        coin = new Coin1Test(1, box.getB());
        list.add(coin);
        
        
        
        
        int countLoc = 33;
        for(int i =0; i<19; i++){
            int count = i+1;
            Button b = new Button(""+count);
            b.setLayoutX(countLoc);
            b.setLayoutY(0);
            buttonList.add(b);  
            countLoc+=33;
        }
        

    
        class DropCoin implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent e){
                for(int i =0; i<buttonList.size(); i++){
                    if(e.getSource() == buttonList.get(i)){
                        if(coinsLeft >0){
                            if(countForSpawning >0){
                                Coin1Test b = new Coin1Test(i+1, box.getB());
                                list.add(b);
                                root.getChildren().add(b.getCoin());
                                b.dropIn(i+1,box.getB());
                                coinsLeft--;
                                coinsMessage.setText(initialMessage+coinsLeft);
                            }
                            else if(countForSpawning ==0){
                                coin.dropIn(i+1,box.getB());
                                coinsLeft--;
                                coinsMessage.setText(initialMessage+coinsLeft);
                            }
                        }
                    }
                    
                    if(countForSpawning ==0){
                        animation.start();
                    }
                    
                    countForSpawning++;
                }
            }
        }
        
        
        for(int i =0; i<19; i++){
            buttonList.get(i).setOnAction(new DropCoin());
        }
        
        
        root.getChildren().addAll(buttonList);
        
        
        
        
        
        
        root.getChildren().addAll(box.getBaskets());
        root.getChildren().add(coinsMessage);
        root.getChildren().add(pointsWonMessage);
        
        root.getChildren().add(coin.getCoin());
        
        Scene scene = new Scene(root, 693,693+33);
        stage.setTitle("CoinDropGame");
        stage.setScene(scene);
        stage.show();
    }
    
    //Must be non-static
    private class DropCoinAnimation extends AnimationTimer{
        private ArrayList<Integer> temp;
        @Override
        public void handle(long arg0){
            counter--;
            if(counter ==0){
                for(Coin1Test coin : list){
                    temp = coin.getList();
                    
                    double x = coin.getCoin().getCenterX();
                    double y = coin.getCoin().getCenterY();
                    
                    if(coin.getCounter()<temp.size()-1){    
                        x+= (temp.get(coin.getCounter())*33+16.5-x);
                    }
                            
                    if(coin.getCounter()< temp.size()-1){
                       y+= (temp.get(coin.getCounter()+1)*33+16.5-y);
                    }
                    
                    coin.getCoin().setCenterX(x);
                    coin.getCoin().setCenterY(y);
                    
                    coin.setCounter();
                }
                
                
                for(int i =0; i< list.size(); i++){
                    if(list.get(i).getCounter() >= list.get(i).getList().size()-1){
                        boolean temp =list.get(i).checkWin(box.getB());
                        if(temp == true){
                            points += 33;
                        }
                        pointsWonMessage.setText(ini+points);
                        list.remove(i);
                        root.getChildren().remove(i+1+box.getPegList().size() + buttonList.size() + box.getBaskets().size()+2);
                    }
                }
                
                counter = 7;
            }  
        }
    }    
}
