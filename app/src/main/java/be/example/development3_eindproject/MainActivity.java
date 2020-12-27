package be.example.development3_eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    //
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        // Link naar button

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                // Als er wordt geklikt, gaat onderstaande functie van start
            }
        });
    }

    private void jsonParse() {

        String url = "https://my.api.mockaroo.com/portfolioitems.json?key=7b107dd0";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Eerste repsonse wordt getoond als request mogelijk is

                        //mTextViewResult.setText("API succesvol ingeladen :)))))");
                        //mTextViewResult.setText("Dit is een tweede regel tekst");
                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextViewResult.setText("An error occured");
                // Tweede wordt getoond als er een error is
            }
        });
        mQueue.add(request);
        super.onStart();
    }

    private void parseData(String response) {
        try {
            // Create JSOn Object
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //mTextViewResult.setText(jsonObject.getString("portfolioitems"));
                mTextViewResult.setText("En nu enkel nog de juiste data fixen :p");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}