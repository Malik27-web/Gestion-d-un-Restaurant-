package application;

import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mesClasses.Article;
import mesClasses.Client;
import mesClasses.Plat;
import mesClasses.Reservation;

public class RestaurantController {
	@FXML
	private TextField tfClient;
	@FXML
	private TextField tfPlat;
	@FXML
	private TextField tfPrix;
	@FXML
	private Label tfInfo;
	@FXML
	private Label tfTotal;
	@FXML
	private ListView<Article> lvPlats;
	@FXML
	private ComboBox<Client> comboClients;

	public void init() throws Exception {

		Client c1 = new Client("Marius");
		Client c2 = new Client("fanny");
		Client.listeClients.addAll(c1, c2);
		comboClients.setItems(Client.listeClients);
		comboClients.getSelectionModel().select(c1);
		Reservation r1 = new Reservation(LocalTime.now(), c1, 4);
		Reservation r2 = new Reservation(LocalTime.now(), c2, 2);
		Plat p1 = new Plat(3, "Salade");
		Plat p2 = new Plat(10, "Tartare de boeuf");
		Plat p3 = new Plat(18, "Salade complete");
		r1.addArticle(p1);
		r1.addArticle(p2);
		r1.addArticle(p3);
		lvPlats.setItems(r1.getListeArticles());
		tfTotal.setText("Total=" + r1.montantFacture() + "$");

	}


	@FXML
	private void addClient() {
		String nomClient = tfClient.getText();
		try {
			Client nouveauClient = new Client(nomClient);
			comboClients.getSelectionModel().select(nouveauClient);
		} catch (Exception e) {
			tfInfo.setText("Le client "+ nomClient +"existe déja");
		}
	}
	
	@FXML
	private void addPlat() throws Exception {
	    int prix = Integer.parseInt(tfPrix.getText());
	    String nomPlat = tfPlat.getText();
	    
	    Plat plat = new Plat(prix, nomPlat);

	    Client clientSelectionne =comboClients.getSelectionModel().getSelectedItem(); 

	    Reservation reservationClient = clientSelectionne.reserveClient();
	    if(reservationClient==null) {
	    	reservationClient=new Reservation(LocalTime.now(), clientSelectionne, 2);
	    }
	    
	        reservationClient.addArticle(plat);
	        lvPlats.setItems(reservationClient.getListeArticles());
	        tfTotal.setText("Total = " + reservationClient.montantFacture() + "$");
	    	tfPlat.setText(null);
			tfPrix.setText(null);
	   
	}
	@FXML
	private void actionSupprimer() {
		
	Plat platSelectionne= (Plat) lvPlats.getSelectionModel().getSelectedItem();
	Client clientSelectionne =comboClients.getSelectionModel().getSelectedItem(); 
	Reservation reservationClient = clientSelectionne.reserveClient();
	if(reservationClient!=null) {
		reservationClient.getListeArticles().remove(platSelectionne);
		lvPlats.refresh(); 
		tfTotal.setText("Total = " + reservationClient.montantFacture() + "$");
		tfPlat.setText(null);
		tfPrix.setText(null);
	}
	
	
	}




	
	

}
