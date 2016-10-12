package com.jvmed.joderdroid;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Home_Activity extends AppCompatActivity {

    private static final int MINIMUM_POSIBLE_VALUE = 5;
    private static final int MAXIMUM_POSIBLE_VALUE = 9999;

    private EditText edtJODER;
    private EditText edtMin;
    private EditText edtMax;
    private Button btnGenerate;
    private RelativeLayout parentLayout;
    private int minimumValue;
    private int maximumValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info) {
            startActivity(new Intent(Home_Activity.this, Info_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendSnack(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
    }

    public void getButtonClick(View view) {
        btnGenerate.setEnabled(false);

        switch (view.getId()) {
            case R.id.btnGenerate:
                if (edtMin.getText().toString().length() == 0) {
                    edtMin.setText(getString(R.string.minimumValue));
                }
                else if (edtMax.getText().toString().length() == 0) {
                    edtMax.setText(edtMin.getText().toString());
                }

                minimumValue = Integer.parseInt(edtMin.getText().toString());
                maximumValue = Integer.parseInt(edtMax.getText().toString());

                if (minimumValue < MINIMUM_POSIBLE_VALUE) {
                    sendSnack(getString(R.string.minJODERChar));
                }
                else if (maximumValue > MAXIMUM_POSIBLE_VALUE) {
                    sendSnack(getString(R.string.maxJODERChar));
                }
                else if (minimumValue > maximumValue) {
                    sendSnack(getString(R.string.minHigherMax));
                }
                else {
                    edtJODER.setText(JODER.Generate(minimumValue, maximumValue));
                }
                break;

            case R.id.btnClipboard:
                if (TextUtils.equals(edtJODER.getText().toString(), "")) {
                    sendSnack(getString(R.string.JODEREmpty));
                }
                else {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("jdr", edtJODER.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    sendSnack(getString(R.string.JODERCopied));
                }
                break;
        }

        btnGenerate.setEnabled(true);
    }

    private void initialize() {
        minimumValue = 0;
        maximumValue = 0;
        parentLayout = (RelativeLayout)findViewById(R.id.parentLayout);
        edtJODER = (EditText)findViewById(R.id.edtJODER);
        edtMin = (EditText)findViewById(R.id.edtMin);
        edtMax = (EditText)findViewById(R.id.edtMax);
        btnGenerate = (Button)findViewById(R.id.btnGenerate);
        edtMin.setText(getString(R.string.minimumValue));
        edtMax.setText(getString(R.string.maximumValue));
        edtJODER.setText(getString(R.string.JODERDefault));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(getResources().getString(R.string.actionSubtitle));
        }
    }
}
