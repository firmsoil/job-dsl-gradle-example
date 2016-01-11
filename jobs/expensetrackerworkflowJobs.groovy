workflowJob('expensetrackerworkflow') {
    
        triggers {
        scm 'H/5 * * * *'
    }
    definition {
        cps {
            script(readFileFromWorkspace('resources/expense-tracker-wf.groovy'))
        }
    }
}
