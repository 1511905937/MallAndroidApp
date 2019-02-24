package mymall.cn.edu.ayit.my_mall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link kind_mz.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link kind_mz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class kind_mz extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public kind_mz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment kind_mz.
     */
    // TODO: Rename and change types and number of parameters
    public static kind_mz newInstance(String param1, String param2) {
        kind_mz fragment = new kind_mz();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kind_mz, container, false);
        ImageButton a,b,c,d;
        a=(ImageButton)view.findViewById(R.id.a);
        b=(ImageButton)view.findViewById(R.id.b);
        c=(ImageButton)view.findViewById(R.id.c);
        d=(ImageButton)view.findViewById(R.id.d);
        a .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","口红");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        b .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","面膜");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        c .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","水乳");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
        d .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent it=new Intent();
                    it.setClassName("mymall.cn.edu.ayit.my_mall","mymall.cn.edu.ayit.my_mall.listd");
                    Bundle argBundle=new Bundle();
                    argBundle.putString("data","眉笔");

                    it.putExtras(argBundle);
                    startActivity(it);
                }
                catch (Exception e)
                {
                    System.out.println("01"+e);
                }
            }
        });
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
