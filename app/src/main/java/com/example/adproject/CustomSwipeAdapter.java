package com.example.adproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by vedatyozkat on 07.12.2016.
 */

public class CustomSwipeAdapter extends PagerAdapter{
    private int[] image_resources = {R.drawable.dino01, R.drawable.dino02, R.drawable.dino03, R.drawable.dino04,
            R.drawable.dino05, R.drawable.dino06, R.drawable.dino07, R.drawable.dino08};
    private String[] isim_resources = {"BIR", "IKI", "UC", "DORT", "BES",
            "ALTI", "YEDI", "SEKIZ"};
    private String[] toast_resources = {"1", "22", "333", "4444", "55555",
            "666666", "7777777", "88888888"};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx)
    {

        this.ctx=ctx;

    }


    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container, false);
        final ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setLongClickable(true);
        TextView textView = (TextView) item_view.findViewById(R.id.image_count);
        imageView.setImageResource(image_resources[position]);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // shows the message
                Toast toast=Toast.makeText(ctx, toast_resources[position],Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300);
                toast.show();
                // audible sound
                MediaPlayer mp1 = MediaPlayer.create(ctx, R.raw.nick);
                mp1.start();
                // image animation
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 100);
                animation.setDuration(750);
                animation.setRepeatCount(5);
                // animation.setRepeatMode (2);
                // animation.setFillAfter(true);
                imageView.startAnimation(animation);
                return true;
            }
        });

        // top text definetion displayed
        textView.setText (isim_resources[position]);
        container.addView(item_view);
        return item_view;

    }

    // destroyItem is useful to release resources for better performance
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }
}
