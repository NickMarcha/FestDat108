package main;

// Klasse som validerer bruker input
public class Validering {

	public static boolean ValiderNavn(String navn) {
		return (navn.length() > 2 && navn.length() < 20) && navn.matches("[a-zA-Z æøåÆØÅ]+");
	}

	public static boolean ValiderMobil(String mobil) {
		return (mobil.length() == 8 && mobil.matches("[0-9]+"));
	}

	public static boolean ValiderKjonn(String kjonn) {
		return (kjonn.equals("mann") || kjonn.equals("kvinne"));
	}

	/*(?=.*[0-9]) a digit must occur at least once
(?=.*[a-z]) a lower case letter must occur at least once
(?=.*[A-Z]) an upper case letter must occur at least once
(?=.*[@#$%^&+=]) a special character must occur at least once
(?=\\S+$) no whitespace allowed in the entire string
.{8,} at least 8 characters
*/
	
	public static void main(String[] args) {
		System.out.println(ValiderPassord("1234ABcd&"));
	}
	public static boolean ValiderPassord(String pass) 
	{
		String passwordcheck = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.\\s+$).{8,}";
		
		return pass.matches(passwordcheck) && pass.length() < 20;
	}
	
	public static Boolean ValiderPassordRep(String pass, String rep) {
		return pass.equals(rep);
	}

	public static boolean gyldigDeltager(String fornavn, String etternavn, String mobil, String passord, String kjonn) {
		return (				
				ValiderNavn(fornavn) &&
				ValiderNavn(etternavn) &&
				ValiderMobil(mobil) &&
				ValiderKjonn(kjonn)
				//ValiderPassord(passord) 
				);
	}
	
	public static String ForceForbokstav(String input) {
		String forbokstav = input.substring(0, 1);
		forbokstav = forbokstav.toUpperCase();
		
		input = forbokstav + input.substring(1);
		
		return input;
	}
}
