/*    */ package sendflow;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SendLogsProcess { private java.util.List<String> LOGNAME;
/*  6 */   private java.util.List<Thread> threads = new java.util.ArrayList();
/*    */   private int INTERVAL;
/*  8 */   private int NUMBERS = -99;
/*    */   private String BROKERLIST;
/* 10 */   private Map<String, String> TOPICS = new java.util.HashMap();
/* 11 */   private Map<String, Integer> LOGTYPE = new java.util.HashMap();
/*    */   
/*    */   public SendLogsProcess(java.util.List<String> logname, String brokerlist, int interval) {
/* 14 */     this.LOGNAME = logname;
/* 15 */     this.INTERVAL = interval;
/* 16 */     this.BROKERLIST = brokerlist;
/* 17 */     setLogTypesMap();
/* 18 */     setTopicsMap();
/*    */   }
/*    */   
/*    */   private void setTopicsMap() {
/* 22 */     this.TOPICS.put("TCPFLOW", "td_skyeye_tcpflow");
/* 23 */     this.TOPICS.put("UDPFLOW", "td_skyeye_udpflow");
/* 24 */     this.TOPICS.put("FTPOP", "td_skyeye_ftpop");
/* 25 */     this.TOPICS.put("DNS", "td_skyeye_dns");
/* 26 */     this.TOPICS.put("TELNET", "td_skyeye_telnetcmd");
/* 27 */     this.TOPICS.put("ABNORMAL", "td_skyeye_abnormal");
/* 28 */     this.TOPICS.put("FILE", "td_skyeye_file");
/* 29 */     this.TOPICS.put("LOGIN", "td_skyeye_login");
/* 30 */     this.TOPICS.put("MAIL", "td_skyeye_mail");
/* 31 */     this.TOPICS.put("DB", "td_skyeye_sql");
/* 32 */     this.TOPICS.put("LDAP", "td_skyeye_ldap");
/* 33 */     this.TOPICS.put("SSL", "td_skyeye_ssl");
/* 34 */     this.TOPICS.put("WEBLOG", "td_skyeye_weblog");
/*    */   }
/*    */   
/* 37 */   private void setLogTypesMap() { this.LOGTYPE.put("TCPFLOW", Integer.valueOf(1));
/* 38 */     this.LOGTYPE.put("UDPFLOW", Integer.valueOf(12));
/* 39 */     this.LOGTYPE.put("FTPOP", Integer.valueOf(11));
/* 40 */     this.LOGTYPE.put("DNS", Integer.valueOf(2));
/* 41 */     this.LOGTYPE.put("TELNET", Integer.valueOf(16));
/* 42 */     this.LOGTYPE.put("ABNORMAL", Integer.valueOf(15));
/* 43 */     this.LOGTYPE.put("FILE", Integer.valueOf(4));
/* 44 */     this.LOGTYPE.put("LOGIN", Integer.valueOf(6));
/* 45 */     this.LOGTYPE.put("MAIL", Integer.valueOf(5));
/* 46 */     this.LOGTYPE.put("DB", Integer.valueOf(7));
/* 47 */     this.LOGTYPE.put("LDAP", Integer.valueOf(9));
/* 48 */     this.LOGTYPE.put("SSL", Integer.valueOf(10));
/* 49 */     this.LOGTYPE.put("WEBLOG", Integer.valueOf(3));
/*    */   }
/*    */   
/*    */   public void stopThread() {
/* 53 */     for (Thread t : this.threads) {
/* 54 */       t.interrupt();
/*    */     }
/*    */   }
/*    */   
/*    */   public void startThreadProcess() {
/* 59 */     LogSource LS = LogSource.getLogSource();
/* 60 */     for (String logname : this.LOGNAME) {
/* 61 */       Thread t = new FlowSender(LS, this.BROKERLIST, logname,  (String)this.TOPICS.get(logname), this.NUMBERS);
/* 62 */       t.setName(logname);
/* 63 */       this.threads.add(t);
/*    */     }
/* 65 */     for (Thread t : this.threads) {
/* 66 */       t.start();
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\sendflow\SendLogsProcess.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */