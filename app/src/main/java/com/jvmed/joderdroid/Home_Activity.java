package com.jvmed.joderdroid;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity {

    //Final variables
    private static final int MINIMUM_POSIBLE_VALUE = 5;
    private static final int MAXIMUM_POSIBLE_VALUE = 9999;
    private static final int TWEET_VALUE = 140;

    //Components
    private EditText edtJODER;
    private EditText edtMin;
    private EditText edtMax;
    private Button btnGenerate;
    private RelativeLayout parentLayout;

    //Variables
    private int minimumValue; //Minimum value for the JODER
    private int maximumValue; //Maximum value for the JODER

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

    /**
     * Method that sends a SnackBar with a message
     * @param message Message to show
     */
    private void sendSnack(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Method that sends a Toast with a message
     * @param message Message to show
     */
    private void sendToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void getButtonClick(View view) {
        btnGenerate.setEnabled(false);

        switch (view.getId()) {
            case R.id.btnGenerate:
                /*
                If one of the EditTexts are empty, the minimum length for the JODER are 5 characters
                 */
                if (edtMin.getText().toString().length() == 0) {
                    edtMin.setText(getString(R.string.minimumValue));
                }
                else if (edtMax.getText().toString().length() == 0) {
                    edtMax.setText(edtMin.getText().toString());
                }

                /*
                If they're not empty, you just take that value and convert it to int
                 */
                minimumValue = Integer.parseInt(edtMin.getText().toString());
                maximumValue = Integer.parseInt(edtMax.getText().toString());

                /*
                Rules to make a JODER

                1. 5 or more characters
                2. 9999 or less characters
                3. You cant enter a minimum value higher than the maximum value (obvious)
                 */
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

            /*
            If the JODER EditText is not empty, it copies it to the clipboard
             */
            case R.id.btnClipboard:
                if (TextUtils.equals(edtJODER.getText().toString(), "")) {
                    sendSnack(getString(R.string.JODERCopyEmpty));
                }
                else {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("jdr", edtJODER.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    sendSnack(getString(R.string.JODERCopied));
                }
                break;

            /*
            If the JODER EditText is not empty, it sahres it with another App
             */
            case R.id.btnShare:
                if (TextUtils.equals(edtJODER.getText().toString(), "")) {
                    sendSnack(getString(R.string.JODERShareEmpty));
                }
                else {
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.putExtra(Intent.EXTRA_TEXT, edtJODER.getText().toString());
                    share.setType("text/plain");
                    startActivity(share);
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
        edtMin.setText(String.valueOf(TWEET_VALUE));
        edtMax.setText(String.valueOf(TWEET_VALUE));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(getResources().getString(R.string.actionSubtitle));
        }

        edtMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //You have to make sure the user dont enter a number higher than 9999
                if (s.length() > 4) {
                    edtMax.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                    sendToast(getString(R.string.maxJODERChar));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        edtMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //You have to make sure the user dont enter a number higher than 9999
                if (s.length() > 4) {
                    edtMin.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                    sendToast(getString(R.string.maxJODERChar));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
    }
}
