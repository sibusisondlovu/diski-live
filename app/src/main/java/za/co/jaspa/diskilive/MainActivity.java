package za.co.jaspa.diskilive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import za.co.jaspa.diskilive.fragments.FanStoriesFragment;
import za.co.jaspa.diskilive.fragments.MatchCenterFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottom_navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new FanStoriesFragment()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {

                case R.id.bm_fan_stories:
                    fragment = new FanStoriesFragment();
                    break;
                case R.id.bm_match_center:
                    fragment = new MatchCenterFragment();
                    break;

                case R.id.bm_find_fans:
                    fragment = new FanStoriesFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

            return true;
        }
    };
}