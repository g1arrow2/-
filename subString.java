package practice1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class subString {
    /**
     * @param args
     */
    public static void main(String[] args) {
    	  FileInputStream fInStream = null;
  		try {
  			fInStream = new FileInputStream("Jj.txt");
  		} catch (FileNotFoundException e) {
  			
  			e.printStackTrace();
  		}
    	InputStreamReader inStreamReader = null;
		try {
			inStreamReader = new InputStreamReader(fInStream, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        try {
			BufferedReader br = new BufferedReader(inStreamReader);
            BufferedWriter wr = new BufferedWriter(new FileWriter("word3.txt"));
            String line = null;
            String str="";
            int i=1;
            while((line = br.readLine()) != null)
            {
                str += line;
                if(str.contains("正确答案") && str.contains("我的答案")){
                	System.out.println("---------"+i);
                    int preIndex = str.indexOf("正确答案");
                    int endIndex = str.indexOf("我的答案");
                    String strObject = str.substring(preIndex+5, endIndex).trim();
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(strObject);
                    String dest = m.replaceAll("");
                    wr.write("第"+i+"题的答案:"+dest);
//                    +System.getProperty("line.separator")
//                  wr.write("\n\r");
                    wr.write(System.getProperty("line.separator"));
                    i++;
                    str = null;
                    strObject = null;
                }
            }
            wr.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found file 1.txt");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("read error");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}