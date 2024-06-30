package com.software.myhub.service;


import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FormcheckService {
    //checkid方法检查id的格式是否正确
    public static boolean checkid(String id)
    {
        if(id.length() > 12 || id.length() < 3)
            return false;
        for(int i = 0;i < id.length();i++)
        {
            if(!Character.isDigit(id.charAt(i))&&!Character.isAlphabetic(id.charAt(i)))
                return false;
        }
        return true;
    }

    //searchid方法查找目标id在用户表中的位置，若找到则返回其行数（从0开始），否则返回-1。
    public static int Searchid(String id)
    {
        if(!checkid(id))
            return -1;
        int count = 0;
        File file = new File("C:\\lessons\\2024spring\\software\\project\\new\\file\\users.txt");
        //声明字节输入流
        FileInputStream is = null;
        //声明转化类
        InputStreamReader ir = null;
        //声明装饰类
        BufferedReader bi = null;
        try
        {
            //把文件作为参数传进去
            is = new FileInputStream(file);
            //对输入流进行转化，以UTF-8的形式
            ir = new InputStreamReader(is,"UTF-8");
            //使用缓存装饰转化流
            bi = new BufferedReader(ir);
            String thatid;
            while((thatid = bi.readLine())!=null)
            {
                //规定用户表中的用户id一定在"user"的下一行。故读到"user"后即检查下一行是否是目标id
                if(thatid.equals("user"))
                {
                    thatid = bi.readLine();
                    count++;
                    if(thatid.equals(id))
                        return count;
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
        return -1;
    }

    public static boolean checkpassword(String password)
    {
        if(password.length() < 8||password.length() > 16)
            return false;
        for(int i = 1;i < password.length() - 1;i++)
        {
            if(!Character.isDigit(password.charAt(i))&&!Character.isAlphabetic(password.charAt(i)))
                return false;
        }
        return true;
    }

    //checkname方法检查name的格式是否正确
    public static boolean checkname(String name)
    {
        if(name.length() > 16)
            return false;
        String pattern = "[\\w]+";
        return name.matches(pattern);
    }
}