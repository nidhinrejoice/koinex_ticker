package com.nidhin.koinexticker.persistance;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefsManager {

    SharedPreferences mSharedPreferences;

    public SharedPrefsManager(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("preferences",
                Context.MODE_PRIVATE);
    }


    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean hasKey(String key) {
        return mSharedPreferences.contains(key);
    }

    public boolean getBooleanValue(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return getString(key).length() == 0 ? defaultValue : getString(key);
    }

    public int getIntValue(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public int getIntValue(String key, int defaultValue) {
        return getIntValue(key) == 0 ? defaultValue : getIntValue(key);
    }

    public long getLongValue(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public long getLongValue(String key, long defaultValue) {
        return getLongValue(key) == 0 ? defaultValue : getLongValue(key);
    }

    public float getFloatValue(String key) {
        return mSharedPreferences.getFloat(key, 0);
    }

    public float getFloatValue(String key, float defaultValue) {
        return getFloatValue(key) == 0f ? defaultValue : getFloatValue(key);
    }

    public void putStringValue(String key, String value) {

        mSharedPreferences.edit().putString(key, value).apply();

    }

    public void putIntValue(String key, int value) {

        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void putBooleanValue(String key, boolean value) {

        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void putLongValue(String key, long value) {


        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public void putFloatValue(String key, float value) {

        mSharedPreferences.edit().putFloat(key, value).apply();
    }


    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public void reset() {
        mSharedPreferences.edit().clear().commit();
    }

}
