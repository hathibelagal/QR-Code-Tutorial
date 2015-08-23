package com.github.hathibelagal.barcodereader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by Hathi on 23/8/15.
 */
public class PhotoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Bitmap myQRCode = BitmapFactory.decodeStream(
                    getAssets().open("myqrcode.jpg")
            );

            BarcodeDetector barcodeDetector =
                    new BarcodeDetector.Builder(this)
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();

            Frame myFrame = new Frame.Builder()
                    .setBitmap(myQRCode)
                    .build();

            SparseArray<Barcode> barcodes = barcodeDetector.detect(myFrame);

            // Check if at least one barcode was detected
            if(barcodes.size() != 0) {

                // Print the QR code's message
                Log.d("My QR Code's Data",
                        barcodes.valueAt(0).displayValue
                );
            }
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
