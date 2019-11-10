package io.github.afifaniks.heimerx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoyStick joyStick = new JoyStick(this);
        setContentView(R.layout.activity_main);
    }
}
