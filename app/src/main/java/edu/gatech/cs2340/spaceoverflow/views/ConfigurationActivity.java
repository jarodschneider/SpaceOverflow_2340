package edu.gatech.cs2340.spaceoverflow.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }
}
