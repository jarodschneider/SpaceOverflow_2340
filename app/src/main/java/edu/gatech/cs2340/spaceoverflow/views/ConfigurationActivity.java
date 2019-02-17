package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import org.w3c.dom.Text;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    Spinner difficulty;
    TextView name;
    TextView skillPoints;
    EditText fighter;
    int sFighter;
    EditText trader;
    int sTrader;
    EditText engineer;
    int sEngineer;
    EditText pilot;
    int sPilot;

    private Player player;

    Button submitButton;

    Integer sPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        difficulty = findViewById(R.id.difficulty_spinner);
        name = findViewById(R.id.commander_name_input);
        skillPoints = findViewById(R.id.skill_points);

        fighter = findViewById(R.id.edit_fighter);
        trader = findViewById(R.id.edit_trader);
        engineer = findViewById(R.id.edit_engineer);
        pilot = findViewById(R.id.edit_pilot);
        submitButton = findViewById(R.id.submit_configuration_button);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

        submitButton.setEnabled(false);

        fighter.setText("0");
        trader.setText("0");
        engineer.setText("0");
        pilot.setText("0");

        fighter.addTextChangedListener(skillWatcher);
        trader.addTextChangedListener(skillWatcher);
        engineer.addTextChangedListener(skillWatcher);
        pilot.addTextChangedListener(skillWatcher);

        computeSkills();
    }

    public void onSubmitPressed(View view) {
        Log.d("Edit", "Submit commander pressed");

        computeSkills();

        if (viewModel.validateSkillLevels(sPilot, sFighter, sTrader, sEngineer)) {
            Log.d("Edit", "Player vals valid");
            viewModel.createPlayer(name.getText().toString(), sPilot, sFighter, sTrader, sEngineer);
        }
    }

    private void computeSkills() {
        if (pilot.getText().toString().equals("")) {
            pilot.setText("0");
        }
        if (fighter.getText().toString().equals("")) {
            fighter.setText("0");
        }
        if (trader.getText().toString().equals("")) {
            trader.setText("0");
        }
        if (engineer.getText().toString().equals("")) {
            engineer.setText("0");
        }

        sPilot = Integer.parseInt(pilot.getText().toString());
        sFighter = Integer.parseInt(fighter.getText().toString());
        sTrader = Integer.parseInt(trader.getText().toString());
        sEngineer = Integer.parseInt(engineer.getText().toString());

        if (sPilot + sFighter + sTrader + sEngineer <= 16) {
            sPoints = 16 - (sPilot + sFighter + sTrader + sEngineer);
            skillPoints.setText(sPoints.toString());
            if (sPoints == 0) {
                submitButton.setEnabled(true);
                skillPoints.setTextColor(Color.GREEN);
            } else {
                submitButton.setEnabled(false);
                skillPoints.setTextColor(Color.RED);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Skills cannot sum greater than 16", Toast.LENGTH_SHORT).show();
        }
    }

    private final TextWatcher skillWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() != 0) {
                computeSkills();
            }
        }
    };
}
