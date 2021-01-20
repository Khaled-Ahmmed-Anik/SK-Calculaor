package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
     //a,b -> double variavle for calculation
     double a = 0, b = 0;int resActive=0;
     int num1cnt=0;int num2cnt=0;
     String num1 = "", num2 = "", oper = "?",display="";//display var stord the value of input
    
    public void reset (View view){   //clear button reffers here:
        againReset();
    }

    public void againReset(){   //clear button reffers here:
        try{
            a = 0;b = 0;num1 = "";num2 = "";oper = "?";display="";resActive=0;
            num1cnt=0;num2cnt=0;
            TextView status= findViewById(R.id.status);
            status.setText("0.0");
            printDisplayInput();
        }
        catch(Exception E){
            Toast.makeText(this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            againReset();
        }
    }
    
    public void printDisplayInput(){         ///dis1 print
        TextView dis1= findViewById(R.id.dis1);     //dis1 --- id of inputer display
        dis1.setText(display);
    }

    public  void operatorCheck(View view){ /// (+,-,*,/) buttons reffers here
        try {




            Button opr = (Button) view;
            String str = opr.getTag().toString();  // uset kon operator button tab korse, seta str a save ase;


            if (resActive == 1) {                           //resutl show kortese ekhon o clear kora hoy nai
                againReset();
            }


            if (num1.equals("") && (str.equals("/") || str.equals("x"))) {    // surutei ki (/,*) tab korlo?
                errorMsg();
                return;
            } else if (num1.equals("") && (str.equals("+") || str.equals("-"))) {    // 1st value te (+,-) add korte casse?
                num1 = str;
                display += num1;
                printDisplayInput();
                return;
            } else if (num1.equals("-") || num1.equals("+")) {     // 1st value ki (--,-+,+-,++) bossasse?
                Toast.makeText(this, "tap valid input or CLEAR", Toast.LENGTH_SHORT).show();
                return;
            } else if (num2.equals("") && oper.equals("?")) {   // oprator paisi?
                operatorGiven(str);
                return;
            } else if (num2.equals("") && (str.equals("/") || str.equals("x"))) {   //oprator er pore (/,*) disse?
                Toast.makeText(this, "tap valid input or CLEAR", Toast.LENGTH_SHORT).show();
                return;
            } else if (num2.equals("") && (str.equals("-") || str.equals("+"))) {     // 1st value te (+,-) add korte casse?
                num2 = str;
                display += str;
                printDisplayInput();
                return;
            }
            Toast.makeText(this, "Tap '=' for result", Toast.LENGTH_SHORT).show();  /// number 1 , operator, 2er pore abar operator captese;
            return;
        }
        catch(Exception E){
            Toast.makeText(this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            againReset();
        }
    }

    public void wrondInputMasg(){
        Toast.makeText(this, "Wrong input, try again", Toast.LENGTH_SHORT).show();
        againReset();
        return;
    }
    public void errorMsg(){
        Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show();
        oper="?";
        return;
    }


    public void operatorGiven (String str){  // operator caple ekhane asbe:
        try {
            oper = str;
            display += " " + str + " ";
            printDisplayInput();
            a = Double.parseDouble(num1); ///number1 a te stored hoilo;
        }
        catch(Exception E){
            Toast.makeText(this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            againReset();
        }
    }
    

    public void buildNumber (View view){ //number caple ekhane asbe:
        try {
            Button n = (Button) view;
            String str = n.getTag().toString();// str a input user input kora value raklam

            if (resActive == 1) {     // equal diye result ber kore clear na kore number capce;
                resActive = 0;
                Toast.makeText(this, "Last calulation CLEARED", Toast.LENGTH_SHORT).show();
                againReset();
                display = "";
            }

            if (oper == "?") {   // number 1 hobe naki number 2 ,check korlam ;
                if (str.equals(".") && num1cnt == 1) {
                    Toast.makeText(this, "Wrong input, try again", Toast.LENGTH_SHORT).show();
                    return;
                } else if (str.equals(".")) {
                    num1cnt++;
                }
                num1 += str;
            } else {
                if (str.equals(".") && num2cnt == 1) {
                    Toast.makeText(this, "Wrong input, try again", Toast.LENGTH_SHORT).show();
                    return;
                } else if (str.equals(".")) {
                    num2cnt++;
                }
                num2 += str;
            }

            display += str;
            printDisplayInput();     // real time a user er input screen a dekaisse emon feel dewar try korlam;
        }
        catch(Exception E){
            Toast.makeText(this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            againReset();
        }

    }

   // public void result (View view){}

    public void result(View view){         // "=" refers here;
        try {
            if (num2.equals("") || (oper.equals("?")) || (num1.equals("")) || num2.equals("-") || num2.equals("+")) {    // incomplere rekhe '='diya dise?
                Toast.makeText(this, "Input not completed", Toast.LENGTH_SHORT).show();
                return;
            }

            resActive = 1;

            b = Double.parseDouble(num2);

            double ans = 0;// operator onojayi oparation kortesi
            if (oper.equals("+")) ans = a + b;
            else if (oper.equals("-")) ans = a - b;
            else if (oper.equals("x")) ans = a * b;
            else ans = a / b;

            String ansStr = Double.toString(ans);
            String tempAns="";

            for(int i=0,flag=0;i<ansStr.length() ;i++){

                String ch=Character.toString(ansStr.charAt(i));
                if(i+1==ansStr.length()-1 && ch.equals(".") && ansStr.charAt(i+1)=='0')break;
                if(ansStr.charAt(i)=='E') {
                    tempAns+=" x10^";
                    continue;
                }else {
                    tempAns+=ch;
                }

            }


            ansStr=tempAns;
            showresult(ansStr);

            a = 0;
            b = 0;
            num1 = "";
            num2 = "";
            oper = "?";
        }
        catch(Exception E){
            Toast.makeText(this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            againReset();
        }
    }

    public void showresult(String ansStr){
        TextView status= findViewById(R.id.status);
        status.setText(ansStr);
    }

    /*try
    {

    }
    catch(Exception e)
    {
        System.out.println(e);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}