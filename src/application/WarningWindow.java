package application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WarningWindow {
	public static void display(String title,String warning) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		
		Label label = new Label ();
		label.setText(warning);
		Button close=new Button("close warning");
		close.setOnAction(arg0->window.close());
		VBox disp = new VBox();
		disp.getChildren().addAll(label,close);
		disp.setAlignment(Pos.CENTER);
		
		Scene warningScene = new Scene(disp);
		window.setScene(warningScene);
		window.showAndWait();
		
		
		
	}
}
