package com.simrge.playview.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;
import com.simrge.playview.MainActivity;
import com.simrge.playview.R;

public class List extends SherlockListFragment {

	private CardsAdapter adapter;
	private Fragment mContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group,
			Bundle saved) {
		View v = inflater.inflate(R.layout.list_main_layout, group, false);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		

		adapter = new CardsAdapter(getActivity().getApplicationContext(),
				android.R.id.list);
		setListAdapter(adapter);

	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mContent = getActivity().getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		super.onCreate(savedInstanceState);
	}

	

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			// app icon in action bar clicked; go home
			getFragmentManager().popBackStack();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class CardsAdapter extends ArrayAdapter<String> {
		String[] colorlist = { "#f2a400", "#e00707", "#4ac925", "#00d5f2",
				"#f2a400" };
		ImageView img;
		
		Context mContext;

		public CardsAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);

			this.mContext = context;
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View row = convertView;

			if (row == null) {

				row = getActivity().getLayoutInflater().inflate(
						R.layout.recent_activity_row, parent, false);

				
				img = (ImageView) row.findViewById(R.id.imageView1);
				img.setBackgroundColor(Color.parseColor(colorlist[position]));

			}

			return row;

		}
	}

}
