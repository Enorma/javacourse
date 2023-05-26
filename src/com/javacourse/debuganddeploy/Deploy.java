package com.javacourse.debuganddeploy;

/*
    //project class for Course 1 Section 5: Debug & Deploy

    //import to Main class with:
    import com.javacourse.debuganddeploy.Deploy; //or...
    import com.javacourse.debuganddeploy.*;

    //call from main() method with:
    com.javacourse.debuganddeploy.Deploy.deployExample(); //or...
    Deploy.deployExample();
*/

public class Deploy {

    public static void deployExample() {

        //how to deploy a JAR
        //01: file > project structure > artifacts
        //02: add new artifact (click the + at the top) > JAR > from modules with dependencies
        //03: select module that contains all packages we want
        //04: select class where main() is
        //05: select "extract to the target JAR" radiobutton
        //06: hit OK, then Apply, then OK
        //07: on the top menu, select Build > build artifacts... > [artifact name]:jar > Build
        //08: the JAR file was created in [project name]/out/artifacts/[project name]_jar/[project name].jar
        //09: right click the JAR, select open in > terminal
        //10: run the JAR with command: java -jar .\[JAR file]

        System.out.println("Hello, world!");
    }
}
//Debug

//eof
