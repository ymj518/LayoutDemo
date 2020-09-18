
package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1.定义控件变量名称
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbAutoLogin;
    private Button btnLogin;

    /**
     * 1.将Activity类与xml布局进行关联：setContentView
     * 2.xml界面控件的初始化
     * 设置按钮等的事件监听
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.初始化控件对象
        etUsername = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        cbAutoLogin = findViewById(R.id.ck_zd);
        btnLogin = findViewById(R.id.btn_input);

        //3.设置按钮的监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        //3.1获取用户名和密码的值
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
        Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
        return;
    }

    //3.2.比较用户名和密码是否正确，然后给出提示
    if("android".equals(username) && "123456".equals(password)){
        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,ToastLayoutActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }else{
            Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
    }
    }
}