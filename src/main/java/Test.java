import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static String crc16(String gprsstr) {
        try {
            int crc;
            int strlength, r;
            byte sbit;
            int tc;
            strlength = gprsstr.length();
            byte[] data = gprsstr.getBytes();
            crc = 0x0000FFFF;
            for (int i = 0; i < strlength; i++) {
                tc = (int) (crc >>> 8);
                crc = (int) (tc ^ data[i]);
                for (r = 0; r < 8; r++) {
                    sbit = (byte) (crc & 0x00000001);
                    crc >>>= 1;
                    if (sbit != 0)
                        crc ^= 0x0000A001;
                }
            }
        //    System.out.println(crc+"");
            return Integer.toHexString(crc);
        } catch (Exception ex) {
            return "";
        }
    }

//    public static  void  main(String []args) {
//        long l =     System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++){
//            String a = "dfdsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
//            Test.crc16(a);
//        }
//        System.out.println("花费时间："+ (System.currentTimeMillis()-l));
//     //   System.out.println(Test.crc16(a));
//    }

    public static void main(String []args){
        Pattern pattern = Pattern.compile("\\[(\\d+)\\?(\\d+)\\?(\\d+)\\?(\\d+)\\]");
        Matcher matcher = pattern.matcher("[1?2?3?48562]");
        if(matcher.find()) {
            int count = matcher.groupCount();
            if(count >= 1) {
//                String[] values = new String[count];
//                for (int i = 1; i <= count; i++) {
//                    values[i-1] =matcher.group(i);
//                }
//                inputDatas = addDatas(inputDatas, destFiled, getArrayValue(values,dType));
                for (int i = 1; i <= count; i++) {
                    System.out.println(  matcher.group(i));
                }
            }
        }
    }


}
