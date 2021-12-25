package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.DiaDiemEntity;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Service.DiaDiemService;
import com.tourdulich.tourdulich.Service.KhachHangService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiaDiemController implements Initializable, Controller {
    public TextField SearchText;
    @FXML
    private TableView<DiaDiemEntity> table;
    @FXML
    private TableColumn<DiaDiemEntity,Integer> idColumn;
    @FXML
    private TableColumn<DiaDiemEntity,String> tpColumn;
    @FXML
    private TableColumn<DiaDiemEntity,String> tenColumn;
    @FXML
    private TableColumn<DiaDiemEntity,String> motaColumn;
    @FXML
    private Button clearButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField tpTextField;
    @FXML
    private TextField tenTextField;
    @FXML
    private TextField moTaTextFeild;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    private ObservableList<DiaDiemEntity> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList(DiaDiemService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tpColumn.setCellValueFactory(new PropertyValueFactory<>("ddThanhpho"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ddTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("ddMota"));
        table.setItems(list);
        valid();
    }

    @Override
    public void onTableClick() {
        DiaDiemEntity dd= table.getSelectionModel().getSelectedItem();
        if(dd != null){
            idTextField.setText(String.valueOf(dd.getId()));
            tenTextField.setText(dd.getDdTen());
            tpTextField.setText(dd.getDdThanhpho());
            moTaTextFeild.setText(dd.getDdMota());
        }
        valid();
    }

    @Override
    public boolean isValidAddData() {
        String data = idTextField.getText();
        return !data.isEmpty();
    }

    @Override
    public boolean isValidUpadte() {
        String data = idTextField.getText();
        return data.isEmpty();
    }

    @Override
    public void valid() {
        addButton.setDisable(isValidAddData());
        updateButton.setDisable(isValidUpadte());
        deleteButton.setDisable(isValidUpadte());
    }

    @Override
    public void clearForm() {
        idTextField.setText("");
        tenTextField.setText("");
        tpTextField.setText("");
        moTaTextFeild.setText("");
        table.setItems(list);
        valid();
    }

    @Override
    public void addEntity() {
        DiaDiemEntity dd = new DiaDiemEntity();
        dd.setDdThanhpho(tpTextField.getText());
        dd.setDdTen(tenTextField.getText());
        dd.setDdMota(moTaTextFeild.getText());

        ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm địa điểm");
        if(alert.getConfirm()){
            DiaDiemEntity result = DiaDiemService.add(dd);
            new SuccessNoti(result!=null,SuccessNoti.THEM);
            list.add(result);
        }else{
            new SuccessNoti(true,SuccessNoti.HUY);
        }
    }

    @Override
    public void updateEntity() {
        DiaDiemEntity ddInput = new DiaDiemEntity();
        ddInput.setId(Integer.valueOf(idTextField.getText()));
        ddInput.setDdThanhpho(tpTextField.getText());
        ddInput.setDdTen(tenTextField.getText());
        ddInput.setDdMota(moTaTextFeild.getText());

        DiaDiemEntity dd = list.stream().filter((item)->
                Objects.equals(item.getId(), ddInput.getId())).findAny().get();
        if(dd.Compare(ddInput)){
            new SuccessNoti("Không có thay đổi");
        }else{
            ConfirmAlert alert = new ConfirmAlert("Xác nhận sửa địa điểm");
            if(alert.getConfirm()){
                DiaDiemEntity result = DiaDiemService.update(ddInput,ddInput.getId());
                if(result!=null){
                    new SuccessNoti(true,SuccessNoti.SUA);
                    for(int i=0;i<list.size();i++){
                        if(Objects.equals(list.get(i).getId(), result.getId())){
                            list.set(i,result);
                            break;
                        }
                    }
                }else{
                    new SuccessNoti(false,SuccessNoti.SUA);
                }
            }else{
                new SuccessNoti(true,SuccessNoti.HUY);
            }
        }
    }

    @Override
    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá địa điểm");
        if(alert.getConfirm()){
            if(DiaDiemService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getId()==id){
                        list.remove(i);
                        break;
                    }
                }
            }else{
                new SuccessNoti(false, SuccessNoti.XOA);
            }

        }else{
            new SuccessNoti(true,SuccessNoti.HUY);
        }
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(list);
            return;
        }
        DiaDiemEntity[] a = list.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getDdTen().toLowerCase().contains(input)
                    ||e.getDdMota().toLowerCase().contains(input)||e.getDdThanhpho().toLowerCase().contains(input);
        }).toArray(DiaDiemEntity[]::new);
        ObservableList<DiaDiemEntity> lists = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(lists);
    }
}
