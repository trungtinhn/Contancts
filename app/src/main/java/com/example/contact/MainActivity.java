package com.example.contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Boolean isASC = true;
    ArrayList<ContactItem> contacts;
    ContactListAdapter contactListAdapter;
    ListView lv1;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int CONTACT_LOADER = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        lv1.setAdapter(contactListAdapter);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        LoaderManager.getInstance(this).restartLoader(this.CONTACT_LOADER,null,this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
        //
        }
        else{
            //
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if( id == CONTACT_LOADER) {
            String[] SELECTED_FIELDS = new String[]
                    {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    };
            return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    SELECTED_FIELDS,
                    null,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " " + (isASC ? "ASC" : "DESC")); }
        return null;

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == CONTACT_LOADER){
            ArrayList<ContactItem> contact = new ArrayList<>();
            if(data != null){
                while (!data.isClosed() && data.moveToNext()){
                    String phone = data.getString(1);
                    String name = data.getString(2);
                    contact.add(new ContactItem(name, phone));
                }
                contact.add(new ContactItem("TrungTinh", "0704408389"));
                contact.add(new ContactItem("ThachSang", "0704408389"));
                contactListAdapter = new ContactListAdapter(MainActivity.this, R.layout.one_contact, contact);

                contactListAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        loader = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option1:
            isASC = true;
            break;
            case R.id.option2:
            isASC = false;
            break;

        }
        return super.onOptionsItemSelected(item);
    }
}