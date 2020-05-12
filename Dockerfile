FROM hmcts/cnp-java-base:openjdk-8u191-jre-alpine3.9-1.0

ENV JAVA_OPTS ""

COPY build/libs/ccpay-scheduled-jobs-1.2.7.jar /opt/app/

CMD ["ccpay-scheduled-jobs-1.2.7.jar"]