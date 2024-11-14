package sendflow;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LogTypeCom {
    public static String BrokerList = "12ngsoc.cn:9092,25ngsoc.cn:9092,26ngsoc.cn:9092";
    public static Map<String, Integer> LogTypeSpeed = new HashMap<>();
    public static Map<String, String> Topics = new HashMap<>();
    public static List<String> LogName = new ArrayList<>();
    public static Map<String, Integer> LogTypes = new HashMap<>();
    public static double Unsettle = 0.0D;
    public static Boolean ipVersion = Boolean.valueOf(true);
    public static List<JSONObject> Attack = new ArrayList<>();
    public static List<JSONObject> WebAttack = new ArrayList<>();
    public static List<JSONObject> Ioc = new ArrayList<>();

    public static void setLogTypes()
    {
        LogTypes.put("TCPFLOW", Integer.valueOf(1));
        LogTypes.put("UDPFLOW", Integer.valueOf(12));
        LogTypes.put("FTPOP", Integer.valueOf(11));
        LogTypes.put("DNS", Integer.valueOf(2));
        LogTypes.put("TELNET", Integer.valueOf(16));
        LogTypes.put("ABNORMAL", Integer.valueOf(15));
        LogTypes.put("FILE", Integer.valueOf(4));
        LogTypes.put("LOGIN", Integer.valueOf(6));
        LogTypes.put("MAIL", Integer.valueOf(5));
        LogTypes.put("DB", Integer.valueOf(7));
        LogTypes.put("LDAP", Integer.valueOf(9));
        LogTypes.put("SSL", Integer.valueOf(10));
        LogTypes.put("WEBLOG", Integer.valueOf(3));
        LogTypes.put("IOC", Integer.valueOf(17));
        LogTypes.put("ATTACK", Integer.valueOf(8));
        LogTypes.put("WEBATTACK", Integer.valueOf(14));
    }

    public static int getLogTypes(String logNmae)
    {
        return ((Integer)LogTypes.get(logNmae)).intValue();
    }

    public static List<String> getLogName()
    {
        return LogName;
    }

    public static void setLogName(String logName)
    {
        if (!LogName.contains(logName)) {
            LogName.add(logName);
        }
    }

    public static String getTopics(String LogName)
    {
        try
        {
            return (String)Topics.get(LogName);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "";
    }

    public static void setTopics(String LogName, String Topic)
    {
        Topics.put(LogName, Topic);
    }

    public static int getLogTypeSpeed(String LogName)
    {
        try
        {
            return ((Integer)LogTypeSpeed.get(LogName)).intValue();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 10;
    }

    public static void setLogTypeSpeed(String LogName, int Speed)
    {
        LogTypeSpeed.put(LogName, Integer.valueOf(Speed));
    }

    public static void setBrokerList(String Brokers)
    {
        BrokerList = Brokers;
    }

    public static String getBrokerList()
    {
        return BrokerList;
    }

    public static void setUnsettle(double a)
    {
        Unsettle = a;
    }

    public static double getUnsettle()
    {
        return Unsettle;
    }

    public static void setAttack(JSONObject json)
    {
        Attack.add(json);
    }

    public static JSONObject getAttack()
    {
        return (JSONObject)Attack.get((int)(Math.random() * Attack.size()));
    }

    public static void setWebAttack(JSONObject json)
    {
        WebAttack.add(json);
    }

    public static JSONObject getWebAttack(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serial_num","serial_num");
        jsonObject.put("rule_id",1);
        jsonObject.put("rule_name","rule_name");
        jsonObject.put("dolog_count",1);
        jsonObject.put("severity",1);
        jsonObject.put("rule_version",1);
        jsonObject.put("sip","sip");
        jsonObject.put("dip","dip");
        jsonObject.put("sport",1);
        jsonObject.put("dport",1);
        jsonObject.put("method","method");
        jsonObject.put("host","host");
        jsonObject.put("uri","uri");
        jsonObject.put("file_name","file_name");
        jsonObject.put("referer","referer");
        jsonObject.put("agent","agent");
        jsonObject.put("cookie","cookie");
        jsonObject.put("parameter","parameter");
        jsonObject.put("req_header","req_header");
        jsonObject.put("req_body","req_body");
        jsonObject.put("rsp_status",1);
        jsonObject.put("rsp_content_length",1);
        jsonObject.put("rsp_content_type","rsp_content_type");
        jsonObject.put("rsp_header","rsp_header");
        jsonObject.put("rsp_body","rsp_body");
        jsonObject.put("rsp_body_len",1);
        jsonObject.put("victim_type","victim_type");
        jsonObject.put("attack_flag","attack_flag");
        jsonObject.put("attacker","attacker");
        jsonObject.put("victim","victim");
        jsonObject.put("write_date",1);
        jsonObject.put("attack_type","attack_type");
        jsonObject.put("confidence",1);
        jsonObject.put("detail_info","detail_info");
        jsonObject.put("solution","solution");
        jsonObject.put("vuln_desc","vuln_desc");
        jsonObject.put("vuln_harm","vuln_harm");
        jsonObject.put("vuln_name","vuln_name");
        jsonObject.put("vuln_type","vuln_type");
        jsonObject.put("webrules_tag","webrules_tag");
        jsonObject.put("public_date","public_date");
        jsonObject.put("code_language","code_language");
        jsonObject.put("site_app","site_app");
        jsonObject.put("kill_chain","kill_chain");
        jsonObject.put("attack_result","attack_result");
        jsonObject.put("vendor_id","vendor_id");
        jsonObject.put("device_ip","device_ip");
        jsonObject.put("mpls_label","mpls_label");
        jsonObject.put("sess_id","sess_id");
        jsonObject.put("attack_type_id",1);
        jsonObject.put("weak_passwd","weak_passwd");
        jsonObject.put("xff","xff");
        jsonObject.put("cve_id","cve_id");
        jsonObject.put("cnnvd_id","cnnvd_id");
        jsonObject.put("user_define","user_define");
        jsonObject.put("vni_id","vni_id");
        jsonObject.put("gre_key","gre_key");
        jsonObject.put("vlan_id","vlan_id");
        jsonObject.put("metadata_id","metadata_id");
        jsonObject.put("src_mac","src_mac");
        jsonObject.put("dst_mac","dst_mac");
        return jsonObject;
    }

    public static void setIoc(JSONObject json)
    {
        Ioc.add(json);
    }

    public static JSONObject getIoc()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tid", 1L);
        jsonObject.put("rule_desc", "rule_desc");
        jsonObject.put("offence_type", "offence_type");
        jsonObject.put("offence_value", "offence_value");
        jsonObject.put("dport", "dport");
        jsonObject.put("severity", "severity");
        jsonObject.put("rule_state", "rule_state");
        jsonObject.put("ioc_type", "ioc_type");
        jsonObject.put("ioc_value", "ioc_value");
        jsonObject.put("nid", "nid");
        jsonObject.put("etime", "2021-01-22 12:33:40");
        jsonObject.put("malicious_type", "malicious_type");
        jsonObject.put("kill_chain", "kill_chain");
        jsonObject.put("confidence", "confidence");
        jsonObject.put("malicious_family", "malicious_family");
        jsonObject.put("tag", "tag");
        jsonObject.put("platform", "platform");
        jsonObject.put("current_status", "current_status");
        jsonObject.put("packet_data", "packet_data");
        jsonObject.put("ioc_source", "ioc_source");
        jsonObject.put("proto", "proto");
        jsonObject.put("dns_type", 1);
        jsonObject.put("filename", "filename");
        jsonObject.put("file_md5", "file_md5");
        jsonObject.put("desc", "desc");
        jsonObject.put("file_direction", 1);
        jsonObject.put("host", "host");
        jsonObject.put("uri", "uri");
        jsonObject.put("dns_arecord", "dns_arecord");
        jsonObject.put("tproto", "tproto");
        jsonObject.put("file_content", "file_content");
        jsonObject.put("attack_type", "attack_type");
        jsonObject.put("attack_type_id", "attack_type_id");
        return jsonObject;
    }

    public static void setIpVersion(String ipv)
    {
        if (ipv.equals("ipv4")) {
            ipVersion = Boolean.valueOf(true);
        } else {
            ipVersion = Boolean.valueOf(false);
        }
    }

    public static Boolean getIpVersion()
    {
        return ipVersion;
    }
}
