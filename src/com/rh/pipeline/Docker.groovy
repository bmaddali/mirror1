package com.rh.pipeline

class Docker implements Serializable {

    def steps

    Docker(steps){
        this.steps = steps
    }

    private String dockerRegistry = 'rhartifactory-rhapsody-docker-local.jfrog.io'
    private String registryUser = 'artifactory-rhapsody' //artifactory credentials ID
    private String appGroup //domain
    private String appName // miracledate
    private String imageTag //IMAGE_VERSION

    def publishAppImage(appGroup, appName, imageTag){

      //if (!appGroup) && (!appName) && (!imageTag)  throw new IllegalArgumentException("Error Missing Parameters, it can not be null or empty.")

        try {
            steps.docker.withRegistry("https://${dockerRegistry}", registryUser) {
                def appImagePath = dockerRegistry + '/' + appGroup + '/' + appName + ':' + imageTag
                def buildImage = docker.build(appImagePath)
                buildImage.push(appImagePath)
            }
        } catch (e) {
            // echo "Failed to Build Docker Image : ${e}"
            throw e
        }
    }
}
