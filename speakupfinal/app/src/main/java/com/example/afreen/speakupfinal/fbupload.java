package com.example.afreen.speakupfinal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by AFREEN on 12/2/2016.
 */

public class fbupload extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;public static Bitmap bitmap;ImageButton camera;public static String ss;EditText status;
    @Override
    public void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        setContentView(R.layout.fb);
        camera=(ImageButton)findViewById(R.id.photo);
        status=(EditText)findViewById(R.id.status);
camera.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",0);
        intent.putExtra("aspectY",0);
        intent.putExtra("outputX",200);
        intent.putExtra("outputY",150);
        try {

            intent.putExtra("return-data", true);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        catch(ActivityNotFoundException e)
        {}
Button but=(Button)findViewById(R.id.butt);
    but.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ss=status.getText().toString();
            Intent intent=new Intent(fbupload.this,facebook.class);
            startActivity(intent);
        }
    });}
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                bitmap = extras2.getParcelable("data");
                // imageView.setImageDrawable(null);
                camera.setImageBitmap(bitmap);



            }

        }
    }
public void progress()
{

}
}
