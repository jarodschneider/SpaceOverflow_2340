package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    Spinner difficulty;
    TextView name;
    EditText fighter;
    EditText trader;
    EditText engineer;
    EditText pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        difficulty = findViewById(R.id.difficulty_spinner);
        name = findViewById(R.id.commander_name_input);

        fighter = findViewById(R.id.edit_fighter);
        trader = findViewById(R.id.edit_trader);
        engineer = findViewById(R.id.edit_engineer);
        pilot = findViewById(R.id.edit_pilot);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    public void submit() {
        int sPilot = Integer.parseInt(pilot.getText().toString());
        int sFighter = Integer.parseInt(pilot.getText().toString());
        int sTrader = Integer.parseInt(pilot.getText().toString());
        int sEngineer = Integer.parseInt(pilot.getText().toString());

        if (viewModel.validateSkillLevels(sPilot, sFighter, sTrader, sEngineer)) {
            viewModel.createPlayer(name.getText().toString(), sPilot, sFighter, sTrader, sEngineer);
        }
    }


}
