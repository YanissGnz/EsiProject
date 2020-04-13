package com.example.esiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Feedback_1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        final EditText feedback_name=(EditText) findViewById(R.id.feedback_name);
        final EditText feedback=(EditText) findViewById(R.id.feedback);
        Button send_f=(Button) findViewById(R.id.send_feedback);
        send_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("message/html");
                i.putExtra(Intent.EXTRA_EMAIL,new String("a.krebbaza@esi-sba.dz"));
                i.putExtra(Intent.EXTRA_SUBJECT,"Feedback From EsiApp");
                i.putExtra(Intent.EXTRA_TEXT,"Name"+feedback_name.getText()+"\n Message:"+feedback.getText());
                try {
                    startActivity(Intent.createChooser(i,"Please select email"));
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(Feedback_1.this,"There are no Email Clients",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
