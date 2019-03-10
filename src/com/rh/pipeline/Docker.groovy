package com.rh.pipeline

class Docker {

    private String dockerRegistry = 'rhartifactory-rhapsody-docker-local.jfrog.io'
    private String registryUser = '4711f1eb-3218-4ce0-9b5c-0f9f43641469' //artifactory credentials ID
    private String appGroup //domain
    private String appName // miracledate
    private String imageTag //IMAGE_VERSION

    def publishAppImage(appGroup, appName, imageTag){

      //if (!appGroup) && (!appName) && (!imageTag)  throw new IllegalArgumentException("Error Missing Parameters, it can not be null or empty.")

        try {
            docker.withRegistry(dockerRegistry, registryUser) {
                def appImagePath = dockerRegistry + '/' + appGroup + '/' + appName + ':' + imageTag
                def buildImage = docker.build(appImagePath)
                buildImage.push(appImagePath)
            }
        } catch (e) {
            echo "Failed to Build Docker Image : ${e}"
            throw e
        }
    }
}
