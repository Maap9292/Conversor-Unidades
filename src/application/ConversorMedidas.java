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

public class ConversorMedidas extends ConversorMonedas implements Initializable {
	
	@FXML
	private ChoiceBox<String> panelConversorMedidas;

	private String[] medidas = {"Metros - Centímetros", "Centímetros - Metros", "Metros - Kilómetros", "Kilómetros - Metros"};

	@FXML
	private Button convertir;
	@FXML
	private Label resultado;
	@FXML
	private TextField valor;

	public String valorConvertir;

	public void conversor(ActionEvent event) { //convierte el valor ingresado según la selección del checkbox

		String med = panelConversorMedidas.getValue();
		valorConvertir = valor.getText();
		
		if (validacionNumero(valorConvertir)) {
			
			double valorConvertido = Double.parseDouble(valorConvertir);
			
			switch (med) {
			
				case "Metros - Centímetros": {
					double conver = (valorConvertido/100) ;
					convertirValor(conver);
					break;
				}
				
				case "Centímetros - Metros": {
					double conver = (valorConvertido*100);
					convertirValor(conver);
					break;
				}
				
				case "Metros - Kilómetros": {
					double conver = (valorConvertido/1000);
					convertirValor(conver);
					break;
				}
				
				case "Kilómetros - Metros": {
					double conver = (valorConvertido*1000);
					convertirValor(conver);
				}
			}			
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //inicializar el checkbox agregando los valores del panel de seleccion de temperatura
		panelConversorMedidas.getItems().addAll(medidas);
	}
	
	@Override
	public void switchScene(ActionEvent event) throws IOException { //lleva al menú principal
		// TODO Auto-generated method stub
		super.switchScene(event);
	}
	
}
