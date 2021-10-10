package com.zavierdev.calculator.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.zavierdev.calculator.R;
import com.zavierdev.calculator.helpers.Calculator;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Calculator calculator;
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

        this.calculator = new Calculator();
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

    /**
     * Saving all component id on activity attributes
     */
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

    /**
     * Adding all button component click listener
     */
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

    /**
     * Set operation value to default state and normal calculation state
     */
    private void setDefaultState() {
        setEdtSelectionToLast();
        this.edtOperation.setTextColor(Color.parseColor("#000000"));
        this.tvResult.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.resultText));
        this.edtOperation.setText("");
        this.tvResult.setText("");
        this.STATE_ERROR = false;
        this.STATE_RESULT = false;
    }

    /**
     * Set to bad expression state error
     */
    @SuppressLint("SetTextI18n")
    private void stateErrorDisplay() {
        this.edtOperation.setTextColor(Color.parseColor("#FF0000"));
        this.tvResult.setTextColor(Color.parseColor("#FF0000"));
        this.tvResult.setText("Bad Expression");
    }

    /**
     * Clear error (bad expression) state to normal state
     */
    private void removeStateError() {
        this.edtOperation.setTextColor(Color.parseColor("#000000"));
        this.tvResult.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.resultText));
        this.tvResult.setText("");
        this.STATE_ERROR = false;
    }

    /**
     * Set state to calculation result
     */
    private void setStateResult() {
        moveResultToEdtOperation();
        setEdtSelectionToLast();
        this.btnBackspace.setIconResource(R.drawable.ic_char_c_24_white); // Change Backspace Button to Clear Button
        this.STATE_RESULT = true;
    }

    /**
     * Set to normal calculation state
     *
     * @param clearOperation
     */
    private void removeStateResult(Boolean clearOperation) {
        if (clearOperation) {
            this.edtOperation.setText("");
            this.tvResult.setText("");
        }
        this.btnBackspace.setIconResource(R.drawable.ic_baseline_backspace_24_white); // Change Clear Button to BackSpace Button
        this.STATE_RESULT = false;
    }

    /**
     * Handle all button click listener
     *
     * @param v
     */
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        final boolean isOnResultState = this.STATE_RESULT;
        final boolean isOnErrorState = this.STATE_ERROR;
        final boolean isButtonBackSpaceActionClick = v.getId() != R.id.btn_backspace;
        final boolean isCanRemoveStateResult = isButtonBackSpaceActionClick && isOnResultState;

        if (isOnErrorState) {
            removeStateError();
        }

        if (isCanRemoveStateResult) {
            removeStateResult(false);
        }

        switch (v.getId()) {
            case R.id.btn_0:
                addEdtOperationText("0");
                calculateAndDisplay();
                break;
            case R.id.btn_1:
                addEdtOperationText("1");
                calculateAndDisplay();
                break;
            case R.id.btn_2:
                addEdtOperationText("2");
                calculateAndDisplay();
                break;
            case R.id.btn_3:
                addEdtOperationText("3");
                calculateAndDisplay();
                break;
            case R.id.btn_4:
                addEdtOperationText("4");
                calculateAndDisplay();
                break;
            case R.id.btn_5:
                addEdtOperationText("5");
                calculateAndDisplay();
                break;
            case R.id.btn_6:
                addEdtOperationText("6");
                calculateAndDisplay();
                break;
            case R.id.btn_7:
                addEdtOperationText("7");
                calculateAndDisplay();
                break;
            case R.id.btn_8:
                addEdtOperationText("8");
                calculateAndDisplay();
                break;
            case R.id.btn_9:
                addEdtOperationText("9");
                calculateAndDisplay();
                break;
            case R.id.btn_addition:
                addEdtOperationText("+");
                calculateAndDisplay();
                break;
            case R.id.btn_backspace:
                if (isOnResultState) { // Backspace button has changed to Clear Button [C]
                    removeStateResult(true);
                } else { // Delete operation
                    removeEdtOperationText();
                }
                calculateAndDisplay();
                break;
            case R.id.btn_close_bracket:
                addEdtOperationText(")");
                calculateAndDisplay();
                break;
            case R.id.btn_division:
                addEdtOperationText("÷");
                calculateAndDisplay();
                break;
            case R.id.btn_dot:
                addEdtOperationText(".");
                calculateAndDisplay();
                break;
            case R.id.btn_multiplication:
                addEdtOperationText("×");
                calculateAndDisplay();
                break;
            case R.id.btn_open_bracket:
                addEdtOperationText("(");
                calculateAndDisplay();
                break;
            case R.id.btn_power:
                addEdtOperationText("^");
                calculateAndDisplay();
                break;
            case R.id.btn_result:
                final boolean isOperationNotEmpty = !this.edtOperation.getText().toString().isEmpty();
                calculateAndDisplay();
                if (isOnErrorState) {
                    stateErrorDisplay();
                } else if (isOperationNotEmpty) {
                    setStateResult();
                }
                break;
            case R.id.btn_subtraction:
                addEdtOperationText("-");
                calculateAndDisplay();
                break;
        }
    }

    /**
     * Set cursor EditText Operation to last position
     */
    private void setEdtSelectionToLast() {
        this.edtOperation.setSelection(this.edtOperation.getText().toString().length());
    }

    /**
     * Add text both number or operation to EditText
     *
     * @param value
     */
    private void addEdtOperationText(String value) {
        if (this.edtOperation.getSelectionStart() != 0 && calculator.checkIsOperator(value) && isEdtCursorOnOperator() && !value.equals("-")) {
            removeEdtOperationText();
        }

        if (!this.edtOperation.getText().toString().isEmpty() || !calculator.checkIsOperator(value)) {
            this.edtOperation.getText().insert(this.edtOperation.getSelectionStart(), value);
        }
    }

    /**
     * Remove one digit value of EditText Operation Component
     */
    private void removeEdtOperationText() {
        int cursorStart = this.edtOperation.getSelectionStart();
        if (cursorStart != 0) {
            this.edtOperation.getText().delete(cursorStart - 1, cursorStart);
        }
    }

    /**
     * Move calculation result to EditText Operation Component
     */
    private void moveResultToEdtOperation() {
        this.edtOperation.setText(this.tvResult.getText().toString().replace(",", ""));
        this.tvResult.setText("");
    }

    /**
     * Evaluate operation and perform calculation then display to TextView Result
     */
    private void calculateAndDisplay() {
        final String operation = this.edtOperation.getText().toString();
        this.STATE_ERROR = false;

        if (operation.isEmpty()) {
            this.tvResult.setText("");
        } else if (!isEdtLastValueOperator()) {
            try {
                double resultVal = calculator.performCalculation(this.edtOperation.getText().toString());
                DecimalFormat formatter = new DecimalFormat("#,###.######");
                this.tvResult.setText(formatter.format(resultVal));
            } catch (Exception e) {
                this.STATE_ERROR = true;
            }
        }
    }

    /**
     * Check is EditText Operation last value is operator
     *
     * @return
     */
    private boolean isEdtLastValueOperator() {
        return calculator.checkIsOperator(String.valueOf(this.edtOperation.getText().toString().charAt(this.edtOperation.getText().toString().length() - 1)));
    }

    /**
     * Check EditText Operation value before cursor is operator
     *
     * @return
     */
    private boolean isEdtCursorOnOperator() {
        String edtValue = String.valueOf(this.edtOperation.getText().toString().charAt(this.edtOperation.getSelectionStart() - 1));
        boolean isOnOperator = false;
        int i = 0;
        while (true) {
            String[] strArr = calculator.LIST_SUPPORTED_OPERATOR;
            if (i >= strArr.length) {
                return isOnOperator;
            }
            if (strArr[i].equals(edtValue)) {
                isOnOperator = true;
            }
            i++;
        }
    }
}
