package view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.contacts.lhj.jennifercontacts.GlobalConstant;
import com.contacts.lhj.jennifercontacts.GlobalVariable;
import com.contacts.lhj.jennifercontacts.R;

/**
 * 欢迎页面
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {

    private android.widget.FrameLayout welcomeFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.welcomeFrameLayout = (FrameLayout) findViewById(R.id.welcomeFrameLayout);
        ImageView imageView = new ImageView(this);
        imageView.setMaxWidth(this.welcomeFrameLayout.getWidth());
        imageView.setMaxHeight(this.welcomeFrameLayout.getHeight());
        imageView.setImageResource(R.drawable.start);
        this.welcomeFrameLayout.addView(imageView);

        gotoStart();

    }

    private void gotoStart() {
        Boolean isFirstOpen = GlobalVariable.getCustomizeSharedPreferences().
                getSharedPreferences().
                getBoolean(GlobalConstant.Application.IS_FIRST_OPEN_KEY, true);
        if (isFirstOpen) {
            jumpToGuideActivity();
        } else {
            jumpToGestureLoginActivity();
        }
    }

    private void jumpToGestureLoginActivity() {
        Intent intent = new Intent(this, GestureLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void jumpToGuideActivity() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }
}
