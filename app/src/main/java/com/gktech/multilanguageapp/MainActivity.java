package com.gktech.multilanguageapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocales();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().getTitle();
        Log.d("name",getResources().getString(R.string.welcome_to_app));
        findViewById(R.id.chengelan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_language();
            }
        });

    }

    private void change_language() {

        String language[]={"English","Bangla","Chinese"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i==0){
                    setLocals("en");
                    recreate();
                }
                if(i==1){
                    setLocals("bn");
                    recreate();
                }
                if(i==2){
                    setLocals("zh");
                    recreate();
                }
            }
        });
    }

    public void setLocals(String localeName) {

            Locale myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            SharedPreferences.Editor editor=this.getSharedPreferences("settings",MODE_PRIVATE).edit();
            editor.putString("my_lang",localeName);
            editor.apply();
        //Intent refresh = new Intent(this, MainActivity.class);
        //refresh.putExtra("current", localeName);
       // startActivity(refresh);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

  private  void loadLocales(){
        String lang=this.getSharedPreferences("settings",MODE_PRIVATE).getString("my_lang","");
        setLocals(lang);
    }
}
