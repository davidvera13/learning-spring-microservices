## Notes

### Using jib
Git repository: https://github.com/GoogleContainerTools/jib
For maven configuration : https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin
Advantage: google jib allow to create a docker image even without having a docker installation. 

Add to pom.xml 

    <project>
      ...
      <build>
        <plugins>
          ...
          <plugin>
            <groupId>com.google.cloud.tools</groupId>
            <artifactId>jib-maven-plugin</artifactId>
            <version>3.3.2</version>
            <configuration>
                <to>
                    <image>davidvera/${project.artifactId}:${project.version}</image>
                </to>
                <from>
                    <image>eclipse-temurin:21.0.5_11-jdk</image>
                </from>
            </configuration>
          </plugin>
          ...
        </plugins>
      </build>
      ...
    </project>

### build docker image

    mvn compile jib:dockerBuild