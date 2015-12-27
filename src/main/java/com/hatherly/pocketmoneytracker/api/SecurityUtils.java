package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

import com.hatherly.pocketmoneytracker.mongodb.MongoLogins;
import com.hatherly.pocketmoneytracker.model.Login;

/**
 * Uses secure password hashing as recommended by OWASP: https://www.owasp.org/index.php/Hashing_Java
 * @author Adam Hatherly
 */
public class SecurityUtils {
	private final static int ITERATION_NUMBER = 1000;
	
	/**
    * Authenticates the user with a given login and password
    * If password and/or login is null then always returns false.
    * If the user does not exist in the database returns false.
    * @param con Connection An open connection to a databse
    * @param login String The login of the user
    * @param password String The password of the user
    * @return boolean Returns true if the user is authenticated, false otherwise
    * @throws SQLException If the database is inconsistent or unavailable (
    *           (Two users with the same login, salt or digested password altered etc.)
    * @throws NoSuchAlgorithmException If the algorithm SHA-1 is not supported by the JVM
    */
   public boolean authenticate(String login, String password) throws NoSuchAlgorithmException, ApplicationException {
       try {
           boolean userExist = true;
           String digest, salt;
           // INPUT VALIDATION
           if (login==null||password==null) {
               // TIME RESISTANT ATTACK
               // Computation time is equal to the time needed by a legitimate user
               userExist = false;
               login="";
               password="";
           }
 
           Login loginDetails = MongoLogins.getLogin(login);
           if (loginDetails != null) {
               digest = loginDetails.getDigest();
               salt = loginDetails.getSalt();
               // DATABASE VALIDATION
               if (digest == null || salt == null) {
                   throw new ApplicationException("Database inconsistant Salt or Digested Password altered");
               }
           } else { // TIME RESISTANT ATTACK (Even if the user does not exist the
               // Computation time is equal to the time needed for a legitimate user
               digest = "000000000000000000000000000=";
               salt = "00000000000=";
               userExist = false;
           }
 
           byte[] bDigest = base64ToByte(digest);
           byte[] bSalt = base64ToByte(salt);
 
           // Compute the new DIGEST
           byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
 
           return Arrays.equals(proposedDigest, bDigest) && userExist;
       } catch (IOException ex){
           throw new ApplicationException("Database inconsistant Salt or Digested Password altered");
       }
   }
 
 
 
   /**
    * Inserts a new user in the database
    * @param con Connection An open connection to a databse
    * @param login String The login of the user
    * @param password String The password of the user
    * @return boolean Returns true if the login and password are ok (not null and length(login)<=100
    * @throws SQLException If the database is unavailable
    * @throws NoSuchAlgorithmException If the algorithm SHA-1 or the SecureRandom is not supported by the JVM
 * @throws UnsupportedEncodingException 
    */
   public boolean createUser(String login, String password)
           throws NoSuchAlgorithmException, UnsupportedEncodingException
   {
       if (login!=null&&password!=null&&login.length()<=100){
           // Uses a secure Random not a simple Random
           SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
           // Salt generation 64 bits long
           byte[] bSalt = new byte[8];
           random.nextBytes(bSalt);
           // Digest computation
           byte[] bDigest = getHash(ITERATION_NUMBER,password,bSalt);
           String sDigest = byteToBase64(bDigest);
           String sSalt = byteToBase64(bSalt);
           MongoLogins.addLogin(new Login(login, sDigest, sSalt));
           return true;
       } else {
           return false;
       }
   }
 
   /**
    * From a password, a number of iterations and a salt,
    * returns the corresponding digest
    * @param iterationNb int The number of iterations of the algorithm
    * @param password String The password to encrypt
    * @param salt byte[] The salt
    * @return byte[] The digested password
    * @throws NoSuchAlgorithmException If the algorithm doesn't exist
 * @throws UnsupportedEncodingException 
    */
   public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest digest = MessageDigest.getInstance("SHA-1");
       digest.reset();
       digest.update(salt);
       byte[] input = digest.digest(password.getBytes("UTF-8"));
       for (int i = 0; i < iterationNb; i++) {
           digest.reset();
           input = digest.digest(input);
       }
       return input;
   }
 
   /**
    * From a base 64 representation, returns the corresponding byte[] 
    * @param data String The base64 representation
    * @return byte[]
    * @throws IOException
    */
   public static byte[] base64ToByte(String data) throws IOException {
       return Base64.decodeBase64(data);
   }
 
   /**
    * From a byte[] returns a base 64 representation
    * @param data byte[]
    * @return String
    * @throws IOException
    */
   public static String byteToBase64(byte[] data){
	   return Base64.encodeBase64String(data);
   }
}
