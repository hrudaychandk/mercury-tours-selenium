pipeline {
    agent any
    stages {
        stage ('SCM checkout') {
            steps {
                git 'https://github.com/hrudaychandk/mercury-tours-selenium.git'
            }
        }
        stage ('Selenium tests') {
            steps {
                sh "mvn clean install"
            }
        }
    }
    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
    }
}
