package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KiemThuClient extends Application {
	@Override
    public void start(Stage sanKhau) {
	    try {
	        // Tạo giao diện đăng nhập
	        Parent giaoDienDangNhap = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
	        Scene sceneDangNhap = new Scene(giaoDienDangNhap);  // Chỉ sử dụng sceneDangNhap
	        
	        // Thêm CSS
	        String css = this.getClass().getResource("dangnhap.css").toExternalForm();
	        sceneDangNhap.getStylesheets().add(css);

	        // Thiết lập Stage với Scene
	        sanKhau.setTitle("Đăng nhập");
	        sanKhau.setScene(sceneDangNhap);  // Chỉ gán sceneDangNhap
	        sanKhau.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }

    }
    
    // Mở giao diện chính khi đăng nhập thành công
    public static void openMainScreen() {
        try {
            // Mở giao diện chính (ViewClient.fxml)
            Stage sanKhau = new Stage();
            Parent giaoDien = FXMLLoader.load(KiemThuClient.class.getResource("ViewClient.fxml"));
            Scene scene = new Scene(giaoDien);
            
            String css = KiemThuClient.class.getResource("ViewClient.css").toExternalForm();
            scene.getStylesheets().add(css);
            
            sanKhau.setTitle("Ứng dụng nhắn tin MiniZalo");
            sanKhau.setScene(scene);
            sanKhau.show();
            sanKhau.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
