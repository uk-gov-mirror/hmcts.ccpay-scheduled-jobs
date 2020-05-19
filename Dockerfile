FROM hmctspublic.azurecr.io/base/java:openjdk-8-distroless-1.4

COPY build/libs/ccpay-scheduled-jobs.jar /opt/app/

CMD ["ccpay-scheduled-jobs.jar"]