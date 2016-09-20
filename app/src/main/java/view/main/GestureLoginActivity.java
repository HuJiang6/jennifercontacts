package view.main;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.contacts.lhj.jennifercontacts.R;

import presenter.main.FingerPrintUiHelper;

/**
 * 手势登录（手势密码、指纹密码）
 */
public class GestureLoginActivity extends AppCompatActivity {

    private final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0;
    private FingerPrintUiHelper fingerPrintUiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initFingerPrint();
            AppCompatButton fingerButton = (AppCompatButton) findViewById(R.id.fingerButton);

            assert fingerButton != null;
            fingerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumpToGesturePassCheck();
                }
            });

        } else {
            jumpToMainActivity();
        }
    }

    /**
     * 跳转到手势密码校验界面
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void jumpToGesturePassCheck() {
        KeyguardManager keyguardManager =
                (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        Intent intent =
                keyguardManager.createConfirmDeviceCredentialIntent("finger", "测试指纹识别");
        fingerPrintUiHelper.stopsFingerPrintListen();
        startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
    }

    private void initFingerPrint() {
        fingerPrintUiHelper = new FingerPrintUiHelper(this);
        fingerPrintUiHelper.startFingerPrintListen(new FingerprintManagerCompat.AuthenticationCallback() {
            /**
             * Called when a fingerprint is recognized.
             *
             * @param result An object containing authentication-related data
             */
            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Toast.makeText(GestureLoginActivity.this, "指纹识别成功", Toast.LENGTH_SHORT).show();
                jumpToMainActivity();
            }

            /**
             * Called when a fingerprint is valid but not recognized.
             */
            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(GestureLoginActivity.this, "指纹识别失败", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when a recoverable error has been encountered during authentication. The help
             * string is provided to give the user guidance for what went wrong, such as
             * "Sensor dirty, please clean it."
             *
             * @param helpMsgId  An integer identifying the error message
             * @param helpString A human-readable string that can be shown in UI
             */
            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                Toast.makeText(GestureLoginActivity.this, helpString, Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an unrecoverable error has been encountered and the operation is complete.
             * No further callbacks will be made on this object.
             *
             * @param errMsgId  An integer identifying the error message
             * @param errString A human-readable error string that can be shown in UI
             */
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
                Toast.makeText(GestureLoginActivity.this, "errMsgId=" + errMsgId + "|" +
                        errString, Toast.LENGTH_SHORT).show();
                if (errMsgId == 7) { //出错次数过多（小米5测试是5次）
                    jumpToGesturePassCheck();
                }
            }
        });
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(GestureLoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "识别成功", Toast.LENGTH_SHORT).show();
                jumpToMainActivity();
            } else {
                Toast.makeText(this, "识别失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
