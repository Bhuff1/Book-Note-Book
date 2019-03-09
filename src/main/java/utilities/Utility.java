package utilities;

public final class Utility {

	public static String formatDate(String date) {
		String newDate = date.substring(0, 10);
    	StringBuilder sb = new StringBuilder(newDate);
    	sb.setCharAt(4, '/');
    	sb.setCharAt(7, '/');
    	sb.append(" 00:00:00");
    	return sb.toString();
    }
	
	public static String removeCommas(String value) {
    	CharSequence s1 = ",,,,";
    	CharSequence s2 = "";
    	return value.replace(s1, s2);	
    
    }
    
    public static String removeApostrophes(String value) {
    	CharSequence s1 = "\'";
    	CharSequence s2 = "''";
    	return value.replace(s1, s2);
    }
	
}
