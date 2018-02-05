package indi.zqc.warehouse.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class MACUtils {

    /**
     * MAC地址
     * @return
     */
    public static String getMACAddress() {
        String os = getOsName();
        if (os.startsWith("Windows")) {
            return getWindowsMACAddress();
        } else if (os.startsWith("Linux")) {
            return getLinuxMACAddress();
        }
        return "";
    }

    private static String getWindowsMACAddress() {
        StringBuffer sb = null;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }

    private static String getLinuxMACAddress() {
        String address = "";
        String command = "/bin/sh -c ifconfig -a";
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("HWaddr") > 0) {
                    int index = line.indexOf("HWaddr") + "HWaddr".length();
                    address = line.substring(index);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
        }
        return address.trim();
    }

    /**
     * 操作系统
     * @return
     */
    private static String getOsName() {
        return System.getProperty("os.name");
    }

    public static void main(String[] args) {
        System.out.println("Operation System = " + getOsName());
        System.out.println("Mac Address = " + getMACAddress());
    }
}
