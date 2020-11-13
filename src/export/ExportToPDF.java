package export;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class ExportToPDF {
	
	
//la méthode qui test sur le format des cellule
	public static boolean isNumeric(String str){
		boolean flag=false;
	  try  {  
	    Double.parseDouble(str);
	    flag=true;
	  }catch(NumberFormatException nfe){  
	    flag= false;  
	  }  
	  return flag;  
	} 
	
	public static void exportPDF(String destination,String titre,String col[],String data[][]){
		Document d = new Document (PageSize.A3);
		try {
			PdfWriter.getInstance(d, new FileOutputStream(destination));
			d.open ();
			/*****************************Logo*********************************
			//Image image = Image.getInstance(imagechemin);
			//image.scaleAbsolute(100,100);
			//d.add(image);
			/******************************************************************/
			/*****************************Titre********************************/
			Paragraph paraRight = new Paragraph();
		    paraRight.setAlignment(Element.ALIGN_CENTER);
		    Font font1 = new Font(Font.HELVETICA  , 16, Font.BOLD);
		    paraRight.setFont(font1);
		    paraRight.add(titre);
		    d.add(paraRight);
		    /******************************************************************/
		    /*****************************Déclaration du Table*****************/
		    PdfPTable tab=new PdfPTable(col.length);
		    float[] columnWidths = new float[col.length];
		    tab.setWidthPercentage(100);
		    tab.setSpacingBefore(0f);
		    tab.setSpacingAfter(0f);
		    /******************************************************************/
		    /***************************Formater les cellules******************/
		    for(int i=0;i<data[0].length;i++){
		    	columnWidths[i]=getMaxSize(data, i)+2;
		    }
		    tab.setWidths(columnWidths);
		    /******************************************************************/
		    /******************************Table Header************************/
		    for(int i=0;i<col.length;i++){
		      PdfPCell cell = new PdfPCell(new Phrase(col[i]));
		      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	          tab.addCell(cell);
		    }
		    /******************************************************************/
		    /****************************Table Detail**************************/
		    for(int i = 0; i < data.length; i++){
		    	for(int j = 0; j < data[i].length; j++){
		    	    	 PdfPCell cell = new PdfPCell(new Phrase(data[i][j]));
		    	    	 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    	    	 cell.setPadding(8);
		    	    	 tab.addCell(cell);
		    	}
		    }
		    /******************************************************************/
		    d.add(new Paragraph("       "));
		    d.add(tab);
		    d.close ();
		   // Desktop.getDesktop().open(new File(destination));
		}catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("******** le PDF EST CREE ***************");
	}
	public static int getMaxSize(String data[][],int col){
		int max=0;
		for(int i=0;i<data.length;i++){
			if(data[i][col].length()>max){
				max=data[i][col].length();
			}
		}
		if(max>20){ max=20; }else if(max<4){ max=4; }
		//System.out.println("col : "+col+" - max : "+max);
		return max;
	}
}