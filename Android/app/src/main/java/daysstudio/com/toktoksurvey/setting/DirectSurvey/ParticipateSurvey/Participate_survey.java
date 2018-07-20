package daysstudio.com.toktoksurvey.setting.DirectSurvey.ParticipateSurvey;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import daysstudio.com.toktoksurvey.R;

/**
 * Created by JinHunLee on 2018. 7. 21..
 */

public class Participate_survey extends AppCompatActivity {
    String url = "http://ngdb.kr:3000/api/posts/namesearch";
    RecyclerView ProblemsRecycler;
    Participate_recyclerAdapter participate_recyclerAdapter;
    TextView titleview;
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participate_survey_activity);
        String ProblemName = getIntent().getStringExtra("ProblemName");

        titleview = (TextView) findViewById(R.id.parttitle);
        titleview.setTypeface(Typeface.createFromAsset(this.getAssets(),"BMJUA_ttf.ttf"));
        titleview.setText("제목 : " + ProblemName);
        submit = (Button) findViewById(R.id.partsuccessbtn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.isSmoothScrolling();
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        ProblemsRecycler = (RecyclerView) findViewById(R.id.partrecyclerview);
        ProblemsRecycler.setHasFixedSize(true);
        ProblemsRecycler.setNestedScrollingEnabled(true);
        ProblemsRecycler.setLayoutManager(linearLayoutManager);


        List<ProblemInfo> problemInfoList  = GetProblem(ProblemName);
        participate_recyclerAdapter = new Participate_recyclerAdapter(problemInfoList);
        ProblemsRecycler.setAdapter(participate_recyclerAdapter);

    }

    private List<ProblemInfo> GetProblem(String problemname)
    {
        try {

            List<ProblemInfo> problemInfos = new ArrayList<>();
            URL requesturl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requesturl.openConnection();
            httpURLConnection.setDefaultUseCaches(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            //LOGIN REQUEST
            JSONObject infologin  = new JSONObject();
            infologin.put("name",problemname);
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
            JSONArray SelectList = new JSONObject(e).getJSONObject("survey").getJSONArray("questions"); //get selections

            for(int i=0; i < SelectList.length(); i++) {
                List<String> selections = new ArrayList<>();
                JSONObject one = SelectList.getJSONObject(i);
                JSONArray selects = one.getJSONArray("selects"); //selections
                for(int j=0; j<selects.length(); j++)
                    selections.add(selects.getString(j));
                problemInfos.add(new ProblemInfo(one.getString("name"),new SelectionInfo(selections))); //problem name , selections list
            }

            return problemInfos;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
