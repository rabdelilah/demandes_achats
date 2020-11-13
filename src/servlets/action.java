package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.Authentif;
import services.services;
import dao.mssqlDao;

@WebServlet("/action")
public class action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	String dest="";
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SS");
	String op="";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		op=request.getParameter("op");
		session=request.getSession();
		
		if(op.equalsIgnoreCase("login")){
			login(request, response);
		}else if(op.equalsIgnoreCase("add_da")){
			add_da(request, response);
		}else if(op.equalsIgnoreCase("add_ligne")){
			add_ligne(request, response);
		}else if(op.equalsIgnoreCase("edit_ligne")){
			edit_ligne(request, response);
		}else if(op.equalsIgnoreCase("delete_ligne")){
			delete_ligne(request, response);
		}else if(op.equalsIgnoreCase("add_profil")){
			add_profil(request, response);
		}else if(op.equalsIgnoreCase("edit_profil")){
			edit_profil(request, response);
		}else if(op.equalsIgnoreCase("confirmation_da")){
			confirmation_da(request, response);
		}else if(op.equalsIgnoreCase("validation_da")){
			validation_da(request, response);
		}else if(op.equalsIgnoreCase("add_ligne_confirmation")){
			add_ligne_confirmation(request, response);
		}else if(op.equalsIgnoreCase("edit_ligne_confirmation")){
			edit_ligne_confirmation(request, response);
		}else if(op.equalsIgnoreCase("delete_ligne_confirmation")){
			delete_ligne_confirmation(request, response);
		}else if(op.equalsIgnoreCase("add_ligne_validation")){
			add_ligne_validation(request, response);
		}else if(op.equalsIgnoreCase("edit_ligne_validation")){
			edit_ligne_validation(request, response);
		}else if(op.equalsIgnoreCase("delete_ligne_validation")){
			delete_ligne_validation(request, response);
		}else if(op.equalsIgnoreCase("remove_profil")){
			remove_profil(request, response);
		}
		
		RequestDispatcher dispat = request.getRequestDispatcher(dest);
		dispat.forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authentif a=new Authentif();
		String login=request.getParameter("login");
		a.setS(request.getParameter("pwd"));
		List<String[]> user=mssqlDao.executeQuery("select PROT_User,PROT_Pwd from "+services.db_name+".dbo.F_PROTECTIONCIAL where PROT_User='"+login+"'");
		boolean flag=false;
		if(user.size()>0){
			if(login.equalsIgnoreCase(user.get(0)[0]) && a.getS().equalsIgnoreCase(user.get(0)[1])){
				//System.out.println("flag=true");
				flag=true;
				session.setAttribute("login", login);
			}
		}
		if(flag){
			dest="index.jsp";
		}else{
			dest="login.jsp";
		}
	}
	private void add_da(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=sdf.format(new Date());
		String req="insert into m_entete(date_doc,depot,reference,id_tiers,date_livraison,acheteur,cbmodification,etat) values('"+request.getParameter("date_doc")+"','"+request.getParameter("depot")+"','"+request.getParameter("reference")+"','"+request.getParameter("id_tiers")+"','"+request.getParameter("date_livraison")+"','"+request.getParameter("acheteur")+"','"+date+"','1')";
		mssqlDao.executeUpdate(req);
		dest="add_da.jsp?mode=editDoc&mode2=addLigne&id="+mssqlDao.executeQuery("select id from m_entete where cbmodification='"+date+"'").get(0)[0];
	}
	private void add_ligne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=sdf.format(new Date());
		String req="insert into m_lignes(reference_article,pu,qte,pt,id_doc,cbmodification) values('"+request.getParameter("reference_article")+"','"+request.getParameter("pu")+"','"+request.getParameter("qte")+"','"+request.getParameter("pt")+"','"+request.getParameter("id_doc")+"','"+date+"')";
		mssqlDao.executeUpdate(req);
		dest="add_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void edit_ligne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="update m_lignes set reference_article='"+request.getParameter("reference_article")+"',pu='"+request.getParameter("pu")+"',qte='"+request.getParameter("qte")+"',pt='"+request.getParameter("pt")+"',cbmodification='"+sdf.format(new Date())+"' where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="add_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void add_ligne_confirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=sdf.format(new Date());
		String req="insert into m_lignes(reference_article,pu,qte,pt,id_doc,cbmodification) values('"+request.getParameter("reference_article")+"','"+request.getParameter("pu")+"','"+request.getParameter("qte")+"','"+request.getParameter("pt")+"','"+request.getParameter("id_doc")+"','"+date+"')";
		mssqlDao.executeUpdate(req);
		dest="confirmation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void edit_ligne_confirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="update m_lignes set reference_article='"+request.getParameter("reference_article")+"',pu='"+request.getParameter("pu")+"',qte='"+request.getParameter("qte")+"',pt='"+request.getParameter("pt")+"',cbmodification='"+sdf.format(new Date())+"' where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="confirmation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void delete_ligne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("delete_ligne");
		String req="delete from m_lignes where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="add_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void delete_ligne_confirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("delete_ligne");
		String req="delete from m_lignes where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="confirmation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void add_ligne_validation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=sdf.format(new Date());
		String req="insert into m_lignes(reference_article,pu,qte,pt,id_doc,cbmodification) values('"+request.getParameter("reference_article")+"','"+request.getParameter("pu")+"','"+request.getParameter("qte")+"','"+request.getParameter("pt")+"','"+request.getParameter("id_doc")+"','"+date+"')";
		mssqlDao.executeUpdate(req);
		dest="validation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void edit_ligne_validation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="update m_lignes set reference_article='"+request.getParameter("reference_article")+"',pu='"+request.getParameter("pu")+"',qte='"+request.getParameter("qte")+"',pt='"+request.getParameter("pt")+"',cbmodification='"+sdf.format(new Date())+"' where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="validation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void delete_ligne_validation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("delete_ligne");
		String req="delete from m_lignes where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="validation_da.jsp?mode=editDoc&mode2=addLigne&id="+request.getParameter("id_doc");
	}
	private void add_profil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="insert into m_profil_user values('"+request.getParameter("id_user")+"','"+request.getParameter("id_profil")+"')";
		mssqlDao.executeUpdate(req);
		dest="profil.jsp?mode=add";
	}
	private void edit_profil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="update m_profil_user set id_user='"+request.getParameter("id_user")+"',id_profil='"+request.getParameter("id_profil")+"' where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="profil.jsp?mode=add";
	}
	private void confirmation_da(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ici confirmation_da");
		
		String req="update m_entete set etat='2' where id="+request.getParameter("id_doc");
		mssqlDao.executeUpdate(req);
		
		List<String[]> entete=mssqlDao.executeQuery("select id_tiers,depot from m_entete where id="+request.getParameter("id_doc"));
		/*
		 * ecrire dans la base sage i7
		 */
		String do_piece=services.getNewEnteteRadical();
		String date=sdf.format(new Date());
		String requete_entete="insert into "+services.db_name+".dbo.F_DOCENTETE(DO_Domaine,DO_Type,DO_Piece,DO_Date,DO_Tiers,DE_No,CBModification) values(1,12,'"+do_piece+"','"+date+"','"+entete.get(0)[0]+"','"+entete.get(0)[1]+"','"+date+"')";
		System.out.println(requete_entete);
		mssqlDao.executeUpdate(requete_entete);
		List<String[]> lignes=mssqlDao.executeQuery("select [reference_article],[pu],[qte],[pt],[id_doc],[cbmodification] from [m_lignes] where id_doc="+request.getParameter("id_doc"));
		for(String tab[]:lignes){
			double montant_ht=(Double.parseDouble(tab[1])*Double.parseDouble(tab[2]));
			double montant_ttc=montant_ht+(montant_ht*20/100);
			double DL_PUTTC=Double.parseDouble(tab[1])+(Double.parseDouble(tab[1])*19.6/100);
			String requete_ligne ="insert into "+services.db_name+".dbo.F_DOCLIGNE(DO_Domaine,"+
		                                                                          "DO_Type,"+
		                                                                          "CT_Num,"+
		                                                                          "DO_Piece,"+
		                                                                          "DO_Date,"+
		                                                                          "AR_Ref,"+
		                                                                          "DL_Design,"+
		                                                                          "DL_Qte,"+
		                                                                          "DL_PrixUnitaire,"+
		                                                                          "PF_Num,"+ //pour la version i7
		                                                                          "EU_Qte,"+
		                                                                          "DL_QteBC,"+
		                                                                          "DL_MontantHT,"+
		                                                                          "DL_MontantTTC,"+
		                                                                          "DL_PUTTC) "+
		                                                                   "values('1'"
		                                                                        +",'12"
		                                                                        +"','"+entete.get(0)[0]
		                                                                		+"','"+do_piece
		                                                                		+"','"+date
		                                                                		+"','"+tab[0]
		                                                                		+"','"+services.getIntituleArticle(tab[0])
		                                                                		+"','"+tab[2]
		                                                                		+"','"+tab[1]
		                                                                		+"','0" //pour la version i7
		                                                                		+"','"+tab[2]
		                                                                		+"','"+tab[2]
		                                                                		+"','"+montant_ht
		                                                                		+"','"+montant_ttc
		                                                                		+"','"+DL_PUTTC+"')";
			System.out.println(requete_ligne);
			mssqlDao.executeUpdate(requete_ligne);
		}
		
		dest="list.jsp";
	}
	private void validation_da(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="update m_entete set etat='3' where id="+request.getParameter("id_doc");
		mssqlDao.executeUpdate(req);
		dest="list.jsp";
	}
	private void remove_profil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req="delete from m_profil_user where id="+request.getParameter("id_ligne");
		mssqlDao.executeUpdate(req);
		dest="profil.jsp?mode=add";
	}
}
