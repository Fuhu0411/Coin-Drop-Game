
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;   
public class Box{
    private char[][] box;
    private boolean[] baskets;
    
    public Box(){
        baskets = new boolean[21];
        randomWinning();
        
        box = new char[21][];
        for(int i =0; i< 21; i++){
            box[i] = new char[21];
        }
        
        shapeBox();
    }
    
    public Box(String s){
        baskets = new boolean[21];
        randomWinning();
        
        box = new char[21][];
        for(int i =0; i< 21; i++){
            box[i] = new char[21];
        }
        
        String c = "C:\\Users\\Mr Hao\\CPSC_1181_LAB\\CoinDropGame\\Boxes\\"+s;
        
        String content = "";
        File check = new File(c);
        Scanner read = null;
     
        try{
            read = new Scanner(check);
            while(read.hasNextLine()){
                content+= read.nextLine();    
            }
            
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        finally{
            if(read != null){
                read.close();
                check = null;
            }
        }
        
        int count =0;
        for(int i =0; i< 21; i++){
            for(int j =0; j<21; j++){
                if(count == content.length()){
                    break;
                }
                box[i][j]= content.charAt(count);
                count++;
            }
        }
    }
    
    private void randomWinning(){
        int temp = (int)(Math.random() * (19-1+1))+1;
        
        for(int i =0; i<5; i++){
            baskets[temp] = true;
            temp=(int)(Math.random() * (19-1+1))+1;
        }
    }
    
    private void shapeBox(){
        for(int i=0; i< 21; i++){
            for(int j = 0; j<21; j++){
                if(j == 0 || j == 20){
                    box[i][j] = '*';
                }
                else{
                    box[i][j] = '.';
                }    
            }
        }
        
        randomPegs();
    }
    
    private void randomPegs(){
        int pegRow = (int)(Math.random() *2)+1;
        
        for(int i=pegRow; i< 20-pegRow+1; i+=2){
            int pegCol = (int)(Math.random() *2)+1;
            for(int j = 1; j<20; j++){
                if(j == pegCol){
                    box[i][j] = '*';
                    j++;
                }
                
                pegCol = (int)(Math.random() *2)+j;
            }
        }
    }
    
    public void displayBox(){
        
        for(int i =0; i< 21; i++){
            for(int j = 0; j<21; j++){
                System.out.print(box[i][j]);
            }
            System.out.println();
        }
    }
    
    public void displayBaskets(){
        for(int i =0; i<21; i++){
            System.out.println(this.baskets[i]);
        }    
    }
    
    public char[][] getBox(){
        return this.box;
    }
    
    public boolean[] getBaskets(){
        return this.baskets;
    }
    
    public boolean checkWinning(int yCol){
        if(baskets[yCol] == true){
            return true;
        }
        
        return false;
    }
    
}