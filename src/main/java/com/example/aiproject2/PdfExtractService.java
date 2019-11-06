package com.example.aiproject2;

import org.python.core.*;
import org.springframework.stereotype.Service;
import org.python.util.PythonInterpreter;

import java.io.*;
import java.util.ArrayList;

@Service
public class PdfExtractService {
    public String ExtractPdf(String filePath) throws IOException, InterruptedException {
        String args1="python "+"C:\\Users\\74583\\Desktop\\ZUZHIJIEGOU\\ppttotxt.py "+filePath;
        System.out.println(args1);
        Process process=Runtime.getRuntime().exec(args1);
        BufferedReader in=new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
        String line;
        ArrayList<String> result=new ArrayList<>();
        while ((line=in.readLine())!=null){
             result.add(line);
        }
        in.close();
        try {
            process.waitFor();
        }
        catch (Exception e)
                {
                    System.out.println(e);
                }
        System.out.println(result);
        System.out.println(readFromTextFile(result.get(0)));
        return result.toString();


    }
    public ArrayList<String> readFromTextFile(String pathname) throws IOException{
        ArrayList<String> strArray = new ArrayList<String>();
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while(line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        return strArray;
    }
}
