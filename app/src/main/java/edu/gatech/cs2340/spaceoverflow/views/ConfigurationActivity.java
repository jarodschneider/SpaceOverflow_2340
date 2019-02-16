package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Spinner difficulty = (Spinner) findViewById(R.id.difficulty_spinner);
        TextView name = (TextView) findViewById(R.id.commander_name_input);

        Number fighter = (Number) findViewById(R.id.fighter);
        Number trader = (Number) findViewById(R.id.trader);
        Number engineer = (Number) findViewById(R.id.engineer);
        Number pilot = (Number) findViewById(R.id.pilot);


    }

    public void submit() {
        viewModel = new ConfigurationViewModel(this);

        if (!viewModel.validateSkillLevels(pilot.intValue(), fighter.intValue(), trader.intValue(), engineer.intValue())) {

        } else {
            viewModel.createPlayer(pilot.intValue(), fighter.intValue(), trader.intValue(), engineer.intValue());
        }
    }


}
