FROM ubuntu:14.04

# curl for downloading java
# python, gcc and build-essential for compiling node-gyp modules
RUN apt-get update && apt-get install -y \
    git \
    curl \
    python2.7 \
    gcc \
    build-essential

WORKDIR /opt

RUN curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" \
        http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz | tar -zxv
RUN ln -s jdk* /opt/jdk

RUN locale-gen en_US.UTF-8

ARG UID
ARG GID

RUN groupadd -g $GID app && useradd -u $UID -m -d /app -g app app
USER app

ENV JAVA_HOME /opt/jdk
COPY entrypoint.sh /

RUN mkdir /app/.gradle
VOLUME /app/sources
VOLUME /app/.gradle

WORKDIR /app/sources

ENTRYPOINT ["/entrypoint.sh"]
