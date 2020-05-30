package com.disablity.app.ui.sigup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Activity;
import android.util.Patterns;

import com.disablity.app.data.LoginRepository;
import com.disablity.app.data.Result;
import com.disablity.app.data.model.LoggedInUser;
import com.disablity.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel
{

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    public MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private FirebaseAuth mAuth;

    LoginViewModel()
    {
        mAuth = FirebaseAuth.getInstance();
    }

    LoginViewModel(LoginRepository loginRepository)
    {
        this();
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState()
    {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult()
    {
        return loginResult;
    }

    public void login(String username, String password, Activity activity)
    {
        // can be launched in a separate asynchronous job
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginResult.setValue(new LoginResult(new LoggedInUserView(user.getEmail())));
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                           Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            // ...
                            LoginResult loginResult = new LoginResult(R.string.login_failed);
                            loginResult.setMessage(task.getException().getMessage());
                            LoginViewModel.this.loginResult.setValue(loginResult);
                        }

                    }
                });
    }

    public void loginDataChanged(String username, String password)
    {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username)
    {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password)
    {
        return password != null && password.trim().length() > 5;
    }

    public void signUp(String email, String password, Activity activity)
    {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginResult.setValue(new LoginResult(new LoggedInUserView(user.getEmail())));
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //        Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            loginResult.setValue(new LoginResult(R.string.login_failed));
                        }

                        // ...
                    }
                });
    }

    public void checkIfLogin()
    {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.signOut();
        if (currentUser!=null){
            loginResult.setValue(new LoginResult(new LoggedInUserView(currentUser.getEmail())));
        }
    }
}
