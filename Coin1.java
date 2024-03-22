import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Coin1{
    private int xRow;
    private int yCol;
    private Circle coin;
    private int xVel =5;
    private int yVel=2;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private int counter;
    
    public Coin1(int y, Box box){
        coin = new Circle(-20, -20, 16.5);
        coin.setFill(Color.SPRINGGREEN);
        this.xRow = 0;
        this.yCol = y;
        counter =0;     
        //dropIn(y, box);
    }
    
    public int getCounter(){
        return this.counter;
    }
    
    public void setCounter(){
        counter+=2;
    }
    
    public Circle getCoin(){
        return this.coin;
    }
    
    public ArrayList getList(){
        return this.list;
    }
    
    public boolean dropIn(int y,Box box){
        char[][] temp = box.getBox(); 
       
        coin.setCenterX(33*y +16.5);
        coin.setCenterY(16.5);
        temp[this.xRow][this.yCol] = 'o';
        
        
        while(this.xRow <20){
            if(temp[this.xRow+1][this.yCol]== '*' && this.yCol != 1 && this.yCol != 19){
                int turn = (int)(Math.random() *(1-0+1));
                if(turn == 1){
                    temp[this.xRow][this.yCol] = '.';
                    this.yCol+=1;
                    this.xRow+=1;
                    temp[this.xRow][this.yCol] = 'o';
                    list.add(this.yCol);
                    list.add(this.xRow);
                }
                else{
                    temp[this.xRow][this.yCol] = '.';
                    this.yCol-=1;
                    this.xRow+=1;
                    temp[this.xRow][this.yCol] = 'o';
                    list.add(this.yCol);
                    list.add(this.xRow);
                }
            }
            else if(temp[this.xRow+1][this.yCol]== '*' &&this.yCol == 1){
                temp[this.xRow][this.yCol] = '.';
                this.yCol+=1;
                this.xRow+=1;
                temp[this.xRow][this.yCol] = 'o';  
                list.add(this.yCol);
                list.add(this.xRow);
            }
            else if(temp[this.xRow+1][this.yCol]== '*' &&this.yCol == 19){
                temp[this.xRow][this.yCol] = '.';
                this.yCol-=1;
                this.xRow+=1;
                temp[this.xRow][this.yCol] = 'o';
                list.add(this.yCol);
                list.add(this.xRow);
            }
            else{
                temp[this.xRow][this.yCol] = '.';
                this.xRow+=1;
                temp[this.xRow][this.yCol] = 'o';
                list.add(this.yCol);
                list.add(this.xRow);
            }
            
        }
        
        temp[this.xRow][this.yCol] = '.';
        list.add(this.yCol);
        list.add(this.xRow);
    
        return box.checkWinning(this.yCol);
    }
    
    public boolean checkWin(Box box){
        return box.checkWinning(this.yCol);
    }
}