package com.software.myhub.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LoginService {
    static String user_list = "C:\\lessons\\2024spring\\software\\project\\new\\file\\users.txt";
    public static boolean login(String id, String password) {

        int line = FormcheckService.Searchid(id);
        if(line == -1)
            return false;
        File file = new File(user_list);
        //声明字节输入流
        FileInputStream is = null;
        //声明转化类
        InputStreamReader ir = null;
        //声明装饰类
        BufferedReader bi = null;
        try
        {
            int count = 0;
            String thatpw;
            //把文件作为参数传进去
            is = new FileInputStream(file);
            //对输入流进行转化，以UTF-8的形式
            ir = new InputStreamReader(is,"UTF-8");
            //使用缓存装饰转化流
            bi = new BufferedReader(ir);
            while(bi.readLine() != null)
            {
                //规定用户表中的用户id一定在"user"的下一行。故读到"user"后即检查下一行是否是目标id
                if(count == line)
                {
                    thatpw = bi.readLine();
                    return thatpw.equals(password);
                }
                count++;
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                //只需要关闭最外层
                bi.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String signup(String id, String password)
    {
        if(!FormcheckService.checkid(id))
            return "wrong id form,the length should be exactly 10 while all character should be numbers!";
        if(!FormcheckService.checkpassword(password))
            return "wrong password form,the length should be between 8 and 16 while every character should be either number or alphabet!";
        File file = new File(user_list);
        if(FormcheckService.Searchid(id) != -1)
            return "id existed!";
        FileOutputStream os = null;
        //声明转化类
        OutputStreamWriter ow = null;
        //声明装饰类
        BufferedWriter bo = null;
        try {
            //把文件作为参数传进去
            os = new FileOutputStream(file,true);
            //对输入流进行转化，以UTF-8的形式
            ow = new OutputStreamWriter(os, "UTF-8");
            //使用缓存装饰转化流
            bo = new BufferedWriter(ow);
            //将新建用户的各项信息写入
            /*
            格式为：
            user
            id
            name
            password
            tag
            */

            bo.write("user");
            bo.newLine();
            bo.write(id);
            bo.newLine();
            bo.write(password);
            bo.newLine();
            bo.flush();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                //关闭
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        File usr_path = new File("C:\\lessons\\2024spring\\software\\project\\new\\file\\" + id);
        if(usr_path.exists())
            return "user exists";
        if(usr_path.mkdir())
            return "success";
        else return "error";
    }
}
