node {
    stage('Checkout from GitHub') {
        checkout([$class: 'GitSCM', branches: [[name: 'PR-\\d+']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GH', url: 'https://github.com/mkgerasimenko/framework-testNg.git']]])
    }
    stage('Run Selenium tests') {
        sh '''
            chmod +x gradlew
            ./gradlew clean test
        '''
    }
    stage('Generate report') {
        allure includeProperties: false, jdk: 'JDK 8u152', results: [[path: 'build/allure-results']]
    }
}
