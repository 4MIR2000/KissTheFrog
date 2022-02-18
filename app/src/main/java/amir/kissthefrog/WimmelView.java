package amir.kissthefrog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Amir on 30.03.2015.
 */
public class WimmelView extends View{
    Random rnd;
    private long randomSeed=1;
    private int imageCount;

    private Paint paint = new Paint();


    private int[] images2;

    public void setImages(int[] image){
        images2 = image;

    }

    public void setImageCount(int imageCount){
        this.imageCount = imageCount;
        randomSeed = System.currentTimeMillis();
        invalidate();
    }

    public WimmelView(Context context){
        super(context);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        rnd = new Random(randomSeed);

        for(int image: images2){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),image);

          for(int i=0; i<imageCount/images2.length;i++){
             float left = (float) (rnd.nextFloat()
                       * (getWidth() - bitmap.getWidth()));

             float top = (float) (rnd.nextFloat())
                       *(getHeight() - bitmap.getHeight());

             canvas.drawBitmap(bitmap, left, top, paint);
         }

            bitmap.recycle();


    }



}
}