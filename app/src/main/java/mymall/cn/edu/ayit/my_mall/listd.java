package mymall.cn.edu.ayit.my_mall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class listd extends AppCompatActivity {

    public  String s;
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
        setContentView(R.layout.activity_listd);
        //请求地址
        String url=myapplication.ip+"Kind";
        final String tag="login";
        //请求队列
        final RequestQueue requestQueue= Volley.newRequestQueue(this);//(getApplicationContext());
        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            //String result = jsonObject.getString("Result");


                            System.out.println("9"+response.toString());
                            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(response);
                            for (int i = 0; i < json.size(); i++) {
                                System.out.println("1");
                                net.sf.json.JSONObject pro=new net.sf.json.JSONObject();
                                pro=json.getJSONObject(i);
                                final String pro_id = pro.getString("pro_id");
                                String pro_name = pro.getString("pro_name");
                                String pro_content= pro.getString("pro_content");
                                String pic1 = pro.getString("pro_picture1");
                                String pic2 = pro.getString("pro_picture2");
                                String pic3= pro.getString("pro_picture3");
                                String pro_sale=pro.getString("pro_sale");
                                String pro_shop=pro.getString("pro_shop");
                                String pro_price=pro.getString("pro_price");
                                LinearLayout course_linearLayout  = (LinearLayout)findViewById(R.id.fram);
                                LinearLayout l=new LinearLayout(course_linearLayout.getContext());
                                TextView n=new TextView(course_linearLayout.getContext());
                                n.setText("\n");
                                course_linearLayout.addView(l);
                                course_linearLayout.addView(n);


                                l.setOrientation(LinearLayout.HORIZONTAL);
                                ImageView im=new ImageView(course_linearLayout.getContext());
                                final LruCache<String,Bitmap> mImageCache=new LruCache<String,Bitmap>(20);
                                ImageLoader.ImageCache imageCache =new ImageLoader.ImageCache()
                                {
                                    @Override
                                    public void putBitmap(String url, Bitmap bitmap) {
                                        mImageCache.put(url, bitmap);
                                    }

                                    @Override
                                    public Bitmap getBitmap(String url) {
                                        return mImageCache.get(url);
                                    }
                                };
                                ImageLoader mImageLoader = new ImageLoader(requestQueue,imageCache);
                                ImageLoader.ImageListener listener =ImageLoader.getImageListener(im,android.R.drawable.ic_menu_rotate,android.R.drawable.ic_delete);
                                mImageLoader.get(myapplication.ip+pic1,listener);
                                im.setMaxHeight(130);
                                im.setMaxWidth(130);
                                TextView t=new TextView(course_linearLayout.getContext());
                                String m=pro_name+"\n"+"价格：￥"+pro_price;
                                t.setText(m);
                                l.addView(im);
                                l.addView(t);
                                l.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        try {

                                            Intent it=new Intent();
                                            it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.detail");
                                            Bundle argBundle=new Bundle();
                                            argBundle.putString("data",pro_id);

                                            it.putExtras(argBundle);
                                            startActivity(it);
                                        }
                                        catch (Exception e)
                                        {
                                            System.out.println("01"+e);
                                        }

                                    }
                                });
                                System.out.println(pro_name);
                                System.out.println(pic1);



                            }


                        } catch (Exception e) {
                            System.out.println("2");
                            Log.e("t1", e.getMessage(), e);
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
                params.put("kind", s);
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
}
