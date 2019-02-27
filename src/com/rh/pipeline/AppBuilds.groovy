package com.rh.pipeline

class AppBuilds implements Serializable {

    def steps

    AppBuilds(steps){
        this.steps = steps
    }

    def buildWithMaven(mvnConfigFileID, mvnArgs){
        //if (!mvnConfigFileID) throw new IllegalArgumentException("The parameter 'mvnConfigFileID' can not be null or empty.") 
        def mvn = steps.tool 'maven'
        steps.withMaven(maven: 'maven', mavenSettingsConfig: "${mvnConfigFileID}" ){
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
