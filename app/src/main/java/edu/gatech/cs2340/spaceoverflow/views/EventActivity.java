package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.viewmodels.EventViewModel;

/**
 * Event activity
 */
public class EventActivity extends AppCompatActivity {

    private TextView eventText;
    private Button continueButton;

    private EventViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventText = findViewById(R.id.event_text);
        continueButton = findViewById(R.id.continue_warp_button);

        viewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        eventText.setText(viewModel.generateEvent());
    }

    /**
     * Continue through travel button pressed
     * @param view current View
     */
    public void onContinuePressed(View view) {
        Log.d("Event", "Continue button pressed");

        finish();
    }
}
