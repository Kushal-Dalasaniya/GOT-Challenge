# GOT-Challenge

# DEPLOYMENT INSTRUCTION  

After unzipping the folder, import the GOT project.

	Import the project in eclips
	1.From the File menu, select Import. The Select screen opens.
	2. Expand Maven and select Existing Maven Projects.
	3. Click Next.
	4. For Root Directory, click Browse and select the top-level project folder.
	5. Verify that the Projects list includes all subprojects and click Finish.
	
	For Import poject in intellij you can go https://www.lagomframework.com/documentation/1.6.x/java/IntellijMaven.html
	
Please include your database password and URL in the application.properties file. Additionally, create the DemandFarm and execute the following query to create the required table:
	
    CREATE TABLE character_info (
      character_id SERIAL PRIMARY KEY,
      character_link VARCHAR(255),
      actor_name VARCHAR(255),
      actor_link VARCHAR(255),
      charname VARCHAR(255) UNIQUE,
      royal VARCHAR(1),
      character_image_thumb VARCHAR(255),
      character_image_full VARCHAR(255),
      nickname VARCHAR(255),
      housename VARCHAR(255),
      kingsguard VARCHAR(1),
      favourite VARCHAR(1)
    );

    CREATE TABLE char_relation (
      main_char INTEGER REFERENCES character_info(character_id),
      second_char INTEGER REFERENCES character_info(character_id),
      relation VARCHAR(255)
    );

After importing the project, right-click on the project and select "Run As" followed by "Maven Install". Once the build is successful, you can run the project.

Executing "Maven Install" will generate the necessary model classes and interfaces required for the project.
