.DEFAULT_GOAL := all
CHART := charts/payment-jobs
RELEASE := chart-payment-jobs-release
NAMESPACE := fees-pay
TEST := ${RELEASE}-test-service
ACR := hmctspublic
ACR_SUBSCRIPTION := DCD-CNP-DEV
AKS_RESOURCE_GROUP := cnp-aks-rg
AKS_CLUSTER := cnp-aks-cluster

setup:
	az account set --subscription ${ACR_SUBSCRIPTION}
	az configure --defaults acr=${ACR}
	az acr helm repo add

clean:
	-helm uninstall ${RELEASE} -n ${NAMESPACE}
	-kubectl delete pod ${TEST} -n ${NAMESPACE}

lint:
	helm lint ${CHART} -f charts/payment-jobs/ci-values.yaml

template:
	helm template ${CHART} -f charts/payment-jobs/ci-values.yaml

deploy:
	helm install ${RELEASE} ${CHART} --namespace ${NAMESPACE} -f charts/payment-jobs/values.yaml  -f charts/payment-jobs/ci-values.yaml --wait --timeout 60s

dry-run:
	helm dependency update ${CHART} 
	helm install ${RELEASE} ${CHART} --namespace ${NAMESPACE} -f charts/payment-jobs/values.yaml -f charts/payment-jobs/ci-values.yaml --dry-run --debug

test:
	helm test ${RELEASE}

all: setup clean lint deploy test

.PHONY: setup clean lint deploy test all