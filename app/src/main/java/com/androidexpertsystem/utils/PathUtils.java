package com.androidexpertsystem.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Serhii Slobodianiuk on 30.04.2017.
 */

public class PathUtils {

    public static File readRaw(Context ctx, int res_id) {

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "DefaultProperties + " + res_id + ".xml");
        try {
            InputStream inputStream = ctx.getResources().openRawResource(res_id);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                fileOutputStream.write(buf, 0, len);
            }

            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e1) {
        }
        return file;

    }


}
