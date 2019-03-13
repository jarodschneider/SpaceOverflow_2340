package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private Player tempPlayer;

    private ConfigurationViewModel viewModel;

    private Spinner difficulty;
    private TextView name;
    private TextView skillPoints;
    private EditText fighter;
    private EditText trader;
    private EditText engineer;
    private EditText pilot;
    private Button submitButton;

    public static List<String> difficultyLevels;

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

        tempPlayer = new Player("Name", 0, 0, 0, 0);

        fighter.setText(tempPlayer.getFighterSkill().toString());
        trader.setText(tempPlayer.getTraderSkill().toString());
        engineer.setText(tempPlayer.getEngineerSkill().toString());
        pilot.setText(tempPlayer.getPilotSkill().toString());

        fighter.addTextChangedListener(skillWatcher);
        trader.addTextChangedListener(skillWatcher);
        engineer.addTextChangedListener(skillWatcher);
        pilot.addTextChangedListener(skillWatcher);

        difficultyLevels = Arrays.asList("Beginner", "Easy", "Normal", "Hard", "Impossible");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficultyLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        updateView();
    }

    public void onSubmitPressed(View view) {
        Log.d("Edit", "Submit commander pressed");

        if (viewModel.validateSkillLevels(tempPlayer)) {
            tempPlayer.setName(name.getText().toString());
            Log.d("Edit", "Player vals valid");
            viewModel.createUniverse(tempPlayer);
            Intent intent = new Intent(ConfigurationActivity.this,
                    UniverseActivity.class);
            startActivity(intent);
        }
    }

    private void updateView() {
        if (pilot.getText().toString().equals("")) {
            tempPlayer.setPilotSkill(0);
        } else {
            tempPlayer.setPilotSkill(Integer.parseInt(pilot.getText().toString()));
        }
        if (fighter.getText().toString().equals("")) {
            tempPlayer.setFighterSkill(0);
        } else {
            tempPlayer.setFighterSkill(Integer.parseInt(fighter.getText().toString()));
        }
        if (trader.getText().toString().equals("")) {
            tempPlayer.setTraderSkill(0);
        } else {
            tempPlayer.setTraderSkill(Integer.parseInt(trader.getText().toString()));
        }
        if (engineer.getText().toString().equals("")) {
            tempPlayer.setEngineerSkill(0);
        } else {
            tempPlayer.setEngineerSkill(Integer.parseInt(engineer.getText().toString()));
        }

        skillPoints.setText(viewModel.skillsRemaining(tempPlayer).toString());

        if (viewModel.validateSkillLevels(tempPlayer)) {
            submitButton.setEnabled(true);
            skillPoints.setTextColor(Color.GREEN);
        } else {
            if (viewModel.skillsRemaining(tempPlayer) < 0) {
                Toast.makeText(getApplicationContext(), "Skills cannot sum past 16",
                                Toast.LENGTH_SHORT).show();
            }
            submitButton.setEnabled(false);
            skillPoints.setTextColor(Color.RED);
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
            updateView();
        }
    };
}
