package edu.mobidev.handsondictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;

public class AddWordActivity extends AppCompatActivity {

    EditText etWord, etDescription;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        etWord = (EditText) findViewById(R.id.et_word);
        etDescription = (EditText) findViewById(R.id.et_description);
        buttonAdd = (Button) findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWord.getText().toString();
                String description = etDescription.getText().toString();
                if(!word.trim().isEmpty() && !description.trim().isEmpty()){
                    new DatabaseHelper(getBaseContext())
                            .addWord(new Word(word, description));
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "Please enter a word and a description.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
