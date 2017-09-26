package com.example.chris.bj_final;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.MalformedJsonException;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chris.bj_final.data.BlackJackContract;
import com.example.chris.bj_final.data.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chris on 9/25/2017.
 */

public class BackgroundTask extends AsyncTask<Void, Void, Void> {
    ProgressDialog progressBar;
    Context ctx;
    String json_url = "http://10.0.2.2/blackjackinfo/get_blackjack_details.php";

    public BackgroundTask(Context context){
        this.ctx = context;
    }
    @Override
    protected void onPreExecute() {
        progressBar = new ProgressDialog(ctx);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please Wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();
    }

    @Override
    protected Void doInBackground (Void... params) {
        try{
            URL url = new URL(json_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line =bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
                    Thread.sleep(500);
            }
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject (json_data);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            DatabaseHelper databaseHelper = new DatabaseHelper(ctx);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            int count = 0;
            while (count < jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                databaseHelper.addUser(JO.getString("Name"), JO.getInt("HighScore"), db);
            }
            databaseHelper.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Background Done", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
            progressBar.dismiss();
    }


}
