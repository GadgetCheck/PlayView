package com.simrge.playview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simrge.playview.fragments.List;
import com.simrge.playview.fragments.TabFragment;

public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayHomeAsUpEnabled(true);
		ActionBar.Tab tab1 = bar.newTab();
		ActionBar.Tab tab2 = bar.newTab();
		ActionBar.Tab tab3 = bar.newTab();
	
		tab1.setText("TAB1");
		tab2.setText("TAB2");
		tab3.setText("TAB3");
		
		tab1.setTabListener(new MyTabListener());
		tab2.setTabListener(new MyTabListener());
		tab3.setTabListener(new MyTabListener());
	
		bar.addTab(tab1);
		bar.addTab(tab2);
		bar.addTab(tab3);
		
	}

	public void switchContent(Fragment fragment) {
		Fragment mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(android.R.id.content, fragment).commit();
		Log.e("MAIN ACTIVITY", "");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class MyTabListener implements ActionBar.TabListener {
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (tab.getPosition() == 0) {
				List frag = new List();
				ft.replace(android.R.id.content, frag);
			} else if (tab.getPosition() == 1) {
				List frag = new List();
				ft.replace(android.R.id.content, frag);
			} else {
				TabFragment frag = new TabFragment();
				ft.replace(android.R.id.content, frag);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}
	}
}
