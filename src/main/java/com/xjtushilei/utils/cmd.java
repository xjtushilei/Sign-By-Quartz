package com.xjtushilei.utils;
/**
 * @author shilei
 * @date 2016年11月10日
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class cmd {

    public static void main(String[] args) {

        exCMD("python D://relation_class.py \"测试课程\" \"mooc\" \"shilei\" \"shi\" \"localhost\" 9220 ");
    }


    public static int exCMD(String cmd) {
        int exitValue = -1;
        InputStream ins = null;
        String[] command = new String[]{"cmd.exe", "/c", cmd}; // 命令
        try {
            Process process = Runtime.getRuntime().exec(command);
            ins = process.getInputStream(); // 获取执行cmd命令后的信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 输出
            }
            exitValue = process.waitFor();
            //			System.out.println("返回值：" + exitValue);
            process.getOutputStream().close(); // 不要忘记了一定要关
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exitValue;
    }
}
