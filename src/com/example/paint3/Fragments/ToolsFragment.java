package com.example.paint3.Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paint3.R;
import com.example.paint3.Views.ToolsView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ToolsFragment extends Fragment {
	ToolsView t;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tools_view, null); //set parent view later
		// Inflate the layout for this fragment
		//t = new ToolsView(getActivity());
		//return t;
	}
}