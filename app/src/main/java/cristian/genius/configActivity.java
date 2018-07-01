package cristian.genius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;

public class configActivity extends AppCompatActivity {

    FileHandler fileHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        fileHandler = new FileHandler();

        try {
            fileHandler.getSongs();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
