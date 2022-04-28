package com.example.fitnessfirst.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{6,}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(CharSequence passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void loadImage(Context context, String userId, ImageView imageView) {
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference()
                .child("user_profile_pictures/" + userId + ".png");

        mStorageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(context)
                    .load(uri.toString())
                    .into(imageView);
        });
    }

    public static void uploadImage(String id, Uri imageUri) {
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference()
                .child("user_profile_pictures/" + id + ".png");

        mStorageRef.putFile(imageUri);
    }

    public static void getDate(Activity context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String startDateStr = sdf.format(myCalendar.getTime());
            editText.setText(startDateStr);

        };
        new DatePickerDialog(Objects.requireNonNull(context), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void getTime(Context context, EditText editText) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, (timePicker, hourOfDay, minute1) -> {
            boolean isPM = (hourOfDay >= 12);
            String time = " " + String.format("%02d:%02d %s",
                    (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute1, isPM ? "PM" : "AM");
            editText.setText(time);
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public static String formulateDate(String date) {
        StringBuilder dateBuilder = new StringBuilder();

        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6));
        Calendar calendar = new GregorianCalendar(year, (month), (day - 1));

        dateBuilder.append(day + " ");

        switch (month) {
            case 1:
                dateBuilder.append("JANUARY ");
                break;
            case 2:
                dateBuilder.append("FEBRUARY ");
                break;
            case 3:
                dateBuilder.append("MARCH ");
                break;
            case 4:
                dateBuilder.append("APRIL ");
                break;
            case 5:
                dateBuilder.append("MAY ");
                break;
            case 6:
                dateBuilder.append("JUNE ");
                break;
            case 7:
                dateBuilder.append("JULY ");
                break;
            case 8:
                dateBuilder.append("AUGUST ");
                break;
            case 9:
                dateBuilder.append("SEPTEMBER ");
                break;
            case 10:
                dateBuilder.append("OCTOBER ");
                break;
            case 11:
                dateBuilder.append("NOVEMBER ");
                break;
            case 12:
                dateBuilder.append("DECEMBER ");
                break;
        }

        dateBuilder.append(year + ", ");

        int daySwitch = calendar.get(Calendar.DAY_OF_WEEK);

        switch (daySwitch) {
            case Calendar.MONDAY:
                dateBuilder.append("MONDAY");
                break;
            case Calendar.TUESDAY:
                dateBuilder.append("TUESDAY");
                break;
            case Calendar.WEDNESDAY:
                dateBuilder.append("WEDNESDAY");
                break;
            case Calendar.THURSDAY:
                dateBuilder.append("THURSDAY");
                break;
            case Calendar.FRIDAY:
                dateBuilder.append("FRIDAY");
                break;
            case Calendar.SATURDAY:
                dateBuilder.append("SATURDAY");
                break;
            case Calendar.SUNDAY:
                dateBuilder.append("SUNDAY");
                break;

        }
        return dateBuilder.toString();
    }

    public static void saveDataInSharedPrefs(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getDataFromSharedPrefs(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }

    public static void clearSharedPrefs(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("email", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("password", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("type", "").apply();
    }
}
