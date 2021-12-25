package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Entity.NhanVienEntity;
import com.tourdulich.tourdulich.Service.KhachHangService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class AddKhachHangContoller implements Initializable {
    public TableView<KhachHangEntity> table;

    public TableColumn<KhachHangEntity, Integer> idColunm;

    public TableColumn<KhachHangEntity, String> tenColunm;

    public TableColumn<KhachHangEntity, String> sdtColunm;

    public TableColumn<KhachHangEntity, Date> ngaySinhColunm;

    public TableColumn<KhachHangEntity, String> emailColumn;

    public TableColumn<KhachHangEntity, String> cmndColunm;
    public TextField SearchText;

    private ObservableList<KhachHangEntity> khachHangList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khachHangList = FXCollections.observableArrayList(KhachHangService.getData());
        idColunm.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColunm.setCellValueFactory(new PropertyValueFactory<>("khTen"));
        sdtColunm.setCellValueFactory(new PropertyValueFactory<>("khSdt"));
        ngaySinhColunm.setCellValueFactory(new PropertyValueFactory<>("khNgaysinh"));
        ngaySinhColunm.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("khEmail"));
        cmndColunm.setCellValueFactory(new PropertyValueFactory<>("khCmnd"));
        table.setItems(khachHangList);
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(khachHangList);
            return;
        }
        KhachHangEntity[] a = khachHangList.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getKhTen().toLowerCase().contains(input)
                    ||e.getKhCmnd().toLowerCase().contains(input)||e.getKhEmail().toLowerCase().contains(input)
                    ||e.getKhSdt().toLowerCase().contains(input);
        }).toArray(KhachHangEntity[]::new);
        ObservableList<KhachHangEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
