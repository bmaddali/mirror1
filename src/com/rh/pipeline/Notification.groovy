def notifyDeveloperBySlack(String buildStatus, String toChannel){
  // build status of null means successful
  buildStatus = buildStatus ?: 'STARTED'
  def colorCode = '#FF0000'
  def summary = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
  try {
    if (buildStatus == 'STARTED' || buildStatus == 'UNSTABLE') {
    colorCode = '#FFFF00'
    // YELLOW
    } else if (buildStatus == 'SUCCESS') {
    colorCode = '#00FF00'
    // GREEN
    } else {
    colorCode = '#FF0000'
    // RED
    }
    // Send slack notifications all messages
    slackSend (color: colorCode, message: summary, channel: toChannel)
  } catch (e) {
    echo "Failed to send Slack notification : ${e}"
    throw e
  }
}//end notifyDeveloperBySlack

def notifyPromoterBySlack(String toChannel, String toApprover){
  def summary = "Deployment to Production requires approval from ${toApprover} for `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}input"

  try {
    // Send slack notifications all messages
    slackSend (message: summary, channel: toChannel)
    input (message: "Deployment Approval?", ok: 'Approve', submitter: "${toApprover}", submitterParameter: 'submitter')
  } catch (e) {
    echo "Failed to send Slack notification : ${e}"
    throw e
  }
}//end notifyPromoterBySlack
