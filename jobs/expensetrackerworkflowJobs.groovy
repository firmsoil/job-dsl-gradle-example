workflowJob('expensetrackerworkflow') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/expense-tracker-wf.groovy'))
        }
    }
}
