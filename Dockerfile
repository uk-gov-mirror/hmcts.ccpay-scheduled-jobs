ARG APP_INSIGHTS_AGENT_VERSION=3.7.8
ARG BASE_REGISTRY=hmctsprod.azurecr.io
FROM ${BASE_REGISTRY}/base/java:21-distroless

USER hmcts
COPY lib/applicationinsights.json /opt/app/
COPY build/libs/ccpay-scheduled-jobs.jar /opt/app/

CMD ["ccpay-scheduled-jobs.jar"]

CMD [ \
    "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
    "ccpay-scheduled-jobs.jar" \
    ]
