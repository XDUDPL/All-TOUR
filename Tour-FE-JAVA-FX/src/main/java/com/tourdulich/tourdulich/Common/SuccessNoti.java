package com.tourdulich.tourdulich.Common;

import javafx.scene.control.Alert;

public class SuccessNoti extends Alert {
    public static final String THEM = "Thêm";
    public static final String SUA = "Sửa";
    public static final String XOA = "Xoá";
    public static final String HUY = "Huỷ";
    public static final String REFUSE_CONNECTION = "Lỗi kết nối dữ liệu";
    public SuccessNoti(boolean result,String action) {
        super(AlertType.INFORMATION);
        setTitle("Thông báo");
        setHeaderText("Kết quả hành động");
        if(result){
            setContentText(action+" thành công");
        }else{
            setContentText(action+" thất bại");
        }
        show();
    }
    public SuccessNoti(String action) {
        super(AlertType.INFORMATION);
        setTitle("Thông báo");
        setHeaderText("Kết quả hành động");
        setContentText(action);
        show();
    }
}
