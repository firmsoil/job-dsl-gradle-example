workflowJob('expensetrackerworkflow') {
    definition {
        cps {
            //script(readFileFromWorkspace('expense-tracker-wf.groovy'))
            
            script(

node()  {

    // Checkout my source
    git credentialsId: 'c07c84c0-468b-44f1-8d84-b0f366e7d511', url: 'https://github.com/infostretch-cd/expense-tracker'

// Set a particular maven install to use for this build
//def mvnHome = tool 'mvn'
//env.PATH = "${mvnHome}/bin:${env.PATH}"

    cleanUp()
    codeQAAnalysis ()
    devQABuild ()
    packageBuild ()
    deployBuild ()
    step([$class: 'JUnitResultArchiver', testResults: 'expense-tracker/build/test-results/debug/*.xml'])

}

def cleanUp () {

// Clean Up Stage
    stage 'CLEAN'
    sh '/Users/isc_2246/StudioProjects/gradlew clean'
    echo 'Cleaning up from prior build(s)'

}

def codeQAAnalysis () {

// QA Analytics Stage
    stage 'QA-ANALYSIS'
    echo 'Generating Sonar Report'
    sh 'cd /Users/Shared/Jenkins/Home/workspace/infostretch-cd-wf-expense-tracker;/etc/sonar-runner/sonar-runner-2.4/bin/sonar-runner'

//Sonar Execution Placeholder

}

def devQABuild () {

// Execute Build
    stage 'BUILD'
    echo 'Building Up...'
    sh '/Users/isc_2246/StudioProjects/gradlew build'

// Run Junit Tests and Publish Report
    stage 'UnitTest/DevTest'
    echo 'Executing JUnit Tests'
// step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

}

def packageBuild () {

//input message: "Does QA look good? Please confirm to package build."
// def checkPassCriterion() { Implementation Driven by your favourite Sonar Profile
    //QA Metric Threshold like Unit Test Pass %, Blocker Issue Severity, et al}

try {

    /*
     * Assuming significant time was spent up until this point, establish a
     * safe point so this build can be rusumed from here if this build fails after
     * this.
     */

    checkpoint('Before Staging')

} catch (NoSuchMethodError _) {

    // Please log in to DCT Jenkins Enterprise,
    // and continue with the rest of the workflow

    echo 'Checkpoint feature available in DCT Jenkins.'
}


// Store Build Package
stage 'STORE PACKAGE', concurrency: 1
echo 'Storing Build Package...'
sh "curl -v -u admin:admin123 --upload-file /Users/Shared/Jenkins/Home/workspace/infostretch-cd-wf-expense-tracker/expense-tracker/build/outputs/apk/expense-tracker-debug.apk http://localhost:8081/nexus/content/repositories/snapshots/"
//echo 'executing... gradle assemble package'
}

def deployBuild () {

//input message: "Does Package look good? Please confirm to Deploy"

// Deploy Build Package
    stage name: 'STAGING', concurrency: 1
    echo 'Deploying Package to STAGING/UAT…'
// sh "mvn clean deploy" // Uncomment after configuring deployment caontainer
// sleep(3000)
    echo 'executing... mvn clean deploy'
// sleep(3000)

}

       ) // script
       
        }
    }
}
