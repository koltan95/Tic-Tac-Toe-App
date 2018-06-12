package koltan.tic_tac_toe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioButton;
import android.content.Intent;

public class GameActivity extends AppCompatActivity {

    int control[][];
    int i, j = 0;
    Button b[][];
    int turn = 1;
    int scoreOne = 0;
    int scoreTwo = 0;
    int scoreTies = 0;
    TextView viewTurn;
    TextView playerOne;
    TextView playerTwo;
    TextView ties;
    TextView p1Score;
    TextView p2Score;
    TextView numTies;
    String p1;
    String p2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
         p1 = extras.getString("PLAYER_ONE");
         p2 = extras.getString("PLAYER_TWO");

        playerOne = (TextView) findViewById(R.id.playerOne);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        ties = (TextView) findViewById(R.id.ties);
        viewTurn = (TextView) findViewById(R.id.viewTurn);
        p1Score = (TextView) findViewById(R.id.p1Score);
        p2Score = (TextView) findViewById(R.id.p2Score);
        numTies = (TextView) findViewById(R.id.numTies);

        playerOne.setText(p1 + ":");
        playerTwo.setText(p2 + ":");
        p1Score.setText(String.valueOf(scoreOne));
        p2Score.setText(String.valueOf(scoreTwo));
        numTies.setText(String.valueOf(scoreTies));



       setBoard();


    }

    public void setBoard(){
        b = new Button[4][4];
        control = new int[4][4];

        b[1][3] = (Button) findViewById(R.id.tr);
        b[1][2] = (Button) findViewById(R.id.tc);
        b[1][1] = (Button) findViewById(R.id.tl);


        b[2][3] = (Button) findViewById(R.id.mr);
        b[2][2] = (Button) findViewById(R.id.mc);
        b[2][1] = (Button) findViewById(R.id.ml);


        b[3][3] = (Button) findViewById(R.id.br);
        b[3][2] = (Button) findViewById(R.id.bc);
        b[3][1] = (Button) findViewById(R.id.bl);

        viewTurn.setText(playerTurn());

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                control[i][j] = 0;
        }
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));

                if (!b[i][j].isEnabled()) {
                       b[i][j].setText(" ");
                       b[i][j].setEnabled(true);
                    }
            }
        }
    }
    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


       public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void onClick(View view) {
            if (b[x][y].isEnabled()) {

                b[x][y].setEnabled(false);
                if(turn == 1){
                    b[x][y].setText("O");
                    control[x][y] = 1;
                    turn = 2;
                    viewTurn.setText(playerTurn());
                }
                else{
                    b[x][y].setText("X");
                    control[x][y]=2;
                    turn = 1;
                    viewTurn.setText(playerTurn());
                }
                checkBoard();
                //if(!checkBoard()){
                 //   viewTurn.setText(playerTurn());
               // }


            }
        }

    }

    public void checkBoard(){
        boolean endGame = false;
        int tieVal = 0;

        if ((control[1][1] == 1 && control[2][2] == 1 && control[3][3] == 1)
                || (control[1][3] == 1 && control[2][2] == 1 && control[3][1] == 1)
                || (control[1][2] == 1 && control[2][2] == 1 && control[3][2] == 1)
                || (control[1][3] == 1 && control[2][3] == 1 && control[3][3] == 1)
                || (control[1][1] == 1 && control[1][2] == 1 && control[1][3] == 1)
                || (control[2][1] == 1 && control[2][2] == 1 && control[2][3] == 1)
                || (control[3][1] == 1 && control[3][2] == 1 && control[3][3] == 1)
                || (control[1][1] == 1 && control[2][1] == 1 && control[3][1] == 1)) {
            viewTurn.setText(p1 + " wins!!");
            scoreOne++;
            p1Score.setText(String.valueOf(scoreOne));
            endGameNow();
            endGame = true;

        }
        if ((control[1][1] == 2 && control[2][2] == 2 && control[3][3] == 2)
                || (control[1][3] == 2 && control[2][2] == 2 && control[3][1] == 2)
                || (control[1][2] == 2 && control[2][2] == 2 && control[3][2] == 2)
                || (control[1][3] == 2 && control[2][3] == 2 && control[3][3] == 2)
                || (control[1][1] == 2 && control[1][2] == 2 && control[1][3] == 2)
                || (control[2][1] == 2 && control[2][2] == 2 && control[2][3] == 2)
                || (control[3][1] == 2 && control[3][2] == 2 && control[3][3] == 2)
                || (control[1][1] == 2 && control[2][1] == 2 && control[3][1] == 2)) {
            viewTurn.setText(p2 + " wins!!");
            scoreTwo++;
            p2Score.setText(String.valueOf(scoreTwo));
            endGameNow();
            endGame = true;
        }

        for(i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                if (control[i][j] == 1 || control[i][j] == 2) {
                    tieVal++;
                }
            }
        }
        if(tieVal == 9 && !endGame){
            endGameNow();
            endGame = true;
            viewTurn.setText("Tie!!");
            scoreTies++;
            numTies.setText(String.valueOf(scoreTies));

        }
    }

    public void endGameNow(){
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                if (b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(false);
                }
            }
        }
    }
    public String playerTurn(){
        String whoTurn;
        if(turn == 1){
            whoTurn = p1;
        } else{
            whoTurn = p2;
        }
        return whoTurn;
    }
    public void clearScores(View view){
        scoreOne = 0;
        scoreTwo = 0;
        scoreTies = 0;
        p1Score.setText(String.valueOf(scoreOne));
        p2Score.setText(String.valueOf(scoreTwo));
        numTies.setText(String.valueOf(scoreTies));
    }
    public void newGame(View view){
        setBoard();
        viewTurn.setText(playerTurn());
    }
}
