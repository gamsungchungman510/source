package daysstudio.com.toktoksurvey.setting.defaultset;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

import org.json.JSONException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import daysstudio.com.toktoksurvey.R;
import daysstudio.com.toktoksurvey.setting.SurveyList.SurveyList;

/**
 * Created by JinHunLee on 2018. 7. 20..
 */

public class LoginActivity extends AppCompatActivity {
    String URL = "http://ngdb.kr:3000/api/posts/signin"; // login request url
    ImageView Loginbtn;
    TextView Registerbtn;
    EditText Idedt, Pwedt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Loginbtn = (ImageView) findViewById(R.id.loginbtn);
        Registerbtn = (TextView) findViewById(R.id.registerbtn);
        Idedt = (EditText) findViewById(R.id.loginid);
        Pwedt = (EditText) findViewById(R.id.loginpw);

        //Http통신 설정
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputid = Idedt.getText().toString();
                String inputpw = Pwedt.getText().toString();
                if (!TextUtils.isEmpty(inputid)  && !TextUtils.isEmpty(inputpw)) //not empty
                {
                    if(loginchk(inputid, inputpw))
                    {
                        SharedPreferences saveuser = (SharedPreferences)getSharedPreferences("userid",0);
                        saveuser.edit().putString("userid",inputid).apply();
                        startActivity(new Intent(getApplicationContext(), SurveyList.class));
                        //move main
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , RegisterActivity.class));
            }
        });
    }

    private boolean loginchk(String id, String pw) {

        try {

            URL requesturl = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requesturl.openConnection();
            httpURLConnection.setDefaultUseCaches(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            //LOGIN REQUEST
            JSONObject infologin  = new JSONObject();
            infologin.put("id",id);
            infologin.put("pw",pw);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write(infologin.toString());
            printWriter.flush();

            //GET RESPONSE
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            if(bufferedReader.readLine().contains("true")) // contains true == success sign up
                return true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}