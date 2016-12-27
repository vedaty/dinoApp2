package com.example.adproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.telephony.TelephonyManager;
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
    private int[] image_resources = {R.drawable.d01, R.drawable.d02, R.drawable.d03, R.drawable.d04,
            R.drawable.d05, R.drawable.d06, R.drawable.d07, R.drawable.d08, R.drawable.d09, R.drawable.d10};
    private String[] isim_resources = {"Sevimliyus", "Yerebakanus", "Dişlerikamaşanus", "Çokboynus", "Uzunkuyrukus",
            "Kabarıkus", "Kocamanus", "Zırhlus", "Uçarus", "Tekboynus"};
    private String[] toast_resources = {
            "Brontosaurus: Otçuldur, büyükleri 20 metre boyunda 30 ton ağırlığındadır.",
            "Stegosaurus: Otçuldur, 150 milyon yıl önce yaşamışlardır.",
            "Tyrannosaurus: En vahşi avcı türlerin başında gelir, ağrılıkları 10 tonun üstüne kadar çıkar.",
            "Triceratops: Otçuldur, 3 boynuzu vardır.",
            "Apatosaurus: Otçuldur, 20-25 metre boyunda 30-32 ton ağırlığındadır.",
            "Euoplocephalus: Otçuldur, sadece 2 tona kadar büyüyebilir.",
            "Giganotosaurus: En büyük etçil türdür. Yetişkinleri 12 metreden büyük, 13 tondan ağır olabilir.",
            "Styracosaurus: Otçuldur, boynuzu dışında kafatasının üstünde yaka gibi görünen bir oluşum vardır.",
            "Pterosaurs: Uçan bir türdür.",
            "Saurolophus: Otçuldur, kendine özgü kafa yapısı vardır. Bir çok kıtada var olmuştur."};
    private Context ctx;
    private LayoutInflater layoutInflater;
    String identifier = null;

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

                // get device info
                // Toast.makeText(ctx, "SDK:"+(Build.VERSION.SDK_INT)+" DEVICE:"+(Build.BRAND)+" VERSION:"+(Build.VERSION.RELEASE),Toast.LENGTH_LONG) .show();

                // get IMEI information in identifier
                TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
                String identifier = tm.getDeviceId();
                // if identifier is not null then it is a mobile phone otherwise it is a tablet
                // no sound if it is a tablet
                if (identifier!=null) {
                    // audible sound
                    // sound section doesnt work on nexus 7
                    MediaPlayer mp1 = MediaPlayer.create(ctx, R.raw.nick);
                    mp1.start();
                }
                // image animation
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 100);
                animation.setDuration(750);
                animation.setRepeatCount(5);
                // * animation.setRepeatMode (2);
                // * animation.setFillAfter(true);
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
