package cristian.genius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_genius extends AppCompatActivity {

    SequenceManager sequenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genius);

        sequenceManager = new SequenceManager();
        sequenceManager.nextStep();

        final Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chamar método que compara id do botão com o passo atual da lista
                //se correto, gera proximo passo, senão, fim de jogo
                manager(buttonBlue.getId());
            }
        });

        final Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(buttonRed.getId());
            }
        });

        final Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(buttonGreen.getId());
            }
        });

        final Button buttonYellow = findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(buttonYellow.getId());
            }
        });
    }

    private boolean isRightStep(int buttonId) {
        boolean isRight = false;
        int currentStep = this.sequenceManager.getCurrentStep();

        if(buttonId == currentStep)
        {
            isRight = true;
        }

        return isRight;
    }

    private void manager(int buttonId)
    {
        if(isRightStep(buttonId))
        {
            sequenceManager.nextStep();
        }
        else
        {
            //chamar a activity que mostra o score da partida
        }
    }
}
