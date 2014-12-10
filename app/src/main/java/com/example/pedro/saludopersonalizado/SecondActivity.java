package com.example.pedro.saludopersonalizado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /*
        RECIBIMOS INFORMACION CON UN SIMPLE INTENT
        */
        String salutation = getIntent().getExtras().getString("saludo");
        TextView out = (TextView) findViewById(R.id.saludo);
        out.setText(salutation);

        /*
        RECIBIMOS INFORMACION SERIALIZADA


        MiClase recogida = (MiClase)getIntent().getSerializableExtra("id1");
            String nombre = recogida.nombre;
            int edad = recogida.edad;
        */

        Button button = (Button)findViewById(R.id.botonHola);
        button.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("respuesta", "Hola");
                //Intent i2 = new Intent();
                //i2.putExtra("respuesta","Otra toast");
                setResult(RESULT_OK,i);
                //setResult(RESULT_CANCELED,i2);
                finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
