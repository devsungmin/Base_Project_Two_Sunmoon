 package Manager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import Client.Check;
import Client.LoginN;
import Client.LoginY;
import Client.Notcheck;
import javafx.scene.control.CheckBox;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
public class Client implements Runnable {
	ClassSix cs = new ClassSix();
	ClassCho ch = new ClassCho();
	ClassTen ct = new ClassTen();
	ClassNine cn = new ClassNine();
	ClassFTSix cfs = new ClassFTSix();
	String[] view;
	String temp;
	String[] loginmac;
	String mac;
	BufferedReader in;
	 PrintWriter out;
	 Socket sock;
	 String macAddress;
	 boolean loginY=false;
	 
		 public Client(){
		  try {
		   sock = new Socket("127.0.0.1",7777); //소켓 ip
		   in =new BufferedReader( 
		    new InputStreamReader(
		    sock.getInputStream()));
		   out=new PrintWriter(
		    sock.getOutputStream(),true);
		   System.out.println("관리자 접속성공");
		   macAddress=mac();
		   out.println("RID:"+macAddress);
		   ch.setVisible(true);
		  } catch (Exception e) {
		   System.err.println("Error in Connect");
		   e.printStackTrace();
		   System.exit(1);
		  }
		  new Thread(this).start();
		 }

	
	@Override
	public void run() {
		 try {
			   boolean done = false; //사용자 구동을 초기 false로 초기값 설정
			   while (!done) {
			    String line = in.readLine();
			    if (line == null)
			     done = true;
			    else
			     routine(line);
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
	}

	public void routine(String line) {
		System.out.println("line :"+line);
		if(line.charAt(0)=='?') {
			
			int i=line.indexOf('?');
			mac=line.substring(i+1);
			System.out.println("mac:"+mac);
			view=mac.split(";");
			if(!mac.equals("")) {
				
				for(int n=0; n<cs.jb.length; n++) {
					cs.jb[n].setText("꺼짐");
				}
				for(int n=0; n<cn.jb.length; n++) {
					cn.jb[n].setText("꺼짐");
				}
				for(int n=0; n<ct.jb.length; n++) {
					ct.jb[n].setText("꺼짐");
				}
				for(int n=0; n<ct.jb.length; n++) {
					cfs.jb[n].setText("꺼짐");
				}
				if(!view[0].equals("123")) {
				for(int j=0; j<view.length; j++) {
				int io=view[j].indexOf(':');
				
				if(view[j].substring(0,io).equals("406")) {
					cs.jb[Integer.parseInt(view[j].substring(io+1))-1].setText("켜짐");
				}
				else if(view[j].substring(0,io).equals("409")){		
					cn.jb[Integer.parseInt(view[j].substring(io+1))-1].setText("켜짐");
					
				}
				else if(view[j].substring(0,io).equals("410")){
					ct.jb[Integer.parseInt(view[j].substring(io+1))-1].setText("켜짐");
					
				}
				else if(view[j].substring(0,io).equals("436")){
					cfs.jb[Integer.parseInt(view[j].substring(io+1))-1].setText("켜짐");
				}
			}
			}
		}

	}
		else if(line.charAt(0)=='!') {
			System.out.println(line);
			int ix=line.indexOf('!');
			temp=line.substring(ix+1);
			loginmac=temp.split(";");
			if(!loginmac.equals("")) {
				if(!loginmac[0].equals("123")) {
				for(int nm=0; nm<loginmac.length; nm++) {
					int iox=loginmac[nm].indexOf(':');
				if(loginmac[nm].substring(0,iox).equals("406")) {
					cs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].setText(cs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].getText()+"로그인");
				}
				else if(loginmac[nm].substring(0,iox).equals("409")){		
					cn.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].setText(cs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].getText()+"로그인");
				}
				else if(loginmac[nm].substring(0,iox).equals("410")){
					ct.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].setText(cs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].getText()+"로그인");
				}
				else if(loginmac[nm].substring(0,iox).equals("436")){
					cfs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].setText(cs.jb[Integer.parseInt(loginmac[nm].substring(iox+1))-1].getText()+"로그인");
				}
				}
				}
			}
		}
	}

	public class ClassCho extends JFrame {
	private JPanel contentPane;
	
	public ClassCho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("강의실 선택");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(210, 12, 145, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("406호");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 24));
		btnNewButton.setBounds(14, 80, 251, 128);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cs.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("409호");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 24));
		btnNewButton_1.setBounds(322, 80, 251, 128);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cn.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("410호");
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 24));
		btnNewButton_2.setBounds(14, 238, 251, 142);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ct.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("436호");
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 24));
		btnNewButton_3.setBounds(322, 238, 251, 142);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cfs.setVisible(true);
			}
		});
	}
	}
	public String mac() {
		InetAddress ip;
		String mic = null;
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            System.out.print("Current MAC address : ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : ""));
            }
            System.out.println(sb.toString());
            mic=sb.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mic;
	}
	
//406호	
	public class ClassSix extends JFrame {
		JLabel[] jb; 
		final String[] x={"14","166","330","483"}; //컴퓨터 박스 x축 값
		final String[] x2= {"63","222","377","533"}; //체크박스 x축 값
		private JPanel contentPane;
		JTabbedPane[] tabbedPane;
		boolean chb[] = new boolean[4]; //체크박스 불 배열
		int chbcnt[] = new int[4]; //체크박스 카운트 배열
		JCheckBox[] cb;
		public ClassSix() {
			setTitle("406호");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 678, 525);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null); 
			jb= new JLabel[4];
			tabbedPane = new JTabbedPane[4];  // 페널 배열
			cb = new JCheckBox[4]; //체크박스 배열
			int rows,cols;//row가로  cols는 세로
			int index = 0;
			for (cols=0; cols<1; cols++) {  //배열을 이용하여 체크박스와 컴퓨터 박스 생성 
				for (rows = 0; rows <4; rows++) {					
					tabbedPane[index] = new JTabbedPane();
					jb[index]= new JLabel();
					contentPane.add(tabbedPane[index]);
					tabbedPane[index].setBounds(Integer.parseInt(x[index]), 127, 139, 182);
					tabbedPane[index].add(jb[index]);
					
					cb[index] = new JCheckBox(String.valueOf(index+1));
					contentPane.add(cb[index]);
					cb[index].setBounds(Integer.parseInt(x2[index]), 317,37, 27);

					index++;		
				}
			}
			cb[0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[0]++;
					if(chbcnt[0]%2==1) {
						chb[0]=true;
						cb[0].setSelected(true);
					}
					else {
						chb[0]=false;
						cb[0].setSelected(false);
					}
				}
			});
			cb[1].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[1]++;
					if(chbcnt[1]%2==1) {
						chb[1]=true;
						cb[1].setSelected(true);
					}
					else {
						chb[1]=false;
						cb[1].setSelected(false);
					}
				}
			});
				cb[2].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[2]++;
					if(chbcnt[2]%2==1) {
						chb[2]=true;
						cb[2].setSelected(true);
					}
					else {
						chb[2]=false;
						cb[2].setSelected(false);
					}
				}
			});
				cb[3].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[3]++;
					if(chbcnt[3]%2==1) {
						chb[3]=true;
						cb[3].setSelected(true);
					}
					else {
						chb[3]=false;
						cb[3].setSelected(false);
					}
				}
			});
			JLabel lblNewLabel = new JLabel("406호");   //406호 표시하기 
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 22));
			lblNewLabel.setBounds(292, 12, 69, 46);    
			contentPane.add(lblNewLabel);
			JButton btnNewButton = new JButton("전체 선택");
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int n=0; n<4; n++) {   //chbcnt가 2로 나누어서  chbcnt=1일시 전체 true
						chb[n]=true;
						cb[n].setSelected(true);
						chbcnt[n]=1;
					}
				}
			});
			btnNewButton.setBounds(29, 420, 172, 27);
			contentPane.add(btnNewButton);
			JButton btnNewButton_1 = new JButton("전체 선택 해제");
			btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 17));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int n=0; n<4; n++) {   //chbcnt가 2로 나누어서  chbcnt=1일시 전체 true
						chb[n]=false;
						cb[n].setSelected(false);
						chbcnt[n]=1;
					}
				}
			});
			btnNewButton_1.setBounds(239, 420, 160, 27);
			contentPane.add(btnNewButton_1);
			JButton btnNewButton_2 = new JButton("종료하기");
			btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
			btnNewButton_2.setBounds(448, 420, 172, 27);
			contentPane.add(btnNewButton_2);
			btnNewButton_2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(int n=0; n<4; n++) {
						if(chb[n]==true) {
							System.out.println((n+1)+"번 종료해");
							//n번 컴퓨터 종료요청
							out.println(macAddress+":406"+(n+1));
							System.out.println(macAddress+":406"+(n+1));
						}
					}
				}
			});
	
	}
	}
	
