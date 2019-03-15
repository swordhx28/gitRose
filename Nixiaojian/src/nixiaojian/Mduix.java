package nixiaojian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class Mduix {
	public static void main(String[] args) throws Exception {
		ObjectOutputStream in = new ObjectOutputStream(new FileOutputStream("d:/object.data"));
		in.writeObject(12);
		in.writeBoolean(true);
		in.writeUTF("ÖÐ¹úÇ°¶Ë");
		in.close();
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("d:/object.data"));
		System.out.println((int )oin.readObject()+":");
		System.out.println(oin.readBoolean()+":");
		System.out.println(oin.readUTF());
		oin.close();
	}
}