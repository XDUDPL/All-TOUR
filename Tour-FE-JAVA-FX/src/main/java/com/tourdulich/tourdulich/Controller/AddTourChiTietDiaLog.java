package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Entity.DiaDiemEntity;
import com.tourdulich.tourdulich.Entity.LoaiChiPhiEntity;
import com.tourdulich.tourdulich.Service.DiaDiemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddTourChiTietDiaLog implements Initializable {

    public TableView<DiaDiemEntity> table;
    public TableColumn<DiaDiemEntity,Integer> idColumn;
    public TableColumn<DiaDiemEntity,String> tpColumn;
    public TableColumn<DiaDiemEntity,String> ddColumn;
    public TableColumn<DiaDiemEntity,String> motaColumn;
    public TextField SearchText;
    ObservableList<DiaDiemEntity> diaDiemEntities;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diaDiemEntities = FXCollections.observableArrayList(DiaDiemService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tpColumn.setCellValueFactory(new PropertyValueFactory<>("ddThanhpho"));
        ddColumn.setCellValueFactory(new PropertyValueFactory<>("ddTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("ddMota"));
        table.setItems(diaDiemEntities);
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(diaDiemEntities);
            return;
        }
        DiaDiemEntity[] a = diaDiemEntities.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getDdTen().toLowerCase().contains(input)
                    ||e.getDdMota().toLowerCase().contains(input)||e.getDdThanhpho().toLowerCase().contains(input);
        }).toArray(DiaDiemEntity[]::new);
        ObservableList<DiaDiemEntity> lists = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(lists);
    }
}
