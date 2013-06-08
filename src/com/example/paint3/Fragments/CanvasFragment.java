package com.example.paint3.Fragments;

import com.example.paint3.Views.PictureView;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CanvasFragment extends Fragment {
	private PictureView v;	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = new PictureView(getActivity());
		System.out.println("HEREd");
		return v;
	}

}
