package main;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (schema="oblig3", name="passord")
public class Passord {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int p_id;
	private String passord;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mobil_id", referencedColumnName = "mobil")
	private Deltager deltager;

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public Deltager getDeltager() {
		return deltager;
	}

	public void setDeltager(Deltager deltager) {
		this.deltager = deltager;
	}
}
