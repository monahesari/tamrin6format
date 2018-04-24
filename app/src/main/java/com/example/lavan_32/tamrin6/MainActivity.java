package com.example.lavan_32.tamrin6;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.DataInput;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private TextView hours, minute;
    private Button button1;
    private RadioButton btn1;
    private RadioButton btn2;
    private int[] voices = {R.raw.recordingsaat, R.raw.recording2o, R.raw.recording50, R.raw.recordingdaghighe, 0};
    private int index = 0;

    int[] voices1 = {
            0,
            R.raw.recording1, R.raw.recording2, R.raw.recording3, R.raw.recording4,
            R.raw.recording5, R.raw.recording6, R.raw.recording7, R.raw.recording8,
            R.raw.recording9, R.raw.recording10, R.raw.recording11, R.raw.recording12,
            R.raw.recording13, R.raw.recording14, R.raw.recording15, R.raw.recording16,
            R.raw.recording17, R.raw.recording18, R.raw.recording19, R.raw.recording20,

    };

    int[] voices1o = {
            0,
            R.raw.recording1o, R.raw.recording2o, R.raw.recording3o, R.raw.recording4o,
            R.raw.recording5o, R.raw.recording6o, R.raw.recording7o, R.raw.recording8o,
            R.raw.recording9o, R.raw.recording10o, R.raw.recording11o, R.raw.recording12o,
            R.raw.recording13o, R.raw.recording14o, R.raw.recording15o, R.raw.recording16o,
            R.raw.recording17o, R.raw.recording18o, R.raw.recording19o, R.raw.recording20o,

    };

    int[] voices10o = {
            0,
            R.raw.recording10o, R.raw.recording20o, R.raw.recording30o, R.raw.recording40o,
            R.raw.recording50o
    };
    int[] voices10 = {
            0,
            R.raw.recording10, R.raw.recording20, R.raw.recording30, R.raw.recording40,
            R.raw.recording50
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hours = (TextView) findViewById(R.id.ho);
        minute = (TextView) findViewById(R.id.minute);
        button1 = (Button) findViewById(R.id.b1);
        btn1=(RadioButton)findViewById(R.id.rd1);
        btn2=(RadioButton)findViewById(R.id.rd2);


        Typeface t1 = Typeface.createFromAsset(getAssets(), "fonts/segment7.otf");
        hours.setTypeface(t1);
        minute.setTypeface(t1);
        int n;
       final SharedPreferences sp =getSharedPreferences("my",MODE_PRIVATE);
        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Date d = new Date();
                        int h = d.getHours();
                        int m = d.getMinutes();
                        String hss = String.format("%2d", h);
                        String hmm = String.format("%2d", m);
                        hours.setText(hss);
                        minute.setText(hmm);

                        if (btn1.isChecked()) {


                            if ((h == 0))
                                h = 12;

                            else if (h > 12)
                                h = h - 12;

                            if (btn2.isChecked()){

                                if ((h == 0))
                                    h = 24;

                                else if (h < 24)

                                    h = h - 1;
                            }


                        }





                int i = 0;
                voices[i++] = m == 0 ? voices1[h] : voices1o[h];
                if (m < 20)
                    voices[i++] = voices1[m];
                else {
                    int m10 = m / 10;
                    int m1 = m % 10;
                    voices[i++] = m1 == 0 ? voices10[m10] : voices10o[m10];
                    if ((m1 != 0))
                        voices[i++] = voices1[m1];
                }


                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.recordingsaat);
                mp.setOnCompletionListener(MainActivity.this);
                mp.start();


            }
        });
        sp.edit().putInt("my",h).apply();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {


        if (voices[index] != 0) {
            MediaPlayer mp2 = MediaPlayer.create(this, voices[index]);
            index++;
            mp2.setOnCompletionListener(this);
            mp2.start();

        }
    }


/*





























 */