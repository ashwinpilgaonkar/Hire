package com.omnify.hire;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class SortingService extends IntentService {

    public static final String ACTION = "com.omnify.hire.Broadcast";
    public static final String MERGESORT = "MergeSortArray";
    public static final String QUICKSORT = "QuickSortArray";

    public SortingService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //sort arrays
        int quicksortarray[] = MainActivity.randomNum;
        quicksort(quicksortarray, 0, quicksortarray.length-1);

        int mergesortarray[] = MainActivity.randomNum;
        mergesort(mergesortarray, 0, mergesortarray.length);

        //Send a Broadcast Intent with the two sorted arrays
        Intent broadcastintent = new Intent(ACTION);
        broadcastintent.putExtra(QUICKSORT, quicksortarray);
        broadcastintent.putExtra(MERGESORT, mergesortarray);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastintent);
    }

    private void quicksort(int[] array, int low, int high){
        if (array == null || array.length == 0 || low >= high)
            return;

        //Pick the pivot- middle element of the given array
        int mid = low + (high - low)/2;
        int pivot = array[mid];

        int i=low;
        int j=high;
        while (i<=j) {

            //increment i till it reaches pivot
            while (array[i] < pivot)
                i++;

            //decrement j till it reaches pivot
            while (array[j] > pivot)
                j--;

            if (i<=j) {
                //swap
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        //The array has been divided into two parts
        //Recursively sort each of them
        if (low < j)
            quicksort(array, low, j);

        if (high > i)
            quicksort(array, i, high);
    }

    private void mergesort(int[] array, int low, int high){
        int size = high-low;
        if (size <= 1)
            return;

        int mid = low + size/2;

        // Recursively sort each section
        mergesort(array, low, mid);
        mergesort(array, mid, high);

        //Merge two sorted arrays
        int[] temp = new int[size];
        int i=low;
        int j = mid;

        for (int k=0; k<size; k++)
        {
            if (i == mid)
                temp[k] = array[j++];

            else if (j == high)
                temp[k] = array[i++];

            else if (array[j]<array[i])
                temp[k] = array[j++];

            else
                temp[k] = array[i++];
        }

        System.arraycopy(temp, 0, array, low, size);
    }
}