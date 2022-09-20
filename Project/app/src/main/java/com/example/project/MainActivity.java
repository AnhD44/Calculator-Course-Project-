 package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

 public class MainActivity extends AppCompatActivity
 {
     TextView inputTV;
     TextView resultTV;

     String input = "";
     String eq = "";
     String tempEq = "";
     
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTextView();
    }

     private void startTextView()
     {
        inputTV = (TextView) findViewById(R.id.inputTextView);
        resultTV = (TextView) findViewById(R.id.resultTextView);
     }

     private void setInput(String givenValue)
     {
         input = input + givenValue;
         inputTV.setText(input);
     }

     public void equalClick(View v)
     {
         Double result = null;
         ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
         checkPower();

         try {
             result = (double)engine.eval(eq);
         } catch (ScriptException e)
         {
             Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
         }

         if(result != null)
             resultTV.setText(String.valueOf(result.doubleValue()));
     }

     private void checkPower()
     {
         ArrayList<Integer> indexOfPowers = new ArrayList<>();
         for(int i = 0; i < input.length(); i++)
         {
             if (input.charAt(i) == '^')
                 indexOfPowers.add(i);
         }

         eq = input;
         tempEq = input;
         for(Integer index: indexOfPowers)
         {
             changeFormula(index);
         }
         eq = tempEq;
     }

     private void changeFormula(Integer index)
     {
         String numbLeft = "";
         String numbRight = "";

         for(int i = index + 1; i< input.length(); i++)
         {
             if(isNumeric(input.charAt(i)))
                 numbRight = numbRight + input.charAt(i);
             else
                 break;
         }

         for(int i = index - 1; i >= 0; i--)
         {
             if(isNumeric(input.charAt(i)))
                 numbLeft = numbLeft + input.charAt(i);
             else
                 break;
         }

         String original = numbLeft + "^" + numbRight;
         String changed = "Math.pow("+numbLeft+","+numbRight+")";
         tempEq = tempEq.replace(original,changed);
     }

     private boolean isNumeric(char c)
     {
         if((c <= '9' && c >= '0') || c == '.')
             return true;

         return false;
     }

     public void clearClick(View view)
     {
        inputTV.setText("");
        input =  "";
        resultTV.setText("");
     }

     public void powerClick(View view)
     {
         setInput("^");
     }

     public void percentClick(View view)
     {
         setInput("%");
     }

     public void divideClick(View view)
     {
         setInput("/");
     }

     public void sevenClick(View view)
     {
         setInput("7");
     }

     public void eightClick(View view)
     {
         setInput("8");
     }

     public void nineClick(View view)
     {
         setInput("9");
     }

     public void multiplyClick(View view)
     {
         setInput("*");
     }

     public void fourClick(View view)
     {
         setInput("4");
     }

     public void fiveClick(View view)
     {
         setInput("5");
     }

     public void sixClick(View view)
     {
         setInput("6");
     }

     public void minusClick(View view)
     {
         setInput("-");
     }

     public void oneClick(View view)
     {
         setInput("1");
     }

     public void twoClick(View view)
     {
         setInput("2");
     }

     public void threeClick(View view)
     {
         setInput("3");
     }

     public void addClick(View view)
     {
         setInput("+");
     }

     public void zeroClick(View view)
     {
         setInput("0");
     }

     public void commaClick(View view)
     {
         setInput(".");
     }

 }