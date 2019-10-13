package com.example.imageview.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.example.imageview.R;
import com.example.imageview.model.User;
import com.example.imageview.utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Author       : Arvindo Mondal
 * Created on   : 22-09-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public class BaseImageView extends AppCompatImageView {

    /*
     * Path of them image to be clipped (to be shown)
     */
    Path clipPath;

    /*
     * Place holder drawable (with background color and initials)
     */
    Drawable drawable;

    /*
     * Used to set size and color of the member initials
     */
    TextPaint textPaint;

    /*
     * Used as background of the initials with user specific color
     */
    Paint paint;

    /*
     * To draw border
     */
    private Paint borderPaint;

    /*
     * Shape to be drawn
     */
    int shape;

    /*
     * Constants to define shape
     */
    protected static final int CIRCLE = 0;
    protected static final int RECTANGLE = 1;

    /*
     * Image width and height (both are same and constant, defined in dimens.xml
     * We cache them in this field
     */
    private int imageSize;

    /*
     * We will set it as 2dp
     */
    int cornerRadius;
    private int imageCornerColor;

    /*
     * Bounds of the canvas in float
     * Used to set bounds of member initial and background
     */
    RectF rectF;

    /*
        Border color, default is white
     */
    private int borderColor;

    private int backgroundInitialColor;

    /*
        Border width, default is 5.0f
     */
    private float borderWidth = 5.0f;
    private float borderWidthDefault = 5.0f;

    /*
        Image name
     */
    private String imageName;

    public BaseImageView(Context context) {
        super(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getAttributes(attrs);
        init(context);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAttributes(attrs);
        init(context);
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BaseImageView,
                0, 0);

        try {

            /*
             * Get the shape and set shape field accordingly
             * */
            String avatarShape = a.getString(R.styleable.BaseImageView_ImageShape);
//            int borderColor = a.getColor(R.styleable.BaseImageView_BorderColor,
//                    getResources().getColor(R.color.border_color));

            borderColor = a.getColor(R.styleable.BaseImageView_BorderColor, Color.parseColor("#FFFFFF"));
            backgroundInitialColor = a.getColor(R.styleable.BaseImageView_BackgroundInitialColor,
                    Color.parseColor("#FFFFFF"));

            imageName = a.getString(R.styleable.BaseImageView_ImageName);

            borderWidth = a.getDimension(R.styleable.BaseImageView_BorderWidth, 0);

            imageCornerColor = (int) a.getDimension(R.styleable.BaseImageView_ImageCornerRadius, 0);

            /*
             * If the attribute is not specified, consider circle shape
             */
            if (avatarShape == null) {
                shape = RECTANGLE;
            } else {
                if (getContext().getString(R.string.rectangle).equals(avatarShape)) {
                    shape = RECTANGLE;
                } else {
                    shape = CIRCLE;
                }
            }
        } finally {
            a.recycle();
        }
    }

    /*
     * Initialize fields
     */
    protected void init(Context context) {

        /*
         * Below Jelly Bean, clipPath on canvas would not work because lack of hardware acceleration
         * support. Hence, we should explicitly say to use software acceleration.
         * */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        rectF = new RectF();
        clipPath = new Path();

        imageSize = getResources().getDimensionPixelSize(R.dimen.avatar_size);
        cornerRadius = (int) Utils.dpToPixel(imageCornerColor, getResources());

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(backgroundInitialColor);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(16f * getResources().getDisplayMetrics().scaledDensity);
        textPaint.setColor(Color.WHITE);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);

        setValues();
    }

    /*
     * Set user specific fields in here
     * */
    private void setValues() {

        setDrawable();

        String sd = "https://i.pinimg.com/originals/0b/b3/70/0bb3704734e0a535ec846772f7d28be7.jpg";

        Picasso.get().load(sd)
                .placeholder(drawable)
                .fit()
                .centerCrop()
                .into(this);
    }

    /*
     * Create placeholder drawable
     */
    private void setDrawable() {
        drawable = new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {

                int centerX = Math.round(getBounds().width() * 0.5f);
                int centerY = Math.round(getBounds().height() * 0.5f);

                /*
                 * To draw text
                 * */
                if (imageName != null) {
                    float textWidth = textPaint.measureText(imageName) * 0.5f;
                    float textBaseLineHeight = textPaint.getFontMetrics().ascent * -0.4f;

                    /*
                     * Draw the background color before drawing initials text
                     * */
                    if (shape == RECTANGLE) {
                        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
                    } else {
                        canvas.drawCircle(centerX, centerY,
                                Math.max(getBounds().height() / 2, textWidth / 2), paint);
                    }

                    /*
                     * Draw the text above the background color
                     * */
                    canvas.drawText(imageName, centerX - textWidth, centerY + textBaseLineHeight, textPaint);
                }
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
    }


    /*
     * Set the canvas bounds here
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);
//        rectF.set(0, 0, screenWidth, screenHeight);

        if (shape == RECTANGLE) {
            float size = 0;
            if (borderWidth > 0) {
                size = borderWidth / 2;
            }
//            rectF.set(0, 0, screenWidth, screenHeight);
//            rectF.set(0 + size, 0 + size, screenWidth - size, screenHeight - size);

            float imageSideRatio = (float)drawable.getIntrinsicWidth() / (float)drawable.getIntrinsicHeight();
            float viewSideRatio = (float)MeasureSpec.getSize(widthMeasureSpec) / (float)MeasureSpec.getSize(heightMeasureSpec);
            if (imageSideRatio >= viewSideRatio) {
                // Image is wider than the display (ratio)
                int height = (int)(screenWidth / imageSideRatio);
                setMeasuredDimension(screenWidth, height);
                rectF.set(0 + size, 0 + size, screenWidth - size, height - size);
            } else {
                // Image is taller than the display (ratio)
                int width = (int)(screenHeight * imageSideRatio);
                setMeasuredDimension(width, screenHeight);
                rectF.set(0 + size, 0 + size, width - size, screenHeight - size);
            }
        } else {
            float size = 0;
            if (borderWidth > 0) {
                size = borderWidth / 2;
            }
            rectF.set(0 + size, 0 + size, screenWidth - size, screenWidth - size);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (shape == RECTANGLE) {
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, borderPaint);
            clipPath.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW);
        } else {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), (rectF.height() / 2), borderPaint);

            clipPath.addCircle(rectF.centerX(), rectF.centerY(), (rectF.height() / 2), Path.Direction.CW);
        }
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }


    public void setShowText(boolean mshowText) {
        invalidate();
        requestLayout();
    }
}
