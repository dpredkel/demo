pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('check java') {
            sh "java -version"
        }
        stage('Compile') {
            steps {
                gradlew('clean', 'classes')
            }
        }

        stage('Long-running Verification') {
            environment {
                SONAR_TOKEN = credentials('SONARCLOUD_TOKEN')
            }
            stage('Unit Tests') {
                steps {
                    gradlew('test')
                }
                post {
                    always {
                        junit '**/build/test-results/test/TEST-*.xml'
                    }
                }
            }
            stage('sonar') {
                steps {
                    gradlew('sonarqube')
                }
            }
        }
        stage('Promotion') {
            steps {
                timeout(time: 1, unit:'DAYS') {
                    input 'Deploy to ?'
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
//         stage('Assemble') {
//             steps {
//                 gradlew('assemble')
//                 stash includes: '**/build/libs/*.war', name: 'app'
//             }
//         }
//         stage('Promotion') {
//             steps {
//                 timeout(time: 1, unit:'DAYS') {
//                     input 'Deploy to Production?'
//                 }
//             }
//         }
//         stage('Deploy to Production') {
//             environment {
//                 HEROKU_API_KEY = credentials('HEROKU_API_KEY')
//             }
//             steps {
//                 unstash 'app'
//                 gradlew('deployHeroku')
//             }
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