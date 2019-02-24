package mymall.cn.edu.ayit.my_mall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detail extends AppCompatActivity implements mymall.cn.edu.ayit.my_mall.detail_f.OnFragmentInteractionListener{
    public  String s;
    StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            Bundle bd=this.getIntent().getExtras();
            s=bd.getString("data");
        }
        catch(Exception e)
        {
            System.out.println("02"+e);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_f d = new detail_f();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        Bundle argBundle=new Bundle();
        argBundle.putString("pro_id",s);
        d.setArguments(argBundle);
        fragmentTransaction.replace(R.id.frame_content, d);
        fragmentTransaction.commit();
        System.out.println("022");

        Button b,l;
        b = (Button) findViewById(R.id.add_car);
        l=(Button) findViewById(R.id.like);

        final String tag="login";
        final RequestQueue requestQueue= Volley.newRequestQueue(detail.this);//(getApplicationContext());
        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myapplication m=new myapplication();
                System.out.println("click");
                System.out.println(m.getSessionid());
                if(m.getSessionid()==null)
                {
                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.login");
                    startActivity(it);
                }
                String url=myapplication.ip+"Add_to_car";

                //请求队列

                final StringRequest  request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    if (response.equals("1"))
                                    {
                                        System.out.println("okk");
                                        AlertDialog.Builder bu=new AlertDialog.Builder(detail.this);
                                        bu.setTitle("恭喜：");
                                        bu.setMessage("加入购物车成功！");
                                        bu.setPositiveButton("确定",null);
                                        bu.show();
                                    }
                                    else
                                    {
                                        System.out.println("ok");
                                        System.out.println(response);
                                        AlertDialog.Builder bu=new AlertDialog.Builder(detail.this);
                                        bu.setTitle("提示：");
                                        bu.setMessage("加入购物车失败！");
                                        bu.setPositiveButton("确定",null);
                                        bu.show();

                                    }
                                }
                              catch (Exception e)
                              {
                                  System.out.println("error2"+e);
                              }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("1");
                        Log.e("t2", error.getMessage(), error);
                        System.out.println("no");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pro_id", s);

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

                requestQueue.add(request);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myapplication m=new myapplication();
                System.out.println(m.getSessionid());
                if(m.getSessionid()==null)
                {
                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.login");
                    startActivity(it);
                }
                String url=myapplication.ip+"Add_to_like";

                final StringRequest  request1 = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    if (response.equals("1"))
                                    {
                                        System.out.println("okk");
                                        AlertDialog.Builder bu=new AlertDialog.Builder(detail.this);
                                        bu.setTitle("恭喜：");
                                        bu.setMessage("收藏成功！");
                                        bu.setPositiveButton("确定",null);
                                        bu.show();
                                    }
                                    else
                                    {
                                        System.out.println("ok");
                                        System.out.println(response);
                                        AlertDialog.Builder bu=new AlertDialog.Builder(detail.this);
                                        bu.setTitle("提示：");
                                        bu.setMessage("收藏失败！");
                                        bu.setPositiveButton("确定",null);
                                        bu.show();

                                    }
                                }
                                catch (Exception e)
                                {
                                    System.out.println("error2"+e);
                                    AlertDialog.Builder bu=new AlertDialog.Builder(detail.this);
                                    bu.setTitle("恭喜：");
                                    bu.setMessage(e.toString());
                                    bu.setPositiveButton("确定",null);
                                    bu.show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("1");
                        Log.e("t2", error.getMessage(), error);
                        System.out.println("no");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pro_id", s);

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
                request1.setRetryPolicy(new DefaultRetryPolicy(
                        1000*30, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                requestQueue.add(request1);
            }
        });
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
