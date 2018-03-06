package com.micrain.smartturnning;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;

public class MainActivity extends Activity {

	private GridLayout gridMain ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gridMain = (GridLayout) findViewById(R.id.gridMain);
		gridMain.setRowCount(4);
		gridMain.setColumnCount(4);
		//init(4,gridMain);
		
	}
	
	/*void init(int col,GridLayout gridlayout){
		
		gridlayout.setColumnCount(col);
		gridlayout.setRowCount(col);
		gridlayout.setRowOrderPreserved(true);
		//gridlayout.getRowCount();
		
	}*/
	
}
