package daysstudio.com.toktoksurvey.setting.DirectSurvey.ParticipateSurvey;

/**
 * Created by JinHunLee on 2018. 7. 21..
 */

public class ProblemInfo {
    public String name;
    public SelectionInfo selectionInfo;

    public ProblemInfo(String name , SelectionInfo selectionInfo)
    {
        this.name = name;
        this.selectionInfo = selectionInfo;
    }

}
