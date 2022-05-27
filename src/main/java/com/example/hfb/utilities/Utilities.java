package com.example.hfb.utilities;

import com.example.hfb.model.dto.StatisticData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Utilities {
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public static void responseDataToken(HttpServletResponse response, String refresh_token, String access_token) throws IOException {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.OK.value());
        data.put("message", HttpStatus.OK.toString());
        data.put("data", tokens);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public static String convertLongToString(long timeInMLS) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMLS);
        return formatDate.format(calendar.getTime());
    }

    public static long convertStringToLong(String timeString) {
        try {
            return formatDate.parse(timeString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getCurrentTimeInMLS() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static long getTimeAfterDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTimeInMillis();
    }

    public static String convertLongToDate (long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);
        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        if(url.toString().endsWith("/")){
            url.append("/");
        }
        return url.toString();
    }

    public static Map<String, Integer> getBetweenDate(String s, String e){
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        Map<String, Integer> totalDates = new HashMap<String, Integer>();
        while (!start.isAfter(end)) {
            totalDates.put(start.toString(), 0);
            start = start.plusDays(1);
        }
        return totalDates;
    }

    public static List<StatisticData> getListBetweenDate(String s, String e){
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<StatisticData> data = new ArrayList<>();
        while (!start.isAfter(end)) {
            data.add(new StatisticData(start.toString(), 0.0));
            start = start.plusDays(1);
        }
        return data;
    }


    public static void main(String[] args) {
////        String date = convertLongToDate(Calendar.getInstance().getTimeInMillis() + (long)(24*60*60*1000));
////        System.out.println(Calendar.getInstance().getTimeInMillis() + (long)(24*60*60*1000));
////        System.out.println(date);
//        System.out.println(convertLongToString(1635699600000L));
        String s = "2022-04-25";
        String e = "2022-04-25";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        System.out.println(totalDates);
    }
}
