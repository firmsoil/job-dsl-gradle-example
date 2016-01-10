/**
 * Created by isc_2246 on 1/10/16.
 */

String basePath = 'expense-tracker'
String repo = 'firmsoil/expense-tracker'

folder(basePath) {
    description 'This example shows basic folder/job creation.'
}


job("$basePath/expense-tracker-base-build") {
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        grails {
            useWrapper true
            targets(['test-app', 'war'])
        }
    }
}
