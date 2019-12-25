
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Minivirittavapuu {
    
    private Graafi graafi;
    private ArrayList<Kaari> mst;
    
    public Minivirittavapuu(Graafi graafi){
        
        this.graafi = graafi;
        this.mst = new ArrayList<>();
       
    }
    
    public void luoMinimiVirittavaPuu(){
        
        PriorityQueue<Kaari> pq = new PriorityQueue<>(this.graafi.getKaariListanKoko(), Comparator.comparingDouble(o -> o.getEtaisyys()));
        
         //Lisätään kaikki kaariparit prioriteettijonoon, jotka lajitellaan kaariparien etäisyyksien perusteella 
         //pienimmästä suurimpaan
        for (int i = 0; i <this.graafi.getKaariListanKoko(); i++) {
            pq.add(this.graafi.getKaariLista().get(i));
        }
       
        int [] parent = new int[400];
       
        makeSet(parent);        
     
        int index = 0;
        while(index<398){
            Kaari kaari = pq.poll();
            
            int x_set = find(parent, kaari.getLahtoSolmu());
            int y_set = find(parent, kaari.getTuloSolmu());

            //tarkistetaan muodostuuko sykli
            //jos muodostuu, kaari hylätään
            if(x_set==y_set){
                
            }else {
                //muussa tapauksessa se lisätään minimivirittävään puuhun
                this.mst.add(kaari);
                index++;
                union(parent,x_set,y_set);
            }
            
        }
               
        
    }

    public void makeSet(int [] parent){
        
        for (int i = 0; i <399 ; i++) {
            parent[i] = i;
        }
    }

    public int find(int [] parent, int vertex){
        
        if(parent[vertex]!=vertex)
            return find(parent, parent[vertex]);
        return vertex;
    }

    public void union(int [] parent, int x, int y){
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }
    
    public void tulostaMinimiVirittavaPuu(){
        
            int idx = 0;
            for (int i = 0; i <this.mst.size() ; i++) {
                Kaari kaari = this.mst.get(i);
                System.out.println("Kaari-"+idx+":" + i + " lähtösolmu: " + kaari.getLahtoSolmu() +
                        " tulosolmu: " + kaari.getTuloSolmu() +
                        " etäisyys: " + kaari.getEtaisyys());
                idx++;
            }
    }
    
    public ArrayList<Integer> teeLeveysHakuMinimiVirittavassaPuussa(){
        
        LinkedList<Integer> jono = new LinkedList<>();
        
        ArrayList<Integer> tulostusLista = new ArrayList<>();
        
        int kasiteltavaSolmu = this.mst.get(0).getLahtoSolmu();
        
        this.graafi.getSolmut().get(kasiteltavaSolmu).setVisited();
        jono.add(kasiteltavaSolmu);
        
        while(jono.isEmpty() == false){
            
            tulostusLista.add(jono.removeFirst());
                                    
            for(Kaari kaari:this.mst){
                                
                try{
                
                    if(kaari.getLahtoSolmu() == kasiteltavaSolmu  
                            && this.graafi.getSolmut().get(kaari.getTuloSolmu()).getVisited() == false){
                        
                        jono.add(kaari.getTuloSolmu());
                    }
                    
                    if(kaari.getTuloSolmu() == kasiteltavaSolmu
                            && this.graafi.getSolmut().get(kaari.getLahtoSolmu()).getVisited() == false){
                        
                        jono.add(kaari.getLahtoSolmu());

                    }
                
                }catch (Exception e){
                
                }
            }
            
            try{
            
                kasiteltavaSolmu = jono.getFirst();
                this.graafi.getSolmut().get(kasiteltavaSolmu).setVisited();
                
            }catch (Exception e){
                
                
            }
            
        }
        /*int idx = 0;
        for(Integer solmu:tulostusLista){
            
            System.out.println("solmu nro "+idx+"--> " +solmu);
            idx++;
            
        }*/
        
        return tulostusLista;
    }


}

