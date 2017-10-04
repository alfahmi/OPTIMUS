package in.alfdev.cdmp;
import android.support.v7.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import com.marlonmafra.android.widget.*;
import android.content.*;
import android.support.v4.content.res.*;

public class LoginActivity extends AppCompatActivity
{

	Spinner SpnChooseOffice, SpnChooseCanvaser;
	EditTextPassword EdtPassword;
	Button BtnLogin;
	ImageView ImgCdmpLogo;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		ThemeUtils.createTheme(this);
		setContentView(R.layout.alfdev__app_login);
		
		
		BtnLogin = (Button) findViewById(R.id.alfdev__button_login);
		EdtPassword = (EditTextPassword) findViewById(R.id.alfdev__edittext_password);
		ImgCdmpLogo = (ImageView) findViewById(R.id.alfdev__image_logo);
		SpnChooseOffice = (Spinner) findViewById(R.id.alfdev__spinner_office_list);
		SpnChooseCanvaser = (Spinner) findViewById(R.id.alfdev__spinner_cvs_list);
		
		//ImageView
		int color = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
		ImgCdmpLogo.setColorFilter(color);
		
		// GetData
	
		// Spinner
		SpnChooseOffice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					String test = SpnChooseOffice.getSelectedItem().toString();
					if (test.equals("TDC CIAWIGEBANG"))
					{
						final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
							LoginActivity.this, R.array.alfdev__office_awg_cvs, android.R.layout.simple_spinner_item);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						SpnChooseCanvaser.setAdapter(adapter);
					} else if (test.equals("TDC CIKIJING")) {
						final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
							LoginActivity.this, R.array.alfdev__office_ckj_cvs, android.R.layout.simple_spinner_item);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						SpnChooseCanvaser.setAdapter(adapter);
					} else if (test.equals("TDC KUNINGAN"))
					{
						final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
							LoginActivity.this, R.array.alfdev__office_kng_cvs, android.R.layout.simple_spinner_item);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						SpnChooseCanvaser.setAdapter(adapter);
					} else if (test.equals("TDC MAJALENGKA")) {
						final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
							LoginActivity.this, R.array.alfdev__office_mjk_cvs, android.R.layout.simple_spinner_item);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						SpnChooseCanvaser.setAdapter(adapter);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});
			
		// Button
		
		BtnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences sharedPreferences = getSharedPreferences("in.alfdev.cdmp_preferences",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				String SpinnerOffice = SpnChooseOffice.getSelectedItem().toString();
				String SpinnerCanvasser = SpnChooseCanvaser.getSelectedItem().toString();
				String StringPassword = EdtPassword.getText().toString();
				
				if (StringPassword.equals("agrabudi555")) {
					editor.putString("office", SpinnerOffice);
					editor.putString("canvasser",SpinnerCanvasser);
					editor.putString("password", StringPassword);
					editor.commit();
					Intent i = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(i);
					finish();
				} else {
					EdtPassword.setError("Password Salah");
				}
			}
		});
	
	}

}
