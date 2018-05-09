package com.example.abc.bmi_5_2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class    Bmi extends AppCompatActivity implements View.OnClickListener {

    private EditText field_height;
    private EditText field_weight;
    private Button submit;
    private TextView result;
    private TextView suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_button_state);
        //findViews();
        //setListeners();
    }

    private void findViews(){
        submit = findViewById(R.id.submit);
        field_height = findViewById(R.id.field_height);
        field_weight = findViewById(R.id.field_weight);
        result = findViewById(R.id.result);
        suggest = findViewById(R.id.suggest);
    }

    private void setListeners(){
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DecimalFormat df = new DecimalFormat("0.00");

        double height = Double.parseDouble(field_height.getText().toString()) / 100;
        double weight = Double.parseDouble(field_weight.getText().toString());
        double BMI = weight / (height * height);

        result.setText("你的BMI值 = " + df.format(BMI));

        if (BMI > 25)
            suggest.setText(R.string.advice_heavy);
        else if (BMI < 20)
            suggest.setText(R.string.advice_light);
        else
            suggest.setText(R.string.advice_average);

        openOptionsDialog();
    }

    void openOptionsDialog(){
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Android BMI");
        //builder.setMessage("Android BMI Calculator");
        //builder.setPositiveButton("確認",dialogListener);
        //builder.show();
     final ProgressDialog progressDialog=new ProgressDialog(Bmi.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.show();
     //ProgressDialog.show(Bmi.this,"處理中...","請等一下，處理完畢會自動關閉");
        Thread thread = new Thread(){
            @Override
         public void run(){
             try{

                 for(int i=0;i<=100;i++){
                     progressDialog.setProgress(i);
                 Thread.sleep(10);}
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             progressDialog.dismiss();
         }
     };
     thread.start();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            System.out.println("按下了確認鈕");
        }
    };
}