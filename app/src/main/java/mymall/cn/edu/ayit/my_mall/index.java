package mymall.cn.edu.ayit.my_mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link index.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link index#newInstance} factory method to
 * create an instance of this fragment.
 */
public class index extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public index() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment index.
     */
    // TODO: Rename and change types and number of parameters
    public static index newInstance(String param1, String param2) {
        index fragment = new index();
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
    private ImageButton lingshi,yinliao,nvzhuang,shenghuoyongpin,tiyu,nanzhuang,xiebao,peishi;
    private Button search_btn;
    private EditText search_text;
    View cFragment;

    public View course(){
        //找到周几的线性布局


        //请求地址
        String url=myapplication.ip+"Index";
        final String tag="login";
        //请求队列
        final RequestQueue requestQueue= Volley.newRequestQueue(getContext());//(getApplicationContext());
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
                            JSONArray json = JSONArray.fromObject(response);
                            for (int i = 0; i < json.size(); i++) {
                                System.out.println("1");
                                JSONObject pro=new JSONObject();
                                pro=json.getJSONObject(i);
                                final  String pro_id = pro.getString("pro_id");
                                String pro_name = pro.getString("pro_name");
                                String pro_content= pro.getString("pro_content");
                                String pic1 = pro.getString("pro_picture1");
                                String pic2 = pro.getString("pro_picture2");
                                String pic3= pro.getString("pro_picture3");
                                String pro_sale=pro.getString("pro_sale");
                                String pro_shop=pro.getString("pro_shop");
                                String pro_price=pro.getString("pro_price");
                                LinearLayout course_linearLayout  = (LinearLayout)cFragment.findViewById(R.id.one);
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

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                1000*30, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //设置tag标签
        request.setTag(tag);
        //将请求添加到队列中
        requestQueue.add(request);
        return cFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_index, container, false);

        cFragment=view;
        lingshi=(ImageButton)view.findViewById(R.id.lingshi);
        yinliao=(ImageButton)view.findViewById(R.id.yinliao);
        nvzhuang=(ImageButton)view.findViewById(R.id.nvzhuang);
        shenghuoyongpin=(ImageButton)view.findViewById(R.id.shenghuoyongpin);
        tiyu=(ImageButton)view.findViewById(R.id.tiyu);
        nanzhuang=(ImageButton)view.findViewById(R.id.nanzhuang);
        xiebao=(ImageButton)view.findViewById(R.id.xiebao);
        peishi=(ImageButton)view.findViewById(R.id.peishi);

        search_btn=(Button)view.findViewById(R.id.search_btn);
        search_text=(EditText)view.findViewById(R.id.search_text);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent();
                it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                Bundle argBundle=new Bundle();
                argBundle.putString("data",search_text.getText().toString().trim());

                it.putExtras(argBundle);
                startActivity(it);
            }
        });
        lingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","零食");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }

            }
        });
        yinliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","饮料");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        nvzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","女装");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        shenghuoyongpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","生活");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        tiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","体育");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        nanzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","男装");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        xiebao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","鞋包");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        peishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","配饰");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });

        view =course();
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
