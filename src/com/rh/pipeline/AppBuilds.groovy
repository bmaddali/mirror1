package com.rh.pipeline

//class AppBuilds {
    
    def buildWithMaven(mvnConfigFileID, args){
        if (!mvnConfigFileID) throw new IllegalArgumentException("The parameter 'mvnConfigFileID' can not be null or empty.")
        try {
            withMaven(maven: 'maven', mavenSettingsConfig: mvnConfigFileID){
                sh "mvn -o ${args}"
            }
        } catch(err) {
            throw(err)
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

//}
