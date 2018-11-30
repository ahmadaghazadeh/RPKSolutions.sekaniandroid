package com.example.sekini.utils.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.sekini.R;
import com.example.sekini.app.GlideApp;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.embedded.SekaniWordExampleDto;
import com.example.sekini.utils.common.CommonUtils;

import java.util.List;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;


public class BindingUtils {

    public enum TextCaps{
        Lower,Upper,None
    }

    @BindingAdapter("error")
    public static void setError(EditText text, String error) {
        if (error != null) {
            text.setError(error);
        }
    }

    @BindingAdapter("error")
    public static void setError(TextInputEditText text, String error) {
        if (error != null) {
            text.setError(error);
        }
    }


//    @BindingAdapter("imageUrlCircle")
//    public static void loadImageUrlCircle(ImageView imageView, String url) {
//        if (url != null) {
//            Context context = imageView.getContext();
//            CommonUtils commonUtils = new CommonUtils(context);
//            GlideApp.with(imageView)
//                    .load(url)
//                    .apply(bitmapTransform(new CropCircleTransformation()))
//                    .placeholder(commonUtils.getCircularProgressDrawable())
//                    .into(imageView);
//        }
//    }


    @BindingAdapter("wordExample")
    public static void wordExample(LinearLayout linearLayout, List<SekaniWordExampleDto> sekaniWordExamplesEntities) {
        Context context = linearLayout.getContext();
        linearLayout.removeAllViews();
        if (sekaniWordExamplesEntities == null)
            return;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (SekaniWordExampleDto sekaniWordExampleDto : sekaniWordExamplesEntities) {
            ConstraintLayout exampleLayout = (ConstraintLayout) inflater.inflate(R.layout.view_word_example, null);
            ((TextView) exampleLayout.findViewById(R.id.txtSekaniExample)).setText(
                    String.format("%s%s", context.getString(R.string.dash),
                            sekaniWordExampleDto.sekaniWordExamplesEntity.sekani.toLowerCase()));
            ((TextView) exampleLayout.findViewById(R.id.txtEnglishExample)).setText(sekaniWordExampleDto.sekaniWordExamplesEntity.english);
            ImageView imageView = exampleLayout.findViewById(R.id.btnAudio);
            imageView.setOnClickListener(v -> CommonUtils.PlayAudio(sekaniWordExampleDto.sekaniWordExampleAudiosEntity.content));
            imageView.setVisibility(sekaniWordExampleDto.sekaniWordExampleAudiosEntity == null ? View.GONE : View.VISIBLE);
            linearLayout.addView(exampleLayout);
        }

    }

    @BindingAdapter("playAudio")
    public static void playAudio(View view, byte[] audio) {
        view.setOnClickListener(v -> CommonUtils.PlayAudio(audio));

    }

    @BindingAdapter("setBackground")
    public static void setBackground(View view, @DrawableRes int resId){
        if(resId!=0)
            view.setBackgroundResource(resId);

    }

    @BindingAdapter("sekaniRootImage")
    public static void loadImageBase64Sekani(ImageView imageView, SekaniRootImagesEntity entity) {
        if (entity != null) {
            Context context = imageView.getContext();

            CommonUtils commonUtils = new CommonUtils(context);
            GlideApp.with(imageView)
                    .asBitmap()
                    .load(entity.content)
                    .placeholder(commonUtils.getCircularProgressDrawable())
                    .into(imageView);


        } else {
            imageView.setImageBitmap(null);
        }
    }

