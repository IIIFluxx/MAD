package curtin.edu.au.assignment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Provides Game Information to User and allows them to interact with the game
 * via build, demolish, run & info buttons. Also contains an inner class to display London's weather
 * REFERENCE: for round() method: https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
 */

public class TaskbarFragment extends Fragment
{

    // GameData reference
    GameData gd;

    // API Key
    private static final String WEATHER_API = "59daedcfb75176638e27771e84dc817b";

    //Info fields
    private TextView timeTxt;
    private TextView balanceTxt;
    private TextView incomeTxt;
    private TextView popnTxt;
    private TextView empRateTxt;
    private TextView cityTxt;
    private TextView tempTxt;

    // Image // buttons
    private ImageView demolishIcon;
    private ImageView infoIcon;
    private ImageView clockIcon;
    private ImageView buildIcon;

    private int mode;

    private int initBalance;


    @Override
    // Defining Fragments
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        gd = GameData.get();
        View view = inflater.inflate(
                R.layout.fragment_taskbar, ui, false);

        // Store the views into variables we can use
        timeTxt = (TextView)view.findViewById(R.id.timeText); // Current game time - Default 0.
        balanceTxt = (TextView)view.findViewById(R.id.balanceText); // How much money I have left.
        incomeTxt = (TextView)view.findViewById(R.id.incomeText); // How much money I receive WHEN TIMESTEP
        popnTxt = (TextView)view.findViewById(R.id.populationText); // Formula based on nResidential
        empRateTxt = (TextView)view.findViewById(R.id.empRateText); // Formula based on calculation
        cityTxt = (TextView)view.findViewById(R.id.cityNameText);
        tempTxt = (TextView)view.findViewById(R.id.tempText);

        mode = 0;

        // Now the ImageView "buttons"
        demolishIcon = (ImageView)view.findViewById(R.id.demolishImg);
        infoIcon = (ImageView)view.findViewById(R.id.infoImg);
        clockIcon = (ImageView)view.findViewById(R.id.clockImg);
        buildIcon = (ImageView)view.findViewById(R.id.buildImg);

        /** Temperature Handling */

        String urlString =
        Uri.parse("http://api.openweathermap.org/data/2.5/weather?")
                .buildUpon()
                .appendQueryParameter("lat", "51.5085")
                .appendQueryParameter("lon", "-0.1257")
                .appendQueryParameter("units", "metric")
                .appendQueryParameter("appid", WEATHER_API)
                .build().toString();

        new DLWeatherTask(tempTxt).execute(urlString);

        buildIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gd.setMode(0);
            }
        });

        demolishIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gd.setMode(1);
            }
        });

        infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gd.setMode(2);
            }
        });

        clockIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gd.step();
                gd.updateGameData(gd);
                refresh();
            }
        });

        refresh();
        return view;
    }


    public void refresh()
    {
        if(gd.getMoney() <= 0) // If you lose
        {
            finishGame();
        }
        else
        {
            setBalanceTxt(gd.getMoney());
        }

        setIncomeTxt(gd.getIncome());
        setTimeTxt(gd.getTime());
        setPopulation(gd.getPopulation());
        setEmpRate(gd.getEmpRate());

        initBalance = gd.getMoney();

        setCity(gd.getSettings().getCity());

    }


    public void finishGame()
    {
        balanceTxt.setText("Balance = £0"); //Game over message
        balanceTxt.setTextColor(Color.RED);
        Context context = getContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "Game Over!\n If you wish to reset game - go into Settings Activity", duration);
        toast.show();
    }


    public void setBalanceTxt(int curBal)
    {
        balanceTxt.setText("Balance: £" + Integer.toString(curBal));
    }

    public void setTimeTxt(int curTime)
    {
        timeTxt.setText("Time: " + Integer.toString(curTime));
    }

    public void setPopulation(int curPopn)
    {
        popnTxt.setText("Population: " + Integer.toString(curPopn));
    }

    public void setIncomeTxt(double curInc)
    {
        double rounded;

         if(isPositive(curInc))
        {
            rounded = round(curInc);
            incomeTxt.setText("Income: +£" + Double.toString(rounded));
        }
       else
        {
            rounded = round(curInc);
            incomeTxt.setText("Income: £" + Double.toString(rounded));
        }
    }

    public boolean isPositive(double in)
    {
        boolean export = false;
        if(in > 0.0)
            return true;
        return export;
    }

    public double round(double in)
    {
        double export;
        export = Math.round(in * 100.0)/100.0;
        return export;
    }

    // Formula: Percentage = (Rate x 100) e.g. Rate = 0.4. That = 40%.
    public void setEmpRate(double empRate)
    {
        empRateTxt.setText("Employment Rate: " + Double.toString(round(100.0 * empRate)) + "%");
    }



    public void setCity(String city)
    {
        cityTxt.setText("City: " + city);
    }


    private class DLWeatherTask extends AsyncTask<String, Void, String> // First String parameter = parameter for doInBackground().
    {
        private TextView textView;

        public DLWeatherTask(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected String doInBackground(String... strings)  // performs the Downloading
        { // The "String..." syntax is just String[] with less clutter
            String weather = "N/A";
            try // Check status, DL data.
            {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream str = new BufferedInputStream(conn.getInputStream());
                BufferedReader bfr = new BufferedReader(new InputStreamReader(str));
                StringBuilder bldr = new StringBuilder();

                String in;
                while((in = bfr.readLine()) != null)
                {
                    bldr.append(in);
                }

                JSONObject jBase = new JSONObject(bldr.toString());
                JSONObject main = jBase.getJSONObject("main");
                weather = String.valueOf(main.getDouble("temp"));

                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return weather;
        }

        @Override
        protected void onPostExecute(String temp) // Takes in that same result, and displays it
        {
            textView.setText("Temperature: " + temp + "C ");
        }
    }



}
