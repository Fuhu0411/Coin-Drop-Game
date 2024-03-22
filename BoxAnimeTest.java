

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;

public class BoxAnimeTest{
    private Rectangle back;
    private BoxTest box;
    private static ArrayList<Node> pegList = new ArrayList<Node>();
    private ArrayList<Node> basketsList = new ArrayList<Node>();
    
    public BoxAnimeTest(){
        box = new BoxTest();        
        back = new Rectangle(0,0, 693, 693+33);
        back.setFill(Color.LIGHTPINK);
        
        rectanglePegs(box.getBox());
        rectangleBaskets(box.getBaskets());
    }
    
    public BoxAnimeTest(String s){
        box = new BoxTest(s);
        back = new Rectangle(0,0, 693, 693+33);
        back.setFill(Color.LIGHTPINK);
        
        rectanglePegs(box.getBox());
        rectangleBaskets(box.getBaskets());
    }
    
    public BoxTest getB(){
        return this.box;
    }
    
    public Rectangle getBack(){
        return back;
    }
    
    public ArrayList getPegList(){
        return this.pegList;
    }
    
    public ArrayList getBaskets(){
        return this.basketsList;
    }
    
    private void rectanglePegs(char[][] box){
        for(int i =0; i< 21; i++){
            for(int j =0; j< 21; j++){
                if(box[i][j] == '*'){
                    Rectangle temp = new Rectangle(33*j, 33*i, 33, 33);
                    temp.setFill(Color.SLATEBLUE);
                    pegList.add(temp);
                }
            }
        }
    }
    
    private void rectangleBaskets(boolean[] baskets){
        for(int i =1; i< 20; i++){
            if(baskets[i] == true){
                Rectangle temp = new Rectangle(33*i, 33*20, 33, 33);
                temp.setFill(Color.RED);
                basketsList.add(temp);  
            } 
            else{
                Rectangle temp = new Rectangle(33*i, 33*20, 33, 33);
                temp.setFill(Color.SEAGREEN);
                basketsList.add(temp);  
            }
        }
    }
    
}
