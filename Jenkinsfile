pipeline {
    agent any

    environment {
        REMOTE_HOST = "172.31.76.227"   // your tomcat server
        REMOTE_USER = "ubuntu"
        TOMCAT_PATH = "/var/lib/tomcat10/webapps"
        APP_NAME = "myapp.war"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Verify WAR') {
            steps {
                sh 'ls -l target/*.war'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sshagent(['tomcat-server']) {
                    sh """
                    scp -o StrictHostKeyChecking=no target/*.war ${REMOTE_USER}@${REMOTE_HOST}:/tmp/${APP_NAME}

                    ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} << EOF
                        sudo systemctl stop tomcat10
                        rm -rf ${TOMCAT_PATH}/${APP_NAME}
                        cp /tmp/${APP_NAME} ${TOMCAT_PATH}/
                        sudo systemctl start tomcat10
                    EOF
                    """
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                echo "Deployment completed successfully"
            }
        }
    }
}
