package com.keafmd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Keafmd
 *
 * @ClassName: HttpUtils
 * @Description: 使用青云客api请求
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 9:13
 */
public class HttpUtils {
    public static String request(String api) throws IOException {
        int code = 0;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(api);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            code = httpURLConnection.getResponseCode();
        } catch (Exception e) {
            return "网络出现错误，请检查网络。";
        }
        if (code >= 200 && code < 300) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "出现错误，错误编码：" + code;

    }
}
