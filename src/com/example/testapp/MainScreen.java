package com.example.testapp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainScreen extends Activity implements OnClickListener, OnCheckedChangeListener{

	/** Type of tractor variables **/
	RadioGroup driveTrainList;
	int previousTractorId = 0;
	
	/** Type of implement variables **/
	RadioGroup implementList;
	int previousImplementId = 0;
	
	/** Miscellaneous variables **/
	EditText ptoEditText;
	EditText speedEditText;
	ImageButton infoSelectButton;
	
    /** Initialization of general variables **/
	int ptoVal;
    int speedVal;
    int tractorType = 0;
    int implementType = 0;
    
	/** Output variables **/
	TextView W2PRatio_text;
	TextView totalWeightTarget_text;
	TextView frontAxlePercent_text;
	TextView frontAxleLb_text;
	TextView rearAxlePercent_text;
	TextView rearAxleLb_text;
	int W2PRatio;
	int tTracWeight;
	double tFrontAxleP;
	double tFrontAxleW;
	int tRearAxleP;
	double tRearAxleW;
    
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

}
