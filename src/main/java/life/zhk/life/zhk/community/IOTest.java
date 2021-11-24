package life.zhk.life.zhk.community;

import java.io.*;

public class IOTest {
    public static void main(String[] args) throws IOException{
       // copy();
        //creatTxt();
        //creatDir();

        //fileUse();

        File file3=new File("zhk_community");
        file3.mkdir();
//        System.out.println(file3);
        File directory = new File("");//设定为当前文件夹
        System.out.println(directory.getCanonicalFile());
    }
    public static void copy() throws IOException {
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("D:\\copy\\张迎运.jpg") );
        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream("D:\\微信图片_202111132154p32.jpg") );
        byte[] bytes=new byte[1024];
        int len = 0;
        while((len=bufferedInputStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,len);
        }
        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
    public static void creatTxt() throws IOException {
        File file = new File("d:\\copy\\a1.txt");
        System.out.println(file);
        file.createNewFile();
    }
    public static void creatDir() throws IOException {
        File file = new File("D://copy//test//1//2");
        file.mkdirs();
    }
    public  static void  fileUse() throws IOException {
        File file =new File("zhk_community\\pom.xml");

        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        File file2 =new File("D:\\copy\\test\\1\\2");
        String[] list = file2.list();
        File[] files = file2.listFiles();
        for (String s : list) {
            System.out.println(s);
        }
        for (File file1 : files) {
            System.out.println(file1);
        }
        file2.delete();

    }
}
