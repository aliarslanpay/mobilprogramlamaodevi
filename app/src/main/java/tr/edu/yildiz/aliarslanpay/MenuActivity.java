package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    String mail="";
    Button addQuestionB, questionListB, examSettingsB, createExamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addQuestionB = findViewById(R.id.addQuestionButton);
        questionListB = findViewById(R.id.questionListButton);
        examSettingsB = findViewById(R.id.examSettingsButton);
        createExamB = findViewById(R.id.createExamButton);

        Bundle intentExtras = getIntent().getExtras();
        String mail = intentExtras.getString("usermail");
        System.out.println(mail);
        addQuestionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddQuestionActivity.class);
                Bundle extras = new Bundle();
                extras.putString("usermail", mail);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        questionListB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuestionListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("usermail", mail);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        examSettingsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ExamSettingsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("usermail", mail);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        createExamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CreateExamActivity.class);
                Bundle extras = new Bundle();
                extras.putString("usermail", mail);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}