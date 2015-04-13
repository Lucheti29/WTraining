package ar.com.wolox.lucasdelatorre.wtraining.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.com.wolox.lucasdelatorre.wtraining.R;
import ar.com.wolox.lucasdelatorre.wtraining.utils.Patterns;
import ar.com.wolox.lucasdelatorre.wtraining.utils.Utils;

/**
 * Created by lucasdelatorre on 10/04/15.
 */
public class Login extends Activity{

    //TODO: Parse para pedir mail y contraseña
    //TODO: guardar credenciales

    //Pregunta: "No se deber crear una entidad usuario nueva sino que se va a tener que utilizar la ya existente." ???

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button clickLogin = (Button) findViewById(R.id.bn_login);
        Button clickSignup = (Button) findViewById(R.id.bn_signup);

        clickLogin.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });

        clickSignup.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }

    private void attemptToLogin() {
        String et_user_text = ((EditText) findViewById(R.id.et_user)).getText().toString();
        String et_pass_text = ((EditText) findViewById(R.id.et_pass)).getText().toString();

        Resources res = this.getResources();

        if(et_user_text.isEmpty())
        {
            Utils.showToast(res.getString(R.string.login_emptyuser), getApplicationContext());
            return;
        }

        if(et_pass_text.isEmpty())
        {
            Utils.showToast(res.getString(R.string.login_emptypass), getApplicationContext());
            return;
        }

        if(!Utils.validate(et_user_text, Patterns.EMAIL_PATTERN))
        {
            Utils.showToast(res.getString(R.string.login_invaliduser), getApplicationContext());
            return;
        }

        MessageType type = checkCredentials(et_user_text, et_pass_text);

        if(type == MessageType.SUCCESS)
        {
            //TODO: Guardar credenciales

            Intent intent = new Intent(this, Board.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(type == MessageType.FAILED_USER)
        {
            Utils.showToast(res.getString(R.string.login_wronguser), getApplicationContext());
        }
        else if(type == MessageType.FAILED_PASS)
        {
            Utils.showToast(res.getString(R.string.login_wrongpass), getApplicationContext());
        }
    }

    private void openSignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private MessageType checkCredentials(String username, String password)
    {
        //Pedir usuario
        //Chequear si existe
        //Pedir pass y corroborar que sea la correcta
        return MessageType.FAILED_USER;
    }

    private enum MessageType {
        SUCCESS,
        FAILED_USER,
        FAILED_PASS
    }
}
