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
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna",""
		};


		for(String navn: gyldige) {
			try {
				Assert.assertEquals(Validering.ValiderNavn(navn),true);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + navn);
			}
		}

		for(String navn: ugyldige) {
			try {
				Assert.assertEquals(Validering.ValiderNavn(navn),false);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + navn);
			}
		}
	}

	@Test
	void testMobilValidering() {
		String[] gyldige = new String [] {
				"12345678", "98765432", "23232323","Ørnulf-Åge","Ærnef"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna",""
		};


		for(String mobil: gyldige) {
			try {
				Assert.assertEquals(Validering.ValiderMobil(mobil),true);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + mobil);
			}
		}

		for(String mobil: ugyldige) {
			try {
				Assert.assertEquals(Validering.ValiderMobil(mobil),false);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + mobil);
			}
		}
	}
	
	@Test
	void testPassordValidering() {
		String[] gyldige = new String [] {
				"12345678", "98765432", "23232323","Ørnulf-Åge","Ærnef"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna",""
		};


		for(String passord: gyldige) {
			try {
				Assert.assertEquals(Validering.ValiderPassord(passord),true);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
			}
		}

		for(String passord: ugyldige) {
			try {
				Assert.assertEquals(Validering.ValiderPassord(passord),false);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
			}
		}
	}
	
	@Test
	void testKjonnValidering() {
		String[] gyldige = new String [] {
				"12345678", "98765432", "23232323","Ørnulf-Åge","Ærnef"
		};
		String[] ugyldige = new String [] {
				"Ava&", "E8dam", "Ole-Pet^ter","","Ør?lf-ge","Ær_nef","erna",""
		};


		for(String passord: gyldige) {
			try {
				Assert.assertEquals(Validering.ValiderKjonn(passord),true);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
			}
		}

		for(String passord: ugyldige) {
			try {
				Assert.assertEquals(Validering.ValiderKjonn(passord),false);
			}catch(AssertionError error) {
				System.out.println(error + " :::::: " + passord);
			}
		}
	}
	
	@Test
	void testPassordRepValidering() {
		
	}
}
