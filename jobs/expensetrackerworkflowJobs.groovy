workflowJob('expensetrackerworkflow') {
    definition {
        cps {
            script(readFileFromWorkspace('expense-tracker-wf.groovy'))
        }
    }
}
