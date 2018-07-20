package daysstudio.com.toktoksurvey.setting.SurveyList;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

class SurveyInfo {
    public String  title , point ,id;
    public int year , month , day;
    public SurveyInfo(int year , int month , int day , String title , String point , String id)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.point = point;
        this.id = id;
    }

}
