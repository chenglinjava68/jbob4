package com.eshore.inas.testDAO;
import org.jasypt.util.text.BasicTextEncryptor;
/**
 * ���ܽ�����
 * @author Administrator
 *
 */
public class PasswordEncryptorTest {
    public static void main(String[] args) {
        //���� 
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor(); 
        textEncryptor.setPassword("root");
        String newPassword = textEncryptor.encrypt("rts");
        System.out.println(newPassword);
//        ���� 
        BasicTextEncryptor textEncryptor2 = new BasicTextEncryptor(); 
        textEncryptor2.setPassword("root"); 
        String oldPassword = textEncryptor2.decrypt(newPassword);   
        System.out.println(oldPassword);
    }
}