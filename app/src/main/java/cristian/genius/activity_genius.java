package cristian.genius;

import android.graphics.Color;
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
                manager(GeniusEnums.BUTTON_BLUE);
            }
        });

        final Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(GeniusEnums.BUTTON_RED);
            }
        });

        final Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(GeniusEnums.BUTTON_GREEN);
            }
        });

        final Button buttonYellow = findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager(GeniusEnums.BUTTON_YELLOW);
            }
        });
        this.showSequence();
    }

    private boolean isRightStep(GeniusEnums button) {
        boolean isRight = false;
        GeniusEnums currentStep = this.sequenceManager.getCurrentStep();

        if(button == currentStep)
        {
            isRight = true;
        }

        return isRight;
    }

    private void manager(GeniusEnums button)
    {
        if(isRightStep(button))
        {
            sequenceManager.nextStep();
            this.showSequence();
        }
        else
        {
            //chamar a activity que mostra o score da partida
        }
    }

    private void showSequence()
    {
        int sequenceSize = sequenceManager.getSequenceSize();
        for(int i = 0; i < sequenceSize; i++)
        {
            turnOnButton(sequenceManager.getStep(i));
            //timer aqui
            turOffButton(sequenceManager.getStep(i));
        }
    }

    private void turnOnButton(GeniusEnums button) {

        String buttonName = button.name();

        if(buttonName == "BUTTON_BLUE")
        {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(76,169,250));
        }
        else  if(buttonName == "BUTTON_RED")
        {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(215,89,85));
        }
        else if(buttonName == "BUTTON_GREEN")
        {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(135,238,83));
        }
        else if(buttonName == "BUTTON_YELLOW")
        {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(235,246,131));
        }
        //fazer um botão acender a luz aqui
    }

    private void turOffButton(GeniusEnums button)
    {
        String buttonName = button.name();

        if(buttonName == "BUTTON_BLUE")
        {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(255,255,255));
        }
        else  if(buttonName == "BUTTON_RED")
        {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if(buttonName == "BUTTON_GREEN")
        {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if(buttonName == "BUTTON_YELLOW")
        {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(255,255,255));
        }
    }
}
