package com.example.combinator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name_edit_text);

        start = findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.text_error)
                            .setMessage(R.string.text_empty_name_error);
                    Dialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    String name = nameEditText.getText().toString();
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });
    }
}