package daysstudio.com.toktoksurvey.setting.DirectSurvey.MakeSurvey;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import daysstudio.com.toktoksurvey.R;
import daysstudio.com.toktoksurvey.setting.DirectSurvey.ParticipateSurvey.Participate_recyclerAdapter;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

public class MakeListAdapter extends RecyclerView.Adapter<MakeListAdapter.ViewHolder> {
    private Context context;
    private List<MakeInfo> MakeInfoList;

    private List<EditText> titleed;
    private List<EditText> value1ed;
    private List<EditText> value2ed;
    private List<EditText> value3ed;
    private List<EditText> value4ed;



    public MakeListAdapter(Context context , List<MakeInfo> MakeInfoList)
    {
        this.context = context;
        this.MakeInfoList = MakeInfoList;
        value1ed = new ArrayList<>();
        value2ed = new ArrayList<>();
        value3ed = new ArrayList<>();
        value4ed = new ArrayList<>();
        titleed = new ArrayList<>();


        for(int i=0; i<20; i++)
        {
            value1ed.add(null);
            value2ed.add(null);
            value3ed.add(null);
            value4ed.add(null);
            titleed.add(null);
        }
    }
    @Override
    public MakeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.make_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MakeListAdapter.ViewHolder holder, int position) {
        titleed.set(position,holder.title);
        value1ed.set(position,holder.value1);
        value2ed.set(position,holder.value2);
        value3ed.set(position,holder.value3);
        value3ed.set(position,holder.value4);

        holder.number.setText(String.valueOf(position)+"번: ");
    }

    @Override
    public int getItemCount() {
        return MakeInfoList.size();
    }

    public List<MakeInfo> gettext()
    {
        List<MakeInfo> about = new ArrayList<>();
        for(int i=0; i<MakeInfoList.size(); i++)
        {
            if(titleed.get(i) != null && value1ed.get(i)!=null &&  value2ed.get(i)!=null && value3ed.get(i)!=null &&  value4ed.get(i)!=null ) {
                Toast.makeText(context, String.valueOf(titleed.get(i)), Toast.LENGTH_SHORT).show();
                about.add(new MakeInfo(titleed.get(i).getText().toString() , new String[]{value1ed.get(i).getText().toString(),value2ed.get(i).getText().toString(),value3ed.get(i).getText().toString(),value4ed.get(i).getText().toString()}));
            }
        }
        return about;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        EditText title , value1 , value2 , value3 , value4;
        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.makename);
            title =  (EditText)itemView.findViewById(R.id.makenum);
            value1 = (EditText)itemView.findViewById(R.id.firstselct);
            value2 = (EditText)itemView.findViewById(R.id.secondselct);
            value3 = (EditText)itemView.findViewById(R.id.thirdselct);
            value4 = (EditText)itemView.findViewById(R.id.fourthselct);


        }



    }
}
