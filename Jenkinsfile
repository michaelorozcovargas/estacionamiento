pipeline {
	//Donde se va a ejecutar el Pipeline
	agent {
		label 'Slave_Induccion'
	}
	//Opciones especificas de Pipeline dentro del Pipeline
	options {
		//Mantener artefactos y salida de consola para el # especifico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '3'))
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()
	}
	//Una seccion que define las herramientas para autoinstalacion y poner en la PATH
	tools {
		jdk 'JDK8_Centos' //Preinstalada en la Configuracion del Master
		gradle 'Gradle4.5_Centos' //Preinstalada en la Configuracion del Master
	}
	stages{
		/* Checkout del codigo fuente
		stage('Checkout') {
			steps{
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], 
				gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'bd154c89-0003-4200-a4ca-09a2ce6c1c24', url:'https://github.com/michaelorozcovargas/estacionamiento']]])
			}
		}*/
		// Pruebas unitarias
		stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle cleanTest test'
			}
		}
		// Analisis de codigo estatico
		stage('Static Code Analysis') {
			steps{
				echo '------------>Analisis de codigo estatico<------------'
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
				}
			}
		}
		// Construccion del proyecto
		stage('Build') {
			steps{
				echo "------------>Build<------------"
				sh 'gradle --b ./build.gradle compileJava'
				
				//Construir sin tarea test que se ejecuto previamente
				// sh 'gradle --b ./build.gradle build -x test'
			}
		}
	}
	// Pasos posteriores
	post {
		// Ante fallo
		failure {
			echo 'This will run only if failed'
			mail (to: 'michael.orozco@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
		}
		// Ante exito
		success {
			echo 'This will run only if successful'
			junit '**/jacoco/test-results/*.xml'
		}
	}
}