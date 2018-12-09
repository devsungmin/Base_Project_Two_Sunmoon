package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Server {
	int cnt=0;
	String rid;
	Vector<ClientThread2> vc;
	OracleJDBCTest oj = new OracleJDBCTest();
	
	public Server() {
		vc = new Vector <ClientThread2>();
		ServerSocket server = null;
		try {
			server = new ServerSocket(7777);
		} catch (Exception e) {
			System.out.println("SERVER ERROR");
		}
		System.out.println("클라이언트 접속 대기중");
		try {
			while(true) {
				Socket sock = server.accept();
				ClientThread2 ct = new ClientThread2(sock);
				ct.start();
				vc.add(ct);
			}
		} catch (Exception e) {
			System.out.println("Socket ERROR!");
		}
	}
	// 접속된 모든 client에게 메세지 전송

	 public void sendAllClient(String msg){
	  for (int i = 0; i < vc.size(); i++) {
	   ClientThread2 ct = vc.get(i);
	   ct.sendMessage(msg);
	  }
	 }
	public void removeClient(ClientThread2 ct) {
		vc.remove(ct); // 추가시 접속 해제
	}
	
	class ClientThread2 extends Thread{
	Hashtable<String,Integer> ht=new Hashtable<String,Integer>();
	String closeid;
	Socket sock;
	BufferedReader in;
	PrintWriter out;
	String id=""; //mac주소
	private String pcid;
	
	public ClientThread2(Socket sock) {
		
		try {
			this.sock=sock;
		} catch (Exception e) {
			}		
		}
		@Override
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out = new PrintWriter(sock.getOutputStream(),true);
				System.out.println(sock+"-접속");
				
				boolean done = true;
				while(done) {
					String line = in.readLine();
					if(line==null)done = false;
					//클라이언트에서 받은 data는 routine에서 분석 처리함
					else routine(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				closeid=this.id;
				if(ht!=null) {
					if(ht.containsKey(closeid)==true) {
						System.out.println("로그인정보 삭제");
						int num=ht.get(closeid);
						oj.updateLoginNDB(num);
						ht.remove(closeid);
					}
				}
				removeClient(this);
				
				System.out.println("나감 mac :"+closeid);
				System.out.println(sock+"접속 해제");
				System.out.println("List:"+getIds());
				
				
			}
		}
		
		public void routine(String line) {
			//맥주소:@
			
			int macx = line.indexOf(':');
			String mac = line.substring(0, macx);//맥주소
			String maccmd = line.substring(macx+1);//@
			String cmd = line.substring(macx+1,macx+2);
			String ucmd = line.substring(macx+2);
			String exitcmd = line.substring(macx+1,macx+4);
			
			System.out.println("mac:"+mac);
			System.out.println("cmd:"+cmd);
			System.out.println("ucmd:"+ucmd);
			System.out.println("exitcmd:"+exitcmd);
			if(mac.equals("ID")) { //처음에 클라이언트에서 맥주소를 보내오면 저장함
				if(cmd!=null&&cmd.length()>0){
					if(cnt==0) {
						oj.updateLoginDB();
						cnt++;
						
					}
				     id = maccmd;
				     System.out.println("List:"+getIds());					
				}
			}
			if(mac.equals("RID")) {//관리자 로그인
				if(cmd!=null&&cmd.length()>0){
					id=maccmd;
					rid = maccmd;
					System.out.println("List:"+getIds());
				}
			}
			if(cmd.equals("?")){ //클라에서 보낸 물음에 답장 
			    String data="hi";
			    ClientThread2 ct = findThread(mac);
			    ct.sendMessage(data);
			    
			}
			if(cmd.equals("@")) { //클라이언트에서 로그인시도시 로그인정보 비교후 데이터전송
				//oj.Qwer();
				String[] ldata=ucmd.split(";");
				System.out.println(ldata[0]);
				System.out.println(ldata[1]);
				boolean ly=oj.selectDB(Integer.parseInt(ldata[0]), ldata[1]);
				if(ly==true) { //true
					String data ="@가능";
						boolean lycheck= oj.selectlycheck(Integer.parseInt(ldata[0]));
						if(lycheck==true) {
						ClientThread2 ct = findThread(mac);
				    	ct.sendMessage(data);
				    	int sid=Integer.parseInt(ldata[0]);
				    	oj.updateLoginYDB(sid);
				    	System.out.println("Server:Login가능");
				    	ht.put(mac, sid);
				    	getIds();
					}
						else {
							data = "@불가능";
							ClientThread2 ct = findThread(mac);
						    ct.sendMessage(data);
						    System.out.println("불가능");
						}
				}
				else{ //false
					String data = "@불가능";
					ClientThread2 ct = findThread(mac);
				    ct.sendMessage(data);
				    System.out.println("불가능");
				}
			}
			if(cmd.equals("*")) {
				String[] udata=ucmd.split(";");
				oj.insertDB(Integer.parseInt(udata[0]), udata[1], udata[2], udata[3]);
				System.out.println(cmd);
			}
			if(cmd.equals("(")) {
				boolean lyc=oj.idcheckDB(Integer.parseInt(ucmd));
				if(lyc==true) { //true
					String data ="(가능";
					ClientThread2 ct = findThread(mac);
				    ct.sendMessage(data);
				    System.out.println("가능");
				}
				else{ //false
					String data = "(불가능";
					ClientThread2 ct = findThread(mac);
				    ct.sendMessage(data);
				    System.out.println("(불가능");
				}
			}
			if(exitcmd.equals("406")){
				System.out.println("HI");
				String macindex = line.substring(macx+4);
				System.out.println("macindex:"+macindex);
				String exmac=oj.sixmacDB(Integer.parseInt(macindex),406);
				System.out.println("mac:"+exmac);
				ClientThread2 ct = findThread(exmac);
				ct.sendMessage("!"+exmac);
				
			}
			if(exitcmd.equals("436")){
				String macindex = line.substring(macx+4);
				System.out.println("macindex:"+macindex);
				String exmac=oj.ctsixmacDB(Integer.parseInt(macindex),436);
				System.out.println("mac:"+exmac);
				ClientThread2 ct = findThread(exmac);
				ct.sendMessage("!"+exmac);
			}
			if(exitcmd.equals("410")){
				String macindex = line.substring(macx+4);
				System.out.println("macindex:"+macindex);
				String exmac=oj.tenmacDB(Integer.parseInt(macindex),410);
				System.out.println("mac:"+exmac);
				ClientThread2 ct = findThread(exmac);
				ct.sendMessage("!"+exmac);
			}
			if(exitcmd.equals("409")){
				String macindex = line.substring(macx+4);
				System.out.println("macindex:"+macindex);
				String exmac=oj.ninemacDB(Integer.parseInt(macindex),409);
				System.out.println("mac:"+exmac);
				ClientThread2 ct = findThread(exmac);
				ct.sendMessage("!"+exmac);
			}
			if(cmd.equals("#")) {
				String[] udata=ucmd.split(";");
				String pwc = oj.pwselect(Integer.parseInt(udata[0]), udata[1], udata[2]);
				
				if(pwc==null) {
					System.out.println("정보일치하지않음");
					ClientThread2 ct = findThread(mac);
					ct.sendMessage("#불가능");
				}
				else {
					pcid = pwc;
					ClientThread2 ct = findThread(mac);
					ct.sendMessage("#가능");
					System.out.println("#가능");
				}
			}
			if(cmd.equals("<")) {
				System.out.println(pcid);
				oj.updateDB(Integer.parseInt(pcid),ucmd);
				ClientThread2 ct = findThread(mac);
				ct.sendMessage("<완료");
			}
			if(cmd.equals("|")) {
				String n=ucmd;
				oj.updateLoginNDB(Integer.parseInt(n));
			}
		}
		// 매개변수로 받은 id로 ClientThread2를 Vector에서 찾는다.
		  public ClientThread2 findThread(String id){
		   ClientThread2 ct = null;
		   for (int i = 0; i < vc.size(); i++) {
		    ct = vc.get(i);
		    if(ct.id.equals(id)) break;
		   }
		   return ct;
		  }
		
		  // ids에 맥주소들 저장
		  public String getIds(){
			Enumeration e=ht.keys();
		   String ids = "";
		   String outmac = "";
		   String vm = "";
		   String logininfo="";
		   String rli="";
		   String rli2="";
		   
		   for (int i = 0; i < vc.size(); i++) {
		    ClientThread2 ct = vc.get(i);
		    ids+=ct.id+";";
		   	}
		  
		   if(rid!=null) {
		   String[] viewmac= ids.split(";");
		   while(e.hasMoreElements()) {
			   logininfo=(String)e.nextElement()+";";
		   }
		   String temp[]=logininfo.split(";");
		   for(int m=0; m<temp.length; m++) {
			   System.out.println("templength:"+temp.length);
			   System.out.println("temp[m]:"+temp[m]);
			   if(!temp[m].equals("")) {
				   String lcs=oj.loginCDB(temp[m]);
				   String lns=oj.loginNDB(temp[m]);
				    if(lcs!=null&&lns!=null) {
				   rli=lcs+":"+lns;
				    }
			   }
			  
			   rli2+=rli+";";
		   
		   }
		   for(int j = 0; j < viewmac.length; j++) {
			   if(!rid.equals(viewmac[j])) {
				   String clm=oj.viewCDB(viewmac[j]);
				   String nom=oj.viewNDB(viewmac[j]);
		   
				   if(clm!=null&&nom!=null) {
					   vm=clm+":"+nom;   
				   }
				   outmac+=vm+";";
			   }
		   }
		   System.out.println("rid="+rid);
		   System.out.println("outmac="+outmac);
		   System.out.println("rli2="+rli2);
		   if(!outmac.equals("")) {
			  if(outmac.charAt(0)!=';'){
				   ClientThread2 ct = findThread(rid);
				   ct.out.println("?"+outmac);
		   		   
		   			System.out.println("서버에서 관리자로 보냄");
			   	   }
			   	  
		   	} else {
			   		   ClientThread2 ct = findThread(rid);
			   		   ct.out.println("?123");
			   		   
			   	   }
		   
		   if(!rli2.equals("")) {
			   if(rli2.charAt(0)!=';') {
				   ClientThread2 ct = findThread(rid);
				   ct.out.println("!"+rli2);
			   }
			   else {
		   		   ClientThread2 ct = findThread(rid);
		   		   ct.out.println("!123");
		   		   
		   	   }
		   }
		  }
		   
		   return ids;
		  }
		 
		public void sendMessage(String msg){
			   out.println(msg);
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}