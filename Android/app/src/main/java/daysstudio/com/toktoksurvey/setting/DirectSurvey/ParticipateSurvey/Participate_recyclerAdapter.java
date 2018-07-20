package daysstudio.com.toktoksurvey.setting.DirectSurvey.ParticipateSurvey;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import daysstudio.com.toktoksurvey.R;

/**
 * Created by JinHunLee on 2018. 7. 21..
 */

public class Participate_recyclerAdapter extends RecyclerView.Adapter<Participate_recyclerAdapter.ViewHolder> {
    private List<ProblemInfo> problemInfos;

    public Participate_recyclerAdapter(List<ProblemInfo> problemInfos)
    {
        this.problemInfos = problemInfos;
    }

    @Override
    public Participate_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.participate_survey_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final Participate_recyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(problemInfos.get(position).name);
        holder.value1.setText(problemInfos.get(position).selectionInfo.selections.get(0));
        holder.value2.setText(problemInfos.get(position).selectionInfo.selections.get(1));
        holder.value3.setText(problemInfos.get(position).selectionInfo.selections.get(2));
        holder.value4.setText(problemInfos.get(position).selectionInfo.selections.get(3));


        holder.value1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.value2.setChecked(false);
                holder.value3.setChecked(false);
                holder.value4.setChecked(false);

            }
        });

        holder.value2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.value1.setChecked(false);
                holder.value3.setChecked(false);
                holder.value4.setChecked(false);

            }
        });

        holder.value3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.value1.setChecked(false);
                holder.value2.setChecked(false);
                holder.value4.setChecked(false);

            }
        });

        holder.value4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.value1.setChecked(false);
                holder.value2.setChecked(false);
                holder.value3.setChecked(false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return problemInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title;
        RadioButton value1, value2, value3, value4;
        public ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            title =(TextView) itemView.findViewById(R.id.partproblem);
            value1 = (RadioButton) itemView.findViewById(R.id.partvalue1);
            value2 = (RadioButton) itemView.findViewById(R.id.partvalue2);
            value3 = (RadioButton) itemView.findViewById(R.id.partvalue3);
            value4 = (RadioButton) itemView.findViewById(R.id.partvalue4);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
