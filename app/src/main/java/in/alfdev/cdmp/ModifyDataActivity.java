package in.alfdev.cdmp;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.*;
import android.view.*;

public class ModifyDataActivity extends AppCompatActivity implements OnClickListener {

    private EditText Edt_office, Edt_canvasser, Edt_day, Edt_outlet, Edt_formsurvey, Edt_telkomsel, Edt_indosat, Edt_axis, Edt_xl, Edt_three, Edt_other;
    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		ThemeUtils2.createTheme(this);
        setTitle("Modify Record");

        setContentView(R.layout.alfdev__modify_record);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
        dbManager = new DBManager(this);
        dbManager.open();

        Edt_office 		= (EditText) findViewById(R.id.alfdev__edittext_view_office);
        Edt_canvasser 	= (EditText) findViewById(R.id.alfdev__edittext_view_canvasser);
        Edt_day 		= (EditText) findViewById(R.id.alfdev__edittext_view_day);
        Edt_outlet 		= (EditText) findViewById(R.id.alfdev__edittext_view_outlet);
        Edt_formsurvey 	= (EditText) findViewById(R.id.alfdev__edittext_view_formsurver);
        Edt_telkomsel 	= (EditText) findViewById(R.id.alfdev__edittext_view_telkomsel);
        Edt_indosat 	= (EditText) findViewById(R.id.alfdev__edittext_view_indosat);
        Edt_axis 		= (EditText) findViewById(R.id.alfdev__edittext_view_axis);
		Edt_xl 			= (EditText) findViewById(R.id.alfdev__edittext_view_xl);
        Edt_three 		= (EditText) findViewById(R.id.alfdev__edittext_view_three);
        Edt_other 		= (EditText) findViewById(R.id.alfdev__edittext_view_other);
        
		
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent 		= getIntent();
        String id 			= intent.getStringExtra("id");
        String office 		= intent.getStringExtra("office");
        String canvasser 	= intent.getStringExtra("canvasser");
		String day 			= intent.getStringExtra("day");
        String outlet 		= intent.getStringExtra("outlet");
		String formsurvey 	= intent.getStringExtra("formsurvey");
        String telkomsel 	= intent.getStringExtra("telkomsel");
		String indosat 		= intent.getStringExtra("indosat");
        String axis 		= intent.getStringExtra("axis");
		String xl 			= intent.getStringExtra("xl");
        String three 		= intent.getStringExtra("three");
		String other 		= intent.getStringExtra("other");
        
        _id = Long.parseLong(id);

        Edt_office.setText(office);
		Edt_canvasser.setText(canvasser);
		Edt_day.setText(day);
		Edt_outlet.setText(outlet);
		Edt_formsurvey.setText(formsurvey);
		Edt_telkomsel.setText(telkomsel);
		Edt_indosat.setText(indosat);
		Edt_axis.setText(axis);
		Edt_xl.setText(xl);
		Edt_three.setText(three);
		Edt_other.setText(other);
		
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String office = Edt_office.getText().toString();
                String canvasser = Edt_canvasser.getText().toString();
				String day = Edt_day.getText().toString();
                String outlet = Edt_outlet.getText().toString();
				String formsurvey = Edt_formsurvey.getText().toString();
                String telkomsel = Edt_telkomsel.getText().toString();
				String indosat = Edt_indosat.getText().toString();
                String axis = Edt_axis.getText().toString();
				String xl = Edt_xl.getText().toString();
                String three = Edt_three.getText().toString();
				String other = Edt_other.getText().toString();
               	
                dbManager.update(_id, office, canvasser, day, formsurvey, outlet, telkomsel, indosat, axis, xl, three, other);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), DataActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
