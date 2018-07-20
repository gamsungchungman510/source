package daysstudio.com.toktoksurvey.setting.DirectSurvey.MakeSurvey;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import daysstudio.com.toktoksurvey.R;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

@SuppressLint("Registered")
public class MakeList extends AppCompatActivity {

    RecyclerView recyclerView;
    MakeListAdapter makeListAdapter;
    List<MakeInfo> makeInfoList;
    Button plus ,minus , submit;
    EditText title ,point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_activity);
        recyclerView = findViewById(R.id.makerc);
        plus = findViewById(R.id.mkplus);
        minus = findViewById(R.id.mkminus);
        submit = findViewById(R.id.mksubmit);
        title  = findViewById(R.id.bigtitle);
        point = findViewById(R.id.pt);
        makeInfoList = new ArrayList<>();


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeInfoList.add(new MakeInfo("tmp",new String[]{"tmp","tmp","dasd","das"}));
                makeListAdapter= new MakeListAdapter(getApplicationContext(),makeInfoList);
                Toast.makeText(MakeList.this, "on", Toast.LENGTH_SHORT).show();

                recyclerView.setAdapter(makeListAdapter);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    makeInfoList.remove(makeInfoList.size() - 1);
                    makeListAdapter.notifyDataSetChanged();
                }
                catch (Error e)
                {
                    Toast.makeText(MakeList.this, "더이상 없앨 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

       // makeInfoList.add(new MakeInfo("tmp",new String[]{"tmp","tmp","tmp","tmp"}));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.isSmoothScrolling();
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        makeListAdapter = new MakeListAdapter(this , makeInfoList);
        recyclerView.setAdapter(makeListAdapter);


        makeListAdapter.notifyDataSetChanged();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ((SharedPreferences)getSharedPreferences("userid",0)).getString("userid",null);
                String name  = title.getText().toString();
                String price = point.getText().toString();
                List<MakeInfo> send = makeListAdapter.gettext();
                ArrayList deadline = new ArrayList();
                boolean active = true;
                int category = 1;
                String inform = "IM'GROOM (GRIN)";

                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for(int i=0; i<send.size(); i++) {
                    JSONObject jsonObject1 = new JSONObject();

                    try {
                        jsonObject.put("id",id);
                        jsonObject.put("name",name);
                        jsonObject.put("price",price);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONArray jsonArray1 = new JSONArray();
                        jsonObject1.put("name",send.get(i).name);
                        jsonArray1.put(send.get(i).selects[0]);
                        jsonArray1.put(send.get(i).selects[1]);
                        jsonArray1.put(send.get(i).selects[2]);
                        jsonArray1.put(send.get(i).selects[3]);
                        jsonObject1.put("selects",jsonArray1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jsonObject.put("questions",jsonObject1.toString());
                        jsonObject.put("active",active);
                        jsonObject.put("deadline",deadline);
                        jsonObject.put("category",category);
                        jsonObject.put("inform",inform);

                        Log.i("REQ" , jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }



            }
        });






    }

}
