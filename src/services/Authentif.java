package services;


import java.util.HashMap;

/**
 *
 * @author 
 */
public class Authentif {
    private HashMap<String,String> hashMap;
    private String s;
    
    public Authentif() {
        System.setProperty( "file.encoding", "UTF-8" );
        hashMap = new HashMap<String,String>();
        
        hashMap.put("a", "Ë");
		hashMap.put("b", "È");
		hashMap.put("c", "É");
		hashMap.put("d", "Î");
		hashMap.put("e", "Ï");
		hashMap.put("f", "Ì");
		hashMap.put("g", "Í");
		hashMap.put("h", "Â");
		hashMap.put("i", "Ã");
		hashMap.put("j", "À");
		hashMap.put("k", "Á");
		hashMap.put("l", "Æ");
		hashMap.put("m", "Ç");
		hashMap.put("n", "Ä");
		hashMap.put("o", "Å");
		hashMap.put("p", "Ú");
		hashMap.put("q", "Û");
		hashMap.put("r", "Ø");
		hashMap.put("s", "Ù");
		hashMap.put("t", "Þ");
		hashMap.put("u", "ß");
		hashMap.put("v", "Ü");
		hashMap.put("w", "Ý");
		hashMap.put("x", "Ò");
		hashMap.put("y", "Ó");
		hashMap.put("z", "Ð");
		hashMap.put("1", "›");
		hashMap.put("2", "˜");
		hashMap.put("3", "™");
		hashMap.put("4", "ž");
		hashMap.put("5", "Ÿ");
		hashMap.put("6", "œ");
		hashMap.put("7", "");
		hashMap.put("8", "’");
		hashMap.put("9", "“");
		hashMap.put("0", "š");
		hashMap.put("&", "Œ");
		hashMap.put("é", "C");
		hashMap.put("\"", "ˆ");
		hashMap.put("'", "");
		hashMap.put("(", "‚");
		hashMap.put("-", "‡");
		hashMap.put("è", "B");
		hashMap.put("_", "õ");
		hashMap.put("ç", "M");
		hashMap.put("à", "J");
		hashMap.put(")", "ƒ");
		hashMap.put("=", "—");
		hashMap.put("}", "×");
		hashMap.put("", "÷");
		hashMap.put("@", "ê");
		hashMap.put("^", "ô");
		//hashMap.put("\", "ö");
		hashMap.put("`", "Ê");
		hashMap.put("|", "Ö");
		hashMap.put("[", "ñ");
		hashMap.put("{", "Ñ");
		hashMap.put("#", "‰");
		hashMap.put("*", "€");
		hashMap.put("$", "Ž");
		hashMap.put("/", "…");
		hashMap.put(":", "");
		hashMap.put(",", "†");
		hashMap.put(";", "‘");
		hashMap.put("+", "");
		hashMap.put("?", "•");
		hashMap.put(".", "„");
		hashMap.put("!", "‹");
    }

    public String getS() {
        
        String x="";
        for (int i =0;i<s.length();i++){
            String a = Character.toString(s.charAt(i));
            x+=hashMap.get(a);
        }
        return x;
    }

    public void setS(String s) {
    	
        this.s = s;
    }
 
}
