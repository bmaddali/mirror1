package com.rh.pipeline

import com.rh.pipeline.Notification

/*
class Deployment{
    private String aws_account_number
    private String deploy_env
    private String kubectl_contex_name
    private String deploy_release_name
    private String image_version
    private String tiller_namespace
    private String cluster_domain
    private String service_release_name
    private String helm_config_yaml
*/

    def deployApplication(String aws_account_number, string deploy_env="nonprod", String kubectl_contex_name, String deploy_release_name,  String tiller_namespace, String helm_config_yaml, String image_version) {
        def notifyUtils = new Notification()
        if (deploy_env == "production" || deploy_env == "prodpci"){
            notifyUtils.notifyPromoterBySlack("#jenkins-ci-prod", "bmaddali,jedwards")
            input (message: "Deployment Approval?", ok: 'Approve', submitter: "bmaddali,jedwards", submitterParameter: 'submitter')
        } else {
            echo "Approval isn't required!!"
        }
        sh """\
            #!/bin/bash
            set -ex

            KUBECONFIG=/var/lib/jenkins/.kube/config-cijenkins-${kubectl_contex_name} \
            /usr/local/bin/helm upgrade --install --version 1.7.0 --debug  ${deploy_release_name} \
            --set service.version=${image_version} --set env=${deploy_env} \
            --set-string awsAccount=${aws_account_number} --set image.tag=${image_version} \
            --tiller-namespace=${tiller_namespace} --namespace=${tiller_namespace} \
            -f ${helm_config_yaml} rhapsody-helm/rhapsody-deployment
         """
        sleep(time:120,unit:"SECONDS")
    }

    def deployService(String deploy_env="nonprod", String kubelet_context_name, String service_release_name, String tiller_namespace, String helm_config_yaml, String cluster_domain) {
        def notifyUtils = new Notification()
        if (deploy_env == "production" || deploy_env == "prodpci"){
            notifyUtils.notifyPromoterBySlack("#jenkins-ci-prod", "bmaddali,jedwards")
            input (message: "Deployment Approval?", ok: 'Approve', submitter: "bmaddali,jedwards", submitterParameter: 'submitter')
        } else {
            echo "Approval isn't required!!"
        }
        sh """\
            #!/bin/bash
            set -ex

            KUBECONFIG=/var/lib/jenkins/.kube/config-cijenkins-${kubelet_context_name} \
            /usr/local/bin/helm upgrade --install --version 1.6.0 --debug ${service_release_name} \
            --tiller-namespace=${tiller_namespace} --namespace=${tiller_namespace} \
            --set clusterDomain=${cluster_domain} -f ${helm_config_yaml} rhapsody-helm/rhapsody-service
        """
        sleep(time:75,unit:"SECONDS")
    }

// }// end class
