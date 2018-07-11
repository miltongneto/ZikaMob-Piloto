package com.zika.lika.zikamob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btn_camera;
    private Button btn_localization;
    private Button btn_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_camera = (Button) findViewById(R.id.main_btn_camera);
        btn_localization = (Button) findViewById(R.id.main_btn_localization);
        btn_db = (Button) findViewById(R.id.main_btn_db);

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
    }
}
