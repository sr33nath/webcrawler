# Getting Started

### Introduction
This is a simple web crawler. Once this project is built and run, it will expose an API. This API can be used to receive the sitemap of URLs the program has crawled through. The output of the API call will be in JSON format.

### Get Started

####Requirements
Operating System:  
Ubuntu 20.04 (or any other linux distro)

Software:  
Java JDK 11  
Gradle 6.8.3  
Git  

#### How to install JDK 11  
[This page has a good guide on how to install JDK on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04)
#### How to install Gradle
[Here is good guide on how to install gradle on Ubuntu 20.04](https://linuxize.com/post/how-to-install-gradle-on-ubuntu-20-04/)  
#### How to install Git
[Installing Git on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-git-on-ubuntu-20-04)  

#### Testing JDK, Gradle and Git installation
Open a terminal on your linux machine and type in below commands. You should receive the something similar to the outputs shown below if the installation has been successful.  
###### Command
```
java -version
```
###### Output
```
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment (build 11.0.10+9-Ubuntu-0ubuntu1.20.04)
OpenJDK 64-Bit Server VM (build 11.0.10+9-Ubuntu-0ubuntu1.20.04, mixed mode, sharing)
```
###### Command
```
git --version
```
###### Output
```
git version 2.25.1
```
###### Command
```
gradle -v
```
###### Output
```
------------------------------------------------------------
Gradle 6.8.3
------------------------------------------------------------

Build time:   2021-02-22 16:13:28 UTC
Revision:     9e26b4a9ebb910eaa1b8da8ff8575e514bc61c78

Kotlin:       1.4.20
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
JVM:          11.0.10 (Ubuntu 11.0.10+9-Ubuntu-0ubuntu1.20.04)
OS:           Linux 5.8.0-50-generic amd64
```
If all the outputs on your terminal look similar, then we are one step closer to running the web crawler.  

### Building the web crawler
Execute the below commands one by one to clone and build our web crawler.  
###### Commands
```
cd /tmp
git clone https://github.com/sreenathisapro/webcrawler.git
cd webcrawler/
gradle build
```

Once all the above commands are execute, we are ready to start our web crawler API. Execute the below commands to start our web crawler API.
###### Commands
```
cd build/libs
java -jar webcrawler-0.0.1-SNAPSHOT.jar
```

Once the above commands are run you will be able to see an output like below. Which means our API is up and running and ready to receive some url to crawl.
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.5)

2021-04-28 03:01:48.513  INFO 12421 --- [           main] c.s.webcrawler.WebcrawlerApplication     : Starting WebcrawlerApplication using Java 11.0.10 on homepc with PID 12421 (/home/homepc/webcrawler/build/classes/java/main started by crano in /home/homepc/webcrawler)
2021-04-28 03:01:48.516  INFO 12421 --- [           main] c.s.webcrawler.WebcrawlerApplication     : No active profile set, falling back to default profiles: default
2021-04-28 03:01:49.409  INFO 12421 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-04-28 03:01:49.421  INFO 12421 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-04-28 03:01:49.422  INFO 12421 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.45]
2021-04-28 03:01:49.487  INFO 12421 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-04-28 03:01:49.487  INFO 12421 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 888 ms
2021-04-28 03:01:49.675  INFO 12421 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-04-28 03:01:49.842  INFO 12421 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-04-28 03:01:49.852  INFO 12421 --- [           main] c.s.webcrawler.WebcrawlerApplication     : Started WebcrawlerApplication in 1.771 seconds (JVM running for 2.102)
```
### Testing
Once our API is up and running you can test the API using CURL like show below.
```
curl -v http://localhost:8080/crawl?url=https%3A%2F%2Fwww.crawler-test.com%2Fmobile%2Fresponsive
```

###### Output

```
* Trying 127.0.0.1:8080...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /crawl?url=https%3A%2F%2Fwww.crawler-test.com%2Fmobile%2Fresponsive HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.68.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 256
< Date: Tue, 27 Apr 2021 20:53:09 GMT
< 
* Connection #0 to host localhost left intact
{"url":"https://www.crawler-test.com/mobile/responsive","childLinks":[{"url":"/","childLinks":null},{"url":"/css/app.css","childLinks":null},{"url":"/favicon.ico?r=1.6","childLinks":null},{"url":"/bower_components/jquery/jquery.min.js","childLinks":null}]}
```

In the above example I tried to crawl through this [url](https://www.crawler-test.com/mobile/responsive). Just remember that before passing a URL to the above API, the URL should be properly URL encoded. Refer to the url parameter in the CURL command.
