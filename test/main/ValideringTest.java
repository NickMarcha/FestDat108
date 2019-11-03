package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValideringTest {

	@Test
	void testNavnValidering() {
		String[] gyldige = new String [] {
				"Ava", "Edam", "Ole-Petter","Ørnulf-Åge","Ærnef"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ar?lf-ge","Ær_nef","erna"
		};


		for(String navn: gyldige) {
			try {
				assertEquals(true,Validering.ValiderNavn(navn));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + navn);
			}
		}

		for(String navn: ugyldige) {
			try {
				assertEquals(false, Validering.ValiderNavn(navn));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + navn);
			}
		}
	}

	@Test
	void testMobilValidering() {
		String[] gyldige = new String [] {
				"12345678", "98765432", "23232323"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ær?lf-ge","Ør_nef","erna","0938482","239474937",""
		};


		for(String mobil: gyldige) {
			try {
				assertEquals(true,Validering.ValiderMobil(mobil));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + mobil);
			}
		}

		for(String mobil: ugyldige) {
			try {
				assertEquals(false,Validering.ValiderMobil(mobil));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + mobil);
			}
		}
	}
	
	@Test
	void testPassordValidering() {
		String[] gyldige = new String [] {
				"ABcd123&", "BasdS123%!"
		};
		String[] ugyldige = new String [] {
				"", "sdfh", "ABcd 123&"
		};


		for(String passord: gyldige) {
			try {
				assertEquals(true,Validering.ValiderPassord(passord));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + passord);
			}
		}

		for(String passord: ugyldige) {
			try {
				assertEquals(false,Validering.ValiderPassord(passord));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + passord);
			}
		}
	}
	
	@Test
	void testKjonnValidering() {
		String[] gyldige = new String [] {
				"mann", "kvinne"
		};
		String[] ugyldige = new String [] {
				"", "vann", "sinne"
		};


		for(String kjonn: gyldige) {
			try {
				assertEquals(true,Validering.ValiderKjonn(kjonn));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + kjonn);
			}
		}

		for(String kjonn: ugyldige) {
			try {
				assertEquals(false,Validering.ValiderKjonn(kjonn));
			}catch(AssertionError error) {
				throw new AssertionError(error + ":::::: " + kjonn);
			}
		}
	}
	
	@Test
	void testPassordRepValidering() {
		try {
			assertEquals(true,Validering.ValiderPassordRep("string", "string"));
			assertEquals(false,Validering.ValiderPassordRep("string", "strin"));
		}catch(AssertionError error) {
			System.out.println(error);
			throw error;
		}
	}
}
