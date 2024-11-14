/*     */
package sendflow;
/*     */
/*     */

import Sensor.NgfwSensor;
import Util.NetItemUtil;
import Util.TimeUtil;
import com.google.protobuf.ByteString;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;

import java.util.*;

/*     */
/*     */
/*     */

/*     */
/*     */ public class LogSource
        /*     */ {
    /*  10 */   private Map<String, String> hostinfo = null;
    /*  11 */   private static final List<String> ftp = Arrays.asList(new String[]{"ftp_control", "ftp"});
    /*  12 */   private static final List<String> ldap = Arrays.asList(new String[]{"ldap", "ldap"});
    /*  13 */   private static final List<String> dns = Arrays.asList(new String[]{"dns", "dns"});
    /*  14 */   private static final List<String> ioc = Arrays.asList(new String[]{"dns", "http"});
    /*  15 */   private static final List<String> ssl = Arrays.asList(new String[]{"https", "ssl", "ssh"});
    /*  16 */   private static final List<String> login = Arrays.asList(new String[]{"ssh", "mysql", "ftp", "telnet", "postgresql", "redis", "db2"});
    /*  17 */   private static final List<String> udp = Arrays.asList(new String[]{"tftp", "dns", "netbios", "udp", "snmp"});
    /*  18 */   private static final List<String> tcp = Arrays.asList(new String[]{"http", "https", "ftp", "ssl", "ssh", "imap", "smtp", "pop3", "tcp"});
    /*  19 */   private static final List<String> mail = Arrays.asList(new String[]{"imap", "smtp", "pop3"});
    /*  20 */   private static final List<String> db = Arrays.asList(new String[]{"mysql", "redis", "tds", "MongoDB", "oracle", "postgres", "sqlserver"});
    /*  21 */   private static final List<String> file = Arrays.asList(new String[]{"http", "http"});
    /*  22 */   private Map<String, List> protoPort = new java.util.HashMap();
    /*     */ List<String> hosts;
    /*     */ List<String> dips;

    public LogSource(int i)
    {
        //nothing
    }
    /*     */
    /*     */
    private LogSource() {
        /*  27 */
        this.hostinfo = NetItemUtil.getLocalInfo();
        /*  28 */
        this.protoPort.put("http", Arrays.asList(new Integer[]{Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(800), Integer.valueOf(888), Integer.valueOf(8080), Integer.valueOf(8888), Integer.valueOf(10800), Integer.valueOf(443)}));
        /*  29 */
        this.protoPort.put("https", Arrays.asList(new Integer[]{Integer.valueOf(8443), Integer.valueOf(443)}));
        /*  30 */
        this.protoPort.put("dns", Arrays.asList(new Integer[]{Integer.valueOf(53), Integer.valueOf(53)}));
        /*  31 */
        this.protoPort.put("ftp", Arrays.asList(new Integer[]{Integer.valueOf(20), Integer.valueOf(21)}));
        /*  32 */
        this.protoPort.put("ftp_control", Arrays.asList(new Integer[]{Integer.valueOf(20), Integer.valueOf(21)}));
        /*  33 */
        this.protoPort.put("ldap", Arrays.asList(new Integer[]{Integer.valueOf(389), Integer.valueOf(389)}));
        /*  34 */
        this.protoPort.put("ssh", Arrays.asList(new Integer[]{Integer.valueOf(22), Integer.valueOf(222), Integer.valueOf(2222)}));
        /*  35 */
        this.protoPort.put("ssl", Arrays.asList(new Integer[]{Integer.valueOf(8443), Integer.valueOf(443), Integer.valueOf(345)}));
        /*  36 */
        this.protoPort.put("mysql", Arrays.asList(new Integer[]{Integer.valueOf(3306), Integer.valueOf(3306)}));
        /*  37 */
        this.protoPort.put("pop3", Arrays.asList(new Integer[]{Integer.valueOf(110), Integer.valueOf(110)}));
        /*  38 */
        this.protoPort.put("smtp", Arrays.asList(new Integer[]{Integer.valueOf(25), Integer.valueOf(25)}));
        /*  39 */
        this.protoPort.put("imap", Arrays.asList(new Integer[]{Integer.valueOf(143), Integer.valueOf(143)}));
        /*  40 */
        this.protoPort.put("db2", Arrays.asList(new Integer[]{Integer.valueOf(50000), Integer.valueOf(9090)}));
        /*  41 */
        this.protoPort.put("postgres", Arrays.asList(new Integer[]{Integer.valueOf(5432), Integer.valueOf(5434)}));
        /*  42 */
        this.protoPort.put("sqlserver", Arrays.asList(new Integer[]{Integer.valueOf(1433), Integer.valueOf(1434)}));
        /*  43 */
        this.protoPort.put("oracle", Arrays.asList(new Integer[]{Integer.valueOf(1521), Integer.valueOf(1521)}));
        /*  44 */
        this.protoPort.put("redis", Arrays.asList(new Integer[]{Integer.valueOf(6379), Integer.valueOf(6380), Integer.valueOf(7000), Integer.valueOf(7001), Integer.valueOf(7002), Integer.valueOf(8000), Integer.valueOf(8001), Integer.valueOf(7003)}));
        /*  45 */
        this.protoPort.put("netbios", Arrays.asList(new Integer[]{Integer.valueOf(137), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(445)}));
        /*  46 */
        this.protoPort.put("MongoDB", Arrays.asList(new Integer[]{Integer.valueOf(27017), Integer.valueOf(27018)}));
        /*  47 */
        this.protoPort.put("tds", Arrays.asList(new Integer[]{Integer.valueOf(1443), Integer.valueOf(1444)}));
        /*  48 */
        this.protoPort.put("qq", Arrays.asList(new Integer[]{Integer.valueOf(8000), Integer.valueOf(8000)}));
        /*  49 */
        this.protoPort.put("snmp", Arrays.asList(new Integer[]{Integer.valueOf(161), Integer.valueOf(162)}));
        /*     */
    }

    /*     */
    /*  52 */
    private LogSource(List<String> hosts, List<String> dips) {
        this.hostinfo = NetItemUtil.getLocalInfo();
        /*  53 */
        this.protoPort.put("http", Arrays.asList(new Integer[]{Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(800), Integer.valueOf(888), Integer.valueOf(8080), Integer.valueOf(8888), Integer.valueOf(10800), Integer.valueOf(443)}));
        /*  54 */
        this.protoPort.put("https", Arrays.asList(new Integer[]{Integer.valueOf(8443), Integer.valueOf(443)}));
        /*  55 */
        this.protoPort.put("dns", Arrays.asList(new Integer[]{Integer.valueOf(53), Integer.valueOf(53)}));
        /*  56 */
        this.protoPort.put("ftp", Arrays.asList(new Integer[]{Integer.valueOf(20), Integer.valueOf(21)}));
        /*  57 */
        this.protoPort.put("ftp_control", Arrays.asList(new Integer[]{Integer.valueOf(20), Integer.valueOf(21)}));
        /*  58 */
        this.protoPort.put("ldap", Arrays.asList(new Integer[]{Integer.valueOf(389), Integer.valueOf(389)}));
        /*  59 */
        this.protoPort.put("ssh", Arrays.asList(new Integer[]{Integer.valueOf(22), Integer.valueOf(222), Integer.valueOf(2222)}));
        /*  60 */
        this.protoPort.put("ssl", Arrays.asList(new Integer[]{Integer.valueOf(8443), Integer.valueOf(443), Integer.valueOf(345)}));
        /*  61 */
        this.protoPort.put("mysql", Arrays.asList(new Integer[]{Integer.valueOf(3306), Integer.valueOf(3306)}));
        /*  62 */
        this.protoPort.put("pop3", Arrays.asList(new Integer[]{Integer.valueOf(110), Integer.valueOf(110)}));
        /*  63 */
        this.protoPort.put("smtp", Arrays.asList(new Integer[]{Integer.valueOf(25), Integer.valueOf(25)}));
        /*  64 */
        this.protoPort.put("imap", Arrays.asList(new Integer[]{Integer.valueOf(143), Integer.valueOf(143)}));
        /*  65 */
        this.protoPort.put("db2", Arrays.asList(new Integer[]{Integer.valueOf(50000), Integer.valueOf(9090)}));
        /*  66 */
        this.protoPort.put("postgres", Arrays.asList(new Integer[]{Integer.valueOf(5432), Integer.valueOf(5434)}));
        /*  67 */
        this.protoPort.put("sqlserver", Arrays.asList(new Integer[]{Integer.valueOf(1433), Integer.valueOf(1434)}));
        /*  68 */
        this.protoPort.put("oracle", Arrays.asList(new Integer[]{Integer.valueOf(1521), Integer.valueOf(1521)}));
        /*  69 */
        this.protoPort.put("redis", Arrays.asList(new Integer[]{Integer.valueOf(6379), Integer.valueOf(6380), Integer.valueOf(7000), Integer.valueOf(7001), Integer.valueOf(7002), Integer.valueOf(8000), Integer.valueOf(8001), Integer.valueOf(7003)}));
        /*  70 */
        this.protoPort.put("netbios", Arrays.asList(new Integer[]{Integer.valueOf(137), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(445)}));
        /*  71 */
        this.protoPort.put("MongoDB", Arrays.asList(new Integer[]{Integer.valueOf(27017), Integer.valueOf(27018)}));
        /*  72 */
        this.protoPort.put("tds", Arrays.asList(new Integer[]{Integer.valueOf(1443), Integer.valueOf(1444)}));
        /*  73 */
        this.protoPort.put("qq", Arrays.asList(new Integer[]{Integer.valueOf(8000), Integer.valueOf(8000)}));
        /*  74 */
        this.protoPort.put("snmp", Arrays.asList(new Integer[]{Integer.valueOf(161), Integer.valueOf(162)}));
        /*  75 */
        this.hosts = hosts;
        /*  76 */
        this.dips = dips;
        /*     */
    }

    /*     */
    /*     */
    public static LogSource getLogSource() {
        /*  80 */
        return new LogSource();
        /*     */
    }

    /*     */
    /*  83 */
    public static LogSource getLogSource(List<String> hosts, List<String> dips) {
        return new LogSource(hosts, dips);
    }

    /*     */
    /*     */
    private Map<String, Integer> getProtoPort(String logtype)
    /*     */ {
        /*  87 */
        List<String> unproto = Arrays.asList(new String[]{"tcp", "udp", "telnet"});
        /*     */
        String proto;
        /*     */
        try
            /*     */ {
            /*     */
            /*  92 */
            if (logtype.equals("ftpop")) {
                /*  93 */
                proto = (String) ftp.get((int) (Math.random() * ftp.size()));
                /*     */
            } else {
                /*  95 */
                if (logtype.equals("ldap")) {
                    /*  96 */
                    proto = (String) ldap.get((int) (Math.random() * ldap.size()));
                    /*     */
                } else {
                    /*  98 */
                    if (logtype.equals("ioc")) {
                        /*  99 */
                        proto = (String) ioc.get((int) (Math.random() * ioc.size()));
                        /*     */
                    } else {
                        /* 101 */
                        if (logtype.equals("dns")) {
                            /* 102 */
                            proto = (String) dns.get((int) (Math.random() * dns.size()));
                            /*     */
                        } else {
                            /* 104 */
                            if (logtype.equals("ssl")) {
                                /* 105 */
                                proto = (String) ssl.get((int) (Math.random() * ssl.size()));
                                /*     */
                            } else {
                                /* 107 */
                                if (logtype.equals("login")) {
                                    /* 108 */
                                    proto = (String) login.get((int) (Math.random() * login.size()));
                                    /*     */
                                } else {
                                    /* 110 */
                                    if (logtype.equals("udpflow")) {
                                        /* 111 */
                                        proto = (String) udp.get((int) (Math.random() * udp.size()));
                                        /*     */
                                    } else {
                                        /* 113 */
                                        if (logtype.equals("tcpflow")) {
                                            /* 114 */
                                            proto = (String) tcp.get((int) (Math.random() * tcp.size()));
                                            /*     */
                                        } else {
                                            /* 116 */
                                            if (logtype.equals("mail")) {
                                                /* 117 */
                                                proto = (String) mail.get((int) (Math.random() * mail.size()));
                                                /*     */
                                            } else {
                                                /* 119 */
                                                if (logtype.equals("db")) {
                                                    /* 120 */
                                                    proto = (String) db.get((int) (Math.random() * db.size()));
                                                    /*     */
                                                } else {
                                                    /* 122 */
                                                    if (logtype.equals("file")) {
                                                        /* 123 */
                                                        proto = (String) file.get((int) (Math.random() * file.size()));
                                                        /*     */
                                                    } else {
                                                        /* 125 */
                                                        if (logtype.equals("telnet")) {
                                                            /* 126 */
                                                            proto = "telnet";
                                                            /*     */
                                                        }
                                                        /*     */
                                                        else
                                                            /* 129 */                               proto = "tcp";
                                                        /*     */
                                                    }
                                                    /*     */
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            /* 132 */
            proto = "tcp";
        }
        /*     */
        int port;
        /*     */
        try {
            /* 135 */
            if (unproto.contains(proto)) {
                /* 136 */
                port = NetItemUtil.getPort();
                /*     */
            }
            /*     */
            else {
                /* 139 */
                List<Integer> ports = (List) this.protoPort.get(proto);
                /* 140 */
                port = ((Integer) ports.get((int) (Math.random() * ports.size()))).intValue();
                /*     */
            }
            /*     */
        } catch (Exception e) {
            /* 143 */
            port = NetItemUtil.getPort();
            /*     */
        }
        /* 145 */
        Map<String, Integer> pP = new java.util.HashMap();
        /* 146 */
        pP.put(proto, Integer.valueOf(port));
        /* 147 */
        return pP;
        /*     */
    }

    /*     */
    /*     */
    private void printPb(String pb) {
        /* 151 */
        System.out.println("===================================================");
        /* 152 */
        System.out.println(pb);
        /* 153 */
        System.out.println("===================================================");
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getFtpOPLog() {
        /* 157 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 158 */
        Sensor.NgfwSensor.FTP_OP.Builder pb = Sensor.NgfwSensor.FTP_OP.newBuilder();
        /* 159 */
        Map<String, Integer> pP = getProtoPort("ftpop");
        /* 160 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 161 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 162 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 163 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 164 */
        pb.setProto(proto);
        /* 165 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 166 */
        pb.setSport(NetItemUtil.getPort());
        /* 167 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 168 */
        pb.setDport(port);
        /* 169 */
        pb.setUser("admin");
        /* 170 */
        pb.setSeq(123);
        /* 171 */
        pb.setOp(NetItemUtil.getFtpCmd());
        /* 172 */
        pb.addRet("ok");
        /* 173 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 174 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 175 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 176 */
        pbdata.setSkyeyeFtpop(pb);
        /* 177 */
        pbdata.setMessageType(12);
        /* 178 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 179 */
        logs.add(data.toByteArray());
        /* 180 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getDnsLog() {
        /* 184 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 185 */
        java.util.Random rand = new java.util.Random();
        /* 186 */
        String dnsip = NetItemUtil.getDnsServer();
        /* 187 */
        String sip = NetItemUtil.getInnerIp();
        /* 188 */
        String host = NetItemUtil.getHost();
        /* 189 */
        int port = NetItemUtil.getPort();
        /*     */
        /* 191 */
        Sensor.NgfwSensor.DNS.Builder pb = Sensor.NgfwSensor.DNS.newBuilder();
        /* 192 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 193 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 194 */
        pb.setDnsType(0);
        /* 195 */
        pb.setSip(sip);
        /* 196 */
        pb.setSport(port);
        /* 197 */
        pb.setDip(dnsip);
        /* 198 */
        pb.setDport(53);
        /* 199 */
        pb.setHost(host);
        /* 200 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 201 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 202 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 203 */
        pbdata.setSkyeyeDns(pb);
        /* 204 */
        pbdata.setMessageType(3);
        /* 205 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /*     */
        /* 207 */
        logs.add(data.toByteArray());
        /*     */
        /*     */
        /* 210 */
        pb.setDnsType(1);
        /* 211 */
        pb.setSip(dnsip);
        /* 212 */
        pb.setSport(53);
        /* 213 */
        pb.setDip(sip);
        /* 214 */
        pb.setDport(port);
        /* 215 */
        pb.setReplyCode(NetItemUtil.getDnsReplyCode());
        /* 216 */
        pb.setCount(NetItemUtil.getDnsCount());
        /* 217 */
        pb.addAddr(NetItemUtil.getOuterIp());
        /* 218 */
        pbdata.setSkyeyeDns(pb);
        /* 219 */
        pbdata.setMessageType(3);
        /* 220 */
        Sensor.NgfwSensor.SENSOR_LOG data1 = pbdata.build();
        /*     */
        /* 222 */
        logs.add(data1.toByteArray());
        /* 223 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getTcpflowLog() {
        /* 227 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 228 */
        Sensor.NgfwSensor.TCPFLOW.Builder pb = Sensor.NgfwSensor.TCPFLOW.newBuilder();
        /* 229 */
        Map<String, Integer> pP = getProtoPort("tcpflow");
        /* 230 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 231 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 232 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 233 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 234 */
        pb.setStatus("fin");
        /* 235 */
        pb.setStime(Util.TimeUtil.getTimeMs());
        /* 236 */
        pb.setDtime(Util.TimeUtil.getTimeMs());
        /* 237 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 238 */
        pb.setSport(NetItemUtil.getPort());
        /* 239 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 240 */
        pb.setDport(port);
        /* 241 */
        pb.setProto(proto);
        /* 242 */
        pb.setUpPayload(NetItemUtil.getTcpUpload());
        /* 243 */
        pb.setDownPayload(NetItemUtil.getTcpDownload());
        /* 244 */
        pb.setUplinkPkts(1);
        /* 245 */
        pb.setDownlinkPkts(2);
        /* 246 */
        pb.setUplinkLength(pb.getUpPayload().length());
        /* 247 */
        pb.setDownlinkLength(pb.getDownPayload().length());
        /* 248 */
        pb.setClientOs("Windows");
        /* 249 */
        pb.setServerOs("Linux 3.x");
        /* 250 */
        pb.setSrcMac("00:50:56:9c:4a:cf");
        /* 251 */
        pb.setDstMac("28:51:32:10:5e:4e");
        /* 252 */
        pb.setSummary("2;0;1460;1460");
        /* 253 */
        pb.setAppType(16);
        /* 254 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 255 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 256 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 257 */
        pbdata.setSkyeyeTcpflow(pb);
        /* 258 */
        pbdata.setMessageType(2);
        /* 259 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 260 */
        logs.add(data.toByteArray());
        /* 261 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getUdpflowLog() {
        /* 265 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 266 */
        Sensor.NgfwSensor.UDPFLOW.Builder pb = Sensor.NgfwSensor.UDPFLOW.newBuilder();
        /* 267 */
        Map<String, Integer> pP = getProtoPort("udpflow");
        /* 268 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 269 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 270 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 271 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 272 */
        pb.setStime(Util.TimeUtil.getTimeMs());
        /* 273 */
        pb.setDtime(Util.TimeUtil.getTimeMs());
        /* 274 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 275 */
        pb.setSport(NetItemUtil.getPort());
        /* 276 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 277 */
        pb.setDport(port);
        /* 278 */
        pb.setProto(proto);
        /* 279 */
        pb.setUpPayload(NetItemUtil.getTcpUpload());
        /* 280 */
        pb.setDownPayload(NetItemUtil.getTcpDownload());
        /* 281 */
        pb.setUplinkPkts(1);
        /* 282 */
        pb.setDownlinkPkts(1);
        /* 283 */
        pb.setUplinkLength(pb.getUpPayload().length());
        /* 284 */
        pb.setDownlinkLength(pb.getDownPayload().length());
        /* 285 */
        pb.setSrcMac("00:51:56:9c:4a:cf");
        /* 286 */
        pb.setDstMac("28:21:32:10:5e:4e");
        /* 287 */
        pb.setAppType(NetItemUtil.getNumber(200));
        /* 288 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 289 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 290 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 291 */
        pbdata.setSkyeyeUdpflow(pb);
        /* 292 */
        pbdata.setMessageType(16);
        /* 293 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 294 */
        logs.add(data.toByteArray());
        /* 295 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getTelnetLog() {
        /* 299 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 300 */
        Sensor.NgfwSensor.TELNET_CMD.Builder pb = Sensor.NgfwSensor.TELNET_CMD.newBuilder();
        /* 301 */
        Map<String, Integer> pP = getProtoPort("telnet");
        /* 302 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 303 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 304 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 305 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 306 */
        pb.setProto("telnet");
        /* 307 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 308 */
        pb.setSport(NetItemUtil.getPort());
        /* 309 */
        pb.setDip(NetItemUtil.getInnerIp());
        /* 310 */
        pb.setDport(port);
        /* 311 */
        pb.setUser("test");
        /* 312 */
        pb.setCmd(NetItemUtil.getFtpCmd());
        /* 313 */
        pb.setRet("hasdfij hahahahah");
        /* 314 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 315 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 316 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 317 */
        pbdata.setSkyeyeTelnetcmd(pb);
        /* 318 */
        pbdata.setMessageType(22);
        /* 319 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 320 */
        logs.add(data.toByteArray());
        /* 321 */
        return logs;
        /*     */
    }

    /*     */
    public List<byte[]> getIocLog() {
        /* 299 */
        List<byte[]> logs = new java.util.ArrayList();
        Random r = new Random(1);
        /* 300 */
        Sensor.NgfwSensor.IOC_DOLOG.Builder pb = Sensor.NgfwSensor.IOC_DOLOG.newBuilder();
        /* 301 */
        Map<String, Integer> pP = getProtoPort("telnet");
//        /* 302 */     String proto = pP.keySet().toArray()[0].toString();
//        /* 303 */     int port = ((Integer)pP.get(proto)).intValue();

        /* 304 */
        pb.setAccessTime(System.currentTimeMillis());
        pb.setTid(r.nextLong());
        pb.setType(r.nextInt(10) + "");
        pb.setRuleDesc("ioc描述");
        pb.setOffenceType(r.nextInt(10) + "");
        pb.setOffenceValue("ioc数据");
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setDip(NetItemUtil.getInnerIp());
        pb.setSeverity(r.nextInt(10));
        pb.setSerialNum("1123332323232323");
        pb.setRuleState("running");
        pb.setIocType(r.nextInt(10) + "");
        pb.setIocValue("iocdd");
        pb.setNid(r.nextInt(1000) + "");
        pb.setEtime(Util.TimeUtil.getTimeMs());
        pb.setMaliciousFamily("dfasdfasdf");
        pb.setMaliciousType(r.nextInt(10) + "");
        pb.setKillChain("killchain");
        pb.setConfidence("confidence");
        pb.setCampaign("猎获团队");
        pb.setTargeted(r.nextBoolean());
        pb.setPlatform(10, "诺亚平台");
        pb.setCurrentStatus(r.nextInt(10) + "");
        pb.setPacketData("packedata");
        pb.setIocSource(r.nextInt(2));
        pb.setSport(NetItemUtil.getPort());
        pb.setDport(NetItemUtil.getPort());
        pb.setDnsType(r.nextInt(2));
        pb.setProto("proto");
        pb.setFilename("filename");
        pb.setFileMd5(DigestUtils.md5Hex("DFASDFADSFASDF"));
        pb.setDesc("描述");
        pb.setFileDirection(r.nextInt(2));
        pb.setHost(NetItemUtil.getHost());
        pb.setUri(NetItemUtil.getUri());
        pb.setDnsArecord("11103");
        pb.setTproto("proto");
        pb.setFileContent("dfdfasdfadf");
        pb.setAttackIp(NetItemUtil.getOuterIp());
        pb.setVictimIp(NetItemUtil.getOuterIp());
        pb.setAttackType(null);
        pb.setVendorId("dfadsfadsfdasfasdfadsf");
        pb.setDeviceIp(NetItemUtil.getOuterIp());
        pb.setMplsLabel("mplabe");
        pb.setSessId("1");
        pb.setAttackTypeId(r.nextInt(1000));
        pb.setVniId(r.nextInt(1000));
        pb.setGreKey(r.nextInt(1000));
        pb.setVlanId(r.nextInt(1000));
        pb.setMetadataId(r.nextInt(1000) + "");
        pb.setAttackResult("1");
        pb.setSrcMac("dfdfdsfdddfd");
        pb.setDstMac("ddfdfdfdsfasf");
        /* 316 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 317 */
        pbdata.setSkyeyeIoc(pb);
        /* 318 */
        pbdata.setMessageType(23);
        /* 319 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 320 */
        logs.add(data.toByteArray());
        /* 321 */
        return logs;
        /*     */
    }

    public List<byte[]> getAttackLog() {
        List<byte[]> logs = new ArrayList();

        LogTypeCom logTypeCom = new LogTypeCom();
        JSONObject json = LogTypeCom.getWebAttack();
        NgfwSensor.IDS_DOLOG.Builder pb = NgfwSensor.IDS_DOLOG.newBuilder();
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        try {
            pb.setRuleId(Integer.parseInt(json.get("rule_id").toString()));
        } catch (Exception e) {
            pb.setRuleId(1);
        }
        try {
            pb.setRuleName(json.get("rule_name").toString());
        } catch (Exception e) {
            pb.setRuleName("SSH暴力破解攻击");
        }
        int i;
        try {
            pb.setPacketSize(1000);
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setPacketData(ByteString.copyFrom("测试数据，暴力攻击".getBytes()));
        } catch (Exception e) {
            i = 0;
        }

        pb.setSip(NetItemUtil.getOuterIp());
        pb.setDip(NetItemUtil.getInnerIp());

        pb.setSport(NetItemUtil.getPort());
        try {
            pb.setDport(Integer.parseInt(json.get("dport").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAppid(10);
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setProtocolId(20);
        } catch (Exception e) {
            i = 0;
        }
        pb.setDescription(ByteString.copyFrom("参考漏洞描述".getBytes()));
        pb.setWriteDate(TimeUtil.getTimeStamps());
        try {
            pb.setSeverity(Integer.parseInt(json.get("severity").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAttackMethod("暴力破解");
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setDetailInfo(json.get("detail_info").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAffectedSystem("linux");
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVictimType(json.get("victim_type").toString());
        } catch (Exception e) {
            pb.setVictimType("sip");
        }
        pb.setSigId(5);
        try {
            pb.setConfidence(Integer.parseInt(json.get("confidence").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setWebrulesTag(json.get("webrules_tag").toString());
        } catch (Exception e) {
            i = 0;
        }
        pb.setAttackFlag("attack");
        pb.setAttacker(pb.getSip());
        pb.setVictim(pb.getDip());
        try {
            pb.setRuleVersion(json.get("rule_version").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setCnnvdId(json.get("cnnvd_id").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setKillChain(json.get("kill_chain").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAttackResult("1");
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAttackType(ByteString.copyFrom(json.get("attack_type").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        try {
            pb.setAttackTypeId(Integer.parseInt(json.get("attack_type_id").toString()));
        } catch (Exception e) {
            i = 0;
        }
        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeIds(pb);
        pbdata.setMessageType(19);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getWebattackLog() {
        List<byte[]> logs = new ArrayList();
        LogTypeCom logTypeCom = new LogTypeCom();
        JSONObject json = LogTypeCom.getWebAttack();

        NgfwSensor.WEBATTACK_DOLOG.Builder pb = NgfwSensor.WEBATTACK_DOLOG.newBuilder();
        try {
            pb.setRuleId(Integer.parseInt(json.get("rule_id").toString()));
        } catch (Exception e) {
            pb.setRuleId(5151);
        }
        try {
            pb.setRuleName(json.get("rule_name").toString());
        } catch (Exception e) {
            pb.setRuleName("dirbuster目录爆破");
        }
        try {
            pb.setDologCount(Integer.parseInt(json.get("dolog_count").toString()));
        } catch (Exception e) {
            pb.setDologCount(1);
        }
        try {
            pb.setSeverity(Integer.parseInt(json.get("severity").toString()));
        } catch (Exception e) {
            pb.setSeverity(2);
        }
        try {
            pb.setRuleVersion(Integer.parseInt(json.get("rule_version").toString()));
        } catch (Exception e) {
            pb.setRuleVersion(1);
        }
        pb.setSip(NetItemUtil.getOuterIp());
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setSport(NetItemUtil.getPort());
        int i;
        try {
            pb.setDport(Integer.parseInt(json.get("dport").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setMethod(json.get("method").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setHost(json.get("host").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setHost(json.get("host").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setUri(ByteString.copyFrom(json.get("uri").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setFileName(json.get("file_name").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setReferer(json.get("referer").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAgent(json.get("agent").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setCookie(json.get("cookie").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setParameter(json.get("parameter").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setReqHeader(ByteString.copyFrom(json.get("req_header").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setReqBody(json.get("req_body").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspStatus(Integer.parseInt(json.get("rsp_status").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspContentLength(Integer.parseInt(json.get("rsp_content_length").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspContentType(json.get("rsp_content_type").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspHeader(ByteString.copyFrom(json.get("rsp_header").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspBody(ByteString.copyFrom(json.get("rsp_body").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRspBodyLen(Integer.parseInt(json.get("rsp_body_len").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVictimType(json.get("victim_type").toString());
        } catch (Exception e) {
            pb.setVictimType("sip");
        }
        pb.setAttackFlag("attack");
        pb.setAttacker(pb.getSip());
        pb.setVictim(pb.getDip());
        pb.setWriteDate(TimeUtil.getTimeStamps());
        try {
            pb.setAttackType(ByteString.copyFrom(json.get("attack_type").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setConfidence(Integer.parseInt(json.get("confidence").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setDetailInfo(json.get("detail_info").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setSolution(json.get("solution").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVulnDesc(json.get("vuln_desc").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVulnHarm(json.get("vuln_harm").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVulnName(json.get("vuln_name").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setVulnType(json.get("vuln_type").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setWebrulesTag(json.get("webrules_tag").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setPublicDate(json.get("public_date").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setCodeLanguage(json.get("code_language").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setSiteApp(json.get("site_app").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setKillChain(json.get("kill_chain").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setAttackResult("1");
        } catch (Exception e) {
            i = 0;
        }
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        try {
            pb.setAttackTypeId(Integer.parseInt(json.get("attack_type_id").toString()));
        } catch (Exception e) {
            i = 0;
        }
        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeWebattack(pb);
        pbdata.setMessageType(18);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;

    }

    /*     */
    /*     */
    public List<byte[]> getAbnormalLog() {
        /* 325 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 326 */
        Sensor.NgfwSensor.ABNORMAL_PKT.Builder pb = Sensor.NgfwSensor.ABNORMAL_PKT.newBuilder();
        /* 327 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 328 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 329 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 330 */
        pb.setSport(NetItemUtil.getPort());
        /* 331 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 332 */
        pb.setDport(NetItemUtil.getAbnormalPort());
        /* 333 */
        pb.setData(NetItemUtil.getTcpDownload());
        /* 334 */
        pb.setDatalen(pb.getData().length());
        /* 335 */
        pb.setType(NetItemUtil.getAbnoramalType());
        /* 336 */
        pb.setInfo("msg_type:11,msg_code:1,src_mac:50:da:00:f1:e3:e6,dst_mac:9c:06:1b:00:63:d6");
        /* 337 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 338 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 339 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 340 */
        pbdata.setSkyeyeAbnormal(pb);
        /* 341 */
        pbdata.setMessageType(20);
        /* 342 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 343 */
        logs.add(data.toByteArray());
        /* 344 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getFileLog() {
        /* 348 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 349 */
        Sensor.NgfwSensor.FILE_BEHAVIOR.Builder pb = Sensor.NgfwSensor.FILE_BEHAVIOR.newBuilder();
        /* 350 */
        Map<String, Integer> pP = getProtoPort("file");
        /* 351 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 352 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 353 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 354 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 355 */
        pb.setProto(proto);
        /* 356 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 357 */
        pb.setSport(NetItemUtil.getPort());
        /* 358 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 359 */
        pb.setDport(port);
        /* 360 */
        pb.setUri(com.google.protobuf.ByteString.copyFrom(NetItemUtil.getUri().getBytes()));
        /* 361 */
        pb.setHost(NetItemUtil.getHost());
        /* 362 */
        pb.setStatus("200");
        /* 363 */
        pb.setFileDir(1);
        /* 364 */
        pb.setFileMd5(NetItemUtil.getMd5());
        /* 365 */
        pb.setFilename(NetItemUtil.getFilename());
        /* 366 */
        pb.setMimeType("application/octet-stream");
        /* 367 */
        pb.setMethod("POST");
        /* 368 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 369 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 370 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 371 */
        pbdata.setSkyeyeFile(pb);
        /* 372 */
        pbdata.setMessageType(5);
        /* 373 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 374 */
        logs.add(data.toByteArray());
        /* 375 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getLoginLog() {
        /* 379 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 380 */
        Sensor.NgfwSensor.LOGIN.Builder pb = Sensor.NgfwSensor.LOGIN.newBuilder();
        /* 381 */
        Map<String, Integer> pP = getProtoPort("login");
        /* 382 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 383 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 384 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 385 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 386 */
        pb.setProto(proto);
        /* 387 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 388 */
        pb.setSport(NetItemUtil.getPort());
        /* 389 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 390 */
        pb.setDport(port);
        /* 391 */
        if (!proto.equals("ssh")) {
            /* 392 */
            pb.setUser(NetItemUtil.getUsername());
            /* 393 */
            pb.setPasswd(NetItemUtil.getMd5());
            /*     */
        }
        /*     */
        else {
            /* 396 */
            pb.setUser("");
            /* 397 */
            pb.setPasswd("");
            /*     */
        }
        /* 399 */
        if ((proto.equals("mysql")) || (proto.equals("db2")) || (proto.equals("redis")) || (proto.equals("postgresql"))) {
            /* 400 */
            pb.setDbType(proto);
            /*     */
        }
        /* 402 */
        pb.setInfo(NetItemUtil.getTcpDownload());
        /* 403 */
        pb.setNormalRet(NetItemUtil.getRet());
        /* 404 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 405 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 406 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 407 */
        pbdata.setSkyeyeLogin(pb);
        /* 408 */
        pbdata.setMessageType(7);
        /* 409 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 410 */
        logs.add(data.toByteArray());
        /* 411 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getMailLog() {
        /* 415 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 416 */
        Sensor.NgfwSensor.MAIL_BEHAVIOR.Builder pb = Sensor.NgfwSensor.MAIL_BEHAVIOR.newBuilder();
        /* 417 */
        Map<String, Integer> pP = getProtoPort("mail");
        /* 418 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 419 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 420 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 421 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 422 */
        pb.setProto(proto);
        /* 423 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 424 */
        pb.setSport(NetItemUtil.getPort());
        /* 425 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 426 */
        pb.setDport(port);
        /* 427 */
        pb.setTime(Util.TimeUtil.getTime());
        /* 428 */
        pb.setMailFrom(NetItemUtil.getMailAddr());
        /* 429 */
        pb.setTo(NetItemUtil.getMailAddr());
        /* 430 */
        pb.setCc(NetItemUtil.getMailAddr());
        /* 431 */
        pb.setSubject(NetItemUtil.getTcpUpload());
        /* 432 */
        pb.setPlain(NetItemUtil.getMailPlain());
        /* 433 */
        pb.addReceived(pb.getTo());
        /* 434 */
        pb.addReceived(pb.getCc());
        /* 435 */
        Sensor.NgfwSensor.MAIL_BEHAVIOR.mail_attachment.Builder ma = Sensor.NgfwSensor.MAIL_BEHAVIOR.mail_attachment.newBuilder();
        /* 436 */
        ma.setAttachMd5(NetItemUtil.getMd5());
        /* 437 */
        ma.setMimeType("application/octet-stream");
        /* 438 */
        ma.setName(NetItemUtil.getFilename());
        /* 439 */
        pb.addAttachment(ma);
        /* 440 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 441 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 442 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 443 */
        pbdata.setSkyeyeMail(pb);
        /* 444 */
        pbdata.setMessageType(6);
        /* 445 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 446 */
        logs.add(data.toByteArray());
        /* 447 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getDbLog() {
        /* 451 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 452 */
        Sensor.NgfwSensor.DB.Builder pb = Sensor.NgfwSensor.DB.newBuilder();
        /* 453 */
        Map<String, Integer> pP = getProtoPort("db");
        /* 454 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 455 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 456 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 457 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 458 */
        pb.setProto(proto);
        /* 459 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 460 */
        pb.setSport(NetItemUtil.getPort());
        /* 461 */
        pb.setDip(NetItemUtil.getInnerIp());
        /* 462 */
        pb.setDport(port);
        /* 463 */
        pb.setUser(NetItemUtil.getUsername());
        /* 464 */
        pb.setDbName(NetItemUtil.getUsername());
        /* 465 */
        pb.setDbType(proto);
        /* 466 */
        pb.setVersion("1.0.0.123");
        /* 467 */
        pb.setSqlInfo(NetItemUtil.getDbSql());
        /* 468 */
        pb.setRetCode(NetItemUtil.getRet());
        /* 469 */
        pb.setNormalRet(pb.getRetCode());
        /* 470 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 471 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 472 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 473 */
        pbdata.setSkyeyeSql(pb);
        /* 474 */
        pbdata.setMessageType(8);
        /* 475 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 476 */
        logs.add(data.toByteArray());
        /* 477 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getLdapLog() {
        /* 481 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 482 */
        Sensor.NgfwSensor.LDAP.Builder pb = Sensor.NgfwSensor.LDAP.newBuilder();
        /* 483 */
        Map<String, Integer> pP = getProtoPort("ldap");
        /* 484 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 485 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 486 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 487 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 488 */
        pb.setProto(proto);
        /* 489 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 490 */
        pb.setSport(NetItemUtil.getPort());
        /* 491 */
        pb.setDip(NetItemUtil.getInnerIp());
        /* 492 */
        pb.setDport(port);
        /* 493 */
        pb.setUser(NetItemUtil.getUsername());
        /* 494 */
        pb.setVersion("3");
        /* 495 */
        pb.setOp(NetItemUtil.getLdapOp());
        /* 496 */
        pb.setInfo(NetItemUtil.getLdapInfo());
        /* 497 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 498 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 499 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 500 */
        pbdata.setSkyeyeLdap(pb);
        /* 501 */
        pbdata.setMessageType(10);
        /* 502 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 503 */
        logs.add(data.toByteArray());
        /* 504 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getSslLog() {
        /* 508 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 509 */
        Sensor.NgfwSensor.SSL.Builder pb = Sensor.NgfwSensor.SSL.newBuilder();
        /* 510 */
        Map<String, Integer> pP = getProtoPort("ssl");
        /* 511 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 512 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 513 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 514 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 515 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 516 */
        pb.setSport(NetItemUtil.getPort());
        /* 517 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 518 */
        pb.setDport(port);
        /* 519 */
        pb.setVersion("TLS 1.2");
        /* 520 */
        pb.setServerName("mmddsbiz.qpisssc.cn");
        /* 521 */
        pb.setSessionId(NetItemUtil.getString(64));
        /* 522 */
        pb.setUserName("/C=CN/ST=guangdong/L=shenzhen/O=Shenzhen Tencent Computer Systems Company Limited/CN=*.cloud.tencent.com");
        /* 523 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 524 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 525 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 526 */
        pbdata.setSkyeyeSsl(pb);
        /* 527 */
        pbdata.setMessageType(11);
        /* 528 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 529 */
        logs.add(data.toByteArray());
        /* 530 */
        return logs;
        /*     */
    }

    /*     */
    /*     */
    public List<byte[]> getWebLog() {
        /* 534 */
        List<byte[]> logs = new java.util.ArrayList();
        /* 535 */
        Sensor.NgfwSensor.WEBLOG.Builder pb = Sensor.NgfwSensor.WEBLOG.newBuilder();
        /* 536 */
        Map<String, Integer> pP = getProtoPort("file");
        /* 537 */
        String proto = pP.keySet().toArray()[0].toString();
        /* 538 */
        int port = ((Integer) pP.get(proto)).intValue();
        /* 539 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 540 */
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        /* 541 */
        pb.setSip(NetItemUtil.getInnerIp());
        /* 542 */
        pb.setSport(NetItemUtil.getPort());
        /* 543 */
        pb.setDip(NetItemUtil.getOuterIp());
        /* 544 */
        pb.setDport(port);
        /* 545 */
        pb.setUri(com.google.protobuf.ByteString.copyFrom(NetItemUtil.getUri().getBytes()));
        /* 546 */
        pb.setHost(NetItemUtil.getHost());
        /* 547 */
        pb.setStatus(200);
        /* 548 */
        pb.setUrlcategory(12);
        /* 549 */
        pb.setAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        /* 550 */
        pb.setData(com.google.protobuf.ByteString.copyFrom(NetItemUtil.getTcpDownload().getBytes()));
        /* 551 */
        pb.setAcceptLanguage("zh-CN,zh;q=0.8");
        /* 552 */
        pb.setContentType("");
        /* 553 */
        pb.setMethod(NetItemUtil.getHttpMethod());
        /* 554 */
        pb.setMethod("POST");
        /* 555 */
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        /* 556 */
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        /* 557 */
        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        /* 558 */
        pbdata.setSkyeyeWeblog(pb);
        /* 559 */
        pbdata.setMessageType(4);
        /* 560 */
        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        /* 561 */
        logs.add(data.toByteArray());
        /* 562 */
        return logs;
        /*     */
    }

    public List<byte[]> getIocLog1() {
        List<byte[]> logs = new ArrayList();
        LogTypeCom logTypeCom = new LogTypeCom();
        JSONObject json = LogTypeCom.getIoc();

        NgfwSensor.IOC_DOLOG.Builder pb = NgfwSensor.IOC_DOLOG.newBuilder();
        pb.setAccessTime(System.currentTimeMillis());
        try {
            pb.setTid(Long.parseLong(json.get("tid").toString()));
        } catch (Exception e) {
            pb.setTid(4L);
        }
        pb.setType("attack");
        try {
            pb.setRuleDesc(json.get("rule_desc").toString());
        } catch (Exception e) {
            pb.setRuleDesc("MsraMiner Botnet C&C 活动事件");
        }
        try {
            pb.setOffenceType(json.get("offence_type").toString());
        } catch (Exception e) {
            pb.setOffenceType("sip");
        }

        pb.setSip(NetItemUtil.getOuterIp());
        pb.setDip(NetItemUtil.getInnerIp());
        try {
            pb.setOffenceValue(json.get("offence_value").toString());
        } catch (Exception e) {
            pb.setOffenceValue(pb.getSip());
        }
        pb.setSport(NetItemUtil.getPort());
        try {
            pb.setDport(Integer.parseInt(json.get("dport").toString()));
        } catch (Exception e) {
            pb.setDport(NetItemUtil.getPort());
        }
        int i;
        try {
            pb.setSeverity(Integer.parseInt(json.get("severity").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setRuleState(json.get("rule_state").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setIocType(json.get("ioc_type").toString());
        } catch (Exception e) {
            pb.setIocType("host");
        }
        try {
            pb.setIocValue(json.get("ioc_value").toString());
        } catch (Exception e) {
            pb.setIocValue("rer.njaavfxcgk3.club");
        }
        try {
            pb.setNid(json.get("nid").toString());
        } catch (Exception e) {
            pb.setNid("5782621921543800265");
        }
        try {
            pb.setEtime(json.get("etime").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setMaliciousType(json.get("malicious_type").toString());
        } catch (Exception e) {
            pb.setMaliciousType("僵尸网络");
        }
        try {
            pb.setKillChain(json.get("kill_chain").toString());
        } catch (Exception e) {
            pb.setKillChain("c2");
        }
        try {
            pb.setConfidence(json.get("confidence").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setMaliciousFamily(json.get("malicious_family").toString());
        } catch (Exception e) {
            pb.setMaliciousFamily("MsraMiner");
        }
        try {
            pb.setCampaign(json.get("campaign").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setTargeted(Boolean.valueOf(json.get("targeted").toString()).booleanValue());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.addTag(json.get("tag").toString());
        } catch (Exception e) {
            pb.addTag("");
        }
        try {
            pb.addPlatform(json.get("platform").toString());
        } catch (Exception e) {
            pb.addPlatform("");
        }
        try {
            pb.setCurrentStatus(json.get("current_status").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setPacketData(json.get("packet_data").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setIocSource(Integer.parseInt(json.get("ioc_source").toString()));
        } catch (Exception e) {
            pb.setIocSource(0);
        }
        try {
            pb.setProto(json.get("proto").toString());
        } catch (Exception e) {
            pb.setProto("UDP");
        }
        try {
            pb.setDnsType(Integer.parseInt(json.get("dns_type").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setFilename(json.get("filename").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setFileMd5(json.get("file_md5").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setDesc(json.get("desc").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setFileDirection(Integer.parseInt(json.get("file_direction").toString()));
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setHost(json.get("host").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setUri(json.get("uri").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setDnsArecord(json.get("dns_arecord").toString());
        } catch (Exception e) {
            i = 0;
        }
        try {
            pb.setTproto(json.get("tproto").toString());
        } catch (Exception e) {
            pb.setTproto("UDP");
        }
        try {
            pb.setFileContent(json.get("file_content").toString());
        } catch (Exception e) {
            i = 0;
        }
        pb.setAttackIp(pb.getSip());
        pb.setVictimIp(pb.getDip());
        try {
            pb.setAttackType(ByteString.copyFrom(json.get("attack_type").toString().getBytes()));
        } catch (Exception e) {
            i = 0;
        }
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        try {
            pb.setAttackTypeId(Integer.parseInt(json.get("attack_type_id").toString()));
        } catch (Exception e) {
            i = 0;
        }
        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeIoc(pb);
        pbdata.setMessageType(23);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getWebShell() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.WEBSHELL_DOLOG.Builder pb = Sensor.NgfwSensor.WEBSHELL_DOLOG.newBuilder();

        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setRuleId(1);
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSport(NetItemUtil.getPort());
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDport(NetItemUtil.getPort());
        pb.setHost(NetItemUtil.getHost());
        pb.setUrl(ByteString.copyFrom("url".getBytes()));
        pb.setFileMd5(NetItemUtil.getMd5());
        pb.setFile(ByteString.copyFrom("file".getBytes()));
        pb.setAttackType(ByteString.copyFromUtf8(NetItemUtil.getAttackType()));
        pb.setFileDir("filedir");
        pb.setVictimType("victim_type");
        pb.setAttackFlag("attack");
        pb.setAttacker("attacker");
        pb.setVictim("victim");
        pb.setWriteDate(TimeUtil.getTimeStamps());
        pb.setSeverity(1);
        pb.setConfidence(1);
        pb.setDetailInfo("detail_info");
        pb.setWebrulesTag("webrules_tag");
        pb.setAttackDesc("attack_desc");
        pb.setAttackHarm("attack_harm");
        pb.setRuleName("rule_name");
        pb.setKillChain("kill_chain");
        pb.setAttackResult("1");
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel("mpls_label");
        pb.setSessId("sess_id");
        pb.setAttackTypeId(1);


        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeWebshell(pb);
        pbdata.setMessageType(30);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getPROPERTY() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.PROPERTY.Builder pb = Sensor.NgfwSensor.PROPERTY.newBuilder();

        pb.setToken("token");
        Date date = new Date();
        pb.setTimestamp(date.getTime()+"");
        pb.setSource("source");
        pb.setAssets("assets");
        pb.setSessId("sessid");
        pb.setSensorName("sensor_name");
        pb.setSensorModel("sensor_model");
        pb.setSensorVendor("sensor_vendor");
        pb.setSensorCpe("sensor_cpe");
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));


        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeProperty(pb);
        pbdata.setMessageType(31);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getICMP() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.ICMP.Builder pb = Sensor.NgfwSensor.ICMP.newBuilder();

        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSipv6("sipv6");
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDipv6("dipv6");
        pb.setProto("proto");
        pb.setSrcMac("00:50:56:9c:4a:cf");
        pb.setDstMac("28:51:32:10:5e:4e");
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel("mpls_label");
        pb.setSessId("sessid");
        pb.setReqType(1);
        pb.setReqCode(1);
        pb.setReqData("reqdata");
        pb.setResType(1);
        pb.setResCode(1);
        pb.setResData("resdata");
    //    pb.addUserDefine(NgfwSensor.UDEFINE_MSG.newBuilder().build());
//        pb.setUserDefine(1, NgfwSensor.UDEFINE_MSG.newBuilder().build());
        pb.setVniId(1);
        pb.setGreKey(1);
        pb.setVlanId(1);

        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeIcmp(pb);
        pbdata.setMessageType(32);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getVIRUSES() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.VIRUSES.Builder pb = Sensor.NgfwSensor.VIRUSES.newBuilder();

        pb.setAccessTime(Math.toIntExact((System.currentTimeMillis()/1000)));
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSipv6("sipv6");
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDipv6("dipv6");
        pb.setSport(NetItemUtil.getPort());
        pb.setDport(NetItemUtil.getPort());
        pb.setAttacker("attacker");
        pb.setVictim("victim");
        pb.setAppId(1);
        pb.setAppName("appname");
        pb.setProtol7Id(1);
        pb.setProtol7Name("proto17name");
        pb.setDirection(1);
        pb.setRuleId(1);
        pb.setRuleName("rulename");
        pb.setMaliciousType("malicious_type");
        pb.setMaliciousTypeId(1);
        pb.setMaliciousFamily("malicious_family");
        pb.setSeverity(1);
        pb.setAffectedPlatform("affected_platform");
        pb.setDesc("desc");
        pb.setConfidence(1);
        pb.setKillChain("kill_chain");
        pb.setAttackResult("1");
        pb.setFileDir("file_dir");
        pb.setFilename("filename");
        pb.setMimeType("mime_type");
        pb.setFileMd5(NetItemUtil.getMd5());
        pb.setFileSha256("filesha256");
        pb.setDetectMethod(1);
        pb.setBehaviourLabel("behaviour_label");
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel(1);
        pb.setSessId("sessid");
   //     pb.addUserDefine( NgfwSensor.UDEFINE_MSG.newBuilder().build());
     //   pb.setUserDefine(1, NgfwSensor.UDEFINE_MSG.newBuilder().build());
        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setVniId(1);
        pb.setGreKey(1);
        pb.setVlanId(1);
        pb.setMetadataId("metadata_id");
        pb.setSrcMac("00:50:56:9c:4a:cf");
        pb.setDstMac("28:51:32:10:5e:4e");

        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeViruses(pb);
        pbdata.setMessageType(33);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getDHCP() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.DHCP.Builder pb = Sensor.NgfwSensor.DHCP.newBuilder();

        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSipv6("sipv6");
        pb.setSport(NetItemUtil.getPort());
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDipv6("dipv6");
        pb.setDport(NetItemUtil.getPort());
        pb.setOp(1);
        pb.setHops(1);
        pb.setCiaddr("ciadrr");
        pb.setYiaddr("yiadrr");
        pb.setSiaddr("siadrr");
        pb.setGiaddr("giadrr");
        pb.setChaddr("chadrr");
        pb.setDHCPMessage(1);
        pb.setSubnetMask("subnet_mask");
        pb.addGateway("gateway");
    //    pb.setGateway(1, "gateway");
      //  pb.setDomainNameServer(1, "domain_name_server");
        pb.addDomainNameServer("domain_name_server");
        pb.setHostName("host_name");
        pb.setDomainName("domain_name");
        pb.setLeaseTime(1);
        pb.setServerID("server_id");
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel("mpls_label");
        pb.setSessId("sessid");
   //     pb.addUserDefine( NgfwSensor.UDEFINE_MSG.newBuilder().build());
    //    pb.setUserDefine(1, NgfwSensor.UDEFINE_MSG.newBuilder().build());
        pb.setVniId(1);
        pb.setGreKey(1);
        pb.setVlanId(1);
        pb.setSrcMac("00:50:56:9c:4a:cf");
        pb.setDstMac("28:51:32:10:5e:4e");

        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeDhcp(pb);
        pbdata.setMessageType(34);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getKERBEROS() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.KERBEROS.Builder pb = Sensor.NgfwSensor.KERBEROS.newBuilder();

        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSipv6("sipv6");
        pb.setSport(NetItemUtil.getPort());
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDipv6("dipv6");
        pb.setDport(NetItemUtil.getPort());
        pb.setMsgType(1);
        pb.setKdcOptions(1);
        pb.setClientRealm("client_realm");
        pb.setCname("cname");
        pb.setServerRealm("server_realm");
        pb.setSname("sname");
        pb.setApReqTicketRealm("ap_req_ticket_realm");
        pb.setApReqTicketSname("ap_req_ticket_sname");
        pb.setApReqTicketCipher("ap_req_ticket_cipher");
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel("mpls_label");
        pb.setSessId("sessid");
    //    pb.addUserDefine(NgfwSensor.UDEFINE_MSG.newBuilder().build());
     //   pb.setUserDefine(1, NgfwSensor.UDEFINE_MSG.newBuilder().build());
        pb.setVniId(1);
        pb.setGreKey(1);
        pb.setVlanId(1);
        pb.setSrcMac("00:50:56:9c:4a:cf");
        pb.setDstMac("28:51:32:10:5e:4e");

        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeKerberos(pb);
        pbdata.setMessageType(35);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

    public List<byte[]> getRADIUS() {
        List<byte[]> logs = new ArrayList();

        NgfwSensor.RADIUS.Builder pb = Sensor.NgfwSensor.RADIUS.newBuilder();

        pb.setSerialNum((String) this.hostinfo.get("sn"));
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        pb.setSip(NetItemUtil.getInnerIp());
        pb.setSipv6("sipv6");
        pb.setSport(NetItemUtil.getPort());
        pb.setDip(NetItemUtil.getOuterIp());
        pb.setDipv6("dipv6");
        pb.setDport(NetItemUtil.getPort());
        pb.setCode(1);
        pb.setCodeName("code_name");
        pb.setId(1);
        pb.setLen(1);
        pb.setAuthenticator("authenticator");

        NgfwSensor.RADIUS.radius_attr.Builder pb1 = Sensor.NgfwSensor.RADIUS.radius_attr.newBuilder();
        pb1.setType(1);
        pb1.setTypeName("type_name");
        pb1.setValueType(1);
        pb1.setValue("value");

        pb.addAttrs(pb1);
        pb.setVendorId((String) this.hostinfo.get("hostname"));
        pb.setDeviceIp((String) this.hostinfo.get("ip"));
        pb.setMplsLabel("mpls_label");
        pb.setSessId("sessid");
     //   pb.addUserDefine( NgfwSensor.UDEFINE_MSG.newBuilder().build());
      //  pb.setUserDefine(1, NgfwSensor.UDEFINE_MSG.newBuilder().build());
        pb.setVniId(1);
        pb.setGreKey(1);
        pb.setVlanId(1);
        pb.setSrcMac("00:50:56:9c:4a:cf");
        pb.setDstMac("28:51:32:10:5e:4e");

        NgfwSensor.SENSOR_LOG.Builder pbdata = NgfwSensor.SENSOR_LOG.newBuilder();
        pbdata.setSkyeyeRadius(pb);
        pbdata.setMessageType(36);
        NgfwSensor.SENSOR_LOG data = pbdata.build();
        logs.add(data.toByteArray());
        return logs;
    }

}


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\sendflow\LogSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */