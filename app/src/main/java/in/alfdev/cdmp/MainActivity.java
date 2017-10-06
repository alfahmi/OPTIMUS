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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.ArrayAdapter;
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
		// FindViewById
		chooseDay = (Spinner) findViewById(R.id.alfdev__spinner_day_visit);
		chooseFormSurvey = (Spinner) findViewById(R.id.alfdev__spinner_form_survey);
		chooseOutlet = (Spinner) findViewById(R.id.alfdev__spinner_outlet);
		
		if (password.equals("agrabudi555")) {
			SetupOutlet();
			
		} else {
			Intent i = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
		}
		
	
	
		if (canvasser.equals("AGUNG S"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_awg_agung_s, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ARDIAN")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_awg_ardian_s, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DEDE I")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_awg_dede_i, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("M IRVAN")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_awg_m_irvan, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DEVI N")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_awg_devi_n, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);

			// KUNINGAN SETUP
		} else if (canvasser.equals("AANG HASAN")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_aang_hasan, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ARIEF F")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_arief_f, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ASEP A")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_asep_a, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DENI SODIKIN"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_deni_sodikin, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DUDI RUDIYANTO")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_dudi_rudiyanto, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("FERRY LIDIANTO"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_ferry_lidianto, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("IMAN AGISMAN")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_iman_agisman, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("NURKHOLIK"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_nurkholik, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ZANURI")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_kng_zanuri, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);

			// CIKIJING SETUP
		} else if (canvasser.equals("ANJAS ASMARA"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_ckj_anjas_asmara, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ANDRI")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_ckj_andri, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DUDU A"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_ckj_dudu_a, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("DEDI KOSWARA")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_ckj_dedi_koswara, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("MOCH RIZAL"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_ckj_moch_rizal, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);

			// MAJALENGA SETUP
		} else if (canvasser.equals("DEDE R")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_dede_r, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("ENJANG B"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_enjang_b, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("JAMAL ABDUL")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_jamal_abdul, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("JEMMY K"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_jemmy_k, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("MAMAT RAHMAT")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_mamat_rahmat, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("NONO W"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_nono_w, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("OKA A")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_oka_a, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("WAWAN HERMAWAN"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_wawan_hermawan, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("YAYAN H")) {
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_yayan_h, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else if (canvasser.equals("YUDI H"))
		{
			final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.alfdev__outlet_mjk_yudi_h, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			chooseOutlet.setAdapter(adapter);
		} else {

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
				
				if (telkomsel.isEmpty() && indosat.isEmpty() && axis.isEmpty() && xl.isEmpty() && three.isEmpty() && other.isEmpty()) {
					Snackbar snackbar = Snackbar.make(AlfDev_CoordinatorLayout,"DATA KOSONG!",Snackbar.LENGTH_LONG);
					snackbar.show();
				} else {
				
					dbManager.insert(office, canvasser, day, formsurvey, outlet, telkomsel, indosat, axis, xl, three, other);
					TelkomselQty.setText("");
					IndosatQty.setText("");
					AxisQty.setText("");
					XlQty.setText("");
					ThreeQty.setText("");
					OtherQty.setText("");
				}
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
							LogoutDialog();
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
	
	
	// About Dialog
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
	
	// Logout Dialog
	public void LogoutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah anda yakin untuk Logout?")
			.setTitle("Logout")
			.setCancelable(false)
			.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();

					editor.putString("office", "");
					editor.putString("canvasser","");
					editor.putString("password", "");
					editor.putString("email","");
					editor.commit();
					// Delete Database
					String DB_NAME = "ALFDEV_INPUTDATA.DB";
					String FILE_PATH = "/data/data/in.alfdev.cdmp/databases/";
					SQLiteDatabase.deleteDatabase(new File(FILE_PATH+DB_NAME));

					Intent i = new Intent(MainActivity.this, LoginActivity.class);
					startActivity(i);
					finish();

				}
			});

		AlertDialog alert = builder.create();
		alert.show();

	}
	
	public void SetupOutlet() {
		//  Setup Outlet
		// =======================================
		// OUTLET SETUP
		SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
		final String canvasser = sharedPreferences.getString("canvasser","");
		
		// ========================================
	}
}
    
