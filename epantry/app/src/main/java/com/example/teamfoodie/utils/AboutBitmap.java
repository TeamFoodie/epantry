package com.example.teamfoodie.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teamfoodie.provider.ProviderUtil;

import java.io.File;
import java.io.FileNotFoundException;


public class AboutBitmap {
    private static final String TAG = "AboutBitmap";

    /**
     * open in album
     *
     * @param ctx
     * @param requestCode
     */
    public static void openPic(Activity ctx, int requestCode) {
        Intent picIntent = new Intent(Intent.ACTION_PICK, null);
        picIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        ctx.startActivityForResult(picIntent, requestCode);
    }

    /**
     * open camera
     */
    public static File openCamera(Activity ctx, int requestCode) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                file.mkdirs();
            }
            File mFile = new File(file, System.currentTimeMillis() + ".jpg");
            Uri uri = getCameraFileUri(ctx, mFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            ctx.startActivityForResult(intent, requestCode);
            return mFile;
        } else {
            Toast.makeText(ctx, "Please confirm that SD card has been inserted.", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    /**
     *  FileUri
     *
     * @param ctx
     * @param mFile
     * @return
     */
    public static Uri getCameraFileUri(Activity ctx, File mFile) {
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(mFile);
        } else {
            uri = FileProvider.getUriForFile(ctx, ProviderUtil.getFileProviderName(ctx), mFile);
        }
        return uri;
    }



    public static Bitmap getBitmapFromUri(Uri uri, Context context, ImageView iv) {
        try {
            Bitmap mBitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
            iv.setImageBitmap(mBitmap);
            return mBitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }




}
