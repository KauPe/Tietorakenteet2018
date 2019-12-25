
import java.util.ArrayList;
import java.util.LinkedList;

public class Syvyyshaku {

    private LinkedList<Node> pino;
    private ArrayList<Node> vieraillut;
    private Boolean loytyyReitti;
    private Node vierailtavaSolmu;
    private int solmuLaskin;
    
    public Syvyyshaku(){
     
        this.pino = new LinkedList<>();
        this.vieraillut = new ArrayList<>();
        this.loytyyReitti = false;
        this.vierailtavaSolmu = null;
        this.solmuLaskin = 0;
   
    }
    
    private void haeNaapuri(String tulostus){
        
        //Käydään läpi vierailtavan solmun naapurit ja valitaan ensimmäinen naapuri, joka ei ole vierailtu
        for(Node seuraava:vierailtavaSolmu.getNaapurit()){
                 
                if(seuraava.getVisited() == false){
                    
                    //Määritellään ensimmäinen löytynyt ei-vierailtu naapuri vierailtavaksi solmuksi 
                    vierailtavaSolmu = seuraava;
                    //Määritellään uusi vierailtava solmu vierailluksi
                    vierailtavaSolmu.setVisited();
                    //Lisätään solmulaskimen arvoa yhdellä --> käytetään syvyyshaun tulostuksessa
                    this.solmuLaskin++;
                    //loytyyReitti asetetaan trueksi --> tällöin ei metodissa teeSyvyysHaku poisteta pinosta päälimmäistä solmua
                    this.loytyyReitti = true;
                    //Lisätään vierailtava solmu pinon päälimmäiseksi
                    this.pino.addFirst(vierailtavaSolmu);
                    //Lisätään solmu ArrayListiin vieraillut
                    this.vieraillut.add(vierailtavaSolmu);
                    //Tulostetaan vierailtu solmu, jos muuttuja tulostus = "k"
                    if(tulostus == "k"){
                        System.out.println("*** Solmu nro: "+this.solmuLaskin+" ***");
                        vierailtavaSolmu.tulostaSolmu();
                    }
                    //poistutaan metodista
                    return;
                }
        }
        
        //Jos yksikään naapuri ei ollut ei-visited
        //asetetaan muuttujan loytyyReitti arvoksi false
        //poistutaan metodista
        this.loytyyReitti = false;
        return;
        
    }
   
    public void teeSyvyysHaku(int luku, String tulostus, Graafi graafi){
                   
        //Määritellään aloitussolmu 
        vierailtavaSolmu = graafi.getSolmut().get(luku);
        //Asetetaan se vierailluksi
        vierailtavaSolmu.setVisited();
        this.solmuLaskin++;
        
        //Lisätään solmu pinoon
        this.pino.addFirst(vierailtavaSolmu);
        this.vieraillut.add(vierailtavaSolmu);
        
        //jos tulostus = "k", tulostetaan vieraillut solmut
        if(tulostus == "k"){
            System.out.println("*** Solmu nro: "+this.solmuLaskin+" ***");
            vierailtavaSolmu.tulostaSolmu();
        }
        
        //Käydään while-looppia läpi, kunnes pino on tyhjä
        while(!(this.pino.isEmpty())){
            
            //Kutsutaan metodia haeNaapuri
            //metodille annetaan parametriksi String tulostus, joka toimii ehtona vierailtujen solmujen tulostukselle 
            //jos tulostus = "k", tulostetaan vieraillut solmut
            haeNaapuri(tulostus);
            
            //Jos metodissa haeNaapuri ei ole löytynyt ei-vierailtuja naapureita
            //poistetaan pinosta päälimmäisin solmu ja asetetaan vierailtavaksi solmuksi 
            //seuraava päälimmäisin solmu
            if(this.loytyyReitti == false){
                
                this.pino.poll();
                
                if(!(this.pino.isEmpty())){
                    vierailtavaSolmu = this.pino.getFirst();
                }
            }
        }
    }
    public ArrayList<Node> getVieraillut(){
        
        return this.vieraillut;
    }
    
    public void tyhjennaVieraillut(){
        
        this.vieraillut.clear();
    }
    public int getsolmuLaskin(){
        
        return this.solmuLaskin;
        
    }
    public void alustaSolmuLaskin(){
        
        this.solmuLaskin = 0;
    }
    
}
