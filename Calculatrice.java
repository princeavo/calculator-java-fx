package com;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculatrice extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	boolean bool = false;
	Calcul calc = new Calcul();
	Label ecran = new Label("0");

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) throws Exception {
		ecran.setPrefSize(320, 40);
		ecran.setFont(Font.font(20));
		ecran.setAlignment(Pos.CENTER_RIGHT);
		ecran.setBorder(Border.stroke(Color.BLACK));
		ecran.setPadding(new Insets(10));

		Label info = new Label("puerohzr iiii");
		info.setPrefSize(320, 40);
		info.setAlignment(Pos.CENTER_RIGHT);
		info.setPadding(new Insets(10));

		Button[] chiffres = new Button[10];
		for (int i = 0; i <= 9; i++) {
			chiffres[i] = new Button(String.valueOf(i));
			chiffres[i].setPrefSize(80, 40);
		}

		Button[] operateurs = new Button[5];
		operateurs[0] = new Button("C");
		operateurs[1] = new Button("+");
		operateurs[2] = new Button("-");
		operateurs[3] = new Button("/");
		operateurs[4] = new Button("*");
		operateurs[4].setPrefSize(80, 40);

		Button point = new Button(".");
		Button egal = new Button("=");
		egal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		GridPane gridPane = new GridPane();
		int numero = 1;
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (numero <= 9)
					gridPane.add(chiffres[numero++], j, i);
			}
		}
		gridPane.add(chiffres[0], 0, 3);

		for (int i = 0; i <= 3; i++) {
			gridPane.add(operateurs[i], 3, i);
			operateurs[i].setPrefSize(80, 40);
		}

		gridPane.add(operateurs[4], 2, 3);
		gridPane.add(point, 1, 3);
		gridPane.add(egal, 0, 4, 4, 1);
		point.setPrefSize(80, 40);

		VBox root = new VBox(5);
		root.setPadding(new Insets(10));
		root.getChildren().addAll(ecran, info, gridPane);

		info.textProperty().bind(calc.getCalcul());

		// Les actions sur les boutons

		for (Button bouton : chiffres) {
			bouton.setOnAction(e -> {
				if (bool) {
					ecran.setText(bouton.getText());
					bool = false;
					calc.setOperande2(Double.parseDouble(ecran.getText()));
				} else if (calc.getOperateur() != ' ') {
					if (!ecran.getText().equals("0")) {
						ecran.setText(ecran.getText() + bouton.getText());
					} else {
						ecran.setText(bouton.getText());
					}
					calc.setOperande2(Double.parseDouble(ecran.getText()));
				} else if (!ecran.getText().equals("0")) {
					ecran.setText(ecran.getText() + bouton.getText());
				} else {
					ecran.setText(bouton.getText());
				}
			});
		}
		point.setOnAction(e -> {
			if (ecran.getText().indexOf('.') == -1) {
				ecran.setText(ecran.getText() + '.');
			}
		});
		egal.setOnAction(e -> {
			if (calc.getOperateur() == ' ') {
				calc.reset();
				bool = true;
			} else {
				calculer();
			}
			if (calc.getOperateur() != ' ')
				calc.setOperande1(Double.parseDouble(ecran.getText()));
		});

		for (Button op : operateurs) {
			if (!op.equals(operateurs[0])) {
				op.setOnAction(e -> {
					bool = true;
					calculer();
					calc.setOperande1(Double.parseDouble(ecran.getText()));
					calc.setOperateur(op.getText().charAt(0));
				});

			} else {
				op.setOnAction(e -> {
					calc.reset();
					ecran.setText("0");
					bool = false;
				});

			}
		}

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setMaxWidth(330);
		primaryStage.setMaxHeight(300);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setTitle("Ma calculatrice");
		primaryStage.getIcons().add(new Image(getClass().getResource("calculatrice.png").toString()));

	}

	public void calculer() {
		switch (calc.getOperateur()) {
		case '+': {
			calc.addition();
			ecran.setText(String.valueOf(calc.getResultat()));
			break;
		}
		case '-': {
			calc.soustraction();
			ecran.setText(String.valueOf(calc.getResultat()));
			break;
		}
		case '*': {
			calc.multiplication();
			ecran.setText(String.valueOf(calc.getResultat()));
			break;
		}
		case '/': {
			calc.division();
			ecran.setText(String.valueOf(calc.getResultat()));
			break;
		}
		default:
//			throw new IllegalArgumentException("Unexpected value: " + calculatrice.getOperateur());
		}
	}

}
/*
 * Quand on appuie sur un chiffre{ ça s'ajoute au chiffre à l'écran } op{
 * 
 * }
 * 
 */
