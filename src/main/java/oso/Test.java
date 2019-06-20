package oso;

import oso.server.StoreHandler;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/weichen/Documents/image.jpg");

        byte[] content = new byte[(int)file.length()];
        FileInputStream in = new FileInputStream(file);
        int size;
        while ((size = in.read(content)) != -1) {
            System.out.println(size);
            //System.out.println(new String(content));
        }

        StoreHandler storeHandler = StoreHandler.getInstance();
        //System.out.println(new String(content));
        storeHandler.write(content);

        File file1 = new File("/Users/weichen/data/01.jpg");
        //File file2 = new File("/Users/weichen/data/02.jpg");

        byte[] image1 = storeHandler.read(0, content.length);
        //System.out.println("load " + new String(image1));
        //byte[] image2 = storeHandler.read(content.length);

        BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(file1));
        out1.write(image1);

        //BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(file2));
        //out2.write(image2);
    }
}