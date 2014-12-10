package com.example.pedro.saludopersonalizado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {


    //BLOQUE 1.
    //Comentario para hacer un commit del proyecto inicial para el examen de PMDM
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos el boton
        Button button = (Button)findViewById(R.id.hello);
        button.setOnClickListener(new View.OnClickListener () {

            //ATENCION todo esto esta dentro del setOnClickListener
            @Override
            public void onClick(View v) { //Esta v es el boton,si ponermos Button v se puede utilizar en vez de tener que llamar a button

                // código a ejecutar cuando sea pulsado el boton
                EditText text = (EditText)findViewById(R.id.entry);

                // comprobar si existe nombre en la caja de texto y si no muestra la toast
                if ("".equals(text.getText().toString().trim())){

                    //mostrar toast si es necesario
                    showToast();
                    return;
                }
                String salutation = null;
                String saludo = null;
                String enteredName = text.getText().toString();

                // referencia al radioButton sobre el sexo (Sr. o Sra.
                //Instancio y defino el RadioButton
                RadioGroup radio = (RadioGroup)findViewById(R.id.RadioGroup01);
                if (R.id.rdsr == radio.getCheckedRadioButtonId()){
                    //para señor
                    salutation = getResources().getString(R.string.saludoSr).toLowerCase();
                }else{
                    salutation = getResources().getString(R.string.saludoSra).toLowerCase();
                }
                //salutation = getResources().getString(R.string.hello) + " " + salutation + " " + enteredName;

                //Creamos este RadioGroup para elegir entre Hola y Adios
                RadioGroup radioHA = (RadioGroup)findViewById(R.id.RadioGroup02);
                if (R.id.rdHola == radioHA.getCheckedRadioButtonId()){
                    //para señor
                    saludo = getResources().getString(R.string.EHola).toLowerCase();
                }else{
                    saludo = getResources().getString(R.string.EAdios).toLowerCase();
                }
                salutation = saludo + " " + salutation + " " + enteredName;

                //Instancio el ChekBox de la hora y la fecha
                // obtención de la hora y fecha
                CheckBox timeCheckBox = (CheckBox)findViewById(R.id.checkBox);

                if (timeCheckBox.isChecked()){
                    DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                    String dateToShow = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    dateToShow += " " + time.getCurrentHour() + ":" + time.getCurrentMinute();
                    salutation += " " + dateToShow;
                }

                //Localizamos los controles
                TextView txtSaludo = (TextView)findViewById(R.id.out);

                //Construimos el mensaje a mostrar
                //txtSaludo.setText(salutation);
                /*
                    FORMA SIMPLE Y NORMAL DE ENVIAR DATOS DE UNA ACTIVITY A LA OTRA
                */
                Intent intento = new Intent(MainActivity.this, SecondActivity.class);
                intento.putExtra("saludo", salutation);
                startActivityForResult(intento,1);




                /*
                    ENVIAMOS COSAS DE FORMA SERIALIZABLE

                Intent intento = new Intent(MainActivity.this, SecondActivity.class);
                MiClase persona1 = new MiClase("Jose",23);
                intento.putExtra("id1",persona1);
                */
            }
        });

        // Defino el onCheckChangedListener para mostrar las fechas
        // Mostrar o no las fechas
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //Esto es lo mismo que el OnClick pero cuando el CheckBox se active
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                View view = findViewById(R.id.timePicker);
                view.setVisibility(visibility);
                view = findViewById(R.id.datePicker);
                view.setVisibility(visibility);
            }
        });

    }


    /*
    Esto recibe el setResult(result_ok, i) de la SecondActivity

    El requestCode es el 1 de startActivityForResult(intento,1);
    El resultCode es el REsULT_OK de la segunda clase
    El Intent data son los datos que le enviamos desde la segunda clase.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        String miRespuesta = data.getStringExtra("respuesta");
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,miRespuesta, duration);
        toast.show();

    }

    //esto muestra un mensaje en una pestañita que se desvanece sola
    protected void showToast() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.noNameMsg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}