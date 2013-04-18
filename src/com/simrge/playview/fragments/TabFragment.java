package com.simrge.playview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.simrge.playview.R;


public class TabFragment extends SherlockFragment {
	// your member variables here
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.tabfragment_layout, container,
				false);

		return view;
	}

}