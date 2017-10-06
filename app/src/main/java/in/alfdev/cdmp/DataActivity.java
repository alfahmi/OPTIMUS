package in.alfdev.cdmp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import com.ajts.androidmads.library.SQLiteToExcel;
import android.widget.Button;
import java.io.*;
import android.os.*;
import android.content.*;
import android.net.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.*;
import android.support.v7.app.*;
import android.provider.*;
import android.content.pm.*;
import android.database.sqlite.*;

public class DataActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;
	TextInputLayout test;
    private SimpleCursorAdapter adapter;
	private SharedPreferences permissionStatus;
	private boolean sentToSettings = false; 
	private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
	private static final int REQUEST_PERMISSION_SETTING = 101;
	
	
	
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.OFFICE, 
			DatabaseHelper.CANVASSER,
			DatabaseHelper.DAY, 
			DatabaseHelper.FORMSURVEY,
			DatabaseHelper.OUTLET, 
			DatabaseHelper.TELKOMSEL,
			DatabaseHelper.INDOSAT, 
			DatabaseHelper.AXIS,
			DatabaseHelper.XL, 
			DatabaseHelper.THREE,
			DatabaseHelper.OTHER
			};

    final int[] to = new int[] { R.id.id, 
			R.id.alfdev__data_input_office,
			R.id.alfdev__data_input_canvasser,
			R.id.alfdev__data_input_day,
			R.id.alfdev__data_input_form_survey,
			R.id.alfdev__data_input_outlet,
			R.id.alfdev__data_input_telkomsel,
			R.id.alfdev__data_input_indosat,
			R.id.alfdev__data_input_axis,
			R.id.alfdev__data_input_xl,
			R.id.alfdev__data_input_three,
			R.id.alfdev__data_input_other
			};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		ThemeUtils2.createTheme(this);
        setContentView(R.layout.alfdev__input_data_main);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.alfdev__data_view_style, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView tvoffice = (TextView) view.findViewById(R.id.alfdev__data_input_office);
              	TextView tvcanvasser = (TextView) view.findViewById(R.id.alfdev__data_input_canvasser);
              	TextView tvday = (TextView) view.findViewById(R.id.alfdev__data_input_day);
              	TextView tvformsurvey = (TextView) view.findViewById(R.id.alfdev__data_input_form_survey);
              	TextView tvoutlet = (TextView) view.findViewById(R.id.alfdev__data_input_outlet);
              	TextView tvtelkomsel = (TextView) view.findViewById(R.id.alfdev__data_input_telkomsel);
              	TextView tvindosat = (TextView) view.findViewById(R.id.alfdev__data_input_indosat);
              	TextView tvaxis = (TextView) view.findViewById(R.id.alfdev__data_input_axis);
              	TextView tvxl = (TextView) view.findViewById(R.id.alfdev__data_input_xl);
              	TextView tvthree = (TextView) view.findViewById(R.id.alfdev__data_input_three);
              	TextView tvother = (TextView) view.findViewById(R.id.alfdev__data_input_other);

                String id = idTextView.getText().toString();
                String office = tvoffice.getText().toString();
				String canvasser = tvcanvasser.getText().toString();
				String day = tvday.getText().toString();
				String formsurvey = tvformsurvey.getText().toString();
				String outlet = tvoutlet.getText().toString();
				String telkomsel = tvtelkomsel.getText().toString();
				String indosat = tvindosat.getText().toString();
				String axis = tvaxis.getText().toString();
				String xl = tvxl.getText().toString();
				String three = tvthree.getText().toString();
				String other = tvother.getText().toString();
				
				Intent modify_intent = new Intent(getApplicationContext(), ModifyDataActivity.class);
                modify_intent.putExtra("office", office);
                modify_intent.putExtra("canvasser", canvasser);
				modify_intent.putExtra("day", day);
                modify_intent.putExtra("formsurvey", formsurvey);
				modify_intent.putExtra("outlet", outlet);
                modify_intent.putExtra("telkomsel", telkomsel);
				modify_intent.putExtra("indosat", indosat);
                modify_intent.putExtra("axis", axis);
				modify_intent.putExtra("xl", xl);
                modify_intent.putExtra("three", three);
				modify_intent.putExtra("other", other);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
		
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.alfdev__menu_convert, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
			case R.id.alfdev__action_delete_database:
				if (adapter.getCount() == 0) {
				
				} else {
					HapusDatabase();
				}
				break;
			case R.id.alfdev__action_convert_and_send:
				if (adapter.getCount() == 0) {
					FileEmptyDialog();
				} else {
					RequestPermission();
				}
				
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
		
	}
	
	// Request Permission
	public void RequestPermission() {
		permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
		if (ActivityCompat.checkSelfPermission(DataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(DataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(DataActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							ActivityCompat.requestPermissions(DataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
						}
					});
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
                builder.show();
            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(DataActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							sentToSettings = true;
							Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
							Uri uri = Uri.fromParts("package", getPackageName(), null);
							intent.setData(uri);
							startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
						//	Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
						}
					});
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(DataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
            editor.commit();


        } else {
            // Converti And Sending File
			ConvertAndSend();
        }
	}
	
	
	// Conver and send
	public void ConvertAndSend () {
		SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
		final String canvasser = sharedPreferences.getString("canvasser","");
		final String email = sharedPreferences.getString("email","");

		String FileExcel = canvasser+"_CDMP.xls";

		String directory_path = Environment.getExternalStorageDirectory().getPath() + "/.alfahmi/";
		File file = new File(directory_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// Export SQLite DB as EXCEL FILE
		SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DatabaseHelper.DB_NAME, directory_path);
		sqliteToExcel.exportAllTables(FileExcel, new SQLiteToExcel.ExportListener() {
				@Override
				public void onStart() {

				}

				@Override
				public void onCompleted(String filePath) {
				}

				@Override
				public void onError(Exception e) {

				}
			});

		// Send The File Via Email
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ email});
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "REPORT CDMP "+canvasser);
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Hatur Nuhun");
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/.alfahmi/"+FileExcel));
		emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(emailIntent, "Pilih app :"));
	}
	
	
	// File is empty
	public void FileEmptyDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Silahkan melakukan penginputan terlebih dahulu untuk melakukan kirim data.")
			.setTitle("Data Kosong.")
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
		AlertDialog alert = builder.create();
		alert.show();

	}
	
	
	// Delete Database Dialog
	public void HapusDatabase() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah anda yakin untuk menghapus semua data?")
			.setTitle("Hapus Data")
			.setCancelable(false)
			.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					String DB_NAME = "ALFDEV_INPUTDATA.DB";
					String FILE_PATH = "/data/data/in.alfdev.cdmp/databases/";
					SQLiteDatabase.deleteDatabase(new File(FILE_PATH+DB_NAME));
					recreateActivity();
					
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
