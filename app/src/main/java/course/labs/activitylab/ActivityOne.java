package course.labs.activitylab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ActivityOne extends Activity {

		// string for logcat documentation
		private final static String TAG = "Lab-ActivityOne";

	
		// lifecycle counts	
		//TODO:
		//Create 7 counter variables, each corresponding to a different one of the lifecycle callback methods.
		// You will need to increment these variables' values when their corresponding lifecycle methods get called.  

		private int onCreateCount = 0;
		private int onStartCount = 0;
		private int onResumeCount = 0;
		private int onPauseCount = 0;
		private int onStopCount = 0;
		private int onRestartCount = 0;
		private int onDestroyCount = 0;

	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_one);

			SharedPreferences prefs = getPreferences(MODE_PRIVATE);

			onCreateCount = prefs.getInt("onCreateCount", 0);
			onStartCount = prefs.getInt("onStartCount", 0);
			onResumeCount = prefs.getInt("onResumeCount", 0);
			onPauseCount = prefs.getInt("onPauseCount", 0);
			onStopCount = prefs.getInt("onStopCount", 0);
			onRestartCount = prefs.getInt("onRestartCount", 0);
			onDestroyCount = prefs.getInt("onDestroyCount", 0);
			//Log cat print out
			Log.i(this.TAG, "Function: \"onCreate()\" called.");
			
			//TODO:
			//update the appropriate count variable
			//update the view
			this.onCreateCount++;
			((TextView) findViewById(R.id.create)).setText(getString(R.string.onCreate) + " " +
				onCreateCount);

		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_one, menu);
			return true;
		}
		
		// lifecycle callback overrides
		
		@Override
		public void onStart(){
			super.onStart();
			
			//Log cat print out
			Log.i(this.TAG, "Function: \"onStart()\" called.");
			
			//TODO:
			//update the appropriate count variable
			//update the view
			onStartCount++;
			((TextView) findViewById(R.id.start)).setText(getString(R.string.onStart) + " " +
				onStartCount);
			displayData();
		}

	    // TODO: implement 5 missing lifecycle callback methods
		@Override
		public void onResume() {
			super.onResume();
			this.onResumeCount++;
			((TextView) findViewById(R.id.resume)).setText(getString(R.string.onResume) + " " +
					onResumeCount);
			Log.i(this.TAG, "Function: \"onResume()\" called.");
		}

		@Override
		public void onPause(){
			super.onPause();
			this.onPauseCount++;
			((TextView) findViewById(R.id.pause)).setText(getString(R.string.onPause) + " " +
					onPauseCount);

			saveSharedPreferences();

			Log.i(this.TAG, "Function: \"onPause()\" called.");
		}


	@Override
		public void onStop() {
			super.onStop();
			this.onStopCount++;
			((TextView) findViewById(R.id.stop)).setText(getString(R.string.onStop) + " " +
					onStopCount);
			Log.i(this.TAG, "Function: \"onStop()\" called.");
		}

		@Override
		public void onRestart(){
			super.onRestart();
			this.onRestartCount++;
			((TextView) findViewById(R.id.restart)).setText(getString(R.string.onRestart) + " " +
					onRestartCount);
			Log.i(this.TAG, "Function: \"onRestart()\" called.");
		}

		@Override
		public void onDestroy(){
			super.onDestroy();
			this.onDestroyCount++;
			((TextView) findViewById(R.id.destroy)).setText(getString(R.string.onDestroy) + " " +
					onDestroyCount);
			saveSharedPreferences();
			Log.i(this.TAG, "Function: \"onDestroy()\" called.");
		}
		//End of 5 lifecycle callback functions

		@Override
		public void onSaveInstanceState(Bundle savedInstanceState){
			//TODO:
			// save state information with a collection of key-value pairs
			// save all count variables
			super.onSaveInstanceState(savedInstanceState);

			savedInstanceState.putInt("onCreateCount", onCreateCount);
			savedInstanceState.putInt("onStartCount", onStartCount);
			savedInstanceState.putInt("onResumeCount", onResumeCount);
			savedInstanceState.putInt("onPauseCount", onPauseCount);
			savedInstanceState.putInt("onStopCount", onStopCount);
			savedInstanceState.putInt("onRestartCount", onRestartCount);
			savedInstanceState.putInt("onDestroyCount", onDestroyCount);

			Log.i(this.TAG, "Function: \"onSaveInstanceState()\" called.");

		}

		@Override
		public void onRestoreInstanceState (Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);

			onCreateCount = savedInstanceState.getInt("onCreateCount", onCreateCount);
			onStartCount = savedInstanceState.getInt("onStartCount", onStartCount);
			onResumeCount = savedInstanceState.getInt("onResumeCount", onResumeCount);
			onPauseCount = savedInstanceState.getInt("onPauseCount", onPauseCount);
			onStopCount = savedInstanceState.getInt("onStopCount", onStopCount);
			onRestartCount = savedInstanceState.getInt("onRestartCount", onRestartCount);
			onDestroyCount = savedInstanceState.getInt("onDestroyCount", onDestroyCount);


			Log.i(this.TAG, "Function: \"onRestoreInstanceState()\" called.");
		}
		
		public void launchActivityTwo(View view) {
			Log.i(this.TAG, "Function: \"launchActivityTwo()\" called.");
			//TODO:
			// This function launches Activity Two. 
			// Hint: use Context’s startActivity() method.
			Intent intent = new Intent(this, ActivityTwo.class);
			startActivity(intent);

		}
		
		public void onClickClear (View view) {
			Log.i(this.TAG, "Function: \"onClickClear()\" called.");

			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();

			editor.clear();
			editor.commit();

			onCreateCount = 0;
			onStartCount = 0;
			onResumeCount = 0;
			onPauseCount = 0;
			onStopCount = 0;
			onRestartCount = 0;
			onDestroyCount = 0;

			displayData();

		}

		private void saveSharedPreferences() {
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();

			editor.putInt("onCreateCount", this.onCreateCount);
			editor.putInt("onStartCount", this.onStartCount);
			editor.putInt("onResumeCount", this.onResumeCount);
			editor.putInt("onPauseCount", this.onPauseCount);
			editor.putInt("onStopCount", this.onStopCount);
			editor.putInt("onRestartCount", this.onRestartCount);
			editor.putInt("onDestroyCount", this.onDestroyCount);

			editor.commit();
		}

		public void displayData () {
			((TextView) findViewById(R.id.create)).setText(getString(R.string.onCreate) + " " +
					onCreateCount);
			((TextView) findViewById(R.id.start)).setText(getString(R.string.onStart) + " " +
					onStartCount);
			((TextView) findViewById(R.id.resume)).setText(getString(R.string.onResume) + " " +
					onResumeCount);
			((TextView) findViewById(R.id.pause)).setText(getString(R.string.onPause) + " " +
					onPauseCount);
			((TextView) findViewById(R.id.stop)).setText(getString(R.string.onStop) + " " +
					onStopCount);
			((TextView) findViewById(R.id.restart)).setText(getString(R.string.onRestart) + " " +
					onRestartCount);
			((TextView) findViewById(R.id.destroy)).setText(getString(R.string.onDestroy) + " " +
					onDestroyCount);
		}
}
