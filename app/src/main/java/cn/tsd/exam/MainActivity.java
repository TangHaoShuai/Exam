package cn.tsd.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.tsd.exam.base.User;
import cn.tsd.exam.utils.NetWorkUti;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_Login, btn_Register;
    private IUiListener listener;
    private static String url = "http://192.168.31.91:8080/user/UserList";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Gson gson = new Gson();
            switch (msg.what) {
                case 1:
                    List<User> users = gson.fromJson(msg.obj.toString(), new TypeToken<List<User>>(){}.getType());
                    System.out.println(users);
                    break;
                case 2:
                    System.out.println(2);
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initClick();
        NetWorkUti.get(url,handler,1,2);
    }

    private void initClick() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_login:
                intent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_register:
                 intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }



}