/*     */ package Util;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NetItemUtil {
/*     */   public static int getNumber(int bond) {
/*   7 */     Random rand = new Random();
/*   8 */     int port = rand.nextInt(bond);
/*   9 */     return port;
/*     */   }
/*     */   
/*  12 */   public static int getPort() { Random rand = new Random();
/*  13 */     int port = rand.nextInt(55000) + 1000;
/*  14 */     return port;
/*     */   }
/*     */   
/*  17 */   public static String getInnerIp() {
             int[][] range = { { -1062731776, -1062666241 } };
/*  23 */     Random rdint = new Random();
/*  24 */     int index = rdint.nextInt(1);
/*  25 */     String ip = NumberUtil.num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
/*  26 */     return ip;
/*     */   }
/*     */   
/*  29 */   public static String getOuterIp() { int[][] range = { { 607649792, 608174079 }, { 1038614528, 1039007743 }, { 1783627776, 1784676351 }, { 2035023872, 2035154943 }, { 2078801920, 2079064063 }, { -1950089216, -1948778497 }, { -1425539072, -1425014785 }, { -1236271104, -1235419137 }, { -770113536, -768606209 }, { -569376768, -564133889 }, { -1084817408, -1084751873 }, { -1062731776, -1062666241 }, { 926416896, 926482431 } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  47 */     Random rdint = new Random();
/*  48 */     int index = rdint.nextInt(13);
/*  49 */     String ip = NumberUtil.num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
/*  50 */     return ip;
/*     */   }
/*     */   
/*     */   public static java.util.Map<String, String> getLocalInfo() {
/*  54 */     String ip = "192.168.100.254";
/*  55 */     String hostname = "254NGSOC.CN";
/*  56 */     java.util.Map<String, String> hostinfo = new java.util.HashMap();
/*     */     try {
/*  58 */       java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
/*  59 */       ip = addr.getHostAddress().toString();
/*  60 */       hostname = addr.getHostName().toString();
/*     */     } catch (java.net.UnknownHostException e) {
/*  62 */       System.out.println("获取本地Houston信息失败，使用默认值");
/*     */     }
/*  64 */     hostinfo.put("ip", ip);
/*  65 */     hostinfo.put("hostname", hostname);
/*  66 */     hostinfo.put("sn", NumberUtil.md5(hostname));
/*  67 */     return hostinfo;
/*     */   }
/*     */   
/*  70 */   public static String getString(int length) { String str = "0123456789abcdefghigklmnopqrstuvwxyz";
/*  71 */     char[] text = new char[length];
/*  72 */     Random rand = new Random();
/*  73 */     for (int i = 0; i < length; i++) {
/*  74 */       text[i] = str.charAt(rand.nextInt(str.length()));
/*     */     }
/*  76 */     return String.valueOf(text);
/*     */   }
/*     */   
/*  79 */   public static String getString(java.util.List<String> str) { return (String)str.get((int)(Math.random() * str.size())); }
/*     */   
/*     */   public static String getString(String flag, int length) {
/*  82 */     String num = "012347899123646957109829156789";
/*  83 */     String low = "abcdefghigklmnopqrstuvwxhkjahfshdfahoieenv,najhyz";
/*  84 */     String up = "ABCDEFGHIJKLMNOPQRSTUVWXYZGAFRGDFASDFGREGDSGASGDFGASDFAOJUOPIKLSJKDFHGKA";
/*  85 */     String str = low;
/*  86 */     if (flag.equals("num")) str = num;
/*  87 */     if (flag.equals("low")) str = low;
/*  88 */     if (flag.equals("up")) str = up;
/*  89 */     char[] text = new char[length];
/*  90 */     Random rand = new Random();
/*  91 */     for (int i = 0; i < length; i++) {
/*  92 */       text[i] = str.charAt(rand.nextInt(str.length()));
/*     */     }
/*  94 */     return String.valueOf(text);
/*     */   }
/*     */   
/*  97 */   public static String getIocPayload() { String payload = "rHQJ9WctbJK/B9LfCABFAABu5G0AAEAROx0KXyQqCl8iDQA1xI0AWltgMlmFgAABAAEAAQABBnllbml1MAR2aWNwA25ldAAAAQABwAwAAQABAAFRgAAEAwIQAsATAAIAAQABUYAABQJuc8ATwD0AAQABAAFRgAAEAwIQAg==";
/*     */     
/*  99 */     return payload;
/*     */   }
/*     */   
/* 102 */   public static String getTcpDownload() { String payload = "23451485454502f312e3120323030204f4b0d0a5365727665723a206e67696e780d0a436f6e74656e74202d20547970653a6170706c69636174696f6e202f206f63746574202d2073747265616d0d0a436f6e74656e74202d204c656e6774683a2033340d0a43";
/*     */     
/* 104 */     Random rand = new Random();
/* 105 */     payload = getString(rand.nextInt(1000) + 100);
/* 106 */     return payload;
/*     */   }
/*     */   
/* 109 */   public static String getTcpUpload() { String payload = "504f5354202f6170692f646174612f696e6465782e68746d6c20485454502f312e310d0a486f73743a2077686f69732e73686f7070696e676c7a2e636f6d0d0a436f6e6e656374696f6e3a206b6565702d616c6976650d0a557365722d4167656e743a20";
/*     */     
/* 111 */     Random rand = new Random();
/* 112 */     payload = getString(rand.nextInt(1000) + 100);
/* 113 */     return payload;
/*     */   }
/*     */   
/* 116 */   public static String getFtpCmd() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "DIR", "CD TEST", "HELP GET", "PWD", "USERNAME", "PUT", "LCD" });
/* 117 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 120 */   public static String getDbSql() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "set names 'utf8' collate 'utf8_unicode_ci'", "select * from xxx", "\\c ngsoc", "use noah", "show databases", "show tables", "update a set ss = 'dada'", "set session sql_mode='NO_ENGINE_SUBSTITUTION'", "SHOW COLUMNS FROM `cdn_script_crontab`", "SELECT * FROM mdl_user WHERE id = '9559'" });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 132 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 135 */   public static String getDnsServer() { java.util.List<String> servers = java.util.Arrays.asList(new String[] { "114.114.114.114", "8.8.8.8", "4.4.4.4", "6.6.6.6", "8.8.6.6", "202.106.0.20", "192.168.1.100" });
/* 136 */     return (String)servers.get((int)(Math.random() * servers.size()));
/*     */   }
/*     */   
/* 139 */   public static String getUri() { Random rand = new Random();
/* 140 */     String uri = "/" + getString(rand.nextInt(20) + 10);
/* 141 */     return uri;
/*     */   }
/*     */   
/* 144 */   public static String getHost() { java.util.List<String> ioc = java.util.Arrays.asList(new String[] { "www.baidu.com", "www.fuck.com", "cublc.com", "oa.ameteksen.com", "www.umengs.com", "tqh.f3322.net", "25000.i77169.com", "donemix.duckdns.org", "dupa.duckdns.org", "main.dresou.net", "www.dresou.net", "www.baidu2233.com", "tarikmouflih10.ddns.net", "abdouza100.ddns.net", "almot8.hopto.org", "shichengxue.3322.org", "lmok1234xing.w239.dns911.cn", "www.oriontronproject.site11.com", "yeniu0.vicp.net", "zer0-sc.no-ip.biz", "zhangze.imwork.net", "dev.null.vg", "wowaskopoq.top", "happypersontyme.ga" });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 171 */     Random rand = new Random();
              String host;
              if (rand.nextInt(2) > 0) {
/* 173 */       host = getString(rand.nextInt(20) + 10) + ".org";
/*     */     }
/*     */     else {
/* 176 */       host = (String)ioc.get((int)(Math.random() * ioc.size()));
/*     */     }
/* 178 */     return host;
/*     */   }
/*     */   
/* 181 */   public static int getAbnormalPort() { return -99; }
/*     */   
/*     */   public static String getAbnoramalType() {
/* 184 */     java.util.List<String> abtype = java.util.Arrays.asList(new String[] { "abnormal icmp", "abnormal syn with payload" });
/* 185 */     return (String)abtype.get((int)(Math.random() * abtype.size()));
/*     */   }
/*     */   
/* 188 */   public static String getAttackType() { return "pass"; }
/*     */   
/*     */   public static String getDnsCount() {
/* 191 */     java.util.List<String> abtype = java.util.Arrays.asList(new String[] { "1;0;1;0", "1;6;0;0", "1;2;0;0", "1;3;0;0", "1;1;0;0", "1;12;0;0", "1;0;1;1" });
/* 192 */     return (String)abtype.get((int)(Math.random() * abtype.size()));
/*     */   }
/*     */   
/* 195 */   public static int getDnsReplyCode() { Random rand = new Random();
/* 196 */     return rand.nextInt(4);
/*     */   }
/*     */   
/* 199 */   public static String getMd5() { return getString(32); }
/*     */   
/*     */   public static String getFilename() {
/* 202 */     java.util.List<String> ename = java.util.Arrays.asList(new String[] { "", "", "txt", "exe", "dll", "doc", "docx", "rar", "zip", "7z", "com", "xls", "xlsx", "ppt", "pptx", "pdf", "pom", "jar", "java", "py", "pyc", "c", "so", "ko", "png", "jpg", "tmp", "ico", "yml", "log", "uri", "gz", "tgz", "ml", "", "", "", "", "sh", "xml" });
/*     */     
/*     */ 
/* 205 */     Random rand = new Random();
/* 206 */     String filename = getString(rand.nextInt(10) + 10) + "." + (String)ename.get((int)(Math.random() * ename.size()));
/* 207 */     return filename;
/*     */   }
/*     */   
/* 210 */   public static String getUsername() { Random rand = new Random();
/* 211 */     String filename = getString(rand.nextInt(10) + 5);
/* 212 */     return filename;
/*     */   }
/*     */   
/* 215 */   public static String getRet() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "success", "fail", "error", "success", "SUCCESS" });
/* 216 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 219 */   public static String getMailAddr() { Random rand = new Random();
/*     */     
/*     */ 
/* 222 */     String ma = getString("low", rand.nextInt(10) + 10) + "@" + getString("low", rand.nextInt(10) + 5) + "." + getString("low", rand.nextInt(2) + 1);
/* 223 */     return ma;
/*     */   }
/*     */   
/* 226 */   public static String getLdapInfo() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "bind request server", "bind server result", "search response entry", "remove bind server", "earch from server", "list [<root>]" });
/* 227 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 230 */   public static String getLdapOp() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "bindResponse", "bindRequest", "searchRequest", "searchResEntry", "unbindRequest", "unbindResponse" });
/* 231 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 234 */   public static String getHttpMethod() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "GET", "POST", "PUT", "DELETE", "GET", "GET", "POST" });
/* 235 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 238 */   public static String getContentType() { java.util.List<String> cmd = java.util.Arrays.asList(new String[] { "image/png", "text/css", "text/javascript", "application/x-www-form-urlencoded", "text/html", "application/x-ndjson", "application/octet-stream", "application/x-www-form-urlencoded; charset=utf-8", "multipart/form-data" });
/*     */     
/*     */ 
/* 241 */     return (String)cmd.get((int)(Math.random() * cmd.size()));
/*     */   }
/*     */   
/* 244 */   public static String getMailPlain() { java.util.List<String> ename = java.util.Arrays.asList(new String[] { "", "", "fuck", "法轮功", "dll", "doc", "docx", "rar", "zip", "7z", "com", "xls", "xlsx", "ppt", "pptx", "pdf", "pom", "jar", "java", "py", "pyc", "c", "so", "ko", "png", "jpg", "tmp", "ico", "yml", "log", "uri", "gz", "tgz", "ml", "", "", "", "", "sh", "xml" });
/*     */     
/*     */ 
/* 247 */     Random rand = new Random();
/*     */     
/* 249 */     String plain = getString(rand.nextInt(1000) + 100) + " " + (String)ename.get((int)(Math.random() * ename.size())) + " " + getString(rand.nextInt(100) + 100);
/* 250 */     return plain;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 257 */     System.out.println(getInnerIp());
/*     */   }
/*     */ }


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\Util\NetItemUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */