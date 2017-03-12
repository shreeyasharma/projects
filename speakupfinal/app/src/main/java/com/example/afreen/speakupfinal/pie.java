package com.example.afreen.speakupfinal;

/**
 * Created by AFREEN on 12/3/2016.
 */



        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.ListAdapter;
        import android.widget.SimpleAdapter;
        import android.widget.Toast;

        import com.github.mikephil.charting.charts.PieChart;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.PieData;
        import com.github.mikephil.charting.data.PieDataSet;
        import com.github.mikephil.charting.utils.ColorTemplate;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.params.BasicHttpParams;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class pie extends AppCompatActivity {

    String result1;
    static int i1 = 0;
    String myJSON;
static String jj;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    PieDataSet dataset;
    ArrayList<Entry> entries = new ArrayList<>();
    PieChart pieChart;
    ArrayList<String> labels = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ppi);

        pieChart = (PieChart) findViewById(R.id.chart);


        getData();
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                int id1 = c.getInt(TAG_NAME);
                entries.add(new Entry(id1, i1));
                i1++;

                dataset = new PieDataSet(entries, "# of Calls");
                labels.add(id);

            }
            PieData data = new PieData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            pieChart.setDescription("Description");
            pieChart.setData(data);

            pieChart.animateY(5000);

            pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData() {
    jj=ph.name;

            List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);


            nameValuePairs.add(new BasicNameValuePair("email",jj));
            Toast.makeText(getApplicationContext(),jj,Toast.LENGTH_LONG).show();

            InputStream is=null;
            try
            {
                HttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost("http://seju.esy.es/pop.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response=httpClient.execute(httpPost);
                HttpEntity entity=response.getEntity();
                is=entity.getContent();
                // Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                StringBuilder sb=new StringBuilder();
                String line=null;
                while((line=reader.readLine())!=null)
                {
                    sb.append(line);
                }
                result1=sb.toString();
                Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                /** String result1=sb.toString();

                 Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                 if(result1.equals("success")){
                 Intent intent=new Intent(ar.getContext(),Login.class);
                 ar.getContext().startActivity(intent);}
                 **/
            }
            catch(ClientProtocolException e)
            {
                Log.e("ClientProtocol","Log_tag");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                Log.e("Log_tag","IOException");
                e.printStackTrace();
            }



            myJSON = result1;
            Toast.makeText(getApplicationContext(), myJSON, Toast.LENGTH_LONG).show();

            showList();
        }
}