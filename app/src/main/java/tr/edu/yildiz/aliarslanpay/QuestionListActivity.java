package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class QuestionListActivity extends AppCompatActivity {

    ArrayList<String> userQuestion, trueOption, option2, option3, option4, option5;
    SQLiteDatabase database;
    RecyclerAdapter recyclerAdapter;
    String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        userQuestion = new ArrayList<>();
        trueOption = new ArrayList<>();
        option2 = new ArrayList<>();
        option3 = new ArrayList<>();
        option4 = new ArrayList<>();
        option5 = new ArrayList<>();


        Bundle intentExtras = getIntent().getExtras();
        info = intentExtras.getString("usermail");
        System.out.println("Treop: " + info);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(userQuestion, trueOption, option2, option3, option4, option5);
        recyclerView.setAdapter(recyclerAdapter);

        getData();

    }
    public void getData() {
        try {
            database = this.openOrCreateDatabase("Questions", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM questions WHERE email = ?", new String[]{String.valueOf(info)});

            int questionIx = cursor.getColumnIndex("question");
            int trueOptionIx = cursor.getColumnIndex("trueOption");
            int option2Ix = cursor.getColumnIndex("option2");
            int option3Ix = cursor.getColumnIndex("option3");
            int option4Ix = cursor.getColumnIndex("option4");
            int option5Ix = cursor.getColumnIndex("option5");

            while (cursor.moveToNext()) {
                System.out.println("Question: " + cursor.getString(questionIx));
                System.out.println("Treop: " + cursor.getString(trueOptionIx));
                userQuestion.add(cursor.getString(questionIx));
                trueOption.add(cursor.getString(trueOptionIx));
                option2.add(cursor.getString(option2Ix));
                option3.add(cursor.getString(option3Ix));
                option4.add(cursor.getString(option4Ix));
                option5.add(cursor.getString(option5Ix));
            }
            recyclerAdapter.notifyDataSetChanged();
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}