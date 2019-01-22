package com.rh.pipeline

class Deployment{

    private String kubectl_contex_name
    private String deploy_release_name
    private String image_version
    private String tiller_namespace
    private String cluster_domain
    private String service_release_name
    private String helm_config_yaml

    def deployApplication(kubectl_contex_name, deploy_release_name,  tiller_namespace, helm_config_yaml, image_version) {

        sh """\
            #!/bin/bash
            set -ex

            KUBECONFIG=/var/lib/jenkins/.kube/config-cijenkins-${kubectl_contex_name} \
            /usr/local/bin/helm upgrade --install --debug  ${deploy_release_name} \
            --set service.version=${image_version} --set image.tag=${image_version} \
            --tiller-namespace=${tiller_namespace} --namespace=${tiller_namespace} \
            -f ${helm_config_yaml} rhapsody-helm/rhapsody-deployment
         """
        sleep(time:120,unit:"SECONDS")
    }

    def deployService(kubelet_context_name, service_release_name, tiller_namespace, helm_config_yaml, cluster_domain) {
        sh """\
            #!/bin/bash
            set -ex

            KUBECONFIG=/var/lib/jenkins/.kube/config-cijenkins-${kubelet_context_name} \
            /usr/local/bin/helm upgrade --install --debug ${service_release_name} \
            --tiller-namespace=${tiller_namespace} --namespace=${tiller_namespace} \
            --set clusterDomain=${cluster_domain} -f ${helm_config_yaml} rhapsody-helm/rhapsody-service
        """
        sleep(time:75,unit:"SECONDS")
    }

}// end class
