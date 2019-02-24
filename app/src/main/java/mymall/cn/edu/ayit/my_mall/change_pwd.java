package mymall.cn.edu.ayit.my_mall;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class change_pwd extends AppCompatActivity {
    private Button b;
    private EditText old,now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        b=(Button)findViewById(R.id.ok);
        old=(EditText)findViewById(R.id.old_password);
        now=(EditText)findViewById(R.id.password);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                myapplication m=new myapplication();
                if(m.getSessionid()==null) {
                    Intent it = new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall", "mymall.cn.edu.ayit.my_mall.login");
                    startActivity(it);
                }
                final String oldpwd=old.getText().toString().trim();
                final String newpwd=now.getText().toString().trim();
                //  Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                //请求地址
                String url=myapplication.ip+"Changepwd";
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

                                        AlertDialog.Builder bu=new AlertDialog.Builder(change_pwd.this);
                                        bu.setTitle("提示：");
                                        bu.setMessage("密码修改成功！");
                                        bu.setPositiveButton("确定",null);
                                        bu.show();

                                    } else {
                                        System.out.println(response);
                                        AlertDialog.Builder bu=new AlertDialog.Builder(change_pwd.this);
                                        bu.setTitle("警告：");
                                        bu.setMessage("密码错误！");
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
                        params.put("oldpwd", oldpwd);
                        params.put("newpwd", newpwd);
                        return params;
                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        myapplication m=new myapplication();
                        if (m.getSessionid()!= null && m.getSessionid().length() > 0) {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("cookie", m.getSessionid());
                            // Log.d("调试", "headers----------------" + headers);
                            System.out.println(m.getSessionid());
                            return headers;
                        }else {
                            return super.getHeaders();
                        }
                    }
                };
                request.setRetryPolicy(new DefaultRetryPolicy(
                        1000*30, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                //设置tag标签
                request.setTag(tag);
                //将请求添加到队列中
                requestQueue.add(request);
            }
        });
    }
}
