pipeline{
    agent any

    stages{


        stage('Cloning from GitHub') {
                steps {
                    git branch: 'main', url: 'https://github.com/mohamedhabibmsahel/SpringAOP'
                }
                
            }
        stage('MVN COMPILE') {
                  steps {
                     sh 'mvn compile'
                 }
              }
                stage('mvn Test') {
                  steps {
                     sh 'mvn test'
                  }
              }
                stage('mvn Verify') {
                   steps {
                     sh 'mvn verify'
                }
             }

         stage('SonarQube Analysis'){
                steps {
                    sh "mvn sonar:sonar \
  -Dsonar.projectKey=sonar \
  -Dsonar.host.url=http://192.168.33.10:9000 \
  -Dsonar.login=b363068e908831f3c25a2c783bdadb206cb77bfb"
                }
                
            }
                stage('mvn clean package') {
                               steps {
                                 sh 'mvn clean package'
                            }
                         }
       stage('Nexus'){
            steps{
                sh 'mvn deploy -DskipTests'
            }
        }
        stage("Building Docker Image") {
                steps{
                    sh 'docker build -t mohamedhabibmsahel123/achat .'
                }
        }
     
         stage("Login to DockerHub") {
                steps{
                   // sh 'sudo chmod 666 /var/run/docker.sock'
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u mohamedhabibmsahel123 -p juka98473912azerty'
                }
        }
        
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push mohamedhabibmsahel123/achat'
                }
        }
    
               stage("Docker-compose") {
                steps{
                    sh 'docker-compose up -d'
                }
        }
    }
      
    }
