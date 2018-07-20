package daysstudio.com.toktoksurvey.setting.SurveyList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import daysstudio.com.toktoksurvey.R;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

public class SurveyListAdapter extends BaseAdapter {
    private Context context;
    private List<SurveyInfo> SurveyInfoList;

    public SurveyListAdapter(Context context , List<SurveyInfo> SurveyInfoList)
    {
        this.context = context;
        this.SurveyInfoList = SurveyInfoList;
    }

    @Override
    public int getCount() {
        return SurveyInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return SurveyInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = View.inflate(context , R.layout.surveylistitem , null);
        }
        TextView idtv = (TextView) view.findViewById(R.id.idtv);
        TextView datetv = (TextView) view.findViewById(R.id.datetv);
        TextView pointtv = (TextView) view.findViewById(R.id.pointtv);
        TextView titletv = (TextView) view.findViewById(R.id.titletv);

        int Year = SurveyInfoList.get(i).year;
        int Month = SurveyInfoList.get(i).month;
        int Day = SurveyInfoList.get(i).day;
        idtv.setTypeface(Typeface.createFromAsset(context.getAssets(),"BMJUA_ttf.ttf"));
        datetv.setTypeface(Typeface.createFromAsset(context.getAssets(),"BMJUA_ttf.ttf"));
        pointtv.setTypeface(Typeface.createFromAsset(context.getAssets(),"BMJUA_ttf.ttf"));
        titletv.setTypeface(Typeface.createFromAsset(context.getAssets(),"BMJUA_ttf.ttf"));

        idtv.setText(SurveyInfoList.get(i).id);
        datetv.setText(String.valueOf(Year) + "년 " +String.valueOf(Month) + "월 "+String.valueOf(Day)+"일");
        pointtv.setText(SurveyInfoList.get(i).point);
        titletv.setText(SurveyInfoList.get(i).title);
        return view;
    }
}
