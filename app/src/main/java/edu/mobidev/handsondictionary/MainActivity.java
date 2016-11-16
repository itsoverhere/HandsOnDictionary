package edu.mobidev.handsondictionary;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvWords;
    TextView tvWord;
    TextView tvDescription;
    Button buttonAdd;
    DatabaseHelper dbHelper;
    WordAdapter wordAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWords = (RecyclerView) findViewById(R.id.rv_words);
        tvWord = (TextView) findViewById(R.id.tv_word);
        buttonAdd = (Button) findViewById(R.id.button_add);
        tvDescription = (TextView) findViewById(R.id.tv_description);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AddWordActivity.class));
            }
        });

        LinearLayoutManager llm
                = new LinearLayoutManager(getBaseContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false );

        rvWords.setLayoutManager(llm);

        dbHelper = new DatabaseHelper(getBaseContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = dbHelper.getAllWordsCursor();

        if(wordAdapter == null){
            wordAdapter = new WordAdapter(getBaseContext(), cursor, new WordAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Word w) {
                    tvWord.setText(w.getWord());
                    tvDescription.setText(w.getDescription());
                }
            });
            wordAdapter.setmOnItemLongClickListener(new WordAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(long id, int position) {
                    int result = dbHelper.deleteWord(id);
                    wordAdapter.notifyItemRemoved(position);
                }
            });
            rvWords.setAdapter(wordAdapter);
        }else{
            wordAdapter.changeCursor(cursor);
            wordAdapter.notifyDataSetChanged();
        }
    }
}
