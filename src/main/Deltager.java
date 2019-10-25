package main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (schema="oblig3", name="deltager")
public class Deltager {
		
	@Id
	private String fornavn;
	private String etternavn;
	private String mobil;
	private String kjonn;
	
	@OneToOne(mappedBy="deltager")
	private Passord passord;
	
	public String getFornavn() {
		return fornavn;
	}
	public String getFornavnLower() {
		return fornavn.toLowerCase();
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public String getEtternavnLower() {
		return etternavn.toLowerCase();
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public Passord getPassord() {
		return passord;
	}
	public void setPassord(Passord passord) {
		this.passord = passord;
	}
	
	
	
	
//	passord kan hentes i annet table. Trenger vi relasjon? Skal jo ikke lagre passord som objekt i kode...
//	private String passord;

}
