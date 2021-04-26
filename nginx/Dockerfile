FROM centos:centos7

ADD default.conf /etc/nginx/conf.d/default.conf
ADD nginx.repo /etc/yum.repos.d/
RUN yum install -y nginx
EXPOSE 80

CMD ["/usr/sbin/nginx", "-g","daemon off;"]