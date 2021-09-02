package com.example.pomodoro;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    Button b;
    TextView tvs, tvm, tv3, tv5;
    ConstraintLayout cl;
    boolean status=false;
    String whatsup="";
    long count=0;
    int howmany=0;
    CountDownTimer cds;
    int one=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText2);
        et2 = findViewById(R.id.editText3);
        b = findViewById(R.id.button);
        tvs = findViewById(R.id.textView5);
        tvm = findViewById(R.id.textView6);
        cl = findViewById(R.id.cst);
        cl.setBackgroundColor(Color.BLACK);
        et3=findViewById(R.id.editText5);
        tvs.setText("00");
        tvm.setText("00");

        tv3=findViewById(R.id.textView8);
        tv5=findViewById(R.id.textView);
        tv3.setText(":");
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status==true) {
                    if (one == 1) {
                        one = one * -1;
                        b.setVisibility(View.INVISIBLE);
                        tvm.setVisibility(View.INVISIBLE);
                        tvs.setVisibility(View.INVISIBLE);
                        tv3.setVisibility(View.INVISIBLE);
                        tv5.setVisibility(View.INVISIBLE);
                        View decorView = getWindow().getDecorView();

                        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
                        decorView.setSystemUiVisibility(uiOptions);

                    } else {
                        one=one*-1;
                        b.setVisibility(View.VISIBLE);
                        tvm.setVisibility(View.VISIBLE);
                        tvs.setVisibility(View.VISIBLE);
                        tv3.setVisibility(View.VISIBLE);
                        tv5.setVisibility(View.VISIBLE);
                    }
                }
            }
        });



    }

    public void me(View v) {
        if(status==false) {

            long secondb, secondw;
            String s1,s2, s3;
            s1=et1.getText().toString();
            s2=et2.getText().toString();
            s3=et3.getText().toString();
            if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty())
            {
                Toast.makeText(this, "Please Enter valid values", Toast.LENGTH_LONG).show();
                return;
            }


            secondw = Long.parseLong(s1) * 60;
            secondb = Long.parseLong(s2) * 60;
            count = Long.parseLong(s3)*2;
            if(secondb<=0||secondw<=0||count<=0)
            {
                Toast.makeText(this, "Please Enter valid values", Toast.LENGTH_LONG).show();
                return;
            }
            whatsup="Immerse";
            tv5.setText(whatsup);
            tv3.setText(":");
            status = true;
            b.setText("Finish");
            cl.removeView(et1);
            cl.removeView(et2);
            cl.removeView(et3);


             startCountdownwk(secondb, secondw);
        }

        else
    {
        howmany=0;
        status=false;
        b.setText("Start");
       tv5.setText("");
        cds.cancel();
        tvm.setText("00");
        tvs.setText("00");
        tv3.setText(":");
        cl.addView(et1);
        cl.addView(et2);
        cl.addView(et3);
        et1.setText("");
        et2.setText("");
        et3.setText("");


    }







}

    public void startCountdownwk(final long secondb, final long secondw) {

            cds=new CountDownTimer(secondw*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long secleft=millisUntilFinished/1000;
                    String secl=""+secleft%60;
                    String minl=""+secleft/60;
                    if(secl.length()<=1)
                    {
                        secl=""+0+secl;
                    }
                    if(minl.length()<=1)
                    {
                        minl=""+0+minl;
                    }
                    tvs.setText(secl);
                    tvm.setText(minl);

                }

                @Override
                public void onFinish() {
                    if(howmany++<=count)
                    {
                        if(whatsup.compareTo("Immerse")==0)
                        {
                            whatsup="Relax";
                        }
                        else
                        {
                            whatsup="Immerse";
                        }
                       tv5.setText(whatsup);
                        startCountdownwk(secondw, secondb);}
                    else
                    {

                        tv5.setText("");
                        howmany=0;
                        status=false;
                        b.setText("Start");
                        tvm.setText("00");
                        tvs.setText("00");
                        tv3.setText(":");
                        cl.addView(et1);
                        cl.addView(et2);
                        cl.addView(et3);
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                    }
                }
            }.start();
    }


}
