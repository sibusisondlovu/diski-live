package za.co.jaspa.diskilive.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import za.co.jaspa.diskilive.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFullNames, tvNickName, tvPoints, tvFollowing, tvFollowers;
    private LinearLayout llTeam;
    private Button btnAddTeam;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        getUserDetails();
    }

    private void initViews() {
        tvFullNames = findViewById(R.id.activity_profile_tvFullNames);
        tvNickName = findViewById(R.id.activity_profile_tvNickname);
        tvPoints = findViewById(R.id.activity_profile_tvPoints);
        tvFollowers = findViewById(R.id.activity_profile_tvFollowers);
        tvFollowing = findViewById(R.id.activity_profile_tvFollowing);
        llTeam = findViewById(R.id.activity_profile_team);
        btnAddTeam = findViewById(R.id.activity_profile_btnAddTeam);
    }

    public void closeActivity(View view) {
        finish();
    }


    private void getUserDetails(){

        Source source = Source.CACHE;

        FirebaseFirestore.getInstance().collection("fans")
                .document(FirebaseAuth.getInstance().getUid())
                .get(source)
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        tvFullNames.setText(documentSnapshot.getString("fullNames"));
                        tvNickName.setText(documentSnapshot.getString("nickName"));
                        tvPoints.setText(documentSnapshot.get("points").toString());
                        tvFollowers.setText(documentSnapshot.get("followers").toString());
                        tvFollowing.setText(documentSnapshot.get("following").toString());

                        if (documentSnapshot.get("team")== null) {
                            btnAddTeam.setVisibility(View.VISIBLE);
                            llTeam.setVisibility(View.GONE);
                        }else{
                            llTeam.setVisibility(View.VISIBLE);
                            btnAddTeam.setVisibility(View.GONE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}