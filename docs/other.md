# 其他说明

## 依赖包说明

* org.yaml:snakeyaml:1.21：用于处理YAML文件。
* com.fasterxml.jackson.core:jackson-databind:2.10.0：用于JSON的序列化和反序列化。
* commons-lang:commons-lang:2.6：提供了一些常用的Java语言功能。
* com.google.protobuf:protobuf-java:3.18.0：用于处理Protocol Buffers。
* com.alibaba:fastjson:1.2.73：用于处理JSON数据。
* commons-codec:commons-codec:1.9：提供了一些常用的编码和解码功能。
* io.protostuff:protostuff-core:1.6.0：用于处理Protocol Buffers。
* io.protostuff:protostuff-runtime:1.6.0：用于处理Protocol Buffers。
* com.baidu:jprotobuf:2.2.2：用于处理Protocol Buffers。
* org.slf4j:slf4j-api:1.7.25：用于日志记录。
* com.esotericsoftware.kryo:kryo:2.24.0：用于对象的序列化和反序列化。
* com.github.seancfoley:ipaddress:4.2.0：用于处理IP地址。
* org.apache.httpcomponents:httpclient:4.5.5：用于发送HTTP请求。
* org.apache.kafka:kafka_2.11:2.4.1：用于处理Kafka。
* com.googlecode.json-simple:json-simple:1.1.1：用于处理JSON数据。
* commons-io:commons-io:2.6：提供了一些常用的文件操作功能。

## 构建说明

* 构建项目：使用mvn clean package命令可以构建项目，并生成一个包含所有依赖的JAR文件。
* 发布项目：使用mvn deploy命令可以将项目发布到Maven仓库。
* 生成Protobuf代码：使用mvn protobuf:compile命令可以生成Protocol Buffers的Java代码。
* 生成gRPC代码：使用mvn protobuf:compile-custom命令可以生成gRPC的Java代码。
* 打包入口在sendflow.FlowSender类中，该类是项目的入口点，使用mvn clean package命令可以构建项目，并生成一个包含所有依赖的JAR文件。