package za.co.jaspa.diskilive.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import za.co.jaspa.diskilive.R;

public class PostFanStoryActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private ImageButton ibVideo,ibPhoto;
    private TextInputLayout tilTitle,tilStory;
    private Button btnSubmitStory ;

    private String strTitle, strStory;
    private String strMediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fan_story);

        db = FirebaseFirestore.getInstance();

        ibVideo = findViewById(R.id.activity_post_fan_story_ivVideo);
        ibPhoto = findViewById(R.id.activity_post_fan_story_ivPhoto);
        tilTitle = findViewById(R.id.activity_post_fan_story_tilTitle);
        tilStory = findViewById(R.id.activity_post_fan_story_titlStory);
        btnSubmitStory = findViewById(R.id.activity_post_fan_story_btnSubmit);

        ibVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMediaType = "VIDEO";
            }
        });

        ibPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMediaType = "PHOTO";
            }
        });

        btnSubmitStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });

    }

    private void checkInputs() {
        if (TextUtils.isEmpty(tilTitle.getEditText().getText())) {
            tilTitle.setError("Title cannot be empty");
            return;
        }else{
            strTitle = tilTitle.getEditText().getText().toString().trim();
        }

        if (tilTitle.getEditText().getText().length() < 5) {
            tilTitle.setError("Title cannot be less then 5 characters");
            return;
        }else{
            strTitle = tilTitle.getEditText().getText().toString().trim();
        }

        if (TextUtils.isEmpty(tilStory.getEditText().getText())) {
            tilStory.setError("Story cannot be empty");
            return;
        }else{
            strStory = tilStory.getEditText().getText().toString().trim();
        }

        if (tilStory.getEditText().getText().length() < 20) {
            tilStory.setError("Story cannot be less then 20 characters");
            return;
        }else{
            strStory = tilStory.getEditText().getText().toString().trim();
        }

        if (strMediaType == null) {
            Toast.makeText(this, "Please upload video or an image for your story", Toast.LENGTH_SHORT).show();
            return;
        }

        saveToFirebase();
    }

    private void saveToFirebase() {

        DocumentReference document = db.collection("fan_stories").document();

        // Create a new user with a first and last name
        Map<String, Object> fanStory = new HashMap<>();
        fanStory.put("uid", document.getId());
        fanStory.put("title", strTitle);
        fanStory.put("body", strStory);
        fanStory.put("media", "https://www.youtube.com/watch?v=d1-QNhcGsq0");
        fanStory.put("mediaType", strMediaType);
        fanStory.put("fanUid", "1qazxsw23edc");
        fanStory.put("fanUsername", "Sibusiso N");
        fanStory.put("fanAvatar", "http://www.rottmair.de/profiles/Sebastian_Rottmair.jpg");
        fanStory.put("createdAt", new Date().toString());
        fanStory.put("commentsCount", 0);
        fanStory.put("downVotesCount", 0);
        fanStory.put("sharesCount", 0);

        db.collection("fan_stories")
                .document(document.getId())
                .set(fanStory)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //TODO add success dialog with option to ask or go to next strot
                        Toast.makeText(PostFanStoryActivity.this, "Your story has been posted successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostFanStoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}