//	410호
	public class ClassTen extends JFrame {
		JLabel[] jb;
		final String[] x={"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",};//컴퓨터 박스의 x축 값
		final String[] y= {"37","37","37","37","37","37","37","37","135","135","135","135","135","135","135","135","227","227","227","227","227","227","227","227","328",
				"328","328","328","328","328","328","328","424","424","424","424","424","424","424","424","521","521","521","521","521","521","521","521","615","615","615","615","615",
				"615","615","615"}; //컴퓨터 박스의 y축 값
		final String[] x2= {"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",}; //체크박스의 x축 값
		final String[] y2= {"87","87","87","87","87","87","87","87","185","185","185","185","185","185","185","185","277","277","277","277","277","277","277","277","378","378","378","378","378",
				"378","378","378","474","474","474","474","474","474","474","474","571","571","571","571","571","571","571","571","665","665","665","665","665","665","665","665"};
				//체크박스의 y축 값
		boolean chb[] = new boolean[56]; //체크박스 불 배열
		int chbcnt[] = new int[56]; //체크박스 카운트 배열
		private JPanel contentPane;

		public ClassTen() {
			setTitle("410호");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1050, 780);
			jb= new JLabel[56];
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			JTabbedPane[] tabbedPane = new JTabbedPane[56]; //패널의 배열
			JCheckBox[] cb = new JCheckBox[56]; //체크박스의 배열 
			int rows,cols;//row가로  ,cols 세로
			int index = 0; //값저장하는 변수
			for (cols=0; cols<7; cols++) {  //8행 7열 배열
				for (rows = 0; rows <8; rows++) {
					jb[index]= new JLabel();
					tabbedPane[index] = new JTabbedPane();
					tabbedPane[index].add(jb[index]);
					contentPane.add(tabbedPane[index]);
					tabbedPane[index].setBounds(Integer.parseInt(x[index]), Integer.parseInt(y[index]), 40, 42);
					//컴퓨터 박스
					cb[index] = new JCheckBox(String.valueOf(index+1));
					contentPane.add(cb[index]);
					cb[index].setBounds(Integer.parseInt(x2[index]), Integer.parseInt(y2[index]),50, 27);
					//체크박스
					index++; //값 저장하는곳 증가 
				}
			}
			cb[0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[0]++;
					if(chbcnt[0]%2==1) {
						chb[0]=true;
						cb[0].setSelected(true);
					}
					else {
						chb[0]=false;
						cb[0].setSelected(false);
					}
				}
			});
			cb[1].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[1]++;
					if(chbcnt[1]%2==1) {
						chb[1]=true;
						cb[1].setSelected(true);
					}
					else {
						chb[1]=false;
						cb[1].setSelected(false);
					}
				}
			});
			cb[2].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[2]++;
					if(chbcnt[2]%2==1) {
						chb[2]=true;
						cb[2].setSelected(true);
					}
					else {
						chb[2]=false;
						cb[2].setSelected(false);
					}
				}
			});
			cb[3].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[3]++;
					if(chbcnt[3]%2==1) {
						chb[3]=true;
						cb[3].setSelected(true);
					}
					else {
						chb[3]=false;
						cb[3].setSelected(false);
					}
				}
			});
			cb[4].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[4]++;
					if(chbcnt[4]%2==1) {
						chb[4]=true;
						cb[4].setSelected(true);
					}
					else {
						chb[4]=false;
						cb[4].setSelected(false);
					}
				}
			});
			cb[5].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[5]++;
					if(chbcnt[5]%2==1) {
						chb[5]=true;
						cb[5].setSelected(true);
					}
					else {
						chb[5]=false;
						cb[5].setSelected(false);
					}
				}
			});
			cb[6].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[6]++;
					if(chbcnt[6]%2==1) {
						chb[6]=true;
						cb[6].setSelected(true);
					}
					else {
						chb[6]=false;
						cb[6].setSelected(false);
					}
				}
			});
			cb[7].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[7]++;
					if(chbcnt[7]%2==1) {
						chb[7]=true;
						cb[7].setSelected(true);
					}
					else {
						chb[7]=false;
						cb[7].setSelected(false);
					}
				}
			});
			cb[8].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[8]++;
					if(chbcnt[8]%2==1) {
						chb[8]=true;
						cb[8].setSelected(true);
					}
					else {
						chb[8]=false;
						cb[8].setSelected(false);
					}
				}
			});
			cb[9].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[9]++;
					if(chbcnt[9]%2==1) {
						chb[9]=true;
						cb[9].setSelected(true);
					}
					else {
						chb[9]=false;
						cb[9].setSelected(false);
					}
				}
			});
			cb[10].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[10]++;
					if(chbcnt[10]%2==1) {
						chb[10]=true;
						cb[10].setSelected(true);
					}
					else {
						chb[10]=false;
						cb[10].setSelected(false);
					}
				}
			});
			cb[11].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					chbcnt[11]++;
					if(chbcnt[11]%2==1) {
						chb[11]=true;
						cb[11].setSelected(true);
					}
					else {
						chb[11]=false;
						cb[11].setSelected(false);
					}
				}
			});
			cb[12].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[12]++;
					if(chbcnt[12]%2==1) {
						chb[12]=true;
						cb[12].setSelected(true);
					}
					else {
						chb[12]=false;
						cb[12].setSelected(false);
					}
				}
			});
			cb[13].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[13]++;
					if(chbcnt[13]%2==1) {
						chb[13]=true;
						cb[13].setSelected(true);
					}
					else {
						chb[13]=false;
						cb[13].setSelected(false);
					}
				}
			});
			cb[14].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[14]++;
					if(chbcnt[14]%2==1) {
						chb[14]=true;
						cb[14].setSelected(true);
					}
					else {
						chb[14]=false;
						cb[14].setSelected(false);
					}
				}
			});
			cb[15].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[15]++;
					if(chbcnt[15]%2==1) {
						chb[15]=true;
						cb[15].setSelected(true);
					}
					else {
						chb[15]=false;
						cb[15].setSelected(false);
					}
				}
			});
			cb[16].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[16]++;
					if(chbcnt[16]%2==1) {
						chb[16]=true;
						cb[16].setSelected(true);
					}
					else {
						chb[16]=false;
						cb[16].setSelected(false);
					}
				}
			});
			cb[17].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[17]++;
					if(chbcnt[17]%2==1) {
						chb[17]=true;
						cb[17].setSelected(true);
					}
					else {
						chb[17]=false;
						cb[17].setSelected(false);
					}
				}
			});
			cb[18].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[18]++;
					if(chbcnt[18]%2==1) {
						chb[18]=true;
						cb[18].setSelected(true);
					}
					else {
						chb[18]=false;
						cb[18].setSelected(false);
					}
				}
			});
			cb[19].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[19]++;
					if(chbcnt[19]%2==1) {
						chb[19]=true;
						cb[19].setSelected(true);
					}
					else {
						chb[19]=false;
						cb[19].setSelected(false);
					}
				}
			});
			cb[20].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[20]++;
					if(chbcnt[20]%2==1) {
						chb[20]=true;
						cb[20].setSelected(true);
					}
					else {
						chb[20]=false;
						cb[20].setSelected(false);
					}
				}
			});
			cb[21].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[21]++;
					if(chbcnt[21]%2==1) {
						chb[21]=true;
						cb[21].setSelected(true);
					}
					else {
						chb[21]=false;
						cb[21].setSelected(false);
					}
				}
			});
			cb[22].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[22]++;
					if(chbcnt[22]%2==1) {
						chb[22]=true;
						cb[22].setSelected(true);
					}
					else {
						chb[22]=false;
						cb[22].setSelected(false);
					}
				}
			});
			cb[23].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[23]++;
					if(chbcnt[23]%2==1) {
						chb[23]=true;
						cb[23].setSelected(true);
					}
					else {
						chb[23]=false;
						cb[23].setSelected(false);
					}
				}
			});
			cb[24].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[24]++;
					if(chbcnt[24]%2==1) {
						chb[24]=true;
						cb[24].setSelected(true);
					}
					else {
						chb[24]=false;
						cb[24].setSelected(false);
					}
				}
			});
			cb[25].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[25]++;
					if(chbcnt[25]%2==1) {
						chb[25]=true;
						cb[25].setSelected(true);
					}
					else {
						chb[25]=false;
						cb[25].setSelected(false);
					}
				}
			});
			cb[26].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[26]++;
					if(chbcnt[26]%2==1) {
						chb[26]=true;
						cb[26].setSelected(true);
					}
					else {
						chb[26]=false;
						cb[26].setSelected(false);
					}
				}
			});
			cb[27].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[27]++;
					if(chbcnt[27]%2==1) {
						chb[27]=true;
						cb[27].setSelected(true);
					}
					else {
						chb[27]=false;
						cb[27].setSelected(false);
					}
				}
			});
			cb[28].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[28]++;
					if(chbcnt[28]%2==1) {
						chb[28]=true;
						cb[28].setSelected(true);
					}
					else {
						chb[28]=false;
						cb[28].setSelected(false);
					}
				}
			});
			cb[29].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[29]++;
					if(chbcnt[29]%2==1) {
						chb[29]=true;
						cb[29].setSelected(true);
					}
					else {
						chb[29]=false;
						cb[29].setSelected(false);
					}
				}
			});
			cb[30].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[30]++;
					if(chbcnt[30]%2==1) {
						chb[30]=true;
						cb[30].setSelected(true);
					}
					else {
						chb[30]=false;
						cb[30].setSelected(false);
					}
				}
			});
			cb[31].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[31]++;
					if(chbcnt[31]%2==1) {
						chb[31]=true;
						cb[31].setSelected(true);
					}
					else {
						chb[31]=false;
						cb[31].setSelected(false);
					}
				}
			});
			cb[32].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[32]++;
					if(chbcnt[32]%2==1) {
						chb[32]=true;
						cb[32].setSelected(true);
					}
					else {
						chb[32]=false;
						cb[32].setSelected(false);
					}
				}
			});
			cb[33].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[33]++;
					if(chbcnt[33]%2==1) {
						chb[33]=true;
						cb[33].setSelected(true);
					}
					else {
						chb[33]=false;
						cb[33].setSelected(false);
					}
				}
			});
			cb[34].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[34]++;
					if(chbcnt[34]%2==1) {
						chb[34]=true;
						cb[34].setSelected(true);
					}
					else {
						chb[34]=false;
						cb[34].setSelected(false);
					}
				}
			});
			cb[35].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[35]++;
					if(chbcnt[35]%2==1) {
						chb[35]=true;
						cb[35].setSelected(true);
					}
					else {
						chb[35]=false;
						cb[35].setSelected(false);
					}
				}
			});
			cb[36].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[36]++;
					if(chbcnt[36]%2==1) {
						chb[36]=true;
						cb[36].setSelected(true);
					}
					else {
						chb[36]=false;
						cb[36].setSelected(false);
					}
				}
			});
			cb[37].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[37]++;
					if(chbcnt[37]%2==1) {
						chb[37]=true;
						cb[37].setSelected(true);
					}
					else {
						chb[37]=false;
						cb[37].setSelected(false);
					}
				}
			});
			cb[38].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[38]++;
					if(chbcnt[38]%2==1) {
						chb[38]=true;
						cb[38].setSelected(true);
					}
					else {
						chb[38]=false;
						cb[38].setSelected(false);
					}
				}
			});
			cb[39].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[39]++;
					if(chbcnt[39]%2==1) {
						chb[39]=true;
						cb[39].setSelected(true);
					}
					else {
						chb[39]=false;
						cb[39].setSelected(false);
					}
				}
			});
			cb[40].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[40]++;
					if(chbcnt[40]%2==1) {
						chb[40]=true;
						cb[40].setSelected(true);
					}
					else {
						chb[40]=false;
						cb[40].setSelected(false);
					}
				}
			});
			cb[41].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[41]++;
					if(chbcnt[41]%2==1) {
						chb[41]=true;
						cb[41].setSelected(true);
					}
					else {
						chb[41]=false;
						cb[41].setSelected(false);
					}
				}
			});
			cb[42].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[42]++;
					if(chbcnt[42]%2==1) {
						chb[42]=true;
						cb[42].setSelected(true);
					}
					else {
						chb[42]=false;
						cb[42].setSelected(false);
					}
				}
			});
			cb[43].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[43]++;
					if(chbcnt[43]%2==1) {
						chb[43]=true;
						cb[43].setSelected(true);
					}
					else {
						chb[43]=false;
						cb[43].setSelected(false);
					}
				}
			});
			cb[44].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[44]++;
					if(chbcnt[44]%2==1) {
						chb[44]=true;
						cb[44].setSelected(true);
					}
					else {
						chb[44]=false;
						cb[44].setSelected(false);
					}
				}
			});
			cb[45].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[45]++;
					if(chbcnt[45]%2==1) {
						chb[45]=true;
						cb[45].setSelected(true);
					}
					else {
						chb[45]=false;
						cb[45].setSelected(false);
					}
				}
			});
			cb[46].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[46]++;
					if(chbcnt[46]%2==1) {
						chb[46]=true;
						cb[46].setSelected(true);
					}
					else {
						chb[46]=false;
						cb[46].setSelected(false);
					}
				}
			});
			cb[47].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[47]++;
					if(chbcnt[47]%2==1) {
						chb[47]=true;
						cb[47].setSelected(true);
					}
					else {
						chb[47]=false;
						cb[47].setSelected(false);
					}
				}
			});
			cb[48].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[48]++;
					if(chbcnt[48]%2==1) {
						chb[48]=true;
						cb[48].setSelected(true);
					}
					else {
						chb[48]=false;
						cb[48].setSelected(false);
					}
				}
			});
			cb[49].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[49]++;
					if(chbcnt[49]%2==1) {
						chb[49]=true;
						cb[49].setSelected(true);
					}
					else {
						chb[49]=false;
						cb[49].setSelected(false);
					}
				}
			});
			cb[50].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[50]++;
					if(chbcnt[50]%2==1) {
						chb[50]=true;
						cb[50].setSelected(true);
					}
					else {
						chb[50]=false;
						cb[50].setSelected(false);
					}
				}
			});
			cb[51].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[51]++;
					if(chbcnt[51]%2==1) {
						chb[51]=true;
						cb[51].setSelected(true);
					}
					else {
						chb[51]=false;
						cb[51].setSelected(false);
					}
				}
			});	
			cb[52].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[52]++;
					if(chbcnt[52]%2==1) {
						chb[52]=true;
						cb[52].setSelected(true);
					}
					else {
						chb[52]=false;
						cb[52].setSelected(false);
					}
				}
			});
			cb[53].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[53]++;
					if(chbcnt[53]%2==1) {
						chb[53]=true;
						cb[53].setSelected(true);
					}
					else {
						chb[53]=false;
						cb[53].setSelected(false);
					}
				}
			});
			cb[54].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[54]++;
					if(chbcnt[54]%2==1) {
						chb[54]=true;
						cb[54].setSelected(true);
					}
					else {
						chb[54]=false;
						cb[54].setSelected(false);
					}
				}
			});
			cb[55].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[55]++;
					if(chbcnt[55]%2==1) {
						chb[55]=true;
						cb[55].setSelected(true);
					}
					else {
						chb[55]=false;
						cb[55].setSelected(false);
					}
				}
			});
		
			JLabel label = new JLabel("410호"); //중간에 410호 표시 
			label.setFont(new Font("굴림", Font.BOLD, 22)); //글씨 폰트와 크기 글씨체 변경 
			label.setBounds(469, 12, 62, 42); //위치 지정 
			contentPane.add(label);
			
			cb[0].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {    //클릭 하였을때 chbcnt 값이 증가하여 그 값을 2로 나누었을때 나머지가 1일시 true 0일시 false하여 저장
					chbcnt[0]++;
					if(chbcnt[0]%2!=0) {
						chb[0]=true; //1
					}
					else
						chb[0]=false; //0
				}
			});
			
			
			JButton btnNewButton = new JButton("전체선택");  //전체 선택버튼 누를 시 전체 선택이 된다.
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						for(int n=0; n<56; n++) {   //chbcnt가 2로 나누어서  chbcnt=1일시 전체 true
							chb[n]=true;
							cb[n].setSelected(true);
							chbcnt[n]=1;
						}
					}
			});
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));  //버튼의 글씨체 위치 크기 지정
			btnNewButton.setBounds(43, 702, 256, 27);
			contentPane.add(btnNewButton);
			
			JButton button = new JButton("전체선택 / 해제");    //버튼 클릭시 전체 선택된것이 해제.
			button.setFont(new Font("굴림", Font.BOLD, 15));
			button.setBounds(386, 700, 256, 27);
			contentPane.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {  //chb 가 false 즉 chbcnt 가0이면 전체 false
						for(int n=0; n<56; n++) {
							chb[n]=false;
							cb[n].setSelected(false);
							chbcnt[n]=0;
						}
						}
				});
				
				JButton button_1 = new JButton("종료하기");    //버튼 클릭시 그 선택한 pc종료 하기 (아직 미완성)
				button_1.setFont(new Font("굴림", Font.BOLD, 15));
				button_1.setBounds(719, 700, 256, 27);
				contentPane.add(button_1);
				
				button_1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								for(int n=0; n<56; n++) {
									if(chb[n]==true) {
									//n번 컴퓨터 종료요청
									out.println(macAddress+":410"+(n+1));
								}
							}
					}
				});
			}
		}
	
