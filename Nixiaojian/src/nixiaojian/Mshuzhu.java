package nixiaojian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Mshuzhu {
	public static void main(String[] args) throws Exception {
		/*File file = new File("d:"+File.separator +"ord.data");
		DataOutputStream dos = new  DataOutputStream(new FileOutputStream(file));
		String names[] = {"����","�羺��","����Ƥ��ͼ���·�"};
		float prices[] = {5799.8f,1278.9f,199.9f};
		int nums[]= {12,15,8};
		for (int i = 0; i < nums.length; i++) {
			dos.writeUTF(names[i]);
			dos.writeUTF("\t");
			dos.writeFloat(prices[i]);
			dos.writeUTF("\t");
			dos.writeInt(nums[i]);
			dos.writeUTF("\n");
			
		}
		dos.close();*/
		File f = new File("d:" + File.separator + "ord.data") ; // �ļ��ı���·��
        DataInputStream in = new DataInputStream(new FileInputStream(f)) ;    // ʵ������������������
        try{
          for (int i = 0; i < 3; i++) {
			System.out.print("����:"+in.readUTF());
			System.out.print(in.readUTF());
			System.out.print("�۸�:"+in.readFloat());
			System.out.print(in.readUTF());
			System.out.print("����:"+in.readInt());
			System.out.print(in.readUTF());
		}
        }catch(Exception e){
        	e.printStackTrace();
        }
        in.close() ;
	}
}
