FROM ubuntu:14.04

# curl for downloading java
RUN apt-get update && apt-get install -y \
    curl

WORKDIR /opt

RUN curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" \
        http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/server-jre-8u131-linux-x64.tar.gz | tar -zxv
RUN ln -s jdk1.8.* /opt/jdk
ENV PATH /opt/jdk/bin:$PATH

RUN locale-gen en_US.UTF-8

RUN groupadd app && useradd -m -d /app -g app app
USER app

WORKDIR /app

COPY app.jar /app/app.jar

COPY entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]

