workflowJob('expensetrackerworkflow') {
    definition {
        cps {
            script(readFileFromWorkspace('expense-tracker-workflow.groovy'))
        }
    }
}
