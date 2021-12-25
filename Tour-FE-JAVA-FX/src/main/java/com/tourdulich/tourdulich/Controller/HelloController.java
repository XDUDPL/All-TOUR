package com.tourdulich.tourdulich.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button NhanVienButton;
    public Button loaiChiPhiButton;
    public Button DoanButton;
    public Button ThongKeButton;
    @FXML
    private Button TourButton;
    @FXML
    private Label TitleLabel;
    @FXML
    private Button loaiTourButton;
    @FXML
    private Button khachHangButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button diaDiemButton;
    @FXML
    private BorderPane main;
    @FXML
    private SplitPane HomePane;

    private Parent khachHangPane;
    private Parent loaiTourPane;
    private Parent diaDiemPane;
    private Parent tourPane;
    private Parent nhanVienPane;
    private Parent loaiChiPhiPanel;
    private Parent doanPanel;
    private Parent thongKePanel;
    public HelloController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khachHangPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/KhachHangPanel.fxml")).getRoot();
        loaiTourPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/LoaiTour.fxml")).getRoot();
        diaDiemPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/DiaDiem.fxml")).getRoot();
        tourPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/Tour.fxml")).getRoot();
        nhanVienPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/NhanVien.fxml")).getRoot();
        loaiChiPhiPanel = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/LoaiChiPhiTour.fxml")).getRoot();
        doanPanel = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/Doan.fxml")).getRoot();
        thongKePanel = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/ThongKe.fxml")).getRoot();
    }

    public static FXMLLoader loadFXML(URL url){
        FXMLLoader fxmlLoader = new FXMLLoader ();
        fxmlLoader.setLocation (url);
        try {
            fxmlLoader.load();
            return fxmlLoader;
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new IllegalStateException(e);
        }
    }

    public void loaiTourPage() {
        main.setCenter(loaiTourPane);
        clearButton();
        loaiTourButton.setDisable(true);
        TitleLabel.setText(loaiTourButton.getText());
    }

    public void KhachHangButton(){
        main.setCenter(khachHangPane);
        clearButton();
        khachHangButton.setDisable(true);
        TitleLabel.setText(khachHangButton.getText());
    }

    private void clearButton(){
        khachHangButton.setDisable(false);
        loaiTourButton.setDisable(false);
        homeButton.setDisable(false);
        diaDiemButton.setDisable(false);
        TourButton.setDisable(false);
        NhanVienButton.setDisable(false);
        loaiChiPhiButton.setDisable(false);
        DoanButton.setDisable(false);
        ThongKeButton.setDisable(false);
    }

    public void homeButtonAction() {
        main.setCenter(HomePane);
        clearButton();
        homeButton.setDisable(true);
        TitleLabel.setText(homeButton.getText());
    }

    public void diaDiemOpenPage() {
        main.setCenter(diaDiemPane);
        clearButton();
        diaDiemButton.setDisable(true);
        TitleLabel.setText(diaDiemButton.getText());
    }

    public void onTourAction() {
        main.setCenter(tourPane);
        clearButton();
        TourButton.setDisable(true);
        TitleLabel.setText(TourButton.getText());
    }

    public void onNhanVienAction() {
        main.setCenter(nhanVienPane);
        clearButton();
        NhanVienButton.setDisable(true);
        TitleLabel.setText(NhanVienButton.getText());
    }

    public void onLoaiChiPhiAtion() {
        main.setCenter(loaiChiPhiPanel);
        clearButton();
        loaiChiPhiButton.setDisable(true);
        TitleLabel.setText(loaiChiPhiButton.getText());
    }

    public void onDoanAction(ActionEvent actionEvent) {
        main.setCenter(doanPanel);
        clearButton();
        DoanButton.setDisable(true);
        TitleLabel.setText(DoanButton.getText());
    }

    public void onThongKeAction(ActionEvent actionEvent) {
        main.setCenter(thongKePanel);
        clearButton();
        ThongKeButton.setDisable(true);
        TitleLabel.setText(ThongKeButton.getText());
    }
}