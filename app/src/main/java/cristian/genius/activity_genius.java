package cristian.genius;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.os.Handler;

public class activity_genius extends AppCompatActivity {

    SequenceManager sequenceManager;
    MediaPlayer buttonBlueSong;
    MediaPlayer buttonRedSong;
    MediaPlayer buttonYellowSong;
    MediaPlayer buttonGreenSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genius);

        initializeSongs();
        enableButtons(false);

        sequenceManager = new SequenceManager();
        sequenceManager.nextStep();

        final Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonBlueSong.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manager(GeniusEnums.BUTTON_BLUE);
                }
                return true;
            }
        });

        final Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonRedSong.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manager(GeniusEnums.BUTTON_RED);
                }
                return true;
            }
        });

        final Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonGreenSong.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manager(GeniusEnums.BUTTON_GREEN);
                }
                return true;
            }
        });

        final Button buttonYellow = findViewById(R.id.buttonYellow);
        buttonYellow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonYellowSong.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manager(GeniusEnums.BUTTON_YELLOW);
                }
                return true;
            }
        });

        final Button initGameButton = findViewById(R.id.initGameButton);
        initGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSequence();
                enableButtons(true);
            }
        });
        Button backToMenu = findViewById(R.id.buttonLeaveGame);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_genius.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isRightStep(GeniusEnums button)
    {
        boolean isRight = false;
        GeniusEnums currentStep = this.sequenceManager.getCurrentStep();

        if (button == currentStep) {
            isRight = true;
        }

        return isRight;
    }

    private void manager(GeniusEnums button) {
        if (isRightStep(button)) {
            boolean showSequence = sequenceManager.isLastStep();

            sequenceManager.nextStep();

            if (showSequence) {
                enableButtons(false);
                showSequence();
                enableButtons(true);
            }
        } else {
            Intent intent = new Intent(activity_genius.this, ScoreActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", sequenceManager.getSequenceSize());
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
    }

    private void showSequence() {
        int sequenceSize = sequenceManager.getSequenceSize();

        Handler h = new Handler();

        long delay = 1000;
        for (int i = 0; i < sequenceSize; i++) {
            final int index = i;
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    turnOnButton(sequenceManager.getStep(index));
                }
            }, delay);

            delay += 1000;

            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    turnOffButton(sequenceManager.getStep(index));
                }
            }, delay);
            delay += 1000;
        }
    }

    private void turnOnButton(GeniusEnums button)
    {
        final GeniusEnums buttonToTurnOn = button;

        String buttonName = buttonToTurnOn.name();

        if (buttonName == "BUTTON_BLUE") {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(76, 169, 250));
        } else if (buttonName == "BUTTON_RED") {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(215, 89, 85));
        } else if (buttonName == "BUTTON_GREEN") {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(135, 238, 83));
        } else if (buttonName == "BUTTON_YELLOW") {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(235, 246, 131));
        }
    }

    private void turnOffButton(GeniusEnums button) {
        String buttonName = button.name();

        if (buttonName == "BUTTON_BLUE") {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(0,0,128));
        } else if (buttonName == "BUTTON_RED") {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(233,1,1));
        } else if (buttonName == "BUTTON_GREEN") {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(11,97,11));
        } else if (buttonName == "BUTTON_YELLOW") {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(207,200,2));
        }
    }

    private void enableButtons(boolean disable) {
        Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setEnabled(disable);

        Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setEnabled(disable);

        Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setEnabled(disable);

        Button buttonYellow = findViewById(R.id.buttonYellow);
        buttonYellow.setEnabled(disable);
    }

    private void initializeSongs() {
        buttonBlueSong = MediaPlayer.create(this, R.raw.sword_hit);

        buttonGreenSong = MediaPlayer.create(this, R.raw.sword_whip);

        buttonYellowSong = MediaPlayer.create(this, R.raw.frog);

        buttonRedSong = MediaPlayer.create(this, R.raw.meeow);
    }
}