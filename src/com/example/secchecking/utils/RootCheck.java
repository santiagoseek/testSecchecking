package com.example.secchecking.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RootCheck {

	/** �ж��ֻ��Ƿ�root
	 *  ��Ҫ˼·�Ǽ��su�Ƿ���ڣ���������Ƿ�Ϊ��ִ�� */
	public static boolean isRoot() {
		String binPath = "/system/bin/su";
		String xBinPath = "/system/xbin/su";
		if (new File(binPath).exists() && isExecutable(binPath))
			return true;
		if (new File(xBinPath).exists() && isExecutable(xBinPath))
			return true;
		return false;
	}

	private static boolean isExecutable(String filePath) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("ls -l " + filePath);
			// ��ȡ��������
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String str = in.readLine();
			if (str != null && str.length() >= 4) {
				char flag = str.charAt(3);
				// Ȩ�޼��
				if (flag == 's' || flag == 'x')
					return true;
			}
		} catch (IOException e) {

		} finally {
			if (p != null) {
				p.destroy();
			}
		}
		return false;
	}
	
}
