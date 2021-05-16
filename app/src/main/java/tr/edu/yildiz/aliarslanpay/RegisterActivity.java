package tr.edu.yildiz.aliarslanpay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText nameText, surnameText, phoneText, mailText, passwordText, password2Text;
    Button registerButton;
    ImageView imageView;
    Bitmap selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
        phoneText = findViewById(R.id.phoneText);
        mailText = findViewById(R.id.mailText);
        passwordText = findViewById(R.id.passwordText);
        password2Text = findViewById(R.id.password2Text);
        registerButton = findViewById(R.id.registerButton);
        imageView = findViewById(R.id.imageView3);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = nameText.getText().toString();
                String s2 = surnameText.getText().toString();
                String s3 = phoneText.getText().toString();
                String s4 = mailText.getText().toString();
                String s5 = passwordText.getText().toString();
                String s6 = password2Text.getText().toString();

                Bitmap smallImage = makeSmallerImage(selectedImage,300);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                byte[] byteArray = outputStream.toByteArray();

                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")){
                    Toast.makeText(getApplicationContext(), "Alanlar doldurulmalıdır!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s5.equals(s6)){
                        Boolean checkisValidEmail = db.isValidEmail(s4);
                        if(checkisValidEmail){
                            Boolean checkmail = db.checkEmail(s4);
                            if(checkmail==true){
                                Boolean insert = db.insert(s1, s2, s4, s3, s5,byteArray);
                                if(insert==true){
                                    Toast.makeText(getApplicationContext(),"Registration Successful!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"There is a user with this e-mail address!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please enter a valid email address!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                        Toast.makeText(getApplicationContext(),"Passwords do not match!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void selectImage(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri imageData = data.getData();


            try {

                if (Build.VERSION.SDK_INT >= 28) {

                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);

                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }
}