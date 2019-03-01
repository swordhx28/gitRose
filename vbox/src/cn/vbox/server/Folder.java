package cn.vbox.server;
import java.io.File;
public class Folder {
	private static String[] varchar = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	/* 鍥犱负linux鏈夋潈闄愶紝鎷呭績鍦ㄧ▼搴忚繍琛岀殑杩囩▼涓垱寤轰笉浜嗘枃浠跺す锛屾墍浠ュ氨鍦ㄥ惎鍔ㄧ殑鏃跺�鍒涘缓浜嗐� */
	public static void initFolder(String basepath) {
		try {
			File file = new File(basepath);
			// 涓昏鏄垽鏂湪linux鎿嶄綔绯荤粺涓綋鍓嶇敤鎴锋病鏈夋潈闄愬垱寤烘枃浠跺す
			if (!file.exists() && !file.mkdirs())
				throw new RuntimeException("folder create error.do you hava authority create folder or your directory write error ?");
			// 鍒涘缓瀛愭枃浠跺す
			createSubFolder(file);
			File[] subFolders = file.listFiles();
			for (int i = 0; i < subFolders.length; i++) {
				createSubFolder(subFolders[i]);
			}
			System.out.println("Success:all folder create success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 鍒涘缓瀛愭枃浠跺す
	private static void createSubFolder(File file) {
		String subfolder;
		for (int i = 0; i < varchar.length; i++) {
			for (int j = 0; j < varchar.length; j++) {
				subfolder = file.getAbsolutePath() + "/" + varchar[i] + varchar[j];
				// 鍒涘缓瀛愭枃浠跺す
				File dfsFile = new File(subfolder);
				if (!dfsFile.exists() && dfsFile.mkdirs()) {
					System.out.println("create mydfs subfolder:" + dfsFile.getAbsolutePath());
				}
			}
		}
	}
}