/*
TEHTÄVÄ 9
Kolmen solmun aligraafit sisältävät minimipainoisia kaaria, jotka toimivat siltakaarina muodostattessa 
minimivirittävää puuta graafin kaikista solmuista.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class T2018 {

    public static void main(String[] args) {
        
        Graafi graafi = new Graafi();
        Etaisyys etaisyys = new Etaisyys();
        Leveyshaku leveyshaku = new Leveyshaku();
        Syvyyshaku syvyyshaku = new Syvyyshaku();
        ArrayList<Integer> poistettujenIndeksit = new ArrayList<>();
        Minivirittavapuu minimivirittavapuu = new Minivirittavapuu(graafi);
        
        //Leveyshaun ja syvyyshaun tulokset voi tulostaa ohjelman ajon aikana, jos muuttujaan
        //hakujenTulostus asettaa arvoksi "k";
        String hakujenTulostus = "e";
        
        try {
            System.out.println("TEHTÄVÄ 1");
            //***** Luetaan tiedosto Tdata.txt ******
            System.out.println("**** Luetaan tiedosto Tdata.txt ****");
            BufferedReader br = new BufferedReader(new FileReader("Tdata.txt"));
            System.out.println("**** Luodaan koordinaateista solmut luokkaan Node ****");
            System.out.println("**** Tallennetaan solmut luokkaan Graafi ****");
            Luetiedosto luetiedosto = new Luetiedosto(br);
            luetiedosto.lueTiedosto(graafi);
            
            //Loopissa 0 suoritetaan tehtävästä 1 ensimmäisen naapurin luonti
            //Loopissa 1 suoritetaan tehtävät 2-6
            //Looppeja voi suorittaa max 399 - poistettujen solmujen määrä
            for(int i=0; i<20-poistettujenIndeksit.size();i++){
                
                int naapurienLkm = i+1;
                //Luokan Luetiedosto metodi luetiedosto palauttaa ArrayListin, joka sisältää datataulukot
                //Näitä tarvitaan solmujen välisten etäisyyksien laskemiseen 
                ArrayList<float[]> dataTaulukot = luetiedosto.getDataTaulukot();
                
                if(i == 1){
                    System.out.println("TEHTÄVÄ 2");
                }
                
                if(i == 2){
                    System.out.println("TEHTÄVÄ 7");
                    System.out.println("**** Tehdään jokaisella kierroksella syvyyshaku alkaen solmusta indeksillä 1 ****");
                    System.out.println("**** Kun syvyyshaussa saavutetaan maksimimäärä vierailtuja solmuja (=399)... ****");
                    System.out.println("**** ...kirjoitetaan syvyyshaun tulos tiedostoon COMP.txt");
                                        
                }
                
                System.out.println("**** Tallennetaan graafin solmuille naapuri nro "+naapurienLkm+" ****");
                etaisyys.kayDataLapiJaTallennaNaapureita(graafi, dataTaulukot.get(0), dataTaulukot.get(1), poistettujenIndeksit);
                            
                //System.out.println("");
                /*System.out.println("**** Tarkistetaan, onko solmuilla kaksi tai useampi sama naapuri ****");
                graafi.tarkistaOnkoSamojaNaapureita(naapurienLkm);
                //System.out.println("");*/
                 
                if(i == 1){
                    
                    //System.out.println("Tulostetaan graafin solmut ja sijainnit");
                    //graafi.tulostaGraafinSolmujenSijainnitjaNaapureidenSijainnit();
                    //System.exit(0);
                    
                    //***** Metodeille teeLeveysHaku ja teeSyvyysHaku voi antaa parametrina arvon väliltä 0-399 - poistettujen solmujen määrä*****
                    //Tällöin ohjelma tulostaa graafin solmujen koordinaatit 
                    //leveyshaun ja syvyyshaun mukaisessa järjestyksessä
                    int aloitusSolmunIndeksiNumero = 0;

                    //Leveyshaun ja syvyyshaun tulokset voi tulostaa ohjelman ajon aikana, jos muuttujaan
                    //hakujenTulostus asettaa arvoksi "k";
                    //String hakujenTulostus = "e";
                    System.out.println("TEHTÄVÄ 3");
                    //***** Tehdään leveyshaku *****
                    System.out.println("*** Tehdään leveyshaku 1 alkaen solmusta "+aloitusSolmunIndeksiNumero+" ***");
                    if(hakujenTulostus == "k"){
                        System.out.println("**** Tulostetaan koordinaatit leveyshaun mukaisessa järjestyksessä ****");
                    }
                    leveyshaku.teeLeveysHaku(aloitusSolmunIndeksiNumero, hakujenTulostus, graafi);
                    ArrayList<Node> tiedostoonKirjoitettavatBFS = leveyshaku.getVieraillut();
                    //***** Kirjoitetaan leveyshaussa läpikäydyt solmut tiedostoon BFS.txt ******
                    System.out.println("**** Kirjoitetaan leveyshaun tulosdata tiedostoon BFS.txt *****");
                    BufferedWriter bw = new BufferedWriter(new FileWriter("BFS.txt"));
                    Kirjoitatiedosto kirjoitatiedosto = new Kirjoitatiedosto(bw, tiedostoonKirjoitettavatBFS);
                    kirjoitatiedosto.tiedostoonKirjoitusSolmuille();

                    //Asetetaan graafin solmut ei-vierailluiksi ennen syvyyshaun ajamista
                    graafi.alustaSolmutEiVierailuiksi();
                    
                     System.out.println("TEHTÄVÄ 4");
                    // ***** Tehdään syvyyshaku *****
                    System.out.println("*** Tehdään syvyyshaku 1 alkaen solmusta "+aloitusSolmunIndeksiNumero+" ***");
                    if(hakujenTulostus == "k"){
                        System.out.println("**** Tulostetaan koordinaatit syvyyshaun 1 mukaisessa järjestyksessä ****");
                    }

                    syvyyshaku.teeSyvyysHaku(aloitusSolmunIndeksiNumero, hakujenTulostus, graafi);
                    ArrayList<Node> tiedostoonKirjoitettavatDFS = syvyyshaku.getVieraillut();

                    ///****** Kirjoitetaan syvyyshaussa läpikäydyt solmut tiedostoon DFS.txt ******
                    System.out.println("**** Kirjoitetaan syvyyshaun tulosdata tiedostoon DFS.txt *****");
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter("DFS.txt"));
                    Kirjoitatiedosto kirjoitatiedosto2 = new Kirjoitatiedosto(bw2, tiedostoonKirjoitettavatDFS);
                    kirjoitatiedosto2.tiedostoonKirjoitusSolmuille();
                    
                    System.out.println("TEHTÄVÄ 5");
                    System.out.println("**** Kirjoitetaan graafin solmujen lähtö- ja tuloasteet tiedostoon Degrees.txt *****");
                    System.out.println("**** HUOM! solmut eivät tiedoston Tdata mukaisessa järjestyksessä ****");
                    graafi.laskeSolmujenLahtoAsteet();
                    graafi.laskeSolmujenTuloAsteet();
                    BufferedWriter bw3 = new BufferedWriter(new FileWriter("Degrees.txt"));
                    Kirjoitatiedosto kirjoitatiedosto3 = new Kirjoitatiedosto(bw3);
                    kirjoitatiedosto3.tiedostoonKirjoituslahtoJaTuloAsteille(graafi);
                                        
                    //Asetetaan graafin solmut ei-vierailluiksi ennen leveyshaun 2a suorittamista
                    graafi.alustaSolmutEiVierailuiksi();
                    //Tyhjennetään Luokan Lveyshaku ArrayList vieraillut ennen leveyshakujen 2a ja 2b suorittamista
                    leveyshaku.tyhjennaVieraillut();
                    
                    System.out.println("TEHTÄVÄ 6");
                    //***** Poistetaan graafista solmu määrätyllä indeksillä = syvyyshaun 1 ja leveyshaun 1 aloitusindeksinumero 
                    System.out.println("**** Poistetaan graafista solmu nro "+aloitusSolmunIndeksiNumero+" ****");
                    LinkedList<Integer> lahimpienAvaimet = graafi.poistaSolmuJaSenLahtoJaTuloKaaret(aloitusSolmunIndeksiNumero);
                                        
                    //Lisätään poistetun solmun indeksi ArrayListiin poistettujenIndeksit
                    poistettujenIndeksit.add(aloitusSolmunIndeksiNumero);
                    
                     // ***** Tehdään leveyshaku 2a alkaen poistetun solmun lähimmästä naapurista
                    System.out.println("*** Tehdään leveyshaku 2a alkaen poistetun solmun "+aloitusSolmunIndeksiNumero+" lähimmästä solmusta "+lahimpienAvaimet.getFirst()+" ***");
                    if(hakujenTulostus == "k"){
                        System.out.println("**** Tulostetaan koordinaatit leveyshaun 2a mukaisessa järjestyksessä ****");
                    }
                    leveyshaku.teeLeveysHaku(lahimpienAvaimet.getFirst(), hakujenTulostus, graafi);
                    ArrayList<Node> tiedostoonKirjoitettavatDIM = leveyshaku.getVieraillut();
                    int leveysHaku2aViimeinenIndeksi = tiedostoonKirjoitettavatDIM.size() -1;
                   
                    //Asetetaan graafin solmut ei-vierailluiksi ennen leveyshaun 2b suorittamista
                    graafi.alustaSolmutEiVierailuiksi();
                                     
                     // ***** Tehdään leveyshaku 2b alkaen poistetun solmun lähimmästä naapurista
                    System.out.println("*** Tehdään leveyshaku 2b alkaen poistetun solmun "+aloitusSolmunIndeksiNumero+" toiseksi lähimmästä solmusta "+lahimpienAvaimet.getLast()+" ***");
                    if(hakujenTulostus == "k"){
                        System.out.println("**** Tulostetaan koordinaatit leveyshaun 2b mukaisessa järjestyksessä ****");
                    }
                                     
                    leveyshaku.teeLeveysHaku(lahimpienAvaimet.getLast(), hakujenTulostus, graafi);
                    ArrayList<Node> tiedostoonKirjoitettavatDIM2 = syvyyshaku.getVieraillut();
                    
                    ///****** Kirjoitetaan syvyyshaussa läpikäydyt solmut tiedostoon  DIM.txt ******
                    System.out.println("**** Kirjoitetaan leveyshakujen 2a ja 2b tulosdatat tiedostoon DIM.txt *****");
                    BufferedWriter bw4 = new BufferedWriter(new FileWriter("DIM.txt"));
                    Kirjoitatiedosto kirjoitatiedosto4 = new Kirjoitatiedosto(bw4, tiedostoonKirjoitettavatDIM);
                    kirjoitatiedosto4.tiedostoonKirjoitusLeveysHaulle2aJa2b(leveysHaku2aViimeinenIndeksi);
                    
                    
                }
                
                if(i == 1){
                    
                    graafi.kaynnistaKaarienTallennus();
                }
                
                if(i == 2){
                    
                    graafi.lopetaKaarienTallennus();
                    
                }
                
                if(i > 1){
                    graafi.alustaSolmutEiVierailuiksi();
                    syvyyshaku.tyhjennaVieraillut();
                    syvyyshaku.alustaSolmuLaskin();
                    syvyyshaku.teeSyvyysHaku(1, hakujenTulostus, graafi);
                    //System.out.println("syvyyshaussa läpikäytyjen solmujen lukumäärä:");
                    //System.out.println(syvyyshaku.getsolmuLaskin());
                    
                    //Graafi on yhtenäinen, kun syvyyshaussa vierailtujen solmujen lukumäärä on 
                    //kaikkien solmujen yhteismäärä
                    if(syvyyshaku.getsolmuLaskin() == graafi.getSolmutKoko()){
                        
                        //Kirjoitetaan syvyyshaussa läpikäydyt solmut tiedostoon COMP.txt 
                        ArrayList<Node> tiedostoonKirjoitettavatCOMP = syvyyshaku.getVieraillut();
                        System.out.println("**** Graafi on yhtenäinen, kun jokaiselle solmulle on tehty "+naapurienLkm+" lisäyskierrosta ****");
                        System.out.println("**** Kirjoitetaan syvyyshaun tulosdata tiedostoon COMP.txt *****");
                        BufferedWriter bw5 = new BufferedWriter(new FileWriter("COMP.txt"));
                        Kirjoitatiedosto kirjoitatiedosto5 = new Kirjoitatiedosto(bw5, tiedostoonKirjoitettavatCOMP);
                        kirjoitatiedosto5.tiedostoonKirjoitusSolmuille();
                        
                        System.out.println("TEHTÄVÄ 8");
                        graafi.alustaSolmutEiVierailuiksi();
                        System.out.println("**** Luodaan graafista minimivirittävä puu ****");
                        minimivirittavapuu.luoMinimiVirittavaPuu();
                        ArrayList<Integer> mstTulostusLista = minimivirittavapuu.teeLeveysHakuMinimiVirittavassaPuussa();
                        System.out.println("**** Tehdään leveyshaku minimivirittävässä puussa ****");
                        System.out.println("**** Tallennetaan minimivirittävä puu leveyshaun mukaisessa järjestyksessä tiedostoon MSP.txt ****");
                        BufferedWriter bw6 = new BufferedWriter(new FileWriter("MSP.txt"));
                        Kirjoitatiedosto kirjoitatiedosto6 = new Kirjoitatiedosto(bw6);
                        kirjoitatiedosto6.tiedostoonKirjoitusMinimiVirittavallePuulle(mstTulostusLista, graafi);
                        /*
                        System.out.println("**** tulostetaan minimivirittävän puun kaaret pituusjärjestyksessä ****");
                        minimivirittavapuu.tulostaMinimiVirittavaPuu();
                        */
                        
                        System.out.println("**** Lopetetaan ohjelma ****");
                        System.exit(0);
                    }
                }
            }
            } catch(IOException e){
                
	    System.out.println("Tiedostoa ei löydy.");
	}
    }
}
