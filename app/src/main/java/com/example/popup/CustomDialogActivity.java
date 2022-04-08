package com.example.popup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialogActivity extends Dialog {
    private RadioButton rbUpperCase, rbLowerCase, rbInItCap;
    private CheckBox cbReverse;
    private Context context;
    private Button btnDone;
    private String inputReceived;
    private TextView tvEditTexxt;
    private RadioGroup rgRadioButtons;

    public CustomDialogActivity(@NonNull Context context, String inputReceived) {
        super(context);
        this.context = context;
        setContentView(R.layout.activity_custom_dialog);
        this.inputReceived = inputReceived;


    }

    public interface OnNextListener {
        void onNextListener(CustomDialogActivity customDialogActivity,
                            String outputReceived);

    }

    private OnNextListener onNextListener = null;

    public void setUpOnNextListener(OnNextListener onNextListener) {
        this.onNextListener = onNextListener;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        inITUI();
        tvEditTexxt.setText(inputReceived);

        cbReverse.setOnClickListener(new CheckBoxActionClickListener());
        rbUpperCase.setOnClickListener(new RbUpperCaseClickedListener());
        rbLowerCase.setOnClickListener(new RbLowerCaseActionListener());
        rbInItCap.setOnClickListener(new RBInItCApListner());
        btnDone.setOnClickListener(new BtnDoneClickListener());

    }

    private void inITUI() {
        rbUpperCase = findViewById(R.id.rbUpperCAse);
        btnDone = findViewById(R.id.btnDone);
        cbReverse = findViewById(R.id.cbReverse);
        rbInItCap = findViewById(R.id.rbInitCap);
        rbLowerCase = findViewById(R.id.rbLowerCaes);
        rgRadioButtons = findViewById(R.id.rgRAdioGr);
        tvEditTexxt = findViewById(R.id.tvTitlleEditDialog);
    }

    private class BtnDoneClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onNextListener.onNextListener(CustomDialogActivity.this,
                    tvEditTexxt.getText().toString());
        }
    }

    private void mt(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    private class CheckBoxActionClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (cbReverse.isChecked()) {

                rgRadioButtons.setEnabled(false);

                String rev = "";
                char a[] = inputReceived.toCharArray();
                int len = a.length;
                for (int i = len - 1; i >= 0; i--) {
                    rev = rev + a[i];
                    tvEditTexxt.setText(rev);
                    mt("Your text has been reversed!");
                }


            }
        }
    }

    private class RbUpperCaseClickedListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (rbUpperCase.isChecked()) {
                //if (!cbReverse.isChecked() && rbUpperCase.isChecked()) {
                rbLowerCase.setChecked(false);

                rbUpperCase.setChecked(true);
                rbInItCap.setChecked(false);
                tvEditTexxt.setText(inputReceived.toUpperCase());
                mt("Your text converted to the Upper Case");
            }

            return;

        }
    }

    private class RbLowerCaseActionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (rbLowerCase.isChecked()) {
                rbUpperCase.setChecked(false);
                rbInItCap.setChecked(false);
                rbLowerCase.setChecked(true);

                tvEditTexxt.setText(inputReceived.toLowerCase());
                mt("Your text converted to the Lower Case");
            }

            return;


        }

    }


    private class RBInItCApListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (rbInItCap.isChecked()) {

                // cbReverse.setChecked(false);
                rbUpperCase.setChecked(false);
                rbInItCap.setChecked(true);
                rbLowerCase.setChecked(false);
                String InItCap = inputReceived.substring(0, 1).toUpperCase() +
                        inputReceived.substring(1).toLowerCase();
                tvEditTexxt.setText(InItCap);
                mt("Your text converted to the InItCap Case");
            }

            return;

        }
    }

}
