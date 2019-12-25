
import java.util.ArrayList;
import java.util.LinkedList;

public class Leveyshaku {
  
    private LinkedList<Node> jono;
    private ArrayList<Node> vieraillut;
    
    public Leveyshaku(){
       
        this.jono = new LinkedList<>();
        this.vieraillut = new ArrayList<>();
    }
    public void teeLeveysHaku(int luku, String tulostus, Graafi graafi){
        
        
        Node vierailtavaSolmu;
                
        //Määritellään aloitussolmu 
        vierailtavaSolmu = graafi.getSolmut().get(luku);
        //Asetetaan se vierailluksi
        vierailtavaSolmu.setVisited();
        
        //Lisätään solmu jonoon
        this.jono.add(vierailtavaSolmu);
        
        
        while(!(this.jono.isEmpty())){

            //Ennen solmun poistoa jonosta, tallennetaan se tulostuslistaan
            this.vieraillut.add(this.jono.getFirst());
            
            if(tulostus == "k"){
                this.jono.poll().tulostaSolmu();
            }else{
                this.jono.poll();
            }
            
            
            
            for(Node naapurisolmu: vierailtavaSolmu.getNaapurit()){
                
                if(naapurisolmu.getVisited() == false){
                    
                    naapurisolmu.setVisited();
                    
                    this.jono.add(naapurisolmu);
                }        
            }
            if(this.jono.isEmpty() == false){
                vierailtavaSolmu = this.jono.getFirst();
            }
        }       
    }
    
    public ArrayList<Node> getVieraillut(){
        
        return this.vieraillut;
    }
    public void tyhjennaVieraillut(){
        
        this.vieraillut.clear();
    }
}
