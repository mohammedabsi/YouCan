package com.example.youcan.model.recipegson;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class HitsItem{

	@SerializedName("bookmarked")
	private boolean bookmarked;

	@SerializedName("bought")
	private boolean bought;

	@SerializedName("recipe")
	private Recipe recipe;

	public void setBookmarked(boolean bookmarked){
		this.bookmarked = bookmarked;
	}

	public boolean isBookmarked(){
		return bookmarked;
	}

	public void setBought(boolean bought){
		this.bought = bought;
	}

	public boolean isBought(){
		return bought;
	}

	public void setRecipe(Recipe recipe){
		this.recipe = recipe;
	}

	public Recipe getRecipe(){
		return recipe;
	}

	@Override
 	public String toString(){
		return 
			"HitsItem{" + 
			"bookmarked = '" + bookmarked + '\'' + 
			",bought = '" + bought + '\'' + 
			",recipe = '" + recipe + '\'' + 
			"}";
		}
}