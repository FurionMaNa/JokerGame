package com.market.jokergame;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ThreadRequest extends AsyncTask<String,Void,String> {

    private String URL_API="http://192.168.0.103/JokerGame/";
    byte[] data = null;
    InputStream is = null;
    String resultString = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL_API+=strings[0];
            URL url = new URL(URL_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream os = conn.getOutputStream();
            data = URL_API.getBytes("UTF-8");
            os.write(data);
            data = null;
            conn.connect();
            int responseCode= conn.getResponseCode();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is = conn.getInputStream();
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            data = baos.toByteArray();
            resultString = new String(data, "UTF-8");
            return resultString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
