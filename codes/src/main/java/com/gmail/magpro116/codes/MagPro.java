package com.gmail.magpro116.codes;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class MagPro {

    public static int previousPosition = 0;
    public static String str;
    public static List<SData> sData =new ArrayList<>();
    public static String[] names = new String[]{
            "أسوان", "أسيوط", "الإسكندرية", "الإسماعيلية", "الأقصر", "البحر الأحمر", "البحيرة", "الجيزة",
            "الدقهلية", "السويس", "الشرقية", "الغربية", "الفيوم", "القاهرة", "القليوبية", "المنوفية",
            "المنيا", "الوادي الجديد", "بني سويف", "بور سعيد", "جنوب سيناء", "دمياط", "سوهاج", "شمال سيناء",
            "قنا", "كفر الشيخ", "مرسى مطروح"
    };

    public static Integer[] flags = new Integer[]{
            R.drawable.aswan, R.drawable.asyout, R.drawable.alex, R.drawable.ismaelia,
            R.drawable.luxor, R.drawable.red_sea, R.drawable.buhira, R.drawable.jeza,
            R.drawable.dakhlya, R.drawable.swiz, R.drawable.sharqya, R.drawable.gharbya,
            R.drawable.fayoum, R.drawable.cairo, R.drawable.qalyoubia, R.drawable.mounfya,
            R.drawable.almenya, R.drawable.wadi, R.drawable.bany_sweef, R.drawable.bour_saeed,
            R.drawable.south_sinai, R.drawable.damayta, R.drawable.souhaj, R.drawable.north_sinai,
            R.drawable.qna, R.drawable.kfs, R.drawable.matrouh
    };


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static Bitmap convertImageViewToBitmap(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        return bitmap;
    }

    public static String convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap convertStringToBitmap(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.DEFAULT
        );
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:MM:ss", Locale.US).format(new Date());
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static String getTime() {
        return new SimpleDateFormat("hh:MM:ss", Locale.US).format(new Date());
    }

    public static String hijriDate() {
        Calendar cl = Calendar.getInstance();
        String formatted = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate dt = LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE));
            HijrahDate hijrahDate = HijrahDate.from(dt);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatted = formatter.format(hijrahDate);
        }
        return formatted;
    }

    public static int day() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int month() {
        return Calendar.getInstance().get(Calendar.MONTH) +1;
    }

    public static int year() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public static void expandView(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
        if (v.isShown()) {
            collapseView(v);
        } else {
            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1 ? LinearLayout.LayoutParams.WRAP_CONTENT
                            : (int) (targtetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            a.setDuration((targtetHeight + 250));
            v.startAnimation(a);
        }

    }

    public static void collapseView(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight
                            - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((v.getLayoutParams().height + 250));
        v.startAnimation(a);
    }

    public static void whatsappDirectory(Context context, String extras, String countryCode, String phone) {
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, extras + "\n");
            sendIntent.putExtra("jid", countryCode + phone + "@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            context.startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(context, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeCall(String num, Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + num));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(intent);
    }

    public static void makeDir(String dir) {
        File root = Environment.getExternalStorageDirectory();
        File directory = new File(root.getAbsolutePath() + "/" + dir + "/");
        try {
            if (directory.exists() == false) {
                directory.mkdirs();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makeHiddenDir(String dirName) {
        File root = Environment.getExternalStorageDirectory();
        File directory = new File(root.getAbsolutePath() + "/." + dirName);
        try {
            if (directory.exists() == false) {
                directory.mkdirs();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCalenderValue(Context context, final TextView textView) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String dfm = "" + dayOfMonth;
                        int mfy = monthOfYear + 1;
                        String res;

                        if (dayOfMonth < 10) {
                            dfm = "0" + dayOfMonth;
                        }

                        if (mfy < 10) {
                            res = "0" + mfy;
                            textView.setText(year + "-" + res + "-" + dfm);
                        } else if (mfy > 9) {
                            res = "" + mfy;
                            textView.setText(year + "-" + res + "-" + dfm);
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public static void shareApp(Activity activity, String sharingText, String PackageName) {
        String app = "http://play.google.com/store/apps/details?id=" + PackageName;
        ShareCompat.IntentBuilder.from(activity)
                .setType("text/plain")
                .setChooserTitle("Chooser title")
                .setText(sharingText + app)
                .startChooser();
    }

    public static void closeApp(Activity context) {
        context.moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static void showHidePassword(EditText editText) {
        if (editText.getTransformationMethod() == null) {
            editText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            editText.setTransformationMethod(null);
        }
    }

    public static void readMoreTextView(int maxLength, String val, TextView textView) {
        if (val.length() > maxLength) {
            val = val.substring(0, maxLength) + "...";
            textView.setText(Html.fromHtml(val + "<font color='green'> <u>Read More ..</u></font>"));
        } else {
            textView.setText(val);
        }
    }

    public static void append2Strings(TextView textView, String longString, String shortString, int largeFontSize, int smallFontSize, String hexLargeColor, String hexSmallColor) {

        SpannableString string1 = new SpannableString(longString);
        string1.setSpan(new ForegroundColorSpan(Color.parseColor(hexLargeColor)), 0, string1.length(), 0);
        string1.setSpan(new AbsoluteSizeSpan(largeFontSize), 0, shortString.length(), SPAN_INCLUSIVE_INCLUSIVE);

        SpannableString str2 = new SpannableString(shortString);
        str2.setSpan(new ForegroundColorSpan(Color.parseColor(hexSmallColor)), 0, str2.length(), 0);
        str2.setSpan(new AbsoluteSizeSpan(smallFontSize), 0, shortString.length(), SPAN_INCLUSIVE_INCLUSIVE);

        CharSequence finalText = TextUtils.concat(string1, "  ", str2);
        textView.setText(finalText);
    }

    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void showMessageDialog(Context context, String title, String message, String btnText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton(btnText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean internetIsConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static String decimalFormatSymbols(double yourDouble) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        String format = new DecimalFormat("##.##", symbols).format(yourDouble);
        return format;
    }

    public static void playSound(Context context, int raw_music_file) {
        try {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, raw_music_file);
            mediaPlayer.start();
        } catch (Exception e) {
        }
    }

    public static void batteryAreaColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           activity.getWindow().setStatusBarColor(activity.getResources().getColor(color, activity.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    public static void marquee(TextView textView, String string) {
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setFocusableInTouchMode(true);
        textView.setFreezesText(true);
        textView.setSingleLine(true);
        textView.setMarqueeRepeatLimit(-1);
        textView.setFocusable(true);
        textView.setSelected(true);
        textView.setText(string);
    }

    public static int reIntegerFromDataBase(Cursor cursor, String field_name){
        int id=0;
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex(field_name));
            }
        }
        return id;
    }

    public static String reStringFromDataBase(Cursor cursor, String field_name){
        String val = null;
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                val = cursor.getString(cursor.getColumnIndex(field_name));
            }
        }
        return val;
    }

    public static void restartApp(Activity context, Class startActivity) {
        context.startActivity(new Intent(context, startActivity.getClass()));
        context.finish();
    }

    public static void egyptianCitiesWithLogo(Context context, Spinner spinner){
        CityAdapter adapter = new CityAdapter(context,flags,names);
        spinner.setAdapter(adapter);
    }

    public static void fillSpinnerFromDataList(Context context, List<String> list, Spinner spinner){

        for (int i = 0; i <list.size() ; i++) {
            sData.add(new SData(list.get(i)));
        }
        MAdapter adapter = new MAdapter(context, sData);
        spinner.setAdapter(adapter);
    }



    public static void recyclerViewAnimation(int itemPosition, RecyclerView.ViewHolder holder) {
        if (itemPosition > previousPosition) {
            zx(holder, true);
        } else {
            zx(holder, false);
        }
        previousPosition = itemPosition;
    }

    public static void zx(RecyclerView.ViewHolder holder, boolean goesDown) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 200 : -200, 0);
        animatorTranslateY.setDuration(1000);

        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
        animatorTranslateX.setDuration(1000);


        //animatorSet.playTogether(animatorTranslateX,animatorTranslateY);
        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();
    }



    public static String sendGetRequest(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String result;

            StringBuilder sb = new StringBuilder();

            while ((result = bufferedReader.readLine()) != null) {
                sb.append(result);
            }

            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(2 * 60 * 1000);
            conn.setConnectTimeout(35 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = br.readLine();
            } else {
                response = "Timeout";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }



    public static String reRelation(Context context, int relation) {
        if (relation == 1) {
            return context.getString(R.string.single);
        } else if (relation == 2) {
            return context.getString(R.string.divorced);
        } else{
            return context.getString(R.string.widow);
        }
    }

    public static String reGender(Context context, int gender) {
        if (gender == 1) {
            return context.getString(R.string.male);
        } else{
            return context.getString(R.string.female);
        }
    }

}

