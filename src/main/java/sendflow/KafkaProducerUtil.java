/*    */ package sendflow;
/*    */ 
/*    */ import java.util.Properties;

/*    */ import org.apache.kafka.clients.producer.KafkaProducer;
/*    */ import org.apache.kafka.clients.producer.Producer;
/*    */ import org.apache.kafka.clients.producer.ProducerRecord;
/*    */ 
/*    */ public class KafkaProducerUtil
/*    */ {
/*    */   public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
/*    */   public static final String KEY_SERIALIZER = "key.serializer";
/*    */   public static final String VALUE_SERIALIZER = "value.serializer";
/*    */   public static final String STRING_SERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringSerializer";
/*    */   public static final String BYTE_SERIALIZER_CLASS = "org.apache.kafka.common.serialization.ByteArraySerializer";
/*    */   public static final String CONFIG_TOPIC_KEY = "topic";
/*    */   private Producer producer;
/*    */   private String brokerlist;
/*    */   private Properties kafkaProperties;
/*    */   private boolean isStringValue;
/*    */   private static KafkaProducerUtil instance;
/*    */   
/*    */   private KafkaProducerUtil(String brokerlist, Properties kafkaProperties, boolean isStringValue)
/*    */   {
/* 25 */     this.brokerlist = brokerlist;
/* 26 */     this.kafkaProperties = kafkaProperties;
/* 27 */     this.isStringValue = isStringValue;
/*    */     
/* 29 */     com.google.common.base.Preconditions.checkArgument(this.brokerlist != null, "kafka brokerList is blank...");
/*    */     
/*    */ 
/* 32 */     Properties properties = new Properties();
/* 33 */     properties.putAll(this.kafkaProperties);
/* 34 */     properties.put("bootstrap.servers", this.brokerlist);
             properties.put("linger.ms",200);
/*    */     
/* 36 */     if (this.isStringValue) {
/* 37 */       this.producer = new KafkaProducer(properties);
/*    */     } else {
/* 39 */       this.producer = new KafkaProducer(properties);
/*    */     }
/*    */   }
/*    */   
/*    */   public static KafkaProducerUtil getKafkaProducer(String brokerList, String topic, boolean isStringValue) {
/* 44 */     Properties properties = new Properties();
/* 45 */     properties.put("topic", topic);
             properties.put("linger.ms",200);
             properties.put("batch.size",49152);
             properties.put("buffer.memory",53554432);

/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 54 */     if (isStringValue) {
/* 55 */       properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
/* 56 */       properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
/*    */     } else {
/* 58 */       properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
/* 59 */       properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
/*    */     }
/*    */     
/* 62 */     instance = new KafkaProducerUtil(brokerList, properties, isStringValue);
/*    */     
/* 64 */     return instance;
/*    */   }


    public static KafkaProducerUtil getKafkaProducer(String brokerList, String topic, boolean isStringValue , String krb5Conf , String krb5Config) {
        Properties properties = new Properties();
        properties.put("topic", topic);


        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put( "sasl.kerberos.service.name", "kafka");
        properties.put("sasl.mechanism", "GSSAPI");
        properties.put("java.security.krb5.conf",krb5Conf);
        properties.put("java.security.auth.login.config",krb5Config);


        if (isStringValue) {
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }else {
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        }

        instance = new KafkaProducerUtil(brokerList, properties, isStringValue);
        return instance;
   }


/*    */   
/*    */   public void shutdown() {
/* 68 */     this.producer.close();
/*    */   }
/*    */   
/*    */   public void send(ProducerRecord<String, String> keyedMessage) {
/* 72 */     this.producer.send(keyedMessage);
/*    */   }
/*    */   
/*    */   public void sendBytes(ProducerRecord<String, byte[]> keyedMessage) {
/* 76 */     this.producer.send(keyedMessage);
/*    */   }
/*    */ }


/* Location:              E:\2NGSOC\测试工具\flowSender.jar!\sendflow\KafkaProducerUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */