package com.example.pratik.womensafety;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
            this.context = context;
    }

    public  int[] slide_images ={
    R.drawable.namaste,
            R.drawable.easy,
            R.drawable.register,
            R.drawable.share

    };

    public String[] slide_heading ={

            "Welcome to bSecure",
            "Never been this Easy to Use",
            "Connect With Us",
            "Sharing is Caring"
    };

    public String[] slide_desc ={
            "Let's together make INDIA a better place..",
            "1.Take a Picture.\n2.Upload it.\n3.Press SOS in Emergency.",
            "Register now and become part of the Family..",
            "Making INDIA safe is now your Responsibility too.."
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_slide, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_images);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription =(TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_heading[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
