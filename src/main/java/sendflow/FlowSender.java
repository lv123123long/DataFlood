/*     */
package sendflow;
/*     */
/*     */

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*     */
/*     */
/*     */

/*     */
/*     */ public class FlowSender extends Thread
        /*     */ {
    /*     */   private String BROKERLIST;
    /*     */   private String TOPIC;
    /*     */   private LogSource LS;
    /*     */   private int NUMBER;
    private String KRB5BROKERLIST;
    private String KRB5CONF ;
    private String KRB5CONFIG;


    /*     */   private String LOGNAME;

    /*     */
    /*     */
    private FlowSender() {
    }

    /*     */
    /*     */
    public FlowSender(LogSource LS, String BROKERLIST, String LOGNAME, String TOPIC, int NUMBER ,String KRB5BROKERLIST , String KRB5CONF , String KRB5CONFIG)
    /*     */ {
        /*  22 */
        this.LS = LS;
        /*  23 */
        this.BROKERLIST = BROKERLIST;
        /*  24 */
        this.TOPIC = TOPIC;
        /*  25 */
        this.NUMBER = NUMBER;
        /*  28 */
        this.LOGNAME = LOGNAME;

        this.KRB5BROKERLIST = KRB5BROKERLIST;
        this.KRB5CONF = KRB5CONF;
        this.KRB5CONFIG = KRB5CONFIG;

    }


    public FlowSender(LogSource LS, String BROKERLIST, String LOGNAME, String TOPIC, int NUMBER )
        /*     */ {
        /*  22 */
        this.LS = LS;
        /*  23 */
        this.BROKERLIST = BROKERLIST;
        /*  24 */
        this.TOPIC = TOPIC;
        /*  25 */
        this.NUMBER = NUMBER;
        /*  28 */
        this.LOGNAME = LOGNAME;
    }

    /*     */
    /*     */
    private List<byte[]> getLogByLogtype() {
        /*     */
        List<byte[]> data;
        switch (this.LOGNAME) {
            case "TCPFLOW":
                data = this.LS.getTcpflowLog();
                break;
            case "DNS":
                data = this.LS.getDnsLog();
                break;
            case "WEBLOG":
                data = this.LS.getWebLog();
                break;
            case "FILE":
                data = this.LS.getFileLog();
                break;
            case "MAIL":
                data = this.LS.getMailLog();
                break;
            case "LOGIN":
                data = this.LS.getLoginLog();
                break;
            case "DB":
                data = this.LS.getDbLog();
                break;
            case "LDAP":
                data = this.LS.getLdapLog();
                break;
            case "SSL":
                data = this.LS.getSslLog();
                break;
            case "FTPOP":
                data = this.LS.getFtpOPLog();
                break;
            case "UDPFLOW":
                data = this.LS.getUdpflowLog();
                break;
            case "ABNORMAL":
                data = this.LS.getAbnormalLog();
                break;
            case "TELNET":
                data = this.LS.getTelnetLog();
                break;
            case "IOC":
                data =this.LS.getIocLog1();
                break;
            case "IDS":
                data = this.LS.getAttackLog();
                break;
            case "WEBSHELL":
                data = this.LS.getWebShell();
                break;
            case "WEBATTACK":
                data = this.LS.getWebattackLog();
                break;
            case "PROPERTY":
                data = this.LS.getPROPERTY();
                break;
            case "ICMP":
                data = this.LS.getICMP();
                break;
            case "VIRUSES":
                data = this.LS.getVIRUSES();
                break;
            case "DHCP":
                data = this.LS.getDHCP();
                break;
            case "KERBEROS":
                data = this.LS.getKERBEROS();
                break;
            case "RADIUS":
                data = this.LS.getRADIUS();
                break;
            default:
                data = new ArrayList<>();
                break;
        }
        /*     */
        /*  89 */
        return data;
        /*     */
    }



    private void SendLog() {
        /*  93 */

        String[] brokers = this.BROKERLIST.split("&");
        ArrayList<KafkaProducerUtil> brokerList = new ArrayList<>();

        //krb5
        if(this.KRB5BROKERLIST != null && !this.KRB5BROKERLIST.equals("")) {
            String[] krb5Brokers = this.KRB5BROKERLIST.split("&");
            String[] krb5Confs = this.KRB5CONF.split("&");
            String[] krb5Configs = this.KRB5CONFIG.split("&");

            for (int i = 0; i < krb5Brokers.length; i++) {
                brokerList.add(KafkaProducerUtil.getKafkaProducer(krb5Brokers[i], this.TOPIC, false, krb5Confs[i], krb5Configs[i]));
            }
        }

        for (String berker : brokers) {
            brokerList.add(KafkaProducerUtil.getKafkaProducer(berker, this.TOPIC, false));
        }

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                brokerList.size(),
                brokerList.size(),
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(brokerList.size()));


        /*  95 */
        System.out.println("Start thread " + this.LOGNAME);
        //开始时间
        long startTime = System.currentTimeMillis();

        brokerList
                .stream()
                .map(producer -> new Runnable() {
                    long count = 0 ;

                    @Override
                    public void run() {
                        while (true){
                            limitSpeed(startTime, count);

                            try {

                                List<byte[]> datalog = getLogByLogtype();
                                /*  99 */
                                for (byte[] pbdata : datalog) {
                                    /* 100 */
                                    byte[] output = getByteStream(pbdata);
                                    /* 101 */
                                    ProducerRecord MESSAGE = new ProducerRecord(TOPIC, output);
                                    /* 102 */
                                    producer.send(MESSAGE);
                                    /* 103 */
                                    count++;
                                    /*     */
                                }
                            } catch (IllegalStateException e) {
                                //add liyu04
                                if (e.getMessage().contains("data read over")) {
                                    System.out.println(LOGNAME +" data read over ");
                                    ((LogSourceFromFile) LS).close();
                                    return;
                                }
                                e.printStackTrace();
                            }
                        }
                    }}).forEach(threadPool::execute);

    }

    public void run() {
        /* 129 */
        SendLog();
        /*     */
    }

    private void limitSpeed(long startTime, long count){
        if (this.NUMBER > 0) {
            long s = (System.currentTimeMillis() - startTime) / 1000;
            if (s > 0) {
                if (count / s >= this.NUMBER) {
                    try {
                        Thread.sleep(1000 - (System.currentTimeMillis() % 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(count >= this.NUMBER) {
                    try {
                        Thread.sleep(1000 - (System.currentTimeMillis() % 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*     */
    /*     */
    private static byte[] getByteStream(byte[] pbdata) {
        /* 133 */
        int pbdata_len = pbdata.length;
        /* 134 */
        byte[] countb = Util.NumberUtil.intToByteLittle(1);
        /* 135 */
        byte[] datab = Util.NumberUtil.intToByteLittle(pbdata_len);
        /* 136 */
        byte[] ret = new byte[8 + pbdata_len];
        /* 137 */
        System.arraycopy(countb, 0, ret, 0, countb.length);
        /* 138 */
        System.arraycopy(datab, 0, ret, countb.length, datab.length);
        /* 139 */
        System.arraycopy(pbdata, 0, ret, countb.length + datab.length, pbdata.length);
        /* 140 */
        return ret;
        /*     */
    }


    public static void main(String[] args) {
//        String BROKERLIST = "12ngsoc.cn:9092,25ngsoc.cn:9092,26ngsoc.cn:9092";
        try {
            org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
//            java.io.File conf = new java.io.File("");
            java.io.File conf = new java.io.File(args[0]);
            Map<String, Object> map = (Map) yaml.load(new java.io.FileInputStream(conf));
            String brokerLists = (String) map.get("brokerlist");
            System.out.println("BrokerList: " + brokerLists);
            map.remove("brokerlist");

            String krb5brokerList = (String) map.get("krb5brokerlist");
            System.out.println("krb5brokerlist: " + krb5brokerList);
            map.remove("krb5brokerlist");

            String krb5Conf = (String) map.get("krb5conf");
            System.out.println("krb5conf: " + krb5Conf);
            map.remove("krb5conf");

            String krb5Config = (String) map.get("krb5config");
            System.out.println("krb5config: " + krb5Config);
            map.remove("krb5config");
//            LogSource LS = LogSource.getLogSource();
            /* 206 */
            List<String> LOGNAME = new java.util.ArrayList();
            /* 207 */
            List<Thread> threads = new java.util.ArrayList();
            /* 208 */
            Map<String, String> TOPICS = new HashMap();
            /* 209 */
            Map<String, Integer> NUMBERS = new HashMap();
            Map<String, String> DATADIR = new HashMap<>();
            for (Map.Entry<String, Object> m : map.entrySet()) {
                Map<String, Object> value = (Map<String, Object>) m.getValue();
                String status = (String)value.get("status");
                if (status.equals("yes")) {
                    TOPICS.put(m.getKey(),(String) value.get("topic"));
                    NUMBERS.put(m.getKey(), (Integer) value.get("number"));
                    //add liyu04
                    if (value.containsKey("data_dir")) {
                        DATADIR.put(m.getKey(), (String) value.get("data_dir"));
                    }
                    LOGNAME.add(m.getKey());
                }
            }

            for (String logname : LOGNAME) {
                /* 305 */
                //add liyu04
                LogSource LS;
                if (DATADIR.containsKey(logname)) {
                    LS = LogSourceFromFile.logSourceFromFile(DATADIR.get(logname));
                }else{
                    LS = LogSource.getLogSource();
                }
                Thread t = new FlowSender(LS, brokerLists, logname, (String) TOPICS.get(logname), ((Integer) NUMBERS.get(logname)).intValue(),krb5brokerList,krb5Conf,krb5Config);
                t.setName(logname);
                threads.add(t);
            }
            for (Thread thread : threads) {
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

}