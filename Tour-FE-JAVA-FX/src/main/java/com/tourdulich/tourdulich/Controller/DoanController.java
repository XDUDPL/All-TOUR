package com.tourdulich.tourdulich.Controller;

import com.google.gson.Gson;
import com.tourdulich.tourdulich.Common.ConfirmAlert;
import com.tourdulich.tourdulich.Common.SuccessNoti;
import com.tourdulich.tourdulich.Entity.*;
import com.tourdulich.tourdulich.Service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.StringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class DoanController implements Initializable, Controller {
    public TableView<DoanEntity> table;
    public TableColumn<DoanEntity, Integer> idColumn;
    public TableColumn<DoanEntity, String> tenColumn;
    public TableColumn<DoanEntity, TourEntity> TourColumn;
    public TableColumn<DoanEntity, Date> ngayDiColumn;
    public TableColumn<DoanEntity, Date> ngayVeColumn;
    public TableColumn<DoanEntity, TourGiaEntity> giaColumn;
    public TableColumn<DoanEntity, String> chiTietColumn;
    public TextField idTextField;
    public TextField tenTextField;
    public DatePicker ngayDiInput;
    public DatePicker ngayVeInput;
    public Label giaLabel;
    public Label TourName;
    public static TourEntity tourEntity;
    public static TourGiaEntity giaEntity;
    public TextArea chiTietInput;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public TableView<ChiPhiEntity> chiPhiTable;
    public TableColumn<ChiPhiEntity, BigDecimal> chiPhiColumn;
    public TableColumn<ChiPhiEntity, Integer> loaiChiPhiColumn;
    public TableColumn<ChiPhiEntity, String> chiPhiGhiChuColumn;
    public TableColumn<KhachHangEntity, Integer> KhachHangId;
    public TableColumn<KhachHangEntity, String> HotenKhColumn;
    public TableView<KhachHangEntity> KhachHangTable;
    public TableView<NhanVienEntity> NhanVienTable;
    public TableColumn<NhanVienEntity, Integer> NhanVienIdColumn;
    public TableColumn<NhanVienEntity, String> HoTenNhanVienColumn;
    public TextField SearchText;
    private ObservableList<DoanEntity> doanEntities;
    private ObservableList<ChiPhiEntity> chiPhiEntities;
    private ObservableList<KhachHangEntity> khachHangEntities;
    private ObservableList<NhanVienEntity> nhanVienEntities;
    static final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doanEntities = FXCollections.observableArrayList(TourDoanSerivce.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("doanName"));
        TourColumn.setCellValueFactory(new PropertyValueFactory<>("tour"));
        TourColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(TourEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getTourTen());
                }
            }
        });
        ngayDiColumn.setCellValueFactory(new PropertyValueFactory<>("doanNgaydi"));
        ngayDiColumn.setCellFactory(column -> new TableCell<>() {
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
        ngayVeColumn.setCellValueFactory(new PropertyValueFactory<>("doanNgayve"));
        ngayVeColumn.setCellFactory(column -> new TableCell<>() {
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
        giaColumn.setCellValueFactory(new PropertyValueFactory<>("gia"));
        giaColumn.setCellFactory(column -> new TableCell<>() {
            final Locale s = new Locale("vi","VN");
            final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(s);
            @Override
            protected void updateItem(TourGiaEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(defaultFormat.format(item.getGiaSotien()));
                }
            }
        });
        chiTietColumn.setCellValueFactory(new PropertyValueFactory<>("doanChitietchuongtrinh"));
        table.setItems(doanEntities);
        valid();
        chiPhiColumn.setCellValueFactory(new PropertyValueFactory<>("gia"));
        loaiChiPhiColumn.setCellValueFactory(new PropertyValueFactory<>("loaiChiPhiId"));
        loaiChiPhiColumn.setCellFactory(column -> new TableCell<>() {
            final Locale s = new Locale("vi","VN");
            final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(s);
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(LoaiChiPhiService.getById(item).getCpTen());
                }
            }
        });
        chiPhiGhiChuColumn.setCellValueFactory(new PropertyValueFactory<>("ghichu"));
        KhachHangId.setCellValueFactory(new PropertyValueFactory<>("id"));
        HotenKhColumn.setCellValueFactory(new PropertyValueFactory<>("khTen"));
        NhanVienIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        HoTenNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("nvTen"));
    }

    @Override
    public void onTableClick() {
        DoanEntity item = table.getSelectionModel().getSelectedItem();
        if(item==null) return;
        idTextField.setText(String.valueOf(item.getId()));
        tenTextField.setText(item.getDoanName());
        ngayDiInput.setValue(new java.sql.Date(item.getDoanNgaydi().getTime()).toLocalDate());
        ngayVeInput.setValue(new java.sql.Date(item.getDoanNgayve().getTime()).toLocalDate());
        TourName.setText(item.getTour().getTourTen());
        tourEntity = item.getTour();
        giaLabel.setText(defaultFormat.format(item.getGia().getGiaSotien()));
        giaEntity = item.getGia();
        chiTietInput.setText(item.getDoanChitietchuongtrinh());
        TourChiPhiEntity chiphi = TourChiPhiService.getByDoanId(item.getId());
        ChiPhiEntity[] arr = new Gson().fromJson(chiphi.getChiphiChitiet(),ChiPhiEntity[].class);
        List<ChiPhiEntity> chiPhiEntityList = Arrays.asList(arr);
        chiPhiEntities =FXCollections.observableArrayList(chiPhiEntityList);
        chiPhiTable.setItems(chiPhiEntities);
        NguoiDiEntity nguoidi;
        try{
            nguoidi = NguoiDiService.getByDoanId(item.getId());
            KhachHangEntity[] khList = new Gson().fromJson(nguoidi.getNguoidiDskhach(),KhachHangEntity[].class);
            NhanVienEntity[] nvList = new Gson().fromJson(nguoidi.getNguoidiDsnhanvien(),NhanVienEntity[].class);
            khachHangEntities = FXCollections.observableArrayList(Arrays.asList(khList));
            nhanVienEntities = FXCollections.observableArrayList(Arrays.asList(nvList));
            KhachHangTable.setItems(khachHangEntities);
            NhanVienTable.setItems(nhanVienEntities);
        }catch (Exception e){
            KhachHangEntity[] khList = {};
            NhanVienEntity[] nvList = {};
            khachHangEntities = FXCollections.observableArrayList(Arrays.asList(khList));
            nhanVienEntities = FXCollections.observableArrayList(Arrays.asList(nvList));
            KhachHangTable.setItems(khachHangEntities);
            NhanVienTable.setItems(nhanVienEntities);
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
        ngayDiInput.setValue(null);
        ngayVeInput.setValue(null);
        TourName.setText("");
        giaLabel.setText("");
        chiTietInput.setText("");
        doanEntities = FXCollections.observableArrayList(TourDoanSerivce.getData());
        valid();
    }

    @Override
    public void addEntity() {

        try {
            DoanEntity entity = new DoanEntity();
            entity.setDoanName(tenTextField.getText());
            entity.setDoanNgaydi(ngayDiInput.getValue());
            entity.setDoanNgayve(ngayVeInput.getValue());
            entity.setTour(tourEntity);
            if(giaEntity!=null){
                entity.setGia(giaEntity);
            }
            entity.setDoanChitietchuongtrinh(chiTietInput.getText());
            ConfirmAlert alert = new ConfirmAlert("Xác nhận thêm đoàn");
            if(alert.getConfirm()){
                DoanEntity result = TourDoanSerivce.add(entity);
                new SuccessNoti(result!=null,SuccessNoti.THEM);
                doanEntities.add(result);
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
            DoanEntity entity = new DoanEntity();
            entity.setId(Integer.valueOf(idTextField.getText()));
            entity.setDoanName(tenTextField.getText());
            entity.setDoanNgaydi(ngayDiInput.getValue());
            entity.setDoanNgayve(ngayVeInput.getValue());
            entity.setTour(tourEntity);
            if(giaEntity!=null){
                entity.setGia(giaEntity);
            }
            entity.setDoanChitietchuongtrinh(chiTietInput.getText());

            DoanEntity oldDoan = doanEntities.stream().filter((item)->
                    Objects.equals(item.getId(), entity.getId())).findAny().get();
            if(oldDoan.Compare(entity)){
                new SuccessNoti("Không có thay đổi");
            }else{
                ConfirmAlert alert = new ConfirmAlert("Xác nhận sửa loại tour");
                if(alert.getConfirm()){
                    DoanEntity result = TourDoanSerivce.update(entity,entity.getId());
                    if(result!=null){
                        new SuccessNoti(true,SuccessNoti.SUA);
                        for(int i=0;i<doanEntities.size();i++){
                            if(Objects.equals(doanEntities.get(i).getId(), result.getId())){
                                doanEntities.set(i,result);
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
            e.printStackTrace();
            new SuccessNoti("Sửa Không thành công");
        }
    }

    @Override
    public void deleteEntity() {
        Integer id = Integer.valueOf(idTextField.getText());
        ConfirmAlert alert = new ConfirmAlert("Xác nhận xoá loại tour");
        if(alert.getConfirm()){
            if(TourDoanSerivce.delete(id)) {
                new SuccessNoti(true, SuccessNoti.XOA);
                for(int i=0;i<doanEntities.size();i++){
                    if(doanEntities.get(i).getId()==id){
                        doanEntities.remove(i);
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

    public void orderUpAction(ActionEvent actionEvent) {
    }

    public void onAddDiaDiemAction(ActionEvent actionEvent) {
    }

    public void orderDownAction(ActionEvent actionEvent) {
    }

    public void deleteDiaDiemOnAction(ActionEvent actionEvent) {
    }

    public void showDetail(ActionEvent actionEvent) {
    }

    public void onAddGiaButtton(ActionEvent actionEvent) {
        if(idTextField.getText().isEmpty()){
            return;
        }
        Dialog<ChiPhiEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm chi phí mới");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/AddChiPhi.fxml"));
        Pane pane;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddChiPhiController con = fxml.getController();

            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                        ChiPhiEntity e = new ChiPhiEntity();
                        e.setGia(BigDecimal.valueOf(Long.parseLong(con.chiPhiTextInput.getText())));
                        e.setGhichu(con.commentText.getText());
                        e.setLoaiChiPhiId(con.loaiChiPhiComboBox.getSelectionModel().getSelectedItem().getId());
                        return e;
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<ChiPhiEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                chiPhiEntities.add(result.get());
                List<ChiPhiEntity> list = chiPhiEntities;
                TourChiPhiEntity tourChiPhiEntity = TourChiPhiService.getByDoanId(Integer.valueOf(idTextField.getText()));
                tourChiPhiEntity.setChiphiChitiet(new Gson().toJson(list));
                TourChiPhiService.update(tourChiPhiEntity,tourChiPhiEntity.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateGia(ActionEvent actionEvent) {
       int id = chiPhiTable.getSelectionModel().getSelectedIndex();
       if(id==-1) {
           return;
       }
        Dialog<ChiPhiEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm chi phí mới");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/AddChiPhi.fxml"));
        Pane pane;
        try {
            ChiPhiEntity entity = chiPhiEntities.get(id);
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddChiPhiController con = fxml.getController();
            con.loaiChiPhiComboBox.getSelectionModel().select(entity.getLoaiChiPhiId()-1);
            con.commentText.setText(entity.getGhichu());
            con.chiPhiTextInput.setText(String.valueOf(entity.getGia()));
            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                        ChiPhiEntity e = new ChiPhiEntity();
                        e.setGia(BigDecimal.valueOf(Long.parseLong(con.chiPhiTextInput.getText())));
                        e.setGhichu(con.commentText.getText());
                        e.setLoaiChiPhiId(con.loaiChiPhiComboBox.getSelectionModel().getSelectedItem().getId());
                        return e;
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<ChiPhiEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                chiPhiEntities.set(id,result.get());
                List<ChiPhiEntity> list = chiPhiEntities;
                TourChiPhiEntity tourChiPhiEntity = TourChiPhiService.getByDoanId(Integer.valueOf(idTextField.getText()));
                tourChiPhiEntity.setChiphiChitiet(new Gson().toJson(list));
                TourChiPhiService.update(tourChiPhiEntity,tourChiPhiEntity.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onDeleteGiaAction(ActionEvent actionEvent) {
        int id = chiPhiTable.getSelectionModel().getSelectedIndex();
        if(id==-1) {
            return;
        }
        chiPhiEntities.remove(id);
        List<ChiPhiEntity> list = chiPhiEntities;
        TourChiPhiEntity tourChiPhiEntity = TourChiPhiService.getByDoanId(Integer.valueOf(idTextField.getText()));
        tourChiPhiEntity.setChiphiChitiet(new Gson().toJson(list));
        TourChiPhiService.update(tourChiPhiEntity,tourChiPhiEntity.getId());
    }

    public void PickTour() {
        if(ngayDiInput.getValue()==null) return;
        Dialog<TourEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm mới địa điểm");
        dialog.setHeaderText("Chọn địa điểm để thêm vào");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/AddTour.fxml"));
        Pane pane = null;
        try {

            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddTourController con = fxml.getController();
            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    return con.table.getSelectionModel().getSelectedItem();
                }
                return null;
            });
            Optional<TourEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                TourEntity tour = result.get();
                TourName.setText(tour.getTourTen());
                tourEntity = tour;
                List<TourGiaEntity> list = TourGiaService.getByTourId(tourEntity.getId());
                if(list.isEmpty()){
                    giaLabel.setText("Chưa có giá");
                    giaEntity = null;
                    return;
                }
                LocalDate ngaydi = ngayDiInput.getValue();
                ngaydi = LocalDate.of(2021,ngaydi.getMonthValue(),ngaydi.getDayOfMonth());
                list.sort((o1, o2) -> 0);
                LocalDate finalNgaydi = ngaydi;
                Optional<TourGiaEntity> entity = list.stream().filter(e->{
                    LocalDate temp = new java.sql.Date(e.getGiaTungay().getTime()).toLocalDate();
                    temp = LocalDate.of(2021,temp.getMonthValue(),temp.getDayOfMonth());
                    return temp.isAfter(finalNgaydi);
                }).findFirst();
                giaEntity = entity.orElseGet(() -> list.get(list.size() - 1));
                giaLabel.setText(defaultFormat.format(giaEntity.getGiaSotien()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddKhachHangAction() {
        if(idTextField.getText().isEmpty()){
            return;
        }
        Dialog<KhachHangEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm khách hàng mới");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/Add_KhachHang.fxml"));
        Pane pane;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddKhachHangContoller con = fxml.getController();

            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                        return con.table.getSelectionModel().getSelectedItem();
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<KhachHangEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                if(khachHangEntities.stream().anyMatch(new Predicate<KhachHangEntity>() {
                    @Override
                    public boolean test(KhachHangEntity khachHangEntity) {
                        return Objects.equals(khachHangEntity.getId(), result.get().getId());
                    }
                })) return;
                khachHangEntities.add(result.get());
                List<KhachHangEntity> list = khachHangEntities;
                NguoiDiEntity nguoiDiEntity = NguoiDiService.getByDoanId(Integer.valueOf(idTextField.getText()));
                nguoiDiEntity.setNguoidiDskhach(new Gson().toJson(list));
                NguoiDiService.update(nguoiDiEntity,nguoiDiEntity.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteKhachHangAction() {
        int id = KhachHangTable.getSelectionModel().getSelectedIndex();
        if(id==-1) {
            return;
        }
        khachHangEntities.remove(id);
        List<KhachHangEntity> list = khachHangEntities;
        NguoiDiEntity nguoiDiEntity = NguoiDiService.getByDoanId(Integer.valueOf(idTextField.getText()));
        nguoiDiEntity.setNguoidiDskhach(new Gson().toJson(list));
        NguoiDiService.update(nguoiDiEntity,nguoiDiEntity.getId());
    }

    public void onAddNhanVienAction(ActionEvent actionEvent) {
        if(idTextField.getText().isEmpty()){
            return;
        }
        Dialog<NhanVienEntity> dialog =  new Dialog();
        dialog.setTitle("Thêm nhân viên mới");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/tourdulich/tourdulich/Add_NhanVien.fxml"));
        Pane pane;
        try {
            pane = fxml.load();
            dialog.getDialogPane().setContent(pane);
            AddNhanVienController con = fxml.getController();

            dialog.setResultConverter(dialogButton->{
                if(dialogButton == ButtonType.OK){
                    try{
                        return con.table.getSelectionModel().getSelectedItem();
                    }catch (NumberFormatException ex){
                        throw new NumberFormatException("Chịu");
                    }
                }
                return null;
            });

            Optional<NhanVienEntity> result = dialog.showAndWait();
            if (result.isPresent()){
                if(nhanVienEntities.stream().anyMatch(khachHangEntity -> Objects.equals(khachHangEntity.getId(), result.get().getId()))) return;
                nhanVienEntities.add(result.get());
                List<NhanVienEntity> list = nhanVienEntities;
                NguoiDiEntity nguoiDiEntity = NguoiDiService.getByDoanId(Integer.valueOf(idTextField.getText()));
                nguoiDiEntity.setNguoidiDsnhanvien(new Gson().toJson(list));
                NguoiDiService.update(nguoiDiEntity,nguoiDiEntity.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVienOnAction(ActionEvent actionEvent) {
        int id = NhanVienTable.getSelectionModel().getSelectedIndex();
        if(id==-1) {
            return;
        }
        khachHangEntities.remove(id);
        List<NhanVienEntity> list = nhanVienEntities;
        NguoiDiEntity nguoiDiEntity = NguoiDiService.getByDoanId(Integer.valueOf(idTextField.getText()));
        nguoiDiEntity.setNguoidiDsnhanvien(new Gson().toJson(list));
        NguoiDiService.update(nguoiDiEntity,nguoiDiEntity.getId());
    }

    public void onSearchAction(ActionEvent actionEvent) {
        String input = SearchText.getText().toLowerCase();
        if(input.isBlank()){
            table.setItems(doanEntities);
            return;
        }
        DoanEntity[] a = doanEntities.stream().filter(e->{
            return e.getId().toString().contains(input)||e.getDoanName().toLowerCase().contains(input)
                    ||e.getTour().getTourTen().toLowerCase().contains(input);
        }).toArray(DoanEntity[]::new);
        ObservableList<DoanEntity> list = FXCollections.observableArrayList(Arrays.asList(a));
        table.setItems(list);
    }
}
