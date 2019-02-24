package mymall.cn.edu.ayit.my_mall;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private Button b;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b=(Button)findViewById(R.id.register);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {

                final String user_name=username.getText().toString().trim();
                final String pwd=password.getText().toString().trim();
                if((!TextUtils.isEmpty(user_name))&&(!TextUtils.isEmpty(pwd)))
                {

                    //请求地址
                    String url=myapplication.ip+"Register";
                    final String tag="login";
                    //请求队列
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    //防止重复请求，所以先取消tag标识的请求队列
                    requestQueue.cancelAll(tag);

                    //创建StringRequest,定义字符串请求的请求方式为post(默认是get)
                    final StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        // JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                                        //String result = jsonObject.getString("Result");

                                        if (response.equals("1")) {

                                            Intent intent =new Intent(register.this,main.class);
                                            startActivity(intent);

                                        } else {
                                            System.out.println("1");
                                            AlertDialog.Builder bu=new AlertDialog.Builder(register.this);
                                            bu.setTitle("警告：");
                                            bu.setMessage("注册失败");
                                            bu.setPositiveButton("确定",null);
                                            bu.show();

                                        }
                                    } catch (Exception e) {

                                        Log.e("t1", e.getMessage(), e);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("t2", error.getMessage(), error);
                            System.out.println("no");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", user_name);
                            params.put("password", pwd);
                            return params;
                        }
                    };
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            1000*30, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    //设置tag标签
                    request.setTag(tag);
                    //将请求添加到队列中
                    requestQueue.add(request);
                }
                else
                {
                    AlertDialog.Builder bu=new AlertDialog.Builder(register.this);
                    bu.setTitle("警告：");
                    bu.setMessage("请填写用户名和密码！！");
                    bu.setPositiveButton("确定",null);
                    bu.show();
                }
            }

        });

    }
}
