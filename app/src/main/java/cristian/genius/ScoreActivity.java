package cristian.genius;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private TextView myScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle b = getIntent().getExtras();
        int value = -1;
        if(b != null)
            value = b.getInt("score");
        value=value-1;

        myScore = (TextView) findViewById(R.id.Score);
        myScore.setText(String.valueOf(value));

       // fillScoresFields();

        Button backToMenu = findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fillScoresFields() {
        FileHandler fileHandler = new FileHandler();
        List<Player> players;
        try {
            players = fileHandler.getPlayers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int numOfPlayers = players.size();

        if (numOfPlayers > 0) {
            Player player;
            player = players.get(0);
            TextView name1 = findViewById(R.id.Player1);
            name1.setText(player.getName());
            TextView score1 = findViewById(R.id.PontosP1);
            score1.setText(String.valueOf(player.getScore()));

            if (numOfPlayers >= 2) {
                player = players.get(1);
                TextView name2 = findViewById(R.id.Player2);
                name2.setText(player.getName());
                TextView score2 = findViewById(R.id.PontosP2);
                score2.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 3) {
                player = players.get(2);
                TextView name3 = findViewById(R.id.Player3);
                name3.setText(player.getName());
                TextView score3 = findViewById(R.id.pontosP3);
                score3.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 4) {
                player = players.get(3);
                TextView name4 = findViewById(R.id.Player4);
                name4.setText(player.getName());
                TextView score4 = findViewById(R.id.pontosP4);
                score4.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 5) {
                player = players.get(4);
                TextView name5 = findViewById(R.id.Player5);
                name5.setText(player.getName());
                TextView score5 = findViewById(R.id.pontosP5);
                score5.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 6) {
                player = players.get(5);
                TextView name6 = findViewById(R.id.Player6);
                name6.setText(player.getName());
                TextView score6 = findViewById(R.id.pontosP6);
                score6.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 7) {
                player = players.get(6);
                TextView name7 = findViewById(R.id.Player7);
                name7.setText(player.getName());
                TextView score7 = findViewById(R.id.pontosP7);
                score7.setText(String.valueOf(player.getScore()));
            }

            if (numOfPlayers >= 8) {
                player = players.get(7);
                TextView name8 = findViewById(R.id.Player7);
                name8.setText(player.getName());
                TextView score8 = findViewById(R.id.pontosP7);
                score8.setText(String.valueOf(player.getScore()));
            }

        }
    }
}