//	409호
	public class ClassNine extends JFrame {
		JLabel jb[];
		final String[] x={"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",};//컴퓨터 박스의 x축 값
		final String[] y= {"37","37","37","37","37","37","37","37","135","135","135","135","135","135","135","135","227","227","227","227","227","227","227","227","328",
				"328","328","328","328","328","328","328","424","424","424","424","424","424","424","424","521","521","521","521","521","521","521","521","615","615","615","615","615",
				"615","615","615"}; //컴퓨터 박스의 y축 값
		final String[] x2= {"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",}; //체크박스의 x축 값
		final String[] y2= {"87","87","87","87","87","87","87","87","185","185","185","185","185","185","185","185","277","277","277","277","277","277","277","277","378","378","378","378","378",
				"378","378","378","474","474","474","474","474","474","474","474","571","571","571","571","571","571","571","571","665","665","665","665","665","665","665","665"};
				//체크박스의 y축 값
		boolean chb[] = new boolean[56]; //체크박스 불 배열
		int chbcnt[] = new int[56]; //체크박스 카운트 배열
		private JPanel contentPane; 
		
		
		public ClassNine() {
			setTitle("409호"); //타이틀 409호지정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1050, 780); //메인 창 크기와 위치 지정
			contentPane = new JPanel(); // 페널생성 
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //패널간격 
			setContentPane(contentPane);
			contentPane.setLayout(null);
			jb= new JLabel[56];
			JTabbedPane[] tabbedPane = new JTabbedPane[56]; //패널의 배열
			JCheckBox[] cb = new JCheckBox[56]; //체크박스의 배열 
			int rows,cols;//row가로  ,cols 세로
			int index = 0; //값저장하는 변수
			for (cols=0; cols<7; cols++) {  //8행 7열 배열
				for (rows = 0; rows <8; rows++) {
					
					tabbedPane[index] = new JTabbedPane();
					jb[index]=new JLabel();
					contentPane.add(tabbedPane[index]);
					tabbedPane[index].setBounds(Integer.parseInt(x[index]), Integer.parseInt(y[index]), 40, 42);
					//컴퓨터 박스
					tabbedPane[index].add(jb[index]);
					
					cb[index] = new JCheckBox(String.valueOf(index+1));
					contentPane.add(cb[index]);
					cb[index].setBounds(Integer.parseInt(x2[index]), Integer.parseInt(y2[index]),50, 27);
					//체크박스
					index++; //값 저장하는곳 증가 
				}
			}
			cb[0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[0]++;
					if(chbcnt[0]%2==1) {
						chb[0]=true;
						cb[0].setSelected(true);
					}
					else {
						chb[0]=false;
						cb[0].setSelected(false);
					}
				}
			});
			cb[1].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[1]++;
					if(chbcnt[1]%2==1) {
						chb[1]=true;
						cb[1].setSelected(true);
					}
					else {
						chb[1]=false;
						cb[1].setSelected(false);
					}
				}
			});
			cb[2].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[2]++;
					if(chbcnt[2]%2==1) {
						chb[2]=true;
						cb[2].setSelected(true);
					}
					else {
						chb[2]=false;
						cb[2].setSelected(false);
					}
				}
			});
			cb[3].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[3]++;
					if(chbcnt[3]%2==1) {
						chb[3]=true;
						cb[3].setSelected(true);
					}
					else {
						chb[3]=false;
						cb[3].setSelected(false);
					}
				}
			});
			cb[4].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[4]++;
					if(chbcnt[4]%2==1) {
						chb[4]=true;
						cb[4].setSelected(true);
					}
					else {
						chb[4]=false;
						cb[4].setSelected(false);
					}
				}
			});
			cb[5].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[5]++;
					if(chbcnt[5]%2==1) {
						chb[5]=true;
						cb[5].setSelected(true);
					}
					else {
						chb[5]=false;
						cb[5].setSelected(false);
					}
				}
			});
			cb[6].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[6]++;
					if(chbcnt[6]%2==1) {
						chb[6]=true;
						cb[6].setSelected(true);
					}
					else {
						chb[6]=false;
						cb[6].setSelected(false);
					}
				}
			});
			cb[7].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[7]++;
					if(chbcnt[7]%2==1) {
						chb[7]=true;
						cb[7].setSelected(true);
					}
					else {
						chb[7]=false;
						cb[7].setSelected(false);
					}
				}
			});
			cb[8].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[8]++;
					if(chbcnt[8]%2==1) {
						chb[8]=true;
						cb[8].setSelected(true);
					}
					else {
						chb[8]=false;
						cb[8].setSelected(false);
					}
				}
			});
			cb[9].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[9]++;
					if(chbcnt[9]%2==1) {
						chb[9]=true;
						cb[9].setSelected(true);
					}
					else {
						chb[9]=false;
						cb[9].setSelected(false);
					}
				}
			});
			cb[10].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[10]++;
					if(chbcnt[10]%2==1) {
						chb[10]=true;
						cb[10].setSelected(true);
					}
					else {
						chb[10]=false;
						cb[10].setSelected(false);
					}
				}
			});
			cb[11].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[11]++;
					if(chbcnt[11]%2==1) {
						chb[11]=true;
						cb[11].setSelected(true);
					}
					else {
						chb[11]=false;
						cb[11].setSelected(false);
					}
				}
			});
			cb[12].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[12]++;
					if(chbcnt[12]%2==1) {
						chb[12]=true;
						cb[12].setSelected(true);
					}
					else {
						chb[12]=false;
						cb[12].setSelected(false);
					}
				}
			});
			cb[13].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[13]++;
					if(chbcnt[13]%2==1) {
						chb[13]=true;
						cb[13].setSelected(true);
					}
					else {
						chb[13]=false;
						cb[13].setSelected(false);
					}
				}
			});
			cb[14].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[14]++;
					if(chbcnt[14]%2==1) {
						chb[14]=true;
						cb[14].setSelected(true);
					}
					else {
						chb[14]=false;
						cb[14].setSelected(false);
					}
				}
			});
			cb[15].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[15]++;
					if(chbcnt[15]%2==1) {
						chb[15]=true;
						cb[15].setSelected(true);
					}
					else {
						chb[15]=false;
						cb[15].setSelected(false);
					}
				}
			});
			cb[16].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[16]++;
					if(chbcnt[16]%2==1) {
						chb[16]=true;
						cb[16].setSelected(true);
					}
					else {
						chb[16]=false;
						cb[16].setSelected(false);
					}
				}
			});
			cb[17].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[17]++;
					if(chbcnt[17]%2==1) {
						chb[17]=true;
						cb[17].setSelected(true);
					}
					else {
						chb[17]=false;
						cb[17].setSelected(false);
					}
				}
			});
			cb[18].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[18]++;
					if(chbcnt[18]%2==1) {
						chb[18]=true;
						cb[18].setSelected(true);
					}
					else {
						chb[18]=false;
						cb[18].setSelected(false);
					}
				}
			});
			cb[19].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[19]++;
					if(chbcnt[19]%2==1) {
						chb[19]=true;
						cb[19].setSelected(true);
					}
					else {
						chb[19]=false;
						cb[19].setSelected(false);
					}
				}
			});
			cb[20].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[20]++;
					if(chbcnt[20]%2==1) {
						chb[20]=true;
						cb[20].setSelected(true);
					}
					else {
						chb[20]=false;
						cb[20].setSelected(false);
					}
				}
			});
			cb[21].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[21]++;
					if(chbcnt[21]%2==1) {
						chb[21]=true;
						cb[21].setSelected(true);
					}
					else {
						chb[21]=false;
						cb[21].setSelected(false);
					}
				}
			});
			cb[22].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[22]++;
					if(chbcnt[22]%2==1) {
						chb[22]=true;
						cb[22].setSelected(true);
					}
					else {
						chb[22]=false;
						cb[22].setSelected(false);
					}
				}
			});
			cb[23].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[23]++;
					if(chbcnt[23]%2==1) {
						chb[23]=true;
						cb[23].setSelected(true);
					}
					else {
						chb[23]=false;
						cb[23].setSelected(false);
					}
				}
			});
			cb[24].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[24]++;
					if(chbcnt[24]%2==1) {
						chb[24]=true;
						cb[24].setSelected(true);
					}
					else {
						chb[24]=false;
						cb[24].setSelected(false);
					}
				}
			});
			cb[25].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[25]++;
					if(chbcnt[25]%2==1) {
						chb[25]=true;
						cb[25].setSelected(true);
					}
					else {
						chb[25]=false;
						cb[25].setSelected(false);
					}
				}
			});
			cb[26].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[26]++;
					if(chbcnt[26]%2==1) {
						chb[26]=true;
						cb[26].setSelected(true);
					}
					else {
						chb[26]=false;
						cb[26].setSelected(false);
					}
				}
			});
			cb[27].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[27]++;
					if(chbcnt[27]%2==1) {
						chb[27]=true;
						cb[27].setSelected(true);
					}
					else {
						chb[27]=false;
						cb[27].setSelected(false);
					}
				}
			});
			cb[28].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[28]++;
					if(chbcnt[28]%2==1) {
						chb[28]=true;
						cb[28].setSelected(true);
					}
					else {
						chb[28]=false;
						cb[28].setSelected(false);
					}
				}
			});
			cb[29].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[29]++;
					if(chbcnt[29]%2==1) {
						chb[29]=true;
						cb[29].setSelected(true);
					}
					else {
						chb[29]=false;
						cb[29].setSelected(false);
					}
				}
			});
			cb[30].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[30]++;
					if(chbcnt[30]%2==1) {
						chb[30]=true;
						cb[30].setSelected(true);
					}
					else {
						chb[30]=false;
						cb[30].setSelected(false);
					}
				}
			});
			cb[31].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[31]++;
					if(chbcnt[31]%2==1) {
						chb[31]=true;
						cb[31].setSelected(true);
					}
					else {
						chb[31]=false;
						cb[31].setSelected(false);
					}
				}
			});
			cb[32].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[32]++;
					if(chbcnt[32]%2==1) {
						chb[32]=true;
						cb[32].setSelected(true);
					}
					else {
						chb[32]=false;
						cb[32].setSelected(false);
					}
				}
			});
			cb[33].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[33]++;
					if(chbcnt[33]%2==1) {
						chb[33]=true;
						cb[33].setSelected(true);
					}
					else {
						chb[33]=false;
						cb[33].setSelected(false);
					}
				}
			});
			cb[34].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[34]++;
					if(chbcnt[34]%2==1) {
						chb[34]=true;
						cb[34].setSelected(true);
					}
					else {
						chb[34]=false;
						cb[34].setSelected(false);
					}
				}
			});
			cb[35].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[35]++;
					if(chbcnt[35]%2==1) {
						chb[35]=true;
						cb[35].setSelected(true);
					}
					else {
						chb[35]=false;
						cb[35].setSelected(false);
					}
				}
			});
			cb[36].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[36]++;
					if(chbcnt[36]%2==1) {
						chb[36]=true;
						cb[36].setSelected(true);
					}
					else {
						chb[36]=false;
						cb[36].setSelected(false);
					}
				}
			});
			cb[37].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[37]++;
					if(chbcnt[37]%2==1) {
						chb[37]=true;
						cb[37].setSelected(true);
					}
					else {
						chb[37]=false;
						cb[37].setSelected(false);
					}
				}
			});
			cb[38].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[38]++;
					if(chbcnt[38]%2==1) {
						chb[38]=true;
						cb[38].setSelected(true);
					}
					else {
						chb[38]=false;
						cb[38].setSelected(false);
					}
				}
			});
			cb[39].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[39]++;
					if(chbcnt[39]%2==1) {
						chb[39]=true;
						cb[39].setSelected(true);
					}
					else {
						chb[39]=false;
						cb[39].setSelected(false);
					}
				}
			});
			cb[40].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[40]++;
					if(chbcnt[40]%2==1) {
						chb[40]=true;
						cb[40].setSelected(true);
					}
					else {
						chb[40]=false;
						cb[40].setSelected(false);
					}
				}
			});
			cb[41].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[41]++;
					if(chbcnt[41]%2==1) {
						chb[41]=true;
						cb[41].setSelected(true);
					}
					else {
						chb[41]=false;
						cb[41].setSelected(false);
					}
				}
			});
			cb[42].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[42]++;
					if(chbcnt[42]%2==1) {
						chb[42]=true;
						cb[42].setSelected(true);
					}
					else {
						chb[42]=false;
						cb[42].setSelected(false);
					}
				}
			});
			cb[43].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[43]++;
					if(chbcnt[43]%2==1) {
						chb[43]=true;
						cb[43].setSelected(true);
					}
					else {
						chb[43]=false;
						cb[43].setSelected(false);
					}
				}
			});
			cb[44].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[44]++;
					if(chbcnt[44]%2==1) {
						chb[44]=true;
						cb[44].setSelected(true);
					}
					else {
						chb[44]=false;
						cb[44].setSelected(false);
					}
				}
			});
			cb[45].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[45]++;
					if(chbcnt[45]%2==1) {
						chb[45]=true;
						cb[45].setSelected(true);
					}
					else {
						chb[45]=false;
						cb[45].setSelected(false);
					}
				}
			});
			cb[46].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[46]++;
					if(chbcnt[46]%2==1) {
						chb[46]=true;
						cb[46].setSelected(true);
					}
					else {
						chb[46]=false;
						cb[46].setSelected(false);
					}
				}
			});
			cb[47].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[47]++;
					if(chbcnt[47]%2==1) {
						chb[47]=true;
						cb[47].setSelected(true);
					}
					else {
						chb[47]=false;
						cb[47].setSelected(false);
					}
				}
			});
			cb[48].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[48]++;
					if(chbcnt[48]%2==1) {
						chb[48]=true;
						cb[48].setSelected(true);
					}
					else {
						chb[48]=false;
						cb[48].setSelected(false);
					}
				}
			});
			cb[49].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[49]++;
					if(chbcnt[49]%2==1) {
						chb[49]=true;
						cb[49].setSelected(true);
					}
					else {
						chb[49]=false;
						cb[49].setSelected(false);
					}
				}
			});
			cb[50].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[50]++;
					if(chbcnt[50]%2==1) {
						chb[50]=true;
						cb[50].setSelected(true);
					}
					else {
						chb[50]=false;
						cb[50].setSelected(false);
					}
				}
			});
			cb[51].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[51]++;
					if(chbcnt[51]%2==1) {
						chb[51]=true;
						cb[51].setSelected(true);
					}
					else {
						chb[51]=false;
						cb[51].setSelected(false);
					}
				}
			});	
			cb[52].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[52]++;
					if(chbcnt[52]%2==1) {
						chb[52]=true;
						cb[52].setSelected(true);
					}
					else {
						chb[52]=false;
						cb[52].setSelected(false);
					}
				}
			});
			cb[53].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[53]++;
					if(chbcnt[53]%2==1) {
						chb[53]=true;
						cb[53].setSelected(true);
					}
					else {
						chb[53]=false;
						cb[53].setSelected(false);
					}
				}
			});
			cb[54].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[54]++;
					if(chbcnt[54]%2==1) {
						chb[54]=true;
						cb[54].setSelected(true);
					}
					else {
						chb[54]=false;
						cb[54].setSelected(false);
					}
				}
			});
			cb[55].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[55]++;
					if(chbcnt[55]%2==1) {
						chb[55]=true;
						cb[55].setSelected(true);
					}
					else {
						chb[55]=false;
						cb[55].setSelected(false);
					}
				}
			});
		
			JLabel label = new JLabel("409호"); //중간에 409호 표시 
			label.setFont(new Font("굴림", Font.BOLD, 23)); //글씨 폰트와 크기 글씨체 변경 
			label.setBounds(469, 12, 62, 42); //위치 지정 
			contentPane.add(label);
			
			cb[0].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) { //클릭 하였을때 chbcnt 값이 증가하여 그 값을 2로 나누었을때 나머지가 1일시 true 0일시 false하여 저장
					chbcnt[0]++;
					if(chbcnt[0]%2!=0) {
						chb[0]=true; //1
					}
					else
						chb[0]=false; //0
				}
			});
			
			
			JButton btnNewButton = new JButton("전체선택");  //전체 선택버튼 누를 시 전체 선택이 된다.
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						for(int n=0; n<56; n++) {   //chbcnt가 2로 나누어서  chbcnt=1일시 전체 true
							chb[n]=true;
							cb[n].setSelected(true);
							chbcnt[n]=1;
						}
					}
			});
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));  //버튼의 글씨체 위치 크기 지정
			btnNewButton.setBounds(43, 702, 256, 27);
			contentPane.add(btnNewButton);
			
			JButton button = new JButton("전체선택 / 해제");    //버튼 클릭시 전체 선택된것이 해제.
			button.setFont(new Font("굴림", Font.BOLD, 15));
			button.setBounds(386, 700, 256, 27);
			contentPane.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {  //chb 가 false 즉 chbcnt 가0이면 전체 false
						for(int n=0; n<56; n++) {
							chb[n]=false;
							chbcnt[n]=0;
							cb[n].setSelected(false);
						}
					}
			});
			
			JButton button_1 = new JButton("종료하기");    //버튼 클릭시 그 선택한 pc종료 하기 (아직 미완성)
			button_1.setFont(new Font("굴림", Font.BOLD, 15));
			button_1.setBounds(719, 700, 256, 27);
			contentPane.add(button_1);
			
			button_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 자동 생성된 메소드 스텁
						for(int n=0; n<56; n++) {
							if(chb[n]==true) {
							//n번 컴퓨터 종료요청
							out.println(macAddress+":409"+(n+1));
						}
					}
				}
			});
		}
	}
	
