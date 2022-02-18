package amir.kissthefrog;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private int points;
   private int round;
   private int countdown;
   private Random rnd = new Random();
   private Handler handler = new Handler();
   private ImageView frog;
   private Typeface ttf;
   private boolean help;
   private int highscore;
    private static final int[] images_green = {R.drawable.distract1, R.drawable.distract2,
            R.drawable.distract3,R.drawable.distract4,
            R.drawable.distract5,R.drawable.distract6,
            R.drawable.distract7,R.drawable.distract8};

    private static final int[] images_darkgreen = {R.drawable.distract1_darkgreen, R.drawable.distract2_darkgreen,
            R.drawable.distract3_darkgreen,R.drawable.distract4_darkgreen,
            R.drawable.distract5_darkgreen,R.drawable.distract6_darkgreen,
            R.drawable.distract7_darkgreen,R.drawable.distract8_darkgreen};


    private static final int[] images_blue = {R.drawable.distract1_blue, R.drawable.distract2_blue,
            R.drawable.distract3_blue,R.drawable.distract4_blue,
            R.drawable.distract5_blue,R.drawable.distract6_blue,
            R.drawable.distract7_blue,R.drawable.distract8_blue};

    private static final int[] images_pink = {R.drawable.distract1_pink, R.drawable.distract2_pink,
            R.drawable.distract3_pink,R.drawable.distract4_pink,
            R.drawable.distract5_pink,R.drawable.distract6_pink,
            R.drawable.distract7_pink,R.drawable.distract8_pink};

    private static final int[] images_light_blue = {R.drawable.distract1_light_blue, R.drawable.distract2_light_blue,
            R.drawable.distract3_light_blue,R.drawable.distract4_light_blue,
            R.drawable.distract5_light_blue,R.drawable.distract6_light_blue,
            R.drawable.distract7_light_blue,R.drawable.distract8_light_blue};

    private static final int[] images_yellow = {R.drawable.distract1_yellow, R.drawable.distract2_yellow,
            R.drawable.distract3_yellow,R.drawable.distract4_yellow,
            R.drawable.distract5_yellow,R.drawable.distract6_yellow,
            R.drawable.distract7_yellow,R.drawable.distract8_yellow};

    private static final int[] images_red = {R.drawable.distract1_red, R.drawable.distract2_red,
            R.drawable.distract3_red,R.drawable.distract4_red,
            R.drawable.distract5_red,R.drawable.distract6_red,
            R.drawable.distract7_red,R.drawable.distract8_red};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");
        showStartFragment();

        loadHighscore();
        writeHighscore();



    }

    private void writeHighscore(){
        fillTextView(R.id.highscore_start, Integer.toString(highscore));
    }

       public void newGame(){
         points = 0;
         round = 1;
         initRound();
    }

        public void initRound(){

          countdown=10;
          ViewGroup container = (ViewGroup) findViewById(R.id.container);
          container.removeAllViews();
          WimmelView wv = new WimmelView(this);
          if(round==1 || round==2) {
              wv.setImages(images_green);
          }
          if(round==3|| round==4){
                wv.setImages(images_darkgreen);
          }

            if(round==5|| round==6){
                wv.setImages(images_blue);
            }

            if(round==7|| round==8){
                wv.setImages(images_pink);
            }

            if(round==9 || round==10){
                wv.setImages(images_light_blue);
            }

            if(round==11 || round==12 ){
                wv.setImages(images_yellow);
            }
            if(round>12){
                wv.setImages(images_red);
            }

          container.addView(wv, ViewGroup.LayoutParams.MATCH_PARENT,
                           ViewGroup.LayoutParams.MATCH_PARENT);
          wv.setImageCount(8*(10+round));

            frog = new ImageView(this);
            frog.setId(R.id.Frog_ID);
            if(round ==1|| round == 2){
               frog.setImageResource(R.drawable.frog);}

            if(round ==3 || round == 4){
              frog.setImageResource(R.drawable.frog_darkgreen);}

            if(round==5|| round==6){
              frog.setImageResource(R.drawable.frog_blue);
            }

            if(round==7|| round==8){
                frog.setImageResource(R.drawable.frog_pink);
            }

            if(round==9|| round==10){
                frog.setImageResource(R.drawable.frog_light_blue);
            }

            if(round==11 || round==12 ){
                frog.setImageResource(R.drawable.frog_yellow);
            }

            if(round>12){
                frog.setImageResource(R.drawable.frog_red);
            }

            frog.setScaleType(ImageView.ScaleType.CENTER);
            float scale = getResources().getDisplayMetrics().density;

            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams
                    (Math.round(64*scale),Math.round(61*scale));

            lp.leftMargin = rnd.nextInt(container.getWidth()-64);
            lp.topMargin = rnd.nextInt(container.getHeight()-61);
            lp.gravity =Gravity.TOP + Gravity.LEFT;
            frog.setOnClickListener(this);
            container.addView(frog, lp);


            container.addView(
                    getLayoutInflater().
                            inflate(R.layout.activity_main, null));


            update();
            handler.postDelayed(runnable, 1000-round*50);
       }

       private void update(){

        fillTextView(R.id.points, Integer.toString(points));
        fillTextView(R.id.round, Integer.toString(round));
        loadHighscore();
        fillTextView(R.id.countdown, Integer.toString(countdown*1000));
        fillTextView(R.id.highscore, Integer.toString(highscore));
           ((TextView)findViewById(R.id.countdown)).setTypeface(ttf);
           ((TextView)findViewById(R.id.round)).setTypeface(ttf);
           ((TextView)findViewById(R.id.points)).setTypeface(ttf);
           ((TextView)findViewById(R.id.highscore)).setTypeface(ttf);

    }

        private void fillTextView(int id, String text){
            TextView tv=(TextView) findViewById(id);
            tv.setText(text);

        }


         private void showStartFragment(){
            ViewGroup container = (ViewGroup) findViewById(R.id.container);
            container.removeAllViews();
            container.addView(
                    getLayoutInflater().
                            inflate(R.layout.fragment_start, null));
             container.findViewById(R.id.start).setOnClickListener(this);
             ((TextView)findViewById(R.id.title)).setTypeface(ttf);
             ((TextView)findViewById(R.id.start)).setTypeface(ttf);
             writeHighscore();
             findViewById(R.id.help).setOnClickListener(this);
             ((TextView)findViewById(R.id.help)).setTypeface(ttf);

             ((TextView)findViewById(R.id.highscore_text)).setTypeface(ttf);
             ((TextView)findViewById(R.id.highscore_start)).setTypeface(ttf);


        }

         private void showGameOverFragment(){
            ViewGroup container =(ViewGroup) findViewById(R.id.container);
            container.addView(
                    getLayoutInflater().
                    inflate(R.layout.fragment_gameover,null));

            container.findViewById(R.id.play_again).setOnClickListener(this);
             ((TextView)findViewById(R.id.gameOver)).setTypeface(ttf);
             ((TextView)findViewById(R.id.play_again)).setTypeface(ttf);

        }

         private void countdown(){
             countdown--;
             update();
             if(countdown <= 0){
                 frog.setOnClickListener(null);
                 if(points>highscore) {
                     saveHighscore(points);
                     update();

                 }
                 showGameOverFragment();


             }else{
                 handler.postDelayed(runnable, 1000 - round * 50);
                 }
             }

          private void loadHighscore(){
              SharedPreferences sp = getPreferences(MODE_PRIVATE);
              highscore=sp.getInt("highscore",0);
          }

          private void saveHighscore(int points){
              highscore=points;
              SharedPreferences sp = getPreferences(MODE_PRIVATE);
              SharedPreferences.Editor e = sp.edit();
              e.putInt("highscore", highscore);
              e.commit();
          }

          private Runnable runnable = new Runnable() {
              @Override
              public void run() {
                  countdown();
              }
          };

          @Override
          protected void onPause(){
              super.onPause();
              handler.removeCallbacks(runnable);
          }



    @Override
           public void onClick(View view) {
        if (view.getId() == R.id.start) {
            startGame();
        } else {
            if (view.getId() == R.id.play_again) {
                showStartFragment();



            }else if(view.getId()==R.id.Frog_ID){
                kissFrog();


            }else if(view.getId()==R.id.help){
                help=true;
                showTutorial();
            }
        }
    }

    private void kissFrog() {
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(toast.LENGTH_SHORT);

        TextView textView = new TextView(this);
        textView.setText(R.string.kissed);
        textView.setTextColor(getResources().getColor(R.color.points));
        textView.setTextSize(48f);
        textView.setTypeface(ttf);

        toast.setView(textView);
        toast.show();
        handler.removeCallbacks(runnable);
        points+=countdown*1000;
        round++;
        initRound();
    }

    private void showTutorial(){

        final Dialog dialog = new Dialog(this,
        android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_tuturial);
        ((TextView)(dialog.findViewById(R.id.text))).setTypeface(ttf);
        ((TextView)(dialog.findViewById(R.id.start))).setTypeface(ttf);
        dialog.findViewById(R.id.start).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                dialog.dismiss();
                startGame();
            }


        });

        dialog.show();

    }

    public void startGame(){
          newGame();

      }
}

