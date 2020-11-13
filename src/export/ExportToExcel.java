package export;

import java.io.FileWriter;

public class ExportToExcel {


	public static void exporter(String destination,String titre,String col[],String data[][]){
		try{
	
		 FileWriter excel = new FileWriter(destination);
		 excel.write(titre);
		 excel.write("\n\n");

         for(int i = 0; i < col.length; i++){
            excel.write(col[i] + ";");
         }

         excel.write("\n");

         for(int i=0; i< data.length; i++) {
            for(int j=0; j <data[i].length; j++) {
                excel.write(data[i][j]+";");
            }
            excel.write("\n");
        }

        excel.close();
        
		//Desktop.getDesktop().open(new File(destination));
        
	    } catch(Exception err){
	    	err.printStackTrace();
	    }
	  } 
}
