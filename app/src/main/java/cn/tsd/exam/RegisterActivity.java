package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText mTvName;
    private EditText mTvStudentNumber;
    private EditText mTvPassword;
    private EditText mTvConfirmPassword;
    private EditText mPhone;
    private Button mBtnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        String name = mTvName.getText().toString().trim();
        String studentNumber = mTvStudentNumber.getText().toString().trim();
        String password = mTvPassword.getText().toString().trim();
        String confirmPassword = mTvConfirmPassword.getText().toString().trim();
        String phone = mBtnRegister.getText().toString().trim();

    }

    private void init() {
        mTvName = (EditText) findViewById(R.id.tv_name);
        mTvStudentNumber = (EditText) findViewById(R.id.tv_studentNumber);
        mTvPassword = (EditText) findViewById(R.id.tv_password);
        mTvConfirmPassword = (EditText) findViewById(R.id.tv_confirmPassword);
        mPhone = (EditText) findViewById(R.id.phone);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
    }
}