package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuestionActivity extends AppCompatActivity {

    EditText questionText, trueOptionText, option2Text, option3Text, option4Text, option5Text;
    Button addFile, addQuestion, button;
    SQLiteDatabase database;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle intentExtras = getIntent().getExtras();
        mail = intentExtras.getString("usermail");

        System.out.println(mail);
        questionText = findViewById(R.id.r_questionText);
        trueOptionText = findViewById(R.id.r_trueOption);
        option2Text = findViewById(R.id.r_option2);
        option3Text = findViewById(R.id.r_option3);
        option4Text = findViewById(R.id.r_option4);
        option5Text = findViewById(R.id.r_option5);
        addFile = findViewById(R.id.r_addFileButton);
        addQuestion = findViewById(R.id.addQuestionButton);
        database = this.openOrCreateDatabase("Questions",MODE_PRIVATE,null);

    }


    public void save(View view) {

        String question = questionText.getText().toString();
        String trueOption = trueOptionText.getText().toString();
        String option2 = option2Text.getText().toString();
        String option3 = option3Text.getText().toString();
        String option4 = option4Text.getText().toString();
        String option5 = option5Text.getText().toString();

        System.out.println(question);

        try {

            database = this.openOrCreateDatabase("Questions",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS questions (id INTEGER PRIMARY KEY,email VARCHAR, question VARCHAR, " +
                    "trueOption VARCHAR, option2 VARCHAR, option3 VARCHAR, option4 VARCHAR, option5 VARCHAR)");

            String sqlString = "INSERT INTO questions (email, question, trueOption, option2, option3, option4, option5) VALUES (?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,mail);
            sqLiteStatement.bindString(2,question);
            sqLiteStatement.bindString(3,trueOption);
            sqLiteStatement.bindString(4,option2);
            sqLiteStatement.bindString(5,option3);
            sqLiteStatement.bindString(6,option4);
            sqLiteStatement.bindString(7,option5);
            sqLiteStatement.execute();


        } catch (Exception e) {

        }


        Intent intent = new Intent(AddQuestionActivity.this,MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

}