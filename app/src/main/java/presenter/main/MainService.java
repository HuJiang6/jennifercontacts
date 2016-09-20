package presenter.main;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import tools.ScreenStatusReceiver;

public class MainService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        registerScreenStatusReceiver();
    }

    private void registerScreenStatusReceiver() {
        // 屏幕状态广播
        ScreenStatusReceiver screenStatusReceiver;
        // 屏幕状态广播初始化
        screenStatusReceiver = new ScreenStatusReceiver();
        IntentFilter screenStatusIF = new IntentFilter();
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON);
        screenStatusIF.addAction(Intent.ACTION_SCREEN_OFF);
        screenStatusIF.addAction(Intent.ACTION_USER_PRESENT);
        // 注册
        registerReceiver(screenStatusReceiver, screenStatusIF);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
