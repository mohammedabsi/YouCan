package com.example.youcan.model.recipegson;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class IngredientsItem{

	@SerializedName("weight")
	private double weight;

	@SerializedName("text")
	private String text;

	public void setWeight(double weight){
		this.weight = weight;
	}

	public double getWeight(){
		return weight;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"IngredientsItem{" + 
			"weight = '" + weight + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}