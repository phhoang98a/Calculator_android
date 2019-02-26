package com.example.hoang.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.hoang.calculator.Calculate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNumber;
    private TextView tvResult;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;
    private Button btnNumber0;

    private Button btnCong;
    private Button btnTru;
    private Button btnNhan;
    private Button btnChia;

    private Button btnPoint;
    private Button btnOpposite;
    private Button btnResult;
    private Button btnClear;
    private Button btnClearOne;
    private Button btnClearAll;
    private String text,chr;

    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickViews();
    }


    public void initWidget() {

        edtNumber = (EditText) findViewById(R.id.edt_input);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnNumber1 = (Button) findViewById(R.id.btnNumber1);
        btnNumber2 = (Button) findViewById(R.id.btnNumber2);
        btnNumber3 = (Button) findViewById(R.id.btnNumber3);
        btnNumber4 = (Button) findViewById(R.id.btnNumber4);
        btnNumber5 = (Button) findViewById(R.id.btnNumber5);
        btnNumber6 = (Button) findViewById(R.id.btnNumber6);
        btnNumber7 = (Button) findViewById(R.id.btnNumber7);
        btnNumber8 = (Button) findViewById(R.id.btnNumber8);
        btnNumber9 = (Button) findViewById(R.id.btnNumber9);
        btnNumber0 = (Button) findViewById(R.id.btnNumber0);

        btnCong = (Button) findViewById(R.id.add);
        btnTru = (Button) findViewById(R.id.sub);
        btnNhan = (Button) findViewById(R.id.mul);
        btnChia = (Button) findViewById(R.id.dev);

        btnPoint = (Button) findViewById(R.id.dot);
        btnOpposite = (Button) findViewById(R.id.opposite);
        btnClear = (Button) findViewById(R.id.CE);
        btnClearOne = (Button) findViewById(R.id.BS);
        btnClearAll = (Button) findViewById(R.id.C);
        btnResult = (Button) findViewById(R.id.result);
    }


    public void setEventClickViews() {
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnNumber0.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);

        btnPoint.setOnClickListener(this);
        btnOpposite.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClearOne.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
        btnResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNumber0:
                edtNumber.append("0");
                break;
            case R.id.btnNumber1:
                edtNumber.append("1");
                break;
            case R.id.btnNumber2:
                edtNumber.append("2");
                break;
            case R.id.btnNumber3:
                edtNumber.append("3");
                break;
            case R.id.btnNumber4:
                edtNumber.append("4");
                break;
            case R.id.btnNumber5:
                edtNumber.append("5");
                break;
            case R.id.btnNumber6:
                edtNumber.append("6");
                break;
            case R.id.btnNumber7:
                edtNumber.append("7");
                break;
            case R.id.btnNumber8:
                edtNumber.append("8");
                break;
            case R.id.btnNumber9:
                edtNumber.append("9");
                break;
            case R.id.add:
                edtNumber.append("+");
                break;
            case R.id.sub:
                edtNumber.append("-");
                break;
            case R.id.mul:
                edtNumber.append("*");
                break;
            case R.id.dev:
                edtNumber.append("/");
                break;
            case R.id.dot:
                edtNumber.append(".");
                break;
            case R.id.opposite:
                text = edtNumber.getText().toString();
                if (text.length()==0) break;
                String number = "";
                chr = text.substring(text.length() - 1);
                while (chr.equals("0")||chr.equals("1")||chr.equals("2")||chr.equals("3")||chr.equals("4")||chr.equals("5")
                        ||chr.equals("6")||chr.equals("7")||chr.equals("8")||chr.equals("9")||chr.equals(".")){
                    text= text.substring(0, text.length() - 1);
                    number = chr +number;
                    if (text.length()==0) break;
                    chr = text.substring(text.length() - 1);
                }
                if (text.length()==0){
                    edtNumber.setText("");
                    edtNumber.append("-"+number);
                }
                else if (text.substring(text.length() - 1).equals("*")||text.substring(text.length() - 1).equals("/")){
                    edtNumber.setText("");
                    edtNumber.append(text+"-"+number);
                }
                else if (text.substring(text.length()-1).equals("-")){
                    edtNumber.setText("");
                    edtNumber.append(text.substring(0, text.length()-1)+"+"+number);
                }
                else if (text.substring(text.length()-1).equals("+")){
                    edtNumber.setText("");
                    edtNumber.append(text.substring(0, text.length()-1)+"-"+number);
                }
                break;
            case R.id.CE:
                text = edtNumber.getText().toString();
                if (text.length()==0) break;
                chr = text.substring(text.length() - 1);
                while (chr.equals("0")||chr.equals("1")||chr.equals("2")||chr.equals("3")||chr.equals("4")||chr.equals("5")
                     ||chr.equals("6")||chr.equals("7")||chr.equals("8")||chr.equals("9")||chr.equals(".")){
                    text= text.substring(0, text.length() - 1);
                    if (text.length()==0) break;
                    chr = text.substring(text.length() - 1);
                }
                edtNumber.setText("");
                edtNumber.append(text);
                break;
            case R.id.BS:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtNumber, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.C:
                edtNumber.setText("");
                tvResult.setText("");
                break;
            case R.id.result    :
                DecimalFormat df = new DecimalFormat("###.#######");
                String result =Calculate.evaluate(edtNumber.getText().toString());
                if (result =="Lỗi cú pháp")
                    tvResult.setText(result);
                else
                    tvResult.setText(df.format(Double.parseDouble(result)) + "");
        }

    }


    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
