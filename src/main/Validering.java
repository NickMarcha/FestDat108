package main;

import java.util.regex.Pattern;

// Klasse som validerer bruker input
public class Validering {

	public static Boolean ValiderNavn(String navn) {
		return (navn.length() > 2 && navn.length() < 20) && Pattern.matches("[A-ZÆØÅ]", navn.substring(0, 0)) && Pattern.matches("[a-zA-Z æøåÆØÅ]", navn);
	}

	public static Boolean ValiderMobil(String mobil) {
		return (mobil.length() == 8 && Pattern.matches("[0-9]", mobil));
	}

	public static Boolean ValiderKjonn(String kjonn) {
		return (kjonn == "mann" || kjonn == "kvinne");
	}
	
	public static Boolean ValiderPassordRep(String pass, String rep) {
		return pass.equals(rep);
	}

	/*(?=.*[0-9]) a digit must occur at least once
(?=.*[a-z]) a lower case letter must occur at least once
(?=.*[A-Z]) an upper case letter must occur at least once
(?=.*[@#$%^&+=]) a special character must occur at least once
(?=\\S+$) no whitespace allowed in the entire string
.{8,} at least 8 characters
*/
	public static Boolean ValiderPassord(String pass) 
	{
		String passwordcheck = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}";
		
		return Pattern.matches(passwordcheck, pass) && pass.length() <20;
	}

	public static boolean gyldigDeltager(String fornavn, String etternavn, String mobil, String passord, String passordRep, String kjonn) {
		return (
				ValiderNavn(fornavn) && 
				ValiderNavn(etternavn) &&
				ValiderMobil(mobil) &&
				ValiderKjonn(kjonn) && 
				ValiderPassord(passord)&&
				ValiderPassordRep(passord, passordRep)
				);
	}
	
	public static String ForceForbokstav(String input) {
		String forbokstav = input.substring(0, 1);
		forbokstav = forbokstav.toUpperCase();
		
		input = forbokstav + input.substring(1);
		
		return input;
	}
}
