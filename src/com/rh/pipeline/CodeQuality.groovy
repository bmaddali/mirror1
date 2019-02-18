package com.rh.pipeline

/*
class CodeQuality {

    static def runSonarScan(script, args){
        if (!args) throw new IllegalArgumentException("The parameter 'args' can not be null or empty.")
        try {
		    script.sh "${script.tool 'sonar'}/bin/sonar-scanner -X ${args}"
        } catch(err) {
            throw(err)
        }
    }
}
*/

def sonarScan(args){
    def scannerHome = tool 'sonar'
    withSonarQubeEnv('RH-SonarQube') {
        sh "${scannerHome}/bin/sonar-scanner -X ${args}"
    }
}