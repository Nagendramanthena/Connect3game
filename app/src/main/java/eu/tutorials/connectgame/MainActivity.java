package eu.tutorials.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;

    int gamestate[] = {2,2,2,2,2,2,2,2,2};
    int winningstates[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive = true;

    public void DropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int pos = Integer.parseInt(counter.getTag().toString());

        if(gamestate[pos] == 2 && gameactive) {
            gamestate[pos] = activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.pmon);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.doremon);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int wp[] : winningstates) {
                if (gamestate[wp[0]] == gamestate[wp[1]] && gamestate[wp[1]] == gamestate[wp[2]] && gamestate[wp[0]] != 2) {
                    int playerwon = 1 - activeplayer;
                    String winner = "";
                    if (playerwon == 0) winner = "pokemon";
                    else winner = "doremon";

                    String ss = winner + " has " + " won";
                    gameactive = false;

                    Button pab = (Button) findViewById(R.id.button);
                    TextView tview = (TextView) findViewById(R.id.textView);

                    tview.setText(ss);

                    pab.setVisibility(View.VISIBLE);
                    tview.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgainButton(View view){
        Button pab = (Button) findViewById(R.id.button);
        TextView tview = (TextView) findViewById(R.id.textView);




        Log.i("Info","buttonpressed");

        pab.setVisibility(View.INVISIBLE);
        tview.setVisibility(View.INVISIBLE);

        GridLayout gl = (GridLayout) findViewById(R.id.GridLayout);

        for(int i=0;i<gl.getChildCount();i++){
            ImageView counter = (ImageView) gl.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gamestate,2);
        activeplayer = 0;
        gameactive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}