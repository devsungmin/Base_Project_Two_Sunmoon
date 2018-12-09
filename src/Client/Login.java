package Client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;

public class Login extends JFrame {
	Client cl = new Client();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton Ssignup;
	private Password ps;
	int idc;
	ImageIcon icon;
	BufferedImage img = null;

	public Login() {
	      setUndecorated(true); // 닫기 버튼 제거_1
	      getRootPane().setWindowDecorationStyle(JRootPane.NONE); //닫기 버튼 제거
	      setBounds(100, 100, 1020, 661);
	      icon = new ImageIcon("image/LBack.png");
	      contentPane = new JPanel() {
	    	  public void paintComponent(Graphics g) {
	    		  g.drawImage(icon.getImage(),0,0,null);
	    		  setOpaque(false);
	    		  super.paintComponent(g);
	    	  }
	      };
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      
	      SpringLayout sl_contentPane = new SpringLayout();
	      contentPane.setLayout(null);
	      
	      /*프로그램 제목*/
	      JLabel PcM = new JLabel("PC 관리 시스템");
	      PcM.setLocation(632,50);
	      PcM.setSize(1000,50);
	      PcM.setFont(new Font("굴림", Font.BOLD, 50));
	      contentPane.add(PcM);
	      
	      /*학번 입력 창*/
	      JLabel UserId = new JLabel("학번 :");
	      UserId.setLocation(1192,657);
	      UserId.setSize(1000,30);
	      UserId.setFont(new Font("굴림", Font.BOLD, 30));
	      contentPane.add(UserId);
	      
	      textField = new JTextField();
	      textField.setLocation(1296,646);
	      textField.setSize(280,50);
	      contentPane.add(textField);
	      textField.setColumns(10);
	      
	      /*비밀번호  입력 창*/
	      JLabel UserPw = new JLabel("비밀번호 : ");
	      UserPw.setLocation(1130,721);
	      UserPw.setSize(800,30);
	      UserPw.setFont(new Font("굴림", Font.BOLD, 30));
	      contentPane.add(UserPw);
	            
	      textField_1 = new JPasswordField();
	      textField_1.setLocation(1296,717);
	      textField_1.setSize(280,50);
	      contentPane.add(textField_1);
	      textField_1.setColumns(10);
	      
	      /*로그인 버튼 */
	      JButton Login = new JButton(new ImageIcon("image/로그인.png"));
	      Login.setLocation(1149,786);
	      Login.setSize(120,50);
	      Login.setFont(new Font("굴림", Font.BOLD, 18));
	      Login.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent arg0) {
	            // TODO Auto-generated method stub         
	            int id = Integer.parseInt(textField.getText());
	            String pw = textField_1.getText();
	            cl.out.println(cl.macAddress+":@"+id+";"+pw);

	         }
	      });
	      contentPane.add(Login);
	      
	      /* 비번 찾기 버튼*/
	      JButton button_1 = new JButton(new ImageIcon("image/비번찾기.png"));
	      button_1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            ps = new Password();
	            ps.setAlwaysOnTop(true);
	            ps.setVisible(true);
	         }
	      });
	      button_1.setLocation(1309,786);
	      button_1.setSize(120,50);
	      button_1.setFont(new Font("굴림", Font.BOLD, 18));
	      contentPane.add(button_1);
	      
	      /*회원가입 창*/
	      JButton Singup = new JButton(new ImageIcon("image/회원가입.png"));
	      /*회원가입 클릭시 Singup클래스가 실행됨*/
	      Singup.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            Signup Su = new Signup();
	            Su.setVisible(true);
	            Su.setAlwaysOnTop(true);
	            
	         }
	      });
	      Singup.setLocation(1463,786);
	      Singup.setSize(120,50);
	      Singup.setFont(new Font("굴림", Font.BOLD, 18));
	      contentPane.add(Singup);
	      
	   }

	public class Signup extends JFrame {
		
		private JPanel contentPane;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_5;
		private JTextField textField_6;
		private JTextField textField_7;
		private JTextField years,moonth,days;
		
		
		
		public Signup() {
			
			setBounds(100, 100, 552, 706);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblSignUp = new JLabel("회원 가입");
			lblSignUp.setBounds(203, 10, 131, 60);
			lblSignUp.setFont(new Font("굴림", Font.BOLD, 24));
			contentPane.add(lblSignUp);
			
			JLabel lblId = new JLabel("학번 : ");
			lblId.setBounds(96, 118, 104, 35);
			lblId.setFont(new Font("굴림", Font.BOLD, 24));
			contentPane.add(lblId);
			
			textField = new JTextField();
			textField.setBounds(175, 124, 202, 32);
			textField.setFont(new Font("굴림", Font.BOLD, 15));
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton(new ImageIcon("image/중복확인.png"));
			btnNewButton.setBounds(413, 127, 113, 23);
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 16));
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					idc = Integer.parseInt(textField.getText());
					cl.out.println(cl.macAddress+":("+idc);

				}
			});
			contentPane.add(btnNewButton);
			
			JLabel label = new JLabel("비밀번호 : ");
			label.setFont(new Font("굴림", Font.BOLD, 24));
			label.setBounds(49, 166, 131, 47);
			contentPane.add(label);
			JPasswordField textField_1= new JPasswordField();
			textField_1.setBounds(175, 175, 202, 38);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("생년 월일 : ");
			lblNewLabel.setBounds(28, 240, 141, 35);
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
			contentPane.add(lblNewLabel);
			
			/*년도*/
			JLabel Year = new JLabel("년");
			Year.setBounds(242, 254, 50, 15);
			contentPane.add(Year);
			years = new JTextField();
			years.setBounds(175, 240, 60, 33);
			contentPane.add(years);
			
			/*월 콤보 박스*/
			JLabel Moonth = new JLabel("월");
			Moonth.setBounds(310, 260, 50, 15);
			contentPane.add(Moonth);
			moonth = new JTextField();
			moonth.setBounds(262, 240, 44, 33);
			contentPane.add(moonth);
			
			/*일 콤보 박스*/
			JLabel Day = new JLabel("일");
			Day.setBounds(382, 260, 50, 15);
			contentPane.add(Day);
			days = new JTextField();
			days.setBounds(330, 240, 47, 33);
			contentPane.add(days);
			
			JLabel lblNewLabel_3 = new JLabel("연락처 : ");
			lblNewLabel_3.setBounds(58, 296, 111, 35);
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 24));
			contentPane.add(lblNewLabel_3);
			
			textField_5 = new JTextField();
			textField_5.setText("010");
			textField_5.setBounds(171, 296, 50, 32);
			textField_5.setFont(new Font("굴림", Font.BOLD, 15));
			contentPane.add(textField_5);
			textField_5.setColumns(10);
			
			JLabel label_2 = new JLabel("-");
			label_2.setBounds(230, 306, 26, 15);
			contentPane.add(label_2);
			
			textField_6 = new JTextField();
			textField_6.setBounds(242, 296, 60, 32);
			textField_6.setFont(new Font("굴림", Font.BOLD, 15));
			contentPane.add(textField_6);
			textField_6.setColumns(10);
			
			JLabel label_3 = new JLabel("-");
			label_3.setBounds(310, 307, 26, 8);
			contentPane.add(label_3);
			
			textField_7 = new JTextField();
			textField_7.setBounds(330, 296, 60, 32);
			textField_7.setFont(new Font("굴림", Font.BOLD, 15));
			contentPane.add(textField_7);
			textField_7.setColumns(10);
			
			Ssignup = new JButton(new ImageIcon("image/회원가입창.png"));
			Ssignup.setEnabled(false);
			Ssignup.setBounds(121, 584, 135, 55);
		      Ssignup.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent arg0) {
			        	
			            int Id=Integer.parseInt(textField.getText());
			            String Pw=textField_1.getText();
			            int year=Integer.parseInt(years.getText());
			            int month=Integer.parseInt(moonth.getText());
			            int day=Integer.parseInt(days.getText());
			            
			            String Birthday=year+"년"+month+"월"+day+"일";
			            
			         	String tel1=textField_5.getText();
			            int tel2=Integer.parseInt(textField_6.getText());
			            int tel3=Integer.parseInt(textField_7.getText());
			       
			            String Tel=tel1+"-"+tel2+"-"+tel3; //전화번호 010 고정
			            
			            String data=Id+";"+Pw+";"+Tel+";"+Birthday;
			            
						cl.out.println(cl.macAddress+":*"+data);

			            setVisible(false);
			           		         }
			      });
			
			Ssignup.setFont(new Font("굴림", Font.BOLD, 20));
			contentPane.add(Ssignup);
			
			JButton btnNewButton_2 = new JButton(new ImageIcon("image/닫기.png"));
			btnNewButton_2.setBounds(328, 584, 91, 55);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					dispose();
				}
			});
			btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
			contentPane.add(btnNewButton_2);
			
		
		}

	}
	

	public class Client implements Runnable {
		BufferedReader in;
		 PrintWriter out;
		 Socket sock;
		 String macAddress;
		 boolean loginY=false;

			 public Client(){
			  try {
			   sock = new Socket("127.0.0.1",7777);
			   in =new BufferedReader(
			    new InputStreamReader(
			    sock.getInputStream()));
			   out=new PrintWriter(
			    sock.getOutputStream(),true);
			   System.out.println("접속성공");
			   macAddress=mac();
			   out.println("ID:"+macAddress);
			  
			   
			   
			  }catch (Exception e) {
			   System.err.println("Error in Connect");
			   e.printStackTrace();
			   System.exit(1);
			  }
			  new Thread(this).start();
			 }

		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 try {
				   boolean done = false;
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
			// TODO Auto-generated method stub	
			
			if(line.charAt(0)=='!'){
				//컴퓨터 종료여부
				int idx = line.indexOf('!');
				String mic = line.substring(idx+1);// '!'뒷부분만 가져옴
				if(mic.equals(macAddress)) {
					System.out.println("시스템 종료.");
					 try {
		                    Runtime.getRuntime().exec("Shutdown.exe -f -s -t " + 5);
		                    
		                }
		                catch (Exception e) {
		                    e.printStackTrace();
		                }
				}	
			}
			else if(line.charAt(0)=='@') {
				//로그인 가능여부
				int idy = line.indexOf('@'); // @을 기준으로
				String loginCmd = line.substring(idy+1); //@ 뒷부분만 저장
				if(loginCmd.equals("가능")) {
					loginY = true;
					System.out.println("C:Login가능");
					LoginY ly = new LoginY();
					ly.setAlwaysOnTop(true);
					ly.setVisible(true);
					setVisible(false);

				}
				else {
					loginY = false;
					System.out.println("C:Login불가능");
					LoginN ln = new LoginN();
					ln.setVisible(true);
					ln.setAlwaysOnTop(true);
				}
			}
			else if(line.charAt(0)=='(') {
				//아이디 중복확인
				int idc = line.indexOf('(');
				String ldCmd = line.substring(idc+1);
				if(ldCmd.equals("불가능")) {
					Check ck = new Check();
					ck.setVisible(true);
					ck.setAlwaysOnTop(true);
				}
				else {
					Ssignup.setEnabled(true);
					Notcheck nck = new Notcheck();
					nck.setVisible(true);
					nck.setAlwaysOnTop(true);
					
				}
			}
			else if(line.charAt(0)=='#') {
				//비밀번호 변경
				int pwc = line.indexOf('#');
				String pwcmd = line.substring(pwc+1);
				if(pwcmd.equals("가능")) {
					System.out.println("#가능받음");
					Pwchange pc = new Pwchange();
					pc.setVisible(true);
					pc.setAlwaysOnTop(true);
				}
				else {
					Pwcfail pf = new Pwcfail();
					pf.setVisible(true);
					pf.setAlwaysOnTop(true);
				}
			}
			else if(line.charAt(0)=='<') {
				//변경완료
				Pcy pcy = new Pcy();
				pcy.setVisible(true);
				pcy.setAlwaysOnTop(true);
			}
			else if(line.charAt(0)=='?') {
				System.out.println("여기아님");
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
		
	}
	
//	비번 찾기 클래스
	public class Password extends JFrame {
		private JPanel contentPane;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		
		public Password() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 496, 376);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			JLabel lblPassword = new JLabel("비밀번호 찾기");
			lblPassword.setBounds(139, 10, 161, 29);
			lblPassword.setVerticalAlignment(SwingConstants.TOP);
			lblPassword.setFont(new Font("굴림", Font.PLAIN, 24));
			contentPane.add(lblPassword);
			
			JLabel lblNewLabel_1 = new JLabel("학번 :");
			lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 24));
			lblNewLabel_1.setBounds(55, 72, 72, 29);
			contentPane.add(lblNewLabel_1);
			textField = new JTextField();
			textField.setBounds(139, 76, 214, 29);
			contentPane.add(textField);
			textField.setColumns(10);
			JLabel lblNewLabel_2 = new JLabel("생년 월일 :");
			lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 24));
			lblNewLabel_2.setBounds(12, 111, 131, 38);
			contentPane.add(lblNewLabel_2);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"1990", "1991", "1992", "1993", "1994", "1995","1996", "1997", "1998", "1999", "2000"}));
			comboBox.setBounds(139, 115, 57, 29);
			contentPane.add(comboBox);
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			comboBox_1.setBounds(212, 115, 45, 29);
			contentPane.add(comboBox_1);

			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			comboBox_2.setBounds(280, 115, 45, 29);
			contentPane.add(comboBox_2);

			JLabel lblNewLabel_3 = new JLabel("년");
			lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(198, 125, 34, 19);
			contentPane.add(lblNewLabel_3);

			JLabel lblNewLabel_4 = new JLabel("월");
			lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 12));
			lblNewLabel_4.setBounds(257, 125, 32, 19);
			contentPane.add(lblNewLabel_4);

			JLabel lblNewLabel_5 = new JLabel("일");
			lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 12));
			lblNewLabel_5.setBounds(325, 125, 34, 19);
			contentPane.add(lblNewLabel_5);

			JLabel lblNewLabel_6 = new JLabel("연락처 :");
			lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 24));
			lblNewLabel_6.setBounds(22, 159, 88, 28);
			contentPane.add(lblNewLabel_6);

			textField_1 = new JTextField();
			textField_1.setFont(new Font("굴림", Font.PLAIN, 12));
			textField_1.setText("010");
			textField_1.setBounds(130, 159, 45, 29);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			textField_2 = new JTextField();
			textField_2.setFont(new Font("굴림", Font.PLAIN, 12));
			textField_2.setColumns(10);
			textField_2.setBounds(187, 159, 45, 29);
			contentPane.add(textField_2);
			textField_3 = new JTextField();
			textField_3.setFont(new Font("굴림", Font.PLAIN, 12));
			textField_3.setColumns(10);
			textField_3.setBounds(244, 159, 45, 29);
			contentPane.add(textField_3);
			JLabel lblNewLabel_7 = new JLabel("-");
			lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 14));
			lblNewLabel_7.setBounds(176, 169, 20, 15);
			contentPane.add(lblNewLabel_7);

			JLabel lblNewLabel_8 = new JLabel("-");
			lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 14));
			lblNewLabel_8.setBounds(233, 169, 34, 15);
			contentPane.add(lblNewLabel_8);
			JButton btnNewButton = new JButton(new ImageIcon("image/찾기.png"));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int Id=Integer.parseInt(textField.getText());
		            
		            int year=Integer.parseInt(comboBox.getSelectedItem().toString());
		            int month=Integer.parseInt(comboBox_1.getSelectedItem().toString());
		            int day=Integer.parseInt(comboBox_2.getSelectedItem().toString());
		            
		            String Birthday=year+"년"+month+"월"+day+"일";
		            String tel1=textField_1.getText();
		            int tel2=Integer.parseInt(textField_2.getText());
		            int tel3=Integer.parseInt(textField_3.getText());
		       
		            String Tel=tel1+"-"+tel2+"-"+tel3;
		            
		            String data=Id+";"+Tel+";"+Birthday;
		            
					cl.out.println(cl.macAddress+":#"+data);
					ps.setVisible(false);
					
				}
			});
			btnNewButton.setFont(new Font("굴림", Font.PLAIN, 24));
			btnNewButton.setBounds(107, 253, 110, 50);
			contentPane.add(btnNewButton);
			JButton btnNewButton_1 = new JButton(new ImageIcon("image/닫기창.png"));
			btnNewButton_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					setVisible(false);
					dispose();
				}
			});
			btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 24));
			btnNewButton_1.setBounds(267, 253, 110, 50);
			contentPane.add(btnNewButton_1);
		}
	}	
