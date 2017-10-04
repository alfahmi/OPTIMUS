package in.alfdev.cdmp;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import android.database.sqlite.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {
	
	private DrawerLayout AlfDev_DrawerLayout;
	private Toolbar AlfDev_Toolbar;
	CoordinatorLayout AlfDev_CoordinatorLayout;
	Spinner chooseDay, chooseFormSurvey, chooseOutlet;
	EditText TelkomselQty, IndosatQty, AxisQty, XlQty, ThreeQty, OtherQty;
	Button buttonSubmit;
	private DBManager dbManager;
	TextView alfdev_ToolbarText,tvHeaderOffice, tvHeaderCanvasser;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		ThemeUtils.createTheme(this);
        setContentView(R.layout.alfdev__app_main);
		SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
		final String office = sharedPreferences.getString("office","");
		final String canvasser = sharedPreferences.getString("canvasser","");
		final String password = sharedPreferences.getString("password","");
		if (password.equals("agrabudi555")) {
			// Null
		} else {
			Intent i = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
		}
		
		// Toolbar
		AlfDev_Toolbar = (Toolbar) findViewById(R.id.alfdev__toolbar);
		AlfDev_Toolbar.setTitle("");
		alfdev_ToolbarText = (TextView) AlfDev_Toolbar.findViewById(R.id.alfdev__toolbar_titlr);
	    setSupportActionBar(AlfDev_Toolbar);
		
		// Navigation Drawer
		AlfDev_InitNavDraw();
		AlfDev_CoordinatorLayout = (CoordinatorLayout) findViewById(R.id.alfdev__CoordinatorLayout);
		
		// Snackbar Welcome
		Snackbar snackbar = Snackbar.make(AlfDev_CoordinatorLayout,"SELAMAT DATANG "+canvasser,Snackbar.LENGTH_LONG);
		snackbar.show();
		
		// FindViewById
		chooseDay = (Spinner) findViewById(R.id.alfdev__spinner_day_visit);
		chooseFormSurvey = (Spinner) findViewById(R.id.alfdev__spinner_form_survey);
		chooseOutlet = (Spinner) findViewById(R.id.alfdev__spinner_outlet);
		
		TelkomselQty = (EditText) findViewById(R.id.alfdev__edittext_telkomsel);
		IndosatQty = (EditText) findViewById(R.id.alfdev__edittext_indosat);
		AxisQty = (EditText) findViewById(R.id.alfdev__edittext_axis);
		XlQty = (EditText) findViewById(R.id.alfdev__edittext_xl);
		ThreeQty = (EditText) findViewById(R.id.alfdev__edittext_three);
		OtherQty = (EditText) findViewById(R.id.alfdev__edittext_other);
		
		
		buttonSubmit = (Button) findViewById(R.id.alfdev__button_save);
		
		// Database
		dbManager = new DBManager(this);
		dbManager.open();
		
		buttonSubmit.setOnClickListener(new OnClickListener() {
			public void onClick (View v) {
				final String day = chooseDay.getSelectedItem().toString();
				final String formsurvey = chooseFormSurvey.getSelectedItem().toString();
				final String outlet = chooseOutlet.getSelectedItem().toString();

				final String telkomsel = TelkomselQty.getText().toString();
				final String indosat = IndosatQty.getText().toString();
				final String axis = AxisQty.getText().toString();
				final String xl = XlQty.getText().toString();
				final String three = ThreeQty.getText().toString();
				final String other = OtherQty.getText().toString();
				
				dbManager.insert(office, canvasser, day, formsurvey, outlet, telkomsel, indosat, axis, xl, three, other);
				
				TelkomselQty.setText("");
				IndosatQty.setText("");
				AxisQty.setText("");
				XlQty.setText("");
				ThreeQty.setText("");
				OtherQty.setText("");
			}
		});
    }

	public void AlfDev_InitNavDraw() {

		NavigationView navigationView = (NavigationView)findViewById(R.id.alfdev__navigation_view);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {

					int id = menuItem.getItemId();

					switch (id){

						case R.id.alfdev__menu_lighttheme:
							ThemeUtils.setTheme(MainActivity.this, 0);
							recreateActivity();
							AlfDev_DrawerLayout.closeDrawers();
							break;
						case R.id.alfdev__menu_darktheme:
							ThemeUtils.setTheme(MainActivity.this, 1);
							recreateActivity();
							AlfDev_DrawerLayout.closeDrawers();
							break;
						case R.id.alfdev__menu_logout:
							SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedPreferences.edit();
							
							editor.putString("office", "");
							editor.putString("canvasser","");
							editor.putString("password", "");
							editor.commit();
							// Delete Database
							String DB_NAME = "ALFDEV_INPUTDATA.DB";
							String FILE_PATH = "/data/data/in.alfdev.cdmp/databases/";
							SQLiteDatabase.deleteDatabase(new File(FILE_PATH+DB_NAME));
							
							Intent i = new Intent(MainActivity.this, LoginActivity.class);
							startActivity(i);
							finish();
							
							AlfDev_DrawerLayout.closeDrawers();
							break;
						case R.id.alfdev__menu_about:
							AboutDialog();
							AlfDev_DrawerLayout.closeDrawers();
							break;
						case R.id.alfdev__menu_exit:
							finish();

					}
					return true;
				}
			});
		View header = navigationView.getHeaderView(0);
		SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
		final String office = sharedPreferences.getString("office","");
		final String canvasser = sharedPreferences.getString("canvasser","");
		
		tvHeaderOffice = (TextView)header.findViewById(R.id.alfdev__text_header_office);
		tvHeaderCanvasser = (TextView) header.findViewById(R.id.alfdev__text_header_canvasser);

		tvHeaderOffice.setText(office);
		tvHeaderCanvasser.setText(canvasser);
		
		AlfDev_DrawerLayout = (DrawerLayout)findViewById(R.id.alfdev__drawer);

		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,AlfDev_DrawerLayout,AlfDev_Toolbar,R.string.drawer_open,R.string.drawer_close){

			@Override
			public void onDrawerClosed(View v){
				super.onDrawerClosed(v);
			}

			@Override
			public void onDrawerOpened(View v) {
				super.onDrawerOpened(v);
			}
		};
		AlfDev_DrawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.alfdev__menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.alfdev__action_data_input:
				Intent i = new Intent(MainActivity.this, DataActivity.class);
				startActivity(i);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	public void AboutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("App OPTIMUS V1.0 dev by AlfDev")
			.setTitle(R.string.app_name)
			.setIcon(R.mipmap.ic_launcher)
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	
	//recreate/refresh Activity
    private void recreateActivity()
	{
    	this.finish();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(new Intent(this, this.getClass()));
    }
}
    
