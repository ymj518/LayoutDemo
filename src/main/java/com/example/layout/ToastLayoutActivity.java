package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastLayoutActivity extends AppCompatActivity
implements View.OnClickListener{
//1.定义控件变量名称
    private TextView tvUsername;
    private  EditText etRealname;
    private RadioGroup sexGroup;
    private CheckBox cbJava,cbAndroid,cbDatabase;

    private RadioButton rbMale,rbFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_layout);

        //初始化界面控件
        //1.获取控件对象
        tvUsername = findViewById(R.id.tv_username);
        etRealname = findViewById(R.id.et_realname);
        cbJava = findViewById(R.id.cb_java);
        cbAndroid = findViewById(R.id.cb_android);
        cbDatabase = findViewById(R.id.cb_database);
        sexGroup = findViewById(R.id.group_sex);
        rbMale = findViewById(R.id.rbtn_male);
        rbFemale = findViewById(R.id.rbtn_female);
        //获取登录界面传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("username");
            tvUsername.setText(name);
        }

        //2.设置点击事件，键盘事件的监听器
        Button btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);
        etRealname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int KeyCode, KeyEvent event) {
                //键盘事件处理
                if (KeyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }
        @Override
                public void onClick(View v){
            if (v.getId() == R.id.btn_confirm){
                getInfo();
            }
        }
        private void getInfo(){
            //1.获取控件的值
        String username = tvUsername.getText().toString().trim();
        String realname = etRealname.getText().toString().trim();
        String sex = "男";
        String favorite = "";
        //获取选择的RadioButton的id
        int id = sexGroup.getCheckedRadioButtonId();
        if(id == R.id.rbtn_male){
            sex = "男";
        }else {
            sex = "女";
        }
        if (cbJava.isChecked()){
            favorite += cbJava.getText().toString() + ", ";
        }
        if (cbAndroid.isChecked()){
            favorite += cbAndroid.getText().toString() + ", ";
        }
        if (cbDatabase.isChecked()){
            favorite += cbDatabase.getText().toString() + ", ";
        }
        //2.显示或传递内容
            String info = "用户名:" + username + "\n姓名："
                    + realname + "\n性别："
                    + sex + "\n喜欢的课程："
                    + favorite.trim().substring(0,favorite.trim().length() -1);
            Toast.makeText(ToastLayoutActivity.this,info,Toast.LENGTH_LONG).show();

            //转跳到主页面
            Intent intent = new Intent(ToastLayoutActivity.this, TabBarActivity.class);
            startActivity(intent);
        }


}

