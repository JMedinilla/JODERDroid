package com.jvmed.joderdroid;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int valorMinimo;
    int valorMaximo;

    LinearLayout layUno;
    LinearLayout layDos;
    LinearLayout layTres;
    LinearLayout layCuatro;
    Button btnGenerar;
    Button btnPortapapeles;
    EditText tbxJoder;
    EditText tbxMenor;
    EditText tbxMayor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorMinimo = 0;
        valorMaximo = 0;

        inicializarControles();

        establecerProporciones();

        suscripcionesTBX();

        suscripcionesBTN();
    }

    private void inicializarControles() {
        layUno = (LinearLayout)findViewById(R.id.layUno);
        layDos = (LinearLayout)findViewById(R.id.layDos);
        layTres = (LinearLayout)findViewById(R.id.layTres);
        layCuatro = (LinearLayout)findViewById(R.id.layCuatro);
        btnGenerar = (Button)findViewById(R.id.btnGenerar);
        btnPortapapeles = (Button)findViewById(R.id.btnPortapapeles);
        tbxJoder = (EditText)findViewById(R.id.tbxJoder);
        tbxMenor = (EditText)findViewById(R.id.tbxMenor);
        tbxMayor = (EditText)findViewById(R.id.tbxMayor);

        tbxJoder.setLines(2);
        tbxJoder.setMaxLines(8);
        tbxMenor.setText("5");
        tbxMayor.setText("140");
    }

    private void establecerProporciones() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        tbxJoder.setWidth((dm.widthPixels * 74) / 100);
        tbxJoder.setHeight((dm.widthPixels * 60) / 100);
        layUno.setPadding(0, (dm.heightPixels * 4) / 100, 0, 0);
        layDos.setPadding(0, (dm.heightPixels * 4) / 100, 0, 0);
        layTres.setPadding(0, (dm.heightPixels * 6) / 100, 0, 0);
        layCuatro.setPadding(0, (dm.heightPixels * 2) / 100, 0, 0);
    }

    private void suscripcionesTBX() {
        tbxMenor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tbxMenor.setText("5");
                } else if (Integer.parseInt(String.valueOf(s)) < 5) {
                    tbxMenor.setText("5");
                } else if (Integer.parseInt(String.valueOf(s)) > 200) {
                    tbxMenor.setText("200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
        tbxMayor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tbxMayor.setText("5");
                } else if (Integer.parseInt(String.valueOf(s)) < 5) {
                    tbxMayor.setText("5");
                } else if (Integer.parseInt(String.valueOf(s)) > 200) {
                    tbxMayor.setText("200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
    }

    private void suscripcionesBTN() {
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorMinimo = Integer.parseInt(tbxMenor.getText().toString());
                valorMaximo = Integer.parseInt(tbxMayor.getText().toString());

                if (valorMinimo > valorMaximo) {
                    Toast.makeText(getApplicationContext(), "El número de caracteres mínimos es mayor que el segundo valor", Toast.LENGTH_LONG).show();
                } else {
                    tbxJoder.setText(JODER.Generate(valorMinimo, valorMaximo));
                }
            }
        });
        btnPortapapeles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(tbxJoder.getText().toString(), "")) {
                        Toast.makeText(getApplicationContext(), "No se ha generado ningún JODER©", Toast.LENGTH_SHORT).show();
                    } else {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("jdr", tbxJoder.getText().toString());
                        clipboard.setPrimaryClip(clip);

                        Toast.makeText(getApplicationContext(), "Se ha copiado el JODER© al portapapeles", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
