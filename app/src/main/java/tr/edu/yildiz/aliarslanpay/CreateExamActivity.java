package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}