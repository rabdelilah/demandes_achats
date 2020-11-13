package export;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.mssqlDao;

/**
 * Servlet implementation class export
 */
@WebServlet("/export")
public class export extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
    String filePath;
    static HttpSession session ;
    
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String op= request.getParameter("op");
    	session=request.getSession();
    	String dest="";
    	
		if(op.equalsIgnoreCase("exportPdf")){
			exportPdf(request, response);
		}else if(op.equalsIgnoreCase("exportCsv")){
			exportCsv(request, response);
		}else if(op.equalsIgnoreCase("exportSql")){
			exportSql(request, response);
			dest="index.jsp";
		}
		
		RequestDispatcher dispat = request.getRequestDispatcher(dest);
		dispat.forward(request, response);
    }
	protected void exportSql(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data[][]=(String[][]) session.getAttribute("data");
		mssqlDao.executeUpdate("delete from etat_nombre_jours");
		for(int i=0;i<data.length;i++){
			String req="insert into etat_nombre_jours values('"+data[i][0]+"','"+data[i][1]+"',"+data[i][2]+","+data[i][3]+","+data[i][4]+","+data[i][5]+")";
			mssqlDao.executeUpdate(req);
		}
	}
	protected void exportPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filePath = "C://data//report.pdf";
		
		String[] col=(String[]) session.getAttribute("col");
		String data[][]=(String[][]) session.getAttribute("data");
		
		ExportToPDF.exportPDF(filePath, "Etat Nombre de jour par projet et consultatnt\n\r Date Début : "+request.getParameter("dated")+" - Date Fin : "+request.getParameter("datef"), col, data);
    	
		File file = new File(filePath);
        int length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength((int)file.length());
        String fileName = (new File(filePath)).getName();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
    
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)){
            outStream.write(byteBuffer,0,length);
        }
        
        in.close();
        outStream.close();
	}
	protected void exportCsv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filePath = "C://data//report.csv";
		
		String[] col=(String[]) session.getAttribute("col");
		String data[][]=(String[][]) session.getAttribute("data");
		
		ExportToExcel.exporter(filePath, "Etat Nombre de jour par projet et consultatnt\n\r Date Début : "+request.getParameter("dated")+" - Date Fin : "+request.getParameter("datef"), col, data);
    	
		File file = new File(filePath);
        int length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        response.setContentType("application/msexcel");
        response.setContentLength((int)file.length());
        String fileName = (new File(filePath)).getName();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
    
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)){
            outStream.write(byteBuffer,0,length);
        }
        
        in.close();
        outStream.close();
	}
		
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
