package Util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test2 {

    public static void main(String [] args) throws IOException {
        File file = new File("C:\\Users\\lindan01\\Desktop\\新建文件夹\\规则一体包_20210326154059.zip");

        InputStream inputStream = new FileInputStream(file);
        byte[] data = IOUtils.toByteArray(inputStream);
        Test2 test = new Test2();
        test.getRulePacakgeData(data);

    }

//    public static void main(String [] args){
//        String p="?";
//        String vlaue ="[{\"aaa\":\"bb\",\"cc\":\"dd\"},{\"ee\":\"ff\",\"gg\":\"xx\"},{\"NN\":\"MM\",\"II\":\"OO\",\"KK\":\"JJ\"}]";
//        System.out.println(turnSpecialString( p));
//        String[] split = vlaue.split(turnSpecialString( p));
//        System.out.println( split.length);
//    }

    /**
     * 转义字符串中的特殊字符
     * @param str  待转义字符串
     * @return
     */
    public static String turnSpecialString(String str ){
        String resStr = "";//容器
        for (int i = 0; i < str.length(); i++) {
            if( (String.valueOf(str.charAt(i))).equals("(")
                    || (String.valueOf(str.charAt(i))).equals("[")
                    || (String.valueOf(str.charAt(i))).equals("{")
                    || (String.valueOf(str.charAt(i))).equals("/")
                    || (String.valueOf(str.charAt(i))).equals("^")
                    || (String.valueOf(str.charAt(i))).equals("-")
                    || (String.valueOf(str.charAt(i))).equals("$")
                    || (String.valueOf(str.charAt(i))).equals("|")
                    || (String.valueOf(str.charAt(i))).equals("}")
                    || (String.valueOf(str.charAt(i))).equals("]")
                    || (String.valueOf(str.charAt(i))).equals(")")
                    || (String.valueOf(str.charAt(i))).equals("?")
                    || (String.valueOf(str.charAt(i))).equals("*")
                    || (String.valueOf(str.charAt(i))).equals("+")
                    || (String.valueOf(str.charAt(i))).equals(".")
            ){
                //当检测出特殊字符时，添加转义符。
                resStr = resStr + "\\" + String.valueOf(str.charAt(i));
            }else {
                //非特殊字符直接添加
                resStr += String.valueOf(str.charAt(i));
            }
        }
        return resStr;
    }

    public Map<String, Map<String, byte[]>> getRulePacakgeData(byte[] data) {
        Map<String, Map<String, byte[]>> rulePackageBytes = new HashMap<>();
        try {
            Map<String, byte[]> ruleBytes = unZipBytes(data);
            Set<String> keySet = ruleBytes.keySet();
            for (String key : keySet) {
                String[] array = key.split("/");
                if(array.length == 2) {
                    String folder = array[0];
                    String fileName = array[1];
                    Map<String, byte[]> ruleBytesTmp = rulePackageBytes.get(folder);
                    if (null == ruleBytesTmp) {
                        ruleBytesTmp = new HashMap<>();
                        rulePackageBytes.put(folder, ruleBytesTmp);
                    }
                    ruleBytesTmp.put(fileName, ruleBytes.get(key));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        //    logger.error("Failed to read or unzip rulepacakge data");
        }
        return rulePackageBytes;
    }


    private static Map<String, byte[]> unZipBytes(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ZipInputStream zipInputStream = new ZipInputStream(bais, Charset.forName("gbk"));
        ZipEntry zipEntry;
        byte[] buffer = new byte[2048];
        Map<String, byte[]> result = new HashMap<>();
 //       System.out.println(zipInputStream.getNextEntry());
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (!zipEntry.isDirectory()) {
                String fileName = zipEntry.getName();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int len = 0;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.close();
                result.put(fileName, outputStream.toByteArray());
            }
        }
        zipInputStream.close();
        return result;
    }
}
