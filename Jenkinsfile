pipeline {
  agent any
  stages {
    stage('clean and build') {
      steps {
        bat 'mvn -B clean'
      }
    }

    stage('Windows Testing') {
        steps {
            script {
                try {
                    bat 'mvn test'
                    build_ok = true
                }
                catch(e) {
                    build_ok = false
                    echo e.toString()
                }
            }
        }
    }

    stage('Reporting') {
        parallel {
            stage('Record Jacoco') {
              steps {
                jacoco()
              }
            }

            stage('Record Junit') {
              steps {
                junit 'target/surefire-reports/*.xml'
              }
            }

            stage('Capture PMD') {
              steps {
                pmd()
              }
            }

            stage('Capture findbugs') {
              steps {
                findbugs()
              }
            }


        }
    }

    stage('create package') {
          when {
            branch 'master'
          }
          post {
            success {
              echo 'Now Archiving...'
              archiveArtifacts 'target/*.jar'
            }

          }
          steps {
            script {
                if (build_ok) {
                    bat 'mvn package verify'
                    currentBuild.result = "SUCCESS"
                }
                else {
                    currentBuild.result = "FAIL"
                }
            }

          }
    }

    stage('Clean environment') {
      steps {
        bat 'mvn clean'
      }
    }

  }
}
