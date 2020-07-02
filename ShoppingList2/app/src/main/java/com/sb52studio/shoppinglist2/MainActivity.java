package com.sb52studio.shoppinglist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int mCount = 0;
    public static final int TEXT_REQUEST = 1;
    TextView mTxtLocation;
    TextView[] tVs = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtLocation = (TextView) findViewById(R.id.textView_ShopLoc);
        tVs[0] = (TextView) findViewById(R.id.textView_List_1);
        tVs[1] = (TextView) findViewById(R.id.textView_List_2);
        tVs[2] = (TextView) findViewById(R.id.textView_List_3);
        tVs[3] = (TextView) findViewById(R.id.textView_List_4);
        tVs[4] = (TextView) findViewById(R.id.textView_List_5);
        tVs[5] = (TextView) findViewById(R.id.textView_List_6);
        tVs[6] = (TextView) findViewById(R.id.textView_List_7);
        tVs[7] = (TextView) findViewById(R.id.textView_List_8);
        tVs[8] = (TextView) findViewById(R.id.textView_List_9);
        tVs[9] = (TextView) findViewById(R.id.textView_List_10);
    }

    public void getItem(View view) {
        if (mCount < 10) {
            Intent intent = new Intent(this, ShoppingItem.class);
            startActivityForResult(intent, TEXT_REQUEST);
        } else {
            Toast toast = Toast.makeText(this, "List is Full", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(ShoppingItem.EXTRA_REPLY);
                tVs[mCount].setText(reply);
                mCount++;

            }
        }
    }

    public void clearItem(View view) {
        String reply = "";
        mCount = 0;
        for (int i = 0; i < 10; i++) {
            tVs[i].setText(reply);
        }
    }

    public void openLocation(View view) {
        // Get the string indicating a location. Input is not validated; it is
        // passed to the location handler intact.
        String loc = mTxtLocation.getText().toString();

        // Parse the location and create the intent.
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}