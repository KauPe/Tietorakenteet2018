
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graafi {
    
    private HashMap<Integer, Node> solmut;
    private Node poistettava;
    private Boolean tallennetaankoKaaret;
    private ArrayList<Kaari> kaariLista;
    private Set<String> kaanteisetIndeksiParit;
    
       
    public Graafi(){
        
        this.solmut = new HashMap<>();
        this.poistettava = null;
        this.tallennetaankoKaaret = false;
        this.kaariLista = new ArrayList<>();
        this.kaanteisetIndeksiParit = new HashSet<String>();  
               
    }
    
    public void tallennaSolmut(int avainLuku, Node node){
        
        this.solmut.put(avainLuku, node);
        
    }
    
    public HashMap<Integer, Node> getSolmut(){
        
        return this.solmut;
    }
    
    public Integer getSolmutKoko(){
        
        return this.solmut.size();
        
    }
    
    public void tarkistaOnkoSamojaNaapureita(int naapurienLkm){
        
        if(naapurienLkm < 2){
            
            System.out.println("     Solmuilla vain yksi naapuri");
            System.out.println("     Vertailua ei voi suorittaa");
            return;
        }
        
        Boolean tupliaNolla = true;
        
        for(Integer avaimet:this.solmut.keySet()){
            
            if(this.solmut.get(avaimet).tarkistaOnkoSamojaNaapureita() == true){
                tupliaNolla = false;
                System.out.println("      Solmulla "+ avaimet + " on samat naapurit");
                this.solmut.get(avaimet).tulostaSolmu();
                this.solmut.get(avaimet).tulostaNaapurit();
            }
        }
        if(tupliaNolla == true){
            System.out.println("     Solmuilla ei ole kahta tai useampaa samaa naapuria");
        }
    }
    
    public void tulostaGraafinSolmujenSijainnitjaNaapureidenSijainnit(){
        
        for(Integer avaimet:this.solmut.keySet()){
            
            int avain = avaimet + 1;
            try {
                System.out.println("solmun numero: "+avain);
                this.solmut.get(avaimet).tulostaSolmu();
                this.solmut.get(avaimet).tulostaNaapurit();
                System.out.println("");
                
            } catch (Exception e) {
                //System.out.println("Virhe tiedoston kirjoittamisessa.");
            }
            avain = 0;
            
        }
    }
    public void alustaSolmutEiVierailuiksi(){
        
        for(Integer avaimet:this.solmut.keySet()){
            
            this.solmut.get(avaimet).setNotVisited();
        }
        
    }
    
    public void laskeSolmujenLahtoAsteet(){
        
        for(Integer avain:this.solmut.keySet()){
            
            int lahtoaste = this.solmut.get(avain).getNaapuritKoko();
            this.solmut.get(avain).setLahtoAste(lahtoaste);
        }
    }
    public void laskeSolmujenTuloAsteet(){
        
        for(Integer avain:this.solmut.keySet()){
            
            for(Integer avain2:this.solmut.keySet()){
                
                for(Node solmu:this.solmut.get(avain2).getNaapurit()){
                    
                    if(this.solmut.get(avain).getX() == solmu.getX() && 
                            this.solmut.get(avain).getY() == solmu.getY()){
                        
                        this.solmut.get(avain).setTuloAste();
                        
                    }
                }
            }
        }
    }
        
    public LinkedList<Integer> poistaSolmuJaSenLahtoJaTuloKaaret(int poistettavaIndeksi){
        
        //Haetaan poistettava solmu HashMapista solmut
        this.poistettava = this.solmut.get(poistettavaIndeksi);
        
        //Tutkitaan, mistä solmuista on lähtökaari poistettavaan solmuun
        for(Integer avain:this.solmut.keySet()){

            //Haetaan tarkasteltavaksi kunkin solmun naapurit
            //ja verrataan niiden X- ja Y -koordinaatteja poistettavan X- ja Y- koordinaatteihin
            for(Node naapuri: this.solmut.get(avain).getNaapurit()){
               
               //Kun solmun naapureista löytyy lähtökaari poistettavaan solmuun, 
               //poistetaan viittaus/linkki solmun ArrayListista neighbors
               if((this.poistettava.getX() == naapuri.getX()) 
                       && (this.poistettava.getY() == naapuri.getY())){
                  
                   this.solmut.get(avain).getNaapurit().remove(naapuri);
                }    
            } 
        }
        Node lahinNaapuri = null;
        Node toiseksiLahinNaapuri = null;
        //Haetaan poistettavan solmun lähin ja toiseksi naapuri uusien syvyyshakujen aloituspisteiksi
        if(this.poistettava.getNaapurit().size() > 0){
            lahinNaapuri = this.poistettava.getNaapurit().get(0);
        } 
        if(this.poistettava.getNaapurit().size() > 1){
            toiseksiLahinNaapuri = this.poistettava.getNaapurit().get(1);
        } 
        
        int molemmatNaapuritLoytyneet = 0;
        LinkedList<Integer>lahimpienAvaimet = new LinkedList<>();
        
        for(Integer avain:this.solmut.keySet()){
            
            if(this.solmut.get(avain).equals(lahinNaapuri)){
                
                lahimpienAvaimet.addFirst(avain);
                molemmatNaapuritLoytyneet++;
            }
            if(this.solmut.get(avain).equals(toiseksiLahinNaapuri)){
                
                lahimpienAvaimet.addLast(avain);
                molemmatNaapuritLoytyneet++;
            }
            /*if(molemmatNaapuriLoytyneet == 2){
                
                //HUOM!! Tässä poistetaan solmu
                this.solmut.remove(poistettavaIndeksi);
                return lahimpienAvaimet;
            }*/
        }
        //HUOM!! Tässä poistetaan solmu
        this.solmut.remove(poistettavaIndeksi);
        return lahimpienAvaimet;
    }
    
    public void tallennetaanKaaret(int i, int j, Float solmujenValinenEtaisyys){
        
        String oikeinPainOlevaIndeksiPari = Integer.toString(i)+","+Integer.toString(j);
     
        //Tallennetaan HashSetiin jokaisen vertaillun solmuparin indeksi-parit käänteisessä 
        //järjestyksessä, jonka avulla voidaan varmistaa, että tallennetaan solmupari vain suunnassa
        //a-b, eikä myös suunnassa b-a
        this.kaanteisetIndeksiParit.add(Integer.toString(j)+","+Integer.toString(i));
        
        if(!(this.kaanteisetIndeksiParit.contains(oikeinPainOlevaIndeksiPari) == true)){
        
            Kaari kaari = new Kaari(i, j, solmujenValinenEtaisyys);
            this.kaariLista.add(kaari);
        }
        
    }
  
    public void kaynnistaKaarienTallennus(){
        
        this.tallennetaankoKaaret = true;
    }
    
    public void lopetaKaarienTallennus(){
        
        this.tallennetaankoKaaret = false;
    }
    
    public Boolean getKaarienTallennus(){
        
        return this.tallennetaankoKaaret;
    }
  
    public void tulostaKaariListanKoko(){
        
        System.out.println(this.kaariLista.size());
    }
    
    public int getKaariListanKoko(){
        
        return this.kaariLista.size();
        
    }
    
    public ArrayList<Kaari> getKaariLista(){
        
        return this.kaariLista;
    }
   
}
