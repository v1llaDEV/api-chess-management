node{
   stage('SETTING GIT CREDENTIALS'){
       git credentialsId: 'git-creds', url: 'https://github.com/v1llaDEV/api-chess-management'
   }
   stage('BUILDING'){
     def mvnHome = tool name: 'Maven-3.8.6', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     sh "${mvnCMD} clean package"
   }
   stage('DOCKERIZE IMAGE'){
     sh 'docker build -t kammana/my-app:2.0.0 .'
	 withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
        sh "docker login -u kammana -p ${dockerHubPwd}"
     }
     sh 'docker push kammana/my-app:2.0.0'
   }
   
   stage('RUN IN CONTAINER'){
     def dockerRun = 'docker run -p 8080:8080 -d --name my-app kammana/my-app:2.0.0'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.18.198 ${dockerRun}"
     }
   }
}