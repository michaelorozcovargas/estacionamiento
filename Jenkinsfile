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
		jdk 'JDK8_Centos' //Preinstalada en la ConfiguraciÃ³n del Master
		gradle 'Gradle4.5_Centos' //Preinstalada en la ConfiguraciÃ³n del Master
	}
	stages{
		// Checkout del codigo fuente
		stage('Checkout') {
			steps{
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], 
				gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_michaelorozcovargas', url:'https://github.com/michaelorozcovargas/estacionamiento']]])
			}
		}
		// Pruebas unitarias
		stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle test'
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
}