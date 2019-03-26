package com.linkfaith.view;

import sql.DatabaseHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	SQLiteDatabase db;
	SQLiteOpenHelper openHelper;
	Button btnLogin;
	TextView textViewRegister;
	EditText EmailLogin;
	EditText PasswordLogin;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		openHelper = new DatabaseHelper(this);
		db = openHelper.getReadableDatabase();
		btnLogin = (Button) findViewById(R.id.buttonLogin);
		textViewRegister = (TextView) findViewById(R.id.textViewNoAccount);
		EmailLogin = (EditText) findViewById(R.id.editTextloginmail);
		PasswordLogin = (EditText) findViewById(R.id.editTextLoginPassword);
		textViewRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String email = EmailLogin.getText().toString();
				String pass = PasswordLogin.getText().toString();
				cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5+ "=? AND " + DatabaseHelper.COL_4 + "=?", new String[]{email,pass});
				if(cursor!=null){
					cursor.moveToNext();
					if(cursor.getCount()>0){
						Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}
