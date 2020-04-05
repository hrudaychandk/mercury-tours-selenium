node {
    stage ('SCM checkout') {
        git 'https://github.com/hrudaychandk/mercury-tours-selenium.git'
    }
    stage ('Selenium tests') {
        sh "mvn clean install"
    }
    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
    }
}
