//imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HeartHealthImagingSystem extends Application {
	private Stage primaryStage;
	
	//starts program
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Heart Health Imaging System");
		primaryStage.setScene(new Scene(createMainView(), 500, 400));
		primaryStage.show();
	}
	//Used to create the main view
	private VBox createMainView() {
		//create VBox that will have everything for the main view
		VBox mainView = new VBox(20);
		mainView.setStyle("-fx-border-color: #6495ED;" +
				"-fx-border-width: 2px;" +
				"-fx-border-style: solid;");
		mainView.setAlignment(Pos.CENTER);
		//Create title label with text
		Label mainTitle = new Label("Welcome to Heart Health Imaging and Recording System");
		//Create buttons with added attributes
		Button receptionistBtn = new Button("Patient Intake");
		receptionistBtn.setStyle("-fx-background-color: #6495ED;");
		receptionistBtn.setPrefHeight(50);
		receptionistBtn.setPrefWidth(175);
		Button technicianBtn = new Button("CT Scan Tech View");
		technicianBtn.setStyle("-fx-background-color: #6495ED;");
		technicianBtn.setPrefHeight(50);
		technicianBtn.setPrefWidth(175);
		Button patientBtn = new Button("Patient View");
		patientBtn.setStyle("-fx-background-color: #6495ED;");
		patientBtn.setPrefHeight(50);
		patientBtn.setPrefWidth(175);
		//Add all buttons and label to the VBox
		mainView.getChildren().addAll(mainTitle, receptionistBtn, technicianBtn, patientBtn);
		//Give actions to the buttons
		receptionistBtn.setOnAction(e -> primaryStage.setScene(new Scene(createReceptionistView(), 500, 400)));
		technicianBtn.setOnAction(e -> primaryStage.setScene(new Scene(createTechnicianView(), 500, 400)));
		patientBtn.setOnAction(e -> primaryStage.setScene(new Scene(createPatientView(), 500, 400)));
		//return the VBox
		return mainView;
	}
	//creates receptionist's view
	private GridPane createReceptionistView() {
		//Create grid pane and add attributes
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		//Create title label
		Label receptionistTitle = new Label("Patient Intake Form");
		//Create text fields
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField emailField = new TextField();
		TextField phoneNumberField = new TextField();
		TextField historyField = new TextField();
		TextField idField = new TextField();
		//Create buttons, give them actions, and give them attributes
		Button saveBtn = new Button("Save");
		saveBtn.setStyle("-fx-background-color: #6495ED;");
		saveBtn.setPrefHeight(50);
		saveBtn.setPrefWidth(75);
		saveBtn.setOnAction(e -> {
			//create string that have text fields text
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String email = emailField.getText();
			String phoneNumber = phoneNumberField.getText();
			String healthHistory = historyField.getText();
			String insuranceId = idField.getText();
			//Create random patient id
			String patientId = String.format("%05d", (int) (Math.random()* 100000));
			//create new file
			File file = new File(patientId + "_PatientInfo.txt");
			//try to write to the file
			try(FileWriter writer = new FileWriter(file)){
				//write the info into file
				writer.write("First Name: " + firstName + "\n");
				writer.write("Last Name: " + lastName + "\n");
				writer.write("Email: " + email + "\n");
				writer.write("Phone Number: " + phoneNumber + "\n");
				writer.write("Health History: " + healthHistory + "\n");
				writer.write("Insurance Id: " + insuranceId);
				//close the file writer
				writer.close();
				//Alert user that the patient was added successfully
				Alert alert = new Alert(Alert.AlertType.INFORMATION); 
				alert.setTitle("Patient Saved");
				alert.setHeaderText(null);
				alert.setContentText("Patient saved successfully. Patient ID: " + patientId);
				alert.showAndWait();
			}
			//catch any IOExceptions
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
			//clear the text fields
			firstNameField.clear();
			lastNameField.clear();
			emailField.clear();
			phoneNumberField.clear();
			historyField.clear();
			idField.clear();
		});
		//back button to return to main view
		Button backBtn = new Button("Back");
		backBtn.setAlignment(Pos.CENTER);
		backBtn.setStyle("-fx-background-color: #6495ED;");
		backBtn.setPrefHeight(50);
		backBtn.setPrefWidth(75);
		backBtn.setOnAction(e -> primaryStage.setScene(new Scene(createMainView(), 500, 400)));
		//Add Labels, text fields, and buttons to grid
		grid.add(receptionistTitle, 1, 0);
		grid.add(new Label("First Name: "), 0, 1);
		grid.add(firstNameField, 1, 1);
		grid.add(new Label("Last Name: "), 0, 2);
		grid.add(lastNameField, 1, 2);
		grid.add(new Label("Email: "), 0, 3);
		grid.add(emailField, 1, 3);
		grid.add(new Label("Phone Number: "), 0, 4);
		grid.add(phoneNumberField, 1, 4);
		grid.add(new Label("Health History: "), 0, 5);
		grid.add(historyField, 1, 5);
		grid.add(new Label("Insurance ID: "), 0, 6);
		grid.add(idField, 1, 6);
		grid.add(saveBtn, 2, 7);
		grid.add(backBtn, 0, 7);
		//return grid
		return grid;
	}
	//Creates technician's view
	private GridPane createTechnicianView() {
		//Create grid pane and add attributes
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		//Create text fields
		TextField patientIdField = new TextField();
		TextField totalField = new TextField();
		TextField lmField = new TextField();
		TextField ladField = new TextField();
		TextField lcxField = new TextField();
		TextField rcaField = new TextField();
		TextField pdaField = new TextField();
		//Create buttons with attributes and actions
		Button saveBtn = new Button("Save");
		saveBtn.setAlignment(Pos.CENTER);
		saveBtn.setStyle("-fx-background-color: #6495ED;");
		saveBtn.setPrefHeight(50);
		saveBtn.setPrefWidth(75);
		saveBtn.setOnAction(e -> {
			//check to see if any text field is empty
			if(patientIdField.getText().isEmpty() || totalField.getText().isEmpty() || 
					lmField.getText().isEmpty() || ladField.getText().isEmpty() ||
					lcxField.getText().isEmpty() || rcaField.getText().isEmpty() || pdaField.getText().isEmpty()) {
				//Alert user that all fields are required
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("All text fields are required.");
				alert.showAndWait();
				return;
			}
			//create string that have text fields text
			String patientId = patientIdField.getText();
			String total = totalField.getText();
			String lm = lmField.getText();
			String lad = ladField.getText();
			String lcx = lcxField.getText();
			String rca = rcaField.getText();
			String pda = pdaField.getText();
			//Create new file
			File file = new File(patientId + "CTResults.txt");
			//try to write info into file
			try (FileWriter writer = new FileWriter(file)){
				//write info into file
				writer.write("Patient ID: " + patientId + "\n");
				writer.write("The total Agatston CAC score: " + total + "\n");
				writer.write("LM: " + lm + "\n");
				writer.write("LAD: " + lad + "\n");
				writer.write("LCX: " + lcx + "\n");
				writer.write("RCA: " + rca + "\n");
				writer.write("PDA: " + pda);
				//close file writer
				writer.close();
				//Let the user know the results have been saved successfully
				Alert alert = new Alert(Alert.AlertType.INFORMATION); 
				alert.setTitle("Patient's Results Saved");
				alert.setHeaderText(null);
				alert.setContentText("Patient's results saved successfully. Patient ID: " + patientId);
				alert.showAndWait();
			}
			//catch IOException
			catch(IOException ex) {
				ex.printStackTrace();
			}
			//clear text fields
			patientIdField.clear();
			totalField.clear();
			lmField.clear();
			ladField.clear();
			lcxField.clear();
			rcaField.clear();
			pdaField.clear();
		});
		//back button to return to main view
		Button backBtn = new Button("Back");
		backBtn.setAlignment(Pos.CENTER);
		backBtn.setStyle("-fx-background-color: #6495ED;");
		backBtn.setPrefHeight(50);
		backBtn.setPrefWidth(75);
		backBtn.setOnAction(e -> primaryStage.setScene(new Scene(createMainView(), 500, 400)));
		//Add Labels, text fields, and buttons to grid
		grid.add(new Label("Patient ID: "), 0, 0);
		grid.add(patientIdField, 1, 0);
		grid.add(new Label("The total Agatston CAC score"), 0, 1);
		grid.add(totalField, 1, 1);
		grid.add(new Label("Vessel level Agatston CAC score"), 0, 2);
		grid.add(new Label("LM: "), 0, 3);
		grid.add(lmField, 1, 3);
		grid.add(new Label("LAD: "), 0, 4);
		grid.add(ladField, 1, 4);
		grid.add(new Label("LCX: "), 0, 5);
		grid.add(lcxField, 1, 5);
		grid.add(new Label("RCA: "), 0, 6);
		grid.add(rcaField, 1, 6);
		grid.add(new Label("PDA: "), 0, 7);
		grid.add(pdaField, 1, 7);
		grid.add(backBtn, 0, 8);
		grid.add(saveBtn, 1, 8);
		//return grid
		return grid;
	}
	//creates patient's view
	private GridPane createPatientView() {
		//Create grid pane and add attributes
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		//Create text field
		TextField idTextField = new TextField();
		//Create buttons with attributes and actions
		Button submitBtn = new Button("Submit");
		submitBtn.setAlignment(Pos.CENTER);
		submitBtn.setStyle("-fx-background-color: #6495ED;");
		submitBtn.setPrefHeight(50);
		submitBtn.setPrefWidth(75);
		submitBtn.setOnAction(e -> {
			//Create String that gets text field's text
			String patientId = idTextField.getText();
			//For the first and last name of the patient
			String firstName = "";
			String lastName = "";
			//Create new patient file
			File patientFile = new File(patientId + "_PatientInfo.txt");
			//if this file exists
			if(patientFile.exists())
			{
				//try to read the file
				try(BufferedReader reader = new BufferedReader(new FileReader(patientFile)))
				{
					//create string line
					String line;
					//set first line of file to line
					line = reader.readLine();
					//create string array data that splits line using the ":"
					String[] data = line.split(":");
					//get the first name of the patient in the file and set it to first name
					firstName = data[1];
					//set second line of file to line
					line = reader.readLine();
					//split line using the ":"
					data = line.split(":");
					//get the last name of the patient in the file and set it to last name
					lastName = data[1];
				}
				//catch IOException
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			//else alert the user and display this message
			else {
				Alert alert1 = new Alert(Alert.AlertType.ERROR);
				alert1.setTitle("Error");
				alert1.setHeaderText(null);
				alert1.setContentText("Incorrect patient ID or no data available.");
				alert1.showAndWait();
			}
			//create new results file 
			File ctResultFile = new File(patientId + "CTResults.txt");
			//if the results file exists 
			if(ctResultFile.exists())
			{
				//go to view the results of the patient
				primaryStage.setScene(new Scene(createCtResultsView(patientId,firstName, lastName),500,400));
			}
			//else alert the user and display this message
			else 
			{
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setTitle("Error");
				alert2.setHeaderText(null);
				alert2.setContentText("Incorrect patient ID or no data available.");
				alert2.showAndWait();
			}
		});
		//back button to return to main view
		Button backBtn = new Button("Back");
		backBtn.setAlignment(Pos.CENTER);
		backBtn.setStyle("-fx-background-color: #6495ED;");
		backBtn.setPrefHeight(50);
		backBtn.setPrefWidth(75);
		backBtn.setOnAction(e -> primaryStage.setScene(new Scene(createMainView(), 500, 400)));
		//Add label, text field, and buttons to grid
		grid.add(new Label("Patient ID:"), 0, 0);
		grid.add(idTextField, 1, 0);
		grid.add(backBtn, 0, 1);
		grid.add(submitBtn, 1, 1);
		//return grid
		return grid;
	}
	//creates result view
	private GridPane createCtResultsView(String patientId, String firstName, String lastName) {
		//Create grid pane and add attributes
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		//add label to grid that says hello to the patient
		grid.add(new Label("Hello" + firstName + lastName), 1, 0);
		//create new results file
		File resultFile = new File(patientId + "CTResults.txt");
		//if the result file exists
		if(resultFile.exists()) {
			//try to read the file
			try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
				//create string line
				String line;
				//create row integer and set to 1
				int row = 1;
				//read first line
				reader.readLine();
				//set line to null
				line = null;
				//while line doesn't equal null
				while((line = reader.readLine())!= null)
				{
					//create string array data which splits line by ":"
					String[] data = line.split(":");
					//Create the labels 
					Label labels = new Label(data[0] + ":");
					//constraint labels to grid pane
					GridPane.setConstraints(labels, 0, row);
					//Create the text fields
					TextField values = new TextField(data[1]);
					//make it so the text fields cannot be edited
					values.setEditable(false);
					//constraint text fields to grid pane
					GridPane.setConstraints(values, 1, row);
					//add labels and text fields to grid
					grid.getChildren().addAll(labels, values);
					//increment row
					row++;
				}
			}
			//catch IOException
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		//else alert the user with this message
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("No data available.");
			alert.showAndWait();
		}
		//back button to return to patient view
		Button backBtn = new Button("Back");
		backBtn.setAlignment(Pos.CENTER);
		backBtn.setStyle("-fx-background-color: #6495ED;");
		backBtn.setPrefHeight(50);
		backBtn.setPrefWidth(75);
		backBtn.setOnAction(e -> primaryStage.setScene(new Scene(createPatientView(), 500, 400)));
		//add back button to grid
		grid.add(backBtn, 0, 7);
		//return grid
		return grid;
	}
	//launches program
	public static void main(String[] args) {
		launch(args);
	}
}