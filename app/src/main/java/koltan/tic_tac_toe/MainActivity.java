package koltan.tic_tac_toe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioButton;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    public final static String PLAYER_ONE = "com.example.tic-tak-toe.MESSAGE";
    public final static String PLAYER_TWO = "com.example.tic-tac-toe.MESSAGE";
    EditText playerOne;
    EditText playerTwo;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = (EditText) findViewById(R.id.playerOne);
        playerTwo = (EditText) findViewById(R.id.playerTwo);
        submit = (Button) findViewById(R.id.submit);

    }

    public void sendMessage(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        Bundle extras = new Bundle();
        String num1 = playerOne.getText().toString();
        String num2 = playerTwo.getText().toString();

        if(num1 == ""){
            num1 = "X";
        }
        if(num2 == ""){
            num2 = "Y";
        }

        extras.putString("PLAYER_ONE", num1);
        extras.putString("PLAYER_TWO", num2);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
