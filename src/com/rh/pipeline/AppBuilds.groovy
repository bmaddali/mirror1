package com.rh.pipeline

class AppBuilds {
    
    static def buildWithMaven(script, mvnConfigFileID, args){
        if (!mvnConfigFileID) throw new IllegalArgumentException("The parameter 'mvnConfigFileID' can not be null or empty.")
        try {
            configFileProvider([configFile(fileId: mvnConfigFileID, variable: 'PACKER_OPTIONS')]) {
                script.sh("cat ${env.PACKER_OPTIONS}")
                script.sh "${script.tool 'maven'}/bin/mvn -s ${env.PACKER_OPTIONS} -o ${args}"
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
