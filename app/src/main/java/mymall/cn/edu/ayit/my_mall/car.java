package mymall.cn.edu.ayit.my_mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link car.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link car#newInstance} factory method to
 * create an instance of this fragment.
 */
public class car extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public car() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment car.
     */
    // TODO: Rename and change types and number of parameters
    public static car newInstance(String param1, String param2) {
        car fragment = new car();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    View cFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_car, container, false);
        cFragment=view;
        myapplication m=new myapplication();
        if(m.getSessionid()==null)
        {
            Intent it=new Intent();
            it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.login");
            startActivity(it);
        }
            //请求地址
            String url=myapplication.ip+"Shopcar";
            final String tag="login";
            //请求队列
            final RequestQueue requestQueue= Volley.newRequestQueue(getContext());
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
                                        LinearLayout course_linearLayout = (LinearLayout) cFragment.findViewById(R.id.one);
                                        LinearLayout l = new LinearLayout(course_linearLayout.getContext());

                                        CheckBox box=new CheckBox(course_linearLayout.getContext());
                                        box.setText(pro_name);
                                        int proid=Integer.parseInt(pro_id);
                                        int su=Integer.parseInt(pro_price);
                                        sum.add(su);
                                        p.add(proid);
                                        box.setId(proid);
                                        course_linearLayout.addView(box);
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
                                LinearLayout course_linearLayout = (LinearLayout) cFragment.findViewById(R.id.one);
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

        Button bb=(Button)view.findViewById(R.id.sum);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int summ=0;
                final ArrayList<Integer> cp=new ArrayList<Integer>();
                for(int i = 0;i<p.size();i++){
                    CheckBox c;
                    c=(CheckBox)view.findViewById(p.get(i));
                    if(c.isChecked()==true)
                    {
                        summ+=sum.get(i);
                        cp.add(p.get(i));
                    }
                }
                System.out.println(summ);
                String url=myapplication.ip+"Sum";
                net.sf.json.JSONArray jsonq = net.sf.json.JSONArray.fromObject(cp);
                final String s=jsonq.toString();
                JSON json;
                StringRequest request1= new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("1"))
                                {
                                    AlertDialog.Builder bu=new AlertDialog.Builder(getContext());
                                    bu.setTitle("提示：");
                                    bu.setMessage("请自觉给张以恒转钱！");
                                    bu.setPositiveButton("确定",null);
                                    bu.show();
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
                        params.put("sum", s);

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
                requestQueue.add(request1);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                1000*30, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //设置tag标签
        request.setTag(tag);
        //将请求添加到队列中
        requestQueue.add(request);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
