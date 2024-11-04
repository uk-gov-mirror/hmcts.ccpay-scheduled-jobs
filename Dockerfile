ARG APP_INSIGHTS_AGENT_VERSION=3.5.2
FROM hmctspublic.azurecr.io/base/java:21-distroless

USER hmcts
COPY lib/applicationinsights.json /opt/app/
COPY build/libs/ccpay-scheduled-jobs.jar /opt/app/

CMD ["ccpay-scheduled-jobs.jar"]

CMD [ \
    "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
    "ccpay-scheduled-jobs.jar" \
    ]
