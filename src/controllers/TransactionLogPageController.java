package controllers;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TransactionLogPageController implements Initializable {

    /**
     * variable initialization
     */
    @FXML
    public Button crossButton;
    public Pane logOutBtn, cancelBtn, reserveBtn;
    public Label logOutInfo, tranLogInfo, cancelInfo, reserveInfo;
    public Text transactionLog, page;
    public Button homeBtn;
    public TableView<TransactionData> transactionTab;
    public TableColumn<String, TransactionData> utkCol, revDateCol, jourDateCol, statCol, serialCol;

    /**
     *  this method is to close the application
     */
    public void onClickCrossButton(){

        System.exit(0);
    }

    /**
     * this method is used for user logging out
     */
    public void onClickLogOutButton(javafx.event.Event actionEvent) {

        try{

            Parent homePage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/home.fxml")));
            Scene homePageScene = new Scene(homePage);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(homePageScene);
            window.show();

        } catch (IOException ignored){

        }
    }

    /**
     * this method is to animate in the log-out menu information
     */
    public void onHoverLogOutButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), logOutInfo);
        transition.setToX(220);
        transition.play();
    }

    /**
     * this method is to animate out the log-out menu information
     */
    public void onExitLogOutButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), logOutInfo);
        transition.setToX(0);
        transition.play();
    }

    /**
     * this method is used to switch to cancellation page
     */
    public void onClickCancellationButton(javafx.event.Event actionEvent) {

        try{

            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/cancellationPage.fxml")));
            Scene scene = new Scene(page);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException ignored){

        }
    }

    /**
     * this method is to animate in the cancellation menu information
     */
    public void onHoverCancellationButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), cancelInfo);
        transition.setToX(220);
        transition.play();

    }

    /**
     *  this method is to animate out the cancellation menu information
     */
    public void onExitCancellationButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), cancelInfo);
        transition.setToX(0);
        transition.play();

    }

    /**
     * this method is used to switch to reservation page
     */
    public void onClickReservationButton(javafx.event.Event actionEvent) {

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/reservationPage.fxml"));
            Parent page = loader.load();
            Scene scene = new Scene(page);

            ReservationPageController.setRpc(loader.getController());

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException ignored){

        }

    }

    /**
     * this method is to animate in the reservation menu information
     */
    public void onHoverReservationButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), reserveInfo);
        transition.setToX(220);
        transition.play();

    }

    /**
     * this method is to animate out the reservation menu information
     */
    public void onExitReservationButton(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), reserveInfo);
        transition.setToX(0);
        transition.play();

    }

    /**
     * this method allows user to return to the dashboard
     */
    public void onClickHomeButton(javafx.event.Event actionEvent){

        try{

            Parent homePage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/dashboard.fxml")));
            Scene homePageScene = new Scene(homePage);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(homePageScene);
            window.show();

        } catch (IOException ignored){

        }
    }

    /**
     * this method is initializing the scene
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translateIt(500, transactionLog, -475, 1);
        translateIt(500, page, -199, 1);
        translateIt(500, transactionTab, 1455, 1);

        ObservableList<TransactionData> data =
                FXCollections.observableArrayList(
                        //TODO add transaction log data here
                        new TransactionData("1","230720211158-M4055D3-240720212200", "23-07-2021", "24-07-2021", "Sold"),
                        new TransactionData("2","230720211158-M4055D3-240720212200", "23-07-2021", "24-07-2021", "Sold"),
                        new TransactionData("3","230720211158-M4055D3-240720212200", "23-07-2021", "24-07-2021", "Sold"),
                        new TransactionData("4","230720211158-M4055D3-240720212200", "23-07-2021", "24-07-2021", "Sold"),
                        new TransactionData("5","230720211158-M4055D3-240720212200", "23-07-2021", "24-07-2021", "Sold")
                );


        serialCol.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        utkCol.setCellValueFactory(new PropertyValueFactory<>("utkNoData"));
        revDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        jourDateCol.setCellValueFactory(new PropertyValueFactory<>("JourneyDate"));
        statCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        transactionTab.setItems(data);
    }

    public void translateIt(double duration, Node node, double translateTo, int type) {

        TranslateTransition transition = new TranslateTransition(Duration.millis(duration), node);

        if (type == 1) {

            transition.setToX(translateTo);
        } else if (type == 2) {

            transition.setToY(translateTo);
        }

        transition.play();

    }


    public static class TransactionData {

        private final SimpleStringProperty serialNo;
        private final SimpleStringProperty utkNoData;
        private final SimpleStringProperty reservationDate;
        private final SimpleStringProperty JourneyDate;
        private final SimpleStringProperty status;

        private TransactionData(String serial, String utk, String revDate, String jourDate, String stat) {

            this.serialNo = new SimpleStringProperty(serial);
            this.utkNoData = new SimpleStringProperty(utk);
            this.reservationDate = new SimpleStringProperty(revDate);
            this.JourneyDate = new SimpleStringProperty(jourDate);
            this.status = new SimpleStringProperty(stat);
        }

        public String getSerialNo() {
            return serialNo.get();
        }

        public SimpleStringProperty serialNoProperty() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo.set(serialNo);
        }

        public String getUtkNoData() {
            return utkNoData.get();
        }

        public SimpleStringProperty utkNoDataProperty() {
            return utkNoData;
        }

        public void setUtkNoData(String utkNoData) {
            this.utkNoData.set(utkNoData);
        }

        public String getReservationDate() {
            return reservationDate.get();
        }

        public SimpleStringProperty reservationDateProperty() {
            return reservationDate;
        }

        public void setReservationDate(String reservationDate) {
            this.reservationDate.set(reservationDate);
        }

        public String getJourneyDate() {
            return JourneyDate.get();
        }

        public SimpleStringProperty journeyDateProperty() {
            return JourneyDate;
        }

        public void setJourneyDate(String journeyDate) {
            this.JourneyDate.set(journeyDate);
        }

        public String getStatus() {
            return status.get();
        }

        public SimpleStringProperty statusProperty() {
            return status;
        }

        public void setStatus(String status) {
            this.status.set(status);
        }
    }
}
