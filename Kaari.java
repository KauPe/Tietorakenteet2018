
public class Kaari {
    
    private int lahtoSolmunIndeksi;
    private int tuloSolmunIndeksi;
    public Float etaisyysAJaBValilla;
        
    public Kaari(int lahtoSolmunIndeksi, int tuloSolmunIndeksi, Float etaisyys){
        
        this.lahtoSolmunIndeksi = lahtoSolmunIndeksi;
        this.tuloSolmunIndeksi = tuloSolmunIndeksi;
        this.etaisyysAJaBValilla = etaisyys;
        
    }
    public Float getEtaisyys(){
        
        return this.etaisyysAJaBValilla;
        
    }
    
    public int getLahtoSolmu(){
        
        return this.lahtoSolmunIndeksi;
    }
    
    public int getTuloSolmu(){
        
        return this.tuloSolmunIndeksi;
    }
    
    
}
