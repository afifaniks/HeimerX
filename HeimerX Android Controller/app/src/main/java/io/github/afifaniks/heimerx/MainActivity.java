package io.github.afifaniks.heimerx;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private final String UUID_STRING = "00001101-0000-1000-8000-00805F9B34FB";
    public static BluetoothSocket mmSocket;
    public static BluetoothDevice mmDevice;
    public static InputStream mmInputStream;
    public static OutputStream mmOutputStream;

    private TextView deviceName;
    private ImageButton btnA;
    private ImageButton btnB;
    private ImageButton btnC;
    private ImageButton btnD;
    private ImageButton btnBTDevices;

    public static void setConnectedDevice (BluetoothDevice device, BluetoothSocket socket, InputStream inputStream, OutputStream outputStream) {
        mmDevice = device;
        mmSocket = socket;
        mmInputStream = inputStream;
        mmOutputStream = outputStream;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mmDevice != null) {
            deviceName.setText(mmDevice.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deviceName = findViewById(R.id.deviceName);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnBTDevices = findViewById(R.id.btDevies);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("a");
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("b");
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("c");
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("d");
            }
        });

        btnBTDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BluetoothDevicesActivity.class);
                startActivity(intent);
            }
        });

        if (mmDevice != null) {
            deviceName.setText(mmDevice.getName());
        }
    }

    public void send(String msg) {
        msg += "\n";
       if (mmDevice != null) {
            try {
                if (mmSocket.isConnected()) {
                    mmOutputStream.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MainActivity.this, "No connected device found!", Toast.LENGTH_SHORT).show();
        }
    }
}
