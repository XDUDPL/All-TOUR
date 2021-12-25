package com.tourdulich.tourdulich.Controller;

import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.*;
import com.tourdulich.tourdulich.Service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TourController implements Controller, Initializable {
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button addDiaDiemButton;
    public Button orderUpbutton;
    public Button orderDownButton;
    public Button deleteDiaDiemButton;
    public TableView<TourGiaEntity> giaTable;
    public TableColumn<TourGiaEntity,Integer> idGiaColumn;
    public TableColumn<TourGiaEntity, Date> ngayBatDauColumn;
    public TableColumn<TourGiaEntity, BigDecimal> GiaColumn;
    public TableColumn<TourGiaEntity,String> ghiChuColumn;
    public HBox MainContent;
    public TextField SearchText;

    @FXML
    private Label contentLoaiTour;
    @FXML
    private TableView<TourChiTietEntity> tableDiaDiem;
    @FXML
    private TableColumn<TourChiTietEntity,Integer> ttColumn;
    @FXML
    private TableColumn<TourChiTietEntity, DiaDiemEntity> thanhPhoColumn;
    @FXML
    private TableColumn<TourChiTietEntity,DiaDiemEntity> diaDiemColumn;
    @FXML
    private TextField idTextField;

    @FXML
    private TextField tenTextField;

    @FXML
    private TextField moTaTextFeild;

    @FXML
    private ChoiceBox<LoaiTourEntity> loaiComboBox;

    public TableView<TourEntity> table;

    @FXML
    private TableColumn<TourEntity,Integer> idColumn;

    @FXML
    private TableColumn<TourEntity,String> tenColumn;

    @FXML
    private TableColumn<TourEntity,String> motaColumn;

    @FXML
    private TableColumn<TourEntity,LoaiTourEntity> loaiCollumn;

    private ObservableList<TourEntity> toutList;
    private ObservableList<LoaiTourEntity> tourLoaiList;
    private ObservableList<TourChiTietEntity> chiTietEntities;
    private ObservableList<TourGiaEntity> tourGiaEntities;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toutList = FXCollections.observableArrayList(TourService.getData());
        tourLoaiList = FXCollections.observableArrayList(LoaiTourService.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tourTen"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("tourMota"));
        loaiCollumn.setCellValueFactory(new PropertyValueFactory<>("loai"));
        loaiCollumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LoaiTourEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getLoaiTen());
                }
            }
        });
        table.setItems(toutList);
        loaiComboBox.setItems(tourLoaiList);
        loaiComboBox.getSelectionModel().selectFirst();
        loaiComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(LoaiTourEntity loaiTourEntity) {
                return loaiTourEntity.getId()+": "+loaiTourEntity.getLoaiTen();
            }

            @Override
            public LoaiTourEntity fromString(String s) {
                String id = s.substring(0,s.indexOf(":"));
                return tourLoaiList.stream().filter(loai ->id.equals(String.valueOf(loai.getId()))).findFirst().get();
            }
        });
        ttColumn.setSortType(TableColumn.SortType.ASCENDING);
        tableDiaDiem.getSortOrder().add(ttColumn);

        idGiaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ngayBatDauColumn.setCellValueFactory(new PropertyValueFactory<>("giaTungay"));
        ngayBatDauColumn.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM");

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

        GiaColumn.setCellValueFactory(new PropertyValueFactory<>("giaSotien"));
        GiaColumn.setCellFactory(column -> new TableCell<>() {
            final Locale s = new Locale("vi","VN");
            final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(s);
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(defaultFormat.format(item));
                }
            }
        });
        ghiChuColumn.setCellValueFactory(new PropertyValueFactory<>("ghichu"));
        ngayBatDauColumn.setSortType(TableColumn.SortType.ASCENDING);
        giaTable.getSortOrder().add(ngayBatDauColumn);
        valid();
    }
    @Override
    public void onTableClick() {
        TourEntity item = table.getSelectionModel().getSelectedItem();
        idTextField.setText(String.valueOf(item.getId()));
        tenTextField.setText(item.getTourTen());
        moTaTextFeild.setText(item.getTourMota());
        loaiComboBox.getSelectionModel().select(tourLoaiList.stream().filter(loai -> Objects.equals(loai.getId(), item.getLoai().getId())).findFirst().get());
//        contentLoaiTour.setText(item.getLoai().getLoaiMota());

        chiTietEntities = FXCollections.observableArrayList(TourChiTietService.getByTourId(item.getId()));

        ttColumn.setCellValueFactory(new PropertyValueFactory<>("ctThutu"));
        thanhPhoColumn.setCellValueFactory(new PropertyValueFactory<>("dd"));
        thanhPhoColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DiaDiemEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getDdThanhpho());
                }
            }
        });
        diaDiemColumn.setCellValueFactory(new PropertyValueFactory<>("dd"));
        diaDiemColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(DiaDiemEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getDdTen());
                }
            }
        });
        tableDiaDiem.setItems(chiTietEntities);
        tourGiaEntities = FXCollections.observableArrayList(TourGiaService.getByTourId(item.getId()));
        giaTable.setItems(tourGiaEntities);
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
        addDiaDiemButton.setDisable(this.isValidUpadte());
    }

    @Override
    public void clearForm() {
        toutList = FXCollections.observableArrayList(TourService.getData());
        idTextField.setText("");
        tenTextField.setText("");
        moTaTextFeild.setText("");
        loaiComboBox.getSelectionModel().clearSelection();
        contentLoaiTour.setText("");
        chiTietEntities = null;
        tableDiaDiem.setItems(chiTietEntities);
        table.setItems(toutList);
        valid();
    }

    @Override
    public void addEntity() {
        try {
            TourEntity tour = new TourEntity();
            tour.setTourTen(tenTextField.getText());
            tour.setTourMota(moTaTextFeild.getText());
            tour.setLoai(loaiComboBox.getSelectionModel().getSelectedItem());
            ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm tour");
            if(alert.getConfirm()){
                TourEntity result = TourService.add(tour);
                new SuccessNoti(result!=null,SuccessNoti.THEM);
                toutList.add(result);
            }else{
                new SuccessNoti(true,SuccessNoti.HUY);
            }
        }catch (Exception e){
            new SuccessNoti("Không thêm mới được");
        }
    }

    @Override
    public void updateEntity() {
        try {
            TourEntity tour = new TourEntity();
            tour.setId(Integer.valueOf(idTextField.getText()));
            tour.setTourTen(tenTextField.getText());
            tour.setTourMota(moTaTextFeild.getText());
            tour.setLoai(loaiComboBox.getSelectionModel().getSelectedItem());

            TourEntity oldTour = toutList.stream().filter((item)->
                Objects.equals(item.getId(), tour.getId())).findAny().get();
            if(oldTour.Compare(tour)){
                new SuccessNoti("Không có thay đổi");
            }else{
                ConfirmAlert alert = new ConfirmAlert("Xác nhận sửa loại tour");
                if(alert.getConfirm()){
                    TourEntity result = TourService.update(tour,tour.getId());
                    if(result!=null){
                        new SuccessNoti(true,SuccessNoti.SUA);
                        for(int i=0;i<toutList.size();i++){
                            if(Objects.equals(toutList.get(i).getId(), result.getId())){
                                toutList.set(i,result);
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
        }catch (Exception e){
            new SuccessNoti("Không sửa");
        }
    }

    @Override
    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá loại tour");
        if(alert.getConfirm()){
            if(TourService.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for(int i=0;i<toutList.size();i++){
                    if(toutList.get(i).getId()==id){
                        toutList.remove(i);
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


    public void comboBoxOnAction() {
        if(!loaiComboBox.getSelectionModel().isEmpty())
            contentLoaiTour.setText(loaiComboBox.getSelectionModel().getSelectedItem().getLoaiMota());
    }

    public void orderUpAction() {
        TourChiTietEntity detail = tableDiaDiem.getSelectionModel().getSelectedItem();
        if(detail.getCtThutu()==1){
            System.out.println("chỉ hạ xuống");
        }else{
            detail.setCtThutu(detail.getCtThutu()-1);
            TourChiTietEntity result = TourChiTietService.update(detail,detail.getId());
            if(result!=null){
                chiTietEntities = FXCollections.observableArrayList(TourChiTietService.getByTourId(Integer.valueOf(idTextField.getText())));
                tableDiaDiem.setItems(chiTietEntities);
            }else{
                System.out.println("Fail");
            }
        }
    }

    public void orderDownAction() {
        TourChiTietEntity detail = tableDiaDiem.getSelectionModel().getSelectedItem();
        if(detail.getCtThutu()==chiTietEntities.size()){
            System.out.println("chỉ nâng lên");
        }else{
            detail.setCtThutu(detail.getCtThutu()+1);
            TourChiTietEntity result = TourChiTietService.update(detail,detail.getId());
            if(result!=null){
                chiTietEntities = FXCollections.observableArrayList(TourChiTietService.getByTourId(Integer.valueOf(idTextField.getText())));
                tableDiaDiem.setItems(chiTietEntities);
            }else{
                System.out.println("Fail");
            }
        }
    }

    public void deleteDiaDiemOnAction() {
        TourChiTietEntity detail = tableDiaDiem.getSelectionModel().getSelectedItem();
        if(TourChiTietService.delete(detail.getId())) {
            chiTietEntities = FXCollections.observableArrayList(TourChiTietService.getByTourId(Integer.valueOf(idTextField.getText())));
            tableDiaDiem.setItems(chiTietEntities);
        }else{
            System.err.println("Xoá không dược");
        }
    }

    public void showDetail() {
        TourChiTietEntity detail = tableDiaDiem.getSelectionModel().getSelectedItem();
        if(detail!=null){
            new SuccessNoti(detail.getDd().getDdMota());
        }
    }

    public void onAddGiaButtton() {
        Dialog<TourGiaEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm giá mới");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/AddGiaTour.fxml"));
        Pane pane;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddGiaTourController con = fxml.getController();

            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                    TourGiaEntity e = new TourGiaEntity();
                    e.setGiaSotien(BigDecimal.valueOf(Long.parseLong(con.PriceText.getText())));
                    e.setGhichu(con.commentText.getText());
                    e.setTourId(table.getSelectionModel().getSelectedItem().getId());
                    e.setGiaTungay(java.sql.Date.valueOf(con.DateInput.getValue()));
                    return e;
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<TourGiaEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                TourGiaEntity e = TourGiaService.add(result.get());
                if(e!=null){
                    tourGiaEntities = FXCollections.observableArrayList(TourGiaService.getByTourId(e.getTourId()));
                    giaTable.setItems(tourGiaEntities);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//                khachHangPane = loadFXML(getClass().getResource("/com/tourdulich/tourdulich/KhachHangPanel.fxml")).getRoot();
    }

    public void onAddDiaDiemAction() {
        Dialog<DiaDiemEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm mới địa điểm");
        dialog.setHeaderText("Chọn địa điểm để thêm vào");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/addTourChiTiet.fxml"));
        Pane pane = null;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddTourChiTietDiaLog con = fxml.getController();
            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    return con.table.getSelectionModel().getSelectedItem();
                }
                return null;
            });
            Optional<DiaDiemEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                TourChiTietEntity chiTietEntity = new TourChiTietEntity();
                chiTietEntity.setTour(table.getSelectionModel().getSelectedItem());
                chiTietEntity.setDd(result.get());
                TourChiTietEntity id = TourChiTietService.add(chiTietEntity);
                if(id!=null){
                    chiTietEntities = FXCollections.observableArrayList(TourChiTietService.getByTourId(id.getTour().getId()));
                    tableDiaDiem.setItems(chiTietEntities);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateGia() {
        TourGiaEntity gia = giaTable.getSelectionModel().getSelectedItem();
        if(gia==null){
            return;
        }
        Dialog<TourGiaEntity> dialog =  new Dialog();
        dialog.setTitle("Chỉnh sửa giá");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/AddGiaTour.fxml"));
        Pane pane = null;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddGiaTourController con = fxml.getController();
            con.PriceText.setText(String.valueOf(gia.getGiaSotien()));
            con.commentText.setText(gia.getGhichu());
            con.DateInput.setValue(new java.sql.Date(gia.getGiaTungay().getTime())
                    .toLocalDate());
            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                        TourGiaEntity e = new TourGiaEntity();
                        e.setGiaSotien(BigDecimal.valueOf(Long.parseLong(con.PriceText.getText())));
                        e.setGhichu(con.commentText.getText());
                        e.setTourId(table.getSelectionModel().getSelectedItem().getId());
                        e.setGiaTungay(java.sql.Date.valueOf(con.DateInput.getValue()));
                        e.setId(gia.getId());
                        return e;
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<TourGiaEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                TourGiaEntity e = TourGiaService.update(result.get(),gia.getId());
                if(e!=null){
                    tourGiaEntities = FXCollections.observableArrayList(TourGiaService.getByTourId(e.getTourId()));
                    giaTable.setItems(tourGiaEntities);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteGiaAction() {
        TourGiaEntity gia = giaTable.getSelectionModel().getSelectedItem();
        TourGiaService.delete(gia.getId());
        tourGiaEntities = FXCollections.observableArrayList(TourGiaService.getByTourId(gia.getTourId()));
        giaTable.setItems(tourGiaEntities);
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(toutList);
            return;
        }
        TourEntity[] a = toutList.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getTourTen().toLowerCase().contains(input)
                    ||e.getTourMota().toLowerCase().contains(input)||e.getLoai().getLoaiTen().contains(input);
        }).toArray(TourEntity[]::new);
        ObservableList<TourEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
