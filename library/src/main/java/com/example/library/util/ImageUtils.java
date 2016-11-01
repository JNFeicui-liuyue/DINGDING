package com.example.library.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 *
 * ImageUtils
 * 描述：图片处理工具类
 * 时间：2016年9月13日
 * <ul>
 * convert between Bitmap, byte array, Drawable
 * <li>{@link #bitmapToByte(Bitmap)}</li>
 * <li>{@link #bitmapToDrawable(Bitmap)}</li>
 * <li>{@link #byteToBitmap(byte[])}</li>
 * <li>{@link #byteToDrawable(byte[])}</li>
 * <li>{@link #drawableToBitmap(Drawable)}</li>
 * <li>{@link #drawableToByte(Drawable)}</li>
 * </ul>
 * <ul>
 * get image
 * <li>{@link #getInputStreamFromUrl(String, int)}</li>
 * <li>{@link #getBitmapFromUrl(String, int)}</li>
 * <li>{@link #getDrawableFromUrl(String, int)}</li>
 * </ul>
 * <ul>
 * scale image
 * <li>{@link #scaleImageTo(Bitmap, int, int)}</li>
 * <li>{@link #scaleImage(Bitmap, float, float)}</li>
 * </ul>
 * 
 */
public class ImageUtils {
    private Bitmap bitmap;
    private static ImageUtils img;
    private ImageUtils() {
    }

    public static  ImageUtils getInstance(){
        if(img==null)
            img=new ImageUtils();
        return  img;
    }




    /**
     * 将bitmap转换成byte[]
     * convert Bitmap to byte array
     * 
     * @param b
     * @return
     */
    public   byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    public Bitmap getBitmapFromSD(String path){
        return  BitmapFactory.decodeFile(path.toString());
    }

    /**
     * 将byte[]转换成bitmap
     * convert byte array to Bitmap
     * 
     * @param b
     * @return
     */
    public  Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * 将Drawable转换成bitmap
     * convert Drawable to Bitmap
     * 
     * @param d
     * @return
     */
    public  Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * 将bitmap转换成Drawable
     * convert Bitmap to Drawable
     * 
     * @param b
     * @return
     */
    public  Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * 将Drawable转换成byte[]
     * convert Drawable to byte array
     * 
     * @param d
     * @return
     */
    public  byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * 将byte[]转换成Drawable
     * convert byte array to Drawable
     * 
     * @param b
     * @return
     */
    public  Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * 从网络中获取图片文件流，需要手动关闭流
     * get input stream from network by imageurl, you need to close inputStream yourself
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     * @return
     */
    public  InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        return getInputStreamFromUrl(imageUrl, readTimeOutMillis, null);
    }



    /**
     *  从网络中获取图片文件流，需要手动关闭流
     * get input stream from network by imageurl, you need to close inputStream yourself
     * 
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @param requestProperties http request properties
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public   InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis,
            Map<String, String> requestProperties) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            HttpUtils.setURLConnection(requestProperties, con);
            if (readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }
            stream = con.getInputStream();
        } catch (MalformedURLException e) {
            IOUtils.close(stream);
            throw new RuntimeException("MalformedURLException occurred. ", e);
        } catch (IOException e) {
            IOUtils.close(stream);
            throw new RuntimeException("IOException occurred. ", e);
        }
        return stream;
    }

    /**
     * 从网络中获取drawable
     * get drawable by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     * @return
     */
    public  Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        return getDrawableFromUrl(imageUrl, readTimeOutMillis, null);
    }

    /**
     * 从网络中获取drawable
     * get drawable by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @param requestProperties http request properties
     * @return
     */
    public  Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis,
            Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis, requestProperties);
        Drawable d = Drawable.createFromStream(stream, "src");
        IOUtils.close(stream);
        return d;
    }

    /**
     * 从网络中获取Bitmap
     * get Bitmap by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOut
     * @return
     */
    public  Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        return getBitmapFromUrl(imageUrl, readTimeOut, null);
    }

    /**
     *  从网络中获取Bitmap
     * get Bitmap by imageUrl
     * 
     * @param imageUrl
     * @param requestProperties http request properties
     * @return
     */
    public  Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut, requestProperties);
        Bitmap b = BitmapFactory.decodeStream(stream);
        IOUtils.close(stream);
        return b;
    }



    /**
     * 缩放图片
     * scale image
     * 
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public   Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * 缩放图片
     * scale image
     * 
     * @param org
     * @param scaleWidth 缩放比例 sacle of width
     * @param scaleHeight 缩放比例 scale of height
     * @return
     */
    public  Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }


    /**
     * 质量压缩
     *
     * @author ping 2015-1-5 下午1:29:58
     * @param image
     * @param maxkb
     * @return
     */
    public  Bitmap compressBitmap(Bitmap image, int maxkb) {
        // L.showlog(压缩图片);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        // Log.i(test,原始大小 + baos.toByteArray().length);
        while (baos.toByteArray().length / 1024 > maxkb) { // 循环判断如果压缩后图片是否大于(maxkb)50kb,大于继续压缩
            // Log.i(test,压缩一次!);
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        // Log.i(test,压缩后大小 + baos.toByteArray().length);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
     * 官网：获取压缩后的图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public  Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 官网：获取压缩后的图片
     *
     * @param filepath
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public  Bitmap decodeSampledBitmapFromFile(String filepath,
                                                     int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filepath, options);
    }

    public  Bitmap decodeSampledBitmapFromBitmap(Bitmap bitmap,
                                                       int reqWidth, int reqHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] data = baos.toByteArray();

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * 计算压缩比例值(改进版 by touch_ping)
     *
     * 原版2>4>8...倍压缩 当前2>3>4...倍压缩
     *
     * @param options
     *            解析图片的配置信息
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度O
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public  int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

        final int picheight = options.outHeight;
        final int picwidth = options.outWidth;
        Logger.i("原尺寸:" + picwidth + " * " + picheight);

        int targetheight = picheight;
        int targetwidth = picwidth;
        int inSampleSize = 1;

        if (targetheight > reqHeight || targetwidth > reqWidth) {
            while (targetheight >= reqHeight && targetwidth >= reqWidth) {
                Logger.i("压缩:" + inSampleSize + "倍");
                inSampleSize += 1;
                targetheight = picheight / inSampleSize;
                targetwidth = picwidth / inSampleSize;
            }
        }

        Logger.i("最终压缩比例:" + inSampleSize + "倍");
        Logger.i("新尺寸:" + targetwidth + " * " + targetheight);
        return inSampleSize;
    }

    /**
     * 水平翻转处理
     * @param bitmap 原图
     * @return 水平翻转后的图片
     */
    public Bitmap reverseByHorizontal(Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /**
     * 垂直翻转处理
     * @param bitmap 原图
     * @return 垂直翻转后的图片
     */
    public Bitmap reverseByVertical(Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /**
     * 将给定资源ID的Drawable转换成Bitmap
     * @param context 上下文
     * @param resId Drawable资源文件的ID
     * @return 新的Bitmap
     */
    public Bitmap drawableToBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 圆角处理
     * @param pixels 角度，度数越大圆角越大
     * @return 转换成圆角后的图片
     */
    public Bitmap roundCorner(float pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  //创建一个同原图一样大小的矩形，用于把原图绘制到这个矩形上
        RectF rectF = new RectF(rect);  //创建一个精度更高的矩形，用于画出圆角效果
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0); //涂上黑色全透明的底色
        paint.setColor(0xff424242);  //设置画笔的颜色为不透明的灰色
        canvas.drawRoundRect(rectF, pixels, pixels, paint); //用给给定的画笔把给定的矩形以给定的圆角的度数画到画布
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint); //用画笔paint将原图bitmap根据新的矩形重新绘制
        return output;
    }

    /**
     * 倒影处理
     * @param reflectionSpacing 原图与倒影之间的间距
     * @return 加上倒影后的图片
     */
    public Bitmap reflection(int reflectionSpacing, int reflectionHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

		/* 获取倒影图片，并创建一张宽度与原图相同，但高度等于原图的高度加上间距加上倒影的高度的图片，并创建画布。画布分为上中下三部分，上：是原图；中：是原图与倒影的间距；下：是倒影 */
        Bitmap reflectionImage = reverseByVertical(bitmap);//
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height + reflectionSpacing + reflectionHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);

		/* 将原图画到画布的上半部分，将倒影画到画布的下半部分，倒影与画布顶部的间距是原图的高度加上原图与倒影之间的间距 */
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionSpacing, null);
        reflectionImage.recycle();

		/* 将倒影改成半透明，创建画笔，并设置画笔的渐变从半透明的白色到全透明的白色，然后再倒影上面画半透明效果 */
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionSpacing, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, height+reflectionSpacing, width, bitmapWithReflection.getHeight() + reflectionSpacing, paint);

        return bitmapWithReflection;
    }

    /**
     * 倒影处理
     * @return 加上倒影后的图片
     */
    public Bitmap reflection() {
        return reflection(4, bitmap.getHeight() / 2);
    }

    /**
     * 旋转处理
     * @param angle 旋转角度
     * @param px 旋转中心点在X轴的坐标
     * @param py 旋转中心点在Y轴的坐标
     * @return 旋转后的图片
     */
    public Bitmap rotate(float angle, float px, float py){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle, px, py);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /**
     * 旋转后处理
     * @param angle 旋转角度
     * @return 旋转后的图片
     */
    public Bitmap rotate(float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /**
     * 合并Bitmap
     * @param bgd 背景Bitmap
     * @param fg 前景Bitmap
     * @return 合成后的Bitmap
     */
    public  Bitmap combineImages(Bitmap bgd, Bitmap fg) {
        Bitmap bmp;

        int width = bgd.getWidth() > fg.getWidth() ? bgd.getWidth() : fg
                .getWidth();
        int height = bgd.getHeight() > fg.getHeight() ? bgd.getHeight() : fg
                .getHeight();

        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bgd, 0, 0, null);
        canvas.drawBitmap(fg, 0, 0, paint);

        return bmp;
    }

    /**
     * 合并
     * @param bgd 后景Bitmap
     * @param fg 前景Bitmap
     * @return 合成后Bitmap
     */
    public  Bitmap combineImagesToSameSize(Bitmap bgd, Bitmap fg) {
        Bitmap bmp;

        int width = bgd.getWidth() < fg.getWidth() ? bgd.getWidth() : fg
                .getWidth();
        int height = bgd.getHeight() < fg.getHeight() ? bgd.getHeight() : fg
                .getHeight();

        if (fg.getWidth() != width && fg.getHeight() != height) {
            fg = zoom(fg, width, height);
        }
        if (bgd.getWidth() != width && bgd.getHeight() != height) {
            bgd = zoom(bgd, width, height);
        }

        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bgd, 0, 0, null);
        canvas.drawBitmap(fg, 0, 0, paint);

        return bmp;
    }

    /**
     * 放大缩小图片
     *
     * @param bitmap 源Bitmap
     * @param w 宽
     * @param h 高
     * @return 目标Bitmap
     */
    public  Bitmap zoom(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    /**
     * 饱和度处理
     * @param saturationValue 新的饱和度值
     * @return 改变了饱和度值之后的图片
     */
    public Bitmap saturation(int saturationValue){
        //计算出符合要求的饱和度值
        float newSaturationValue = saturationValue * 1.0F / 127;
        //创建一个颜色矩阵
        ColorMatrix saturationColorMatrix = new ColorMatrix();
        //设置饱和度值
        saturationColorMatrix.setSaturation(newSaturationValue);
        //创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(saturationColorMatrix));
        //创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        //将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 亮度处理
     * @param lumValue 新的亮度值
     * @return 改变了亮度值之后的图片
     */
    public Bitmap lum(int lumValue){
        //计算出符合要求的亮度值
        float newlumValue = lumValue * 1.0F / 127;
        //创建一个颜色矩阵
        ColorMatrix lumColorMatrix = new ColorMatrix();
        //设置亮度值
        lumColorMatrix.setScale(newlumValue, newlumValue, newlumValue, 1);
        //创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(lumColorMatrix));
        //创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        //将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 色相处理
     * @param hueValue 新的色相值
     * @return 改变了色相值之后的图片
     */
    public Bitmap hue(int hueValue){
        //计算出符合要求的色相值
        float newHueValue = (hueValue - 127) * 1.0F / 127 * 180;
        //创建一个颜色矩阵
        ColorMatrix hueColorMatrix = new ColorMatrix();
        // 控制让红色区在色轮上旋转的角度
        hueColorMatrix.setRotate(0, newHueValue);
        // 控制让绿红色区在色轮上旋转的角度
        hueColorMatrix.setRotate(1, newHueValue);
        // 控制让蓝色区在色轮上旋转的角度
        hueColorMatrix.setRotate(2, newHueValue);
        //创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(hueColorMatrix));
        //创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        //将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 亮度、色相、饱和度处理
     * @param lumValue 亮度值
     * @param hueValue 色相值
     * @param saturationValue 饱和度值
     * @return 亮度、色相、饱和度处理后的图片
     */
    public Bitmap lumAndHueAndSaturation(int lumValue, int hueValue, int saturationValue){
        //计算出符合要求的饱和度值
        float newSaturationValue = saturationValue * 1.0F / 127;
        //计算出符合要求的亮度值
        float newlumValue = lumValue * 1.0F / 127;
        //计算出符合要求的色相值
        float newHueValue = (hueValue - 127) * 1.0F / 127 * 180;

        //创建一个颜色矩阵并设置其饱和度
        ColorMatrix colorMatrix = new ColorMatrix();

        //设置饱和度值
        colorMatrix.setSaturation(newSaturationValue);
        //设置亮度值
        colorMatrix.setScale(newlumValue, newlumValue, newlumValue, 1);
        // 控制让红色区在色轮上旋转的角度
        colorMatrix.setRotate(0, newHueValue);
        // 控制让绿红色区在色轮上旋转的角度
        colorMatrix.setRotate(1, newHueValue);
        // 控制让蓝色区在色轮上旋转的角度
        colorMatrix.setRotate(2, newHueValue);

        //创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        //将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 怀旧效果处理
     * @param bitmap 原图
     * @return 怀旧效果处理后的图片
     */
    public Bitmap nostalgic(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255
                        : newB);
                pixels[width * i + k] = newColor;
            }
        }
        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 模糊效果处理
     * @return 模糊效果处理后的图片
     */
    public Bitmap blur() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int newColor = 0;

        int[][] colors = new int[9][3];
        for (int i = 1, length = width - 1; i < length; i++) {
            for (int k = 1, len = height - 1; k < len; k++) {
                for (int m = 0; m < 9; m++) {
                    int s = 0;
                    int p = 0;
                    switch (m) {
                        case 0:
                            s = i - 1;
                            p = k - 1;
                            break;
                        case 1:
                            s = i;
                            p = k - 1;
                            break;
                        case 2:
                            s = i + 1;
                            p = k - 1;
                            break;
                        case 3:
                            s = i + 1;
                            p = k;
                            break;
                        case 4:
                            s = i + 1;
                            p = k + 1;
                            break;
                        case 5:
                            s = i;
                            p = k + 1;
                            break;
                        case 6:
                            s = i - 1;
                            p = k + 1;
                            break;
                        case 7:
                            s = i - 1;
                            p = k;
                            break;
                        case 8:
                            s = i;
                            p = k;
                    }
                    pixColor = bitmap.getPixel(s, p);
                    colors[m][0] = Color.red(pixColor);
                    colors[m][1] = Color.green(pixColor);
                    colors[m][2] = Color.blue(pixColor);
                }

                for (int m = 0; m < 9; m++) {
                    newR += colors[m][0];
                    newG += colors[m][1];
                    newB += colors[m][2];
                }

                newR = (int) (newR / 9F);
                newG = (int) (newG / 9F);
                newB = (int) (newB / 9F);

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                newColor = Color.argb(255, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }
        return newBitmap;
    }

    /**
     * 柔化效果处理
     * @return 柔化效果处理后的图片
     */
    public Bitmap soften() {
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 光照效果处理
     * @param centerX 光源在X轴的位置
     * @param centerY 光源在Y轴的位置
     * @return 光照效果处理后的图片
     */
    public Bitmap sunshine(int centerX, int centerY) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int radius = Math.min(centerX, centerY);

        final float strength = 150F; // 光照强度 100~150
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = pixR;
                newG = pixG;
                newB = pixB;

                // 计算当前点到光照中心的距离，平面座标系中求两点之间的距离
                int distance = (int) (
                        Math.pow((centerY - i), 2) + Math.pow(centerX - k, 2));
                if (distance < radius * radius) {
                    // 按照距离大小计算增加的光照值
                    int result = (int) (strength * (1.0 - Math.sqrt(distance) / radius));
                    newR = pixR + result;
                    newG = pixG + result;
                    newB = pixB + result;
                }

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[pos] = Color.argb(255, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 底片效果处理
     * @return 底片效果处理后的图片
     */
    public Bitmap film() {
        // RGBA的最大值
        final int MAX_VALUE = 255;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = MAX_VALUE - pixR;
                newG = MAX_VALUE - pixG;
                newB = MAX_VALUE - pixB;

                newR = Math.min(MAX_VALUE, Math.max(0, newR));
                newG = Math.min(MAX_VALUE, Math.max(0, newG));
                newB = Math.min(MAX_VALUE, Math.max(0, newB));

                pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 锐化效果处理
     * @return 锐化效果处理后的图片
     */
    public Bitmap sharpen() {
        // 拉普拉斯矩阵
        int[] laplacian = new int[] { -1, -1, -1, -1, 9, -1, -1, -1, -1 };

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int idx = 0;
        float alpha = 0.3F;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + n) * width + k + m];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * laplacian[idx] * alpha);
                        newG = newG + (int) (pixG * laplacian[idx] * alpha);
                        newB = newB + (int) (pixB * laplacian[idx] * alpha);
                        idx++;
                    }
                }

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);
                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 浮雕效果处理
     * @return 浮雕效果处理后的图片
     */
    public Bitmap emboss() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                pixColor = pixels[pos + 1];
                newR = Color.red(pixColor) - pixR + 127;
                newG = Color.green(pixColor) - pixG + 127;
                newB = Color.blue(pixColor) - pixB + 127;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[pos] = Color.argb(255, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

}
