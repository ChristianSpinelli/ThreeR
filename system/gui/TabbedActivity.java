package krys.threer.system.gui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import krys.threer.R;
import krys.threer.RecycleStore.dao.RecycleStoreLocalDataBase;
import krys.threer.system.negocio.TabsAdapter;
import krys.threer.user.dao.UserSession;
import krys.threer.user.gui.LoginActivity;

public class TabbedActivity extends ActionBarActivity implements ActionBar.TabListener{

    private ViewPager viewPager;
    private ActionBar actionBar;
    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        viewPager = (ViewPager) findViewById(R.id.tabspager);
        actionBar = getSupportActionBar();
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsAdapter);

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#238e23")));

        ActionBar.Tab mapTab = actionBar.newTab().setText("Mapa").setTabListener(this);
        ActionBar.Tab storeListTab = actionBar.newTab().setText("Pontos de reciclagem").setTabListener(this);
        ActionBar.Tab contactTab = actionBar.newTab().setText("Contato").setTabListener(this);

        actionBar.addTab(mapTab);
        actionBar.addTab(storeListTab);
        actionBar.addTab(contactTab);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(TabbedActivity.this,SelectCategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_threer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.id_logout){
            UserSession session = new UserSession(this);
            RecycleStoreLocalDataBase recycleStore = new RecycleStoreLocalDataBase(this);

            session.setUserLogged(false);
            session.userClearAll();
            recycleStore.clearAll();

            finish();
            Intent intent = new Intent(TabbedActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        return true;
    }

}
