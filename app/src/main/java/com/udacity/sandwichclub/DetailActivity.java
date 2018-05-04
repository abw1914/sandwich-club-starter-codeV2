package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;


public class DetailActivity extends AppCompatActivity {

    // ActivityMainBinding mBinding;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }


        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;


        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (sandwich.getMainName() != null) {
            String mainName = sandwich.getMainName().toString();
            mainName.toString();
        }

        if (sandwich.getAlsoKnownAs() != null) {
            TextView alsoKnowsAsText = findViewById(R.id.also_known_tv);
            String alsoKnowsAs = sandwich.getAlsoKnownAs().toString().replaceAll("\\[", "").replaceAll("\\]","");
            alsoKnowsAsText.setText(alsoKnowsAs);
        } else {
            TextView alsoKnownAsLabel = findViewById(R.id.textView4);
            alsoKnownAsLabel.setVisibility(View.VISIBLE);
        }

        if (sandwich.getDescription() != null) {
            TextView descriptionText = findViewById(R.id.description_tv);
            String description = sandwich.getDescription().toString();
            descriptionText.setText(description);
        } else {
            TextView descriptionLabel = findViewById(R.id.textView3);
            descriptionLabel.setVisibility(View.VISIBLE);
        }

        if (sandwich.getPlaceOfOrigin() != null) {
            TextView placeOfOriginText = findViewById(R.id.origin_tv);
            String placeOfOrigin = sandwich.getPlaceOfOrigin().toString();
            placeOfOriginText.setText(placeOfOrigin);
        } else {
            TextView placeOfOriginLabel = findViewById(R.id.textView);
            placeOfOriginLabel.setText(View.VISIBLE);
        }

        if (sandwich.getIngredients() != null) {

            TextView ingredientsText = findViewById(R.id.ingredients_tv);
            String ingredients = sandwich.getIngredients().toString().replaceAll("\\[", "").replaceAll("\\]","");
            ingredientsText.setText(ingredients);
        } else {
            TextView ingredientsLabel = findViewById(R.id.textView2);
            ingredientsLabel.setText(View.VISIBLE);
        }


    }

}

