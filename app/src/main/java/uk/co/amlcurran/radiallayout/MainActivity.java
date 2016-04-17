package uk.co.amlcurran.radiallayout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final RadialLayout radialLayout = (RadialLayout) findViewById(R.id.radial_layout);
        radialLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator angleOffset = ObjectAnimator.ofInt(radialLayout, "angleOffset", radialLayout.getAngleOffset(), radialLayout.getAngleOffset() + 90);
                angleOffset.setInterpolator(new FastOutSlowInInterpolator());
                angleOffset.start();
            }
        });
    }
}
