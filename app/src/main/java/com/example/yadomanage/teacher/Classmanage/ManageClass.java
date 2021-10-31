package com.example.yadomanage.teacher.Classmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.adapter.StudentAdapter;
import com.example.yadomanage.teacher.TeacherHome;
import com.example.yadomanage.teacher.TeacherLogin;
import com.example.yadomanage.teacher.classfragment.FragmentClassStudent;
import com.example.yadomanage.teacher.classfragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ManageClass extends AppCompatActivity {
    ImageView back;
    String idclass,nameclass,linkclass;
    TextView malop;
    BottomNavigationView mNavigationView;
    ViewPager mViewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_class);

        toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            idclass = bundle.getString("Key_1", "");
            nameclass =bundle.getString("Key_2", "");
            linkclass =bundle.getString("Key_3", "");
        }


        //malop = findViewById(R.id.txtmalop);
        mNavigationView = findViewById(R.id.bottomnavi);
        mViewPager = findViewById(R.id.viewPager);


        setUpViewPager();
       // senDataToFragment(idclass);
        mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_newfeed:
                        mViewPager.setCurrentItem(1);
                        break;
//                    case R.id.action_chat:
//                        mViewPager.setCurrentItem(2);
//                        break;

                }
                return true;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_edit:
                        Intent intentmark = new Intent(ManageClass.this,ClassViewMark.class);
                        Bundle bundlemark = new Bundle();
                        bundlemark.putString("Key_1",idclass); // Truyền một String
                        intentmark.putExtras(bundlemark);
                        startActivity(intentmark);
                        return true;
                    case R.id.action_manage:
                        Intent intenup = new Intent(ManageClass.this,UpdateClass.class);
                        Bundle bundleup = new Bundle();
                        bundleup.putString("Key_1",idclass);
                        bundleup.putString("Key_2",nameclass);
                        bundleup.putString("Key_3",linkclass);// Truyền một String
                        intenup.putExtras(bundleup);
                        startActivity(intenup);
                        return true;
                    case R.id.action_delete:
                        Toast.makeText(getApplication(),"Item3 clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_addstudent:
                        Intent intent = new Intent(ManageClass.this,AddStudent.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Key_1",idclass); // Truyền một String
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }

            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Dashboard = new Intent(
                        ManageClass.this, TeacherHome.class);
                startActivity(Dashboard);
            }
        });
        toolbar.setTitle("  "+idclass);

//        malop.setText(idclass);
//        back = findViewById(R.id.icback);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }




    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                            break;
                        case 1:
                            mNavigationView.getMenu().findItem(R.id.action_newfeed).setChecked(true);
                            break;
//                        case 2:
//                            mNavigationView.getMenu().findItem(R.id.action_chat).setChecked(true);
//                            break;
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void senDataToFragment(String data){
        FragmentClassStudent fragmentClassStudent = new FragmentClassStudent();

        Bundle bundle = new Bundle();
        bundle.putString("key_idclass", data);

        fragmentClassStudent.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, fragmentClassStudent);
        fragmentTransaction.commit();
    }

    public String getId(){
        return idclass;
    }

}