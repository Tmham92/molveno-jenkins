pipeline {
    agent any
    stages {
        stage('build') {
            steps {
		checkout scm
		sh 'mvn package -D maven.test.skip=true -f pom.xml'
		sh 'docker build -f molveno-app-jenkins'
            }
        }
    }
}
