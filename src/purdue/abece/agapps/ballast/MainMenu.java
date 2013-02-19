package purdue.abece.agapps.ballast;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity {

	/** Type of tractor variables **/
	private ImageButton tractor_2wd;
	private ImageButton tractor_frntAsst;
	private ImageButton tractor_4wd;
	private int previousTractorId = 0;
	/******************************/

	/** Type of implement variables **/
	private ImageButton implement_semi;
	private ImageButton implement_full;
	private ImageButton implement_towable;
	private int previousImplementId = 0;
	/******************************/

	/** Miscellaneous variables **/
	private EditText ptoEditText;
	private EditText speedEditText;
	private ImageButton infoSelectButton;
	// private Button calculateButton;
	/******************************/

	// Initialization of general variables
	private float ptoVal;
	private float speedVal;
	private int tractorType = 0;
	private int implementType = 0;

	private ImageView outputTractor;
	//private ImageView WeightBar;

	private TextView W2PRatio_text;
	private TextView totalWeightTarget_text;
	private TextView frontAxlePercent_text;
	private TextView frontAxleLb_text;
	private TextView rearAxlePercent_text;
	private TextView rearAxleLb_text;

	private int W2PRatio;
	private int tTracWeight;
	private double tFrontAxleP;
	private double tFrontAxleW;
	private int tRearAxleP;
	private double tRearAxleW;

	private int[][] lookup = new int[][] { { 25, 40, 55 }, { 30, 45, 60 },
			{ 35, 45, 60 } };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		// ImageButton
		tractor_2wd = (ImageButton) findViewById(R.id.ib_2wdType);
		tractor_4wd = (ImageButton) findViewById(R.id.ib_4wdType);
		tractor_frntAsst = (ImageButton) findViewById(R.id.ib_fwdType);
		
		tractor_frntAsst.setFocusableInTouchMode(true);
		tractor_frntAsst.requestFocus();

		implement_semi = (ImageButton) findViewById(R.id.ib_semiType);
		implement_full = (ImageButton) findViewById(R.id.ib_fullyType);
		implement_towable = (ImageButton) findViewById(R.id.ib_towableType);
		infoSelectButton = (ImageButton) findViewById(R.id.ib_info);
		// EditText
		ptoEditText = (EditText) findViewById(R.id.et_pto);
		speedEditText = (EditText) findViewById(R.id.et_speed);
		// Output Tractor
		outputTractor = (ImageView) findViewById(R.id.TractorOutput);
		//WeightBar = (ImageView) findViewById(R.id.WeightBar);

		// Calculate button
		// calculateButton = (Button) findViewById(R.id.b_output);

		/** Button and TextView variables **/
		infoSelectButton = (ImageButton) findViewById(R.id.ib_info);
		frontAxlePercent_text = (TextView) findViewById(R.id.frontAxle_percent);
		frontAxleLb_text = (TextView) findViewById(R.id.frontAxle_lb);
		rearAxlePercent_text = (TextView) findViewById(R.id.rearAxle_percent);
		rearAxleLb_text = (TextView) findViewById(R.id.rearAxle_lb);
		W2PRatio_text = (TextView) findViewById(R.id.tv_WTPR);
		totalWeightTarget_text = (TextView) findViewById(R.id.tv_TWT);

		/*********************************************/

		// Initialize selection
		tractorType = 2;
		previousTractorId = 2;
		tractor_frntAsst.setBackgroundResource(R.drawable.jd_fwa_highlighted);
		outputTractor.setImageResource(R.drawable.jd_fwa);

		implementType = 2;
		previousImplementId = 2;
		implement_semi.setBackgroundResource(R.drawable.jd_semimounted_plow_highlighted);

		Button.OnClickListener listener = new Button.OnClickListener() {
			public void onClick(View buttonPress) {
				/**** Debounce the push buttons ****/
				if (buttonPress == tractor_2wd
						|| buttonPress == tractor_frntAsst
						|| buttonPress == tractor_4wd) {
					switch (previousTractorId) {
					case 1:
						tractor_2wd.setBackgroundResource(R.drawable.two_wd);
						break;
					case 2:
						tractor_frntAsst.setBackgroundResource(R.drawable.frnt_asst);
						break;
					case 3:
						tractor_4wd.setBackgroundResource(R.drawable.four_wd);
						break;
					case 0:
						break;
					}
				}

				if (buttonPress == implement_towable
						|| buttonPress == implement_semi
						|| buttonPress == implement_full) {
					switch (previousImplementId) {
					case 1:
						implement_towable.setBackgroundResource(R.drawable.tow);
						break;
					case 2:
						implement_semi.setBackgroundResource(R.drawable.semimnt);
						break;
					case 3:
						implement_full.setBackgroundResource(R.drawable.fullmnt);
						break;
					case 0:
						break;
					}
				}
				/** END OF DEBOUNCING BUTTON **/

				// Set the flags and previousTractorId flag
				if (buttonPress == tractor_2wd) {
					tractorType = 1;
					previousTractorId = 1;
					tractor_2wd.setBackgroundResource(R.drawable.jd_2wd_highlighted);
					outputTractor.setImageResource(R.drawable.jd_2wd);
				} else if (buttonPress == tractor_frntAsst) {
					tractorType = 2;
					previousTractorId = 2;
					tractor_frntAsst.setBackgroundResource(R.drawable.jd_fwa_highlighted);
					outputTractor.setImageResource(R.drawable.jd_fwa);
				} else if (buttonPress == tractor_4wd) {
					tractorType = 3;
					previousTractorId = 3;
					tractor_4wd.setBackgroundResource(R.drawable.jd_4wd_highlighted);
					outputTractor.setImageResource(R.drawable.jd_4wd);
				} else if (buttonPress == implement_towable) {
					implementType = 1;
					previousImplementId = 1;
					implement_towable.setBackgroundResource(R.drawable.jd_drill_towable_highlighted);
				} else if (buttonPress == implement_semi) {
					implementType = 2;
					previousImplementId = 2;
					implement_semi.setBackgroundResource(R.drawable.jd_semimounted_plow_highlighted);
				} else if (buttonPress == implement_full) {
					implementType = 3;
					previousImplementId = 3;
					implement_full.setBackgroundResource(R.drawable.fully_subsoiler_jtw_highlighted);
				} else if (buttonPress == infoSelectButton) {
					Intent intent = new Intent(getApplicationContext(),
							ShowInfo.class);
					startActivity(intent);
				}
				calculate();
			} // End of onClick method
		};// end of OnCLickListener
		
		TextWatcher changeWatcher = new TextWatcher() {
			public void afterTextChanged(Editable arg0) {
				calculate();				
			}
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
		};
		
		speedEditText.addTextChangedListener(changeWatcher);
		ptoEditText.addTextChangedListener(changeWatcher);

		// setOnClickListener
		tractor_2wd.setOnClickListener(listener);
		tractor_frntAsst.setOnClickListener(listener);
		tractor_4wd.setOnClickListener(listener);
		implement_semi.setOnClickListener(listener);
		implement_full.setOnClickListener(listener);
		implement_towable.setOnClickListener(listener);
		infoSelectButton.setOnClickListener(listener);
		// calculateButton.setOnClickListener(listener);
		
		calculate(); //Calculate based on initial values
	}// End of onCreate method
	
	private void calculate(){
		tFrontAxleP = lookup[implementType - 1][tractorType - 1];
		/*if (tFrontAxleP == 25){
			WeightBar.setImageResource(R.drawable.bar_75_25);
		}
		else if(tFrontAxleP == 30){
			WeightBar.setImageResource(R.drawable.bar_70_30);
		}
		else if(tFrontAxleP == 35){
			WeightBar.setImageResource(R.drawable.bar_65_35);
		}
		else if(tFrontAxleP == 40){
			WeightBar.setImageResource(R.drawable.bar_60_40);
		}
		else if(tFrontAxleP == 45){
			WeightBar.setImageResource(R.drawable.bar_55_45);
		}
		else if(tFrontAxleP == 55){
			WeightBar.setImageResource(R.drawable.bar_45_55);
		}
		else if(tFrontAxleP == 60){
			WeightBar.setImageResource(R.drawable.bar_40_60);
		}*/
		tRearAxleP = (int) (100 - tFrontAxleP);
		rearAxlePercent_text.setText(String.valueOf(tRearAxleP));
		frontAxlePercent_text.setText(String.valueOf((int) tFrontAxleP));
		
		int speedVaild = 1;
		int ptoVaild = 1;
		
		speedEditText.setBackgroundColor(Color.WHITE);
		ptoEditText.setBackgroundColor(Color.WHITE);
		
		try{
			speedVal = Float.parseFloat(speedEditText.getText().toString());
			if (speedVal < 3 || speedVal > 10){
				//toast();
				speedVaild = 0;
			}
		} catch (NumberFormatException e) {
			speedVaild = 0;
		}
		try {
			ptoVal = Float.parseFloat(ptoEditText.getText().toString());
			if (ptoVal < 50 || ptoVal >700){
				//toast();
				ptoVaild = 0;
			}
		} catch (NumberFormatException e) {
			ptoVaild = 0;
		}
		
		if(speedVaild == 0){
			speedEditText.setBackgroundColor(Color.RED);
		}
		if(ptoVaild == 0){
			ptoEditText.setBackgroundColor(Color.RED);
		}
		
		if(speedVaild == 0 || ptoVaild == 0){
			W2PRatio_text.setText("-");
			totalWeightTarget_text.setText("-");
			frontAxleLb_text.setText("-");
			rearAxleLb_text.setText("-");
		} else {
			// Calculation area
			W2PRatio = (int) Math.min((670 / speedVal) * 0.94 * 0.93, 160);
			tTracWeight = Math.round(W2PRatio * ptoVal / 50) * 50;
			tFrontAxleP = lookup[implementType - 1][tractorType - 1];
			tFrontAxleW = Math.round(tFrontAxleP * tTracWeight / 5000) * 50;
			tRearAxleW = tTracWeight - tFrontAxleW;

			W2PRatio_text.setText(String.valueOf(W2PRatio));
			totalWeightTarget_text.setText(String.valueOf((int) tTracWeight));
			frontAxleLb_text.setText(String.valueOf((int) tFrontAxleW));
			rearAxleLb_text.setText(String.valueOf((int) tRearAxleW));
		}
		
	}
	private void toast(){
		Toast error = Toast.makeText(getApplicationContext(), "This ballast calculator is applicable for tractors from 50 to 700 HP pulling soil engaging implments at typlical speeds of 3 to 10 MPH. Please change your inputs.", Toast.LENGTH_LONG);
		error.setGravity(Gravity.CENTER, 0, -2);
		error.show();
	}
}