//	 비번 변경
	public class Pwchange extends JFrame {

		private JPanel contentPane;
		private JPasswordField passwordField;
		private JPasswordField passwordField_1;

		
		public Pwchange() {
			setTitle("비밀번호 변경");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 342, 257);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			passwordField = new JPasswordField(); // 비밀번호
			
			passwordField_1 = new JPasswordField(); // 비밀번호 확인
			
			JLabel label = new JLabel("비밀번호 : ");
			
			JLabel label_1 = new JLabel("비밀번호 확인 :");
			
			JButton btnNewButton = new JButton("변경");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String pw = passwordField.getText();
					String pwh = passwordField_1.getText();
					
					if(pw.equals(pwh)) {
						cl.out.println(cl.macAddress+":<"+pw);
						setVisible(false);
						dispose();
					}
					else
					{
						Pcn pc = new Pcn();
						pc.setVisible(true);
						pc.setAlwaysOnTop(true);
					}
				}
			});
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(55, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(passwordField_1, Alignment.LEADING)
									.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
								.addGap(96))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(61)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		}
	}
	
	public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	            	Login frame = new Login();
	               	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	               	frame.setUndecorated(true);
	               	frame.setVisible(true);
	               	frame.setAlwaysOnTop(true); // 최상위 윈도우 -> art+f4를 못함
	               	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // alt+f4 종료 못하게 함
	               	frame.setResizable(false); //사이즈 조정 x
	            } catch (Exception e) {
	               e.printStackTrace(); //예외처리 정보 받기
	            }
	         }
	      });
	      
	   }
	}