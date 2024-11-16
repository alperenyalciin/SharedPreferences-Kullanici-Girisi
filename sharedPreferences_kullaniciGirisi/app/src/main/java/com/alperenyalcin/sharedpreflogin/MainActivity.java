package com.alperenyalcin.sharedpreflogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.edittextIsim);
        editText2 = findViewById(R.id.editTextTextSoyisim);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.alperenyalcin.sharedpreflogin", Context.MODE_PRIVATE);
        String strName = sharedPreferences.getString("myname", "kayıt yok");
        String strSurname = sharedPreferences.getString("mysurname", "kayıt yok");
        textView.setText("name:" + strName + " " + " surname: " + strSurname);
        Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();

    }

    public void save(View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Save");
        alert.setMessage("are you sure");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"saved",Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Not saved",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();


        String name = editText.getText().toString();
        String surname = editText2.getText().toString();
        textView.setText("adınız: " + name + " " + "soyadınız: " + surname);
        sharedPreferences.edit().putString("myname", name).apply();
        sharedPreferences.edit().putString("mysurname", surname).apply();




    }


}