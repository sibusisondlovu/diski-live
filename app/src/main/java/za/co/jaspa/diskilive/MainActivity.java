package za.co.jaspa.diskilive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import za.co.jaspa.diskilive.activities.PostFanStoryActivity;
import za.co.jaspa.diskilive.activities.ProfileActivity;
import za.co.jaspa.diskilive.activities.WelcomeActivity;
import za.co.jaspa.diskilive.fragments.FanStoriesFragment;
import za.co.jaspa.diskilive.fragments.FindFansFragment;
import za.co.jaspa.diskilive.fragments.MatchCenterFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

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
                    fragment = new FindFansFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

            return true;
        }
    };

    public void openPostStoryActivity(View view) {

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this, PostFanStoryActivity.class);
            startActivity(intent);
        }

    }

    public void openProfileScreen(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}