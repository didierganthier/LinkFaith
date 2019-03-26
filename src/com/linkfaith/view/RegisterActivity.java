package com.linkfaith.view;

import sql.DatabaseHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	public Activity registeractivity= RegisterActivity.this;
	public SQLiteOpenHelper openHelper;
	public SQLiteDatabase db;
	Button btnReg;
	EditText firstname;
	EditText lastname;
	EditText password;
	EditText email;
	EditText phone;
	TextView login;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        btnReg = (Button) findViewById(R.id.buttonLogin);
        openHelper = new DatabaseHelper(this);
        firstname = (EditText) findViewById(R.id.editTextFirstName);
        lastname = (EditText) findViewById(R.id.editTextLastName);
        password = (EditText) findViewById(R.id.editTextLoginPassword);
        email = (EditText) findViewById(R.id.editTextEmail);
        phone = (EditText) findViewById(R.id.editTextPhone);
        login = (TextView)findViewById(R.id.textViewLogin);
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
				startActivity(loginIntent);
			}
		});
        btnReg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db = openHelper.getWritableDatabase();
				String fname= firstname.getText().toString().trim();
				String lname= lastname.getText().toString().trim();
				String pass= password.getText().toString().trim();
				String mail= email.getText().toString().trim();
				String tel= phone.getText().toString().trim();
				insertData(fname, lname, pass, mail, tel);
				Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_LONG).show();
			}
		});
    }
    public void insertData(String fname, String lname, String pass, String mail, String tel){
    	ContentValues contentValues = new ContentValues();
    	contentValues.put(DatabaseHelper.COL_2,fname);
    	contentValues.put(DatabaseHelper.COL_3, lname);
    	contentValues.put(DatabaseHelper.COL_4, pass);
    	contentValues.put(DatabaseHelper.COL_5, mail);
    	contentValues.put(DatabaseHelper.COL_6, tel);
    	long id= db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
