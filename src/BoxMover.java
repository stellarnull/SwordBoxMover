import java.awt.Color;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import sun.audio.*;

public class BoxMover {
	/*ɥ�Ĳ����ȫ�־�̬����֮������������*/
	static int LEVELS = 9;
	static int level = 1;
	static int step;
	static int total;
	static int justLoad = 1;
	static String name = "lfs";
	static int[] retreatTimes = new int[10]; 
	static long[] time = new long[10];
	static int[] score = new int[10];
	static Map map[] = new Map[10000];
	static boolean unlock[] = new boolean[10];
	static boolean win;
	static boolean isGaming;
	static boolean start;
	static boolean newlevel;
	static long startTime;
	static long pauseTime1;
	static long pauseTime2;
	static long levelTime;
	static long loadTime;
	
	/*ɥ�Ĳ����ȫ�־�̬����֮GUI���*/
	static JFrame jf = new JFrame("������");
	static JPanel jpCover = new JPanel();
	static JPanel jpGame = new JPanel();
	static JPanel jpSelect = new JPanel();
	static JPanel jpWin = new JPanel();
	static JPanel jpHelp = new JPanel();
	static JPanel jpAuthor = new JPanel();
	static JPanel jpRank = new JPanel();
	static JMenuBar jmbGame = new JMenuBar();
	static ImageIcon[] elementIcon = new ImageIcon[7];
	static ImageIcon[] backgroundIcon = new ImageIcon[10];
	static JLabel jlblTime = new JLabel();
	static JLabel jlblShowTime = new JLabel();
	static JLabel[] jlbWin = new JLabel[7];
	static JLabel[] jlblRank = new JLabel[7];
	static JLabel[] jbgGame = new JLabel[10];
	static JLabel[][] jlbGame = new JLabel[16][16];
	static KeyListen keyListen = new KeyListen();
	
	/*ɥ�Ĳ����ȫ�־�̬����֮����*/
	static Font mw = null;
	static File mwFont = new File(".\\Font\\FZMWFont.ttf");
	
	/*ɥ�Ĳ����ȫ�־�̬����֮��ʱ��*/
	static Timer timer = new Timer();
	static TimerTask timerTask = new TimerTask(){
		/*��ʱ�������Ѿ����ǵ�������ô���д��뵽���Ǹ�ʲô���ˡ�����*/
		int hour, minute, second;
		public void run() 
		{
			if (isGaming) 
			{
				if(!newlevel)levelTime = timerTask.scheduledExecutionTime() - pauseTime2 + loadTime;
				else
				{
					levelTime = timerTask.scheduledExecutionTime();
					pauseTime2 = 0;
					newlevel = false;
				}
				time[level] = levelTime - startTime;
				second = (int) (time[level] / 1000) % 60;
				minute = (int) (time[level] / 60000) % 60;
				hour = (int) (time[level] / 3600000);
				jlblShowTime = new JLabel(standard(hour, minute, second));
				jlblShowTime.setBounds(640, 43, 400, 100);
				jlblShowTime.setFont(mw.deriveFont(Font.BOLD, 60));
				jlblShowTime.setForeground(new Color(114, 112, 179));
				jpGame.add(jlblShowTime);
				Methods.refreshMap();
				System.out.printf("%02d:%02d:%02d\n", hour, minute, second);
			}
		}
		public String standard(int hour, int minute, int second){
			/*����ʱ��������ʽ*/
			String s = "";
			s += hour + ":";
			if(minute >= 10)s += minute + ":";
			else s += "0" + minute + ":";
			if(second >= 10)s += second + "";
			else s += "0" + second;
			return s;
		}
	};
	
	public static void setFont(){
		try {
			FileInputStream fi = new FileInputStream(mwFont);
			BufferedInputStream fb = new BufferedInputStream(fi);
			mw = Font.createFont(Font.TRUETYPE_FONT, fb);
			mw = mw.deriveFont(Font.BOLD, 40);
			}
			catch (Exception e) {
				
			}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		
		/*��������*/
		try
		{          
			AudioStream as = new AudioStream(new FileInputStream(new File(".\\music\\fmt.wav").getAbsolutePath()));         
			AudioData data = as.getData();           
			ContinuousAudioDataStream cas = new ContinuousAudioDataStream(data);         
			AudioPlayer.player.start(cas); 
		}
		catch(Exception e)
		{            
			e.printStackTrace();              
		}     
			
		//AudioPlayer.player.start(new AudioStream(new FileInputStream(new File(".\\music\\qingpingle.wav").getAbsolutePath())));
		
			
			
		/*��������*/
		setFont();
		
		
		for(int i = 0; i < 10000; i++)map[i] = new Map();
		for(int i = 0; i < 7; i++) elementIcon[i] = new ImageIcon(".\\pic\\" + i + ".png");
		GUI.setFrame();
		GUI.setWelcomeWindow();
	}

}

