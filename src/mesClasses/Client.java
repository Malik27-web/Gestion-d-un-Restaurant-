package mesClasses;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Client {
	private String nom;
	public static  ObservableList<Client> listeClients =FXCollections.observableArrayList();
	public Client(String nom)throws Exception {
		super();
		this.nom = nom;
		if(!listeClients.contains(this)) {
			listeClients.add(this);
		}
		else {
			throw new Exception("Le client existe déja");
		}
	}

	@Override
	public String toString() {
		return " "+nom+" ";
	}

	public String getNom() {
		return nom;
	}
	public Reservation reserveClient() {
	    for (Reservation reservation : Reservation.listeReservations) {
	        if (reservation.getClient().equals(this)) {
	            return reservation;
	        }
	    }
	   
	    return null; 
	}


	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(nom, other.nom);
	}
	

	
}
