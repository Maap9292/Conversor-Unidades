package application;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConversorMonedas implements Initializable {

	public Stage stage;
	public Scene scene;
	public Parent root;
	
	@FXML
	private ChoiceBox<String> panelConversorMonedas;

	private String[] monedas = { "USD-EUR", "USD-GBR", "USD-YEN", "USD-WON", "EUR-USD", "GBR-USD", "WON-USD",
			"YEN-USD" };
	@FXML
	private Button convertir;
	@FXML
	private Label resultado;
	@FXML
	private TextField valor;
	public String valorConvertir;
	public double valorfinal;

	public double apiQuery(String para, String de, double valor) { //llamada a la API de exchange
		
		try {
			URL url = new URL ("https://api.apilayer.com/exchangerates_data/convert?to="+para+"&from="+de+"&amount="+valor+"&apikey=CJegLPN7N7Th7bYy29olaWjQeT47ZnZs");
			HttpURLConnection conn =(HttpURLConnection) url.openConnection() ;
			conn.setRequestMethod("GET");
			conn.setRequestProperty("apikey", "CJegLPN7N7Th7bYy29olaWjQeT47ZnZs");
			
			int responseCode = conn.getResponseCode();
			
			if (responseCode != 200) {
				throw new RuntimeException("Ocurrio un error");
			} else {				
				List<String> informationString= new ArrayList<String>();
				Scanner scanner = new Scanner(url.openStream());
				
				while (scanner.hasNext()) {
					informationString.add(scanner.nextLine());
				}				
				scanner.close();
				String valorCambio3 = (String)informationString.get(9);
				valorCambio3 = valorCambio3.replaceAll("\"rate\": ","");
				valorfinal = Double.parseDouble(valorCambio3);
			}		
			conn.disconnect();			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return valorfinal;
	}
	
	public boolean validacionNumero(String numero) { //verifica si el valor ingresado es un número, si no, escribe que se debe ingresar un número
		
		this.valorConvertir = numero;
		
		try {
			Double.parseDouble(numero);
			return true;
		} catch (Exception error) {
			resultado.setText("Debe Ingresar un número");
			return false;
		}
	}
	
	public void convertirValor(double conver) { //muestra el resultado de la operación en el textfield
		String convers = String.valueOf(conver);
		resultado.setText("El resultado es " +convers);
	}
	
	public void conversor(ActionEvent event) {

		String moned = panelConversorMonedas.getValue();
		valorConvertir = valor.getText();
		
		if (validacionNumero(valorConvertir)) {
			
			double valorConvertido = Double.parseDouble(valorConvertir);
			
			switch (moned) {
			
				case "USD-EUR": {
					apiQuery("EUR", "USD", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "USD-GBR": {
					apiQuery("GBP", "USD", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "USD-YEN": {
					apiQuery("JPY", "USD", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
			
				case "USD-WON": {
					apiQuery("KRW", "USD", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "EUR-USD": {
					apiQuery("USD", "EUR", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "GBR-USD": {
					apiQuery("USD", "GBP", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "WON-USD": {
					apiQuery("USD", "KRW", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}
				
				case "YEN-USD": {
					apiQuery("USD", "JPY", valorConvertido);
					double conver = Math.round(((valorConvertido) * valorfinal)*100.0)/100.0;
					convertirValor(conver);
					break;
				}		
			}		
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		panelConversorMonedas.getItems().addAll(monedas);
	}

	public void switchScene(ActionEvent event) throws IOException { //lleva al menú principal
		root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Conversor");
		stage.show();
	}
	
}
