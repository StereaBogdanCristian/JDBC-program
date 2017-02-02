package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TabelaController implements Initializable {
	
	@FXML
	private Label usertabela;
	
	
	@FXML
	private TableView<Locatari> tableID;
	private ObservableList<Locatari> observbleList = FXCollections.observableArrayList();
	
	@FXML
	private TableColumn<Locatari, Integer> id;
	@FXML
	private TableColumn<Locatari, String> nume;
	@FXML
	private TableColumn<Locatari, String> prenume;
	@FXML
	private TableColumn<Locatari, Integer> apartament;
	@FXML
	private TableColumn<Locatari, Integer> suprafata;
	@FXML
	private TableColumn<Locatari, Integer> nr_pers;
	@FXML
	private TableColumn<Locatari, String> user;
	@FXML
	private TableColumn<Locatari, String> password;
	@FXML
	private TableColumn<Locatari, Integer> index_a_r;
	@FXML
	private TableColumn<Locatari, Integer> index_a_c;
	@FXML
	private TableColumn<Locatari, Integer> intretinere;
	@FXML
	private TableColumn<Locatari, Integer> restante;
	@FXML
	private TableColumn<Locatari, String> email;
	@FXML
	private TableColumn<Locatari, String> tel;
	
	
	@FXML
	private TextField textID;
	@FXML
	private TextField textNume;
	@FXML
	private TextField textPrenume;
	@FXML
	private TextField textApartament;
	@FXML
	private TextField textSuprafata;
	@FXML
	private TextField textNr_pers;
	@FXML
	private TextField textUser;
	@FXML
	private TextField textPassword;
	@FXML
	private TextField textIndex_a_r;
	@FXML
	private TextField textIndex_a_c;
	@FXML
	private TextField textIntretinere;
	@FXML
	private TextField textRestante;
	@FXML
	private TextField textemail;
	@FXML
	private TextField textTel;
	
	
	@FXML
	private Button salveaza;
	@FXML
	private Button sterge;
	@FXML
	private Button modifica;
	
	
	@FXML
	private Label attention;
	
	private Connection connection;
	
	private int idModifica = -1;
	private int indexTable;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// selectarea mai multor elemente din lista
		// tableID.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		id.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("id"));
		nume.setCellValueFactory(new PropertyValueFactory<Locatari, String>("nume"));
		prenume.setCellValueFactory(new PropertyValueFactory<Locatari, String>("prenume"));
		apartament.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("apartament"));
		suprafata.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("suprafata"));
		nr_pers.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("nr_pers"));
		user.setCellValueFactory(new PropertyValueFactory<Locatari, String>("user"));
		password.setCellValueFactory(new PropertyValueFactory<Locatari, String>("password"));
		index_a_r.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("index_a_r"));
		index_a_c.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("index_a_c"));
		intretinere.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("intretinere"));
		restante.setCellValueFactory(new PropertyValueFactory<Locatari, Integer>("restante"));
		email.setCellValueFactory(new PropertyValueFactory<Locatari, String>("email"));
		tel.setCellValueFactory(new PropertyValueFactory<Locatari, String>("tel"));
		tableID.setItems(getData());
		attention.setText("");
		
	}
	
	// Read Locatari from DB
	public ObservableList<Locatari> getData()  {
		connection = SqliteConnection.Conn();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement("select * from BDlocatari");
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
	
	// First Label, left up
	public void user(String user) {
		usertabela.setText("Salut, " + user);
	}

	
	// Salveaza Button
	public void salveaza(ActionEvent e) {
		// validate data
		String date[] = {textID.getText(), textApartament.getText(),textSuprafata.getText(), 
				textNr_pers.getText(), textIndex_a_r.getText(), textIndex_a_c.getText(), 
				textIntretinere.getText(),textRestante.getText()};
		
		if (validateData(date)) {
			if (idModifica<0) {
	
		//read from TextFields and write new data into Table
		Locatari locatar = readLocatarFromTextFields();
		tableID.getItems().add(locatar);
		
		//write new data (Locatar) into DB
		connection = SqliteConnection.Conn();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into BDlocatari values (null, '" + 
					locatar.getNume() + "', '" + 
					locatar.getPrenume() + "', " + 
					locatar.getApartament() + ", " + 
					locatar.getSuprafata() + ", " +
					locatar.getNr_pers() + ", '" + 
					locatar.getUser() + "', '" + 
					locatar.getPassword() + "', " + 
					locatar.getIndex_a_r() + ", " + 
					locatar.getIndex_a_c() + ", " + 
					locatar.getIntretinere() + ", " + 
					locatar.getRestante() + ", '" + 
					locatar.getEmail() + "', '" + 
					locatar.getTel() + "')");
			statement.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		clearTextFields();
		
			} else {//idModifica>0 - when change some data from a row
				
				//read data from TextFields and write new data into Table at specified row
				Locatari locatar = readLocatarFromTextFields();
				tableID.getItems().set(indexTable, locatar);
				
				connection = SqliteConnection.Conn();
				
				try {
					Statement statement = connection.createStatement();
					statement.executeUpdate("update BDlocatari set nume='" + 
							locatar.getNume() + "', prenume='" + 
							locatar.getPrenume() + "', apartament=" + 
							locatar.getApartament() + ", suprafata=" + 
							locatar.getSuprafata() + ", nr_pers=" + 
							locatar.getNr_pers() + ", user='" + 
							locatar.getUser() + "', password='" + 
							locatar.getPassword() + "', index_ap_r=" + 
							locatar.getIndex_a_r() + ", index_ap_c=" + 
							locatar.getIndex_a_c() + ", intretinere=" + 
							locatar.getIntretinere() + ", restante=" + 
							locatar.getRestante() + ", email='" + 
							locatar.getEmail() + "', tel='" + 
							locatar.getTel() + "' where id=" + idModifica);
					statement.close();
					idModifica = -1;
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				clearTextFields();
			}
		}
	}
	
	public Locatari readLocatarFromTextFields() {
		Locatari locatar = new Locatari (
				Integer.parseInt(textID.getText()), 
				textNume.getText(), textPrenume.getText(), 
				Integer.parseInt(textApartament.getText()),
				Integer.parseInt(textSuprafata.getText()),
				Integer.parseInt(textNr_pers.getText()), 
				textUser.getText(), textPassword.getText(),
				Integer.parseInt(textIndex_a_r.getText()), 
				Integer.parseInt(textIndex_a_c.getText()), 
				Integer.parseInt(textIntretinere.getText()),
				Integer.parseInt(textRestante.getText()), 
				textemail.getText(), textTel.getText());
		
		return locatar;
	}
	
	// clear TextFields
	public void clearTextFields() {
		textID.setText("id"); textNume.setText("Nume"); textPrenume.setText("Prenume"); 
		textSuprafata.setText("Suprafata"); textNr_pers.setText("Nr persoane");
		textUser.setText("user"); textPassword.setText("Password"); textIndex_a_c.setText("Apa calda"); 
		textIndex_a_r.setText("Apa rece"); textIntretinere.setText("Intretinere");
		textRestante.setText("Restante"); textemail.setText("Mail"); textTel.setText("Telefon"); 
		textApartament.setText("Ap");
		attention.setText("");
	}
	
	// delete button
	public void sterge(ActionEvent e) {
		if (!tableID.getSelectionModel().getSelectedItems().isEmpty()) {
			
			//delete data from tabel
			observbleList.remove(tableID.getSelectionModel().getSelectedItem());
			
			//delete data from DB
			connection = SqliteConnection.Conn();
			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate("delete from BDlocatari where id = " + tableID.getSelectionModel().getSelectedItem().getId());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					
					try {
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
			// clear TextFields
			clearTextFields();
		}
	}
	
	
	//modifica button
	public void modifica() {
		if (!tableID.getSelectionModel().getSelectedItems().isEmpty()) {
			
			//set in textfields the data of selected row
			textID.setText(tableID.getSelectionModel().getSelectedItem().getId().toString()); 
			textNume.setText(tableID.getSelectionModel().getSelectedItem().getNume()); 
			textPrenume.setText(tableID.getSelectionModel().getSelectedItem().getPrenume());
			textApartament.setText(tableID.getSelectionModel().getSelectedItem().getApartament().toString());
			textSuprafata.setText(tableID.getSelectionModel().getSelectedItem().getSuprafata().toString()); 
			textNr_pers.setText(tableID.getSelectionModel().getSelectedItem().getNr_pers().toString());
			textUser.setText(tableID.getSelectionModel().getSelectedItem().getUser()); 
			textPassword.setText(tableID.getSelectionModel().getSelectedItem().getPassword()); 
			textIndex_a_c.setText(tableID.getSelectionModel().getSelectedItem().getIndex_a_c().toString());
			textIndex_a_r.setText(tableID.getSelectionModel().getSelectedItem().getIndex_a_r().toString()); 
			textIntretinere.setText(tableID.getSelectionModel().getSelectedItem().getIntretinere().toString());
			textRestante.setText(tableID.getSelectionModel().getSelectedItem().getRestante().toString());
			textemail.setText(tableID.getSelectionModel().getSelectedItem().getEmail()); 
			textTel.setText(tableID.getSelectionModel().getSelectedItem().getTel());  
			idModifica = tableID.getSelectionModel().getSelectedItem().getId();
			indexTable = tableID.getSelectionModel().getSelectedIndex();
		}
	}
	
	
	public boolean validateData(String[] date) {
		try {
			for (int i = 0; i < date.length; i++) {
				Integer.parseInt(date[i]);
			}
			return true;
		} catch (Exception e) {
			attention.setText("Atentie la datele introduse!!!!!");
			return false;
		}
		
	}
	
	public void restantieri (ActionEvent e) {
		
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root1 = null;
		try {
			root1 = loader.load(getClass().getResource("/application/Restantieri.fxml").openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scene scene = new Scene(root1);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Restantieri");
		primaryStage.show();
	}
	
	public void renunta (ActionEvent e) {
		// clear TextFields
		textID.setText("id");; textNume.setText("Nume"); textPrenume.setText("Prenume"); textSuprafata.setText("Suprafata"); textNr_pers.setText("Nr persoane");
		textUser.setText("user"); textPassword.setText("Password"); textIndex_a_c.setText("Apa calda"); textIndex_a_r.setText("Apa rece"); textIntretinere.setText("Intretinere");
		textRestante.setText("Restante"); textemail.setText("Mail"); textTel.setText("Telefon"); textApartament.setText("Ap");
		attention.setText("");
		//deselect table row
		tableID.getSelectionModel().select(null);
		
	}
	
}




