package com.example.popup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private EditText edtInput;
    private Button btnNext;
    private CustomDialogActivity customDialogActivity;
    private  Boolean Flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationCheck();
                if (Flag) {

                    Toast.makeText(HomeActivity.this, "Please Enter Input",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    customDialogActivity = new CustomDialogActivity(HomeActivity.this,
                            edtInput.getText().toString());
                    customDialogActivity.setUpOnNextListener(new MyDoneActionListener());
                    customDialogActivity.show();
                }
            }
        });
    }

    private void initUI() {
        btnNext = findViewById(R.id.btnNextOpenDialogBox);
        edtInput = findViewById(R.id.edtInputText);
    }

    public class MyDoneActionListener implements CustomDialogActivity.OnNextListener {


        @Override
        public void onNextListener(CustomDialogActivity customDialogActivity, String outputReceiveed) {
        edtInput.setText(outputReceiveed);

        customDialogActivity.dismiss();
        }
    }
    //function to check the validations if the edittext is empty
    public void validationCheck(){
        if (edtInput.getText().toString().isEmpty()){

            Flag=true;
        }
        else
        {
            Flag=false;
        }

    }
}

//Custom dialog implemented
//input and output sharing from dialog to activity
//check validation such as input stream must not be empty.