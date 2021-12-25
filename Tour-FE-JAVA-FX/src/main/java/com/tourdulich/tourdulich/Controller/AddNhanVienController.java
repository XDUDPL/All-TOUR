package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.NhanVienEntity;
import com.tourdulich.tourdulich.Service.NhanVienService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class AddNhanVienController implements Initializable {
    public TableView<NhanVienEntity> table;
    public TableColumn<NhanVienEntity,Integer> idColunm;
    public TableColumn<NhanVienEntity,String> tenColunm;
    public TableColumn<NhanVienEntity,String> sdtColunm;
    public TableColumn<NhanVienEntity, Date> ngaySinhColunm;
    public TableColumn<NhanVienEntity,String> emailColumn;
    public TableColumn<NhanVienEntity,String> nhienVuColumn;
    public TextField SearchText;
    private ObservableList<NhanVienEntity> nhanVienEntities;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nhanVienEntities = FXCollections.observableArrayList(NhanVienService.getData());
        idColunm.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColunm.setCellValueFactory(new PropertyValueFactory<>("nvTen"));
        sdtColunm.setCellValueFactory(new PropertyValueFactory<>("nvSdt"));
        ngaySinhColunm.setCellValueFactory(new PropertyValueFactory<>("nvNgaysinh"));
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
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("nvEmail"));
        nhienVuColumn.setCellValueFactory(new PropertyValueFactory<>("nvNhiemvu"));
        table.setItems(nhanVienEntities);
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(nhanVienEntities);
            return;
        }
        NhanVienEntity[] a = nhanVienEntities.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getNvTen().toLowerCase().contains(input)
                    ||e.getNvEmail().toLowerCase().contains(input)
                    ||e.getNvSdt().toLowerCase().contains(input)
                    ||e.getNvNhiemvu().toLowerCase().contains(input);
        }).toArray(NhanVienEntity[]::new);
        ObservableList<NhanVienEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
