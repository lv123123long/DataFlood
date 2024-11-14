package sendflow;

import Util.NetItemUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import com.example.tutorial.AddressBookProtos;
/**
 * @author: liyu04
 * @date: 2021/10/14
 * @version: V1.0
 * @Description:
 */
public class LogSourceFromFile extends LogSource
{
    private String dataFileDir;
    private LineIterator lineIterator;
    private Iterator<File> fileIterator;
//    private List<File> dataDirs = new ArrayList<>();
    private LogSourceFromFile(String dataFileDir) throws IOException
    {
        super(1);
        this.dataFileDir = dataFileDir;
        initFileReader();
    }

    public static LogSourceFromFile logSourceFromFile(String dataFileDir) throws IOException
    {
        return new LogSourceFromFile(dataFileDir);
    }

    private void initFileReader() throws IOException
    {
        if (null == fileIterator) {
            String[] dirs = dataFileDir.split(",");
            List<File> dataDirs = new ArrayList<>();
            for (String dir : dirs) {
                dataDirs.addAll(FileUtils.listFiles(new File(dir), null, false));
            }
            this.fileIterator = dataDirs.iterator();
            if (!fileIterator.hasNext()) {
                throw new IllegalArgumentException("Data dir is empty ! " + this.dataFileDir);
            }
        }
        if (null == lineIterator) {
            lineIterator = FileUtils.lineIterator(fileIterator.next());
        }
    }


