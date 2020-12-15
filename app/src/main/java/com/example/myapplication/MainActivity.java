package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn;
    private TextInputLayout textInputCant;
    TextView moneda, txtRes, txtTitu1,txtTitu2,txtTitu3 ;
    Spinner sp1, sp2, sp3;
    Switch switchtCambio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        txtTitu1 = findViewById(R.id.txt1);
        txtTitu2 = findViewById(R.id.txt2);
        txtTitu3 = findViewById(R.id.txt3);
        btn = findViewById(R.id.btnCalcular);
        txtRes = findViewById(R.id.txtResult);
        textInputCant = findViewById(R.id.txt_input_cant);
        sp1 = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>  adapter = ArrayAdapter.createFromResource(this,R.array.material, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(this);

        sp2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence>  adapter2 = ArrayAdapter.createFromResource(this,R.array.dije, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);

        sp3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence>  adapter3 = ArrayAdapter.createFromResource(this,R.array.tipo, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);
        sp3.setOnItemSelectedListener(this);

        switchtCambio = findViewById(R.id.switch1);
        switchtCambio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if(isChecked){
                      switchtCambio.setText("Dolar");
                  }else{
                      switchtCambio.setText("Peso");

                  }

            }
        });
    }

    private boolean validateCantidad(){
        if(textInputCant.getEditText().getText().toString().trim().length()==0){
            textInputCant.setError(getResources().getString(R.string.errVacio));
            return false;
        }else{
            textInputCant.setError(null);
        }

        Integer cant = Integer.parseInt(textInputCant.getEditText().getText().toString());
        Log.e("a", String.valueOf(cant));
        if(cant<=0 ){
            textInputCant.setError(getResources().getString(R.string.errMayorCero));
            return false;
        }else{
            textInputCant.setError(null);
            return true;
        }
    }

    private boolean validateSelct(){
        Integer position = (Integer) Integer.parseInt(String.valueOf(sp1.getSelectedItemId()));
        Integer position2 =(Integer) Integer.parseInt(String.valueOf(sp2.getSelectedItemId()));
        Integer position3 = (Integer)Integer.parseInt(String.valueOf(sp3.getSelectedItemId()));

        if(position==0){
            txtTitu1.setError(getResources().getString(R.string.errSelOpcion));
            return false;
        }else{
            txtTitu1.setError(null);
        }
        if(position2==0){
            txtTitu2.setError(getResources().getString(R.string.errSelOpcion));
            return false;
        }else{
            txtTitu2.setError(null);
        }
        if(position3==0){
            txtTitu3.setError(getResources().getString(R.string.errSelOpcion));
            return false;
        }else{
            txtTitu3.setError(null);
        }
        return true;
    }

    public void calcular(View v) {
        if(!validateCantidad()){
            return ;

        }

        if(!validateSelct()){
            return ;
        }

        Integer valueSpinner1 = Integer.parseInt(String.valueOf(sp1.getSelectedItemId()));
        Integer valueSpinner2 = Integer.parseInt(String.valueOf(sp2.getSelectedItemId()));
        Integer valueSpinner3 = Integer.parseInt(String.valueOf(sp3.getSelectedItemId()));
        Double cantidad = Double.parseDouble(String.valueOf(textInputCant.getEditText().getText()));

        Double tasaCambio ;
        String simbol;
        if(switchtCambio.isChecked()){
            tasaCambio= 1/3200.0;
            simbol="USD";
        }else{
            tasaCambio = 1.0;
            simbol="PESOS";
        }

        Double precUnit = 0.0;
        //cond1
        if(valueSpinner1==1 && valueSpinner2==1 && (valueSpinner3==1 || valueSpinner3==3)){
            precUnit=100.0;
        }

        //cond2
        if(valueSpinner1==1 && valueSpinner2==1 &&  valueSpinner3==4 ){
            precUnit=80.0;
        }

        //cond3
        if(valueSpinner1==1 && valueSpinner2==1 && valueSpinner3==5){
            precUnit=70.0;
        }

        //cond4
        if(valueSpinner1==1 && valueSpinner2==2 && (valueSpinner3==1 || valueSpinner3==3)){
            precUnit=120.0;
        }

        //cond5
        if(valueSpinner1==1 && valueSpinner2==2 && valueSpinner3==4){
            precUnit=100.0;
        }


        //cond6
        if(valueSpinner1==1 && valueSpinner2==2 && valueSpinner3==5){
            precUnit=90.0;
        }


        //cond7
        if(valueSpinner1==2 && valueSpinner2==1 && (valueSpinner3==1 || valueSpinner3==3)){
            precUnit=90.0;
        }



        //cond8
        if(valueSpinner1==2 && valueSpinner2==1 && valueSpinner3==4){
            precUnit=70.0;
        }


        //cond9
        if(valueSpinner1==2 && valueSpinner2==1 && valueSpinner3==5){
            precUnit=50.0;
        }


        //cond10
        if(valueSpinner1==2 && valueSpinner2==2 && (valueSpinner3==1 || valueSpinner3==3)){
            precUnit=110.0;
        }

        //cond11
        if(valueSpinner1==2 && valueSpinner2==2 && valueSpinner3==4){
            precUnit=90.0;
        }

        //cond12
        if(valueSpinner1==2 && valueSpinner2==2 && valueSpinner3==5){
            precUnit=80.0;
        }

        Double valor = cantidad*precUnit*tasaCambio;

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        String currency = format.format(valor);

        if(Locale.getDefault().getLanguage()=="es"){
            txtRes.setText("La(s) "+ cantidad+ " Manillas Tiene un Valor de "+currency+" "+simbol);
        }
        if(Locale.getDefault().getLanguage()=="en"){
            txtRes.setText("The "+cantidad+" bracelets are priced at "+currency+" "+simbol);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String text = parent.getItemAtPosition(position).toString();
       Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}