package io.github.ngbrown11.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "io.github.ngbrown11.myfirstapp.MESSAGE";
    public Random guessNumber = new Random();
    public static int gameNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create the random number for the user to guess
        gameNumber = guessNumber.nextInt(100 - 1) + 1;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onDestroy();
            onRestart();
            return true;
        }
        else if (id == R.id.change_range) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        //Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        TextView textView = (TextView) findViewById(R.id.textView);
        String message = editText.getText().toString();
        //Compare user number to game number
        int userNumber = Integer.parseInt(message);
        String message2 = "";
        if(userNumber > gameNumber) {
            message2 = " is too high";
            textView.setText(message+message2);
            editText.setText("");
        }
        else if(userNumber < gameNumber) {
            message2 = " is too low";
            textView.setText(message+message2);
            editText.setText("");
        }
        else if(userNumber == gameNumber) {
            message2 = " is correct! \n\nPress the button at the bottom of the screen to play again";
            intent.putExtra(EXTRA_MESSAGE, message+message2);
            startActivity(intent);
            onStop();
        }
    }
}
