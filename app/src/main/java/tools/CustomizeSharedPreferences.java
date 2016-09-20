package tools;

import android.content.Context;
import android.content.SharedPreferences;

public class CustomizeSharedPreferences {
    private SharedPreferences sharedPreferences;

    /**
     * 初始化一个定制的SharedPreferences管理数据
     *
     * @param context Context
     * @param name    Desired preferences file. If a preferences file by this name does not exist,
     *                it will be created when you retrieve an editor (SharedPreferences.edit()) and
     *                then commit changes (Editor.commit()).
     * @param mode    Operating mode. Use 0 or MODE_PRIVATE for the default operation,
     *                MODE_WORLD_READABLE and MODE_WORLD_WRITEABLE to control permissions.
     */
    public CustomizeSharedPreferences(Context context, String name, int mode) {
        sharedPreferences = context.getSharedPreferences(name, mode);
    }

    public void putStringToSharedPreferences(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public void putBooleanToSharedPreferences(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
