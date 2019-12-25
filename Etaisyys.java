
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Etaisyys {
    
    //private Graafi graafi;
    private ArrayList<Float> lajitellutEtaisyydetYhdelleSolmulle;
    private HashMap<Float, Integer> etaisyydetJaIndeksitYhdelleSolmulle;
   
    public Etaisyys(){
       
        //this.graafi = graafi;
        this.lajitellutEtaisyydetYhdelleSolmulle = new ArrayList<>();
        this.etaisyydetJaIndeksitYhdelleSolmulle = new HashMap<>();
       
    }
    public void kayDataLapiJaTallennaNaapureita(Graafi graafi, float x[], float y[], ArrayList<Integer> poistettujenIndeksit){
                
        for(int i=0; i<400; i++){
            
            //ei suoriteta etäisyysvertailuja sillä indeksillä, 
            //jonka mukainen solmu on poistettu graafista
            if(poistettujenIndeksit.contains(i) == true){
                    
                    continue;
                }
                        
            float solmujenValinenEtaisyys = 0;

            for(int j=0; j<400; j++){
                
                //(!(i == j) --> solmua ei verrata itseensä
                //(!(poistettujenIndeksit.contains(j) == true) --> muita solmuja ei verrata 
                //poistettuun solmuun
                if((!(i == j)) && (!(poistettujenIndeksit.contains(j) == true))){
                    
                    //Solmujen välinen etäisyys lasketaan metodilla laskeEtaisyys
                    solmujenValinenEtaisyys = laskeEtaisyys(x[i], y[i], x[j], y[j]);
                    
                    //Solmujenvälisten etäisyyksien mittaaminen suoritetaan vain
                    //pääohjelman kierroksella 2
                    //Liittyy minimivirittävän puun toteutukseen
                    //*** keskeneräinen ***
                    if(graafi.getKaarienTallennus() == true){
                        
                        graafi.tallennetaanKaaret(i, j, solmujenValinenEtaisyys);
                    
                    }
                    
                    
                    //etäisyydet tallennetaan ensin lajittelemattomana ArrayListiin
                    this.lajitellutEtaisyydetYhdelleSolmulle.add(solmujenValinenEtaisyys);
                    
                    //kukin laskettu etäisyys tallennetaan HashMapin avaimeksi ja 
                    //arvoksi tallennetaan indeksiarvo j
                    this.etaisyydetJaIndeksitYhdelleSolmulle.put(solmujenValinenEtaisyys, j);
                    
                    

                }
            }
            
            //Lajitellaan tallennetut etäisyydet pienimmästä suurimpaan
            Collections.sort(this.lajitellutEtaisyydetYhdelleSolmulle);
            
            //Testaustulostus, jolla tarkistetaan onko arvot lajiteltu pienimmästä suurimpaan
            /*if(i == 0){

                    Object[] arr = this.lajitellutEtaisyydetYhdelleSolmulle.toArray(); 
                    System.out.println ( "Value in array: "); 
                    for (int f = 0; f<arr.length; f++) 
                    System.out.println ( "Value: " + arr[f].toString()) ; 
                }*/
            
            
            //ArrayList muunnetaan taulukoksi arr
            //HUOM, voi olla tarpeeton muunnos, mutta en lähde tätä nyt muuttelemaan...
            Object[] arr = this.lajitellutEtaisyydetYhdelleSolmulle.toArray(); 

            //int indeksiMin1 = naapurienLkm+1;
            //Määritellään solmun naapurit sisältävän taulukon mukaan
            //monesko lajiteltu etäisyysarvo on solmulle seuraavaksi lähin etäisyys
            //Kun naapureita = 0, haetaan lajitelluista etäisyyksistä indeksillä 0 eli lähin naapuri
            //Kun naapureita = 1, haetaan lajitelluista naapureista indeksillä 1 eli toiseksi lähin naapuri
            int indeksiMin1 = graafi.getSolmut().get(i).getNaapurit().size();
           
            //Haetaan taulukosta arr indeksiMin1 vastaava arvo eli etäisyys
            //ja tallennetaan etäisyys muuttujaan minimi
            float minimi = (float)arr[indeksiMin1];
            
            //Haetaan HashMapista etaisyydetJaIndeksit etäisyyttä vastaava indeksiarvo
            //ja tallennetaan se muuttujaan indeksiMin2
            int indeksiMin2 = this.etaisyydetJaIndeksitYhdelleSolmulle.get(minimi);
            
            //lähimmän naapurin tallennus tähän
            //graafi.getSolmut() --> HashMap
            //graafi.getSolmut().get(i) --> Node, johon naapuri tallennetaan
            //graafi.getSolmut().get(i).tallennaNaapuri() --> Noden metodi, jolla tallennetaan lähin naapuri
            //this.graafi.getSolmut().get(i).tallennaNaapuri(this.graafi.getSolmut().get(jalkimmaisenIndeksi));
            graafi.getSolmut().get(i).tallennaNaapuri(graafi.getSolmut().get(indeksiMin2));
            
            //Jokaisen kierroksen päätteeksi tyhjennetään ArrayList
            //ja HashMap, jotta niihin voidaan tallentaa uudet arvot seuraavalla
            //kierroksella
            this.lajitellutEtaisyydetYhdelleSolmulle.clear();
            this.etaisyydetJaIndeksitYhdelleSolmulle.clear();
        }
        
    }
    
    public float laskeEtaisyys(float x1, float y1, float x2, float y2){
        
        
        return (float)(Math.sqrt(Math.pow(x2 - x1, 2) +  Math.pow(y2 - y1, 2)));
        
        
        
        
    }
    
}
