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
    String name="BGM.wav";//��������ѡ������Ҫ���ŵ�����
     File f = new File("D:\\java\\SchoolPractice\\src\\practice4\\"+name); //�������ļ��ģ�ע��һ��Ҫ��WAV��ʽ�����ֲ�Ȼ�����Բ���
     URL url; 
     URI uri;
     AudioClip clip; 
    void setMusic(String name)//�޸Ĳ��ŵ������ļ�
    {
        this.name=name;
    }
   public Play0(){     
     try
     {  
        uri=f.toURI();
        url = uri.toURL();
        clip = Applet.newAudioClip(url); 
        clip.loop();//ѭ������
        //clip.play();//����
        //clip.stop();//ֹͣ����
        System.out.println("�����ļ��Ѿ���");
    }
     catch (MalformedURLException e) { 
            e.printStackTrace(); 
            System.out.println("���Ŵ���");
        }
    }
   public void stopMusic()//ֹͣ����
   {
       clip.stop();
   }
   public void playMusic()//����
   {
	   System.out.println("��������ing~~~");
       clip.play();
       JOptionPane.showMessageDialog(null,"��װ���ֲ�����~���Ҳ������ʵķ������ţ�");
   }
   public void loopMusic()//ѭ������
   {
       clip.loop();
   }
} 

