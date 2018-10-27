package com.example.chris.bj_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.bj_final.data.BlackJackContract;
import com.example.chris.bj_final.data.DatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    EditText editTextUserName;
    SQLiteDatabase db;

    RecyclerView login;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter listAdapter;
    ArrayList < ListItemLogin > arrayList = new ArrayList < > ();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.editTextUser);
        mDatabaseHelper = new DatabaseHelper(this);
        login = (RecyclerView) findViewById(R.id.loginRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        login.setLayoutManager(layoutManager);
        login.setHasFixedSize(true);

        BackgroundTask backgroundTask = new BackgroundTask(Login.this);
        backgroundTask.execute();

        populateListView();
    }

    private void populateListView() {

        db = mDatabaseHelper.getReadableDatabase();

        Cursor cursor = mDatabaseHelper.getInfo(db);

        cursor.moveToFirst();
        do {
            ListItemLogin tmpList = new ListItemLogin(cursor.getString(0), cursor.getInt(1));
            arrayList.add(tmpList);
        } while (cursor.moveToNext());
        db.close();

        listAdapter = new ListAdapterLogin(arrayList);
        login.setAdapter(listAdapter);
    }

    public void onClickCreate(View view) {
        db = mDatabaseHelper.getWritableDatabase();
        String userName = editTextUserName.getText().toString().trim(); //trim name

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(BlackJackContract.BlackJackEntry.COLUMN_NAME, userName);
        contentValues.put(BlackJackContract.BlackJackEntry.COLUMN_HS, 0);
        db.insert(BlackJackContract.BlackJackEntry.TABLE_NAME, null, contentValues);
        Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show();

        populateListView();
    }
}