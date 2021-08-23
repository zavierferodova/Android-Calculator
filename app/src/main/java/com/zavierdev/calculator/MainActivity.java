package com.zavierdev.calculator;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] LIST_CHECK_OPERAND = {"^", "+", "-", "×", "÷"};
    private Boolean STATE_ERROR = false;
    private Boolean STATE_RESULT = false;
    private MaterialButton btn0;
    private MaterialButton btn1;
    private MaterialButton btn2;
    private MaterialButton btn3;
    private MaterialButton btn4;
    private MaterialButton btn5;
    private MaterialButton btn6;
    private MaterialButton btn7;
    private MaterialButton btn8;
    private MaterialButton btn9;
    private MaterialButton btnAddition;
    private MaterialButton btnBackspace;
    private MaterialButton btnCloseBracket;
    private MaterialButton btnDivision;
    private MaterialButton btnDot;
    private MaterialButton btnMultiplication;
    private MaterialButton btnOpenBracket;
    private MaterialButton btnPower;
    private MaterialButton btnResult;
    private MaterialButton btnSubtraction;
    private EditText edtOperation;
    private TextView tvResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);

        initViewComponents();
        setButtonClickListener();
        setDefaultState();
        if (Build.VERSION.SDK_INT >= 21) {
            this.edtOperation.setShowSoftInputOnFocus(false);
        } else {
            this.edtOperation.setTextIsSelectable(true);
        }
        this.tvResult.setSelected(true);
    }

    private void initViewComponents() {
        this.edtOperation = (EditText) findViewById(R.id.edt_operation);
        this.tvResult = (TextView) findViewById(R.id.tv_result);
        this.btnResult = (MaterialButton) findViewById(R.id.btn_result);
        this.btnBackspace = (MaterialButton) findViewById(R.id.btn_backspace);
        this.btnOpenBracket = (MaterialButton) findViewById(R.id.btn_open_bracket);
        this.btnCloseBracket = (MaterialButton) findViewById(R.id.btn_close_bracket);
        this.btnPower = (MaterialButton) findViewById(R.id.btn_power);
        this.btnAddition = (MaterialButton) findViewById(R.id.btn_addition);
        this.btnSubtraction = (MaterialButton) findViewById(R.id.btn_subtraction);
        this.btnMultiplication = (MaterialButton) findViewById(R.id.btn_multiplication);
        this.btnDivision = (MaterialButton) findViewById(R.id.btn_division);
        this.btnDot = (MaterialButton) findViewById(R.id.btn_dot);
        this.btn0 = (MaterialButton) findViewById(R.id.btn_0);
        this.btn1 = (MaterialButton) findViewById(R.id.btn_1);
        this.btn2 = (MaterialButton) findViewById(R.id.btn_2);
        this.btn3 = (MaterialButton) findViewById(R.id.btn_3);
        this.btn4 = (MaterialButton) findViewById(R.id.btn_4);
        this.btn5 = (MaterialButton) findViewById(R.id.btn_5);
        this.btn6 = (MaterialButton) findViewById(R.id.btn_6);
        this.btn7 = (MaterialButton) findViewById(R.id.btn_7);
        this.btn8 = (MaterialButton) findViewById(R.id.btn_8);
        this.btn9 = (MaterialButton) findViewById(R.id.btn_9);
    }

    private void setButtonClickListener() {
        this.btnResult.setOnClickListener(this);
        this.btnBackspace.setOnClickListener(this);
        this.btnOpenBracket.setOnClickListener(this);
        this.btnCloseBracket.setOnClickListener(this);
        this.btnPower.setOnClickListener(this);
        this.btnAddition.setOnClickListener(this);
        this.btnSubtraction.setOnClickListener(this);
        this.btnMultiplication.setOnClickListener(this);
        this.btnDivision.setOnClickListener(this);
        this.btnDot.setOnClickListener(this);
        this.btn0.setOnClickListener(this);
        this.btn1.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
        this.btn4.setOnClickListener(this);
        this.btn5.setOnClickListener(this);
        this.btn6.setOnClickListener(this);
        this.btn7.setOnClickListener(this);
        this.btn8.setOnClickListener(this);
        this.btn9.setOnClickListener(this);
    }

    private void setDefaultState() {
        setEdtSelectionToLast();
        this.edtOperation.setTextColor(Color.parseColor("#000000"));
        this.tvResult.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.resultText));
        this.edtOperation.setText("");
        this.tvResult.setText("");
        this.STATE_ERROR = false;
        this.STATE_RESULT = false;
    }

    private void stateErrorDisplay() {
        this.edtOperation.setTextColor(Color.parseColor("#FF0000"));
        this.tvResult.setTextColor(Color.parseColor("#FF0000"));
        this.tvResult.setText("Bad Expression");
    }

    private void removeStateError() {
        this.edtOperation.setTextColor(Color.parseColor("#000000"));
        this.tvResult.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.resultText));
        this.tvResult.setText("");
        this.STATE_ERROR = false;
    }

    private void setStateResult() {
        moveResultToEdtOperation();
        setEdtSelectionToLast();
        this.btnBackspace.setIconResource(R.drawable.ic_char_c_24_white);
        this.STATE_RESULT = true;
    }

    private void removeStateResult(Boolean clearOperation) {
        if (clearOperation.booleanValue()) {
            this.edtOperation.setText("");
            this.tvResult.setText("");
        }
        this.btnBackspace.setIconResource(R.drawable.ic_baseline_backspace_24_white);
        this.STATE_RESULT = false;
    }

    public void onClick(View v) {
        if (this.STATE_ERROR.booleanValue()) {
            removeStateError();
        }
        if (v.getId() != R.id.btn_backspace && this.STATE_RESULT.booleanValue()) {
            removeStateResult(false);
        }
        switch (v.getId()) {
            case R.id.btn_0:
                addEdtOperationText("0");
                calculateAndDisplay();
                return;
            case R.id.btn_1:
                addEdtOperationText("1");
                calculateAndDisplay();
                return;
            case R.id.btn_2:
                addEdtOperationText("2");
                calculateAndDisplay();
                return;
            case R.id.btn_3:
                addEdtOperationText("3");
                calculateAndDisplay();
                return;
            case R.id.btn_4:
                addEdtOperationText("4");
                calculateAndDisplay();
                return;
            case R.id.btn_5:
                addEdtOperationText("5");
                calculateAndDisplay();
                return;
            case R.id.btn_6:
                addEdtOperationText("6");
                calculateAndDisplay();
                return;
            case R.id.btn_7:
                addEdtOperationText("7");
                calculateAndDisplay();
                return;
            case R.id.btn_8:
                addEdtOperationText("8");
                calculateAndDisplay();
                return;
            case R.id.btn_9:
                addEdtOperationText("9");
                calculateAndDisplay();
                return;
            case R.id.btn_addition:
                addEdtOperationText("+");
                calculateAndDisplay();
                return;
            case R.id.btn_backspace:
                if (this.STATE_RESULT.booleanValue()) {
                    removeStateResult(true);
                } else {
                    removeEdtOperationText();
                }
                calculateAndDisplay();
                return;
            case R.id.btn_close_bracket:
                addEdtOperationText(")");
                calculateAndDisplay();
                return;
            case R.id.btn_division:
                addEdtOperationText("÷");
                calculateAndDisplay();
                return;
            case R.id.btn_dot:
                addEdtOperationText(".");
                calculateAndDisplay();
                return;
            case R.id.btn_multiplication:
                addEdtOperationText("×");
                calculateAndDisplay();
                return;
            case R.id.btn_open_bracket:
                addEdtOperationText("(");
                calculateAndDisplay();
                return;
            case R.id.btn_power:
                addEdtOperationText("^");
                calculateAndDisplay();
                return;
            case R.id.btn_result:
                calculateAndDisplay();
                if (this.STATE_ERROR.booleanValue()) {
                    stateErrorDisplay();
                    return;
                } else {
                    setStateResult();
                    return;
                }
            case R.id.btn_subtraction:
                addEdtOperationText("-");
                calculateAndDisplay();
                return;
            default:
                return;
        }
    }

    private void setEdtSelectionToLast() {
        this.edtOperation.setSelection(this.edtOperation.getText().toString().length());
    }

    private void addEdtOperationText(String textValue) {
        if (this.edtOperation.getSelectionStart() != 0 && isOperand(textValue) && isEdtCursorOnOperand() && !textValue.equals("-")) {
            removeEdtOperationText();
        }
        if (!this.edtOperation.getText().toString().isEmpty() || !isOperand(textValue)) {
            this.edtOperation.getText().insert(this.edtOperation.getSelectionStart(), textValue);
        }
    }

    private void removeEdtOperationText() {
        int cursorStart = this.edtOperation.getSelectionStart();
        if (cursorStart != 0) {
            this.edtOperation.getText().delete(cursorStart - 1, cursorStart);
        }
    }

    private void calculateAndDisplay() {
        if (this.edtOperation.getText().toString().isEmpty()) {
            this.tvResult.setText("");
        } else if (!isEdtLastValueOperand()) {
            String edtValue = fillLostBracket(this.edtOperation.getText().toString().replace("×", "*").replace("÷", "/"));
            try {
                DecimalFormat formatter = new DecimalFormat("#,###.######");
                double resultVal = new ExpressionBuilder(edtValue).build().evaluate();
                this.STATE_ERROR = false;
                if (!edtValue.isEmpty()) {
                    this.tvResult.setText(formatter.format(resultVal));
                }
            } catch (Exception e) {
                this.STATE_ERROR = true;
            }
        }
    }

    private void moveResultToEdtOperation() {
        this.edtOperation.setText(this.tvResult.getText().toString().replace(",", ""));
        this.tvResult.setText("");
    }

    private boolean isOperand(String value) {
        boolean isOperand = false;
        int i = 0;
        while (true) {
            String[] strArr = this.LIST_CHECK_OPERAND;
            if (i >= strArr.length) {
                return isOperand;
            }
            if (strArr[i].equals(value)) {
                isOperand = true;
            }
            i++;
        }
    }

    private boolean isEdtLastValueOperand() {
        return isOperand(String.valueOf(this.edtOperation.getText().toString().charAt(this.edtOperation.getText().toString().length() - 1)));
    }

    private boolean isEdtCursorOnOperand() {
        String edtValue = String.valueOf(this.edtOperation.getText().toString().charAt(this.edtOperation.getSelectionStart() - 1));
        boolean isOnOperand = false;
        int i = 0;
        while (true) {
            String[] strArr = this.LIST_CHECK_OPERAND;
            if (i >= strArr.length) {
                return isOnOperand;
            }
            if (strArr[i].equals(edtValue)) {
                isOnOperand = true;
            }
            i++;
        }
    }

    private String fillLostBracket(String str) {
        if (String.valueOf(str.charAt(str.length() - 1)).equals("(")) {
            str = str.substring(0, str.length() - 1);
        }
        return fillCloseBracket(fillOpenBracket(str));
    }

    private String fillOpenBracket(String str) {
        int openBracketAmmounts = 0;
        int closeBracketAmmounts = 0;
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals("(")) {
                openBracketAmmounts++;
            }
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (String.valueOf(str.charAt(i2)).equals(")")) {
                closeBracketAmmounts++;
            }
        }
        for (int i3 = 0; i3 < openBracketAmmounts - closeBracketAmmounts; i3++) {
            str = str + ")";
        }
        return str;
    }

    private String fillCloseBracket(String str) {
        int openBracketAmmounts = 0;
        int closeBracketAmmounts = 0;
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals(")")) {
                closeBracketAmmounts++;
            }
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (String.valueOf(str.charAt(i2)).equals("(")) {
                openBracketAmmounts++;
            }
        }
        for (int i3 = 0; i3 < closeBracketAmmounts - openBracketAmmounts; i3++) {
            str = "(" + str;
        }
        return str;
    }
}
