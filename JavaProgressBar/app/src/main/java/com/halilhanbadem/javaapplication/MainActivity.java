package com.halilhanbadem.javaapplication;


import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText edt;
    private ProgressBar progress;
    private int progressdurum = 0;
    private Handler handler = new Handler();
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.btnClick);
        edt = (EditText) findViewById(R.id.edtMesaj);
        progress = (ProgressBar) findViewById(R.id.prgBar);
        text = (TextView) findViewById(R.id.textDurum);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Mesaj = new AlertDialog.Builder(MainActivity.this);
                Mesaj.setMessage("Mesajınız: " + edt.getText());
                Mesaj.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressdurum = 0;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progressdurum < 100) {
                                 progressdurum += 1;
                                 handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         progress.setProgress(progressdurum);
                                         text.setText("Yükleme başlatıldı: " + progressdurum);
                                     }
                                 });
                                  try {
                                      Thread.sleep(1000);
                                  }
                                  catch (InterruptedException e)
                                  {
                                      e.printStackTrace();
                                  }
                                }
                            }
                        }).start();
                    }
                });
                Mesaj.show();
            }
        });

    }
}