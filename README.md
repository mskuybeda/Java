# Java
Banking app:
Archivation:
jar cvf program.jar -C src .

Go to archive directory:
jar xf program.jar
cd main/java/
javac edu/btp400/w2017/server/RemoteBankServer.java
javac edu/btp400/w2017/client/FinancialApp.java
java edu/btp400/w2017/server/RemoteBankServer
// in new window go to archive folder
cd main/java/
java edu/btp400/w2017/client/FinancialApp
