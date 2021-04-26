FROM centos:7.5.1804
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN yum -y update && yum -y install epel-release && yum -y install redis

EXPOSE 6379

RUN yum clean all

RUN sed -i -e 's@bind 127.0.0.1@bind 0.0.0.0@g' /etc/redis.conf
RUN sed -i -e 's@protected-mode yes@protected-mode no@g' /etc/redis.conf
RUN echo "requirepass mypassword" >> /etc/redis.conf

ENTRYPOINT [ "/usr/bin/redis-server","/etc/redis.conf"]
