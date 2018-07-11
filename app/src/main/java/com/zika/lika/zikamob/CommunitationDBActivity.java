package com.zika.lika.zikamob;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import org.apache.http.client.HttpClient;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CommunitationDBActivity extends Activity {

    JSONParser jsonParser = new JSONParser();
    private EditText edt_name;
    private EditText edt_description;
    private Button btn_submit;

    private String name;
    private String description;

    private static String url_inserir_test = "http://192.168.25.10/DB_Server/inserir_teste.php?nome={name}&descricao={description}";
    private static String url_select_test = "http://192.168.25.10/DB_Server/selecionar_teste.php?id=1";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communitation_db);

        edt_name = (EditText) findViewById(R.id.communication_edt_name);
        edt_description = (EditText) findViewById(R.id.communication_edt_description);
        btn_submit = (Button) findViewById(R.id.communication_btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edt_name.getText().toString();
                description = edt_description.getText().toString();
                new InsertTestTask().execute();
            }
        });
    }

    class InsertTestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            //String name = strings[0];
            //String description = strings[1];

            //List<NameValuePair> params;

            //JSONObject json = jsonParser.make
            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost request = new HttpPost();
                //HttpGet request = new HttpGet();

                request.setURI(new URI(url_inserir_test.replace("{name}", name).replace("{description}", description)));
                HttpResponse response = client.execute(request);

                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    if (line.contains("success")) {
                        sb.append(line);
                     }
                    //break;
                }

                in.close();
                String result = sb.toString();

                Log.i("CommunicationDBActivity", result);
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                //JSONObject json = (JSONObject) jsonParser.parse(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
