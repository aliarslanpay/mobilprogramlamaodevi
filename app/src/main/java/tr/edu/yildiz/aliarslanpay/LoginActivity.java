package tr.edu.yildiz.aliarslanpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mailText, passwordText;
    Button loginButton, registerButton;
    DatabaseHelper db;
    int count;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        mailText = findViewById(R.id.emailLogin);
        passwordText = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerLogin);
        count = 0;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mailText.getText().toString();
                String password = passwordText.getText().toString();
                Boolean checkLogin = db.loginCheck(email, password);
                if (checkLogin==true){
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    System.out.println(email);
                    Bundle extras = new Bundle();
                    extras.putString("usermail", email);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong email or password!",Toast.LENGTH_SHORT).show();
                    count++;
                    if(count==3){
                        Toast.makeText(getApplicationContext(),"You have entered wrong username/password 3 times in a row!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}