package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Entity.LoaiChiPhiEntity;
import com.tourdulich.tourdulich.Service.LoaiChiPhiService;
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

public class LoaiChiPhiController implements Initializable, Controller {
    public TextField SearchText;
    @FXML
    private TableView<LoaiChiPhiEntity> table;
    @FXML
    private Button clearButton;
    @FXML
    private TextField idTextField;
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
    @FXML
    private TableColumn<LoaiChiPhiEntity, Integer> idColumn;
    @FXML
    private TableColumn<LoaiChiPhiEntity,String> tenColumn;
    @FXML
    private TableColumn<LoaiChiPhiEntity,String> motaColumn;
    private ObservableList<LoaiChiPhiEntity> loaiChiPhiEntities;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaiChiPhiEntities = FXCollections.observableArrayList(LoaiChiPhiService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("cpTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("cpMota"));
        table.setItems(loaiChiPhiEntities);
        valid();
    }
    @Override
    public void onTableClick() {
        LoaiChiPhiEntity LoaiChiPhiEntity = table.getSelectionModel().getSelectedItem();
        if(LoaiChiPhiEntity!=null){
            idTextField.setText(String.valueOf(LoaiChiPhiEntity.getId()));
            tenTextField.setText(LoaiChiPhiEntity.getCpTen());
            moTaTextFeild.setText(LoaiChiPhiEntity.getCpMota());
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
        addButton.setDisable(this.isValidAddData());
        updateButton.setDisable(this.isValidUpadte());
        deleteButton.setDisable(this.isValidUpadte());
    }

    @Override
    public void clearForm() {
        idTextField.setText("");
        tenTextField.setText("");
        moTaTextFeild.setText("");
        loaiChiPhiEntities = FXCollections.observableArrayList(LoaiChiPhiService.getData());
        table.setItems(loaiChiPhiEntities);
        valid();
    }

    @Override
    public void addEntity() {
        LoaiChiPhiEntity loai = new LoaiChiPhiEntity();
        loai.setCpTen(tenTextField.getText());
        loai.setCpMota(moTaTextFeild.getText());

        ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm loại tour");
        if(alert.getConfirm()){
            LoaiChiPhiEntity result = LoaiChiPhiService.add(loai);
            new SuccessNoti(result!=null,SuccessNoti.THEM);
            loaiChiPhiEntities.add(result);
        }else{
            new SuccessNoti(true,SuccessNoti.HUY);
        }
    }

    @Override
    public void updateEntity() {
        LoaiChiPhiEntity loaiInput = new LoaiChiPhiEntity();
        loaiInput.setId(Integer.valueOf(idTextField.getText()));
        loaiInput.setCpTen(tenTextField.getText());
        loaiInput.setCpMota(moTaTextFeild.getText());

        LoaiChiPhiEntity loai = loaiChiPhiEntities.stream().filter((item)->
                Objects.equals(item.getId(), loaiInput.getId())).findAny().get();
        if(loai.Compare(loaiInput)){
            new SuccessNoti("Không có thay đổi");
        }else{
            ConfirmAlert alert = new ConfirmAlert("Xác nhận sửa loại tour");
            if(alert.getConfirm()){
                LoaiChiPhiEntity result = LoaiChiPhiService.update(loaiInput,loaiInput.getId());
                if(result!=null){
                    new SuccessNoti(true,SuccessNoti.SUA);
                    for(int i = 0; i< loaiChiPhiEntities.size(); i++){
                        if(Objects.equals(loaiChiPhiEntities.get(i).getId(), result.getId())){
                            loaiChiPhiEntities.set(i,result);
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
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá loại tour");
        if(alert.getConfirm()){
            if(LoaiChiPhiService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for(int i = 0; i< loaiChiPhiEntities.size(); i++){
                    if(loaiChiPhiEntities.get(i).getId()==id){
                        loaiChiPhiEntities.remove(i);
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
            table.setItems(loaiChiPhiEntities);
            return;
        }
        LoaiChiPhiEntity[] a = loaiChiPhiEntities.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getCpTen().toLowerCase().contains(input)
                    ||e.getCpMota().toLowerCase().contains(input);
        }).toArray(LoaiChiPhiEntity[]::new);
        ObservableList<LoaiChiPhiEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
