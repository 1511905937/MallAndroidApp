package mymall.cn.edu.ayit.my_mall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.CheckBox;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class userlike extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlike);

        myapplication m=new myapplication();
        if(m.getSessionid()==null)
        {
            Intent it=new Intent();
            it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.login");
            startActivity(it);
        }
        //请求地址
        String url=myapplication.ip+"Userlike";
        final String tag="login";
        //请求队列
        final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final ArrayList<Integer> p=new ArrayList<Integer>();   //pro_id
        final ArrayList<Integer> sum=new ArrayList<Integer>();   //price
        //创建StringRequest,定义字符串请求的请求方式为post(默认是get)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("9" + response.toString());
                            JSONArray json = JSONArray.fromObject(response);
                            for (int i = 0; i < json.size(); i++) {
                                System.out.println("1");
                                JSONObject pro = new JSONObject();
                                pro = json.getJSONObject(i);
                                final String pro_id = pro.getString("pro_id");
                                String pro_name = pro.getString("pro_name");
                                String pro_content = pro.getString("pro_content");
                                String pic1 = pro.getString("pro_picture1");
                                String pro_shop = pro.getString("pro_shop");
                                String pro_price = pro.getString("pro_price");
                                LinearLayout course_linearLayout = (LinearLayout) findViewById(R.id.one);
                                LinearLayout l = new LinearLayout(course_linearLayout.getContext());

                                int proid=Integer.parseInt(pro_id);
                                int su=Integer.parseInt(pro_price);
                                sum.add(su);
                                p.add(proid);
                                course_linearLayout.addView(l);


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


                            }
                            LinearLayout course_linearLayout = (LinearLayout) findViewById(R.id.one);
                            TextView ttt=new TextView(course_linearLayout.getContext());
                            ttt.setText("\n\n\n");
                            course_linearLayout.addView(ttt);


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
}
