import java.security.MessageDigest;
import java.util.Scanner;

public class MD {
	
	// encrypt methods
	public static String encipher(String pwd, String type) throws Exception {
		MessageDigest md = MessageDigest.getInstance(type);
		md.update(pwd.getBytes("UTF-8"));
		byte[] bytes = md.digest(pwd.getBytes("UTF-8"));
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < bytes.length; i++) {
	    		sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    String signature = sb.toString();
	    
	    return signature;		
	}
	
	// provide encrypt options: MD5, SHA or both
	public static String chooseEncipher(String pwd, String type) throws Exception {
		String pwdSenior = "";
		if (type.equals("Both")) {
			String pwdJunior = encipher(pwd, "MD5");
			pwdSenior = encipher(pwdJunior, "SHA");
		} else {
			pwdSenior = encipher(pwd, type);
		}
		
		return pwdSenior;
	}
	
	// main function
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//part 1. ask user to choose hash function needed
		System.out.print("Please choose type of Hash function (MD5, SHA, Both): ");
		String type = scan.next();
		
		// check user input, give error message and ask for new input if not defined
		while (!type.equals("MD5") && !type.equals("SHA") && !type.equals("Both")) { 
			System.err.println("Undefined function type. Please choose type of Hash function (MD5, SHA, Both): "); // error message
			type = scan.next();
		}
		
		// part 2. ask user to enter input string
		System.out.print("Please enter string for encryption: ");
		scan.nextLine(); // consume '/n' after calling .next() before calling .nextLine() 
		String pwd = scan.nextLine();
		scan.close();
		try {
			System.out.println("Encrypted String: " + chooseEncipher(pwd, type));
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
