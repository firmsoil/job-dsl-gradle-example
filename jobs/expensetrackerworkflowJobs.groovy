workflowJob('expensetrackerworkflow') {
    definition {
        cps {
            script(readFileFromWorkspace('https://github.com/firmsoil/job-dsl-gradle-example/blob/master/jobs/expense-tracker-wf.groovy'))
        }
    }
}
