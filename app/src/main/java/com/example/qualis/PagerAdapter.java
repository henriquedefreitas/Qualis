package com.example.qualis;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.qualis.conferencia.ConferenciaFragment;
import com.example.qualis.correlacao.CorrelacaoFragment;
import com.example.qualis.periodicos.PeriodicoFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new PeriodicoFragment();
            case 1: return new ConferenciaFragment();
            case 2: return new CorrelacaoFragment();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
