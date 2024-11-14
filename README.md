# DataFlood
For the stability test of big data platform continuously write data and query data

这个项目的主要功能是从日志文件中读取日志数据，然后将日志数据发送到Kafka。它支持多种日志类型，包括TCPFLOW、DNS、WEBLOG、FILE、MAIL、LOGIN、DB、ATTACK、LDAP、SSL、FTPOP、UDPFLOW、ABNORMAL、TELNET、IOC等。每个日志类型都有一个对应的Kafka主题，可以配置日志发送的速度和日志文件的路径。

## 技术原理

连接到kafka，发送大量日志信息给kafka



### Protobuf

用于日志的序列化和反序列化。protobuf是一种高效的序列化格式，可以减少网络传输的数据量，提高传输效率



### SLF4J

用于日志记录。SLF4J是一个日志框架，可以方便地记录日志信息。



### Kryo

用于对象的序列化和反序列化。Kryo是一个高性能的序列化库，可以快速地序列化和反序列化对象。



### Jackson

用于JSON的序列化和反序列化。Jackson是一个流行的JSON处理库，可以方便地将Java对象转换为JSON格式，或者将JSON转换为Java对象。



### 其他

HTTPClient：用于HTTP请求。HTTPClient是一个HTTP客户端库，可以发送HTTP请求，接收HTTP响应。

JSON-Simple：用于JSON的处理。JSON-Simple是一个简单的JSON处理库，可以方便地解析和生成JSON数据。

Apache Commons IO：用于文件操作。Apache Commons IO是一个Java库，提供了一些常用的文件操作功能。
