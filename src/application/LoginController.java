package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected;

	@FXML
	private TextField numeBox;
	
	@FXML
	private TextField passwordBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (loginModel.isDbConnected()) {
			isConnected.setText("Conectarea la BD efectuata");	
		} else {
			isConnected.setText("Nu s-a realizat conectarea la BD");
		}
	}
	
	public void login(ActionEvent event) {
		try {
			if(loginModel.isLogin(numeBox.getText(), passwordBox.getText())) {
				isConnected.setText("Logare reusita");
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/Tabela.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Administratie");
				primaryStage.show();
				
				// pass user to the next window (from login to tabela)
				TabelaController tabelaController = (TabelaController) loader.getController();
				tabelaController.user(numeBox.getText());
				
				
			} else {
				isConnected.setText("Logare nereusita");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			isConnected.setText("Logare nereusita");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clear(ActionEvent event) {
		numeBox.clear();
		passwordBox.clear();
		
	}
	
	
	@FXML
	public void textAction(KeyEvent e) {
	    if(e.getCode().toString().equals("ENTER")) {
	    	System.out.println("askakls");
	    }
	}

	
}
