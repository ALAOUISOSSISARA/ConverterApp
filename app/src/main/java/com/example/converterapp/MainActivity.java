package com.example.converterapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    // ── Fields ─────────────────────────────────────────────────────────────
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    // ── onCreate ───────────────────────────────────────────────────────────
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Find the Toolbar and set it as the action bar
        //    This is necessary because our theme is NoActionBar
        //    so we manage the toolbar ourselves
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Find the Views from activity_main.xml
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // 3. Create the adapter and connect it to ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // 4. Connect TabLayout and ViewPager2 together
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(TabLayout.Tab tab, int position) {
                        if (position == 0) {
                            tab.setText("Temperature");
                        } else {
                            tab.setText("Distance");
                        }
                    }
                }
        ).attach();

        // 5. Intercept the back button with a confirmation dialog
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        showQuitDialog();
                    }
                }
        );
    }

    // ── Menu: inflate the menu XML into the action bar ────────────────────
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // ── Menu: handle menu item clicks ─────────────────────────────────────
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_quit) {
            showQuitDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ── Dialog: show a confirmation popup before quitting ─────────────────
    private void showQuitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter")
                .setMessage("Êtes-vous sûr de vouloir quitter ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}