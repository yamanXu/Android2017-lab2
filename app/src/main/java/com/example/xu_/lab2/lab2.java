package com.example.xu_.lab2;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class lab2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
        initDialog();
        initEvent();
        initSnackBar();
    }
    private AlertDialog.Builder dialog;
    private RadioGroup radioGroup;
    private TextInputLayout mNumID, mNumPass;
    private EditText mNumText, mPassText;
    private Button login, register;

    //region dialog
    private void initDialog(){
        String[] items = {this.getString(R.string.dialogItem1), this.getString(R.string.dialogItem2)};
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.dialogTitle)
                .setNegativeButton(R.string.dialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makeToast(R.string.dialogButtonMsg);
                    }
                });
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0: makeToast(R.string.dialogItem1Msg);
                        break;
                    case 1: makeToast(R.string.dialogItem2Msg);
                        break;
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dialog.show();
            }
        });
    }
    //endregion

    //region radioGroup
    private void initSnackBar(){
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.radioStudent){
                    makeSnackbar(R.string.radioStudent);
                }
                else{
                    makeSnackbar(R.string.radioTeacher);
                }
            }
        });
    }
    //endregion

    private void initEvent(){
        //region 输入框
        mNumID = (TextInputLayout)findViewById(R.id.studentID);
        mNumPass = (TextInputLayout)findViewById(R.id.password);
        mNumText = mNumID.getEditText();
        mPassText = mNumPass.getEditText();

        mNumText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mNumID.setErrorEnabled(false);
            }
        });
        mPassText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mNumPass.setErrorEnabled(false);
            }
        });
        //endregion
        //region 登录
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mNumText.getText().toString();
                String password = mPassText.getText().toString();
                if(!TextUtils.isEmpty(id)){
                    mNumID.setErrorEnabled(false);
                }
                if(!TextUtils.isEmpty(password)){
                    mNumPass.setErrorEnabled(false);
                }
                if(TextUtils.isEmpty(id)){
                    mNumID.setErrorEnabled(true);
                    mNumID.setError(getString(R.string.emptyID));
                }
                else if(TextUtils.isEmpty(password)){
                    mNumPass.setErrorEnabled(true);
                    mNumPass.setError(getString(R.string.emptyPassword));
                }
                if(UserPasswordCheck(id, password)){
                    makeSnackbar(R.string.loginSuccessful);
                }
                else{
                    makeSnackbar(R.string.loginFailed);
                }
            }
        });
        //endregion
        //region 注册
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = 0;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioStudent: message = R.string.registerStudent;
                        break;
                    case R.id.radioTeacher: message = R.string.registerTeacher;
                        break;
                }
                makeSnackbar(message);
            }
        });
        //endregion
    }

    //region 自定义函数
    boolean UserPasswordCheck(String id, String password){
        if(id.equals("123456") & password.equals("6666")){
            return true;
        }
        return false;
    }
    private void makeToast(int id){
        Toast.makeText(lab2.this, id, Toast.LENGTH_SHORT).show();
    }
    private void makeSnackbar(int id){
        Snackbar.make(findViewById(R.id.radioGroup), id, Snackbar.LENGTH_SHORT)
                .setAction(R.string.snackbarButton, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeToast(R.string.snackbarSure);
                    }
                })
                .show();
    }
    //endregion
}
