package com.zika.lika.zikamob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private Button btn_camera;
    private Button btn_localization;
    private Button btn_db;
    private Button btn_login;
    private TextView txt_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_camera = (Button) findViewById(R.id.main_btn_camera);
        btn_localization = (Button) findViewById(R.id.main_btn_localization);
        btn_db = (Button) findViewById(R.id.main_btn_db);
        btn_login = (Button) findViewById(R.id.main_btn_login);
        txt_user = (TextView) findViewById(R.id.main_txt_user);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        btn_localization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocalizationActivity.class);
                startActivity(intent);
            }
        });

        btn_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommunitationDBActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        updateLoggedInUser();
    }

    private void updateLoggedInUser() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {
            GraphRequest request = GraphRequest.newMeRequest(accessToken,
                    new GraphRequest.GraphJSONObjectCallback(){

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.i("MainActivity", object.toString());
                            try {
                                txt_user.setText(object.getString("name").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name");
            request.setParameters(parameters);
            request.executeAsync();
        } else {
            txt_user.setText("Nenhum");
        }
    }
}