//	436호
	public class ClassFTSix extends JFrame {
		final String[] x={"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",};//컴퓨터 박스의 x축 값
		final String[] y= {"37","37","37","37","37","37","37","37","135","135","135","135","135","135","135","135","227","227","227","227","227","227","227","227","328",
				"328","328","328","328","328","328","328","424","424","424","424","424","424","424","424","521","521","521","521","521","521","521","521","615","615","615","615","615",
				"615","615","615"}; //컴퓨터 박스의 y축 값
		final String[] x2= {"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935","43","159","275","386","581","707","820","935",
				"43","159","275","386","581","707","820","935",}; //체크박스의 x축 값
		final String[] y2= {"87","87","87","87","87","87","87","87","185","185","185","185","185","185","185","185","277","277","277","277","277","277","277","277","378","378","378","378","378",
				"378","378","378","474","474","474","474","474","474","474","474","571","571","571","571","571","571","571","571","665","665","665","665","665","665","665","665"};
				//체크박스의 y축 값
		boolean chb[] = new boolean[56]; //체크박스 불 배열
		int chbcnt[] = new int[56]; //체크박스 카운트 배열
		private JPanel contentPane; 
		JLabel[] jb;
		public ClassFTSix() {
			setTitle("436호");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1050, 780);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			jb= new JLabel[56];
			JTabbedPane[] tabbedPane = new JTabbedPane[56]; //패널의 배열
			JCheckBox[] cb = new JCheckBox[56]; //체크박스의 배열 
			int rows,cols;//row가로  ,cols 세로
			int index = 0; //값저장하는 변수
			for (cols=0; cols<7; cols++) {  //8행 7열 배열
				for (rows = 0; rows <8; rows++) {
					jb[index]=new JLabel();
					tabbedPane[index] = new JTabbedPane();
					tabbedPane[index].add(jb[index]);
					contentPane.add(tabbedPane[index]);
					tabbedPane[index].setBounds(Integer.parseInt(x[index]), Integer.parseInt(y[index]), 40, 42);
					//컴퓨터 박스
					
					cb[index] = new JCheckBox(String.valueOf(index+1));
					contentPane.add(cb[index]);
					cb[index].setBounds(Integer.parseInt(x2[index]), Integer.parseInt(y2[index]),50, 27);
					//체크박스
					
					index++; //값 저장하는곳 증가 
					
				}
			}
cb[0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[0]++;
					if(chbcnt[0]%2==1) {
						chb[0]=true;
						cb[0].setSelected(true);
					}
					else {
						chb[0]=false;
						cb[0].setSelected(false);
					}
				}
			});
			cb[1].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[1]++;
					if(chbcnt[1]%2==1) {
						chb[1]=true;
						cb[1].setSelected(true);
					}
					else {
						chb[1]=false;
						cb[1].setSelected(false);
					}
				}
			});
			cb[2].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[2]++;
					if(chbcnt[2]%2==1) {
						chb[2]=true;
						cb[2].setSelected(true);
					}
					else {
						chb[2]=false;
						cb[2].setSelected(false);
					}
				}
			});
			cb[3].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[3]++;
					if(chbcnt[3]%2==1) {
						chb[3]=true;
						cb[3].setSelected(true);
					}
					else {
						chb[3]=false;
						cb[3].setSelected(false);
					}
				}
			});
			cb[4].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[4]++;
					if(chbcnt[4]%2==1) {
						chb[4]=true;
						cb[4].setSelected(true);
					}
					else {
						chb[4]=false;
						cb[4].setSelected(false);
					}
				}
			});
			cb[5].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[5]++;
					if(chbcnt[5]%2==1) {
						chb[5]=true;
						cb[5].setSelected(true);
					}
					else {
						chb[5]=false;
						cb[5].setSelected(false);
					}
				}
			});
			cb[6].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[6]++;
					if(chbcnt[6]%2==1) {
						chb[6]=true;
						cb[6].setSelected(true);
					}
					else {
						chb[6]=false;
						cb[6].setSelected(false);
					}
				}
			});
			cb[7].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[7]++;
					if(chbcnt[7]%2==1) {
						chb[7]=true;
						cb[7].setSelected(true);
					}
					else {
						chb[7]=false;
						cb[7].setSelected(false);
					}
				}
			});
			cb[8].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[8]++;
					if(chbcnt[8]%2==1) {
						chb[8]=true;
						cb[8].setSelected(true);
					}
					else {
						chb[8]=false;
						cb[8].setSelected(false);
					}
				}
			});
			cb[9].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[9]++;
					if(chbcnt[9]%2==1) {
						chb[9]=true;
						cb[9].setSelected(true);
					}
					else {
						chb[9]=false;
						cb[9].setSelected(false);
					}
				}
			});
			cb[10].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[10]++;
					if(chbcnt[10]%2==1) {
						chb[10]=true;
						cb[10].setSelected(true);
					}
					else {
						chb[10]=false;
						cb[10].setSelected(false);
					}
				}
			});
			cb[11].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[11]++;
					if(chbcnt[11]%2==1) {
						chb[11]=true;
						cb[11].setSelected(true);
					}
					else {
						chb[11]=false;
						cb[11].setSelected(false);
					}
				}
			});
			cb[12].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[12]++;
					if(chbcnt[12]%2==1) {
						chb[12]=true;
						cb[12].setSelected(true);
					}
					else {
						chb[12]=false;
						cb[12].setSelected(false);
					}
				}
			});
			cb[13].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[13]++;
					if(chbcnt[13]%2==1) {
						chb[13]=true;
						cb[13].setSelected(true);
					}
					else {
						chb[13]=false;
						cb[13].setSelected(false);
					}
				}
			});
			cb[14].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[14]++;
					if(chbcnt[14]%2==1) {
						chb[14]=true;
						cb[14].setSelected(true);
					}
					else {
						chb[14]=false;
						cb[14].setSelected(false);
					}
				}
			});
			cb[15].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[15]++;
					if(chbcnt[15]%2==1) {
						chb[15]=true;
						cb[15].setSelected(true);
					}
					else {
						chb[15]=false;
						cb[15].setSelected(false);
					}
				}
			});
			cb[16].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[16]++;
					if(chbcnt[16]%2==1) {
						chb[16]=true;
						cb[16].setSelected(true);
					}
					else {
						chb[16]=false;
						cb[16].setSelected(false);
					}
				}
			});
			cb[17].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[17]++;
					if(chbcnt[17]%2==1) {
						chb[17]=true;
						cb[17].setSelected(true);
					}
					else {
						chb[17]=false;
						cb[17].setSelected(false);
					}
				}
			});
			cb[18].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[18]++;
					if(chbcnt[18]%2==1) {
						chb[18]=true;
						cb[18].setSelected(true);
					}
					else {
						chb[18]=false;
						cb[18].setSelected(false);
					}
				}
			});
			cb[19].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[19]++;
					if(chbcnt[19]%2==1) {
						chb[19]=true;
						cb[19].setSelected(true);
					}
					else {
						chb[19]=false;
						cb[19].setSelected(false);
					}
				}
			});
			cb[20].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[20]++;
					if(chbcnt[20]%2==1) {
						chb[20]=true;
						cb[20].setSelected(true);
					}
					else {
						chb[20]=false;
						cb[20].setSelected(false);
					}
				}
			});
			cb[21].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[21]++;
					if(chbcnt[21]%2==1) {
						chb[21]=true;
						cb[21].setSelected(true);
					}
					else {
						chb[21]=false;
						cb[21].setSelected(false);
					}
				}
			});
			cb[22].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[22]++;
					if(chbcnt[22]%2==1) {
						chb[22]=true;
						cb[22].setSelected(true);
					}
					else {
						chb[22]=false;
						cb[22].setSelected(false);
					}
				}
			});
			cb[23].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[23]++;
					if(chbcnt[23]%2==1) {
						chb[23]=true;
						cb[23].setSelected(true);
					}
					else {
						chb[23]=false;
						cb[23].setSelected(false);
					}
				}
			});
			cb[24].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[24]++;
					if(chbcnt[24]%2==1) {
						chb[24]=true;
						cb[24].setSelected(true);
					}
					else {
						chb[24]=false;
						cb[24].setSelected(false);
					}
				}
			});
			cb[25].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[25]++;
					if(chbcnt[25]%2==1) {
						chb[25]=true;
						cb[25].setSelected(true);
					}
					else {
						chb[25]=false;
						cb[25].setSelected(false);
					}
				}
			});
			cb[26].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[26]++;
					if(chbcnt[26]%2==1) {
						chb[26]=true;
						cb[26].setSelected(true);
					}
					else {
						chb[26]=false;
						cb[26].setSelected(false);
					}
				}
			});
			cb[27].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[27]++;
					if(chbcnt[27]%2==1) {
						chb[27]=true;
						cb[27].setSelected(true);
					}
					else {
						chb[27]=false;
						cb[27].setSelected(false);
					}
				}
			});
			cb[28].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[28]++;
					if(chbcnt[28]%2==1) {
						chb[28]=true;
						cb[28].setSelected(true);
					}
					else {
						chb[28]=false;
						cb[28].setSelected(false);
					}
				}
			});
			cb[29].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[29]++;
					if(chbcnt[29]%2==1) {
						chb[29]=true;
						cb[29].setSelected(true);
					}
					else {
						chb[29]=false;
						cb[29].setSelected(false);
					}
				}
			});
			cb[30].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[30]++;
					if(chbcnt[30]%2==1) {
						chb[30]=true;
						cb[30].setSelected(true);
					}
					else {
						chb[30]=false;
						cb[30].setSelected(false);
					}
				}
			});
			cb[31].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[31]++;
					if(chbcnt[31]%2==1) {
						chb[31]=true;
						cb[31].setSelected(true);
					}
					else {
						chb[31]=false;
						cb[31].setSelected(false);
					}
				}
			});
			cb[32].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[32]++;
					if(chbcnt[32]%2==1) {
						chb[32]=true;
						cb[32].setSelected(true);
					}
					else {
						chb[32]=false;
						cb[32].setSelected(false);
					}
				}
			});
			cb[33].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[33]++;
					if(chbcnt[33]%2==1) {
						chb[33]=true;
						cb[33].setSelected(true);
					}
					else {
						chb[33]=false;
						cb[33].setSelected(false);
					}
				}
			});
			cb[34].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[34]++;
					if(chbcnt[34]%2==1) {
						chb[34]=true;
						cb[34].setSelected(true);
					}
					else {
						chb[34]=false;
						cb[34].setSelected(false);
					}
				}
			});
			cb[35].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[35]++;
					if(chbcnt[35]%2==1) {
						chb[35]=true;
						cb[35].setSelected(true);
					}
					else {
						chb[35]=false;
						cb[35].setSelected(false);
					}
				}
			});
			cb[36].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[36]++;
					if(chbcnt[36]%2==1) {
						chb[36]=true;
						cb[36].setSelected(true);
					}
					else {
						chb[36]=false;
						cb[36].setSelected(false);
					}
				}
			});
			cb[37].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[37]++;
					if(chbcnt[37]%2==1) {
						chb[37]=true;
						cb[37].setSelected(true);
					}
					else {
						chb[37]=false;
						cb[37].setSelected(false);
					}
				}
			});
			cb[38].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[38]++;
					if(chbcnt[38]%2==1) {
						chb[38]=true;
						cb[38].setSelected(true);
					}
					else {
						chb[38]=false;
						cb[38].setSelected(false);
					}
				}
			});
			cb[39].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[39]++;
					if(chbcnt[39]%2==1) {
						chb[39]=true;
						cb[39].setSelected(true);
					}
					else {
						chb[39]=false;
						cb[39].setSelected(false);
					}
				}
			});
			cb[40].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[40]++;
					if(chbcnt[40]%2==1) {
						chb[40]=true;
						cb[40].setSelected(true);
					}
					else {
						chb[40]=false;
						cb[40].setSelected(false);
					}
				}
			});
			cb[41].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[41]++;
					if(chbcnt[41]%2==1) {
						chb[41]=true;
						cb[41].setSelected(true);
					}
					else {
						chb[41]=false;
						cb[41].setSelected(false);
					}
				}
			});
			cb[42].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[42]++;
					if(chbcnt[42]%2==1) {
						chb[42]=true;
						cb[42].setSelected(true);
					}
					else {
						chb[42]=false;
						cb[42].setSelected(false);
					}
				}
			});
			cb[43].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[43]++;
					if(chbcnt[43]%2==1) {
						chb[43]=true;
						cb[43].setSelected(true);
					}
					else {
						chb[43]=false;
						cb[43].setSelected(false);
					}
				}
			});
			cb[44].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[44]++;
					if(chbcnt[44]%2==1) {
						chb[44]=true;
						cb[44].setSelected(true);
					}
					else {
						chb[44]=false;
						cb[44].setSelected(false);
					}
				}
			});
			cb[45].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[45]++;
					if(chbcnt[45]%2==1) {
						chb[45]=true;
						cb[45].setSelected(true);
					}
					else {
						chb[45]=false;
						cb[45].setSelected(false);
					}
				}
			});
			cb[46].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[46]++;
					if(chbcnt[46]%2==1) {
						chb[46]=true;
						cb[46].setSelected(true);
					}
					else {
						chb[46]=false;
						cb[46].setSelected(false);
					}
				}
			});
			cb[47].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[47]++;
					if(chbcnt[47]%2==1) {
						chb[47]=true;
						cb[47].setSelected(true);
					}
					else {
						chb[47]=false;
						cb[47].setSelected(false);
					}
				}
			});
			cb[48].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[48]++;
					if(chbcnt[48]%2==1) {
						chb[48]=true;
						cb[48].setSelected(true);
					}
					else {
						chb[48]=false;
						cb[48].setSelected(false);
					}
				}
			});
			cb[49].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[49]++;
					if(chbcnt[49]%2==1) {
						chb[49]=true;
						cb[49].setSelected(true);
					}
					else {
						chb[49]=false;
						cb[49].setSelected(false);
					}
				}
			});
			cb[50].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[50]++;
					if(chbcnt[50]%2==1) {
						chb[50]=true;
						cb[50].setSelected(true);
					}
					else {
						chb[50]=false;
						cb[50].setSelected(false);
					}
				}
			});
			cb[51].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[51]++;
					if(chbcnt[51]%2==1) {
						chb[51]=true;
						cb[51].setSelected(true);
					}
					else {
						chb[51]=false;
						cb[51].setSelected(false);
					}
				}
			});	
			cb[52].addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) { 
					chbcnt[52]++;
					if(chbcnt[52]%2==1) {
						chb[52]=true;
						cb[52].setSelected(true);
					}
					else {
						chb[52]=false;
						cb[52].setSelected(false);
					}
				}
			});
			cb[53].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[53]++;
					if(chbcnt[53]%2==1) {
						chb[53]=true;
						cb[53].setSelected(true);
					}
					else {
						chb[53]=false;
						cb[53].setSelected(false);
					}
				}
			});
			cb[54].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[54]++;
					if(chbcnt[54]%2==1) {
						chb[54]=true;
						cb[54].setSelected(true);
					}
					else {
						chb[54]=false;
						cb[54].setSelected(false);
					}
				}
			});
			cb[55].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					chbcnt[55]++;
					if(chbcnt[55]%2==1) {
						chb[55]=true;
						cb[55].setSelected(true);
					}
					else {
						chb[55]=false;
						cb[55].setSelected(false);
					}
				}
			});
		
			JLabel label = new JLabel("436호"); //중간에 436호 표시 
			label.setFont(new Font("굴림", Font.BOLD, 23)); //글씨 폰트와 크기 글씨체 변경 
			label.setBounds(469, 12, 62, 42); //위치 지정 
			contentPane.add(label);
			
			cb[0].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {  //클릭 하였을때 chbcnt 값이 증가하여 그 값을 2로 나누었을때 나머지가 1일시 true 0일시 false하여 저장
					chbcnt[0]++;
					if(chbcnt[0]%2!=0) {
						chb[0]=true; //1
					}
					else
						chb[0]=false; //0
				}
			});
			
			
			JButton btnNewButton = new JButton("전체선택");  //전체 선택버튼 누를 시 전체 선택이 된다.
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						for(int n=0; n<56; n++) {   //chbcnt가 2로 나누어서  chbcnt=1일시 전체 true
							chb[n]=true;
							cb[n].setSelected(true);
							chbcnt[n]=1;
						}
					}
			});
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 15)); //버튼의 글씨체 위치 크기 지정
			btnNewButton.setBounds(43, 702, 256, 27);
			contentPane.add(btnNewButton);
			
			JButton button = new JButton("전체선택 / 해제"); //버튼 클릭시 전체 선택된것이 해제.
			button.setFont(new Font("굴림", Font.BOLD, 15));
			button.setBounds(386, 700, 256, 27);
			contentPane.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {  //chb 가 false 즉 chbcnt 가0이면 전체 false
						for(int n=0; n<56; n++) {
							chb[n]=false;
							cb[n].setSelected(false);
							chbcnt[n]=0;
						}
					}
			});
			
			JButton button_1 = new JButton("종료하기");    //버튼 클릭시 그 선택한 pc종료 하기 (아직 미완성)
			button_1.setFont(new Font("굴림", Font.BOLD, 15));
			button_1.setBounds(719, 700, 256, 27);
			contentPane.add(button_1);
			
			button_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						for(int n=0; n<56; n++) {
							if(chb[n]==true) {
							//n번 컴퓨터 종료요청
							out.println(macAddress+":436"+(n+1));
						}
					}
				}
			});
		}
	}
	public static void main(String[] args) {
		Client cl = new Client();
	}
}