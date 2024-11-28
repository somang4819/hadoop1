# Base image
FROM apache/hadoop:3.4.0

# yum 레포지터리 업데이트
RUN echo "https://vault.centos.org/7.6.1810/os/x86_64/" | sudo tee /var/cache/yum/x86_64/7/base/mirrorlist.txt > /dev/null
RUN echo "https://vault.centos.org/7.6.1810/extras/x86_64/" | sudo tee /var/cache/yum/x86_64/7/extras/mirrorlist.txt > /dev/null
RUN echo "https://vault.centos.org/7.6.1810/updates/x86_64/" | sudo tee /var/cache/yum/x86_64/7/updates/mirrorlist.txt > /dev/null

RUN sudo yum install -y epel-release

RUN sudo yum update -y 
RUN sudo yum install python36 python34-setuptools -y 
RUN sudo yum remove -y java-1.8.0-openjdk
RUN sudo yum install -y java-1.8.0-openjdk-devel

# 환경변수
ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0
ENV PATH=${JAVA_HOME}/bin:${PATH}
ENV HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar

RUN mkdir -p /opt/hadoop/work

