#/bin/sh 

for dir in `ls -l | awk '/^d/{print $9}'`; 
do 
    cd $dir ;
    echo "current direcory:"$dir;
    ./restart.sh
    cd .. ; 
done
