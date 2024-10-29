## Notes

### Configuring swagger:
#### Adding dependency: 

    <!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.6.0</version>
    </dependency>

### Adding annotations to class & methods
In the main class : 
![images/img_3.png](../images/img_3.png)

Will generate the swagger header:

![images/img_4.png](../images/img_4.png)

In the controller :
![images/img_5.png](../images/img_5.png)

Will generate the swagger header:

![images/img_6.png](../images/img_6.png)

In the controller methods: 

![images/img_7.png](../images/img_7.png)

Will generate the method information & expected responses definitions:

![images/img_8.png](../images/img_8.png)

In the DTO, we can pass information such as description and sample data:

![images/img_9.png](../images/img_9.png)

We can have the sample in swagger

![images/img_10.png](../images/img_10.png)

We also find same information in schemas part of swagger

![images/img_11.png](../images/img_11.png)



