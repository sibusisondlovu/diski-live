package za.co.jaspa.diskilive.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import za.co.jaspa.diskilive.MainActivity;
import za.co.jaspa.diskilive.R;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "signUpActivity";
    private Button btnVerify;
    private CheckBox cbTerms;
    private EditText etCellNumber, etNickName, etFullNames;
    private String strNickname, strFullNames, strCellNumber;
    private ProgressDialog progressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        initViews();
    }

    public void closeActivity(View view) {
        finish();
    }

    private void initViews(){
        btnVerify = findViewById(R.id.sign_up_screen_btnContinue);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInputs();
            }
        });
        cbTerms = findViewById(R.id.sign_up_screen_cbTerms);
        etCellNumber = findViewById(R.id.sign_up_screen_etCellphoneNumber);
        etNickName = findViewById(R.id.sign_up_screen_etNickname);
        etFullNames = findViewById(R.id.sign_up_screen_etFullNames);
    }

    private void checkInputs() {

        if (TextUtils.isEmpty(etFullNames.getText())) {
            etFullNames.setError("Your full name is required");
            return;
        }else{
            strFullNames = etFullNames.getText().toString().trim();
        }

        if (etFullNames.getText().length() < 4) {
            etFullNames.setError("Your full name is too short");
            return;
        }else{
            strFullNames = etFullNames.getText().toString().trim();
        }

        if (TextUtils.isEmpty(etNickName.getText())) {
            etNickName.setError("Your nickname is required");
            return;
        }else{
            strNickname = etNickName.getText().toString().trim();
        }

        if (etNickName.getText().length() < 5) {
            etNickName.setError("Your nickname is too short");
            return;
        }else{
            strNickname = etNickName.getText().toString().trim();
        }

        if (TextUtils.isEmpty(etCellNumber.getText())) {
            etCellNumber.setError("Cell number is required");
            return;
        }else{
            strCellNumber = etCellNumber.getText().toString().trim();
        }

        if (etCellNumber.getText().length() < 10) {
            etCellNumber.setError("Invalid cellphone number");
            return;
        }else{
            strCellNumber = etCellNumber.getText().toString().trim();
        }

        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Accept terms and conditions to continue", Toast.LENGTH_SHORT).show();
            return;
        }

        //passDataToNextActivity();
        signUpWithEmailAndPassword();
    }

    private void signUpWithEmailAndPassword() {

        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please wait while we create your account");
        progressDialog.show();

        Log.d(TAG, "signUpWithEmailAndPassword: About to call sign up code");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword("strCellNumber@diskilive.co.za", "1qazxsw2#EDC")
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "onSuccess: Successful created and account");
                        FirebaseUser user = authResult.getUser();
                        if(user != null){
                            Log.d(TAG, "onSuccess: Creating firebase user profile");
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                                              .setDisplayName(strFullNames)
                                                                              .build();
                            user.updateProfile(profileUpdates);

                        }

                        // add user to firestore
                        createUserOnFirestore(authResult);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        showErrorMessage("Authentication Error",e.getMessage());
                    }
                });
    }

    private void createUserOnFirestore(AuthResult authResult) {
        Log.d(TAG, "createUserOnFirestore: Creating user called");
        progressDialog.setMessage("Setting up your profile....");
        Map<String, Object> user = new HashMap<>();
        user.put("uid",authResult.getUser().getUid());
        user.put("fullNames", strFullNames);
        user.put("nickName", strNickname);
        user.put("cellNumber", strCellNumber);
        user.put("emailAddress", null);
        user.put("points", 100);
        user.put("team", null);
        user.put("rating", 1);
        user.put("rank", "BLUE");
        user.put("avatar", null);
        user.put("createdAt", new Date().toString());
        user.put("followers", 0);
        user.put("following", 0);
        user.put("sharesCount", 0);

        FirebaseFirestore.getInstance().collection("fans")
                .document(authResult.getUser().getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Successful created user on database");
                         progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Account created successfully",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        showErrorMessage("Authentication Error", e.getMessage());
                    }
                });
    }


    private void showErrorMessage(String code, String message) {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error_message, viewGroup,
                false);

        final TextView tvErrorCode =
                dialogView.findViewById(R.id.dialog_error_message_code);

        tvErrorCode.setText(code);

        final TextView tvErrorMessage =
                dialogView.findViewById(R.id.dialog_error_message_body);

        tvErrorMessage.setText(message);

        final Button btnDismiss = dialogView.findViewById(R.id.dialog_error_btnDismiss);



        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                finish();
            }
        });

        alertDialog.show();

    }
}