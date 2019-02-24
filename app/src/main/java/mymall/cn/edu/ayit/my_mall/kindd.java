package mymall.cn.edu.ayit.my_mall;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentTransaction;


public class kindd extends AppCompatActivity  implements mymall.cn.edu.ayit.my_mall.kind_ty.OnFragmentInteractionListener, mymall.cn.edu.ayit.my_mall.kind_women.OnFragmentInteractionListener,mymall.cn.edu.ayit.my_mall.kind_dz.OnFragmentInteractionListener,
        mymall.cn.edu.ayit.my_mall.kind_life.OnFragmentInteractionListener,mymall.cn.edu.ayit.my_mall.kind_man.OnFragmentInteractionListener,mymall.cn.edu.ayit.my_mall.kind_mz.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kindd);
        Button nz,dz,ty,nanz,sh,mz;
        nz  = (Button)findViewById(R.id.nz);
        dz  = (Button)findViewById(R.id.dz);
        ty  = (Button)findViewById(R.id.ty);
        nanz  = (Button)findViewById(R.id.nanz);
        sh  = (Button)findViewById(R.id.sh);
        mz  = (Button)findViewById(R.id.mz);
        dz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_dz  kinddz = new kind_dz();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kinddz);
                fragmentTransaction.commit();
            }
        });
        nz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_women  kind = new  kind_women();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kind);
                fragmentTransaction.commit();
            }
        });
        ty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_ty  kind = new  kind_ty();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kind);
                fragmentTransaction.commit();
            }
        });
        nanz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_man  kind = new  kind_man();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kind);
                fragmentTransaction.commit();
            }
        });
        sh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_life  kind = new  kind_life();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kind);
                fragmentTransaction.commit();
            }
        });
        mz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kind_mz  kind = new  kind_mz();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_kind, kind);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
