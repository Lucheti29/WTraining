package ar.com.wolox.lucasdelatorre.wtraining.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.com.wolox.lucasdelatorre.wtraining.R;
import ar.com.wolox.lucasdelatorre.wtraining.utils.Patterns;
import ar.com.wolox.lucasdelatorre.wtraining.utils.Utils;

/**
 * Created by lucasdelatorre on 13/04/15.
 */
public class Signup extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        setTitle(getResources().getText(R.string.signup_label));

        Button clickSignup = (Button) findViewById(R.id.bn_signup_accept);

        clickSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptToSignup();
            }
        });
    }

    private void attemptToSignup()
    {
        String et_signup_user = ((EditText) findViewById(R.id.et_signup_user)).getText().toString();
        String et_signup_pass = ((EditText) findViewById(R.id.et_signup_pass)).getText().toString();
        String et_signup_cpass = ((EditText) findViewById(R.id.et_signup_cpass)).getText().toString();

        Resources res = this.getResources();

        if(et_signup_user.isEmpty())
        {
            Utils.showToast(res.getString(R.string.signup_emptyuser), getApplicationContext());
            return;
        }

        if(et_signup_pass.isEmpty())
        {
            Utils.showToast(res.getString(R.string.signup_emptypass), getApplicationContext());
            return;
        }

        if(et_signup_cpass.isEmpty())
        {
            Utils.showToast(res.getString(R.string.signup_cpassnotmatch), getApplicationContext());
            return;
        }

        if(!Utils.validate(et_signup_user, Patterns.EMAIL_PATTERN))
        {
            Utils.showToast(res.getString(R.string.login_invaliduser), getApplicationContext());
            return;
        }

        if(!et_signup_pass.equals(et_signup_cpass))
        {
            Utils.showToast(res.getString(R.string.signup_cpassnotmatch), getApplicationContext());
            return;
        }

        if(userExists(et_signup_user))
        {
            Utils.showToast(res.getString(R.string.signup_userexists), getApplicationContext());
            return;
        }

        //Registrar
    }

    //TODO: implementar
    private boolean userExists(String username)
    {
        return false;
    }

}