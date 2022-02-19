#!/bin/bash
# Test de la passe 3 avec JaCoCo

rm -f jacoco.exec

for fich in cas/*.cas
do
    echo "---------------------------------------------------------------------"
    echo "Fichier : $fich"
    echo "---------------------------------------------------------------------"
    cd ../../classes ; java -javaagent:../lib/jacocoagent.jar=destfile=../test/gencode/jacoco.exec -cp .:../lib/java-cup-11a-runtime.jar fr.esisar.compilation.gencode/JCasc ../test/gencode/$fich ; cd ../test/gencode
done

