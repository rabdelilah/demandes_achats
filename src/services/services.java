package services;

import java.util.List;

import dao.mssqlDao;

public class services {
	public static String db_name="bijou_i7";
	//public static String db_name="BIJOU";
	public static List<String[]> getDepot(){
		return mssqlDao.executeQuery("select DE_No,DE_Intitule from "+db_name+".dbo.F_DEPOT");
	}
	public static List<String[]> getFournisseur(){
		return mssqlDao.executeQuery("select CT_Num,CT_Intitule from "+db_name+".dbo.F_COMPTET where CT_Type=1");
	}
	public static List<String[]> getReferenceArticle(){
		return mssqlDao.executeQuery("select AR_Ref,AR_Design from "+db_name+".dbo.F_ARTICLE");
	}
	public static List<String[]> getArticlePrix(){
		return mssqlDao.executeQuery("select AR_Ref,AR_PrixVen from "+db_name+".dbo.F_ARTICLE");
	}
	public static List<String[]> getListeDa(String profil){
		if(profil.equals("2")){
			return mssqlDao.executeQuery("select id,date_doc,depot,reference,date_livraison,acheteur,id_tiers from m_entete where etat='1' ");
		}else if(profil.equals("3")){
			return mssqlDao.executeQuery("select id,date_doc,depot,reference,date_livraison,acheteur,id_tiers from m_entete where etat='1' ");
		}else if(profil.equals("4")){
			return mssqlDao.executeQuery("select id,date_doc,depot,reference,date_livraison,acheteur,id_tiers from m_entete where etat='3' ");
		}
		return null;
	}
	public static List<String[]> getUsers(){
		return mssqlDao.executeQuery("select PROT_User from "+db_name+".dbo.F_PROTECTIONCIAL");
	}
	public static List<String[]> getProfils(){
		return mssqlDao.executeQuery("select * from m_profil");
	}
	public static List<String[]> getProfilUser(){
		return mssqlDao.executeQuery("select pu.id,pu.id_user,p.intitule_profil from m_profil_user as pu,m_profil as p where pu.id_profil=p.id_profil");
	}
	public static String getProfil(String user){
		return mssqlDao.getValeur("select id_profil from m_profil_user where id_user='"+user+"'");
	}
	public static String getIntituleDepot(String de_no){
		return mssqlDao.getValeur("select DE_Intitule from "+db_name+".dbo.F_DEPOT where DE_No='"+de_no+"'");
	}
	public static String getIntituleClient(String CT_Num){
		return mssqlDao.getValeur("select CT_Intitule from "+db_name+".dbo.F_COMPTET where CT_Num='"+CT_Num+"'");
	}
	public static String getIntituleArticle(String ar_ref){
		return mssqlDao.getValeur("select AR_Design from "+db_name+".dbo.F_ARTICLE where AR_Ref='"+ar_ref+"'");
	}
	public static String getNewEnteteRadical() {
		String req="select top 1 do_piece from "+db_name+".dbo.F_DOCENTETE where do_piece Like('BC%') order by DO_Piece desc;";
		try {
			return icrement(mssqlDao.getValeur(req));
		} catch (Exception e) {
			return "BC000001";
		}
	}
	public static String icrement(String getdOPiece) {
		String partieEntiere = "", partieTexte = "";
		char[] temp = getdOPiece.toCharArray();
		int i=0;
		for (Character c : temp) {
			try {
				Integer.parseInt("" + c);
				if (c == '0')
					partieTexte += c;
				else{
					partieEntiere += getdOPiece.substring(i, getdOPiece.length());
					break;
				}
				i++;
			} catch (NumberFormatException ee) {
				i++;
				partieTexte += c;
			}
		}

		String increment = "" + (Integer.parseInt(partieEntiere) + 1);
		String nouveauEntete = "";
		if ((increment.length() > partieEntiere.length())&& (partieTexte.endsWith("0")))
			nouveauEntete = partieTexte.substring(0, partieTexte.length() - 1)+ increment;
		else
			nouveauEntete = partieTexte + increment;
		return nouveauEntete;
	}
}
