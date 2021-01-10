package be.example.development3_eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;

    static final String JsonUrlStr = "https://my.api.mockaroo.com/portfolioitems.json?key=7b107dd0";

    LinearLayout rootLayout;
    //
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.list_view);

        try {
            URL url = new URL(JsonUrlStr);

            FetchPortfolio getDataTask = new FetchPortfolio();
            getDataTask.execute(url);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class FetchPortfolio extends AsyncTask<URL, Void, Item[]> {
        final String TAG = FetchPortfolio.class.getSimpleName();

        URL url;

        @Override
        protected Item[] doInBackground(URL... params) {
            url = params[0];

            if(url.toString().contains(.json)){
                return FetchPortfolioAsJSON();
            }
            return new Item[0];
        }

        @Override
        protected void onPostExecute(Item[] items) {
            for (Item item: items) {

                View portfolioView = getLayoutInflater().inflate(R.layout.list_item, null);

                TextView titleView = portfolioView.findViewById(R.id.title);
                titleView.setText(String.format("%s", item.getTitle()));

                rootLayout.addView(portfolioView);
            }
        }

    }

    private void jsonParse() {

        String url = "https://my.api.mockaroo.com/portfolioitems.json?key=7b107dd0";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for(int i = 0; i< response.length();i++){
                                JSONObject portfolio = response.getJSONObject(i);

                                String title = portfolio.getString("title");
                                // String omschrijving = portfolio.getString("description");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // Tweede wordt getoond als er een error is
            }
        });
    }
}