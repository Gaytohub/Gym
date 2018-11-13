package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class register
 */
@WebServlet("/registerservlet")
public class register extends HttpServlet {
	int number=0;
	private static final long serialVersionUID = 1L;

	public void signup(String name,String password,String type) {
		File file = new File("E:\\dd.txt");//Text�ļ�
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}//����һ��BufferedReader������ȡ�ļ�
		String s = null;
		try {
			while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
			//System.out.println(s);
			String[] splited = s.split(" ");
			if(name.equals(splited[0])){
				System.out.println("name existed");
				br.close();
				return;
			}
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		FileWriter fw = null;
		try {
			//����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File f=new File("E:\\dd.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
			}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(name+" "+password+" "+type);
		pw.flush();
		System.out.println("register succeed");
		try {
		fw.flush();
		pw.close();
		fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
	}
	
	public int seek(String name,String password){
		boolean exist = false;
		File file = new File("E:\\dd.txt");//Text�ļ�
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}//����һ��BufferedReader������ȡ�ļ�
		String s = null;
		try {
			while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
			//System.out.println(s);
			String[] splited = s.split(" ");
			if(name.equals(splited[0])&&password.equals(splited[1])){
				System.out.println("name exist");
				br.close();
				return 1;
			}
			}
			if(exist==false){
				System.out.println("name not exist");
				br.close();
				return 0;
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 0;
	}
	
    /**
     * Default constructor. 
     */
    public register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		number++;
        System.out.println("�Ѿ�����... ,��"+number+"��");
        
        request.setCharacterEncoding("utf-8");
        String name="";
        String password = "";
        String type = "";
        name = request.getParameter("name");
        password = request.getParameter("password");
        type = request.getParameter("type");
        //System.out.println(type);
        String result = "";
        //ģ�����ݿ��ȡ���ݲ��ж�
        int t = seek(name,password);
        if (t==1) {
            result="error";
        }else{
        	signup(name,password,type);
            result = "success "+type;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().print(jsonObject);
        
       
        
	}

}
