package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ValideringTest {

	@Test
	void testNavnValidering() {
		String[] gyldige = new String [] {
				"Ava", "Edam", "Ole-Petter","Ørnulf-Åge","Ærnef"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna"
		};


		for(String navn: gyldige) {
			try {
				Assert.assertEquals(true,Validering.ValiderNavn(navn));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + navn);
			}
		}

		for(String navn: ugyldige) {
			try {
				Assert.assertEquals(false, Validering.ValiderNavn(navn));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + navn);
			}
		}
	}

	@Test
	void testMobilValidering() {
		String[] gyldige = new String [] {
				"12345678", "98765432", "23232323"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna",""
		};


		for(String mobil: gyldige) {
			try {
				Assert.assertEquals(true,Validering.ValiderMobil(mobil));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + mobil);
			}
		}

		for(String mobil: ugyldige) {
			try {
				Assert.assertEquals(false,Validering.ValiderMobil(mobil));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + mobil);
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
				Assert.assertEquals(true,Validering.ValiderPassord(passord));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
			}
		}

		for(String passord: ugyldige) {
			try {
				Assert.assertEquals(false,Validering.ValiderPassord(passord));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
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
				Assert.assertEquals(true,Validering.ValiderKjonn(kjonn));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + kjonn);
			}
		}

		for(String kjonn: ugyldige) {
			try {
				Assert.assertEquals(false,Validering.ValiderKjonn(kjonn));
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + kjonn);
			}
		}
	}
	
	@Test
	void testPassordRepValidering() {
		try {
			Assert.assertEquals(true,Validering.ValiderPassordRep("string", "string"));
			Assert.assertEquals(false,Validering.ValiderPassordRep("string", "strin"));
		}catch(AssertionError error) {
			System.out.println(error);
		}
	}
}
