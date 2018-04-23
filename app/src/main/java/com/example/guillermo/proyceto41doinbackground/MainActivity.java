package com.example.guillermo.proyceto41doinbackground;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnprogres;
    EditText edinum;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edinum = (EditText) findViewById(R.id.editTextNum);
        progress=(ProgressBar) findViewById(R.id.progressBar);
        btnprogres=(Button) findViewById(R.id.buttonP);

        btnprogres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTarea asyncTarea = new AsyncTarea();
                asyncTarea.execute();
            }
        });
    }

    private void UnSegundo() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class  AsyncTarea extends AsyncTask<Void, Integer,Boolean> {

        int progreso=Integer.parseInt(edinum.getText().toString());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setMax(progreso);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (int i = 1; i < progreso; i++) {
                UnSegundo();
                publishProgress(i);

                if (isCancelled()) {
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Actualizar la barra de progreso
            progress.setProgress(values[0].intValue());
            Toast.makeText(getApplicationContext(),"Progreso: "+values[0].intValue(),Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);

            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask",Toast.LENGTH_SHORT).show();

        }
    }

}
