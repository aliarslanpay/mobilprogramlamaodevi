package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExamSettingsActivity extends AppCompatActivity {

    EditText durationText, scoreText, difficultyText;
    TextView textView1, textView2, textView3;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        durationText = findViewById(R.id.durationText);
        scoreText = findViewById(R.id.scoreText);
        difficultyText = findViewById(R.id.difficultyText);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);


        sharedPreferences = this.getSharedPreferences("tr.edu.yildiz.aliarslanpay", Context.MODE_PRIVATE);

        int storedDuration = sharedPreferences.getInt("duration",60);
        int storedScore = sharedPreferences.getInt("score",10);
        int storedDifficulty = sharedPreferences.getInt("difficulty",5);

        textView1.setText("Duration: "+storedDuration);
        textView2.setText("Score: "+storedScore);
        textView3.setText("Difficulty: "+storedDifficulty);

    }

    public void save(View view){

        if(!durationText.getText().toString().matches("") &&
                !scoreText.getText().toString().matches("") && !difficultyText.getText().toString().matches("")){
            int duration = Integer.parseInt(durationText.getText().toString());
            int score = Integer.parseInt(scoreText.getText().toString());
            int difficulty = Integer.parseInt(difficultyText.getText().toString());

            if(difficulty< 2 || difficulty>5){
                Toast.makeText(getApplicationContext(),"Difficulty Level must be between 2 and 5!",Toast.LENGTH_SHORT).show();
                difficultyText.setText("");
            }
            else{
                sharedPreferences.edit().putInt("duration",duration).apply();
                sharedPreferences.edit().putInt("score",score).apply();
                sharedPreferences.edit().putInt("difficulty",difficulty).apply();

                textView1.setText("Duration: "+duration);
                textView2.setText("Score: "+score);
                textView3.setText("Difficulty: "+difficulty);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"All fields must be filled!",Toast.LENGTH_SHORT).show();
        }
    }
}