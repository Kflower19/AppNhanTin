/**
 * 
 */
/**
 * @author acer
 *
 */
module miniZalo {
	
	
	requires java.sql;
	requires javafx.controls;
	requires javafx.fxml;
	opens client to javafx.graphics, javafx.fxml;
	opens server to javafx.graphics, javafx.fxml;
}