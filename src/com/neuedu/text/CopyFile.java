package com.neuedu.text;

import java.io.*;

/**
 * @Author SuXiaojun
 * @Date 2020/2/12 11:04
 * @Version 1.0
 */
public class CopyFile {
    public static void main(String[] args) {
        File file =new File("E:/狼人杀发牌器.txt");
        File file1 =new File("E:/a.txt");
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            inputStream =new FileInputStream(file);
            outputStream =new FileOutputStream(file1,true);
            byte [] a =new byte[100];
            try {
                int b = inputStream.read(a);
                while(b!=-1){
                    outputStream.write(a);
                    outputStream.flush();
                    b=inputStream.read(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                    if(outputStream!=null)
                        outputStream.close();
                    if(inputStream!=null)
                        inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
