package com.example.ramiro.autoescuela.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Ramiro on 31/7/2017.
 */

public class pictureHandler {

    private static final String LOG_TAG = "pictureHandler";
    private static final String STUDENT_PIN = "stdnt_PIN";
    private static final String STUDENT_PICTURES = "stdnt_pic";

    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getFilesDir(), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
}
