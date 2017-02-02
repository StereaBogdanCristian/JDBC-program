package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RestantieriController implements Initializable {
	
	@FXML
	private TableView<Locatari> tabelRestantieri;
	
	
	@FXML
	private TableColumn<Locatari, Integer> ap;
	@FXML
	private TableColumn<Locatari, String> nume;
	@FXML
	private TableColumn<Locatari, String> prenume;
	@FXML
	private TableColumn<Locatari, Integer> intretinere;
	@FXML
	private TableColumn<Locatari, Integer> restante;
	
	
	private ObservableList<Locatari> observbleList = FXCollections.observableArrayList();
	
	
	private Connection connection;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ap.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("apartament"));
		nume.setCellValueFactory(new PropertyValueFactory<Locatari, String>("nume"));
		prenume.setCellValueFactory(new PropertyValueFactory<Locatari, String>("prenume"));
		intretinere.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("intretinere"));
		restante.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("restante"));
		tabelRestantieri.setItems(observbleList);
		tabelRestantieri.setItems(getData());
		
	}


	public ObservableList<Locatari> getData() {
		connection = SqliteConnection.Conn();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from BDlocatari where restante>0");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nume = resultSet.getString("nume");
				String prenume = resultSet.getString("prenume");
				int apartament = resultSet.getInt("apartament");
				int suprafata = resultSet.getInt("suprafata");
				int nr_pers = resultSet.getInt("nr_pers");
				String user = resultSet.getString("user");
				String password = resultSet.getString("password");
				int index_a_r = resultSet.getInt("index_ap_r");
				int index_a_c = resultSet.getInt("index_ap_c");
				int intretinere = resultSet.getInt("intretinere");
				int restante = resultSet.getInt("restante");
				String email = resultSet.getString("email");
				String tel = resultSet.getString("tel");
				
				Locatari locatar = new Locatari(id, nume, prenume, apartament, suprafata, nr_pers, 
						user, password, index_a_r, index_a_c, intretinere, restante, email, tel);
				observbleList.add(locatar);
			}

		} catch (Exception e) {
			System.exit(1);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return observbleList;
	}
	

}
