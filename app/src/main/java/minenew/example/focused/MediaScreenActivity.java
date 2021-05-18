package minenew.example.focused;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MediaScreenActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    private SeekBar seekbar;
    private TextView timerIn;
    private TextView soundName;
    String SoundName = "SongName";
    String SoundName1,myUrl;
    public static int oneTimeOnly = 0;

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_screen);
        Bundle extras =getIntent().getExtras();
        SoundName = extras.getString("SoundName");
        SoundName1 = SoundName.toLowerCase();
        Button backButton =findViewById(R.id.btnBack);
        switch (SoundName1){
            case "ambient":
                mediaPlayer = MediaPlayer.create(this,R.raw.sound);
                break;
            case "asmr":
                mediaPlayer = MediaPlayer.create(this,R.raw.lofi);
                break;
            case "coding":
                mediaPlayer = MediaPlayer.create(this,R.raw.lofi);
                break;
            case "farm":
                mediaPlayer = MediaPlayer.create(this,R.raw.rain);
                break;
            case "lofi":
                mediaPlayer = MediaPlayer.create(this,R.raw.lofi);
                break;
            case "meditation":
                mediaPlayer = MediaPlayer.create(this,R.raw.sound);
                break;
            case "nature":
                mediaPlayer = MediaPlayer.create(this,R.raw.rain);
                break;
            case "rain":
                mediaPlayer = MediaPlayer.create(this,R.raw.rain);
                break;
            case "summer":
                mediaPlayer = MediaPlayer.create(this,R.raw.rain);
                break;
            case "tranquility":
                mediaPlayer = MediaPlayer.create(this,R.raw.rain);
                break;
            case "waves":
                mediaPlayer = MediaPlayer.create(this,R.raw.sound);
                break;
            default:
                mediaPlayer = MediaPlayer.create(this,R.raw.lofi);
                break;
        }

        seekbar = findViewById(R.id.seekerBar);
        seekbar.setClickable(true);
        Button playButton =  findViewById(R.id.startMusic);
        Button pauseButton =  findViewById(R.id.stopMusic);
        timerIn = findViewById(R.id.tx2);
        soundName =findViewById(R.id.tx1);
        soundName.setText(SoundName);

        playButton.setOnClickListener(v -> {
            mediaPlayer.start();
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();
            if (oneTimeOnly == 0) {
                seekbar.setMax((int) finalTime);
                oneTimeOnly = 1;
            }
            mediaPlayer.setOnCompletionListener(mp -> Toast.makeText(MediaScreenActivity.this, "The Song is Over", Toast.LENGTH_SHORT).show());
            timerIn.setText(String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(UpdateSongTime,100);
        });



        pauseButton.setOnClickListener(v -> mediaPlayer.pause());

        backButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(MediaScreenActivity.this,MainActivity.class);
            startActivity(homeIntent);
            finish();
            startTime = 0;
            finalTime = 0;
        });

    }
    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            timerIn.setText(String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}