package com.example.youcan.model.recipegson;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class VITK1{

	@SerializedName("unit")
	private String unit;

	@SerializedName("quantity")
	private double quantity;

	@SerializedName("label")
	private String label;

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setQuantity(double quantity){
		this.quantity = quantity;
	}

	public double getQuantity(){
		return quantity;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return label;
	}

	@Override
 	public String toString(){
		return 
			"VITK1{" + 
			"unit = '" + unit + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",label = '" + label + '\'' + 
			"}";
		}
}