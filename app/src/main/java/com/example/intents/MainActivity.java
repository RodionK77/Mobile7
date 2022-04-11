package com.example.intents;

import static java.net.Proxy.Type.HTTP;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.ContactsContract;
import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mapButton(View view) {
        String geoUriString = "geo:0,0?q=Razvilka";
        Uri geoUri = Uri.parse(geoUriString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        String title = getResources().getString(R.string.choose);
        Intent chooser = Intent.createChooser(mapIntent, title);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public void webButton(View view) {
        Uri webpage = Uri.parse("https://www.mirea.ru/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        }
    }

    public void mailButton(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"k.rodion77@gmail.com"});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        mailIntent.putExtra(Intent.EXTRA_TEXT, "test test");
        //mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("https://trikky.ru/wp-content/blogs.dir/1/files/2018/04/Kotik11.jpg"));
        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mailIntent);
        }
    }

    public void calendarButton(View view) {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, 04, 12, 17, 0);
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        cal.set(2022, 04, 12, 18, 0);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Test test");
        startActivity(calendarIntent);
    }

    public void contactsButton(View view) {
        /*Intent data = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        data.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(data, 1);*/

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"ASC";
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(uri, null, null, null, sort);
    }
}