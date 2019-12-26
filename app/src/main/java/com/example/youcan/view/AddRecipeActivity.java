package com.example.youcan.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.youcan.R;
import com.example.youcan.model.SampleSearchModel;
import com.example.youcan.viewmodel.RecipeViewModel;

import java.util.ArrayList;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;

public class AddRecipeActivity extends AppCompatActivity {

    private RecipeViewModel recipeViewModel ;
    private  Bundle bundle;
    public static final String MEAL_TYPE = "meal type";
    private AddManualBottomSheet bottomSheet;
    private ArrayList<SampleSearchModel> createSampleData() {
        ArrayList<SampleSearchModel> items = new ArrayList<>();
        items.add(new SampleSearchModel("First item"));
        items.add(new SampleSearchModel("Second item"));
        items.add(new SampleSearchModel("Third item"));
        items.add(new SampleSearchModel("The ultimate item"));
        items.add(new SampleSearchModel("Last item"));
        items.add(new SampleSearchModel("Lorem ipsum"));
        items.add(new SampleSearchModel("Dolor sit"));
        items.add(new SampleSearchModel("Some random word"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess whqo's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess w22o's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess wh2 who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("g2back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's 22back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who'wws back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guess ass back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("gauess wdho's back"));
        items.add(new SampleSearchModel("guess who's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guees back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessaes back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessases bsdfsdsdff's back"));
        items.add(new SampleSearchModel("guessas's basdfsdck"));
        items.add(new SampleSearchModel("guessadfdfhdfack"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        items.add(new SampleSearchModel("guessas's back"));
        return items;
    }

    private androidx.appcompat.widget.SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeViewModel = new RecipeViewModel();

        bottomSheet=new AddManualBottomSheet();

        search_view = findViewById(R.id.search_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        addManualMeal();
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText!= null && !newText.isEmpty()){
                    recipeViewModel.getRecipeInfo(newText);
                }


                return false;
            }
        });

        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Recipe </font>"));
        toolbar.setSubtitle("search and add");
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddRecipeActivity.this, HomePageActivity.class));
                finish();
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu_item:
                new SimpleSearchDialogCompat(AddRecipeActivity.this, "What are you looking for...?",
                        "Search...", null, createSampleData(),
                        new SearchResultListener<SampleSearchModel>() {

                            @Override
                            public void onSelected(BaseSearchDialogCompat dialog,
                                                   SampleSearchModel item, int position) {


                                Toast.makeText(AddRecipeActivity.this, item.getTitle(),
                                        Toast.LENGTH_SHORT).show();
                                search_view.clearFocus();
                                search_view.setIconified(true);
                                dialog.dismiss();
                            }

                        }).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void addManualMeal() {
        findViewById(R.id.add_maunal_breakfast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle=new Bundle();
                bottomSheet=new AddManualBottomSheet();
                bundle.putInt(MEAL_TYPE,0);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(),"bottomsheet");

            }
        });
        findViewById(R.id.add_maunal_lunch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle=new Bundle();
                bundle.putInt(MEAL_TYPE,1);
                bottomSheet=new AddManualBottomSheet();
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(),"bottomsheet");
            }
        });
        findViewById(R.id.add_maunal_snacks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle=new Bundle();
                bottomSheet=new AddManualBottomSheet();
                bundle.putInt(MEAL_TYPE,2);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(),"bottomsheet");
            }
        });
        findViewById(R.id.add_maunal_dinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle=new Bundle();
                bottomSheet=new AddManualBottomSheet();
                bundle.putInt(MEAL_TYPE,3);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(),"bottomsheet");
            }
        });

    }


}
