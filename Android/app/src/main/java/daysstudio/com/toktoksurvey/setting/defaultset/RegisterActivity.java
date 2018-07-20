package daysstudio.com.toktoksurvey.setting.defaultset;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */
import android.graphics.Typeface;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import daysstudio.com.toktoksurvey.R;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

public class RegisterActivity  extends AppCompatActivity {

    EditText regid , regpw ;
    Spinner first , second , third;
    ImageView submit;
    ArrayAdapter menuadapter;
    TextView text1 , text2;

    String url  = "http://ngdb.kr:3000/api/posts/signup";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        regid = (EditText) findViewById(R.id.regid);
        regpw = (EditText) findViewById(R.id.regpw);
        first = (Spinner) findViewById(R.id.regmenu1);
        second = (Spinner) findViewById(R.id.regmenu2);
        third = (Spinner) findViewById(R.id.regmenu3);
        submit = (ImageView) findViewById(R.id.regsubmit);
        text1 = (TextView) findViewById(R.id.regfont1);
        text2 = (TextView) findViewById(R.id.regfont2);

        // Set font
        text1.setTypeface(Typeface.createFromAsset(this.getAssets(),"BMJUA_ttf.ttf"));
        text2.setTypeface(Typeface.createFromAsset(this.getAssets(),"BMJUA_ttf.ttf"));

        // Set spinner adapter
        menuadapter = ArrayAdapter.createFromResource(this , R.array.category , R.layout.spinner_item);
        menuadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        first.setAdapter(menuadapter);
        second.setAdapter(menuadapter);
        third.setAdapter(menuadapter);
        first.setSelection(0);
        second.setSelection(1);
        third.setSelection(2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Info info = new Info(regid.getText().toString() , regpw.getText().toString(),first.getSelectedItemPosition() , second.getSelectedItemPosition() , third.getSelectedItemPosition());

                if(firstchk(info)) {
                    if(sendchk(info)) {
                        Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean firstchk(Info tmp) { // check before sending

        if (TextUtils.isEmpty(tmp.id) || TextUtils.isEmpty(tmp.pw) ) {
            Toast.makeText(this, "id 와 pw를 채워주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tmp.cate1 == tmp.cate2 || tmp.cate2==tmp.cate3  || tmp.cate1 == tmp.cate3) {
            Toast.makeText(this, "중복되지 않는 카테고리로 선택해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean sendchk(Info tmp) //send and check
    {

        try {

            URL requesturl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requesturl.openConnection();
            httpURLConnection.setDefaultUseCaches(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            //LOGIN REQUEST
            JSONObject infologin  = new JSONObject();
            ArrayList category = new ArrayList();
            category.add(tmp.cate1);
            category.add(tmp.cate2);
            category.add(tmp.cate3);

            infologin.put("id",tmp.id);
            infologin.put("pw",tmp.pw);
            infologin.put("category",category.toString());

            OutputStreamWriter os = new  OutputStreamWriter(httpURLConnection.getOutputStream());

            os.write(infologin.toString());
            os.flush();

            //GET RESPONSE
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String temp = bufferedReader.readLine();
            Log.i("RESPONSE" , temp);
            if(temp.contains("true")) // contains true == success sign up
            {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }
}