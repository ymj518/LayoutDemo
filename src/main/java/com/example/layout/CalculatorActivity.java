package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.googlecode.aviator.AviatorEvaluator;

public class CalculatorActivity extends AppCompatActivity {
    //1.定义界面上需要的控件对象
    private TextView tvInput;
    private TextView tvResult;

    //需要的变量定义
    private String inputstr;//存储输入的数字等内容的字符串
    private String express;//表示真实的表达式
    private  boolean flag; //清除TextView内容的标志,按了“=”之后清除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //2. 获取界面的控件对象
        tvInput = findViewById(R.id.tv_input);
        tvResult = findViewById(R.id.tv_result);

        flag = false;
        express = "";
        inputstr = "";
    }

    public void calculate(View view){
        Button  button = (Button) view;
        if (flag){
            inputstr = "";
            tvInput.setText(inputstr);
            tvResult.setText("0");
            flag = !flag;
        }
        switch(view.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dian:
            case R.id.btn_jia:
            case R.id.btn_jian:
                inputstr += button.getText().toString();
                express += button.getText().toString();
                break;
            case R.id.btn_chen:
                inputstr += button.getText().toString();
                express += "*";
                break;
            case R.id.btn_chu:
                inputstr += button.getText().toString();
                express += "/";
                break;
            case R.id.btn_C:
                inputstr += "";
                express += "";
                break;
            case R.id.btn_DEL:
                if (inputstr.length() >0) {
                    inputstr = inputstr.substring(0, inputstr.length() -1);
                    express = express.substring(0, express.length() -1);
                }
                break;
            case R.id.btn_deng:
                if (express.length() > 0){
                    long result = (long) AviatorEvaluator.execute(express);
                    tvResult.setText(String.valueOf(result));
                }
                flag = true;
                express = "";
                break;
            case R.id.btn_bai:
                if (tvResult.length() > 0){
                    long temp = Long.parseLong(tvResult.getText().toString());
                    tvResult.setText(String.valueOf(temp * 0.01));
                }
                break;
        }
        tvInput.setText(inputstr);

    }

}