    private JSONObject nextLine()
    {
        try {
            if (!fileIterator.hasNext() && !lineIterator.hasNext()) {
//                throw new IllegalStateException("data read over");
                //循环写数据文件
                close();
                fileIterator=null;
                lineIterator=null;
                initFileReader();
            }
            if (!lineIterator.hasNext()) {
                lineIterator.close();
                lineIterator = FileUtils.lineIterator(fileIterator.next());
            }
            String line = lineIterator.nextLine();
//            System.out.println("line = " + line);
            if (null != line) {
                return JSONObject.parseObject(line);
            } else {
                return nextLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close()
    {
        try {
            lineIterator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addMeybeArrayStringValue(JSONObject obj, String key,
                                                Consumer<String> setFunc)
    {
        //数据跳过
        if (String.valueOf(obj.get(key)).isEmpty()) {
            return;
        }

        String value = String.valueOf(obj.get(key));
        if (value.charAt(0) != '[') {
            //单值
            setFunc.accept(value);
        } else {
            //数组
            JSONArray arrays = obj.getJSONArray(key);
            for (int i = 0; i < arrays.size(); i++) {
                setFunc.accept(arrays.getString(i));
            }
        }
    }
    public List<byte[]> getDnsLog()
    {
        JSONObject obj = nextLine();
        AddressBookProtos.DNS.Builder pb = AddressBookProtos.DNS.newBuilder();
//        Sensor.NgfwSensor.DNS.Builder pb = Sensor.NgfwSensor.DNS.newBuilder();
        pb.setSerialNum(obj.getString("serial_num"));
        /* 193 */
//        pb.setAccessTime(obj.getString("access_time"));
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 194 */
        pb.setDnsType(obj.getInteger("dns_type"));
        /* 195 */
        pb.setSip(obj.getString("sip"));
        /* 196 */
        pb.setSport(obj.getInteger("sport"));
        /* 197 */
        pb.setDip(obj.getString("dip"));
        /* 198 */
        pb.setDport(obj.getInteger("dport"));
        /* 199 */
        pb.setHost(obj.getString("host"));
        /* 200 */
        pb.setVendorId(obj.getString("vendor_id"));
        /* 201 */
        pb.setDeviceIp(obj.getString("device_ip"));
        pb.setSipv6(obj.getString("sipv6"));
        pb.setDipv6(obj.getString("dipv6"));
        //pb.setHostRaw(obj.getString("host_raw"));
        //pb.setHostReraw(obj.getString("host_reraw"));
        //pb.setHostMd5(obj.getString("host_md5"));
//        pb.setCname(obj.getString("cname"));
//        pb.setAddr(obj.getString("addr"));
//        pb.setMx(obj.getString("mx"));
//        pb.setTxt(obj.getString("txt"));
        addMeybeArrayStringValue(obj,"cname", pb::addCname);
        addMeybeArrayStringValue(obj, "addr", pb::addAddr);
        addMeybeArrayStringValue(obj, "mx", pb::addMx);
        addMeybeArrayStringValue(obj, "txt", pb::addTxt);

        pb.setCount(obj.getString("dipv6"));
        if (!obj.getString("reply_code").isEmpty()) {
            pb.setReplyCode(Integer.parseInt(obj.getString("reply_code")));
        }
        /* 202 */
//        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        AddressBookProtos.SENSOR_LOG.Builder pbdata = AddressBookProtos.SENSOR_LOG.newBuilder();
        /* 203 */
        pbdata.setSkyeyeDns(pb);
        /* 204 */
        pbdata.setMessageType(3);
        /* 205 */
//        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        AddressBookProtos.SENSOR_LOG data = pbdata.build();
        return Arrays.asList(data.toByteArray());
    }

    public List<byte[]> getTcpflowLog()
    {
        JSONObject obj = nextLine();
//        Sensor.NgfwSensor.TCPFLOW.Builder pb = Sensor.NgfwSensor.TCPFLOW.newBuilder();
        AddressBookProtos.TCPFLOW.Builder pb = AddressBookProtos.TCPFLOW.newBuilder();
        /* 232 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 233 */
        pb.setSerialNum(obj.getString("serial_num"));
        /* 234 */
        pb.setStatus(obj.getString("status"));
        /* 235 */
        pb.setSip(obj.getString("sip"));
        /* 236 */
        pb.setDip(obj.getString("dip"));
        /* 238 */
        pb.setSport(obj.getInteger("sport"));
        /* 239 */
        pb.setDport(obj.getInteger("dport"));
        /* 240 */
        pb.setSipv6(obj.getString("sipv6"));
        /* 241 */
        pb.setDipv6(obj.getString("dipv6"));
        /* 242 */
        pb.setDtime(obj.getString("dtime").concat(".000"));
        /* 243 */
        pb.setProto(obj.getString("proto"));
        /* 244 */
        pb.setUplinkLength(obj.getInteger("uplink_length"));
        /* 245 */
        pb.setDownlinkLength(obj.getInteger("downlink_length"));
        /* 246 */
        pb.setUplinkPkts(obj.getInteger("uplink_pkts"));
        /* 247 */
        pb.setDownlinkPkts(obj.getInteger("downlink_pkts"));
        /* 248 */
        pb.setClientOs(obj.getString("client_os"));
        /* 249 */
        pb.setServerOs(obj.getString("server_os"));
        /* 250 */
        pb.setSrcMac(obj.getString("src_mac"));
        /* 251 */
        pb.setDstMac(obj.getString("dst_mac"));
        pb.setDownPayload(obj.getString("down_payload"));
        pb.setUpPayload(obj.getString("up_payload"));
        pb.setStime(Util.TimeUtil.getTimeMs());
        /* 252 */
        pb.setSummary(obj.getString("summary"));
        /* 253 */
//        pb.setAppType(obj.getInteger("app_type"));
        /* 254 */
        pb.setVendorId(obj.getString("vendor_id"));
        /* 255 */
        pb.setDeviceIp(obj.getString("device_ip"));
//        pb.set\@Timestamp(obj.getString("@timestamp"));
//        pb.set\@Version(obj.getString("@version"));
        /* 256 */
//        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        AddressBookProtos.SENSOR_LOG.Builder pbdata = AddressBookProtos.SENSOR_LOG.newBuilder();
        /* 257 */
        pbdata.setSkyeyeTcpflow(pb);
        /* 258 */
        pbdata.setMessageType(2);
        /* 259 */
//        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        AddressBookProtos.SENSOR_LOG data = pbdata.build();
        /* 260 */
        return Arrays.asList(data.toByteArray());
    }

    public List<byte[]> getWebLog()
    {
        JSONObject obj = nextLine();
//        Sensor.NgfwSensor.WEBLOG.Builder pb = Sensor.NgfwSensor.WEBLOG.newBuilder();
        AddressBookProtos.WEBLOG.Builder pb = AddressBookProtos.WEBLOG.newBuilder();

        /* 539 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 540 */
        pb.setSerialNum(obj.getString("serial_num"));
        pb.setMethod(obj.getString("method"));
        /* 541 */
        pb.setSip(obj.getString("sip"));
        /* 542 */
        pb.setSport(obj.getInteger("sport"));
        /* 543 */
        pb.setDip(obj.getString("dip"));
        /* 544 */
        pb.setDport(obj.getInteger("dport"));
        pb.setSipv6(obj.getString("sipv6"));
        pb.setDipv6(obj.getString("dipv6"));
        /* 545 */
        pb.setUri(ByteString.copyFromUtf8(obj.getString("uri")));
        //pb.setUriRaw(obj.getString("uri_raw"));
        //pb.setUriReraw(obj.getString("uri_reraw"));
        //pb.setUriMd5(obj.getString("uri_md5"));
        /* 546 */
        pb.setHost(obj.getString("host"));
        //pb.setHostRaw(obj.getString("host_raw"));
        //pb.setHostReraw(obj.getString("host_reraw"));
        //pb.setHostMd5(obj.getString("host_md5"));
        /* 547 */
        pb.setStatus(obj.getInteger("status"));
        /* 548 */
        pb.setOrigin(obj.getString("origin"));
        pb.setCookie(obj.getString("cookie"));
        /* 549 */
        pb.setAgent(obj.getString("agent"));
        /* 550 */
        pb.setData(ByteString.copyFromUtf8(obj.getString("data")));
        pb.setReferer(obj.getString("referer"));
        pb.setXff(obj.getString("xff"));
//        pb.setWeibo(obj.getString("weibo"));
//        pb.setQq(obj.getString("qq"));
//        pb.setMid(obj.getString("mid"));
        pb.setSetcookie(obj.getString("setcookie"));
        pb.setContentType(obj.getString("content_type"));
        /* 551 */
        pb.setAcceptLanguage(obj.getString("accept_language"));
        /* 552 */
//        pb.setUrlcategory(obj.getInteger("urlcategory"));
        /* 555 */
        pb.setVendorId(obj.getString("vendor_id"));
        /* 556 */
        pb.setDeviceIp(obj.getString("device_ip"));

        JSONArray ipJson = obj.getJSONArray("ip_list");
        if (ipJson != null) {
            List<String> ipList = new ArrayList<>();
            ipJson.forEach(ip -> {
                if (ip != null) {
                    ipList.add(ip.toString());
                }
            });
            pb.addAllIpList(ipList);
        }
        /* 557 */
//        Sensor.NgfwSensor.SENSOR_LOG.Builder pbdata = Sensor.NgfwSensor.SENSOR_LOG.newBuilder();
        AddressBookProtos.SENSOR_LOG.Builder pbdata = AddressBookProtos.SENSOR_LOG.newBuilder();

        /* 558 */
        pbdata.setSkyeyeWeblog(pb);
        /* 559 */
        pbdata.setMessageType(4);
        /* 560 */
//        Sensor.NgfwSensor.SENSOR_LOG data = pbdata.build();
        AddressBookProtos.SENSOR_LOG data = pbdata.build();

        /* 561 */
        return Arrays.asList(data.toByteArray());
    }

    public List<byte[]> getFileLog() {
        /* 348 */
        JSONObject obj = nextLine();
        /* 349 */
        AddressBookProtos.FILE_BEHAVIOR.Builder pb = AddressBookProtos.FILE_BEHAVIOR.newBuilder();
        /* 350 */
        pb.setAccessTime(Util.TimeUtil.getTimeMs());
        /* 350 */
//        Map<String, Integer> pP = getProtoPort("file");
//        /* 351 */
//        /* 351 */
//        String proto = pP.keySet().toArray()[0].toString();
//        /* 352 */
//        int port = ((Integer) pP.get(proto)).intValue();
        /* 353 */
        /* 354 */
        pb.setSerialNum(obj.getString("serial_num"));
        /* 355 */
        pb.setProto(obj.getString("proto"));
        /* 356 */
        pb.setSip(obj.getString("sip"));
        /* 357 */
        pb.setSport(obj.getInteger("sport"));
        /* 358 */
        pb.setDip(obj.getString("dip"));
        /* 359 */
        pb.setDport(obj.getInteger("dport"));
        /* 360 */
        pb.setUri(ByteString.copyFromUtf8(obj.getString("uri")));
        /* 361 */
        pb.setHost(obj.getString("host"));
        /* 362 */
        pb.setStatus(obj.getString("status"));
        /* 363 */
        pb.setFileDir(1);
        /* 364 */
        pb.setFileMd5(obj.getString("file_md5"));
        /* 365 */
        pb.setFilename(obj.getString("filename"));
        /* 366 */
        pb.setMimeType(obj.getString("mime_type"));
        /* 367 */
        pb.setMethod(obj.getString("method"));
        /* 368 */
        pb.setVendorId(obj.getString("vendor_id"));
        /* 369 */
        pb.setDeviceIp(obj.getString("device_ip"));
        /* 370 */
        AddressBookProtos.SENSOR_LOG.Builder pbdata = AddressBookProtos.SENSOR_LOG.newBuilder();
        /* 371 */
        pbdata.setSkyeyeFile(pb);
        /* 372 */
        pbdata.setMessageType(5);
        /* 373 */
        AddressBookProtos.SENSOR_LOG data = pbdata.build();
        /* 374 */

        /* 375 */
        return  Arrays.asList(data.toByteArray());
        /*     */
    }

    public static void test() throws IOException
    {
        //dns
//        String dir = "D:\\aa\\dns";
//        LogSourceFromFile lsff = LogSourceFromFile.logSourceFromFile(dir);
//
//        for (;;) {
//            List<byte[]> dnsLog = lsff.getDnsLog();
//            System.out.println("dnsLog = " + dnsLog);
//        }

        //tcp
//        String dirTcp = "D:\\aa\\tcp";
//        LogSourceFromFile lsffTcp = LogSourceFromFile.logSourceFromFile(dirTcp);
//
//        for (;;) {
//            List<byte[]> tcpflowLog = lsffTcp.getTcpflowLog();
//            System.out.println("tcpflowLog = " + tcpflowLog);
//        }

        //tcp
        String dirWeb = "D:\\aa\\web";
        LogSourceFromFile lsffWeb = LogSourceFromFile.logSourceFromFile(dirWeb);

        for (;;) {
            List<byte[]> webLog = lsffWeb.getWebLog();
            System.out.println("webLog = " + webLog);
        }

//        lsff.close();


    }

    public static void main(String[] args) throws IOException
    {
        test();
    }

}
