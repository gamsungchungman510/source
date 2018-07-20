package daysstudio.com.toktoksurvey.setting.SurveyList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import daysstudio.com.toktoksurvey.R;
import daysstudio.com.toktoksurvey.setting.DirectSurvey.MakeSurvey.MakeList;
import daysstudio.com.toktoksurvey.setting.DirectSurvey.ParticipateSurvey.Participate_survey;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

public class SurveyList extends AppCompatActivity {
    FloatingActionButton surveymenubtn , surveysettingbtn , surveyaddbtn;
    LinearLayout surveysettinglay , surveyaddlay;
    ListView surveylist;
    SurveyListAdapter surveyListAdapter;
    List<SurveyInfo> surveyInfoList;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surveylist_activity);
        surveyaddlay  = (LinearLayout) findViewById(R.id.surveyaddlayout);
        surveysettinglay = (LinearLayout) findViewById(R.id.surveysettinglayout);
        surveyaddbtn = (FloatingActionButton) findViewById(R.id.surveyadd);
        surveymenubtn = (FloatingActionButton) findViewById(R.id.surveymenu);
        surveysettingbtn = (FloatingActionButton) findViewById(R.id.surveysetting);
        back = (View) findViewById(R.id.groundback);

        //survey list
        surveylist = (ListView) findViewById(R.id.surveylist);
        surveyInfoList = new ArrayList<>();
        surveyInfoList = new GetSurveyLists().Get(this);

        surveyListAdapter = new SurveyListAdapter(this , surveyInfoList);
        surveylist.setAdapter(surveyListAdapter);

        surveyListAdapter.notifyDataSetChanged();;


        surveysettingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), po.class));
            }
        });

        surveymenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(surveyaddlay.getVisibility() == View.VISIBLE)
                    closefloatingbtn();
                else
                    showfloatingbtn();
            }
        });

        surveyaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , MakeList.class));
            }
        });

        surveylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(getApplicationContext(), Participate_survey.class);
                intent.putExtra("ProblemName",surveyInfoList.get(pos).title); //send problem name
                startActivity(intent);
            }
        });



    }

    private void closefloatingbtn()
    {
        surveysettinglay.setAnimation(AnimationUtils.loadAnimation(this,R.anim.rightfab));
        surveysettinglay.setVisibility(View.INVISIBLE);
        surveyaddlay.setAnimation(AnimationUtils.loadAnimation(this,R.anim.downfab));
        surveyaddlay.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        surveylist.setActivated(true);
    }

    private void showfloatingbtn()
    {
        back.setVisibility(View.VISIBLE);
        surveylist.setActivated(false);
        surveysettinglay.setAnimation(AnimationUtils.loadAnimation(this,R.anim.leftfab));
        surveysettinglay.setVisibility(View.VISIBLE);
        surveyaddlay.setAnimation(AnimationUtils.loadAnimation(this,R.anim.upfab));
        surveyaddlay.setVisibility(View.VISIBLE);
    }

}
