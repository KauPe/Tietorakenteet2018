
import java.util.ArrayList;

public class Node {
 
    private float x;  
    private float y;  
    private ArrayList<Node> neighbors; 
    private boolean visited;
    
    private int lahtoAste;
    private int tuloAste;
    
    public Node(float x, float y){
        
        this.x=x;
        this.y=y;
        this.neighbors = new ArrayList<>();
        this.visited = false;
        this.lahtoAste = 0;
        this.tuloAste = 0;
    }
    
    public void tallennaNaapuri(Node node){
        
        this.neighbors.add(node);
    }
    
    public void setVisited(){
        
        this.visited = true;
    }
    
    public void setNotVisited(){
        
        this.visited = false;
    }
    
    public boolean getVisited(){
        
        return this.visited;
        
    }
    
    public float getX(){
        
        return this.x;
    }
    public float getY(){
        
        return this.y;
    }
    
    public ArrayList<Node> getNaapurit(){
        
        return this.neighbors;   
    }
    
    public void setTuloAste(){
        
        this.tuloAste = tuloAste +1;
    }
    
    public int getTuloAste(){
        
        return this.tuloAste;
    }
    public void setLahtoAste(int lahtoAste){
        
        this.lahtoAste = lahtoAste;
    }
    
    public int getLahtoAste(){
        
        return this.lahtoAste;
    }
    public void tulostaSolmu(){
        
        System.out.println("Solmun sijainti:");
        System.out.println("X: "+this.x);
        System.out.println("Y: "+this.y);
        System.out.println("Onko vierailtu: "+this.getVisited());
        
    }
    
    public Boolean tarkistaOnkoSamojaNaapureita(){
        
        if(this.neighbors.get(0).x == this.neighbors.get(1).x 
                && this.neighbors.get(0).y == this.neighbors.get(1).y){
            
            return true;
        }
        return false;
    }
    public void tulostaNaapurit(){
        
        int indeksi = 1;
        
        for(Node solmut: this.neighbors){
            
            System.out.println("Naapuri nro "+indeksi);
            System.out.println("X: "+solmut.getX());
            System.out.println("Y: "+solmut.getY());
            
            indeksi++;
        }
    }
    
    public int getNaapuritKoko(){
        
        return this.neighbors.size();
    }
    
}
