package com.example.myapplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StickyNote {

    String getStick(Context context){
        File file= new File(context.getFilesDir().getPath()+"/ujj.txt");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;

            while ((line=br.readLine())!=null){
                text.append(line);
                text.append("\n");
            }
            br.close();

        }
        catch (IOException e){
                e.printStackTrace();
        }
        return text.toString();
    }

    void setStick(String texttobeSaved, Context context){
        String text=texttobeSaved;
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream= context.getApplicationContext().openFileOutput("ujj.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
