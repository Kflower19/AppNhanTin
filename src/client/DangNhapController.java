package client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DangNhapController {

    @FXML
    private TextField textFieldTaiKhoan;
    
    @FXML
    private PasswordField passwordFieldMatKhau;
    
    private  String DB_URL = "jdbc:mysql://localhost:3306/dbTaiKhoan"; // Thay đổi tên DB của bạn
    private  String DB_USER = "root"; // Tên người dùng của bạn
    private  String DB_PASSWORD = "khoa1601"; // Mật khẩu của bạn

    // Hàm xử lý đăng nhập
    @FXML
    public void handleDangNhap() {
        String taiKhoan = textFieldTaiKhoan.getText();
        String matKhau = passwordFieldMatKhau.getText();

        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            thongBao("Vui lòng nhập tài khoản và mật khẩu!");
            return;
        }

        if (kiemTraTaiKhoanMatKhau(taiKhoan, matKhau)) {
            // Đăng nhập thành công, mở giao diện chính
            KiemThuClient.openMainScreen();
        } else {
            thongBao("Sai tài khoản hoặc mật khẩu!");
        }
    }

    // Hàm kiểm tra tài khoản và mật khẩu
    private boolean kiemTraTaiKhoanMatKhau(String taiKhoan, String matKhau) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE tai_khoan = ? AND mat_khau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, taiKhoan);
            stmt.setString(2, matKhau);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Nếu tìm thấy tài khoản trong CSDL, trả về true
        } catch (Exception e) {
            e.printStackTrace();
            thongBao("Lỗi kết nối với cơ sở dữ liệu!");
            return false;
        }
    }

    // Hàm hiển thị thông báo
    private void thongBao(String thongBao) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(thongBao);
        alert.showAndWait();
    }
}
