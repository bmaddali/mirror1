package com.rh.pipeline

class AppBuilds implements Serializable {

    def steps

    AppBuilds(steps){
        this.steps = steps
    }

    def buildWithMaven(mvnArgs){
        if (!mvnArgs) throw new IllegalArgumentException("Maven Build Arguments can not be null or empty.")
        def mvn = steps.tool 'maven'
        steps.withMaven(maven: 'maven', mavenSettingsConfig: 'GlobalMavenSettings', options: [junitPublisher(disabled: false), artifactsPublisher(disabled: false), jacocoPublisher(disabled: false)], tempBinDir: '') {
            steps.sh "${mvn}/bin/mvn -o ${mvnArgs}"
        }
    }

    def buildWithGradle() {

    }

    def buildWithGrunt(){

    }

    def buildWithNpm(){

    }

    def buildWithPip(){

    }

}
