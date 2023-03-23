# ccpay-scheduled-jobs
This repository is used to trigger the web jobs. This JAR has to be included as a dependency in **build.gradle** file of **ccpay-payment-app** module.

## Endpoint Details

The below are the endpoints details which is being invoked from **run.sh** files in **ccpay-payment-app** module.

1. bar-csv-report

POST - /jobs/email-pay-reports?service_name=Digital Bar

2. card-csv-report

POST - /jobs/email-pay-reports?payment_method=CARD

3. pba-csv-report

POST - /jobs/email-pay-reports?payment_method=PBA&service_name=Specified Money Claims

POST - /jobs/email-pay-reports?payment_method=PBA&service_name=Divorce

POST - /jobs/email-pay-reports?payment_method=PBA&service_name=Financial Remedy

POST - /jobs/email-pay-reports?payment_method=PBA&service_name=Probate

4. pba-finrem-weekly-csv-report

POST - /jobs/email-pay-reports?payment_method=PBA&service_name=Financial Remedy&start_date="+date

5. status-update

PATCH - /jobs/card-payments-status-update
