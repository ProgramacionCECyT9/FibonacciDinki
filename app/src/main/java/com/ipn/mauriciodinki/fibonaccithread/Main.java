package com.ipn.mauriciodinki.fibonaccithread;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Main extends ActionBarActivity {

    EditText numberInput;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        numberInput = (EditText) findViewById(R.id.numberEditText);
        outputText = (TextView) findViewById(R.id.resultTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fibonacci, menu);
        return true;
    }

    // Method to calculate the fibonacci succession
    public void getFibonacciResult(View view){
        int number = Integer.parseInt(numberInput.getText().toString());
        if (number < 0){
            outputText.setText("Impossible to calculate fibonacci with negative numbers");
        } else {
            fibonacciMethod fibonacci = new fibonacciMethod();
            fibonacci.execute(number);
        }
    }

    //Class of async task to calculate the fibonacci number
    class fibonacciMethod extends AsyncTask <Integer, Integer, Integer> {

        private ProgressDialog taskProgress;

        @Override protected void onPreExecute() {
            taskProgress = new ProgressDialog(Main.this);
            taskProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            taskProgress.setMessage("Slaves Working...");
            taskProgress.setCancelable(true);
            taskProgress.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    fibonacciMethod.this.cancel(true);
                }

            });
            taskProgress.setMax(100);
            taskProgress.setProgress(0);
            taskProgress.show();
        }

        @Override protected Integer doInBackground(Integer... axis) {

            int result = 1;
            int number_1 = 1;
            int number_2 = 1;
            int position = axis[0];

            if(position == 1) {
                return 0;
            }
            else if(position == 2 || position == 3) {
                return 1;
            }
            else {
                for (int i = 4; i <= position && !isCancelled(); i++) {
                    number_2 = number_1;
                    number_1 = result;
                    result = number_1 + number_2;
                    SystemClock.sleep(100);
                }
                return result;
            }
        }

        @Override protected void onPostExecute(Integer result) {
            taskProgress.dismiss();
            outputText.setText("Fibonacci result : " + result +"");
        }

        @Override protected void onCancelled() {
            outputText.setText("Process canceled...");
        }

    }

    // Method to calculate the factorial number
    public void getFactorialResult(View view){
        int number = Integer.parseInt(numberInput.getText().toString());
        if (number < 0){
            outputText.setText("Impossible to calculate factorial of negative numbers");
        } else {
            /*factorialMethod factorial = new factorialMethod();
            factorialMethod.execute(number);*/
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
