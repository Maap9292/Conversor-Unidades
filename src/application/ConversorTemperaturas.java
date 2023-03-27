package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConversorTemperaturas extends ConversorMonedas implements Initializable {
	
	@FXML
	private ChoiceBox<String> panelConversorTemperaturas;

	private String[] temperaturas = {"Célsius - Farenheit", "Farenheit - Célsius", "Célsius - Kelvin", "Kelvin - Célsius", "Farenheit - Kelvin", "Kelvin - Farenheit"};

	@FXML
	private Button convertir;
	@FXML
	private Label resultado;
	@FXML
	private TextField valor;

	public String valorConvertir;

	public void conversor(ActionEvent event) { //convierte el valor ingresado según la selección del checkbox

		String temp = panelConversorTemperaturas.getValue();
		valorConvertir = valor.getText();
		
		if (validacionNumero(valorConvertir)) {
			
			double valorConvertido = Double.parseDouble(valorConvertir);
			
			switch (temp) {
			
				case "Célsius - Farenheit": {
					double conver = (valorConvertido *1.8)+32;
					convertirValor(conver, "F°");
					break;
				}
				
				case "Farenheit - Célsius": {
					double conver = (valorConvertido-32)/1.8;
					convertirValor(conver, "C°");
					break;
				}
				
				case "Célsius - Kelvin": {
					double conver = (valorConvertido+273.15);
					convertirValor(conver, "K");
					break;
				}
				
				case "Kelvin - Célsius": {
					double conver = (valorConvertido-273.15);
					convertirValor(conver, "C°");
					break;
				}
				
				case "Farenheit - Kelvin": {
					double conver = ((0.555555)*(valorConvertido-32))+273.15;
					convertirValor(conver, "K");
					break;
				}
				
				case "Kelvin - Farenheit": {
					double conver = ((1.8)*(valorConvertido-273.15))+32;
					convertirValor(conver, "F°");
					break;
				}	
			}		
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //inicializar el checkbox agregando los valores del panel de seleccion de temperatura
		panelConversorTemperaturas.getItems().addAll(temperaturas);
	}
	
	@Override
	public void switchScene(ActionEvent event) throws IOException { //lleva al menú principal
		// TODO Auto-generated method stub
		super.switchScene(event);
	}
	
}
