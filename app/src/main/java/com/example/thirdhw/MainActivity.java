package com.example.thirdhw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newChips((CustomView) findViewById(R.id.first), 10);
    }

    private void newChips(CustomView customView, int chips) {
        for (int i = 0; i < chips; i++) {
            Chip chip = new Chip(this, null, R.style.Widget_MaterialComponents_Chip_Entry);
            chip.setCheckable(false);
            chip.setText(getString(R.string.chipName, i));
            customView.addView(chip);
        }
    }
}
