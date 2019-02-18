package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
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
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    private Spinner difficulty;
    private TextView name;
    private TextView skillPoints;
    private EditText fighter;
    private EditText trader;
    private EditText engineer;
    private EditText pilot;
    private Button submitButton;

    private Player player;

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

        player = new Player(name.getText().toString(), 0, 0, 0,
                0);

        fighter.setText(player.getFighterSkill().toString());
        trader.setText(player.getTraderSkill().toString());
        engineer.setText(player.getEngineerSkill().toString());
        pilot.setText(player.getPilotSkill().toString());

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

        if (viewModel.validateSkillLevels(player)) {
            Log.d("Edit", "Player vals valid");
            viewModel.createPlayer(player);
        }
    }

    private void updateView() {
        if (pilot.getText().toString().equals("")) {
            player.setPilotSkill(0);
        } else {
            player.setPilotSkill(Integer.parseInt(pilot.getText().toString()));
        }
        if (fighter.getText().toString().equals("")) {
            player.setFighterSkill(0);
        } else {
            player.setFighterSkill(Integer.parseInt(fighter.getText().toString()));
        }
        if (trader.getText().toString().equals("")) {
            player.setTraderSkill(0);
        } else {
            player.setTraderSkill(Integer.parseInt(trader.getText().toString()));
        }
        if (engineer.getText().toString().equals("")) {
            player.setEngineerSkill(0);
        } else {
            player.setEngineerSkill(Integer.parseInt(engineer.getText().toString()));
        }

        skillPoints.setText(viewModel.skillsRemaining(player).toString());

        if (viewModel.validateSkillLevels(player)) {
            submitButton.setEnabled(true);
            skillPoints.setTextColor(Color.GREEN);
        } else {
            if (viewModel.skillsRemaining(player) < 0) {
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
