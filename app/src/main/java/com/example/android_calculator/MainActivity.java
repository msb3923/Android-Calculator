package com.example.android_calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;
import android.util.Log;




import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView input;
    private TextView output;
    private String in;
    private String out;
    private String sign;

    private double a, b;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView)findViewById(R.id.outputText);
        input = (TextView)findViewById(R.id.inputText);
        out = "";
        in = "";
    }
    public void onClick(View v)
    {
        Button button = (Button)v;
        in = in + button.getText().toString();
        input.setText(in);
    }

    public void onClickTan(View v)
    {
        Button button = (Button)v;
        in = in + "t";
        input.setText(in);
    }

    public void onClickSin(View v)
    {
        Button button = (Button)v;
        in = in + "s";
        input.setText(in);
    }

    public void onClickCos(View v)
    {
        Button button = (Button)v;
        in = in + "c";
        input.setText(in);
    }

    public void clear(View v)
    {
        Button button = (Button)v;
        in = "";
        out = "";
        input.setText(in);
        output.setText(out);
    }

    public void onClickOperations(View v)
    {
        Button button = (Button)v;
        sign = button.getText().toString();
        in = in + sign;
        input.setText(in);
    }


    public double parenCalculate(List<String> values)
    {
        boolean op = false;
        Log.d("FIRST", "look");
        double result = Double.parseDouble(values.get(0));
        for (int i = 1; i < values.size(); i++) {
            op = false;
            if ((values.get(i).equals("*")) || (values.get(i).equals("-")) || (values.get(i).equals("+")) || (values.get(i).equals("/"))) {
                String b = (values.get(i + 1));
                Double a = 0d;
//                if (values.get(i+1).equals("(")) {
//                    i = i + 2;
//                    List<String> inside = new ArrayList<String>();
//                    while (!values.get(i).equals(")")) {
//                        inside.add(values.get(i));
//                        i = i + 1;
//                    }
//                    b = parenCalculate(inside);
//                }
//                else {
//                    Log.d("VALUE", b2);
//                }
                if ((b.equals("s")) || (b.equals("c")) || (b.equals("t"))) {
                    Double c = Double.parseDouble(values.get(i + 2));
                    switch (b) {
                        case "s":
                            a = Math.sin(c);
                            break;
                        case "c":
                            a = Math.cos(c);
                            break;
                        case "t":
                            a = Math.tan(c);
                            break;
                    }
                    op = true;
                }
                else {
                    a = Double.parseDouble(b);
                }
                switch (values.get(i)) {
                    case "*":
                        result = result * a;
                        break;
                    case "/":
                        result = result / a;
                        break;
                    case "+":
                        result = result + a;
                        break;
                    case "-":
                        result = result - a;
                        break;
                }
                i = i + 1;
                if (op)
                {
                    i = i + 1;
                }
            }
            else if ((values.get(i).equals("s")) || (values.get(i).equals("c")) || (values.get(i).equals("t")))
            {
                Log.d("TRIG", "afs");
                String b = (values.get(i));
                Double a = 0d;
                Double c = Double.parseDouble(values.get(i + 1));
                switch (b) {
                    case "s":
                        a = Math.sin(c);
                        break;
                    case "c":
                        a = Math.cos(c);
                        break;
                    case "t":
                        a = Math.tan(c);
                        break;
                }
                result = result + a;
                i = i + 2;
            }

        }
        return result;
    }


    public void calculate(View v) {
        boolean trig = false;

        List<String> values = new ArrayList<String>();
        String currNum = "";
        String oper = "";
        if (in.charAt(0) == '*' || in.charAt(0) == '/' || in.charAt(0) == '+' || in.charAt(0) == '-') {
            out = "Error: First Entry Must be a number";
            output.setText(out);
        } else if (in.charAt(in.length() - 1) == 't' || in.charAt(in.length() - 1) == 's' || in.charAt(in.length() - 1) == 'c' || in.charAt(in.length() - 1) == '*' || in.charAt(in.length() - 1) == '/' || in.charAt(in.length() - 1) == '+' || in.charAt(in.length() - 1) == '-') {
            out = "Error: Last Entry Must be a number";
            output.setText(out);
        }
        else if (in.charAt(0) == '.')
        {
            out = "0.0";
            output.setText(out);
        } else{
            for (char ch : in.toCharArray()) {
                if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == '.') {
                    currNum = currNum + ch;
                    trig = false;
                } else {
                    if (currNum != "") {
                        values.add(currNum);
                    }
                    currNum = "";
                    if ((ch == 't' || ch == 'c' || ch == 's') && !trig) {
                        oper = oper + ch;
                        values.add(oper);
                        oper = "";
                        trig = true;
                    } else if (ch == 'o' || ch == 'i' || ch == 'n' || ch == 'a' || ch == 't' || ch == 's' || ch == 'c') {

                    }
//                else if (ch == '(' || ch == ')')
//                {
//                    oper = oper + ch;
//                    values.add(oper);
//                    oper = "";
//                    if (ch == '(')
//                    {
//                        closed = false;
//                    }
//                    else
//                    {
//                        closed = true;
//
//                    }
                    else {
                        oper = oper + ch;
                        values.add(oper);
                        oper = "";
                        trig = false;
                    }
                }
            }
            if (currNum != "") {
                values.add(currNum);
            }


            boolean op = false;
            Log.d("FIRST", "look");
            double result = 0d;
            if (!(values.get(0).equals("s")) && !(values.get(0).equals("c")) && !(values.get(0).equals("t"))) {
                result = Double.parseDouble(values.get(0));
            } else {
                result = 0d;
            }
            for (int i = 1; i < values.size(); i++) {
                op = false;
                if ((values.get(i).equals("*")) || (values.get(i).equals("-")) || (values.get(i).equals("+")) || (values.get(i).equals("/"))) {
                    String b = (values.get(i + 1));
                    Double a = 0d;
//                if (values.get(i+1).equals("(")) {
//                    i = i + 2;
//                    List<String> inside = new ArrayList<String>();
//                    while (!values.get(i).equals(")")) {
//                        inside.add(values.get(i));
//                        i = i + 1;
//                    }
//                    b = parenCalculate(inside);
//                }
//                else {
//                    Log.d("VALUE", b2);
//                }
                    if ((b.equals("s")) || (b.equals("c")) || (b.equals("t"))) {
                        Double c = Double.parseDouble(values.get(i + 2));
                        switch (b) {
                            case "s":
                                a = Math.sin(c);
                                break;
                            case "c":
                                a = Math.cos(c);
                                break;
                            case "t":
                                a = Math.tan(c);
                                break;
                        }
                        op = true;
                    } else {
                        a = Double.parseDouble(b);
                    }
                    switch (values.get(i)) {
                        case "*":
                            result = result * a;
                            break;
                        case "/":
                            result = result / a;
                            break;
                        case "+":
                            result = result + a;
                            break;
                        case "-":
                            result = result - a;
                            break;
                    }
                    i = i + 1;
                    if (op) {
                        i = i + 1;
                    }
                } else if ((values.get(0).equals("s")) || (values.get(0).equals("c")) || (values.get(0).equals("t"))) {
                    Log.d("TRIG", "afs");
                    String b = (values.get(0));
                    Double a = 0d;
                    Double c = Double.parseDouble(values.get(1));
                    switch (b) {
                        case "s":
                            a = Math.sin(c);
                            break;
                        case "c":
                            a = Math.cos(c);
                            break;
                        case "t":
                            a = Math.tan(c);
                            break;
                    }
                    result = result + a;
                    i = i + 2;
                }

            }


            out = Double.toString(result);
            output.setText(out);
//
//        if (closed == false)
//        {
//            out = "Unclosed Parenthese in Equation";
//            output.setText(out);
//        }
        }
    }



}
