pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Clean') {
            steps {
                gradlew('clean')
            }
        }

        stage('Checks and tests') {
            steps {
                gradlew('check')
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage('Promotion') {
            steps {
                timeout(time: 1, unit:'DAYS') {
                    input 'Deploy to prod?'
                }
            }
        }
//         stage('Publish') {
//                     steps {
//                         dir('build/docker') {
//                             script{
//                                 withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
//                                     sh 'docker login -u ${dockerHubUser} -p ${dockerHubPassword}'
//                                     sh 'docker push pokl/test:latest'
//                                 }
//                             }
//                         }
//                     }
//                 }
//                 stage('Deploy') {
//                     steps {
//                         script {
//                             withKubeConfig([credentialsId: 'service-account', serverUrl:"${KUBERNETES_URL}"]){
//                                 try {
//                                     sh 'kubectl delete -f k8s/'
//                                 } catch (e) {
//                                     echo 'Error when executing kubectl'
//                                 }
//                                 sh 'kubectl apply -f k8s/'
//                             }
//                         }
//                     }
//         }
    }
    post {
        failure {
            mail to: 'dpredkel@gmail.com', subject: 'Build failed', body: 'Please fix!'
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}