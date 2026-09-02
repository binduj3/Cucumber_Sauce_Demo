pipeline {
    agent any
    stages {
        stage('Run Smoke Tests') {
            steps {
                sh "mvn test -Dcucumber.filter.tags=@smoke"
            }
        }
    }
}