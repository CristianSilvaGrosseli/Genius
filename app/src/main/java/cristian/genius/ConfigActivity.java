package cristian.genius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.List;

public class ConfigActivity extends AppCompatActivity {

    FileHandler fileHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        fileHandler = new FileHandler();
        List<Player> players;
        try {
            players = fileHandler.getPlayers();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
