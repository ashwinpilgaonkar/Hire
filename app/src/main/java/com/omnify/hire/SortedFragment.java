package com.omnify.hire;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

import static com.omnify.hire.SortingService.MERGESORT;
import static com.omnify.hire.SortingService.QUICKSORT;

public class SortedFragment extends Fragment {

    @BindView(R.id.sorted_tableview) TableView<String[]> SortedTableview;

    public SortedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(getSortedArrays);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(SortingService.ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(getSortedArrays, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sorted, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    private BroadcastReceiver getSortedArrays = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] headers = {getActivity().getString(R.string.quick_sort_title), getActivity().getString(R.string.merge_sort_title)};
            String[][] data = new String[20][20];

            //Get sorted arrays from service
            int quicksortarray[] = intent.getIntArrayExtra(QUICKSORT);
            int mergesortarray[] = intent.getIntArrayExtra(MERGESORT);

            for(int i=0; i<20; i++){
                //fill first row of table with quicksort values
                data[i][0] = String.valueOf(quicksortarray[i]);

                //fill second row of table with mergesort values
                data[i][1] = String.valueOf(mergesortarray[i]);
            }

            SortedTableview.setDataAdapter(new SimpleTableDataAdapter(getActivity(), data));
            SortedTableview.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), headers));
        }
    };
}