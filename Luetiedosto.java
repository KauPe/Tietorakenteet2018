import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Luetiedosto {
    
    private BufferedReader reader;
    private String line;
    private float x[];
    private float y[];    
    private ArrayList<float[]> dataTaulukot;
    
    public Luetiedosto(BufferedReader reader){
        
        this.reader = reader;
        this.x=new float[400];
	this.y=new float[400];
        this.dataTaulukot = new ArrayList<>();
        
        this.dataTaulukot.add(x);
        this.dataTaulukot.add(y);
    }
    
    public void lueTiedosto(Graafi graafi){
               
        for(int i=0; i<400; i++){
                        
            try {
                
                line=this.reader.readLine();
                String[] values=line.split(",");	
                
                x[i]=Float.parseFloat(values[0]);	
                y[i]=Float.parseFloat(values[1]);				  
                
                //System.out.print(x[i]+" , "+y[i]+"\n");
                
                Node solmu = new Node(x[i], y[i]);
                graafi.tallennaSolmut(i, solmu);
                              
                
            } catch(IOException e){
                System.out.println("Virhe tiedoston luvussa.");
            }
            
        }
        //Tallennetaan solmuille lähin naapuri
        //etaisyys.kayDataLapiJaTallennaNaapureita(x, y);
        
    }
    public ArrayList<float[]> getDataTaulukot(){
        
        return this.dataTaulukot;
        
    }
    
}
