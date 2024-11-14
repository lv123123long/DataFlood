/*    */ package Util;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class NumberUtil
/*    */ {
/*    */   public static byte[] intToByteBig(int i) {
/*  8 */     byte[] targets = new byte[4];
/*  9 */     targets[3] = ((byte)(i & 0xFF));
/* 10 */     targets[2] = ((byte)(i >> 8 & 0xFF));
/* 11 */     targets[1] = ((byte)(i >> 16 & 0xFF));
/* 12 */     targets[0] = ((byte)(i >> 24 & 0xFF));
/* 13 */     return targets;
/*    */   }
/*    */   
/*    */   public static byte[] intToByteLittle(int i) {
/* 17 */     byte[] targets = new byte[4];
/* 18 */     targets[0] = ((byte)(i & 0xFF));
/* 19 */     targets[1] = ((byte)(i >> 8 & 0xFF));
/* 20 */     targets[2] = ((byte)(i >> 16 & 0xFF));
/* 21 */     targets[3] = ((byte)(i >> 24 & 0xFF));
/* 22 */     return targets;
/*    */   }
/*    */   
/* 25 */   public static String bytesToHexString(byte[] src) { StringBuilder stringBuilder = new StringBuilder("");
/* 26 */     if ((src == null) || (src.length <= 0)) {
/* 27 */       return null;
/*    */     }
/* 29 */     for (int i = 0; i < src.length; i++) {
/* 30 */       int v = src[i] & 0xFF;
/* 31 */       String hv = Integer.toHexString(v);
/* 32 */       if (hv.length() < 2) {
/* 33 */         stringBuilder.append(0);
/*    */       }
/* 35 */       stringBuilder.append(hv);
/*    */     }
/* 37 */     return stringBuilder.toString();
/*    */   }
/*    */   
/*    */ 
/*    */   public static String num2ip(int ip)
/*    */   {
/* 43 */     int[] b = new int[4];
/* 44 */     String x = "";
/* 45 */     b[0] = (ip >> 24 & 0xFF);
/* 46 */     b[1] = (ip >> 16 & 0xFF);
/* 47 */     b[2] = (ip >> 8 & 0xFF);
/* 48 */     b[3] = (ip & 0xFF);
/* 49 */     x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
/* 50 */     return x;
/*    */   }
/*    */   
/*    */   public static String md5(String plainText)
/*    */   {
/* 55 */     byte[] secretBytes = null;
/*    */     try
/*    */     {
/* 58 */       MessageDigest md = MessageDigest.getInstance("MD5");
/*    */       
/* 60 */       md.update(plainText.getBytes());
/*    */       
/* 62 */       secretBytes = md.digest();
/*    */     } catch (java.security.NoSuchAlgorithmException e) {
/* 64 */       throw new RuntimeException("没有md5这个算法！");
/*    */     }
/*    */     
/* 67 */     String md5code = new java.math.BigInteger(1, secretBytes).toString(16);
/*    */     
/* 69 */     for (int i = 0; i < 32 - md5code.length(); i++) {
/* 70 */       md5code = "0" + md5code;
/*    */     }
/* 72 */     return md5code;
/*    */   }
/*    */ }


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\Util\NumberUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */