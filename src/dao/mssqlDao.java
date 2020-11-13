package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class mssqlDao {
	static Connection cnx=null;
	static Statement stm=null;
	static ResultSet rs=null;
	
	public static Connection getConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cnx = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GDA","sa","123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnx;
	}
	public static  List<String[]> executeQuery(String req){
		List<String[]> data=null;
		//System.out.println(req);
		try {
			cnx=getConnection();
			stm=cnx.createStatement();
	        rs=stm.executeQuery(req);
	        
	        int cols=rs.getMetaData().getColumnCount();
	          
	        data =new ArrayList<String[]>();
	        
	        while (rs.next()) {
	        	String tab[]=new String[cols];
	        	for(int j=0;j<cols;j++){
	        		tab[j]=rs.getString(j+1);
	        	}
	        	data.add(tab);
			}
	   
	        rs.close();
	        cnx.close();
	        
	       // System.out.println("count = "+data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public static  double getVal(String req){
		List<String[]> s=executeQuery(req);
		String valeur = "0";
		if(s.size()>0){
			valeur=s.get(0)[0];
		}
		return Double.parseDouble(valeur);
	}
	public static  String getValeur(String req){
		List<String[]> s=executeQuery(req);
		String valeur = "";
		if(s.size()>0){
			valeur=s.get(0)[0];
		}
		return valeur;
	}
	public static  String[] metaDataQuery(String req){
		String data[]=null;
		//System.out.println(req);
		try {
			cnx=getConnection();
			stm=cnx.createStatement();
	        rs=stm.executeQuery(req);
	        
	        int nbr=rs.getMetaData().getColumnCount();
	        data=new String[nbr];
	        
	        for(int i=1;i<=nbr;i++){
	        	data[i-1]=rs.getMetaData().getColumnLabel(i);
	        }
	   
	        rs.close();
	        cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public static  int getCount(String req){
		int count=0;
		try {
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return count;
	}
	public static  void executeUpdate(String req){
		try {
			cnx=getConnection();
			stm=cnx.createStatement();
	        stm.executeUpdate(req);
	        cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
