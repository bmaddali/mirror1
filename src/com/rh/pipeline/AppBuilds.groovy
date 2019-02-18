package com.rh.pipeline

class AppBuilds {
    
    static def buildWithMaven(script, mvnConfigFilePath, args){
        if (!mvnConfigFilePath) throw new IllegalArgumentException("The parameter 'mvnConfigFilePath' can not be null or empty.")
        try {
            withMaven(maven: 'maven', mavenSettingsConfig: mvnConfigFilePath) {
                script.sh "mvn -o ${args}"
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

}
