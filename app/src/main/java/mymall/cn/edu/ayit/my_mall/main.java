package mymall.cn.edu.ayit.my_mall;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class main extends AppCompatActivity implements mymall.cn.edu.ayit.my_mall.index.OnFragmentInteractionListener,mymall.cn.edu.ayit.my_mall.car.OnFragmentInteractionListener
,mymall.cn.edu.ayit.my_mall.person.OnFragmentInteractionListener{

    private FrameLayout aFrameLayout,bFrameLayout,cFrameLayout,dFrameLayout;
    private index index;

    private car car;
    private person person;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // this.moveTaskToBack(true);
        chickFragment1();
        aFrameLayout = (FrameLayout)findViewById(R.id.layout1);
        bFrameLayout = (FrameLayout)findViewById(R.id.layout2);
        cFrameLayout = (FrameLayout)findViewById(R.id.layout3);
        dFrameLayout = (FrameLayout)findViewById(R.id.layout4);
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_content,new index());


        aFrameLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chickFragment1();
            }
        });

        bFrameLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chickFragment2();
            }
        });
        cFrameLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chickFragment3();
            }
        });
        dFrameLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chickFragment4();
            }
        });
    }
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch(arg0.getId()){
            case R.id.layout1:

                break;
            case R.id.layout2:
                chickFragment2();
                break;

        }
    }
    private void chickFragment1(){
        index = new index();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, index);
        fragmentTransaction.commit();
    }

    private void chickFragment2(){
       Intent intent=new Intent(main.this,kindd.class);
       startActivity(intent);
    }
    private void chickFragment3(){
        car = new car();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, car);
        fragmentTransaction.commit();
    }

    private void chickFragment4(){
        person= new person();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, person);
        fragmentTransaction.commit();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
