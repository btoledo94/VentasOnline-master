package gt.umg.ventasonline;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import gt.umg.ventasonline.Common.Common;
import gt.umg.ventasonline.Common.Utils;
import gt.umg.ventasonline.dto.SessionDto;
import gt.umg.ventasonline.entities.Sesion;
import gt.umg.ventasonline.localDb.ConfigurationDb;
import gt.umg.ventasonline.ws.ResourceResponse;
import gt.umg.ventasonline.ws.VentasOnlineWs;


/**
 * Actividad para el login
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private View mProgressView;
    private View mLoginFormView;

    private Button registrarButton;
    private ImageButton configuracionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        emailView = (AutoCompleteTextView) findViewById(R.id.email);

        passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        registrarButton = (Button) findViewById(R.id.register_button);
        configuracionButton = (ImageButton) findViewById(R.id.login_configuration_button);

        registrarButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        configuracionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ConfiguracionActivity.class);
                startActivity(intent);
            }
        });

        validaSesionIniciada();
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        if (emailView.getText() == null || "".equals(emailView.getText().toString())) {
            emailView.setError("Email es requerido");
            return;
        }

        if(passwordView.getText() == null || "".equals(passwordView.getText().toString())){
            passwordView.setError("Password es requerido");
            return;
        }

        if(!isEmailValid(emailView.getText().toString())){
            emailView.setError("Email incorrecto");
            return;
        }

        VentasOnlineWs ventasOnlineWs = new VentasOnlineWs();

        showProgress(true);

        ventasOnlineWs.login(emailView.getText().toString(), passwordView.getText().toString()).execute(new ResourceResponse<Sesion>() {
            @Override
            public void success(int statusCode, Sesion responseData) {
                showProgress(false);
                try{
                    if(statusCode == 200){

                        //login ok

                        //guarda la sesion en local
                        ConfigurationDb configurationDb = new ConfigurationDb(LoginActivity.this);

                        SessionDto sessionDto = new SessionDto();

                        sessionDto.setName(responseData.getUsuario().getNombre() + " " + responseData.getUsuario().getApellido());
                        sessionDto.setEmail(responseData.getUsuario().getCorreo());
                        sessionDto.setToken(responseData.getToken());
                        sessionDto.setHelpActive(true);

                        configurationDb.saveSession(sessionDto.getName(), sessionDto.getEmail(), sessionDto.getToken());

                        //guarda la sesion en memoria
                        Common.setSession(sessionDto);

                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();

                    }
                }catch (Exception exception){
                    Utils.showCustomMessage(1, exception.getMessage(), LoginActivity.this);
                }
            }

            @Override
            public void error(int errorCode, String error) {
                showProgress(false);
                if(errorCode == 404){
                    emailView.setError("Correo no registrado");
                } else if(errorCode == 401){
                    passwordView.setError("Password incorrecto");
                } else {
                    Utils.showCustomMessage(1, error, LoginActivity.this);
                }
            }
        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Verifica si ya hay una sesion iniciada muestra el menu principal
     */
    private void validaSesionIniciada(){
        try{
            ConfigurationDb configuration = new ConfigurationDb(LoginActivity.this);

            Common.setUrlWs(configuration.getConfigurationByName("WS_URL"));

            SessionDto session = configuration.getSession();

            if(session != null){
                Common.setSession(session);

                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

                this.finish();
            }

        }catch (Exception exception){
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }
    }

}

