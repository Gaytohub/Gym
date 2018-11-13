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
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	int number=0;
	private static final long serialVersionUID = 1L;
	
	public String seek(String name,String password){
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
				System.out.println("succeed");
				br.close();
				return splited[2];
			}
			}
			if(exist==false){
				System.out.println("failed");
				br.close();
				return "failed";
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return "failed";
	}
	
    /**
     * Default constructor. 
     */
    public loginservlet() {
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
        name = request.getParameter("name");
        password = request.getParameter("password");
        
        
        String result = "";
        //ģ�����ݿ��ȡ���ݲ��ж�
        String t = seek(name,password);
        if (t.equals("failed")) {
            result="error";
        }else{
            result = "success "+t;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().print(jsonObject);
        
       
        
	}

}
