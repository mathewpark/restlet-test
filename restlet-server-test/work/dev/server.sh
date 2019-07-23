if [ -z $2 ]
then
	JAR_NAME=server.jar
else
	JAR_NAME=$2
fi

DAEMON_HOME=/root/workspace/restlet-server-test

JSVC=$DAEMON_HOME/jsvc

USER=root

JAVA_HOME=$(dirname $(dirname $(dirname  $(readlink -f  /usr/bin/java))))/

PID_FILE=$DAEMON_HOME/daemon_$JAR_NAME.pid
OUT_FILE=$DAEMON_HOME/daemon_$JAR_NAME.out
#ERR_FILE=$DAEMON_HOME/daemon.err

CLASSPATH=$DAEMON_HOME/$JAR_NAME
 
MAIN_CLASS=com.example.server.DaemonImpl

GC_LOGGING_FLAGS='-verbose:gc -Xloggc:/var/log/restlet-server-test/restlet-server-test_gc.log -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails'
GC_FLAGS='-XX:ParallelGCThreads=8 -XX:+UseConcMarkSweepGC'

JAVA_FLAGS="$GC_LOGGING_FLAGS $GC_FLAGSi"

function start {
	echo "start restlet-server-test"
    #
    # Start Daemon
    #
    rm -f $OUT_FILE
    $JSVC \
	-server \
    -user $USER \
    -java-home $JAVA_HOME \
    -pidfile $PID_FILE \
    -outfile $OUT_FILE \
    -errfile $OUT_FILE \
	$JAVA_FLAGS \
    -cp $CLASSPATH \
    $MAIN_CLASS
    #
    # To get a verbose JVM
    #-verbose \
    # To get a debug of jsvc.
    #-debug \
}
function stop {
	echo "stop restlet-server-test"
    #
    # Stop Daemon
    #
    $JSVC \
	-stop \
    -nodetach \
    -java-home $JAVA_HOME \
    -pidfile $PID_FILE \
    -outfile $OUT_FILE \
    -errfile $OUT_FILE \
	$JAVA_FLAGS \
    -cp $CLASSPATH \
    $MAIN_CLASS
}

# start lustre
case "$1" in
  start)
    start
    exit $?
    ;;
 
  stop)
    stop
    exit $?
    ;;
    
  restart)
    stop
    start
    exit $?
    ;;
  *)
    echo "[Usage] server.sh start | stop | restart"
    exit 1;;
esac