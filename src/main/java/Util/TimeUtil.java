/*    */ package Util;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
import java.util.Date;

/*    */
/*    */ public class TimeUtil
/*    */ {
/*    */   public static String getTimeStamp()
/*    */   {
/*  9 */     return String.valueOf(System.currentTimeMillis());
/*    */   }
/*    */   
/*    */   public static String getTime() {
/* 13 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 14 */     String time = sdf.format(new java.util.Date());
/* 15 */     return time;
/*    */   }
/*    */   
/*    */   public static String getTimeMs() {
/* 19 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
/* 20 */     String time = sdf.format(new java.util.Date());
/* 21 */     return time;
/*    */   }

            public static int getTimeStamps()
            {
                Date date = new Date();
                String timestamp = String.valueOf(date.getTime() / 1000L);
                return Integer.valueOf(timestamp).intValue();
            }
/*    */   
/* 24 */   public static String getTimeDay() { SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 25 */     String time = sdf.format(new java.util.Date());
/* 26 */     return time;
/*    */   }
/*    */   
/* 29 */   public static String getTimeHour() { SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
/* 30 */     String time = sdf.format(new java.util.Date());
/* 31 */     return time;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 35 */     System.out.println(getTimeDay());
/* 36 */     System.out.println(getTimeHour());
/*    */   }
/*    */ }


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\Util\TimeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */