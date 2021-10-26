package com.example.yadomanage.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yadomanage.R;
import com.example.yadomanage.sharedPreferences.DataLocalManager;
import com.example.yadomanage.teacher.teacherflagment.FragmentTeacherAdd;
import com.example.yadomanage.teacher.teacherflagment.FragmentTeacherChange;
import com.example.yadomanage.teacher.teacherflagment.FragmentTeacherHome;
import com.example.yadomanage.teacher.teacherflagment.FragmentTeacherInfo;
import com.example.yadomanage.teacher.teacherflagment.FragmentTeacherNewfeed;
import com.google.android.material.navigation.NavigationView;

public class TeacherHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    TextView emailteacher,  nameteacher;

    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_STREAM_VIEW=1;
    private static final int FRAGMENT_HISTORY=2;
    private static final int FRAGMENT_PROFILE=3;
    private static final int FRAGMENT_CHANGEPASS=4;

    private int mCurrentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(TeacherHome.this,mDrawerLayout,toolbar,R.string.nav_draw_open,R.string.nav_draw_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

        emailteacher= (TextView) headerView.findViewById(R.id.emailteacher);
        nameteacher= (TextView) headerView.findViewById(R.id.nameteacher);

        emailteacher.setText(DataLocalManager.getStringEmail());
        nameteacher.setText(DataLocalManager.getStringName());

        //emailteacher.getText().toString();

        navigationView.setNavigationItemSelectedListener(this);




        replaceFragment(new FragmentTeacherHome());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            if(mCurrentFragment!= FRAGMENT_HOME){
                replaceFragment(new FragmentTeacherHome());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }
        else if(id == R.id.nav_stream){
            if(mCurrentFragment!= FRAGMENT_STREAM_VIEW){
                replaceFragment(new FragmentTeacherAdd());
                mCurrentFragment = FRAGMENT_STREAM_VIEW;
            }
        }

        else if(id == R.id.nav_history){
            if(mCurrentFragment!= FRAGMENT_HISTORY){
                replaceFragment(new FragmentTeacherNewfeed());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if(id == R.id.nav_my_profile){
            if(mCurrentFragment!= FRAGMENT_PROFILE){
                replaceFragment(new FragmentTeacherInfo());
                mCurrentFragment = FRAGMENT_PROFILE;
            }
        }
        else if(id == R.id.nav_change_password){
            if(mCurrentFragment!= FRAGMENT_CHANGEPASS){
                replaceFragment(new FragmentTeacherChange());
                mCurrentFragment = FRAGMENT_CHANGEPASS;
            }}



        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }


}