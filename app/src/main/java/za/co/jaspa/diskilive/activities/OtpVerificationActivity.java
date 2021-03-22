package za.co.jaspa.diskilive.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import za.co.jaspa.diskilive.MainActivity;
import za.co.jaspa.diskilive.R;

public class OtpVerificationActivity extends AppCompatActivity {

    private static final String TAG ="OTPVerification" ;
    private String verificationCodeBySystem, strFullName, strNickName, strCellNumber;
    private ProgressBar progressBar;
    private TextView tvMessage, tvNotice;
    private Button btnVerify;
    private EditText etOtp;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        initViews();

        Bundle extras = getIntent().getExtras();
        strFullName = extras.getString("FULL_NAMES");
        strNickName = extras.getString("NICK_NAME");
        strCellNumber = extras.getString("CELL_NUMBER");

        tvNotice.setText("An authentication code has been sent \nto 27"+strCellNumber.substring(1, 10) );
        sendVerificationCodeToUser(strCellNumber.substring(1, 10));
    }

    private void initViews() {

        tvNotice = findViewById(R.id.activity_otp_verification_tvNotice);

        etOtp = findViewById(R.id.activity_opt_verification_etVerificationCode);
        etOtp.setEnabled(false);

        progressBar = findViewById(R.id.activity_opt_verification_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        tvMessage  = findViewById(R.id.activity_opt_verification_tvMessage);
        tvMessage.setVisibility(View.GONE);

        btnVerify =findViewById(R.id.activity_opt_verification_btnVerify);
        btnVerify.setEnabled(false);
    }

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpVerificationActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    showErrorMessage("Authentication Error",
                                            task.getException().getMessage());
                                }
                            }
                        });
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+27" + phoneNo,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,   // Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    //Get the code in global variable
                    verificationCodeBySystem = s;
                    progressBar.setVisibility(View.GONE);
                    etOtp.setEnabled(true);
                }

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                    progressBar.setVisibility(View.GONE);
                    showErrorMessage("Time Out", "Could not retrieve OTP due to network error");
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(code);
                    }else{
                        showErrorMessage("Error", "Could not complete verification at this time");
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    progressBar.setVisibility(View.GONE);
                    showErrorMessage("Error", e.getMessage());
                }


            };

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