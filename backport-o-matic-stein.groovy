podTemplate {
    node("autoscale") {
        stage('Run shell') {
            container('uca-bom-container') {
                sh """
                    # We can't execute that in the docker image
                    # because of the security context. Some work are in
                    # progress to have ability to build with --priviledged
                    mk-sbuild --type=file xenial

                    export TARGET=xenial-queens
                    export OS_SERIES=queens
                    export TESTING_PPA=ppa:openstack-ubuntu-testing/queens
                    /bin/bash /root/bom.sh
                    """
            }
        }
    }
}
