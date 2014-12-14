@echo off
echo ** Signature du fichier JAR **
jarsigner -keystore store/group9store -storepass group9storepass -keypass group9keypass -signedjar ./applet/Applet.jar ./dist/GameOfLife.jar group9
echo ** Signature OK ! **
pause