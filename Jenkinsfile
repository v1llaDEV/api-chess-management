pipeline{
	agent any
		tools {
			maven "Maven-3.8.6"
		}
	stages {
	   stage('SETTING GIT CREDENTIALS'){
		   steps{
			git credentialsId: 'git-creds', url: 'https://github.com/v1llaDEV/api-chess-management'
		   }
	   }
	   stage('BUILDING'){
		   steps{
			sh "mvn clean package"
		   }
		 
	   }
	   
	}
}