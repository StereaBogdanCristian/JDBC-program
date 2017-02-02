package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Locatari {
	
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nume;
	private final SimpleStringProperty prenume;
	private final SimpleIntegerProperty apartament;
	private final SimpleIntegerProperty suprafata;
	private final SimpleIntegerProperty nr_pers;
	private final SimpleStringProperty user;
	private final SimpleStringProperty password;
	private final SimpleIntegerProperty index_a_r;
	private final SimpleIntegerProperty index_a_c;
	private final SimpleIntegerProperty intretinere;
	private final SimpleIntegerProperty restante;
	private final SimpleStringProperty email;
	private final SimpleStringProperty tel;
	
	
	public Locatari(int id, String nume, String prenume,
			int apartament, int suprafata, int nr_pers,
			String user, String password, int index_a_r,
			int index_a_c, int intretinere, int restante,
			String email, String tel) {
		this.id = new SimpleIntegerProperty(id);
		this.nume = new SimpleStringProperty(nume);
		this.prenume = new SimpleStringProperty(prenume);
		this.apartament = new SimpleIntegerProperty(apartament);
		this.suprafata = new SimpleIntegerProperty(suprafata);
		this.nr_pers = new SimpleIntegerProperty(nr_pers);
		this.user = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(password);
		this.index_a_r = new SimpleIntegerProperty(index_a_r);
		this.index_a_c = new SimpleIntegerProperty(index_a_c);
		this.intretinere = new SimpleIntegerProperty(intretinere);
		this.restante = new SimpleIntegerProperty(restante);
		this.email = new SimpleStringProperty(email);
		this.tel = new SimpleStringProperty(tel);
	}


	public Integer getId() {
		return id.get();
	}


	public String getNume() {
		return nume.get();
	}


	public String getPrenume() {
		return prenume.get();
	}


	public Integer getApartament() {
		return apartament.get();
	}


	public Integer getSuprafata() {
		return suprafata.get();
	}


	public Integer getNr_pers() {
		return nr_pers.get();
	}


	public String getUser() {
		return user.get();
	}


	public String getPassword() {
		return password.get();
	}


	public Integer getIndex_a_r() {
		return index_a_r.get();
	}


	public Integer getIndex_a_c() {
		return index_a_c.get();
	}


	public Integer getIntretinere() {
		return intretinere.get();
	}


	public Integer getRestante() {
		return restante.get();
	}


	public String getEmail() {
		return email.get();
	}


	public String getTel() {
		return tel.get();
	}
	
	

}
