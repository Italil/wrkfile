package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        File direction = new File("C://NewDir");
        boolean created = direction.mkdir();
        if(created) {
            System.out.println("Каталог создан");

            File newFile1 = new File(direction + "//test1.txt");
            File newFile2 = new File(direction + "//test2.txt");

            try {
                boolean created1 = newFile1.createNewFile();
                boolean created2 = newFile2.createNewFile();

                if (created1 && created2) {
                    System.out.println("Файлы созданы");
                    try(FileWriter writer1 = new FileWriter(newFile1, false))
                    {
                        System.out.println("Введите первую часть: ");
                        String text1 = sc.nextLine();
                        writer1.write(text1);
                    }
                    try(FileReader reader = new FileReader(newFile1))
                    {
                        char[] fromFile1 = new char[256];
                        int c;
                        while((c = reader.read(fromFile1))>0){
                            if(c < 256){
                                fromFile1 = Arrays.copyOf(fromFile1, c);
                            }
                            System.out.print(fromFile1);
                        }
                        try(FileWriter writer2 = new FileWriter(newFile2, false)) {
                            Charset cset = Charset.forName("UTF-8");
                            ByteBuffer buf = cset.encode(CharBuffer.wrap(fromFile1));
                            byte[] b = buf.array();
                            String str = new String(b);

                            System.out.println("Введите вторую часть: ");
                            String text2 = sc.nextLine();
                            writer2.write(str + " " + text2);
                        }
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println("Каталог не был создан");
        }
    }
}
