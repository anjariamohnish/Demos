/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author mohnish
 */
public class SecretKeyProvider {

    private static final String FILE_NAME = "key.ser";
    public static SecretKeyProvider secretKeyProvider = null;
    private final String key;

    private SecretKeyProvider() {
        if (doesFileExist()) {
            SecretKey secretKey = deserializeObject();
            this.key = secretKey.getKey();
        } else {
            SecretKey secretKey = new SecretKey(generateRandomString());
            this.key = secretKey.getKey();
            serializeObject(secretKey);
        }
    }

    public static SecretKeyProvider getInstance() {
        if (secretKeyProvider == null) {
            synchronized (SecretKeyProvider.class) {
                if (secretKeyProvider == null) {
                    secretKeyProvider = new SecretKeyProvider();
                }
            }
        }
        return secretKeyProvider;
    }

    public String getKey() {
        return key;
    }

    private boolean doesFileExist() {
        File file = new File(FILE_NAME);
        return file.exists() && !file.isDirectory();
    }

    private void serializeObject(SecretKey secretKey) {
        try {
            FileOutputStream file = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(secretKey);

            out.close();
            file.close();
            System.out.println("Object has been serialized");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private SecretKey deserializeObject() {
        try {
            FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);
            SecretKey secretKey = (SecretKey) in.readObject();
            in.close();
            file.close();
            return secretKey;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private String generateRandomString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < 20; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
