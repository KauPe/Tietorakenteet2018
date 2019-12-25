
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kirjoitatiedosto {
    
    private BufferedWriter bw;
    private BufferedWriter bw2;
    private BufferedWriter bw3;
    private BufferedWriter bw4;
    private ArrayList<Node> tiedostoonKirjoitettavat;
    private HashMap<Integer, Node> tiedostoonkirjoitettavatGraafi;
    
       
    public Kirjoitatiedosto(BufferedWriter bw, ArrayList<Node> tiedostoonKirjoitettavat){
        
        this.bw = bw;
        this.tiedostoonKirjoitettavat = tiedostoonKirjoitettavat;
    }
    
    public Kirjoitatiedosto(BufferedWriter bw2, HashMap<Integer, Node> graafinSolmut){
        
        this.bw2 = bw2;
        this.tiedostoonkirjoitettavatGraafi = graafinSolmut;
        
    }
    public Kirjoitatiedosto(BufferedWriter bw3){
        
        this.bw3 = bw3;
                
    }
    
    public void tiedostoonKirjoitusSolmuille(){
        
        try {
            for(Node solmu:this.tiedostoonKirjoitettavat){

                    bw.write(Float.toString(solmu.getX()));
                    bw.write(",");
                    bw.write(Float.toString(solmu.getY()));
                    bw.newLine(); 
                    
            }
        bw.close();
        } catch (IOException ex) {
                System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
    }
    public void tiedostoonKirjoitusLeveysHaulle2aJa2b(int leveysHaku2aViimeinenIndeksi){
        
        try {
            
            bw.write("Syvyyshaun 2a tulos alkaen poistetun solmun lähimmästä solmusta:");
            bw.newLine(); 
            for(Node solmu:this.tiedostoonKirjoitettavat){
                
                if(this.tiedostoonKirjoitettavat.get(leveysHaku2aViimeinenIndeksi+1) == solmu){
                    bw.write("Syvyyshaun 2b tulos alkaen poistetun solmun toiseksi lähimmästä solmusta:");
                    bw.newLine();
                }
                bw.write(Float.toString(solmu.getX()));
                bw.write(",");
                bw.write(Float.toString(solmu.getY()));
                bw.newLine(); 

            }
            
        bw.close();
        } catch (IOException ex) {
                System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
    }
    public void tiedostoonKirjoitusGraafille(){
        
        try {
            for(Integer avain:this.tiedostoonkirjoitettavatGraafi.keySet()){
                   
                    bw2.write(Float.toString(tiedostoonkirjoitettavatGraafi.get(avain).getX()));
                    bw2.write(",");
                    bw2.write(Float.toString(tiedostoonkirjoitettavatGraafi.get(avain).getY()));
                    bw2.newLine(); 
                    
            }
        bw2.close();
        } catch (IOException ex) {
                System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
    }
   
    public void tiedostoonKirjoituslahtoJaTuloAsteille(Graafi graafi){
        
        try {
            bw3.write("Solmujen lähtö- ja tuloasteet (X,Y: lähtöaste,tuloaste):");
            bw3.newLine();
            for(Integer avain:graafi.getSolmut().keySet()){
                
                bw3.write(Float.toString(graafi.getSolmut().get(avain).getX())+","
                        +Float.toString(graafi.getSolmut().get(avain).getY())+": ");
                bw3.write(Integer.toString(graafi.getSolmut().get(avain).getLahtoAste()));
                bw3.write(",");
                bw3.write(Integer.toString(graafi.getSolmut().get(avain).getTuloAste()));
                bw3.newLine();
            
            }
        bw3.close();
        } catch (IOException ex) {
                System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
        
    }
    
    public void tiedostoonKirjoitusMinimiVirittavallePuulle(ArrayList<Integer> mstTulostusLista, Graafi graafi){
        
               
        try {
            
            
            for(Integer solmuIdx :mstTulostusLista){
                
                      
           try{
                    bw3.write("SolmuIdx:"+solmuIdx+" --> X:"+Float.toString(graafi.getSolmut().get(solmuIdx).getX())
                            
                            + ", Y:"+Float.toString(graafi.getSolmut().get(solmuIdx).getY()));
                  
                    bw3.newLine();
                    
           }catch (Exception e){
               
               
           }
            
            }
        bw3.close();
        } catch (IOException ex) {
                System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
    }
    
}
