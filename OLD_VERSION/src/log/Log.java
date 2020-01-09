package log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class Log implements Runnable {
    private final Date date;
    private final String name;
    private final String header;
    private static final String PATH = "log";
    
    @Override
    public void run() {
        try {
            printlnAppen();    
        } catch(Exception e) {
            System.err.println("LOG ERROR");
        } 
    }
    
    public Log(String header) throws IOException {        
        this.header = header;
        date = new Date();
        name = String.valueOf(date.getDate()) + "-" + String.valueOf(date.getMonth() + 1) + "-" + String.valueOf(date.getYear() - 100);
    }
       
    private synchronized void printlnAppen() throws IOException {      
        try (PrintStream printStream = new PrintStream(new FileOutputStream(new File(PATH + "/" + name + ".txt"), true), true)) {
            printStream.println((new Date().toLocaleString()) + "\r\n" + header + "\r\n" + "###############################" + "\r\n" );
            printStream.flush();
            printStream.close();
        }
    }
}