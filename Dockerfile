FROM hmctspublic.azurecr.io/base/java:openjdk-8-distroless-1.4

COPY build/libs/ccpay-scheduled-jobs-1.2.7.jar /opt/app/

CMD ["ccpay-scheduled-jobs-1.2.7.jar"]