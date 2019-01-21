package com.example.mhanif.project;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ItemAndShop extends AppCompatActivity {
String category_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_and_shop);


        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        BlankFragment blankFragment=new BlankFragment();
        blankFragment.setArguments(getIntent().getExtras());
        // Add Fragments to adapter one by one
        adapter.addFragment(blankFragment, "Items");
        adapter.addFragment(new BlankFragment2(), "Shops");

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        if(savedInstanceState!=null){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.home_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        // Handle item selection

        {
            if(R.id.logout==menuItem.getItemId()){
                Toast.makeText(getApplicationContext(),"logging out.....",Toast.LENGTH_LONG);
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                startActivity(new Intent(getApplicationContext(),Login.class));
                return true;
            }
            else if(menuItem.getItemId()== R.id.profile){
                startActivity(new Intent(getApplicationContext(),profile.class));
                return true;
            }
            else
                return super.onOptionsItemSelected(menuItem);
        }



    }
    public void ontoggleclick1(View v){
        startActivity(new Intent(getApplicationContext(),addItem.class));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

