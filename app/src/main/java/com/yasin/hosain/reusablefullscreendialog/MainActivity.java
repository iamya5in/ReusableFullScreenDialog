package com.yasin.hosain.reusablefullscreendialog;

import android.app.Dialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;


public class MainActivity extends LocalizationActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLanguage("en");
    }

    public void openFullScreenDialog(View view) {
        ReusableFullScreenDialog reusableFullScreenDialog=ReusableFullScreenDialog.newInstance(R.layout.full_screen_dialog_view,
                R.style.FullScreenDialogStyle, "Test Full Screen", new ReusableFullScreenDialog.OnSetFullScreenDialogView() {
                    @Override
                    public View setView(View view, Dialog dialog) {
                        return view;
                    }
                });
        reusableFullScreenDialog.show(getSupportFragmentManager(),ReusableFullScreenDialog.TAG);
    }
}
