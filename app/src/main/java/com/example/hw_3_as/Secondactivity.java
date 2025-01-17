package com.example.hw_3_as;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Secondactivity extends AppCompatActivity {
    String[] type = {"Item 1", "Item 2", "Item 3"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    private TextView mainText;
    private boolean isHeartPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });
        AppCompatButton btnNext = findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Secondactivity.this, MainActivity.class);
                // Устанавливаем флаг, чтобы очистить все активити в стеке
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                finishAffinity();
            }
        });
        mainText = findViewById(R.id.mainText);
        String result = getIntent().getStringExtra("result");
        if (result != null) {
            mainText.setText(result);  // Меняем текст на результат
        }
        autoCompleteTextView = findViewById(R.id.auto_complete_view);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, type);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = adapterItems.getItem(position).toString();

            }
        });

        ImageView heartImageView = findViewById(R.id.heart_image_view);
        heartImageView.setOnClickListener( v->{
            if (isHeartPressed){
                heartImageView.setImageResource(R.drawable.ic_heart);
            }else{
                heartImageView.setImageResource(R.drawable.ic_heart_selected);
            }
            isHeartPressed = !isHeartPressed;
        });


    }
}