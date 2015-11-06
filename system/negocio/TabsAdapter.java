package krys.threer.system.negocio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import krys.threer.system.gui.MapFragment;
import krys.threer.system.gui.StoreListFragment;

/**
 * Created by Krys on 06/11/2015.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {

    private static int TOTAL_TABS = 2;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new MapFragment();
        }
        if(position == 1){
            return new StoreListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
