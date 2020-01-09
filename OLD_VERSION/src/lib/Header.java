package lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class Header {
    private static final String DEFAULT_DIR = "src/";
    
    public String getURLFromHeader(String header) { 
        int from = header.indexOf(" ") + 2; 
        int to = header.indexOf(" ", from);
        String url = header.substring(from, to); 
        int paramIndex = url.indexOf("?");
            if (paramIndex != -1) { 
                url = url.substring(0, paramIndex);
            } 
        return getHTMLFile(url); 
    }
            
    public String readHeader(InputStream in) throws IOException { 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder(); 
        String ln; 
            while (true) { 
                ln = reader.readLine(); 
                    if (ln == null || ln.isEmpty()) { 
                        break; 
                    } 
                builder.append(ln).append(System.getProperty("line.separator")); 
            } 
        return builder.toString(); 
    }
    
    public String getHeader(int code) {
        StringBuilder buffer = new StringBuilder(); 
        buffer.append("HTTP/1.1 ").append(code).append(" ").append(getAnswer(code)).append("\n");
        buffer.append("Date: ").append(new Date().toGMTString()).append("\n");
        buffer.append("Accept-Ranges: none\n"); 
        buffer.append("\n");
        return buffer.toString(); 
    } 
    
    public String getAnswer(int code) {     
        switch (code) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            default:
                return "Internal Server Error";
        }
    }
    
    public String getHTMLFile(String url) {
        if (url.isEmpty())
            if(new File(DEFAULT_DIR + "index.html").isFile())
                return DEFAULT_DIR  + "index.html";
            else if(new File(DEFAULT_DIR + "index.php").isFile())    
                return DEFAULT_DIR  + "index.php";
            else
                return DEFAULT_DIR + url;
        else
            return DEFAULT_DIR + url;
    }
}