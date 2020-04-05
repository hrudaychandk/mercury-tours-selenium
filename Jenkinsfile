node {
    stage ('SCM checkout') {
        git 'https://github.com/hrudaychandk/mercury-tours-selenium.git'
    }
    stage ('Selenium tests') {
        dir("mercury-tours-selenium") {
            sh “mvn clean install”
        }
    }
}
