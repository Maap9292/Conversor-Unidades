package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ControladorDeEscenas implements Initializable {
	public Stage stage;
	public Scene scene;
	public Parent root;

	@FXML
	private ChoiceBox<String> panelGenerales;

	private String[] opciones = { "Convertir monedas", "Convertir temperatura", "Convertir medidas" };

	@FXML
	private Button ir;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		panelGenerales.getItems().addAll(opciones);
	}
	
	public void menuMonedas (ActionEvent event) throws IOException { //lleva al conversor de monedas
		root = FXMLLoader.load(getClass().getResource("Monedas.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void menuTemperatura (ActionEvent event) throws IOException { //lleva al conversor de temperaturas
		root = FXMLLoader.load(getClass().getResource("Temperaturas.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void menuMedidas (ActionEvent event) throws IOException { //lleva al conversor de medidas
		root = FXMLLoader.load(getClass().getResource("Medidas.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchScene2(ActionEvent event) throws IOException { //relaciona las escenas con el array de elección múltiple

		String eleccionPrincipal = panelGenerales.getValue(); //convierte a string la selección del checkbox

		if (eleccionPrincipal == opciones[0]) { //abre conversor monedas
			
			menuMonedas(event);

		} else if (eleccionPrincipal == opciones[1]) { //abre conversor temperaturas
			
			menuTemperatura(event);
		} else if (eleccionPrincipal == opciones[2]) { //abre conversor medidas
			
			menuMedidas(event);
		}
		
	}	
		
}
