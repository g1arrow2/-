package practice4;


import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JOptionPane; 

@SuppressWarnings("deprecation")
public class Play0 { 
    String name="BGM.wav";//可以用来选择你想要播放的音乐
     File f = new File("D:\\java\\SchoolPractice\\src\\practice4\\"+name); //放音乐文件的，注意一定要是WAV格式的音乐不然不可以播放
     URL url; 
     URI uri;
     AudioClip clip; 
    void setMusic(String name)//修改播放的音乐文件
    {
        this.name=name;
    }
   public Play0(){     
     try
     {  
        uri=f.toURI();
        url = uri.toURL();
        clip = Applet.newAudioClip(url); 
        clip.loop();//循环播放
        //clip.play();//播放
        //clip.stop();//停止播放
        System.out.println("音乐文件已经打开");
    }
     catch (MalformedURLException e) { 
            e.printStackTrace(); 
            System.out.println("播放错误！");
        }
    }
   public void stopMusic()//停止播放
   {
       clip.stop();
   }
   public void playMusic()//播放
   {
	   System.out.println("播放音乐ing~~~");
       clip.play();
       JOptionPane.showMessageDialog(null,"假装音乐播放中~（找不到合适的方法播放）");
   }
   public void loopMusic()//循环播放
   {
       clip.loop();
   }
} 

