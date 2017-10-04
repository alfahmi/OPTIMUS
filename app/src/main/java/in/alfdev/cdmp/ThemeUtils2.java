package in.alfdev.cdmp;

import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.preference.*;
/*12.09.2017 by Hendriyawan (GDEV)*/
public class ThemeUtils2
{
	private static SharedPreferences pref;
	private static SharedPreferences.Editor editor;
	public static final int LIGHT = 0;
	public static final int DARK = 1;

	//createTheme
	public static void createTheme(Context context)
	{
		pref = PreferenceManager.getDefaultSharedPreferences(context);
		int theme = pref.getInt("theme_app", 0);
		switch(theme)
		{
			case LIGHT:
			    context.setTheme(R.style.AppThemeLight2);
			    break;
			case DARK:
			    context.setTheme(R.style.AppTheme2);
			    break;
			default:
			    context.setTheme(R.style.AppThemeLight2);
			    break;
		}
	}
	//set theme
	public static void setTheme(AppCompatActivity activity, int theme)
	{
		pref = PreferenceManager.getDefaultSharedPreferences(activity);
		editor = pref.edit();
		editor.putInt("theme_app", theme);
		editor.apply();

	}
}
