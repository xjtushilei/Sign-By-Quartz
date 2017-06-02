package com.xjtushilei.utils;

/**
 * @author shilei
 * @Date 2017/6/2.
 */
public class MailTemplate {

    public static String SignHtml = "<!DOCTYPE html>\n" +
            "<html >\n" +
            "<head>\n" +
            "    <title>签到</title>\n" +
            "    <meta charset=\"UTF-8\"/>\n" +
            "</head>\n" +
            "<body style=\"text-align: center\">\n" +
            "<h1>签到结果</h1>\n" +
            "<div> table</div>\n" +
            "</body>\n" +
            "</html>";

    public static String weekendHtml = "<!DOCTYPE html>\n" +
            "<html >\n" +
            "<head>\n" +
            "    <title>签到</title>\n" +
            "    <meta charset=\"UTF-8\"/>\n" +
            "</head>\n" +
            "<body style=\"text-align: center\">\n" +
            "<h1>本周签到汇总</h1>\n" +
            "<h3>共计 : number 次 </h3>\n" +
            "<h2>具体细节：</h2>\n" +
            "<div> jutixijie</div>\n" +
            "</body>\n" +
            "</html>";
}
