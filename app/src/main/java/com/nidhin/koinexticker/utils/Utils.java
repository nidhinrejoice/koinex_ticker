package com.nidhin.koinexticker.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nidhin.koinexticker.R;
import com.nidhin.koinexticker.homescreen.data.Coin;

import org.json.JSONException;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Utils {

    private static Toast toast;

    public static boolean isNotStringEmpty(String string) {
        return string != null ? string.length() != 0 : false;
    }

    public static boolean isNotListEmpty(List list) {
        return list != null ? (list.isEmpty() ? false : true) : false;
    }

    public static boolean isNotNullOrZero(Double value) {
        return value != null ? value > 0 : false;
    }


    public static String makeFirstLetterUpperCase(String paramString) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isNotStringEmpty(paramString)) {
            paramString = paramString.toLowerCase();
            String[] words = paramString.split(" ");
            for (String word : words) {
                stringBuilder.append(stringBuilder.length() > 0 ? " " : "");
                stringBuilder.append(word.substring(0, 1).toUpperCase());
                stringBuilder.append(word.substring(1, word.length()));
            }
        }
        return removeUnderScores(stringBuilder.toString());
    }

    public static String removeUnderScores(String paramString) {
        if ((paramString != null) && (!paramString.isEmpty())) {
            paramString = paramString.replaceAll("_", " ");
        }
        return paramString;
    }

    public static String formatStringToDisplay(String string) {
        string = removeUnderScores(string);
        string = makeFirstLetterUpperCase(string);
        return string;
    }

    public static String twoDecimalPlaces(Double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);
        return df.format(value);
    }


    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,##,##,###.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMaximumFractionDigits(2);

        return df.format(amount);
    }

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ((Activity) context).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    public static String getException(Throwable t) {
        if (t != null) {
            if (t instanceof UnknownHostException)
                return "Internet not available";
            else if (t instanceof ConnectException)
                return "Internet not available";
            else if (t instanceof SocketTimeoutException)
                return "Your request has been timed out";
            else if (t instanceof TimeoutException)
                return "Your request has been timed out";
            else if (t instanceof NullPointerException)
                return "Something went wrong";
            else if (t instanceof JSONException)
                return "Something went wrong";
            else if (t instanceof SocketException)
                return "Network not available";
            else
                return t.getMessage();
        } else
            return "Network error";
    }

    public static void makeToast(Context context, String message) {
        try {
            if (toast != null)
                toast.cancel();
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            View toastView = toast.getView();
            TextView toastMessage = toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(20);
            toastMessage.setText(message);
            toastMessage.setTextColor(context.getResources().getColor(R.color.textColorPrimaryInverse));
            toastMessage.setGravity(Gravity.CENTER);
            toastMessage.setPadding(10, 0, 10, 0);
            toastView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calculateSpread(Coin coin) {
        Double bidVal=Double.valueOf(coin.getHighestBid());
        Double askVal=Double.valueOf(coin.getLowestAsk());
        Double currentVal=Double.parseDouble(coin.getLastTradedPrice());
        Double spreadVal=(askVal-bidVal)/currentVal*100;
        return formatAmount(spreadVal);
    }
}
