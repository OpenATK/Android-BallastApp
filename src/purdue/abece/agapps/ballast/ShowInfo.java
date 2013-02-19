package purdue.abece.agapps.ballast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ShowInfo extends Activity{
	private ImageButton goBackSelectButton;


	
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.show_info);
       
        goBackSelectButton = (ImageButton)findViewById(R.id.ib_go_back);
        
        goBackSelectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
            	//Return to main menu screen
                finish();
            }
        });           
    }
}