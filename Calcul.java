package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class Calcul {
	private double operande1 = 0;
	private double operande2 = 0;
	private double resultat = 0;
	private char operateur =' ';
	private StringProperty calcul = new SimpleStringProperty(this, "calcul", " ");
	private boolean point = false;
	
	
	public void addition() {
		resultat = operande1+ operande2;
	}
	public void soustraction() {
		resultat = operande1- operande2;
	}
	public void multiplication() {
		resultat = operande1* operande2;
	}
	public void division() {
		resultat = operande1/ operande2;
	}
	public double getOperande1() {
		return operande1;
	}
	public void setOperande1(double operande1) {
		this.operande1 = operande1;
		calcul.set(operande1 + " "+operateur+" "+operande2);
	}
	public double getOperande2() {
		return operande2;
	}
	public void setOperande2(double operande2) {
		this.operande2 = operande2;
		calcul.set(operande1 + " "+operateur+" "+operande2);
	}
	public double getResultat() {
		return resultat;
	}
	public void setResultat(double resultat) {
		this.resultat = resultat;
	}
	public void reset() {
		this.setOperande1(0); 
		this.setOperande2(0);
		this.setResultat(0);
		this.setOperateur(' ');
		this.calcul.setValue(" ");
	}
	public char getOperateur() {
		return operateur;
	}
	public void setOperateur(char operateur) {
		this.operateur = operateur;
		calcul.set(operande1 + " "+operateur+" "+operande2);
	}
	public StringProperty getCalcul() {
		return calcul;
	}
	public boolean getPoint() {
		return point;
	}
	public void setPoint(boolean point) {
		this.point = point;
	}
}
