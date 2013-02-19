package purdue.abece.agapps.ballast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShowOutput extends Activity{
	/** Variables declaration **/
	private ImageButton goBackSelectButton;
	private ImageButton infoSelectButton;
	
	private TextView W2PRatio_text;
	private TextView totalWeightTarget_text;
	private TextView frontAxlePercent_text;
	private TextView frontAxleLb_text;
	private TextView rearAxlePercent_text;
	private TextView rearAxleLb_text;
	private int asd;
	
	//private int optSelect;
	
	private int W2PRatio;
	private int tTracWeight;
	private double tFrontAxleP;
	private double tFrontAxleW;
	private int tRearAxleP;
	private double tRearAxleW;
	
	private int[][] lookup = new int[][] {{25,40,55},{30,45,60},{35,45,60}};
	/************************************/
	
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.main_menu);
       
        /** Button and TextView variables **/
        goBackSelectButton = (ImageButton)findViewById(R.id.ib_go_back);
        infoSelectButton = (ImageButton)findViewById(R.id.ib_info);
        frontAxlePercent_text = (TextView)findViewById(R.id.frontAxle_percent);
        frontAxleLb_text = (TextView)findViewById(R.id.frontAxle_lb);
        rearAxlePercent_text = (TextView)findViewById(R.id.rearAxle_percent);
        rearAxleLb_text = (TextView)findViewById(R.id.rearAxle_lb);
        W2PRatio_text = (TextView)findViewById(R.id.tv_WTPR);
        totalWeightTarget_text = (TextView)findViewById(R.id.tv_TWT);
        
        /*********************************************/
        
        /** Retrieve the values from user inputs **/
        Bundle vals = getIntent().getExtras();
        int ptoVal = vals.getInt("ptoVal",0);
        int speedVal = vals.getInt("speedVal",0);
        int tractorType = vals.getInt("tractorType",0);
        int implementType = vals.getInt("implementType",0);
        /*********************************************/
        
        //Calculation area
        W2PRatio = (int) Math.min(670/speedVal*0.94*.93,160);
        tTracWeight = Math.round(W2PRatio*ptoVal/50)*50;
        tFrontAxleP = lookup[implementType-1][tractorType-1];
        tFrontAxleW = Math.round(tFrontAxleP*tTracWeight/5000)*50;
        tRearAxleP = (int) (100 - tFrontAxleP);
        tRearAxleW = tTracWeight - tFrontAxleW;
        
        
        W2PRatio_text.setText(String.valueOf(W2PRatio));
        totalWeightTarget_text.setText(String.valueOf((int)tTracWeight));
        frontAxlePercent_text.setText(String.valueOf((int)tFrontAxleP));
        frontAxleLb_text.setText(String.valueOf((int)tFrontAxleW));
        rearAxlePercent_text.setText(String.valueOf(tRearAxleP));
        rearAxleLb_text.setText(String.valueOf((int)tRearAxleW));
        
        
        goBackSelectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
            	//Return to main menu screen
                finish();
            }
        });        
    }
}