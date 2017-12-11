# spring-jmx
共四个服务，一个Eureka服务，一个Admin服务，两个客户端服务。通过Redis存储两个客户端关键参数值，并在Admin服务中显示参数值的和
## eureka-server
注册中心，端口8761，其他三个服务都要注册在eureka-server
## admin-server
Spring Boot Admin Server模块，注册到eureka-server，端口号8090，用来监控jmx-client和jmx-client-2相关信息，并有UI界面显示。并自定义MBean将jmx-client和jmx-client-2中参数maxPostSize和maxThreads加和显示在JMX中。
## jmx-client
客户端，注册到eureka-server，端口号8081，自定义MBean显示Tomcat中参数maxPostSize和maxThreads。
## jmx-client-2
客户端，注册到eureka-server，是jmx-client拷贝，端口号8082，其他内容相同
