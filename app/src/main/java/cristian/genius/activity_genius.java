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
import android.widget.Switch;

public class activity_genius extends AppCompatActivity {

    SequenceManager sequenceManager;
    boolean songMuted;
    MediaPlayer buttonBlueSong;
    MediaPlayer buttonRedSong;
    MediaPlayer buttonYellowSong;
    MediaPlayer buttonGreenSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genius);

        songMuted = false;
        initializeSongs();
        enableButtons(false);

        sequenceManager = new SequenceManager();
        sequenceManager.nextStep();

        turnOffButton(GeniusEnums.BUTTON_GREEN);
        turnOffButton(GeniusEnums.BUTTON_RED);
        turnOffButton(GeniusEnums.BUTTON_YELLOW);
        turnOffButton(GeniusEnums.BUTTON_BLUE);

        final Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    turnOnButton(GeniusEnums.BUTTON_BLUE);
                    if(!songMuted) {
                        if(buttonBlueSong.isPlaying())
                        {
                            buttonBlueSong.stop();
                        }
                        buttonBlueSong.start();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    turnOffButton(GeniusEnums.BUTTON_BLUE);
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
                    turnOnButton(GeniusEnums.BUTTON_RED);
                    if(!songMuted) {
                        if(buttonRedSong.isPlaying())
                        {
                            buttonRedSong.stop();
                        }
                        buttonRedSong.start();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    turnOffButton(GeniusEnums.BUTTON_RED);
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
                    turnOnButton(GeniusEnums.BUTTON_GREEN);
                    if(!songMuted) {
                        if(buttonGreenSong.isPlaying()) {
                            buttonGreenSong.stop();
                        }
                        buttonGreenSong.start();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    turnOffButton(GeniusEnums.BUTTON_GREEN);
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
                    turnOnButton(GeniusEnums.BUTTON_YELLOW);
                    if(!songMuted) {
                        if(buttonYellowSong.isPlaying()) {
                           buttonYellowSong.stop();
                        }
                        buttonYellowSong.start();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    turnOffButton(GeniusEnums.BUTTON_YELLOW);
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
                initGameButton.setEnabled(false);
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

        final Switch muteButton = findViewById(R.id.muteButton);
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songMuted = muteButton.isChecked();
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

        long delay = 500;
        for (int i = 0; i < sequenceSize; i++) {
            final int index = i;
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    turnOnButton(sequenceManager.getStep(index));
                }
            }, delay);

            delay += 500;

            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    turnOffButton(sequenceManager.getStep(index));
                }
            }, delay);
            delay += 500;
        }
    }

    private void turnOnButton(GeniusEnums button)
    {
        String buttonName = button.name();

        if (buttonName == "BUTTON_BLUE") {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(0, 0, 255));
        } else if (buttonName == "BUTTON_RED") {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(255, 0, 0));
        } else if (buttonName == "BUTTON_GREEN") {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(0, 255, 0));
        } else if (buttonName == "BUTTON_YELLOW") {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(247, 254, 46));
        }
    }

    private void turnOffButton(GeniusEnums button) {
        String buttonName = button.name();

        if (buttonName == "BUTTON_BLUE") {
            final Button buttonBlue = findViewById(R.id.buttonBlue);
            buttonBlue.setBackgroundColor(Color.rgb(8,8,138));
        } else if (buttonName == "BUTTON_RED") {
            final Button buttonRed = findViewById(R.id.buttonRed);
            buttonRed.setBackgroundColor(Color.rgb(138,8,8));
        } else if (buttonName == "BUTTON_GREEN") {
            final Button buttonGreen = findViewById(R.id.buttonGreen);
            buttonGreen.setBackgroundColor(Color.rgb(8,138,8));
        } else if (buttonName == "BUTTON_YELLOW") {
            final Button buttonYellow = findViewById(R.id.buttonYellow);
            buttonYellow.setBackgroundColor(Color.rgb(134,138,8));
        }
    }

    private void enableButtons(boolean enabled) {
        Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setEnabled(enabled);

        Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setEnabled(enabled);

        Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setEnabled(enabled);

        Button buttonYellow = findViewById(R.id.buttonYellow);
        buttonYellow.setEnabled(enabled);
    }

    private void initializeSongs() {
        buttonBlueSong = MediaPlayer.create(this, R.raw.sword_hit);

        buttonGreenSong = MediaPlayer.create(this, R.raw.sword_whip);

        buttonYellowSong = MediaPlayer.create(this, R.raw.frog);

        buttonRedSong = MediaPlayer.create(this, R.raw.meeow);
    }
}