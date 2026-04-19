package com.example.labthreadsasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusLabel;
    private ProgressBar taskProgress;
    private ImageView displayImage;
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusLabel = findViewById(R.id.txtStatus);
        taskProgress = findViewById(R.id.progressBar);
        displayImage = findViewById(R.id.img);

        Button loadBtn = findViewById(R.id.btnLoadThread);
        Button calcBtn = findViewById(R.id.btnCalcAsync);
        Button toastBtn = findViewById(R.id.btnToast);

        // B) Créer le Handler qui poste sur le UI thread
        uiHandler = new Handler(Looper.getMainLooper());

        // C) Bouton Toast : doit toujours répondre immédiatement
        toastBtn.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "UI réactive", Toast.LENGTH_SHORT).show()
        );

        loadBtn.setOnClickListener(v -> processImageInThread());

        calcBtn.setOnClickListener(v -> new BackgroundCalculation().execute());
    }

    private void processImageInThread() {
        taskProgress.setVisibility(View.VISIBLE);
        taskProgress.setProgress(0);
        statusLabel.setText("Statut : chargement image (Thread)...");

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic);

            uiHandler.post(() -> {
                displayImage.setImageBitmap(icon);
                taskProgress.setVisibility(View.INVISIBLE);
                statusLabel.setText("Statut : image chargée (Thread)");
            });
        }).start();
    }

    private class BackgroundCalculation extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            taskProgress.setVisibility(View.VISIBLE);
            taskProgress.setProgress(0);
            statusLabel.setText("Statut : calcul lourd (AsyncTask)...");
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long result = 0;
            for (int i = 1; i <= 100; i++) {
                for (int j = 0; j < 200000; j++) {
                    result += (i * j) % 7;
                }
                publishProgress(i);
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... steps) {
            taskProgress.setProgress(steps[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            taskProgress.setVisibility(View.INVISIBLE);
            statusLabel.setText("Statut : calcul terminé résultat = " + result);
        }
    }
}
