package daysstudio.com.toktoksurvey.setting.SurveyList;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import daysstudio.com.toktoksurvey.setting.defaultset.Info;

/**
 * Created by JinHunLee on 2018. 7. 21..
 */

public class GetSurveyLists  {
    public  List<SurveyInfo> Get(Context context)
    {
        try {

            List<SurveyInfo> surveyInfos = new ArrayList<>();
            URL requesturl = new URL("http://ngdb.kr:3000/api/posts/search");
            HttpURLConnection httpURLConnection = (HttpURLConnection) requesturl.openConnection();
            httpURLConnection.setDefaultUseCaches(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            //LOGIN REQUEST
            JSONObject infologin  = new JSONObject();
            infologin.put("id",userid(context));
            infologin.put("category",getcategory(context));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write(infologin.toString());
            printWriter.flush();

            //GET RESPONSE
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String tmp;
            StringBuilder stringBuilder = new StringBuilder();
            while((tmp = bufferedReader.readLine()) != null)
                stringBuilder.append(tmp);

            String e = stringBuilder.toString();
            JSONArray list = new JSONObject(e).getJSONArray("list");

            for(int i   =0; i < list.length(); i++) {
                JSONObject one = list.getJSONObject(i);

                surveyInfos.add(new SurveyInfo(one.getJSONArray("deadline").getInt(0),one.getJSONArray("deadline").getInt(1),one.getJSONArray("deadline").getInt(2),one.getString("name") , one.getString("price"),one.getString("id")));
            }
            return surveyInfos;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    private String getcategory(Context context)
    {
        try {

            URL requesturl = new URL("http://ngdb.kr:3000/api/posts/userinfo");
            HttpURLConnection httpURLConnection = (HttpURLConnection) requesturl.openConnection();
            httpURLConnection.setDefaultUseCaches(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            //LOGIN REQUEST
            JSONObject infologin  = new JSONObject();
            infologin.put("id",userid(context));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write(infologin.toString());
            printWriter.flush();

            //GET RESPONSE
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String category = bufferedReader.readLine();

            return  new JSONObject(category).getJSONObject("data").getJSONArray("category").toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String userid(Context context)
    {
        SharedPreferences saveuser = (SharedPreferences)context.getSharedPreferences("userid",0);
        return  saveuser.getString("userid",null);
    }


}