    @BindingAdapter("image")
    public static void loadImageBase64(ImageView imageView, byte[] bytes) {
        if (bytes != null) {
            Context context = imageView.getContext();

            CommonUtils commonUtils = new CommonUtils(context);
            GlideApp.with(imageView)
                    .asBitmap()
                    .load(bytes)
                    .placeholder(commonUtils.getCircularProgressDrawable())
                    .into(imageView);
        } else {
            imageView.setImageBitmap(null);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImageUrl(ImageView imageView, String url) {
        if (url != null) {
            Context context = imageView.getContext();

            CommonUtils commonUtils = new CommonUtils(context);

            GlideApp.with(imageView)
                    .load(url)
                    .placeholder(commonUtils.getCircularProgressDrawable())
                    .into(imageView);
        }
    }

    @BindingAdapter("setToggleListener")
    public static void onToggleSwitchChangeListener(ToggleSwitch view, BaseToggleSwitch.OnToggleSwitchChangeListener onToggleSwitchChangeListener) {
        if (onToggleSwitchChangeListener != null) {
            view.setOnToggleSwitchChangeListener(onToggleSwitchChangeListener);
        }
    }
//
//    @BindingAdapter("paletteDrawable")
//    public static void setCollapsingToolbarLayoutPaletteDrawable(CollapsingToolbarLayout view, Drawable drawable) {
//        if (drawable != null) {
//            Palette vibrantSwatch = Palette.from(CommonUtils.drawableToBitmap(drawable)).generate();
//            view.setContentScrimColor(vibrantSwatch.getMutedColor(colorPrimaryDark));
//            view.setStatusBarScrimColor(vibrantSwatch.getMutedColor(colorPrimary));
//        }
//
//    }
//
//    @BindingAdapter("paletteDrawable")
//    public static void setToolbarPaletteDrawable(Toolbar view, Drawable drawable) {
//        if (drawable != null) {
//            Context context = view.getContext();
//            Palette vibrantSwatch = Palette.from(CommonUtils.drawableToBitmap(drawable)).generate();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                view.setTitleTextColor(vibrantSwatch.getMutedColor(context.getResources().getColor(textLightPrimary)));
//            }
//        }
//    }

    @BindingAdapter("drawable")
    public static void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    @BindingAdapter("visibility")
    public static void setVisibility(View view, Boolean value) {
        if (value != null)
            view.setVisibility(value ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter("tintColor")
    public static void setTintColor(ImageView imageView, @ColorRes int resourceId) {
        if (resourceId != 0)
            imageView.setColorFilter(imageView.getResources().getColor(resourceId), PorterDuff.Mode.SRC_IN);

    }

    @BindingAdapter("android:src")
    public static void setImage(ImageView imageView, int resourceId) {
        if (resourceId != 0)
            imageView.setImageResource(resourceId);
    }


    @BindingAdapter("android:drawableStart")
    public static void setDrawableStart(TextView textView, int resourceId) {
        if (resourceId != 0) {
            Drawable drawable = AppCompatResources.getDrawable(textView.getContext(), resourceId);
            Drawable[] drawables = textView.getCompoundDrawables();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                textView.setCompoundDrawablesRelative(drawable,
                        drawables[1], drawables[2], drawables[3]);
            } else {
                textView.setCompoundDrawables(drawable,
                        drawables[1], drawables[2], drawables[3]);
            }
        }

    }


    //-------------------SwipeRefreshLayout----------------------

    @BindingAdapter("onRefresh")
    public static void onRefresh(SwipeRefreshLayout view, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        if (onRefreshListener != null)
            view.setOnRefreshListener(onRefreshListener);
    }

    @BindingAdapter("onTouchListener")
    public static void onRefresh(View view, View.OnTouchListener onTouchListener) {
        if (onTouchListener != null)
            view.setOnTouchListener(onTouchListener);
    }


    @BindingAdapter("Enable")
    public static void setEnable(SwipeRefreshLayout view, Boolean flag) {
        if (flag != null)
            view.setEnabled(flag);
    }

    @BindingAdapter("ColorScheme")
    public static void setColorScheme(SwipeRefreshLayout view, int colorScheme) {
        view.setColorSchemeResources(CommonUtils.colorScheme);
    }

    @BindingAdapter("ColorScheme")
    public static void setColorScheme(ProgressBar view, int colorScheme) {
        if (colorScheme != 0)
            view.getIndeterminateDrawable().setColorFilter(colorScheme
                    , PorterDuff.Mode.SRC_OUT);
    }


}
