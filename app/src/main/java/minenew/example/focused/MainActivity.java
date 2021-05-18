package minenew.example.focused;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.Time;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputEditText etvTimer_Init;
    ChipGroup chipGroupHere;
    Chip chip;
    Button btnLetsGo;
    Editable timer;
    String soundName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etvTimer_Init = findViewById(R.id.etvTimerInit);
        timer = etvTimer_Init.getText();
        chipGroupHere = findViewById(R.id.chipGroupHere);
        btnLetsGo = findViewById(R.id.btnLetsGo);


        chipGroupHere.setOnCheckedChangeListener((group, checkedId) -> {
            chip = chipGroupHere.findViewById(checkedId);
            if(chip != null){
                soundName = chip.getText().toString();
            }
            else{
                Toast.makeText(getApplicationContext(),"Select the sound",Toast.LENGTH_SHORT).show();
            }

        });

        btnLetsGo.setOnClickListener(v ->{
            if(!timer.toString().isEmpty() && !soundName.isEmpty()){
                Toast.makeText(getApplicationContext(),"Let's Go we are fouced with sound "+soundName+" for "+timer.toString(),Toast.LENGTH_SHORT).show();
                Intent mediaIntent = new Intent(MainActivity.this,MediaScreenActivity.class);
                mediaIntent.putExtra("SoundName",soundName);
                startActivity(mediaIntent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Select the sound and input time",Toast.LENGTH_SHORT).show();

            }


        });





    }

}