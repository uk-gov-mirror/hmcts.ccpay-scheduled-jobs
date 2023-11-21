FROM hmctspublic.azurecr.io/base/java:21-distroless

COPY build/libs/ccpay-scheduled-jobs.jar /opt/app/

CMD ["ccpay-scheduled-jobs.jar"]

CMD [ \
    "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
    "ccpay-scheduled-jobs.jar" \
    ]
